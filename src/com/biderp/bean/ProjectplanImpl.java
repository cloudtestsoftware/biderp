
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

	 public class ProjectplanImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ProjectplanImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String milestonexml,milestonedeleteid;
		 protected String pplinkxml,pplinkdeleteid;
		 protected String whattodoxml,whattododeleteid;

		 protected TemplateTable milestonedata=new TemplateTable();

		 protected TemplateTable pplinkdata=new TemplateTable();

		 protected TemplateTable whattododata=new TemplateTable();

		 public String ProjectplanFilter="ppfloat.schedule Schedule,ppprogress.achievedtarget AchievedTarget,ppfloat.floatcount FloatCount@ppprogress,projectplan,ppfloat@ppfloat.objid=projectplan.objid and projectplan.objid=ppprogress2projectplan(+) order by projectplan.name";
		 public String MilestoneFilter="msfloat.schedule Schedule,msprogress.achievedtarget AchievedTarget,msfloat.floatcount FloatCount,msprogress.netBoqqnt BoqQnt,msprogress.qntfinished QntFinished,msprogress.QntPlanned QntPlanned@msprogress,milestone,msfloat@msfloat.objid=milestone.objid and milestone.objid=msprogress.msprogress2milestone(+) order by milestone.name";
		 public String PplinkFilter="";
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
		 public String getMilestonexml() {
			 return milestonexml;
		 }
		 public void setMilestonexml(String milestonexml) {
			 this.milestonexml=milestonexml;
		 }
		 public String getMilestonedeleteid() {
			 return milestonedeleteid;
		 }
		 public void setMilestonedeleteid(String milestonedeleteid) {
			 this.milestonedeleteid=milestonedeleteid;
		 }
		 public String getPplinkxml() {
			 return pplinkxml;
		 }
		 public void setPplinkxml(String pplinkxml) {
			 this.pplinkxml=pplinkxml;
		 }
		 public String getPplinkdeleteid() {
			 return pplinkdeleteid;
		 }
		 public void setPplinkdeleteid(String pplinkdeleteid) {
			 this.pplinkdeleteid=pplinkdeleteid;
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
			 EventListener.registerPreQueryParent("Projectplan",column,datatype);
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
					EventListener.registerPostQueryParent("Projectplan",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Projectplan",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Projectplan",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Projectplan",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Projectplan",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Projectplan",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Projectplan",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Projectplan",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"ProjectPlan2Project",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Projectplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Projectplan",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getMilestonexml()) ){
					 sql+=query.makeBulkSQL(false,getMilestonexml(),"MileStone2ProjectPlan",this.getUsername(),this.getGroupuser());
					 milestonedata=query.getTableData();
					 tu.applyObjectRule("Milestone",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,milestonedata);
					 EventListener.registerPreInsertEvent("MileStone",milestonedata);
				
				 String [] autofieldMileStonelist={"title","projectcode","maincode"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"MileStone",autofieldMileStonelist,"MileStone2ProjectPlan",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"MileStone","MileStone2ProjectPlan",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPplinkxml()) ){
					 sql+=query.makeBulkSQL(false,getPplinkxml(),"PPLink2ProjectPlan",this.getUsername(),this.getGroupuser());
					 pplinkdata=query.getTableData();
					 tu.applyObjectRule("Pplink",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,pplinkdata);
					 EventListener.registerPreInsertEvent("PPLink",pplinkdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PPLink","PPLink2ProjectPlan",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getWhattodoxml()) ){
					 sql+=query.makeBulkSQL(false,getWhattodoxml(),"WhatToDo2ProjectPlan",this.getUsername(),this.getGroupuser());
					 whattododata=query.getTableData();
					 tu.applyObjectRule("Whattodo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,whattododata);
					 EventListener.registerPreInsertEvent("WhatToDo",whattododata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"WhatToDo","WhatToDo2ProjectPlan",getParentobjid());
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
				 tu.applyConsoleObject("Projectplan",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Projectplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Projectplan","Projectplan",getParentobjid());
				 EventListener.registerPostInsertEvent("Projectplan",maindata);
				 tu.applyObjectRule("Milestone",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,milestonedata);
				 tu.applyMTMRelation("milestone","Projectplan",getParentobjid());
				 EventListener.registerPostInsertEvent("MileStone",milestonedata);
				 tu.applyConsoleObject("milestone",milestonedata,this.getUsername(),groupuser,false);
				 tu.applyObjectRule("Pplink",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,pplinkdata);
				 tu.applyMTMRelation("pplink","Projectplan",getParentobjid());
				 EventListener.registerPostInsertEvent("PPLink",pplinkdata);
				 tu.applyObjectRule("Whattodo",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,whattododata);
				 tu.applyMTMRelation("whattodo","Projectplan",getParentobjid());
				 EventListener.registerPostInsertEvent("WhatToDo",whattododata);
				 tu.applyConsoleObject("whattodo",whattododata,this.getUsername(),groupuser,false);
				 return(true);
			}
			 return(false);
		}
	}
