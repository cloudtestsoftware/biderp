
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

	 public class ModuleImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ModuleImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String moduleobjectxml,moduleobjectdeleteid;
		 protected String submodulexml,submoduledeleteid;

		 protected TemplateTable moduleobjectdata=new TemplateTable();

		 protected TemplateTable submoduledata=new TemplateTable();

		 public String ModuleFilter="";
		 public String ModuleobjectFilter="";
		 public String SubmoduleFilter="";

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
		 public String getModuleobjectxml() {
			 return moduleobjectxml;
		 }
		 public void setModuleobjectxml(String moduleobjectxml) {
			 this.moduleobjectxml=moduleobjectxml;
		 }
		 public String getModuleobjectdeleteid() {
			 return moduleobjectdeleteid;
		 }
		 public void setModuleobjectdeleteid(String moduleobjectdeleteid) {
			 this.moduleobjectdeleteid=moduleobjectdeleteid;
		 }
		 public String getSubmodulexml() {
			 return submodulexml;
		 }
		 public void setSubmodulexml(String submodulexml) {
			 this.submodulexml=submodulexml;
		 }
		 public String getSubmoduledeleteid() {
			 return submoduledeleteid;
		 }
		 public void setSubmoduledeleteid(String submoduledeleteid) {
			 this.submoduledeleteid=submoduledeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Module",column,datatype);
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
					EventListener.registerPostQueryParent("Module",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Module",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Module",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Module",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Module",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Module",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Module",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Module",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Module2PrivilegeGroup",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Module",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Module",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getModuleobjectxml()) ){
					 sql+=query.makeBulkSQL(false,getModuleobjectxml(),"ModuleObject2Module",this.getUsername(),this.getGroupuser());
					 moduleobjectdata=query.getTableData();
					 tu.applyObjectRule("Moduleobject",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,moduleobjectdata);
					 EventListener.registerPreInsertEvent("ModuleObject",moduleobjectdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ModuleObject","ModuleObject2Module",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSubmodulexml()) ){
					 sql+=query.makeBulkSQL(false,getSubmodulexml(),"SubModule2Module",this.getUsername(),this.getGroupuser());
					 submoduledata=query.getTableData();
					 tu.applyObjectRule("Submodule",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,submoduledata);
					 EventListener.registerPreInsertEvent("SubModule",submoduledata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SubModule","SubModule2Module",getParentobjid());
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
				 tu.applyObjectRule("Module",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Module","Module",getParentobjid());
				 EventListener.registerPostInsertEvent("Module",maindata);
				 tu.applyObjectRule("Moduleobject",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,moduleobjectdata);
				 tu.applyMTMRelation("moduleobject","Module",getParentobjid());
				 EventListener.registerPostInsertEvent("ModuleObject",moduleobjectdata);
				 tu.applyObjectRule("Submodule",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,submoduledata);
				 tu.applyMTMRelation("submodule","Module",getParentobjid());
				 EventListener.registerPostInsertEvent("SubModule",submoduledata);
				 return(true);
			}
			 return(false);
		}
	}
