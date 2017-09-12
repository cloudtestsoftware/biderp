
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

	 public class AssetImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(AssetImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String maintenancexml,maintenancedeleteid;
		 protected String servicecostxml,servicecostdeleteid;
		 protected String depricatedxml,depricateddeleteid;
		 protected String addressxml,addressdeleteid;
		 protected String accountdebitxml,accountdebitdeleteid;

		 protected TemplateTable maintenancedata=new TemplateTable();

		 protected TemplateTable servicecostdata=new TemplateTable();

		 protected TemplateTable depricateddata=new TemplateTable();

		 protected TemplateTable addressdata=new TemplateTable();

		 protected TemplateTable accountdebitdata=new TemplateTable();

		 public String AssetFilter="";
		 public String MaintenanceFilter="servicetotal.totalcost TotalCost@Maintenance,ServiceTotal@Maintenance.objid=ServiceTotal.ServiceTotal2Maintenance(+)";
		 public String ServicecostFilter="";
		 public String DepricatedFilter="";
		 public String AddressFilter="";
		 public String AccountdebitFilter="";

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
		 public String getMaintenancexml() {
			 return maintenancexml;
		 }
		 public void setMaintenancexml(String maintenancexml) {
			 this.maintenancexml=maintenancexml;
		 }
		 public String getMaintenancedeleteid() {
			 return maintenancedeleteid;
		 }
		 public void setMaintenancedeleteid(String maintenancedeleteid) {
			 this.maintenancedeleteid=maintenancedeleteid;
		 }
		 public String getServicecostxml() {
			 return servicecostxml;
		 }
		 public void setServicecostxml(String servicecostxml) {
			 this.servicecostxml=servicecostxml;
		 }
		 public String getServicecostdeleteid() {
			 return servicecostdeleteid;
		 }
		 public void setServicecostdeleteid(String servicecostdeleteid) {
			 this.servicecostdeleteid=servicecostdeleteid;
		 }
		 public String getDepricatedxml() {
			 return depricatedxml;
		 }
		 public void setDepricatedxml(String depricatedxml) {
			 this.depricatedxml=depricatedxml;
		 }
		 public String getDepricateddeleteid() {
			 return depricateddeleteid;
		 }
		 public void setDepricateddeleteid(String depricateddeleteid) {
			 this.depricateddeleteid=depricateddeleteid;
		 }
		 public String getAddressxml() {
			 return addressxml;
		 }
		 public void setAddressxml(String addressxml) {
			 this.addressxml=addressxml;
		 }
		 public String getAddressdeleteid() {
			 return addressdeleteid;
		 }
		 public void setAddressdeleteid(String addressdeleteid) {
			 this.addressdeleteid=addressdeleteid;
		 }
		 public String getAccountdebitxml() {
			 return accountdebitxml;
		 }
		 public void setAccountdebitxml(String accountdebitxml) {
			 this.accountdebitxml=accountdebitxml;
		 }
		 public String getAccountdebitdeleteid() {
			 return accountdebitdeleteid;
		 }
		 public void setAccountdebitdeleteid(String accountdebitdeleteid) {
			 this.accountdebitdeleteid=accountdebitdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Asset",column,datatype);
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
					EventListener.registerPostQueryParent("Asset",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Asset",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Asset",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Asset",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Asset",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Asset",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Asset",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Asset",this.getParentobjid());
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
					tu.applyObjectRule("Asset",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Asset",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getMaintenancexml()) ){
					 sql+=query.makeBulkSQL(false,getMaintenancexml(),"Maintenance2Asset",this.getUsername(),this.getGroupuser());
					 maintenancedata=query.getTableData();
					 tu.applyObjectRule("Maintenance",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maintenancedata);
					 EventListener.registerPreInsertEvent("Maintenance",maintenancedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Maintenance","Maintenance2Asset",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getServicecostxml()) ){
					 sql+=query.makeBulkSQL(false,getServicecostxml(),"ServiceCost2Asset",this.getUsername(),this.getGroupuser());
					 servicecostdata=query.getTableData();
					 tu.applyObjectRule("Servicecost",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,servicecostdata);
					 EventListener.registerPreInsertEvent("ServiceCost",servicecostdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ServiceCost","ServiceCost2Asset",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getDepricatedxml()) ){
					 sql+=query.makeBulkSQL(false,getDepricatedxml(),"Depricated2Asset",this.getUsername(),this.getGroupuser());
					 depricateddata=query.getTableData();
					 tu.applyObjectRule("Depricated",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,depricateddata);
					 EventListener.registerPreInsertEvent("Depricated",depricateddata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Depricated","Depricated2Asset",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAddressxml()) ){
					 sql+=query.makeBulkSQL(false,getAddressxml(),"Address2Asset",this.getUsername(),this.getGroupuser());
					 addressdata=query.getTableData();
					 tu.applyObjectRule("Address",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,addressdata);
					 EventListener.registerPreInsertEvent("Address",addressdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Address","Address2Asset",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAccountdebitxml()) ){
					 sql+=query.makeBulkSQL(false,getAccountdebitxml(),"AccountDebit2Asset",this.getUsername(),this.getGroupuser());
					 accountdebitdata=query.getTableData();
					 tu.applyObjectRule("Accountdebit",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,accountdebitdata);
					 EventListener.registerPreInsertEvent("AccountDebit",accountdebitdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"AccountDebit","AccountDebit2Asset",getParentobjid());
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
				 tu.applyObjectRule("Asset",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Asset","Asset",getParentobjid());
				 EventListener.registerPostInsertEvent("Asset",maindata);
				 tu.applyObjectRule("Maintenance",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maintenancedata);
				 tu.applyMTMRelation("maintenance","Asset",getParentobjid());
				 EventListener.registerPostInsertEvent("Maintenance",maintenancedata);
				 tu.applyObjectRule("Servicecost",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,servicecostdata);
				 tu.applyMTMRelation("servicecost","Asset",getParentobjid());
				 EventListener.registerPostInsertEvent("ServiceCost",servicecostdata);
				 tu.applyObjectRule("Depricated",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,depricateddata);
				 tu.applyMTMRelation("depricated","Asset",getParentobjid());
				 EventListener.registerPostInsertEvent("Depricated",depricateddata);
				 tu.applyObjectRule("Address",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,addressdata);
				 tu.applyMTMRelation("address","Asset",getParentobjid());
				 EventListener.registerPostInsertEvent("Address",addressdata);
				 tu.applyObjectRule("Accountdebit",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,accountdebitdata);
				 tu.applyMTMRelation("accountdebit","Asset",getParentobjid());
				 EventListener.registerPostInsertEvent("AccountDebit",accountdebitdata);
				 return(true);
			}
			 return(false);
		}
	}
