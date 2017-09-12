
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

	 public class EmployeeImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(EmployeeImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String contactxml,contactdeleteid;
		 protected String payrolexml,payroledeleteid;
		 protected String benifitxml,benifitdeleteid;
		 protected String dependentxml,dependentdeleteid;
		 protected String deductionxml,deductiondeleteid;
		 protected String additionxml,additiondeleteid;
		 protected String substructionxml,substructiondeleteid;
		 protected String vacationxml,vacationdeleteid;
		 protected String earnedvacationxml,earnedvacationdeleteid;
		 protected String benifitchangexml,benifitchangedeleteid;
		 protected String taxxml,taxdeleteid;

		 protected TemplateTable contactdata=new TemplateTable();

		 protected TemplateTable payroledata=new TemplateTable();

		 protected TemplateTable benifitdata=new TemplateTable();

		 protected TemplateTable dependentdata=new TemplateTable();

		 protected TemplateTable deductiondata=new TemplateTable();

		 protected TemplateTable additiondata=new TemplateTable();

		 protected TemplateTable substructiondata=new TemplateTable();

		 protected TemplateTable vacationdata=new TemplateTable();

		 protected TemplateTable earnedvacationdata=new TemplateTable();

		 protected TemplateTable benifitchangedata=new TemplateTable();

		 protected TemplateTable taxdata=new TemplateTable();

		 public String EmployeeFilter="";
		 public String ContactFilter="";
		 public String PayroleFilter="payroletotal.netaftertax Amount@payroletotal,payrole@payroletotal.payroletotal2payrole=payrole.objid";
		 public String BenifitFilter="";
		 public String DependentFilter="";
		 public String DeductionFilter="";
		 public String AdditionFilter="";
		 public String SubstructionFilter="";
		 public String VacationFilter="";
		 public String EarnedvacationFilter="vacationtotal.totaldays TotalDays,vacationtotal.balancedays BalanceDays@EarnedVacation,vacationtotal@earnedvacation.objid=vacationtotal2earnedvacation(+) and earnedvacation.yearcode=vacationtotal.yearcode and earnedvacation.vacationcode=vacationtotal.vacationcode";
		 public String BenifitchangeFilter="";
		 public String TaxFilter="";

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
		 public String getPayrolexml() {
			 return payrolexml;
		 }
		 public void setPayrolexml(String payrolexml) {
			 this.payrolexml=payrolexml;
		 }
		 public String getPayroledeleteid() {
			 return payroledeleteid;
		 }
		 public void setPayroledeleteid(String payroledeleteid) {
			 this.payroledeleteid=payroledeleteid;
		 }
		 public String getBenifitxml() {
			 return benifitxml;
		 }
		 public void setBenifitxml(String benifitxml) {
			 this.benifitxml=benifitxml;
		 }
		 public String getBenifitdeleteid() {
			 return benifitdeleteid;
		 }
		 public void setBenifitdeleteid(String benifitdeleteid) {
			 this.benifitdeleteid=benifitdeleteid;
		 }
		 public String getDependentxml() {
			 return dependentxml;
		 }
		 public void setDependentxml(String dependentxml) {
			 this.dependentxml=dependentxml;
		 }
		 public String getDependentdeleteid() {
			 return dependentdeleteid;
		 }
		 public void setDependentdeleteid(String dependentdeleteid) {
			 this.dependentdeleteid=dependentdeleteid;
		 }
		 public String getDeductionxml() {
			 return deductionxml;
		 }
		 public void setDeductionxml(String deductionxml) {
			 this.deductionxml=deductionxml;
		 }
		 public String getDeductiondeleteid() {
			 return deductiondeleteid;
		 }
		 public void setDeductiondeleteid(String deductiondeleteid) {
			 this.deductiondeleteid=deductiondeleteid;
		 }
		 public String getAdditionxml() {
			 return additionxml;
		 }
		 public void setAdditionxml(String additionxml) {
			 this.additionxml=additionxml;
		 }
		 public String getAdditiondeleteid() {
			 return additiondeleteid;
		 }
		 public void setAdditiondeleteid(String additiondeleteid) {
			 this.additiondeleteid=additiondeleteid;
		 }
		 public String getSubstructionxml() {
			 return substructionxml;
		 }
		 public void setSubstructionxml(String substructionxml) {
			 this.substructionxml=substructionxml;
		 }
		 public String getSubstructiondeleteid() {
			 return substructiondeleteid;
		 }
		 public void setSubstructiondeleteid(String substructiondeleteid) {
			 this.substructiondeleteid=substructiondeleteid;
		 }
		 public String getVacationxml() {
			 return vacationxml;
		 }
		 public void setVacationxml(String vacationxml) {
			 this.vacationxml=vacationxml;
		 }
		 public String getVacationdeleteid() {
			 return vacationdeleteid;
		 }
		 public void setVacationdeleteid(String vacationdeleteid) {
			 this.vacationdeleteid=vacationdeleteid;
		 }
		 public String getEarnedvacationxml() {
			 return earnedvacationxml;
		 }
		 public void setEarnedvacationxml(String earnedvacationxml) {
			 this.earnedvacationxml=earnedvacationxml;
		 }
		 public String getEarnedvacationdeleteid() {
			 return earnedvacationdeleteid;
		 }
		 public void setEarnedvacationdeleteid(String earnedvacationdeleteid) {
			 this.earnedvacationdeleteid=earnedvacationdeleteid;
		 }
		 public String getBenifitchangexml() {
			 return benifitchangexml;
		 }
		 public void setBenifitchangexml(String benifitchangexml) {
			 this.benifitchangexml=benifitchangexml;
		 }
		 public String getBenifitchangedeleteid() {
			 return benifitchangedeleteid;
		 }
		 public void setBenifitchangedeleteid(String benifitchangedeleteid) {
			 this.benifitchangedeleteid=benifitchangedeleteid;
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


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Employee",column,datatype);
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
					EventListener.registerPostQueryParent("Employee",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Employee",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Employee",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Employee",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Employee",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Employee",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Employee",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Employee",this.getParentobjid());
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
					tu.applyObjectRule("Employee",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Employee",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getContactxml()) ){
					 sql+=query.makeBulkSQL(false,getContactxml(),"Contact2Employee",this.getUsername(),this.getGroupuser());
					 contactdata=query.getTableData();
					 tu.applyObjectRule("Contact",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,contactdata);
					 EventListener.registerPreInsertEvent("Contact",contactdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Contact","Contact2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPayrolexml()) ){
					 sql+=query.makeBulkSQL(false,getPayrolexml(),"Payrole2Employee",this.getUsername(),this.getGroupuser());
					 payroledata=query.getTableData();
					 tu.applyObjectRule("Payrole",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,payroledata);
					 EventListener.registerPreInsertEvent("Payrole",payroledata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Payrole","Payrole2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBenifitxml()) ){
					 sql+=query.makeBulkSQL(false,getBenifitxml(),"Benifit2Employee",this.getUsername(),this.getGroupuser());
					 benifitdata=query.getTableData();
					 tu.applyObjectRule("Benifit",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,benifitdata);
					 EventListener.registerPreInsertEvent("Benifit",benifitdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Benifit","Benifit2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getDependentxml()) ){
					 sql+=query.makeBulkSQL(false,getDependentxml(),"Dependent2Employee",this.getUsername(),this.getGroupuser());
					 dependentdata=query.getTableData();
					 tu.applyObjectRule("Dependent",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,dependentdata);
					 EventListener.registerPreInsertEvent("Dependent",dependentdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Dependent","Dependent2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getDeductionxml()) ){
					 sql+=query.makeBulkSQL(false,getDeductionxml(),"Deduction2Employee",this.getUsername(),this.getGroupuser());
					 deductiondata=query.getTableData();
					 tu.applyObjectRule("Deduction",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,deductiondata);
					 EventListener.registerPreInsertEvent("Deduction",deductiondata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Deduction","Deduction2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAdditionxml()) ){
					 sql+=query.makeBulkSQL(false,getAdditionxml(),"Addition2Employee",this.getUsername(),this.getGroupuser());
					 additiondata=query.getTableData();
					 tu.applyObjectRule("Addition",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,additiondata);
					 EventListener.registerPreInsertEvent("Addition",additiondata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Addition","Addition2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSubstructionxml()) ){
					 sql+=query.makeBulkSQL(false,getSubstructionxml(),"Substruction2Employee",this.getUsername(),this.getGroupuser());
					 substructiondata=query.getTableData();
					 tu.applyObjectRule("Substruction",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,substructiondata);
					 EventListener.registerPreInsertEvent("Substruction",substructiondata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Substruction","Substruction2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getVacationxml()) ){
					 sql+=query.makeBulkSQL(false,getVacationxml(),"Vacation2Employee",this.getUsername(),this.getGroupuser());
					 vacationdata=query.getTableData();
					 tu.applyObjectRule("Vacation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,vacationdata);
					 EventListener.registerPreInsertEvent("Vacation",vacationdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Vacation","Vacation2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEarnedvacationxml()) ){
					 sql+=query.makeBulkSQL(false,getEarnedvacationxml(),"EarnedVacation2Employee",this.getUsername(),this.getGroupuser());
					 earnedvacationdata=query.getTableData();
					 tu.applyObjectRule("Earnedvacation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,earnedvacationdata);
					 EventListener.registerPreInsertEvent("EarnedVacation",earnedvacationdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EarnedVacation","EarnedVacation2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBenifitchangexml()) ){
					 sql+=query.makeBulkSQL(false,getBenifitchangexml(),"BenifitChange2Employee",this.getUsername(),this.getGroupuser());
					 benifitchangedata=query.getTableData();
					 tu.applyObjectRule("Benifitchange",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,benifitchangedata);
					 EventListener.registerPreInsertEvent("BenifitChange",benifitchangedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BenifitChange","BenifitChange2Employee",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTaxxml()) ){
					 sql+=query.makeBulkSQL(false,getTaxxml(),"Tax2Employee",this.getUsername(),this.getGroupuser());
					 taxdata=query.getTableData();
					 tu.applyObjectRule("Tax",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,taxdata);
					 EventListener.registerPreInsertEvent("Tax",taxdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Tax","Tax2Employee",getParentobjid());
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
				 tu.applyObjectRule("Employee",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Employee","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Employee",maindata);
				 tu.applyObjectRule("Contact",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,contactdata);
				 tu.applyMTMRelation("contact","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Contact",contactdata);
				 tu.applyObjectRule("Payrole",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,payroledata);
				 tu.applyMTMRelation("payrole","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Payrole",payroledata);
				 tu.applyObjectRule("Benifit",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,benifitdata);
				 tu.applyMTMRelation("benifit","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Benifit",benifitdata);
				 tu.applyObjectRule("Dependent",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,dependentdata);
				 tu.applyMTMRelation("dependent","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Dependent",dependentdata);
				 tu.applyObjectRule("Deduction",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,deductiondata);
				 tu.applyMTMRelation("deduction","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Deduction",deductiondata);
				 tu.applyObjectRule("Addition",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,additiondata);
				 tu.applyMTMRelation("addition","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Addition",additiondata);
				 tu.applyObjectRule("Substruction",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,substructiondata);
				 tu.applyMTMRelation("substruction","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Substruction",substructiondata);
				 tu.applyObjectRule("Vacation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,vacationdata);
				 tu.applyMTMRelation("vacation","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Vacation",vacationdata);
				 tu.applyObjectRule("Earnedvacation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,earnedvacationdata);
				 tu.applyMTMRelation("earnedvacation","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("EarnedVacation",earnedvacationdata);
				 tu.applyObjectRule("Benifitchange",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,benifitchangedata);
				 tu.applyMTMRelation("benifitchange","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("BenifitChange",benifitchangedata);
				 tu.applyObjectRule("Tax",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,taxdata);
				 tu.applyMTMRelation("tax","Employee",getParentobjid());
				 EventListener.registerPostInsertEvent("Tax",taxdata);
				 return(true);
			}
			 return(false);
		}
	}
