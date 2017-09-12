
 	 package com.biderp.bean; 

 	 import org.apache.commons.logging.Log; 
 	 import org.apache.commons.logging.LogFactory; 
 	 import cms.service.app.ApplicationConstants; 
	 import cms.service.event.EventListener;
	 import cms.service.event.QueryEvent;
	 import cms.service.template.*; 
	 /** A simple bean that has a single String property 
	 *  called message. 
 	 *  
	 * @author S.K Jana Version 1.0 
 	 * @Copyright : This code belongs to BidERP.com. All right reserved! 
 	 * @since 2005-2017 
 	 */ 

	 public class LeadImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(LeadImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String leadnotexml,leadnotedeleteid;
		 protected String leademailxml,leademaildeleteid;
		 protected String leadaccessxml,leadaccessdeleteid;

		 protected TemplateTable leadnotedata=new TemplateTable();

		 protected TemplateTable leademaildata=new TemplateTable();

		 protected TemplateTable leadaccessdata=new TemplateTable();

		 public String LeadFilter="messagequeue.name AssignTo@messagequeue,lead@nvl(lead.lead2messagequeue,0)=messagequeue.objid(+) order by lead.name";
		 public String LeadnoteFilter="";
		 public String LeademailFilter="";
		 public String LeadaccessFilter="";

		 public void setObject(String object){
			 this.object=object;
		 }
		 public String getObject(){
			 return(this.object);
		 }
		 public void setFilters(String filters){
			 this.filters=filters;
		 }
		 public String getFilters(){
			 return(filters);
		 }
		 public void setBqn(String bqn){
			 this.bqn=bqn;
		 }
		 public String getBqn(){
			 return(bqn);
		 }
		 public void setClientip(String clientip){
			 this.clientip=clientip;
		 }
		 public String getClientip(){
			 return(clientip);
		 }
		 public void setPagesize(int pagesize){
			 this.pagesize=pagesize;
		 }
		 public  int getPagesize(){
			 return(pagesize);
		 }
		 public void setPage(int page){
			 this.page=page;
		 }
		 public int getPage(){
			 return(page);
		 }
		 public void setToken(String token){
			 this.token=token;
		 }
		 public String getToken(){
			 return(token);
		 }
		 public void setUsername(String username){
			 this.username=username;
		 }
		 public String getUsername(){
			 return(username);
		 }
		 public void setMainxml(String mainxml){
			 this.mainxml=mainxml;
		 }
		 public String getMainxml(){
			 return(mainxml);
		 }
		 public void setGroupuser(String groupuser){
			 this.groupuser=groupuser;
		 }
		 public String getGroupuser(){
			 return(groupuser);
		 }
		 public void setSearchdata(String searchdata){
			 this.searchdata=searchdata;
		 }
		 public String getSearchdata(){
			 return(searchdata);
		 }
		 public void setParentobjid(String parentobjid){
			 this.parentobjid=parentobjid;
		 }
		 public String getParentobjid(){
			 return(parentobjid);
		 }
		 public String getLeadnotexml() {
			 return leadnotexml;
		 }
		 public void setLeadnotexml(String leadnotexml) {
			 this.leadnotexml=leadnotexml;
		 }
		 public String getLeadnotedeleteid() {
			 return leadnotedeleteid;
		 }
		 public void setLeadnotedeleteid(String leadnotedeleteid) {
			 this.leadnotedeleteid=leadnotedeleteid;
		 }
		 public String getLeademailxml() {
			 return leademailxml;
		 }
		 public void setLeademailxml(String leademailxml) {
			 this.leademailxml=leademailxml;
		 }
		 public String getLeademaildeleteid() {
			 return leademaildeleteid;
		 }
		 public void setLeademaildeleteid(String leademaildeleteid) {
			 this.leademaildeleteid=leademaildeleteid;
		 }
		 public String getLeadaccessxml() {
			 return leadaccessxml;
		 }
		 public void setLeadaccessxml(String leadaccessxml) {
			 this.leadaccessxml=leadaccessxml;
		 }
		 public String getLeadaccessdeleteid() {
			 return leadaccessdeleteid;
		 }
		 public void setLeadaccessdeleteid(String leadaccessdeleteid) {
			 this.leadaccessdeleteid=leadaccessdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Lead",column,datatype);
			 query.setUserName(this.getUsername());
			 if (tu.isEmptyValue(parentfilter)){
				 if(!tu.isEmptyValue(this.getParentobjid()))
					query.makeTableSelect(this.getObject(),"ObjId","=",this.getParentobjid(),column,datatype);
				 else
					query.makeSQL(this.getObject(),query.getArrayData(this.getSearchdata()),column,datatype);
			 }else{
				 if(!tu.isEmptyValue(this.getParentobjid()))
					 query.makeTableSelectObjectFilter(this.getObject(),"ObjId","=",this.getParentobjid(),column,datatype,parentfilter);
				 else
					 query.makeObjectFilterSQL(this.getObject(),query.getArrayData(this.getSearchdata()),column,datatype,parentfilter);
			 }
			 if(ACONST.GENERATE_LOG)
				 logger.info(query.getQuery());
			 if(this.getPage()>0){
				 int startrow=(this.getPage()-1)*getPagesize();
				 query.setStartRow(startrow);
				 query.setNumRows(getPagesize());
			}
			 maindata=query.getTableResultset();
					// do any post query operation for custom implementation
					EventListener.registerPostQueryParent("Lead",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Lead",childname,pid,relfield,column,datatype);
			if (tu.isEmptyValue(childfilter)){
				sql=query.makeChildSql(this.getObject(),childname,relfield,pid,column,datatype);
			}else{
					sql=query.makeChildObjectFilterSql(this.getObject(),childname,relfield,pid,column,datatype,childfilter);
			}
			query.setQuery(sql);
			data=query.getTableResultset();
			if(ACONST.GENERATE_LOG)
				logger.info(query.getQuery());
			if (data.getRowCount()>0){
			//Do some post query operation for child
					EventListener.registerPostQueryChild("Lead",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Lead",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Lead",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Lead",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Lead",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Lead",this.getParentobjid());
					return(true);
				}
			}
			return(false);
		}

		public  boolean  doInsert(){
			String sql=null;
			String usql=""; 
			TemplateQuery query =new TemplateQuery();
			if(!tu.isEmptyValue(this.getMainxml())){
					sql=query.makeBulkSQL(true,this.getMainxml(),"",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Lead",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Lead",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getLeadnotexml()) ){
					 sql+=query.makeBulkSQL(false,getLeadnotexml(),"LeadNote2Lead",this.getUsername(),this.getGroupuser());
					 leadnotedata=query.getTableData();
					 tu.applyObjectRule("Leadnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,leadnotedata);
					 EventListener.registerPreInsertEvent("LeadNote",leadnotedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"LeadNote","LeadNote2Lead",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getLeademailxml()) ){
					 sql+=query.makeBulkSQL(false,getLeademailxml(),"LeadEmail2Lead",this.getUsername(),this.getGroupuser());
					 leademaildata=query.getTableData();
					 tu.applyObjectRule("Leademail",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,leademaildata);
					 EventListener.registerPreInsertEvent("LeadEmail",leademaildata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"LeadEmail","LeadEmail2Lead",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getLeadaccessxml()) ){
					 sql+=query.makeBulkSQL(false,getLeadaccessxml(),"LeadAccess2Lead",this.getUsername(),this.getGroupuser());
					 leadaccessdata=query.getTableData();
					 tu.applyObjectRule("Leadaccess",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,leadaccessdata);
					 EventListener.registerPreInsertEvent("LeadAccess",leadaccessdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"LeadAccess","LeadAccess2Lead",getParentobjid());
				}
			 }
			 sql+="\t\t end;";
			 query.setQuery(sql);
			if(ACONST.GENERATE_LOG)
			 logger.info(query.getQuery());
			 if (query.getTableResultset().getRowCount()>0){
				
				 usql=(usql.equals("")?"":"\n\t begin"+usql +"\n\t end;");
				 if(!usql.equals(""))
					 tu.executeQuery(usql);
				if(ACONST.GENERATE_LOG)
					logger.info(usql);
				 tu.applyConsoleObject("Lead",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Lead",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Lead","Lead",getParentobjid());
				 EventListener.registerPostInsertEvent("Lead",maindata);
				 tu.applyObjectRule("Leadnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,leadnotedata);
				 tu.applyMTMRelation("leadnote","Lead",getParentobjid());
				 EventListener.registerPostInsertEvent("LeadNote",leadnotedata);
				 tu.applyObjectRule("Leademail",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,leademaildata);
				 tu.applyMTMRelation("leademail","Lead",getParentobjid());
				 EventListener.registerPostInsertEvent("LeadEmail",leademaildata);
				 tu.applyObjectRule("Leadaccess",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,leadaccessdata);
				 tu.applyMTMRelation("leadaccess","Lead",getParentobjid());
				 EventListener.registerPostInsertEvent("LeadAccess",leadaccessdata);
				 return(true);
			}
			 return(false);
		}
	}
