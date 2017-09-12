
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

	 public class ResourceorderImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ResourceorderImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String taskresourcexml,taskresourcedeleteid;
		 protected String poinvoicexml,poinvoicedeleteid;

		 protected TemplateTable taskresourcedata=new TemplateTable();

		 protected TemplateTable poinvoicedata=new TemplateTable();

		 public String ResourceorderFilter="PoResAmount.amount Amount,PoResAmount.total Total@ResourceOrder,PoResAmount@ResourceOrder.objid=PoResAmount.PoResAmount2ResourceOrder(+)";
		 public String TaskresourceFilter="resourceuse.QntUsed ActualUnit,ResourceUse.qntbalance QntBalance,partprice.UmCode UmCode,partprice.unitprice Rate@ResourceUse,partprice,taskresource,milestone@taskresource.taskresource2milestone=milestone.objid(+) and taskresource.objid=resourceuse.resourceuse2taskresource and taskresource.taskresource2partprice=partprice.objid order by TaskResource.name";
		 public String PoinvoiceFilter="";

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
		 public String getTaskresourcexml() {
			 return taskresourcexml;
		 }
		 public void setTaskresourcexml(String taskresourcexml) {
			 this.taskresourcexml=taskresourcexml;
		 }
		 public String getTaskresourcedeleteid() {
			 return taskresourcedeleteid;
		 }
		 public void setTaskresourcedeleteid(String taskresourcedeleteid) {
			 this.taskresourcedeleteid=taskresourcedeleteid;
		 }
		 public String getPoinvoicexml() {
			 return poinvoicexml;
		 }
		 public void setPoinvoicexml(String poinvoicexml) {
			 this.poinvoicexml=poinvoicexml;
		 }
		 public String getPoinvoicedeleteid() {
			 return poinvoicedeleteid;
		 }
		 public void setPoinvoicedeleteid(String poinvoicedeleteid) {
			 this.poinvoicedeleteid=poinvoicedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Resourceorder",column,datatype);
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
					EventListener.registerPostQueryParent("Resourceorder",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Resourceorder",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Resourceorder",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Resourceorder",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Resourceorder",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Resourceorder",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Resourceorder",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Resourceorder",this.getParentobjid());
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
					tu.applyObjectRule("Resourceorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Resourceorder",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getTaskresourcexml()) ){
					 sql+=query.makeBulkSQL(false,getTaskresourcexml(),"TaskResource2ResourceOrder",this.getUsername(),this.getGroupuser());
					 taskresourcedata=query.getTableData();
					 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,taskresourcedata);
					 EventListener.registerPreInsertEvent("TaskResource",taskresourcedata);
				
				 String [] autofieldTaskResourcelist={"title","projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"TaskResource",autofieldTaskResourcelist,"TaskResource2ResourceOrder",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TaskResource","TaskResource2ResourceOrder",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPoinvoicexml()) ){
					 sql+=query.makeBulkSQL(false,getPoinvoicexml(),"PoInvoice2ResourceOrder",this.getUsername(),this.getGroupuser());
					 poinvoicedata=query.getTableData();
					 tu.applyObjectRule("Poinvoice",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,poinvoicedata);
					 EventListener.registerPreInsertEvent("PoInvoice",poinvoicedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PoInvoice","PoInvoice2ResourceOrder",getParentobjid());
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
				 tu.applyObjectRule("Resourceorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Resourceorder","Resourceorder",getParentobjid());
				 EventListener.registerPostInsertEvent("Resourceorder",maindata);
				 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,taskresourcedata);
				 tu.applyMTMRelation("taskresource","Resourceorder",getParentobjid());
				 EventListener.registerPostInsertEvent("TaskResource",taskresourcedata);
				 tu.applyObjectRule("Poinvoice",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,poinvoicedata);
				 tu.applyMTMRelation("poinvoice","Resourceorder",getParentobjid());
				 EventListener.registerPostInsertEvent("PoInvoice",poinvoicedata);
				 return(true);
			}
			 return(false);
		}
	}
