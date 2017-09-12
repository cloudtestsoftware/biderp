
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

	 public class SiteImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(SiteImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String phasexml,phasedeleteid;
		 protected String warehousexml,warehousedeleteid;
		 protected String machinaryxml,machinarydeleteid;
		 protected String assetplanxml,assetplandeleteid;
		 protected String assetxml,assetdeleteid;

		 protected TemplateTable phasedata=new TemplateTable();

		 protected TemplateTable warehousedata=new TemplateTable();

		 protected TemplateTable machinarydata=new TemplateTable();

		 protected TemplateTable assetplandata=new TemplateTable();

		 protected TemplateTable assetdata=new TemplateTable();

		 public String SiteFilter="sitetotal.total Total@site,sitetotal@site.objid=sitetotal.sitetotal2site";
		 public String PhaseFilter="phasetotal.total Total@phase,phasetotal@phase.objid=phasetotal.phasetotal2phase";
		 public String WarehouseFilter="";
		 public String MachinaryFilter="";
		 public String AssetplanFilter="Machinary.estunit EstUnit,Machinary.QntRequest QntRequest,Machinary.startdate ReqStartDate,Machinary.enddate ReqEndDate@AssetPlan,Machinary@AssetPlan.AssetPlan2Machinary=Machinary.objid";
		 public String AssetFilter="";

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
		 public String getPhasexml() {
			 return phasexml;
		 }
		 public void setPhasexml(String phasexml) {
			 this.phasexml=phasexml;
		 }
		 public String getPhasedeleteid() {
			 return phasedeleteid;
		 }
		 public void setPhasedeleteid(String phasedeleteid) {
			 this.phasedeleteid=phasedeleteid;
		 }
		 public String getWarehousexml() {
			 return warehousexml;
		 }
		 public void setWarehousexml(String warehousexml) {
			 this.warehousexml=warehousexml;
		 }
		 public String getWarehousedeleteid() {
			 return warehousedeleteid;
		 }
		 public void setWarehousedeleteid(String warehousedeleteid) {
			 this.warehousedeleteid=warehousedeleteid;
		 }
		 public String getMachinaryxml() {
			 return machinaryxml;
		 }
		 public void setMachinaryxml(String machinaryxml) {
			 this.machinaryxml=machinaryxml;
		 }
		 public String getMachinarydeleteid() {
			 return machinarydeleteid;
		 }
		 public void setMachinarydeleteid(String machinarydeleteid) {
			 this.machinarydeleteid=machinarydeleteid;
		 }
		 public String getAssetplanxml() {
			 return assetplanxml;
		 }
		 public void setAssetplanxml(String assetplanxml) {
			 this.assetplanxml=assetplanxml;
		 }
		 public String getAssetplandeleteid() {
			 return assetplandeleteid;
		 }
		 public void setAssetplandeleteid(String assetplandeleteid) {
			 this.assetplandeleteid=assetplandeleteid;
		 }
		 public String getAssetxml() {
			 return assetxml;
		 }
		 public void setAssetxml(String assetxml) {
			 this.assetxml=assetxml;
		 }
		 public String getAssetdeleteid() {
			 return assetdeleteid;
		 }
		 public void setAssetdeleteid(String assetdeleteid) {
			 this.assetdeleteid=assetdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Site",column,datatype);
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
					EventListener.registerPostQueryParent("Site",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Site",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Site",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Site",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Site",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Site",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Site",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Site",this.getParentobjid());
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
					tu.applyObjectRule("Site",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Site",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getPhasexml()) ){
					 sql+=query.makeBulkSQL(false,getPhasexml(),"Phase2Site",this.getUsername(),this.getGroupuser());
					 phasedata=query.getTableData();
					 tu.applyObjectRule("Phase",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,phasedata);
					 EventListener.registerPreInsertEvent("Phase",phasedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Phase","Phase2Site",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getWarehousexml()) ){
					 sql+=query.makeBulkSQL(false,getWarehousexml(),"Warehouse2Site",this.getUsername(),this.getGroupuser());
					 warehousedata=query.getTableData();
					 tu.applyObjectRule("Warehouse",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,warehousedata);
					 EventListener.registerPreInsertEvent("Warehouse",warehousedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Warehouse","Warehouse2Site",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getMachinaryxml()) ){
					 sql+=query.makeBulkSQL(false,getMachinaryxml(),"Machinary2Site",this.getUsername(),this.getGroupuser());
					 machinarydata=query.getTableData();
					 tu.applyObjectRule("Machinary",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,machinarydata);
					 EventListener.registerPreInsertEvent("Machinary",machinarydata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Machinary","Machinary2Site",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAssetplanxml()) ){
					 sql+=query.makeBulkSQL(false,getAssetplanxml(),"AssetPlan2Site",this.getUsername(),this.getGroupuser());
					 assetplandata=query.getTableData();
					 tu.applyObjectRule("Assetplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,assetplandata);
					 EventListener.registerPreInsertEvent("AssetPlan",assetplandata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"AssetPlan","AssetPlan2Site",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAssetxml()) ){
					 sql+=query.makeBulkSQL(false,getAssetxml(),"Asset2Site",this.getUsername(),this.getGroupuser());
					 assetdata=query.getTableData();
					 tu.applyObjectRule("Asset",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,assetdata);
					 EventListener.registerPreInsertEvent("Asset",assetdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Asset","Asset2Site",getParentobjid());
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
				 tu.applyConsoleObject("Site",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Site",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Site","Site",getParentobjid());
				 EventListener.registerPostInsertEvent("Site",maindata);
				 tu.applyObjectRule("Phase",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,phasedata);
				 tu.applyMTMRelation("phase","Site",getParentobjid());
				 EventListener.registerPostInsertEvent("Phase",phasedata);
				 tu.applyConsoleObject("phase",phasedata,this.getUsername(),groupuser,false);
				 tu.applyObjectRule("Warehouse",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,warehousedata);
				 tu.applyMTMRelation("warehouse","Site",getParentobjid());
				 EventListener.registerPostInsertEvent("Warehouse",warehousedata);
				 tu.applyObjectRule("Machinary",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,machinarydata);
				 tu.applyMTMRelation("machinary","Site",getParentobjid());
				 EventListener.registerPostInsertEvent("Machinary",machinarydata);
				 tu.applyObjectRule("Assetplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,assetplandata);
				 tu.applyMTMRelation("assetplan","Site",getParentobjid());
				 EventListener.registerPostInsertEvent("AssetPlan",assetplandata);
				 tu.applyObjectRule("Asset",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,assetdata);
				 tu.applyMTMRelation("asset","Site",getParentobjid());
				 EventListener.registerPostInsertEvent("Asset",assetdata);
				 return(true);
			}
			 return(false);
		}
	}
