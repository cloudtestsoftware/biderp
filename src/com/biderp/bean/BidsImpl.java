
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

	 public class BidsImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BidsImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String itemcostxml,itemcostdeleteid;
		 protected String quizreplyxml,quizreplydeleteid;
		 protected String attachmentxml,attachmentdeleteid;

		 protected TemplateTable itemcostdata=new TemplateTable();

		 protected TemplateTable quizreplydata=new TemplateTable();

		 protected TemplateTable attachmentdata=new TemplateTable();

		 public String BidsFilter="quiztotal.totalpoint TotalPoint,quiztotal.totalearned TotalEarned,quiztotal.totalpct TotalPct,quiztotal.totalcomplete TotalComplete@bids,quiztotal@bids.objid=quiztotal.quiztotal2bids(+)";
		 public String ItemcostFilter="";
		 public String QuizreplyFilter="bidquiz.quizindex QuizIndex,bidquiz.Description Description,bidquiz.RequireCode RequireCode,bidquiz.UmCode UmCode,bidquiz.unitcount UnitCount,bidquiz.Criteria Criteria,bidquiz.point Point,vendordoc.attachments Url@quizreply,bidquiz,vendordoc@vendordoc.destinitionid=bidquiz.bidquiz2bidrequest and quizreply.objid=vendordoc.vendordoc2quizreply and vendordoc.objid=quizreply.quizreply2vendorbid and quizreply.destinitionid=bidquiz.objid order by quizreply.objid";
		 public String AttachmentFilter="";

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
		 public String getItemcostxml() {
			 return itemcostxml;
		 }
		 public void setItemcostxml(String itemcostxml) {
			 this.itemcostxml=itemcostxml;
		 }
		 public String getItemcostdeleteid() {
			 return itemcostdeleteid;
		 }
		 public void setItemcostdeleteid(String itemcostdeleteid) {
			 this.itemcostdeleteid=itemcostdeleteid;
		 }
		 public String getQuizreplyxml() {
			 return quizreplyxml;
		 }
		 public void setQuizreplyxml(String quizreplyxml) {
			 this.quizreplyxml=quizreplyxml;
		 }
		 public String getQuizreplydeleteid() {
			 return quizreplydeleteid;
		 }
		 public void setQuizreplydeleteid(String quizreplydeleteid) {
			 this.quizreplydeleteid=quizreplydeleteid;
		 }
		 public String getAttachmentxml() {
			 return attachmentxml;
		 }
		 public void setAttachmentxml(String attachmentxml) {
			 this.attachmentxml=attachmentxml;
		 }
		 public String getAttachmentdeleteid() {
			 return attachmentdeleteid;
		 }
		 public void setAttachmentdeleteid(String attachmentdeleteid) {
			 this.attachmentdeleteid=attachmentdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Bids",column,datatype);
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
					EventListener.registerPostQueryParent("Bids",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Bids",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Bids",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Bids",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Bids",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Bids",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Bids",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Bids",this.getParentobjid());
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
					tu.applyObjectRule("Bids",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Bids",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getItemcostxml()) ){
					 sql+=query.makeBulkSQL(false,getItemcostxml(),"ItemCost2Bids",this.getUsername(),this.getGroupuser());
					 itemcostdata=query.getTableData();
					 tu.applyObjectRule("Itemcost",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,itemcostdata);
					 EventListener.registerPreInsertEvent("ItemCost",itemcostdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ItemCost","ItemCost2Bids",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getQuizreplyxml()) ){
					 sql+=query.makeBulkSQL(false,getQuizreplyxml(),"QuizReply2Bids",this.getUsername(),this.getGroupuser());
					 quizreplydata=query.getTableData();
					 tu.applyObjectRule("Quizreply",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,quizreplydata);
					 EventListener.registerPreInsertEvent("QuizReply",quizreplydata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"QuizReply","QuizReply2Bids",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAttachmentxml()) ){
					 sql+=query.makeBulkSQL(false,getAttachmentxml(),"Attachment2Bids",this.getUsername(),this.getGroupuser());
					 attachmentdata=query.getTableData();
					 tu.applyObjectRule("Attachment",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,attachmentdata);
					 EventListener.registerPreInsertEvent("Attachment",attachmentdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Attachment","Attachment2Bids",getParentobjid());
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
				 tu.applyObjectRule("Bids",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Bids","Bids",getParentobjid());
				 EventListener.registerPostInsertEvent("Bids",maindata);
				 tu.applyObjectRule("Itemcost",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,itemcostdata);
				 tu.applyMTMRelation("itemcost","Bids",getParentobjid());
				 EventListener.registerPostInsertEvent("ItemCost",itemcostdata);
				 tu.applyObjectRule("Quizreply",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,quizreplydata);
				 tu.applyMTMRelation("quizreply","Bids",getParentobjid());
				 EventListener.registerPostInsertEvent("QuizReply",quizreplydata);
				 tu.applyObjectRule("Attachment",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,attachmentdata);
				 tu.applyMTMRelation("attachment","Bids",getParentobjid());
				 EventListener.registerPostInsertEvent("Attachment",attachmentdata);
				 return(true);
			}
			 return(false);
		}
	}
