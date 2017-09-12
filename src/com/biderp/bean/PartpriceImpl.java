
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

	 public class PartpriceImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(PartpriceImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String orderpendingxml,orderpendingdeleteid;
		 protected String partcountxml,partcountdeleteid;
		 protected String qrinfoxml,qrinfodeleteid;

		 protected TemplateTable orderpendingdata=new TemplateTable();

		 protected TemplateTable partcountdata=new TemplateTable();

		 protected TemplateTable qrinfodata=new TemplateTable();

		 public String PartpriceFilter="partlist.partcode PartCode@partlist,partprice@partlist.objid=partprice.partprice2partlist order by partprice.name";
		 public String OrderpendingFilter="";
		 public String PartcountFilter="";
		 public String QrinfoFilter="";

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
		 public String getOrderpendingxml() {
			 return orderpendingxml;
		 }
		 public void setOrderpendingxml(String orderpendingxml) {
			 this.orderpendingxml=orderpendingxml;
		 }
		 public String getOrderpendingdeleteid() {
			 return orderpendingdeleteid;
		 }
		 public void setOrderpendingdeleteid(String orderpendingdeleteid) {
			 this.orderpendingdeleteid=orderpendingdeleteid;
		 }
		 public String getPartcountxml() {
			 return partcountxml;
		 }
		 public void setPartcountxml(String partcountxml) {
			 this.partcountxml=partcountxml;
		 }
		 public String getPartcountdeleteid() {
			 return partcountdeleteid;
		 }
		 public void setPartcountdeleteid(String partcountdeleteid) {
			 this.partcountdeleteid=partcountdeleteid;
		 }
		 public String getQrinfoxml() {
			 return qrinfoxml;
		 }
		 public void setQrinfoxml(String qrinfoxml) {
			 this.qrinfoxml=qrinfoxml;
		 }
		 public String getQrinfodeleteid() {
			 return qrinfodeleteid;
		 }
		 public void setQrinfodeleteid(String qrinfodeleteid) {
			 this.qrinfodeleteid=qrinfodeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Partprice",column,datatype);
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
					EventListener.registerPostQueryParent("Partprice",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Partprice",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Partprice",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Partprice",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Partprice",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Partprice",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Partprice",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Partprice",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"PartPrice2PartList",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Partprice",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Partprice",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getOrderpendingxml()) ){
					 sql+=query.makeBulkSQL(false,getOrderpendingxml(),"OrderPending2PartPrice",this.getUsername(),this.getGroupuser());
					 orderpendingdata=query.getTableData();
					 tu.applyObjectRule("Orderpending",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,orderpendingdata);
					 EventListener.registerPreInsertEvent("OrderPending",orderpendingdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"OrderPending","OrderPending2PartPrice",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPartcountxml()) ){
					 sql+=query.makeBulkSQL(false,getPartcountxml(),"PartCount2PartPrice",this.getUsername(),this.getGroupuser());
					 partcountdata=query.getTableData();
					 tu.applyObjectRule("Partcount",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partcountdata);
					 EventListener.registerPreInsertEvent("PartCount",partcountdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PartCount","PartCount2PartPrice",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getQrinfoxml()) ){
					 sql+=query.makeBulkSQL(false,getQrinfoxml(),"QRInfo2PartPrice",this.getUsername(),this.getGroupuser());
					 qrinfodata=query.getTableData();
					 tu.applyObjectRule("Qrinfo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,qrinfodata);
					 EventListener.registerPreInsertEvent("QRInfo",qrinfodata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QRInfo","QRInfo2PartPrice",getParentobjid());
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
				 tu.applyObjectRule("Partprice",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Partprice","Partprice",getParentobjid());
				 EventListener.registerPostInsertEvent("Partprice",maindata);
				 tu.applyObjectRule("Orderpending",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,orderpendingdata);
				 tu.applyMTMRelation("orderpending","Partprice",getParentobjid());
				 EventListener.registerPostInsertEvent("OrderPending",orderpendingdata);
				 tu.applyObjectRule("Partcount",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partcountdata);
				 tu.applyMTMRelation("partcount","Partprice",getParentobjid());
				 EventListener.registerPostInsertEvent("PartCount",partcountdata);
				 tu.applyObjectRule("Qrinfo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,qrinfodata);
				 tu.applyMTMRelation("qrinfo","Partprice",getParentobjid());
				 EventListener.registerPostInsertEvent("QRInfo",qrinfodata);
				 return(true);
			}
			 return(false);
		}
	}
