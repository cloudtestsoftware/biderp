
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

	 public class ProjectcontrolImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ProjectcontrolImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String currentplanxml,currentplandeleteid;
		 protected String partplanxml,partplandeleteid;
		 protected String resourceplanxml,resourceplandeleteid;

		 protected TemplateTable currentplandata=new TemplateTable();

		 protected TemplateTable partplandata=new TemplateTable();

		 protected TemplateTable resourceplandata=new TemplateTable();

		 public String ProjectcontrolFilter="";
		 public String CurrentplanFilter="";
		 public String PartplanFilter="";
		 public String ResourceplanFilter="";

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
		 public String getCurrentplanxml() {
			 return currentplanxml;
		 }
		 public void setCurrentplanxml(String currentplanxml) {
			 this.currentplanxml=currentplanxml;
		 }
		 public String getCurrentplandeleteid() {
			 return currentplandeleteid;
		 }
		 public void setCurrentplandeleteid(String currentplandeleteid) {
			 this.currentplandeleteid=currentplandeleteid;
		 }
		 public String getPartplanxml() {
			 return partplanxml;
		 }
		 public void setPartplanxml(String partplanxml) {
			 this.partplanxml=partplanxml;
		 }
		 public String getPartplandeleteid() {
			 return partplandeleteid;
		 }
		 public void setPartplandeleteid(String partplandeleteid) {
			 this.partplandeleteid=partplandeleteid;
		 }
		 public String getResourceplanxml() {
			 return resourceplanxml;
		 }
		 public void setResourceplanxml(String resourceplanxml) {
			 this.resourceplanxml=resourceplanxml;
		 }
		 public String getResourceplandeleteid() {
			 return resourceplandeleteid;
		 }
		 public void setResourceplandeleteid(String resourceplandeleteid) {
			 this.resourceplandeleteid=resourceplandeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Projectcontrol",column,datatype);
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
					EventListener.registerPostQueryParent("Projectcontrol",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Projectcontrol",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Projectcontrol",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Projectcontrol",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Projectcontrol",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Projectcontrol",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Projectcontrol",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Projectcontrol",this.getParentobjid());
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
					tu.applyObjectRule("Projectcontrol",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Projectcontrol",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getCurrentplanxml()) ){
					 sql+=query.makeBulkSQL(false,getCurrentplanxml(),"CurrentPlan2ProjectControl",this.getUsername(),this.getGroupuser());
					 currentplandata=query.getTableData();
					 tu.applyObjectRule("Currentplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,currentplandata);
					 EventListener.registerPreInsertEvent("CurrentPlan",currentplandata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"CurrentPlan","CurrentPlan2ProjectControl",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPartplanxml()) ){
					 sql+=query.makeBulkSQL(false,getPartplanxml(),"PartPlan2ProjectControl",this.getUsername(),this.getGroupuser());
					 partplandata=query.getTableData();
					 tu.applyObjectRule("Partplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partplandata);
					 EventListener.registerPreInsertEvent("PartPlan",partplandata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PartPlan","PartPlan2ProjectControl",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getResourceplanxml()) ){
					 sql+=query.makeBulkSQL(false,getResourceplanxml(),"ResourcePlan2ProjectControl",this.getUsername(),this.getGroupuser());
					 resourceplandata=query.getTableData();
					 tu.applyObjectRule("Resourceplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,resourceplandata);
					 EventListener.registerPreInsertEvent("ResourcePlan",resourceplandata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ResourcePlan","ResourcePlan2ProjectControl",getParentobjid());
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
				 tu.applyObjectRule("Projectcontrol",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Projectcontrol","Projectcontrol",getParentobjid());
				 EventListener.registerPostInsertEvent("Projectcontrol",maindata);
				 tu.applyObjectRule("Currentplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,currentplandata);
				 tu.applyMTMRelation("currentplan","Projectcontrol",getParentobjid());
				 EventListener.registerPostInsertEvent("CurrentPlan",currentplandata);
				 tu.applyObjectRule("Partplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partplandata);
				 tu.applyMTMRelation("partplan","Projectcontrol",getParentobjid());
				 EventListener.registerPostInsertEvent("PartPlan",partplandata);
				 tu.applyObjectRule("Resourceplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,resourceplandata);
				 tu.applyMTMRelation("resourceplan","Projectcontrol",getParentobjid());
				 EventListener.registerPostInsertEvent("ResourcePlan",resourceplandata);
				 return(true);
			}
			 return(false);
		}
	}
