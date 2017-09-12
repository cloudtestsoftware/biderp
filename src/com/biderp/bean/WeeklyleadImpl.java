
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

	 public class WeeklyleadImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(WeeklyleadImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String weekleadcontactxml,weekleadcontactdeleteid;
		 protected String weekleadjobsxml,weekleadjobsdeleteid;

		 protected TemplateTable weekleadcontactdata=new TemplateTable();

		 protected TemplateTable weekleadjobsdata=new TemplateTable();

		 public String WeeklyleadFilter="projectlead.listdate listdate@projectlead,weeklylead@projectlead.objid=weeklylead.objid order by to_char(projectlead.listdate,'mm/dd/yyyy') desc";
		 public String WeekleadcontactFilter="";
		 public String WeekleadjobsFilter="";

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
		 public String getWeekleadcontactxml() {
			 return weekleadcontactxml;
		 }
		 public void setWeekleadcontactxml(String weekleadcontactxml) {
			 this.weekleadcontactxml=weekleadcontactxml;
		 }
		 public String getWeekleadcontactdeleteid() {
			 return weekleadcontactdeleteid;
		 }
		 public void setWeekleadcontactdeleteid(String weekleadcontactdeleteid) {
			 this.weekleadcontactdeleteid=weekleadcontactdeleteid;
		 }
		 public String getWeekleadjobsxml() {
			 return weekleadjobsxml;
		 }
		 public void setWeekleadjobsxml(String weekleadjobsxml) {
			 this.weekleadjobsxml=weekleadjobsxml;
		 }
		 public String getWeekleadjobsdeleteid() {
			 return weekleadjobsdeleteid;
		 }
		 public void setWeekleadjobsdeleteid(String weekleadjobsdeleteid) {
			 this.weekleadjobsdeleteid=weekleadjobsdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Weeklylead",column,datatype);
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
					EventListener.registerPostQueryParent("Weeklylead",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Weeklylead",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Weeklylead",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Weeklylead",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Weeklylead",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Weeklylead",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Weeklylead",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Weeklylead",this.getParentobjid());
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
					tu.applyObjectRule("Weeklylead",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Weeklylead",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getWeekleadcontactxml()) ){
					 sql+=query.makeBulkSQL(false,getWeekleadcontactxml(),"WeekLeadContact2WeeklyLead",this.getUsername(),this.getGroupuser());
					 weekleadcontactdata=query.getTableData();
					 tu.applyObjectRule("Weekleadcontact",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,weekleadcontactdata);
					 EventListener.registerPreInsertEvent("WeekLeadContact",weekleadcontactdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"WeekLeadContact","WeekLeadContact2WeeklyLead",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getWeekleadjobsxml()) ){
					 sql+=query.makeBulkSQL(false,getWeekleadjobsxml(),"WeekLeadJobs2WeeklyLead",this.getUsername(),this.getGroupuser());
					 weekleadjobsdata=query.getTableData();
					 tu.applyObjectRule("Weekleadjobs",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,weekleadjobsdata);
					 EventListener.registerPreInsertEvent("WeekLeadJobs",weekleadjobsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"WeekLeadJobs","WeekLeadJobs2WeeklyLead",getParentobjid());
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
				 tu.applyObjectRule("Weeklylead",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Weeklylead","Weeklylead",getParentobjid());
				 EventListener.registerPostInsertEvent("Weeklylead",maindata);
				 tu.applyObjectRule("Weekleadcontact",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,weekleadcontactdata);
				 tu.applyMTMRelation("weekleadcontact","Weeklylead",getParentobjid());
				 EventListener.registerPostInsertEvent("WeekLeadContact",weekleadcontactdata);
				 tu.applyObjectRule("Weekleadjobs",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,weekleadjobsdata);
				 tu.applyMTMRelation("weekleadjobs","Weeklylead",getParentobjid());
				 EventListener.registerPostInsertEvent("WeekLeadJobs",weekleadjobsdata);
				 return(true);
			}
			 return(false);
		}
	}
