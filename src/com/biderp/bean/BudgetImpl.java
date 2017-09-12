
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

	 public class BudgetImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BudgetImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String budgetheadxml,budgetheaddeleteid;
		 protected String budgetplanxml,budgetplandeleteid;
		 protected String invoicexml,invoicedeleteid;
		 protected String paymentxml,paymentdeleteid;
		 protected String monthlyxml,monthlydeleteid;
		 protected String yearlyxml,yearlydeleteid;

		 protected TemplateTable budgetheaddata=new TemplateTable();

		 protected TemplateTable budgetplandata=new TemplateTable();

		 protected TemplateTable invoicedata=new TemplateTable();

		 protected TemplateTable paymentdata=new TemplateTable();

		 protected TemplateTable monthlydata=new TemplateTable();

		 protected TemplateTable yearlydata=new TemplateTable();

		 public String BudgetFilter="budgettotal.totalcontract TotalContract,budgettotal.totalwithtax TotalWithTax,budgettotal.totalestamount InitialEstimate,budgettotal.cumulativeamount CumulativeCost,budgettotal.fundreleased FundReleased,budgettotal.BalanceAmount FundBalance,budgettotal.fundused FundUsed,budgettotal.unusedfund FundUnUsed,budgettotal.conttocumulative ContractToCost,budgettotal.achievedtarget AchievedTarget,budgettotal.budgetused BudgetUsed@budgettotal,budget@budget.objid=budgettotal.budgettotal2budget(+) order by budget.name";
		 public String BudgetheadFilter="DeptBudget.PctSpent PctSpent,DeptBudget.PctBalance PctBalance,DeptBudget.total Total,DeptBudget.Balance Balance,DeptBudget.AmountSpent AmountSpent@DeptBudget,BudgetHead@BudgetHead.objid=DeptBudget.DeptBudget2BudgetHead(+) order by BudgetHead.name";
		 public String BudgetplanFilter="PlanReceived.plannedamount PlannedAmount,PlanReceived.invoiced Received,PlanReceived.notinvoiced NotReceived@PlanReceived,budgetplan@budgetplan.objid=PlanReceived.PlanReceived2budgetplan order by budgetplan.name";
		 public String InvoiceFilter="invoiceamount.amount Amount,invoiceamount.taxamount Tax,invoiceamount.total Total@Invoice,InvoiceAmount@InvoiceAmount.InvoiceAmount2Invoice(+)=Invoice.objid";
		 public String PaymentFilter="";
		 public String MonthlyFilter="monthlyforcast.monthlyestimated FundRequested,monthlyforcast.monthlyapproved FundApproved,monthlyforcast.pctprojectestimate PctProjectEst,monthlyforcast.pctprojectapproved AlotedPercent@monthlyforcast,monthly@monthly.objid=monthlyforcast.monthlyforcast2monthly(+) order by monthly.yearcode,monthly.monthcode";
		 public String YearlyFilter="yearlyforcast.yearlyestimated FundRequested,yearlyforcast.yearlyapproved FundApproved,yearlyforcast.pctprojectestimate PctProjectEst,yearlyforcast.pctprojectapproved AlotedPercent@yearlyforcast,yearly@yearly.objid=yearlyforcast.yearlyforcast2yearly(+) order by yearly.name";

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
		 public String getBudgetheadxml() {
			 return budgetheadxml;
		 }
		 public void setBudgetheadxml(String budgetheadxml) {
			 this.budgetheadxml=budgetheadxml;
		 }
		 public String getBudgetheaddeleteid() {
			 return budgetheaddeleteid;
		 }
		 public void setBudgetheaddeleteid(String budgetheaddeleteid) {
			 this.budgetheaddeleteid=budgetheaddeleteid;
		 }
		 public String getBudgetplanxml() {
			 return budgetplanxml;
		 }
		 public void setBudgetplanxml(String budgetplanxml) {
			 this.budgetplanxml=budgetplanxml;
		 }
		 public String getBudgetplandeleteid() {
			 return budgetplandeleteid;
		 }
		 public void setBudgetplandeleteid(String budgetplandeleteid) {
			 this.budgetplandeleteid=budgetplandeleteid;
		 }
		 public String getInvoicexml() {
			 return invoicexml;
		 }
		 public void setInvoicexml(String invoicexml) {
			 this.invoicexml=invoicexml;
		 }
		 public String getInvoicedeleteid() {
			 return invoicedeleteid;
		 }
		 public void setInvoicedeleteid(String invoicedeleteid) {
			 this.invoicedeleteid=invoicedeleteid;
		 }
		 public String getPaymentxml() {
			 return paymentxml;
		 }
		 public void setPaymentxml(String paymentxml) {
			 this.paymentxml=paymentxml;
		 }
		 public String getPaymentdeleteid() {
			 return paymentdeleteid;
		 }
		 public void setPaymentdeleteid(String paymentdeleteid) {
			 this.paymentdeleteid=paymentdeleteid;
		 }
		 public String getMonthlyxml() {
			 return monthlyxml;
		 }
		 public void setMonthlyxml(String monthlyxml) {
			 this.monthlyxml=monthlyxml;
		 }
		 public String getMonthlydeleteid() {
			 return monthlydeleteid;
		 }
		 public void setMonthlydeleteid(String monthlydeleteid) {
			 this.monthlydeleteid=monthlydeleteid;
		 }
		 public String getYearlyxml() {
			 return yearlyxml;
		 }
		 public void setYearlyxml(String yearlyxml) {
			 this.yearlyxml=yearlyxml;
		 }
		 public String getYearlydeleteid() {
			 return yearlydeleteid;
		 }
		 public void setYearlydeleteid(String yearlydeleteid) {
			 this.yearlydeleteid=yearlydeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Budget",column,datatype);
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
					EventListener.registerPostQueryParent("Budget",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Budget",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Budget",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Budget",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Budget",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Budget",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Budget",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Budget",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Budget2Project",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Budget",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Budget",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getBudgetheadxml()) ){
					 sql+=query.makeBulkSQL(false,getBudgetheadxml(),"BudgetHead2Budget",this.getUsername(),this.getGroupuser());
					 budgetheaddata=query.getTableData();
					 tu.applyObjectRule("Budgethead",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,budgetheaddata);
					 EventListener.registerPreInsertEvent("BudgetHead",budgetheaddata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BudgetHead","BudgetHead2Budget",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBudgetplanxml()) ){
					 sql+=query.makeBulkSQL(false,getBudgetplanxml(),"BudgetPlan2Budget",this.getUsername(),this.getGroupuser());
					 budgetplandata=query.getTableData();
					 tu.applyObjectRule("Budgetplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,budgetplandata);
					 EventListener.registerPreInsertEvent("BudgetPlan",budgetplandata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BudgetPlan","BudgetPlan2Budget",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getInvoicexml()) ){
					 sql+=query.makeBulkSQL(false,getInvoicexml(),"Invoice2Budget",this.getUsername(),this.getGroupuser());
					 invoicedata=query.getTableData();
					 tu.applyObjectRule("Invoice",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,invoicedata);
					 EventListener.registerPreInsertEvent("Invoice",invoicedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Invoice","Invoice2Budget",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPaymentxml()) ){
					 sql+=query.makeBulkSQL(false,getPaymentxml(),"Payment2Budget",this.getUsername(),this.getGroupuser());
					 paymentdata=query.getTableData();
					 tu.applyObjectRule("Payment",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,paymentdata);
					 EventListener.registerPreInsertEvent("Payment",paymentdata);
				
				 String [] autofieldPaymentlist={"title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Payment",autofieldPaymentlist,"Payment2Budget",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Payment","Payment2Budget",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getMonthlyxml()) ){
					 sql+=query.makeBulkSQL(false,getMonthlyxml(),"Monthly2Budget",this.getUsername(),this.getGroupuser());
					 monthlydata=query.getTableData();
					 tu.applyObjectRule("Monthly",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,monthlydata);
					 EventListener.registerPreInsertEvent("Monthly",monthlydata);
				
				 String [] autofieldMonthlylist={"title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Monthly",autofieldMonthlylist,"Monthly2Budget",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Monthly","Monthly2Budget",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getYearlyxml()) ){
					 sql+=query.makeBulkSQL(false,getYearlyxml(),"Yearly2Budget",this.getUsername(),this.getGroupuser());
					 yearlydata=query.getTableData();
					 tu.applyObjectRule("Yearly",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,yearlydata);
					 EventListener.registerPreInsertEvent("Yearly",yearlydata);
				
				 String [] autofieldYearlylist={"title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Yearly",autofieldYearlylist,"Yearly2Budget",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Yearly","Yearly2Budget",getParentobjid());
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
				 tu.applyObjectRule("Budget",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Budget","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("Budget",maindata);
				 tu.applyObjectRule("Budgethead",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,budgetheaddata);
				 tu.applyMTMRelation("budgethead","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("BudgetHead",budgetheaddata);
				 tu.applyObjectRule("Budgetplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,budgetplandata);
				 tu.applyMTMRelation("budgetplan","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("BudgetPlan",budgetplandata);
				 tu.applyObjectRule("Invoice",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,invoicedata);
				 tu.applyMTMRelation("invoice","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("Invoice",invoicedata);
				 tu.applyObjectRule("Payment",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,paymentdata);
				 tu.applyMTMRelation("payment","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("Payment",paymentdata);
				 tu.applyObjectRule("Monthly",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,monthlydata);
				 tu.applyMTMRelation("monthly","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("Monthly",monthlydata);
				 tu.applyObjectRule("Yearly",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,yearlydata);
				 tu.applyMTMRelation("yearly","Budget",getParentobjid());
				 EventListener.registerPostInsertEvent("Yearly",yearlydata);
				 return(true);
			}
			 return(false);
		}
	}
