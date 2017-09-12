
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

	 public class BidrequestImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BidrequestImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String generalxml,generaldeleteid;
		 protected String invitationxml,invitationdeleteid;
		 protected String artifactsxml,artifactsdeleteid;
		 protected String bidquizxml,bidquizdeleteid;
		 protected String partbidxml,partbiddeleteid;
		 protected String requestinfoxml,requestinfodeleteid;
		 protected String bidsxml,bidsdeleteid;
		 protected String lowbidxml,lowbiddeleteid;
		 protected String purchaseorderxml,purchaseorderdeleteid;
		 protected String resourceorderxml,resourceorderdeleteid;

		 protected TemplateTable generaldata=new TemplateTable();

		 protected TemplateTable invitationdata=new TemplateTable();

		 protected TemplateTable artifactsdata=new TemplateTable();

		 protected TemplateTable bidquizdata=new TemplateTable();

		 protected TemplateTable partbiddata=new TemplateTable();

		 protected TemplateTable requestinfodata=new TemplateTable();

		 protected TemplateTable bidsdata=new TemplateTable();

		 protected TemplateTable lowbiddata=new TemplateTable();

		 protected TemplateTable purchaseorderdata=new TemplateTable();

		 protected TemplateTable resourceorderdata=new TemplateTable();

		 public String BidrequestFilter="LeadTotal.PartTotal EstAmount,LeadResult.LowBidTotal MinBid,LeadResult.HighBidTotal MaxBid,LeadResult.WinBid OfferingBid,LeadResult.ResponseCount ResponseCount,LeadResult.BidCount BidCount,LeadResult.TotRFICount RFICount@BidRequest,LeadResult,LeadTotal@BidRequest.objid=LeadTotal.LeadTotal2BidRequest and BidRequest.objid=LeadResult.LeadResult2BidRequest(+) order by BidRequest.name";
		 public String GeneralFilter="";
		 public String InvitationFilter="";
		 public String ArtifactsFilter="";
		 public String BidquizFilter="";
		 public String PartbidFilter="";
		 public String RequestinfoFilter="";
		 public String BidsFilter="quiztotal.totalpoint TotalPoint,quiztotal.totalearned TotalEarned,quiztotal.totalpct TotalPct,quiztotal.totalcomplete TotalComplete@bids,quiztotal@bids.objid=quiztotal.quiztotal2bids(+)";
		 public String LowbidFilter="";
		 public String PurchaseorderFilter="PoAmount.total Total@PurchaseOrder,PoAmount@PurchaseOrder.objid=PoAmount.PoAmount2PurchaseOrder(+)";
		 public String ResourceorderFilter="PoResAmount.amount Amount,PoResAmount.total Total@ResourceOrder,PoResAmount@ResourceOrder.objid=PoResAmount.PoResAmount2ResourceOrder(+)";

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
		 public String getGeneralxml() {
			 return generalxml;
		 }
		 public void setGeneralxml(String generalxml) {
			 this.generalxml=generalxml;
		 }
		 public String getGeneraldeleteid() {
			 return generaldeleteid;
		 }
		 public void setGeneraldeleteid(String generaldeleteid) {
			 this.generaldeleteid=generaldeleteid;
		 }
		 public String getInvitationxml() {
			 return invitationxml;
		 }
		 public void setInvitationxml(String invitationxml) {
			 this.invitationxml=invitationxml;
		 }
		 public String getInvitationdeleteid() {
			 return invitationdeleteid;
		 }
		 public void setInvitationdeleteid(String invitationdeleteid) {
			 this.invitationdeleteid=invitationdeleteid;
		 }
		 public String getArtifactsxml() {
			 return artifactsxml;
		 }
		 public void setArtifactsxml(String artifactsxml) {
			 this.artifactsxml=artifactsxml;
		 }
		 public String getArtifactsdeleteid() {
			 return artifactsdeleteid;
		 }
		 public void setArtifactsdeleteid(String artifactsdeleteid) {
			 this.artifactsdeleteid=artifactsdeleteid;
		 }
		 public String getBidquizxml() {
			 return bidquizxml;
		 }
		 public void setBidquizxml(String bidquizxml) {
			 this.bidquizxml=bidquizxml;
		 }
		 public String getBidquizdeleteid() {
			 return bidquizdeleteid;
		 }
		 public void setBidquizdeleteid(String bidquizdeleteid) {
			 this.bidquizdeleteid=bidquizdeleteid;
		 }
		 public String getPartbidxml() {
			 return partbidxml;
		 }
		 public void setPartbidxml(String partbidxml) {
			 this.partbidxml=partbidxml;
		 }
		 public String getPartbiddeleteid() {
			 return partbiddeleteid;
		 }
		 public void setPartbiddeleteid(String partbiddeleteid) {
			 this.partbiddeleteid=partbiddeleteid;
		 }
		 public String getRequestinfoxml() {
			 return requestinfoxml;
		 }
		 public void setRequestinfoxml(String requestinfoxml) {
			 this.requestinfoxml=requestinfoxml;
		 }
		 public String getRequestinfodeleteid() {
			 return requestinfodeleteid;
		 }
		 public void setRequestinfodeleteid(String requestinfodeleteid) {
			 this.requestinfodeleteid=requestinfodeleteid;
		 }
		 public String getBidsxml() {
			 return bidsxml;
		 }
		 public void setBidsxml(String bidsxml) {
			 this.bidsxml=bidsxml;
		 }
		 public String getBidsdeleteid() {
			 return bidsdeleteid;
		 }
		 public void setBidsdeleteid(String bidsdeleteid) {
			 this.bidsdeleteid=bidsdeleteid;
		 }
		 public String getLowbidxml() {
			 return lowbidxml;
		 }
		 public void setLowbidxml(String lowbidxml) {
			 this.lowbidxml=lowbidxml;
		 }
		 public String getLowbiddeleteid() {
			 return lowbiddeleteid;
		 }
		 public void setLowbiddeleteid(String lowbiddeleteid) {
			 this.lowbiddeleteid=lowbiddeleteid;
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


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Bidrequest",column,datatype);
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
					EventListener.registerPostQueryParent("Bidrequest",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Bidrequest",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Bidrequest",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Bidrequest",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Bidrequest",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Bidrequest",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Bidrequest",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Bidrequest",this.getParentobjid());
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
					tu.applyObjectRule("Bidrequest",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Bidrequest",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getGeneralxml()) ){
					 sql+=query.makeBulkSQL(false,getGeneralxml(),"General2BidRequest",this.getUsername(),this.getGroupuser());
					 generaldata=query.getTableData();
					 tu.applyObjectRule("General",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,generaldata);
					 EventListener.registerPreInsertEvent("General",generaldata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"General","General2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getInvitationxml()) ){
					 sql+=query.makeBulkSQL(false,getInvitationxml(),"Invitation2BidRequest",this.getUsername(),this.getGroupuser());
					 invitationdata=query.getTableData();
					 tu.applyObjectRule("Invitation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,invitationdata);
					 EventListener.registerPreInsertEvent("Invitation",invitationdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Invitation","Invitation2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getArtifactsxml()) ){
					 sql+=query.makeBulkSQL(false,getArtifactsxml(),"Artifacts2BidRequest",this.getUsername(),this.getGroupuser());
					 artifactsdata=query.getTableData();
					 tu.applyObjectRule("Artifacts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,artifactsdata);
					 EventListener.registerPreInsertEvent("Artifacts",artifactsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Artifacts","Artifacts2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBidquizxml()) ){
					 sql+=query.makeBulkSQL(false,getBidquizxml(),"BidQuiz2BidRequest",this.getUsername(),this.getGroupuser());
					 bidquizdata=query.getTableData();
					 tu.applyObjectRule("Bidquiz",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bidquizdata);
					 EventListener.registerPreInsertEvent("BidQuiz",bidquizdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BidQuiz","BidQuiz2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPartbidxml()) ){
					 sql+=query.makeBulkSQL(false,getPartbidxml(),"PartBid2BidRequest",this.getUsername(),this.getGroupuser());
					 partbiddata=query.getTableData();
					 tu.applyObjectRule("Partbid",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partbiddata);
					 EventListener.registerPreInsertEvent("PartBid",partbiddata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PartBid","PartBid2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getRequestinfoxml()) ){
					 sql+=query.makeBulkSQL(false,getRequestinfoxml(),"RequestInfo2BidRequest",this.getUsername(),this.getGroupuser());
					 requestinfodata=query.getTableData();
					 tu.applyObjectRule("Requestinfo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,requestinfodata);
					 EventListener.registerPreInsertEvent("RequestInfo",requestinfodata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"RequestInfo","RequestInfo2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBidsxml()) ){
					 sql+=query.makeBulkSQL(false,getBidsxml(),"Bids2BidRequest",this.getUsername(),this.getGroupuser());
					 bidsdata=query.getTableData();
					 tu.applyObjectRule("Bids",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bidsdata);
					 EventListener.registerPreInsertEvent("Bids",bidsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Bids","Bids2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getLowbidxml()) ){
					 sql+=query.makeBulkSQL(false,getLowbidxml(),"LowBid2BidRequest",this.getUsername(),this.getGroupuser());
					 lowbiddata=query.getTableData();
					 tu.applyObjectRule("Lowbid",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,lowbiddata);
					 EventListener.registerPreInsertEvent("LowBid",lowbiddata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"LowBid","LowBid2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPurchaseorderxml()) ){
					 sql+=query.makeBulkSQL(false,getPurchaseorderxml(),"PurchaseOrder2BidRequest",this.getUsername(),this.getGroupuser());
					 purchaseorderdata=query.getTableData();
					 tu.applyObjectRule("Purchaseorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,purchaseorderdata);
					 EventListener.registerPreInsertEvent("PurchaseOrder",purchaseorderdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PurchaseOrder","PurchaseOrder2BidRequest",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getResourceorderxml()) ){
					 sql+=query.makeBulkSQL(false,getResourceorderxml(),"ResourceOrder2BidRequest",this.getUsername(),this.getGroupuser());
					 resourceorderdata=query.getTableData();
					 tu.applyObjectRule("Resourceorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,resourceorderdata);
					 EventListener.registerPreInsertEvent("ResourceOrder",resourceorderdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ResourceOrder","ResourceOrder2BidRequest",getParentobjid());
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
				 tu.applyConsoleObject("Bidrequest",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Bidrequest",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Bidrequest","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("Bidrequest",maindata);
				 tu.applyObjectRule("General",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,generaldata);
				 tu.applyMTMRelation("general","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("General",generaldata);
				 tu.applyObjectRule("Invitation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,invitationdata);
				 tu.applyMTMRelation("invitation","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("Invitation",invitationdata);
				 tu.applyObjectRule("Artifacts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,artifactsdata);
				 tu.applyMTMRelation("artifacts","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("Artifacts",artifactsdata);
				 tu.applyObjectRule("Bidquiz",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bidquizdata);
				 tu.applyMTMRelation("bidquiz","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("BidQuiz",bidquizdata);
				 tu.applyObjectRule("Partbid",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partbiddata);
				 tu.applyMTMRelation("partbid","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("PartBid",partbiddata);
				 tu.applyObjectRule("Requestinfo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,requestinfodata);
				 tu.applyMTMRelation("requestinfo","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("RequestInfo",requestinfodata);
				 tu.applyObjectRule("Bids",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bidsdata);
				 tu.applyMTMRelation("bids","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("Bids",bidsdata);
				 tu.applyObjectRule("Lowbid",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,lowbiddata);
				 tu.applyMTMRelation("lowbid","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("LowBid",lowbiddata);
				 tu.applyObjectRule("Purchaseorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,purchaseorderdata);
				 tu.applyMTMRelation("purchaseorder","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("PurchaseOrder",purchaseorderdata);
				 tu.applyObjectRule("Resourceorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,resourceorderdata);
				 tu.applyMTMRelation("resourceorder","Bidrequest",getParentobjid());
				 EventListener.registerPostInsertEvent("ResourceOrder",resourceorderdata);
				 return(true);
			}
			 return(false);
		}
	}
