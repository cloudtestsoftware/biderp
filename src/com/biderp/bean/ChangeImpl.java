
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

	 public class ChangeImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ChangeImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String lognotexml,lognotedeleteid;
		 protected String partsxml,partsdeleteid;
		 protected String taskresourcexml,taskresourcedeleteid;
		 protected String whattodoxml,whattododeleteid;

		 protected TemplateTable lognotedata=new TemplateTable();

		 protected TemplateTable partsdata=new TemplateTable();

		 protected TemplateTable taskresourcedata=new TemplateTable();

		 protected TemplateTable whattododata=new TemplateTable();

		 public String ChangeFilter="totalchangecost.estchangecost EstCost,Boq.umcode UmCode@Boq,totalchangecost,change@change.objid=totalchangecost.totalchangecost2change(+) and change.change2Boq=Boq.objid order by change.name";
		 public String LognoteFilter="";
		 public String PartsFilter="milestone.title Title,partprice.domaincode DomainCode,partlist.partcode PartCode,partprice.partno PartNo,partlist.description Description,milestone.startdate StartDate,milestone.enddate EndDate,partprice.unitprice UnitPrice,partprice.UmCode UmCode@partlist,partprice,parts,milestone@parts.parts2partprice=partprice.objid and parts.parts2Boq=milestone.milestone2Boq(+) and partprice.partprice2partlist=partlist.objid order by parts.name";
		 public String TaskresourceFilter="resourceuse.QntUsed ActualUnit,ResourceUse.qntbalance QntBalance,partprice.UmCode UmCode,partprice.unitprice Rate@ResourceUse,partprice,taskresource,milestone@taskresource.taskresource2milestone=milestone.objid(+) and taskresource.objid=resourceuse.resourceuse2taskresource and taskresource.taskresource2partprice=partprice.objid order by TaskResource.name";
		 public String WhattodoFilter="";

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
		 public String getLognotexml() {
			 return lognotexml;
		 }
		 public void setLognotexml(String lognotexml) {
			 this.lognotexml=lognotexml;
		 }
		 public String getLognotedeleteid() {
			 return lognotedeleteid;
		 }
		 public void setLognotedeleteid(String lognotedeleteid) {
			 this.lognotedeleteid=lognotedeleteid;
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
		 public String getWhattodoxml() {
			 return whattodoxml;
		 }
		 public void setWhattodoxml(String whattodoxml) {
			 this.whattodoxml=whattodoxml;
		 }
		 public String getWhattododeleteid() {
			 return whattododeleteid;
		 }
		 public void setWhattododeleteid(String whattododeleteid) {
			 this.whattododeleteid=whattododeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Change",column,datatype);
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
					EventListener.registerPostQueryParent("Change",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Change",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Change",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Change",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Change",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Change",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Change",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Change",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Change2Boq",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Change",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Change",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getLognotexml()) ){
					 sql+=query.makeBulkSQL(false,getLognotexml(),"LogNote2Change",this.getUsername(),this.getGroupuser());
					 lognotedata=query.getTableData();
					 tu.applyObjectRule("Lognote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,lognotedata);
					 EventListener.registerPreInsertEvent("LogNote",lognotedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"LogNote","LogNote2Change",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPartsxml()) ){
					 sql+=query.makeBulkSQL(false,getPartsxml(),"Parts2Change",this.getUsername(),this.getGroupuser());
					 partsdata=query.getTableData();
					 tu.applyObjectRule("Parts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partsdata);
					 EventListener.registerPreInsertEvent("Parts",partsdata);
				
				 String [] autofieldPartslist={"projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"Parts",autofieldPartslist,"Parts2Change",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Parts","Parts2Change",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTaskresourcexml()) ){
					 sql+=query.makeBulkSQL(false,getTaskresourcexml(),"TaskResource2Change",this.getUsername(),this.getGroupuser());
					 taskresourcedata=query.getTableData();
					 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,taskresourcedata);
					 EventListener.registerPreInsertEvent("TaskResource",taskresourcedata);
				
				 String [] autofieldTaskResourcelist={"title","projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"TaskResource",autofieldTaskResourcelist,"TaskResource2Change",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TaskResource","TaskResource2Change",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getWhattodoxml()) ){
					 sql+=query.makeBulkSQL(false,getWhattodoxml(),"WhatToDo2Change",this.getUsername(),this.getGroupuser());
					 whattododata=query.getTableData();
					 tu.applyObjectRule("Whattodo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,whattododata);
					 EventListener.registerPreInsertEvent("WhatToDo",whattododata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"WhatToDo","WhatToDo2Change",getParentobjid());
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
				 tu.applyConsoleObject("Change",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Change",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Change","Change",getParentobjid());
				 EventListener.registerPostInsertEvent("Change",maindata);
				 tu.applyObjectRule("Lognote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,lognotedata);
				 tu.applyMTMRelation("lognote","Change",getParentobjid());
				 EventListener.registerPostInsertEvent("LogNote",lognotedata);
				 tu.applyObjectRule("Parts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partsdata);
				 tu.applyMTMRelation("parts","Change",getParentobjid());
				 EventListener.registerPostInsertEvent("Parts",partsdata);
				 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,taskresourcedata);
				 tu.applyMTMRelation("taskresource","Change",getParentobjid());
				 EventListener.registerPostInsertEvent("TaskResource",taskresourcedata);
				 tu.applyObjectRule("Whattodo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,whattododata);
				 tu.applyMTMRelation("whattodo","Change",getParentobjid());
				 EventListener.registerPostInsertEvent("WhatToDo",whattododata);
				 tu.applyConsoleObject("whattodo",whattododata,this.getUsername(),groupuser,false);
				 return(true);
			}
			 return(false);
		}
	}
