
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

	 public class PrivilegegroupImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(PrivilegegroupImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String userxml,userdeleteid;
		 protected String modulexml,moduledeleteid;
		 protected String objectprivilegexml,objectprivilegedeleteid;

		 protected TemplateTable userdata=new TemplateTable();

		 protected TemplateTable moduledata=new TemplateTable();

		 protected TemplateTable objectprivilegedata=new TemplateTable();

		 public String PrivilegegroupFilter="";
		 public String UserFilter="";
		 public String ModuleFilter="";
		 public String ObjectprivilegeFilter="";

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
		 public String getUserxml() {
			 return userxml;
		 }
		 public void setUserxml(String userxml) {
			 this.userxml=userxml;
		 }
		 public String getUserdeleteid() {
			 return userdeleteid;
		 }
		 public void setUserdeleteid(String userdeleteid) {
			 this.userdeleteid=userdeleteid;
		 }
		 public String getModulexml() {
			 return modulexml;
		 }
		 public void setModulexml(String modulexml) {
			 this.modulexml=modulexml;
		 }
		 public String getModuledeleteid() {
			 return moduledeleteid;
		 }
		 public void setModuledeleteid(String moduledeleteid) {
			 this.moduledeleteid=moduledeleteid;
		 }
		 public String getObjectprivilegexml() {
			 return objectprivilegexml;
		 }
		 public void setObjectprivilegexml(String objectprivilegexml) {
			 this.objectprivilegexml=objectprivilegexml;
		 }
		 public String getObjectprivilegedeleteid() {
			 return objectprivilegedeleteid;
		 }
		 public void setObjectprivilegedeleteid(String objectprivilegedeleteid) {
			 this.objectprivilegedeleteid=objectprivilegedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Privilegegroup",column,datatype);
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
					EventListener.registerPostQueryParent("Privilegegroup",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Privilegegroup",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Privilegegroup",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Privilegegroup",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Privilegegroup",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Privilegegroup",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Privilegegroup",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Privilegegroup",this.getParentobjid());
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
					tu.applyObjectRule("Privilegegroup",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Privilegegroup",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getUserxml()) ){
					 sql+=query.makeBulkSQL(false,getUserxml(),"User2PrivilegeGroup",this.getUsername(),this.getGroupuser());
					 userdata=query.getTableData();
					 tu.applyObjectRule("User",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,userdata);
					 EventListener.registerPreInsertEvent("User",userdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"User","User2PrivilegeGroup",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getModulexml()) ){
					 sql+=query.makeBulkSQL(false,getModulexml(),"Module2PrivilegeGroup",this.getUsername(),this.getGroupuser());
					 moduledata=query.getTableData();
					 tu.applyObjectRule("Module",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,moduledata);
					 EventListener.registerPreInsertEvent("Module",moduledata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Module","Module2PrivilegeGroup",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getObjectprivilegexml()) ){
					 sql+=query.makeBulkSQL(false,getObjectprivilegexml(),"ObjectPrivilege2PrivilegeGroup",this.getUsername(),this.getGroupuser());
					 objectprivilegedata=query.getTableData();
					 tu.applyObjectRule("Objectprivilege",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,objectprivilegedata);
					 EventListener.registerPreInsertEvent("ObjectPrivilege",objectprivilegedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ObjectPrivilege","ObjectPrivilege2PrivilegeGroup",getParentobjid());
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
				 tu.applyObjectRule("Privilegegroup",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Privilegegroup","Privilegegroup",getParentobjid());
				 EventListener.registerPostInsertEvent("Privilegegroup",maindata);
				 tu.applyObjectRule("User",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,userdata);
				 tu.applyMTMRelation("user","Privilegegroup",getParentobjid());
				 EventListener.registerPostInsertEvent("User",userdata);
				 tu.applyObjectRule("Module",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,moduledata);
				 tu.applyMTMRelation("module","Privilegegroup",getParentobjid());
				 EventListener.registerPostInsertEvent("Module",moduledata);
				 tu.applyObjectRule("Objectprivilege",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,objectprivilegedata);
				 tu.applyMTMRelation("objectprivilege","Privilegegroup",getParentobjid());
				 EventListener.registerPostInsertEvent("ObjectPrivilege",objectprivilegedata);
				 return(true);
			}
			 return(false);
		}
	}
