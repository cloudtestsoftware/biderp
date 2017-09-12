
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

	 public class PomasterImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(PomasterImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String purchaseorderxml,purchaseorderdeleteid;
		 protected String posupplierxml,posupplierdeleteid;
		 protected String resourceorderxml,resourceorderdeleteid;
		 protected String resourcesupplierxml,resourcesupplierdeleteid;
		 protected String bompurchasexml,bompurchasedeleteid;
		 protected String deptpobudgetxml,deptpobudgetdeleteid;

		 protected TemplateTable purchaseorderdata=new TemplateTable();

		 protected TemplateTable posupplierdata=new TemplateTable();

		 protected TemplateTable resourceorderdata=new TemplateTable();

		 protected TemplateTable resourcesupplierdata=new TemplateTable();

		 protected TemplateTable bompurchasedata=new TemplateTable();

		 protected TemplateTable deptpobudgetdata=new TemplateTable();

		 public String PomasterFilter="";
		 public String PurchaseorderFilter="PoAmount.total Total@PurchaseOrder,PoAmount@PurchaseOrder.objid=PoAmount.PoAmount2PurchaseOrder(+)";
		 public String PosupplierFilter="";
		 public String ResourceorderFilter="PoResAmount.amount Amount,PoResAmount.total Total@ResourceOrder,PoResAmount@ResourceOrder.objid=PoResAmount.PoResAmount2ResourceOrder(+)";
		 public String ResourcesupplierFilter="";
		 public String BompurchaseFilter="";
		 public String DeptpobudgetFilter="";

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
		 public String getPurchaseorderxml() {
			 return purchaseorderxml;
		 }
		 public void setPurchaseorderxml(String purchaseorderxml) {
			 this.purchaseorderxml=purchaseorderxml;
		 }
		 public String getPurchaseorderdeleteid() {
			 return purchaseorderdeleteid;
		 }
		 public void setPurchaseorderdeleteid(String purchaseorderdeleteid) {
			 this.purchaseorderdeleteid=purchaseorderdeleteid;
		 }
		 public String getPosupplierxml() {
			 return posupplierxml;
		 }
		 public void setPosupplierxml(String posupplierxml) {
			 this.posupplierxml=posupplierxml;
		 }
		 public String getPosupplierdeleteid() {
			 return posupplierdeleteid;
		 }
		 public void setPosupplierdeleteid(String posupplierdeleteid) {
			 this.posupplierdeleteid=posupplierdeleteid;
		 }
		 public String getResourceorderxml() {
			 return resourceorderxml;
		 }
		 public void setResourceorderxml(String resourceorderxml) {
			 this.resourceorderxml=resourceorderxml;
		 }
		 public String getResourceorderdeleteid() {
			 return resourceorderdeleteid;
		 }
		 public void setResourceorderdeleteid(String resourceorderdeleteid) {
			 this.resourceorderdeleteid=resourceorderdeleteid;
		 }
		 public String getResourcesupplierxml() {
			 return resourcesupplierxml;
		 }
		 public void setResourcesupplierxml(String resourcesupplierxml) {
			 this.resourcesupplierxml=resourcesupplierxml;
		 }
		 public String getResourcesupplierdeleteid() {
			 return resourcesupplierdeleteid;
		 }
		 public void setResourcesupplierdeleteid(String resourcesupplierdeleteid) {
			 this.resourcesupplierdeleteid=resourcesupplierdeleteid;
		 }
		 public String getBompurchasexml() {
			 return bompurchasexml;
		 }
		 public void setBompurchasexml(String bompurchasexml) {
			 this.bompurchasexml=bompurchasexml;
		 }
		 public String getBompurchasedeleteid() {
			 return bompurchasedeleteid;
		 }
		 public void setBompurchasedeleteid(String bompurchasedeleteid) {
			 this.bompurchasedeleteid=bompurchasedeleteid;
		 }
		 public String getDeptpobudgetxml() {
			 return deptpobudgetxml;
		 }
		 public void setDeptpobudgetxml(String deptpobudgetxml) {
			 this.deptpobudgetxml=deptpobudgetxml;
		 }
		 public String getDeptpobudgetdeleteid() {
			 return deptpobudgetdeleteid;
		 }
		 public void setDeptpobudgetdeleteid(String deptpobudgetdeleteid) {
			 this.deptpobudgetdeleteid=deptpobudgetdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Pomaster",column,datatype);
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
					EventListener.registerPostQueryParent("Pomaster",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Pomaster",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Pomaster",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Pomaster",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Pomaster",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Pomaster",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Pomaster",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Pomaster",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"PoMaster2Project",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Pomaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Pomaster",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getPurchaseorderxml()) ){
					 sql+=query.makeBulkSQL(false,getPurchaseorderxml(),"PurchaseOrder2PoMaster",this.getUsername(),this.getGroupuser());
					 purchaseorderdata=query.getTableData();
					 tu.applyObjectRule("Purchaseorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,purchaseorderdata);
					 EventListener.registerPreInsertEvent("PurchaseOrder",purchaseorderdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PurchaseOrder","PurchaseOrder2PoMaster",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPosupplierxml()) ){
					 sql+=query.makeBulkSQL(false,getPosupplierxml(),"PoSupplier2PoMaster",this.getUsername(),this.getGroupuser());
					 posupplierdata=query.getTableData();
					 tu.applyObjectRule("Posupplier",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,posupplierdata);
					 EventListener.registerPreInsertEvent("PoSupplier",posupplierdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PoSupplier","PoSupplier2PoMaster",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getResourceorderxml()) ){
					 sql+=query.makeBulkSQL(false,getResourceorderxml(),"ResourceOrder2PoMaster",this.getUsername(),this.getGroupuser());
					 resourceorderdata=query.getTableData();
					 tu.applyObjectRule("Resourceorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,resourceorderdata);
					 EventListener.registerPreInsertEvent("ResourceOrder",resourceorderdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ResourceOrder","ResourceOrder2PoMaster",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getResourcesupplierxml()) ){
					 sql+=query.makeBulkSQL(false,getResourcesupplierxml(),"ResourceSupplier2PoMaster",this.getUsername(),this.getGroupuser());
					 resourcesupplierdata=query.getTableData();
					 tu.applyObjectRule("Resourcesupplier",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,resourcesupplierdata);
					 EventListener.registerPreInsertEvent("ResourceSupplier",resourcesupplierdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ResourceSupplier","ResourceSupplier2PoMaster",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBompurchasexml()) ){
					 sql+=query.makeBulkSQL(false,getBompurchasexml(),"BomPurchase2PoMaster",this.getUsername(),this.getGroupuser());
					 bompurchasedata=query.getTableData();
					 tu.applyObjectRule("Bompurchase",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bompurchasedata);
					 EventListener.registerPreInsertEvent("BomPurchase",bompurchasedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BomPurchase","BomPurchase2PoMaster",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getDeptpobudgetxml()) ){
					 sql+=query.makeBulkSQL(false,getDeptpobudgetxml(),"DeptPoBudget2PoMaster",this.getUsername(),this.getGroupuser());
					 deptpobudgetdata=query.getTableData();
					 tu.applyObjectRule("Deptpobudget",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,deptpobudgetdata);
					 EventListener.registerPreInsertEvent("DeptPoBudget",deptpobudgetdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"DeptPoBudget","DeptPoBudget2PoMaster",getParentobjid());
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
				 tu.applyObjectRule("Pomaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Pomaster","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("Pomaster",maindata);
				 tu.applyObjectRule("Purchaseorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,purchaseorderdata);
				 tu.applyMTMRelation("purchaseorder","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("PurchaseOrder",purchaseorderdata);
				 tu.applyObjectRule("Posupplier",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,posupplierdata);
				 tu.applyMTMRelation("posupplier","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("PoSupplier",posupplierdata);
				 tu.applyObjectRule("Resourceorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,resourceorderdata);
				 tu.applyMTMRelation("resourceorder","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("ResourceOrder",resourceorderdata);
				 tu.applyObjectRule("Resourcesupplier",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,resourcesupplierdata);
				 tu.applyMTMRelation("resourcesupplier","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("ResourceSupplier",resourcesupplierdata);
				 tu.applyObjectRule("Bompurchase",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bompurchasedata);
				 tu.applyMTMRelation("bompurchase","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("BomPurchase",bompurchasedata);
				 tu.applyObjectRule("Deptpobudget",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,deptpobudgetdata);
				 tu.applyMTMRelation("deptpobudget","Pomaster",getParentobjid());
				 EventListener.registerPostInsertEvent("DeptPoBudget",deptpobudgetdata);
				 return(true);
			}
			 return(false);
		}
	}
