
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

	 public class ProjectImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ProjectImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String jobmasterxml,jobmasterdeleteid;
		 protected String contactxml,contactdeleteid;
		 protected String estimationxml,estimationdeleteid;
		 protected String projectplanxml,projectplandeleteid;
		 protected String budgetxml,budgetdeleteid;
		 protected String taxxml,taxdeleteid;
		 protected String pomasterxml,pomasterdeleteid;

		 protected TemplateTable jobmasterdata=new TemplateTable();

		 protected TemplateTable contactdata=new TemplateTable();

		 protected TemplateTable estimationdata=new TemplateTable();

		 protected TemplateTable projectplandata=new TemplateTable();

		 protected TemplateTable budgetdata=new TemplateTable();

		 protected TemplateTable taxdata=new TemplateTable();

		 protected TemplateTable pomasterdata=new TemplateTable();

		 public String ProjectFilter="project.objid ProjectNo,totaltax.totaltax TotalTax,budgettotal.totalcontract ContractCost,budgettotal.totalwithtax TotalCost,budgettotal.cumulativeamount ActualCost,budgettotal.achievedtarget AchievedTarget,budgettotal.budgetused BudgetUsed@budgettotal,project,totaltax@totaltax.totaltax2project=project.objid and project.objid=budgettotal.budgettotal2project(+) order by project.name";
		 public String JobmasterFilter="";
		 public String ContactFilter="";
		 public String EstimationFilter="projectplan.startdate StartDate,projectplan.enddate EndDate,jobcost.contractamount ContractAmount,jobcost.estchangecost EstChangeCost,jobcost.actchangecost ActChangeCost,jobcost.estjobcost EstJobCost,jobcost.actjobcost ActJobCost,jobcost.profit NetAmount@jobcost,Estimation ,projectplan@projectplan.projectplan2project=Estimation.Estimation2project and projectplan.maincode=Estimation.maincode and Estimation.objid=jobcost.jobcost2estimation(+) order by Estimation.name";
		 public String ProjectplanFilter="ppfloat.schedule Schedule,ppprogress.achievedtarget AchievedTarget,ppfloat.floatcount FloatCount@ppprogress,projectplan,ppfloat@ppfloat.objid=projectplan.objid and projectplan.objid=ppprogress2projectplan(+) order by projectplan.name";
		 public String BudgetFilter="budgettotal.totalcontract TotalContract,budgettotal.totalwithtax TotalWithTax,budgettotal.totalestamount InitialEstimate,budgettotal.cumulativeamount CumulativeCost,budgettotal.fundreleased FundReleased,budgettotal.BalanceAmount FundBalance,budgettotal.fundused FundUsed,budgettotal.unusedfund FundUnUsed,budgettotal.conttocumulative ContractToCost,budgettotal.achievedtarget AchievedTarget,budgettotal.budgetused BudgetUsed@budgettotal,budget@budget.objid=budgettotal.budgettotal2budget(+) order by budget.name";
		 public String TaxFilter="";
		 public String PomasterFilter="";

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
		 public String getJobmasterxml() {
			 return jobmasterxml;
		 }
		 public void setJobmasterxml(String jobmasterxml) {
			 this.jobmasterxml=jobmasterxml;
		 }
		 public String getJobmasterdeleteid() {
			 return jobmasterdeleteid;
		 }
		 public void setJobmasterdeleteid(String jobmasterdeleteid) {
			 this.jobmasterdeleteid=jobmasterdeleteid;
		 }
		 public String getContactxml() {
			 return contactxml;
		 }
		 public void setContactxml(String contactxml) {
			 this.contactxml=contactxml;
		 }
		 public String getContactdeleteid() {
			 return contactdeleteid;
		 }
		 public void setContactdeleteid(String contactdeleteid) {
			 this.contactdeleteid=contactdeleteid;
		 }
		 public String getEstimationxml() {
			 return estimationxml;
		 }
		 public void setEstimationxml(String estimationxml) {
			 this.estimationxml=estimationxml;
		 }
		 public String getEstimationdeleteid() {
			 return estimationdeleteid;
		 }
		 public void setEstimationdeleteid(String estimationdeleteid) {
			 this.estimationdeleteid=estimationdeleteid;
		 }
		 public String getProjectplanxml() {
			 return projectplanxml;
		 }
		 public void setProjectplanxml(String projectplanxml) {
			 this.projectplanxml=projectplanxml;
		 }
		 public String getProjectplandeleteid() {
			 return projectplandeleteid;
		 }
		 public void setProjectplandeleteid(String projectplandeleteid) {
			 this.projectplandeleteid=projectplandeleteid;
		 }
		 public String getBudgetxml() {
			 return budgetxml;
		 }
		 public void setBudgetxml(String budgetxml) {
			 this.budgetxml=budgetxml;
		 }
		 public String getBudgetdeleteid() {
			 return budgetdeleteid;
		 }
		 public void setBudgetdeleteid(String budgetdeleteid) {
			 this.budgetdeleteid=budgetdeleteid;
		 }
		 public String getTaxxml() {
			 return taxxml;
		 }
		 public void setTaxxml(String taxxml) {
			 this.taxxml=taxxml;
		 }
		 public String getTaxdeleteid() {
			 return taxdeleteid;
		 }
		 public void setTaxdeleteid(String taxdeleteid) {
			 this.taxdeleteid=taxdeleteid;
		 }
		 public String getPomasterxml() {
			 return pomasterxml;
		 }
		 public void setPomasterxml(String pomasterxml) {
			 this.pomasterxml=pomasterxml;
		 }
		 public String getPomasterdeleteid() {
			 return pomasterdeleteid;
		 }
		 public void setPomasterdeleteid(String pomasterdeleteid) {
			 this.pomasterdeleteid=pomasterdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Project",column,datatype);
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
					EventListener.registerPostQueryParent("Project",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Project",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Project",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Project",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Project",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Project",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Project",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Project",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Project2Phase",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Project",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Project",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getJobmasterxml()) ){
					 sql+=query.makeBulkSQL(false,getJobmasterxml(),"JobMaster2Project",this.getUsername(),this.getGroupuser());
					 jobmasterdata=query.getTableData();
					 tu.applyObjectRule("Jobmaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,jobmasterdata);
					 EventListener.registerPreInsertEvent("JobMaster",jobmasterdata);
				
				 String [] autofieldJobMasterlist={"projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"JobMaster",autofieldJobMasterlist,"JobMaster2Project",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"JobMaster","JobMaster2Project",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getContactxml()) ){
					 sql+=query.makeBulkSQL(false,getContactxml(),"Contact2Project",this.getUsername(),this.getGroupuser());
					 contactdata=query.getTableData();
					 tu.applyObjectRule("Contact",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,contactdata);
					 EventListener.registerPreInsertEvent("Contact",contactdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Contact","Contact2Project",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEstimationxml()) ){
					 sql+=query.makeBulkSQL(false,getEstimationxml(),"Estimation2Project",this.getUsername(),this.getGroupuser());
					 estimationdata=query.getTableData();
					 tu.applyObjectRule("Estimation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,estimationdata);
					 EventListener.registerPreInsertEvent("Estimation",estimationdata);
				
				 String [] autofieldEstimationlist={"title","projectcode","maincode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Estimation",autofieldEstimationlist,"Estimation2Project",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Estimation","Estimation2Project",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getProjectplanxml()) ){
					 sql+=query.makeBulkSQL(false,getProjectplanxml(),"ProjectPlan2Project",this.getUsername(),this.getGroupuser());
					 projectplandata=query.getTableData();
					 tu.applyObjectRule("Projectplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,projectplandata);
					 EventListener.registerPreInsertEvent("ProjectPlan",projectplandata);
				
				 String [] autofieldProjectPlanlist={"title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"ProjectPlan",autofieldProjectPlanlist,"ProjectPlan2Project",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ProjectPlan","ProjectPlan2Project",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBudgetxml()) ){
					 sql+=query.makeBulkSQL(false,getBudgetxml(),"Budget2Project",this.getUsername(),this.getGroupuser());
					 budgetdata=query.getTableData();
					 tu.applyObjectRule("Budget",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,budgetdata);
					 EventListener.registerPreInsertEvent("Budget",budgetdata);
				
				 String [] autofieldBudgetlist={"title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Budget",autofieldBudgetlist,"Budget2Project",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Budget","Budget2Project",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTaxxml()) ){
					 sql+=query.makeBulkSQL(false,getTaxxml(),"Tax2Project",this.getUsername(),this.getGroupuser());
					 taxdata=query.getTableData();
					 tu.applyObjectRule("Tax",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,taxdata);
					 EventListener.registerPreInsertEvent("Tax",taxdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Tax","Tax2Project",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPomasterxml()) ){
					 sql+=query.makeBulkSQL(false,getPomasterxml(),"PoMaster2Project",this.getUsername(),this.getGroupuser());
					 pomasterdata=query.getTableData();
					 tu.applyObjectRule("Pomaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,pomasterdata);
					 EventListener.registerPreInsertEvent("PoMaster",pomasterdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PoMaster","PoMaster2Project",getParentobjid());
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
				 tu.applyConsoleObject("Project",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Project",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Project","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("Project",maindata);
				 tu.applyObjectRule("Jobmaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,jobmasterdata);
				 tu.applyMTMRelation("jobmaster","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("JobMaster",jobmasterdata);
				 tu.applyObjectRule("Contact",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,contactdata);
				 tu.applyMTMRelation("contact","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("Contact",contactdata);
				 tu.applyObjectRule("Estimation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,estimationdata);
				 tu.applyMTMRelation("estimation","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("Estimation",estimationdata);
				 tu.applyConsoleObject("estimation",estimationdata,this.getUsername(),groupuser,false);
				 tu.applyObjectRule("Projectplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,projectplandata);
				 tu.applyMTMRelation("projectplan","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("ProjectPlan",projectplandata);
				 tu.applyConsoleObject("projectplan",projectplandata,this.getUsername(),groupuser,false);
				 tu.applyObjectRule("Budget",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,budgetdata);
				 tu.applyMTMRelation("budget","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("Budget",budgetdata);
				 tu.applyObjectRule("Tax",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,taxdata);
				 tu.applyMTMRelation("tax","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("Tax",taxdata);
				 tu.applyObjectRule("Pomaster",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,pomasterdata);
				 tu.applyMTMRelation("pomaster","Project",getParentobjid());
				 EventListener.registerPostInsertEvent("PoMaster",pomasterdata);
				 return(true);
			}
			 return(false);
		}
	}
