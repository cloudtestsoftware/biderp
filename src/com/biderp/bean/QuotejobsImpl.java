
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

	 public class QuotejobsImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(QuotejobsImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String bidquizxml,bidquizdeleteid;
		 protected String quotepartsxml,quotepartsdeleteid;
		 protected String quoteresourcexml,quoteresourcedeleteid;

		 protected TemplateTable bidquizdata=new TemplateTable();

		 protected TemplateTable quotepartsdata=new TemplateTable();

		 protected TemplateTable quoteresourcedata=new TemplateTable();

		 public String QuotejobsFilter="subcode.UmCode UmCode,subcode.unitrate UnitPrice,QuoteEst.estrate EstRate,QuoteEst.profitrate ProfitRate,QuoteEst.Total Total@QuoteJobs,QuoteEst,SubCode@QuoteJobs.objid=QuoteEst.objid and QuoteJobs.projectcode=subcode.projectcode and QuoteJobs.maincode=subcode.mainjobcode and QuoteJobs.subcode=subcode.subjobcode  order by QuoteJobs.name";
		 public String BidquizFilter="";
		 public String QuotepartsFilter="partprice.domaincode DomainCode,partlist.partcode PartCode,partprice.partno PartNo,partlist.description Description,partprice.unitprice UnitPrice,partprice.UmCode UmCode@partlist,partprice,QuoteParts@QuoteParts.QuoteParts2partprice=partprice.objid and partprice.partprice2partlist=partlist.objid order by QuoteParts.name";
		 public String QuoteresourceFilter="partprice.UmCode UmCode,partprice.unitprice Rate@partprice,QuoteResource@QuoteResource.QuoteResource2partprice=partprice.objid order by QuoteResource.name";

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
		 public String getQuotepartsxml() {
			 return quotepartsxml;
		 }
		 public void setQuotepartsxml(String quotepartsxml) {
			 this.quotepartsxml=quotepartsxml;
		 }
		 public String getQuotepartsdeleteid() {
			 return quotepartsdeleteid;
		 }
		 public void setQuotepartsdeleteid(String quotepartsdeleteid) {
			 this.quotepartsdeleteid=quotepartsdeleteid;
		 }
		 public String getQuoteresourcexml() {
			 return quoteresourcexml;
		 }
		 public void setQuoteresourcexml(String quoteresourcexml) {
			 this.quoteresourcexml=quoteresourcexml;
		 }
		 public String getQuoteresourcedeleteid() {
			 return quoteresourcedeleteid;
		 }
		 public void setQuoteresourcedeleteid(String quoteresourcedeleteid) {
			 this.quoteresourcedeleteid=quoteresourcedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Quotejobs",column,datatype);
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
					EventListener.registerPostQueryParent("Quotejobs",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Quotejobs",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Quotejobs",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Quotejobs",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Quotejobs",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Quotejobs",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Quotejobs",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Quotejobs",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"QuoteJobs2QuoteMaster",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Quotejobs",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Quotejobs",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getBidquizxml()) ){
					 sql+=query.makeBulkSQL(false,getBidquizxml(),"BidQuiz2QuoteJobs",this.getUsername(),this.getGroupuser());
					 bidquizdata=query.getTableData();
					 tu.applyObjectRule("Bidquiz",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bidquizdata);
					 EventListener.registerPreInsertEvent("BidQuiz",bidquizdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BidQuiz","BidQuiz2QuoteJobs",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getQuotepartsxml()) ){
					 sql+=query.makeBulkSQL(false,getQuotepartsxml(),"QuoteParts2QuoteJobs",this.getUsername(),this.getGroupuser());
					 quotepartsdata=query.getTableData();
					 tu.applyObjectRule("Quoteparts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,quotepartsdata);
					 EventListener.registerPreInsertEvent("QuoteParts",quotepartsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QuoteParts","QuoteParts2QuoteJobs",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getQuoteresourcexml()) ){
					 sql+=query.makeBulkSQL(false,getQuoteresourcexml(),"QuoteResource2QuoteJobs",this.getUsername(),this.getGroupuser());
					 quoteresourcedata=query.getTableData();
					 tu.applyObjectRule("Quoteresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,quoteresourcedata);
					 EventListener.registerPreInsertEvent("QuoteResource",quoteresourcedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QuoteResource","QuoteResource2QuoteJobs",getParentobjid());
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
				 tu.applyObjectRule("Quotejobs",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Quotejobs","Quotejobs",getParentobjid());
				 EventListener.registerPostInsertEvent("Quotejobs",maindata);
				 tu.applyObjectRule("Bidquiz",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bidquizdata);
				 tu.applyMTMRelation("bidquiz","Quotejobs",getParentobjid());
				 EventListener.registerPostInsertEvent("BidQuiz",bidquizdata);
				 tu.applyObjectRule("Quoteparts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,quotepartsdata);
				 tu.applyMTMRelation("quoteparts","Quotejobs",getParentobjid());
				 EventListener.registerPostInsertEvent("QuoteParts",quotepartsdata);
				 tu.applyObjectRule("Quoteresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,quoteresourcedata);
				 tu.applyMTMRelation("quoteresource","Quotejobs",getParentobjid());
				 EventListener.registerPostInsertEvent("QuoteResource",quoteresourcedata);
				 return(true);
			}
			 return(false);
		}
	}
