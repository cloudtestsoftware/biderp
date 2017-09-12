
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

	 public class PartrequestImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(PartrequestImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String itemreceivedxml,itemreceiveddeleteid;
		 protected String itemdispatchxml,itemdispatchdeleteid;

		 protected TemplateTable itemreceiveddata=new TemplateTable();

		 protected TemplateTable itemdispatchdata=new TemplateTable();

		 public String PartrequestFilter="itemdispatchcount.indentno IndentNo,partuse.qntbalance QntBalance,(orderreceivedcount.totalqntaccepted-itemdispatchcount.totalqntdispatched) QntInventory,orderreceivedcount.totalqntaccepted totalqntaccepted,itemdispatchcount.totalqntdispatched totalqntdispatched@partrequest,partuse,orderreceivedcount,itemdispatchcount@itemdispatchcount.objid=partrequest.objid and orderreceivedcount.objid=partrequest.objid and partuse.partuse2partrequest=partrequest.objid order by PartRequest.name";
		 public String ItemreceivedFilter="";
		 public String ItemdispatchFilter="";

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
		 public String getItemreceivedxml() {
			 return itemreceivedxml;
		 }
		 public void setItemreceivedxml(String itemreceivedxml) {
			 this.itemreceivedxml=itemreceivedxml;
		 }
		 public String getItemreceiveddeleteid() {
			 return itemreceiveddeleteid;
		 }
		 public void setItemreceiveddeleteid(String itemreceiveddeleteid) {
			 this.itemreceiveddeleteid=itemreceiveddeleteid;
		 }
		 public String getItemdispatchxml() {
			 return itemdispatchxml;
		 }
		 public void setItemdispatchxml(String itemdispatchxml) {
			 this.itemdispatchxml=itemdispatchxml;
		 }
		 public String getItemdispatchdeleteid() {
			 return itemdispatchdeleteid;
		 }
		 public void setItemdispatchdeleteid(String itemdispatchdeleteid) {
			 this.itemdispatchdeleteid=itemdispatchdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Partrequest",column,datatype);
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
					EventListener.registerPostQueryParent("Partrequest",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Partrequest",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Partrequest",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Partrequest",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Partrequest",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Partrequest",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Partrequest",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Partrequest",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"PartRequest2Parts",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Partrequest",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Partrequest",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getItemreceivedxml()) ){
					 sql+=query.makeBulkSQL(false,getItemreceivedxml(),"ItemReceived2PartRequest",this.getUsername(),this.getGroupuser());
					 itemreceiveddata=query.getTableData();
					 tu.applyObjectRule("Itemreceived",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,itemreceiveddata);
					 EventListener.registerPreInsertEvent("ItemReceived",itemreceiveddata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ItemReceived","ItemReceived2PartRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getItemdispatchxml()) ){
					 sql+=query.makeBulkSQL(false,getItemdispatchxml(),"ItemDispatch2PartRequest",this.getUsername(),this.getGroupuser());
					 itemdispatchdata=query.getTableData();
					 tu.applyObjectRule("Itemdispatch",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,itemdispatchdata);
					 EventListener.registerPreInsertEvent("ItemDispatch",itemdispatchdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ItemDispatch","ItemDispatch2PartRequest",getParentobjid());
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
				 tu.applyObjectRule("Partrequest",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Partrequest","Partrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("Partrequest",maindata);
				 tu.applyObjectRule("Itemreceived",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,itemreceiveddata);
				 tu.applyMTMRelation("itemreceived","Partrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("ItemReceived",itemreceiveddata);
				 tu.applyObjectRule("Itemdispatch",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,itemdispatchdata);
				 tu.applyMTMRelation("itemdispatch","Partrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("ItemDispatch",itemdispatchdata);
				 return(true);
			}
			 return(false);
		}
	}
