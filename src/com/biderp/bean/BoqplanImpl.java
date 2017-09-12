
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

	 public class BoqplanImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BoqplanImpl.class); 
 		 protected static ApplicationConstants ACONST =new ApplicationConstants(); 
 		 protected static TemplateUtility tu=new TemplateUtility();
		 protected TemplateTable maindata=new TemplateTable();
		 protected String searchdata,object,parentobjid,filters, bqn;
		 protected String username, groupuser,token,clientip; 
		 protected int pagesize=30; 
 		 protected int page;
 		 protected String mainxml;
 
		 protected String materialxml,materialdeleteid;
		 protected String resourceauditxml,resourceauditdeleteid;
		 protected String inspectionxml,inspectiondeleteid;

		 protected TemplateTable materialdata=new TemplateTable();

		 protected TemplateTable resourceauditdata=new TemplateTable();

		 protected TemplateTable inspectiondata=new TemplateTable();

		 public String BoqplanFilter="Boqqnt.netqnt BoqQnt,inspectioncount.qntinspect QntInspect@Boqqnt,Boqplan,inspectioncount@boqplan.objid=inspectioncount.inspectioncount2boqplan and Boqplan.Boqplan2Boq=Boqqnt.Boqqnt2Boq(+) order by Boqplan.name";
		 public String MaterialFilter="";
		 public String ResourceauditFilter="";
		 public String InspectionFilter="";

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
		 public String getMaterialxml() {
			 return materialxml;
		 }
		 public void setMaterialxml(String materialxml) {
			 this.materialxml=materialxml;
		 }
		 public String getMaterialdeleteid() {
			 return materialdeleteid;
		 }
		 public void setMaterialdeleteid(String materialdeleteid) {
			 this.materialdeleteid=materialdeleteid;
		 }
		 public String getResourceauditxml() {
			 return resourceauditxml;
		 }
		 public void setResourceauditxml(String resourceauditxml) {
			 this.resourceauditxml=resourceauditxml;
		 }
		 public String getResourceauditdeleteid() {
			 return resourceauditdeleteid;
		 }
		 public void setResourceauditdeleteid(String resourceauditdeleteid) {
			 this.resourceauditdeleteid=resourceauditdeleteid;
		 }
		 public String getInspectionxml() {
			 return inspectionxml;
		 }
		 public void setInspectionxml(String inspectionxml) {
			 this.inspectionxml=inspectionxml;
		 }
		 public String getInspectiondeleteid() {
			 return inspectiondeleteid;
		 }
		 public void setInspectiondeleteid(String inspectiondeleteid) {
			 this.inspectiondeleteid=inspectiondeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Boqplan",column,datatype);
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
					EventListener.registerPostQueryParent("Boqplan",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Boqplan",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Boqplan",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Boqplan",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Boqplan",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Boqplan",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Boqplan",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Boqplan",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"BoqPlan2MileStone",this.getUsername(),this.getGroupuser());
					maindata=query.getTableData();
					tu.applyObjectRule("Boqplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Boqplan",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getMaterialxml()) ){
					 sql+=query.makeBulkSQL(false,getMaterialxml(),"Material2BoqPlan",this.getUsername(),this.getGroupuser());
					 materialdata=query.getTableData();
					 tu.applyObjectRule("Material",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,materialdata);
					 EventListener.registerPreInsertEvent("Material",materialdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Material","Material2BoqPlan",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getResourceauditxml()) ){
					 sql+=query.makeBulkSQL(false,getResourceauditxml(),"ResourceAudit2BoqPlan",this.getUsername(),this.getGroupuser());
					 resourceauditdata=query.getTableData();
					 tu.applyObjectRule("Resourceaudit",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,resourceauditdata);
					 EventListener.registerPreInsertEvent("ResourceAudit",resourceauditdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ResourceAudit","ResourceAudit2BoqPlan",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getInspectionxml()) ){
					 sql+=query.makeBulkSQL(false,getInspectionxml(),"Inspection2BoqPlan",this.getUsername(),this.getGroupuser());
					 inspectiondata=query.getTableData();
					 tu.applyObjectRule("Inspection",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,inspectiondata);
					 EventListener.registerPreInsertEvent("Inspection",inspectiondata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Inspection","Inspection2BoqPlan",getParentobjid());
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
				 tu.applyObjectRule("Boqplan",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Boqplan","Boqplan",getParentobjid());
				 EventListener.registerPostInsertEvent("Boqplan",maindata);
				 tu.applyObjectRule("Material",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,materialdata);
				 tu.applyMTMRelation("material","Boqplan",getParentobjid());
				 EventListener.registerPostInsertEvent("Material",materialdata);
				 tu.applyObjectRule("Resourceaudit",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,resourceauditdata);
				 tu.applyMTMRelation("resourceaudit","Boqplan",getParentobjid());
				 EventListener.registerPostInsertEvent("ResourceAudit",resourceauditdata);
				 tu.applyObjectRule("Inspection",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,inspectiondata);
				 tu.applyMTMRelation("inspection","Boqplan",getParentobjid());
				 EventListener.registerPostInsertEvent("Inspection",inspectiondata);
				 return(true);
			}
			 return(false);
		}
	}
