
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

	 public class QuoteImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(QuoteImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String quotemasterxml,quotemasterdeleteid;
		 protected String quotelogxml,quotelogdeleteid;

		 protected TemplateTable quotemasterdata=new TemplateTable();

		 protected TemplateTable quotelogdata=new TemplateTable();

		 public String QuoteFilter="quotetotal.total EstAmount@Quotetotal,Quote@quote.objid=Quotetotal.Quotetotal2Quote(+)";
		 public String QuotemasterFilter="QuoteMasterEst.Total Total@QuoteMaster,QuoteMasterEst,MainCode@QuoteMaster.objid=QuoteMasterEst.objid(+) and QuoteMaster.projectcode=maincode.projectcode and QuoteMaster.maincode=maincode.mainjobcode order by QuoteMaster.name";
		 public String QuotelogFilter="";

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
		 public String getQuotemasterxml() {
			 return quotemasterxml;
		 }
		 public void setQuotemasterxml(String quotemasterxml) {
			 this.quotemasterxml=quotemasterxml;
		 }
		 public String getQuotemasterdeleteid() {
			 return quotemasterdeleteid;
		 }
		 public void setQuotemasterdeleteid(String quotemasterdeleteid) {
			 this.quotemasterdeleteid=quotemasterdeleteid;
		 }
		 public String getQuotelogxml() {
			 return quotelogxml;
		 }
		 public void setQuotelogxml(String quotelogxml) {
			 this.quotelogxml=quotelogxml;
		 }
		 public String getQuotelogdeleteid() {
			 return quotelogdeleteid;
		 }
		 public void setQuotelogdeleteid(String quotelogdeleteid) {
			 this.quotelogdeleteid=quotelogdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Quote",column,datatype);
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
					EventListener.registerPostQueryParent("Quote",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Quote",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Quote",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Quote",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Quote",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Quote",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Quote",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Quote",this.getParentobjid());
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
					tu.applyObjectRule("Quote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Quote",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getQuotemasterxml()) ){
					 sql+=query.makeBulkSQL(false,getQuotemasterxml(),"QuoteMaster2Quote",this.getUsername(),this.getGroupuser());
					 quotemasterdata=query.getTableData();
					 tu.applyObjectRule("Quotemaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,quotemasterdata);
					 EventListener.registerPreInsertEvent("QuoteMaster",quotemasterdata);
				
				 String [] autofieldQuoteMasterlist={"projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"QuoteMaster",autofieldQuoteMasterlist,"QuoteMaster2Quote",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QuoteMaster","QuoteMaster2Quote",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getQuotelogxml()) ){
					 sql+=query.makeBulkSQL(false,getQuotelogxml(),"QuoteLog2Quote",this.getUsername(),this.getGroupuser());
					 quotelogdata=query.getTableData();
					 tu.applyObjectRule("Quotelog",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,quotelogdata);
					 EventListener.registerPreInsertEvent("QuoteLog",quotelogdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QuoteLog","QuoteLog2Quote",getParentobjid());
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
				 tu.applyConsoleObject("Quote",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Quote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Quote","Quote",getParentobjid());
				 EventListener.registerPostInsertEvent("Quote",maindata);
				 tu.applyObjectRule("Quotemaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,quotemasterdata);
				 tu.applyMTMRelation("quotemaster","Quote",getParentobjid());
				 EventListener.registerPostInsertEvent("QuoteMaster",quotemasterdata);
				 tu.applyObjectRule("Quotelog",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,quotelogdata);
				 tu.applyMTMRelation("quotelog","Quote",getParentobjid());
				 EventListener.registerPostInsertEvent("QuoteLog",quotelogdata);
				 return(true);
			}
			 return(false);
		}
	}
