
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

	 public class RfqImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(RfqImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String rfqpartsxml,rfqpartsdeleteid;
		 protected String rfqemailxml,rfqemaildeleteid;
		 protected String trtasksxml,trtasksdeleteid;
		 protected String qrtasksxml,qrtasksdeleteid;
		 protected String bidtasksxml,bidtasksdeleteid;
		 protected String potasksxml,potasksdeleteid;
		 protected String emaildocsxml,emaildocsdeleteid;

		 protected TemplateTable rfqpartsdata=new TemplateTable();

		 protected TemplateTable rfqemaildata=new TemplateTable();

		 protected TemplateTable trtasksdata=new TemplateTable();

		 protected TemplateTable qrtasksdata=new TemplateTable();

		 protected TemplateTable bidtasksdata=new TemplateTable();

		 protected TemplateTable potasksdata=new TemplateTable();

		 protected TemplateTable emaildocsdata=new TemplateTable();

		 public String RfqFilter="rfqtotal.total Total@RFQtotal,RFQ@RFQ.objid=RFQtotal.RFQtotal2RFQ(+)";
		 public String RfqpartsFilter="partlist.partcode PartCode,partprice.partno PartNo,partlist.description Description,partprice.unitprice UnitPrice,partprice.UmCode UmCode@partlist,partprice,RfqParts@RfqParts.RfqParts2partprice=partprice.objid and partprice.partprice2partlist=partlist.objid order by RfqParts.name";
		 public String RfqemailFilter="RFQEmailDocs.attachments url@RFQEmail, RFQEmailDocs@RFQEmail.objid=RFQEmailDocs2RFQEmail(+)";
		 public String TrtasksFilter="qrinfo.description Description,qrinfo.url URL@qrinfo,trtasks,rfqparts@qrinfo.qrinfo2partprice=rfqparts.rfqparts2partprice and trtasks.trtasks2rfq(+)=rfqparts.rfqparts2rfq and qrinfo.infocode='TR'";
		 public String QrtasksFilter="qrinfo.description Description,qrinfo.url URL@qrinfo,QRTasks,rfqparts@qrinfo.qrinfo2partprice=rfqparts.rfqparts2partprice and QRTasks.QRTasks2rfq=rfqparts.rfqparts2rfq and qrinfo.infocode='QR'";
		 public String BidtasksFilter="qrinfo.description Description,qrinfo.url URL@qrinfo,BidTasks,rfqparts@qrinfo.qrinfo2partprice=rfqparts.rfqparts2partprice and BidTasks.BidTasks2rfq=rfqparts.rfqparts2rfq and qrinfo.infocode='QR'";
		 public String PotasksFilter="qrinfo.description Description,qrinfo.url URL@qrinfo,POTasks,rfqparts@qrinfo.qrinfo2partprice=rfqparts.rfqparts2partprice and POTasks.POTasks2rfq=rfqparts.rfqparts2rfq and qrinfo.infocode='PO'";
		 public String EmaildocsFilter="";

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
		 public String getRfqpartsxml() {
			 return rfqpartsxml;
		 }
		 public void setRfqpartsxml(String rfqpartsxml) {
			 this.rfqpartsxml=rfqpartsxml;
		 }
		 public String getRfqpartsdeleteid() {
			 return rfqpartsdeleteid;
		 }
		 public void setRfqpartsdeleteid(String rfqpartsdeleteid) {
			 this.rfqpartsdeleteid=rfqpartsdeleteid;
		 }
		 public String getRfqemailxml() {
			 return rfqemailxml;
		 }
		 public void setRfqemailxml(String rfqemailxml) {
			 this.rfqemailxml=rfqemailxml;
		 }
		 public String getRfqemaildeleteid() {
			 return rfqemaildeleteid;
		 }
		 public void setRfqemaildeleteid(String rfqemaildeleteid) {
			 this.rfqemaildeleteid=rfqemaildeleteid;
		 }
		 public String getTrtasksxml() {
			 return trtasksxml;
		 }
		 public void setTrtasksxml(String trtasksxml) {
			 this.trtasksxml=trtasksxml;
		 }
		 public String getTrtasksdeleteid() {
			 return trtasksdeleteid;
		 }
		 public void setTrtasksdeleteid(String trtasksdeleteid) {
			 this.trtasksdeleteid=trtasksdeleteid;
		 }
		 public String getQrtasksxml() {
			 return qrtasksxml;
		 }
		 public void setQrtasksxml(String qrtasksxml) {
			 this.qrtasksxml=qrtasksxml;
		 }
		 public String getQrtasksdeleteid() {
			 return qrtasksdeleteid;
		 }
		 public void setQrtasksdeleteid(String qrtasksdeleteid) {
			 this.qrtasksdeleteid=qrtasksdeleteid;
		 }
		 public String getBidtasksxml() {
			 return bidtasksxml;
		 }
		 public void setBidtasksxml(String bidtasksxml) {
			 this.bidtasksxml=bidtasksxml;
		 }
		 public String getBidtasksdeleteid() {
			 return bidtasksdeleteid;
		 }
		 public void setBidtasksdeleteid(String bidtasksdeleteid) {
			 this.bidtasksdeleteid=bidtasksdeleteid;
		 }
		 public String getPotasksxml() {
			 return potasksxml;
		 }
		 public void setPotasksxml(String potasksxml) {
			 this.potasksxml=potasksxml;
		 }
		 public String getPotasksdeleteid() {
			 return potasksdeleteid;
		 }
		 public void setPotasksdeleteid(String potasksdeleteid) {
			 this.potasksdeleteid=potasksdeleteid;
		 }
		 public String getEmaildocsxml() {
			 return emaildocsxml;
		 }
		 public void setEmaildocsxml(String emaildocsxml) {
			 this.emaildocsxml=emaildocsxml;
		 }
		 public String getEmaildocsdeleteid() {
			 return emaildocsdeleteid;
		 }
		 public void setEmaildocsdeleteid(String emaildocsdeleteid) {
			 this.emaildocsdeleteid=emaildocsdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Rfq",column,datatype);
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
					EventListener.registerPostQueryParent("Rfq",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Rfq",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Rfq",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Rfq",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Rfq",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Rfq",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Rfq",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Rfq",this.getParentobjid());
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
					tu.applyObjectRule("Rfq",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Rfq",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getRfqpartsxml()) ){
					 sql+=query.makeBulkSQL(false,getRfqpartsxml(),"RfqParts2RFQ",this.getUsername(),this.getGroupuser());
					 rfqpartsdata=query.getTableData();
					 tu.applyObjectRule("Rfqparts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,rfqpartsdata);
					 EventListener.registerPreInsertEvent("RfqParts",rfqpartsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"RfqParts","RfqParts2RFQ",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getRfqemailxml()) ){
					 sql+=query.makeBulkSQL(false,getRfqemailxml(),"RFQEmail2RFQ",this.getUsername(),this.getGroupuser());
					 rfqemaildata=query.getTableData();
					 tu.applyObjectRule("Rfqemail",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,rfqemaildata);
					 EventListener.registerPreInsertEvent("RFQEmail",rfqemaildata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"RFQEmail","RFQEmail2RFQ",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTrtasksxml()) ){
					 sql+=query.makeBulkSQL(false,getTrtasksxml(),"TRTasks2RFQ",this.getUsername(),this.getGroupuser());
					 trtasksdata=query.getTableData();
					 tu.applyObjectRule("Trtasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,trtasksdata);
					 EventListener.registerPreInsertEvent("TRTasks",trtasksdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TRTasks","TRTasks2RFQ",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getQrtasksxml()) ){
					 sql+=query.makeBulkSQL(false,getQrtasksxml(),"QRTasks2RFQ",this.getUsername(),this.getGroupuser());
					 qrtasksdata=query.getTableData();
					 tu.applyObjectRule("Qrtasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,qrtasksdata);
					 EventListener.registerPreInsertEvent("QRTasks",qrtasksdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QRTasks","QRTasks2RFQ",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBidtasksxml()) ){
					 sql+=query.makeBulkSQL(false,getBidtasksxml(),"BidTasks2RFQ",this.getUsername(),this.getGroupuser());
					 bidtasksdata=query.getTableData();
					 tu.applyObjectRule("Bidtasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bidtasksdata);
					 EventListener.registerPreInsertEvent("BidTasks",bidtasksdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BidTasks","BidTasks2RFQ",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPotasksxml()) ){
					 sql+=query.makeBulkSQL(false,getPotasksxml(),"POTasks2RFQ",this.getUsername(),this.getGroupuser());
					 potasksdata=query.getTableData();
					 tu.applyObjectRule("Potasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,potasksdata);
					 EventListener.registerPreInsertEvent("POTasks",potasksdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"POTasks","POTasks2RFQ",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEmaildocsxml()) ){
					 sql+=query.makeBulkSQL(false,getEmaildocsxml(),"EmailDocs2RFQ",this.getUsername(),this.getGroupuser());
					 emaildocsdata=query.getTableData();
					 tu.applyObjectRule("Emaildocs",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,emaildocsdata);
					 EventListener.registerPreInsertEvent("EmailDocs",emaildocsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EmailDocs","EmailDocs2RFQ",getParentobjid());
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
				 tu.applyConsoleObject("Rfq",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Rfq",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Rfq","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("Rfq",maindata);
				 tu.applyObjectRule("Rfqparts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,rfqpartsdata);
				 tu.applyMTMRelation("rfqparts","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("RfqParts",rfqpartsdata);
				 tu.applyObjectRule("Rfqemail",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,rfqemaildata);
				 tu.applyMTMRelation("rfqemail","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("RFQEmail",rfqemaildata);
				 tu.applyObjectRule("Trtasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,trtasksdata);
				 tu.applyMTMRelation("trtasks","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("TRTasks",trtasksdata);
				 tu.applyObjectRule("Qrtasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,qrtasksdata);
				 tu.applyMTMRelation("qrtasks","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("QRTasks",qrtasksdata);
				 tu.applyObjectRule("Bidtasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bidtasksdata);
				 tu.applyMTMRelation("bidtasks","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("BidTasks",bidtasksdata);
				 tu.applyObjectRule("Potasks",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,potasksdata);
				 tu.applyMTMRelation("potasks","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("POTasks",potasksdata);
				 tu.applyObjectRule("Emaildocs",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,emaildocsdata);
				 tu.applyMTMRelation("emaildocs","Rfq",getParentobjid());
				 EventListener.registerPostInsertEvent("EmailDocs",emaildocsdata);
				 return(true);
			}
			 return(false);
		}
	}
