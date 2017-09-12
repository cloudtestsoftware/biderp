
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

	 public class MilestoneImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(MilestoneImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String taskresourcexml,taskresourcedeleteid;
		 protected String boqplanxml,boqplandeleteid;
		 protected String mslinkxml,mslinkdeleteid;
		 protected String whattodoxml,whattododeleteid;

		 protected TemplateTable taskresourcedata=new TemplateTable();

		 protected TemplateTable boqplandata=new TemplateTable();

		 protected TemplateTable mslinkdata=new TemplateTable();

		 protected TemplateTable whattododata=new TemplateTable();

		 public String MilestoneFilter="msfloat.schedule Schedule,msprogress.achievedtarget AchievedTarget,msfloat.floatcount FloatCount,msprogress.netBoqqnt BoqQnt,msprogress.qntfinished QntFinished,msprogress.QntPlanned QntPlanned@msprogress,milestone,msfloat@msfloat.objid=milestone.objid and milestone.objid=msprogress.msprogress2milestone(+) order by milestone.name";
		 public String TaskresourceFilter="resourceuse.QntUsed ActualUnit,ResourceUse.qntbalance QntBalance,partprice.UmCode UmCode,partprice.unitprice Rate@ResourceUse,partprice,taskresource,milestone@taskresource.taskresource2milestone=milestone.objid(+) and taskresource.objid=resourceuse.resourceuse2taskresource and taskresource.taskresource2partprice=partprice.objid order by TaskResource.name";
		 public String BoqplanFilter="Boqqnt.netqnt BoqQnt,inspectioncount.qntinspect QntInspect@Boqqnt,Boqplan,inspectioncount@boqplan.objid=inspectioncount.inspectioncount2boqplan and Boqplan.Boqplan2Boq=Boqqnt.Boqqnt2Boq(+) order by Boqplan.name";
		 public String MslinkFilter="";
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
		 public String getBoqplanxml() {
			 return boqplanxml;
		 }
		 public void setBoqplanxml(String boqplanxml) {
			 this.boqplanxml=boqplanxml;
		 }
		 public String getBoqplandeleteid() {
			 return boqplandeleteid;
		 }
		 public void setBoqplandeleteid(String boqplandeleteid) {
			 this.boqplandeleteid=boqplandeleteid;
		 }
		 public String getMslinkxml() {
			 return mslinkxml;
		 }
		 public void setMslinkxml(String mslinkxml) {
			 this.mslinkxml=mslinkxml;
		 }
		 public String getMslinkdeleteid() {
			 return mslinkdeleteid;
		 }
		 public void setMslinkdeleteid(String mslinkdeleteid) {
			 this.mslinkdeleteid=mslinkdeleteid;
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
			 EventListener.registerPreQueryParent("Milestone",column,datatype);
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
					EventListener.registerPostQueryParent("Milestone",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Milestone",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Milestone",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Milestone",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Milestone",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Milestone",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Milestone",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Milestone",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"MileStone2ProjectPlan",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Milestone",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Milestone",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getTaskresourcexml()) ){
					 sql+=query.makeBulkSQL(false,getTaskresourcexml(),"TaskResource2Milestone",this.getUsername(),this.getGroupuser());
					 taskresourcedata=query.getTableData();
					 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,taskresourcedata);
					 EventListener.registerPreInsertEvent("TaskResource",taskresourcedata);
				
				 String [] autofieldTaskResourcelist={"title","projectcode","maincode","subcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"TaskResource",autofieldTaskResourcelist,"TaskResource2Milestone",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TaskResource","TaskResource2Milestone",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getBoqplanxml()) ){
					 sql+=query.makeBulkSQL(false,getBoqplanxml(),"BoqPlan2MileStone",this.getUsername(),this.getGroupuser());
					 boqplandata=query.getTableData();
					 tu.applyObjectRule("Boqplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,boqplandata);
					 EventListener.registerPreInsertEvent("BoqPlan",boqplandata);
				
				 String [] autofieldBoqPlanlist={"title","projectcode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"BoqPlan",autofieldBoqPlanlist,"BoqPlan2MileStone",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BoqPlan","BoqPlan2MileStone",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getMslinkxml()) ){
					 sql+=query.makeBulkSQL(false,getMslinkxml(),"MSLink2MileStone",this.getUsername(),this.getGroupuser());
					 mslinkdata=query.getTableData();
					 tu.applyObjectRule("Mslink",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,mslinkdata);
					 EventListener.registerPreInsertEvent("MSLink",mslinkdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"MSLink","MSLink2MileStone",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getWhattodoxml()) ){
					 sql+=query.makeBulkSQL(false,getWhattodoxml(),"WhatToDo2MileStone",this.getUsername(),this.getGroupuser());
					 whattododata=query.getTableData();
					 tu.applyObjectRule("Whattodo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,whattododata);
					 EventListener.registerPreInsertEvent("WhatToDo",whattododata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"WhatToDo","WhatToDo2MileStone",getParentobjid());
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
				 tu.applyConsoleObject("Milestone",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Milestone",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Milestone","Milestone",getParentobjid());
				 EventListener.registerPostInsertEvent("Milestone",maindata);
				 tu.applyObjectRule("Taskresource",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,taskresourcedata);
				 tu.applyMTMRelation("taskresource","Milestone",getParentobjid());
				 EventListener.registerPostInsertEvent("TaskResource",taskresourcedata);
				 tu.applyObjectRule("Boqplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,boqplandata);
				 tu.applyMTMRelation("boqplan","Milestone",getParentobjid());
				 EventListener.registerPostInsertEvent("BoqPlan",boqplandata);
				 tu.applyObjectRule("Mslink",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,mslinkdata);
				 tu.applyMTMRelation("mslink","Milestone",getParentobjid());
				 EventListener.registerPostInsertEvent("MSLink",mslinkdata);
				 tu.applyObjectRule("Whattodo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,whattododata);
				 tu.applyMTMRelation("whattodo","Milestone",getParentobjid());
				 EventListener.registerPostInsertEvent("WhatToDo",whattododata);
				 tu.applyConsoleObject("whattodo",whattododata,this.getUsername(),groupuser,false);
				 return(true);
			}
			 return(false);
		}
	}
