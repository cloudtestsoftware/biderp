
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

	 public class BoqImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BoqImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String partsxml,partsdeleteid;
		 protected String taskresourcexml,taskresourcedeleteid;
		 protected String changexml,changedeleteid;
		 protected String requirementxml,requirementdeleteid;

		 protected TemplateTable partsdata=new TemplateTable();

		 protected TemplateTable taskresourcedata=new TemplateTable();

		 protected TemplateTable changedata=new TemplateTable();

		 protected TemplateTable requirementdata=new TemplateTable();

		 public String BoqFilter="Boqrate.qntchange QntChange,Boqrate.netqnt QntTotal,Boqrate.actBoqrate ActualRate,Boqrate.contracttoactrate ContractToActual,Boqrate.ContractToEstRate ContractToEst@Boqrate,Boq@Boq.objid=Boqrate.Boqrate2Boq(+) order by Boq.name";
		 public String PartsFilter="milestone.title Title,partprice.domaincode DomainCode,partlist.partcode PartCode,partprice.partno PartNo,partlist.description Description,milestone.startdate StartDate,milestone.enddate EndDate,partprice.unitprice UnitPrice,partprice.UmCode UmCode@partlist,partprice,parts,milestone@parts.parts2partprice=partprice.objid and parts.parts2Boq=milestone.milestone2Boq(+) and partprice.partprice2partlist=partlist.objid order by parts.name";
		 public String TaskresourceFilter="resourceuse.QntUsed ActualUnit,ResourceUse.qntbalance QntBalance,partprice.UmCode UmCode,partprice.unitprice Rate@ResourceUse,partprice,taskresource,milestone@taskresource.taskresource2milestone=milestone.objid(+) and taskresource.objid=resourceuse.resourceuse2taskresource and taskresource.taskresource2partprice=partprice.objid order by TaskResource.name";
		 public String ChangeFilter="totalchangecost.estchangecost EstCost,Boq.umcode UmCode@Boq,totalchangecost,change@change.objid=totalchangecost.totalchangecost2change(+) and change.change2Boq=Boq.objid order by change.name";
		 public String RequirementFilter="";

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
		 public String getPartsxml() {
			 return partsxml;
		 }
		 public void setPartsxml(String partsxml) {
			 this.partsxml=partsxml;
		 }
		 public String getPartsdeleteid() {
			 return partsdeleteid;
		 }
		 public void setPartsdeleteid(String partsdeleteid) {
			 this.partsdeleteid=partsdeleteid;
		 }
		 public String getTaskresourcexml() {
			 return taskresourcexml;
		 }
		 public void setTaskresourcexml(String taskresourcexml) {
			 this.taskresourcexml=taskresourcexml;
		 }
		 public String getTaskresourcedeleteid() {
			 return taskresourcedeleteid;
		 }
		 public void setTaskresourcedeleteid(String taskresourcedeleteid) {
			 this.taskresourcedeleteid=taskresourcedeleteid;
		 }
		 public String getChangexml() {
			 return changexml;
		 }
		 public void setChangexml(String changexml) {
			 this.changexml=changexml;
		 }
		 public String getChangedeleteid() {
			 return changedeleteid;
		 }
		 public void setChangedeleteid(String changedeleteid) {
			 this.changedeleteid=changedeleteid;
		 }
		 public String getRequirementxml() {
			 return requirementxml;
		 }
		 public void setRequirementxml(String requirementxml) {
			 this.requirementxml=requirementxml;
		 }
		 public String getRequirementdeleteid() {
			 return requirementdeleteid;
		 }
		 public void setRequirementdeleteid(String requirementdeleteid) {
			 this.requirementdeleteid=requirementdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Boq",column,datatype);
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
					EventListener.registerPostQueryParent("Boq",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Boq",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Boq",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Boq",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Boq",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Boq",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Boq",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Boq",this.getParentobjid());
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
					tu.applyObjectRule("Boq",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Boq",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getPartsxml()) ){
					 sql+=query.makeBulkSQL(false,getPartsxml(),"Parts2Boq",this.getUsername(),this.getGroupuser());
					 partsdata=query.getTableData();
					 tu.applyObjectRule("Parts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partsdata);
					 EventListener.registerPreInsertEvent("Parts",partsdata);
				
				 String [] autofieldPartslist={"projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Parts",autofieldPartslist,"Parts2Boq",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Parts","Parts2Boq",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTaskresourcexml()) ){
					 sql+=query.makeBulkSQL(false,getTaskresourcexml(),"TaskResource2Boq",this.getUsername(),this.getGroupuser());
					 taskresourcedata=query.getTableData();
					 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,taskresourcedata);
					 EventListener.registerPreInsertEvent("TaskResource",taskresourcedata);
				
				 String [] autofieldTaskResourcelist={"title","projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"TaskResource",autofieldTaskResourcelist,"TaskResource2Boq",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TaskResource","TaskResource2Boq",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getChangexml()) ){
					 sql+=query.makeBulkSQL(false,getChangexml(),"Change2Boq",this.getUsername(),this.getGroupuser());
					 changedata=query.getTableData();
					 tu.applyObjectRule("Change",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,changedata);
					 EventListener.registerPreInsertEvent("Change",changedata);
				
				 String [] autofieldChangelist={"title","projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Change",autofieldChangelist,"Change2Boq",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Change","Change2Boq",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getRequirementxml()) ){
					 sql+=query.makeBulkSQL(false,getRequirementxml(),"Requirement2Boq",this.getUsername(),this.getGroupuser());
					 requirementdata=query.getTableData();
					 tu.applyObjectRule("Requirement",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,requirementdata);
					 EventListener.registerPreInsertEvent("Requirement",requirementdata);
				
				 String [] autofieldRequirementlist={"maincode","subcode","title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Requirement",autofieldRequirementlist,"Requirement2Boq",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Requirement","Requirement2Boq",getParentobjid());
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
				 tu.applyObjectRule("Boq",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Boq","Boq",getParentobjid());
				 EventListener.registerPostInsertEvent("Boq",maindata);
				 tu.applyObjectRule("Parts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partsdata);
				 tu.applyMTMRelation("parts","Boq",getParentobjid());
				 EventListener.registerPostInsertEvent("Parts",partsdata);
				 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,taskresourcedata);
				 tu.applyMTMRelation("taskresource","Boq",getParentobjid());
				 EventListener.registerPostInsertEvent("TaskResource",taskresourcedata);
				 tu.applyObjectRule("Change",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,changedata);
				 tu.applyMTMRelation("change","Boq",getParentobjid());
				 EventListener.registerPostInsertEvent("Change",changedata);
				 tu.applyConsoleObject("change",changedata,this.getUsername(),groupuser,false);
				 tu.applyObjectRule("Requirement",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,requirementdata);
				 tu.applyMTMRelation("requirement","Boq",getParentobjid());
				 EventListener.registerPostInsertEvent("Requirement",requirementdata);
				 return(true);
			}
			 return(false);
		}
	}
