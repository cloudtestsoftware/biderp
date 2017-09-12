
 	 package com.biderp.dao; 

 	 import java.util.Map; 
 	 import java.util.ArrayList; 
	 import java.util.Arrays; 
	 import javax.ws.rs.core.Cookie;
 	 import javax.ws.rs.core.HttpHeaders; 
	 import javax.ws.rs.core.UriInfo; 
	 import cms.service.app.ServiceManager;
	 import cms.service.dhtmlx.*;
	 import cms.service.exceptions.DaoException; 
	 import cms.service.exceptions.AuthenticationException;
	 import cms.service.jdbc.DataType; 
	 import cms.service.template.TemplateTable; 
	 import com.biderp.bean.*; 
 
 	 /** A simple bean that has a single String property 
	 *  called message. 
 	 *  
	 * @author S.K Jana Version 1.0 
 	 * @Copyright : This code belongs to BidERP.com. All right reserved! 
 	 * @since 2005-2017 
 	 */ 

	public class ProjectplanDao extends ProjectplanImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"projectplan,milestone,whattodo"};
		protected String []childtabs={"projectplan,milestone,pplink,whattodo"};
		protected String []childtabnames={"Projectplan Facts,MileStone,Predecessor,WhatToDo"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","projectplan2messagequeue","projectplan2project","name","title","projectcode","maincode","startdate","enddate","status"};
		protected String [] maincolcaption={"Id","MessageQueue","Project","Name","Project Title","Project Code","Main Code","Start Date","End Date","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Name_t","Name_t","Code_t","Code_t","Date_t","Date_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","schedule","achievedtarget","floatcount"};
		protected String [] summarycolcaption={"Name","Schedule(-->indays)","Achieved Tagret(%)","Float Count"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","String20_t","Float_t","Float_t"};
		
		protected String [] reportcol={"objid","name","title","startdate","enddate","schedule","achievedtarget","floatcount","status"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Start Date","End Date","Schedule(-->indays)","Achieved Tagret(%)","Float Count","Status"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Date_t","Date_t","String20_t","Float_t","Float_t","Status_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","maincode","startdate","enddate","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Main Code","Start Date","End Date","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select","date","date","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Date_t","Date_t","Status_t"};

	protected String [] propProjectplanlist={"status"};
		protected String [] codeProjectplanlist={"projectcode","maincode"};
		protected String [] relationProjectplanlist={"messagequeue:projectplan2messagequeue:list:","project:projectplan2project:list:"};
		
		protected String [] milestonetype={"table"};
		protected String [] milestonecol={"objid","name","startdate","enddate","schedule","achievedtarget","status","floatcount","boqqnt","qntfinished","qntplanned"};
		protected String [] milestonecolcaption={"Id","Name","Start Date","End Date","Schedule(-->indays)","Achieved Tagret(%)","Status","Float Count","Boq Quantity","Boq Quantity Finished","Planned Boq Quantity"};
		protected String [] milestonesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] milestonedatadomain={"Int_t","Name_t","Date_t","Date_t","String20_t","Float_t","Status_t","Float_t","Int_t","Int_t","Int_t"};
		protected String [] milestonedisable={"yes,no,no,no,no,no,no,yes,yes,yes,yes"};
		protected String [] milestonecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,,#select_filter,,,,"};
		
		protected String [] pplinktype={"view"};
		protected String [] pplinkcol={"objid","name","startbefore","startactual","floatcount","startdate","enddate"};
		protected String [] pplinkcolcaption={"Id","Name","Starting Before(in days)","Starting Actual(in days)","Float(in days)","Start Date","End Date"};
		protected String [] pplinksqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.DATE,DataType.DATE};
		protected String [] pplinkdatadomain={"Int_t","Name_t","Int_t","Int_t","Int_t","Date_t","Date_t"};
		protected String [] pplinkdisable={"yes,no,no,no,no,no,no"};
		protected String [] pplinkcolsearch={"#text_filter,#text_filter,,,,#text_filter,#text_filter"};
		
		protected String [] whattodotype={"table"};
		protected String [] whattodocol={"objid","whattodo2messagequeue","name","youshould","status","relatedname","whatyoudid","expirydate"};
		protected String [] whattodocolcaption={"Id","MessageQueue","ToDo","What Should Do","Your Status","Related Object","What You Did","Expiry Data"};
		protected String [] whattodosqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] whattododatadomain={"Int_t","Int_t","Name_t","String4000_t","Status_t","Type_t","String4000_t","Date_t"};
		protected String [] whattododisable={"yes,no,no,no,no,no,no,no"};
		protected String [] whattodocolsearch={"#text_filter,#select_filter,#text_filter,,#select_filter,#select_filter,,"};

		public ProjectplanDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Projectplan");
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("generate_log"))){
					ACONST.GENERATE_LOG=true;
			}
			if(!tu.isEmptyValue(uriInfo.getPathParameters().getFirst("id"))){
				this.setParentobjid(uriInfo.getPathParameters().getFirst("id").replace("id-", ""));
			}else if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("searchfilter"))){
				this.setSearchdata(uriInfo.getQueryParameters().getFirst("searchfilter")+""+(char)2);
			}else{
				this.setSearchdata("ObjId"+(char)1+"All"+(char)1+""+(char)2);
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("token"))){
				this.setToken(uriInfo.getQueryParameters().getFirst("token"));
				this.userdata=ServiceManager.verifyUserToken(this.getToken());
			}
			if(this.userdata!=null &&!this.userdata.isEmpty()){
				this.groupuser=userdata.get("groupuser");
				this.username=userdata.get("username");
				this.setSearchdata(this.getSearchdata()+"groupuser"+(char)1+"="+(char)1+getGroupuser());
			}else{
				throw new AuthenticationException("Authentication Failed for user="+username+" Token ="+ this.getToken());
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("pagesize"))){
				this.setPagesize(Integer.parseInt(uriInfo.getQueryParameters().getFirst("pagesize")));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("bqn"))){
				this.setBqn(uriInfo.getQueryParameters().getFirst("bqn"));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("page"))){
				this.setPage(Integer.parseInt(uriInfo.getQueryParameters().getFirst("page")));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("X-Forwarded-For"))){
				this.setClientip(uriInfo.getQueryParameters().getFirst("X-Forwarded-For"));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("filters"))){
				this.setFilters(uriInfo.getQueryParameters().getFirst("filters"));
			}
			if(ACONST.GENERATE_LOG){
				logger.info("getPathParameters="+uriInfo.getPathParameters().values());
				logger.info("getQueryParameters="+uriInfo.getQueryParameters().values());
				logger.info("User Data="+this.userdata.toString());
			}
			this.cookies=header.getCookies();
		}

		public void setPostXml(String xml) throws DaoException{
			if(tu.isEmptyValue(xml)) throw new DaoException("ERROR: Post XML Is null or empty");
			if(!xml.contains("<?xml")) throw new DaoException("ERROR: Please provide xml document header at the begining of each entity in the POST XML body.");
			String [] entitys=xml.split("<?xml");
			for(String entity:entitys){
				String tmp="";
				if(entity.toLowerCase().contains("<projectplan>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<milestone>")){
					this.setMilestonexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMilestonexml());
					}
				}else if(entity.toLowerCase().contains("<pplink>")){
					this.setPplinkxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPplinkxml());
					}
				}else if(entity.toLowerCase().contains("<whattodo>")){
					this.setWhattodoxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getWhattodoxml());
					}
				}
			}
		}

		public Rows getMilestoneRows(){
			TemplateTable tab=this.doSelectChild("milestone", "milestone2projectplan",this.getParentobjid(),milestonecol,milestonesqldatatype,this.MilestoneFilter);
			String [] propMilestonelist={"status"};
			String [] codeMilestonelist={};
			String [] relationMilestonelist={};
			Rows rows=tu.getXMLRows(tab, "milestone",codeMilestonelist,propMilestonelist,relationMilestonelist,milestonecolcaption,milestonedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(milestonecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(milestonedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(milestonetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPplinkRows(){
			TemplateTable tab=this.doSelectChild("pplink", "pplink2projectplan",this.getParentobjid(),pplinkcol,pplinksqldatatype,this.PplinkFilter);
			String [] propPplinklist={};
			String [] codePplinklist={};
			String [] relationPplinklist={};
			Rows rows=tu.getXMLRows(tab, "pplink",codePplinklist,propPplinklist,relationPplinklist,pplinkcolcaption,pplinkdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(pplinkcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(pplinkdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(pplinktype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getWhattodoRows(){
			TemplateTable tab=this.doSelectChild("whattodo", "whattodo2projectplan",this.getParentobjid(),whattodocol,whattodosqldatatype,this.WhattodoFilter);
			String [] propWhattodolist={"status","relatedname"};
			String [] codeWhattodolist={};
			String [] relationWhattodolist={"messagequeue:whattodo2messagequeue:list:"};
			Rows rows=tu.getXMLRows(tab, "whattodo",codeWhattodolist,propWhattodolist,relationWhattodolist,whattodocolcaption,whattododatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(whattodocolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(whattododisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(whattodotype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getProjectplanSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ProjectplanFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Projectplan");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Projectplan", tab, chartcol);
				ArrayList<String> data2= new ArrayList<String>();
				data2.add(datas.get(0));
				Userdata chart= new Userdata(chartcol+".chart",data2);
				userdata.add(chart);
				ArrayList<String> data3= new ArrayList<String>();
				data3.add(datas.get(1));
				Userdata griddata= new Userdata(chartcol+".data",data3);
				userdata.add(griddata);
			}
			Userdata data4= new Userdata("grid.moneycols",moneycols);
			userdata.add(data4);
			rows.setUserdata(userdata);
			return rows;
		}

		public Rows getProjectplanRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Projectplan",codeProjectplanlist,propProjectplanlist,relationProjectplanlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("tabs",Arrays.asList(childtabs));
			Userdata data2= new Userdata("tabnames",Arrays.asList(childtabnames));
			Userdata data3= new Userdata("filters",Arrays.asList(maincolsearch));
			Userdata data4= new Userdata("deletetabs",Arrays.asList(deletetabs));
			Userdata data5= new Userdata("disablecols",Arrays.asList(maincoldisable));
			Userdata data6= new Userdata("tabletype",Arrays.asList(maintype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			userdata.add(data4);
			userdata.add(data5);
			userdata.add(data6);
			rows.setUserdata(userdata);
			return rows;
		}

		public Rows getProjectplanRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Projectplan",codeProjectplanlist,propProjectplanlist,relationProjectplanlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getProjectplanRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postProjectplanContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getProjectplanByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and projectplan2"+this.getFilters();
			}
			String sql= "select * from table_Projectplan where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Projectplan",codeProjectplanlist,propProjectplanlist,relationProjectplanlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("tabs",Arrays.asList(childtabs));
			Userdata data2= new Userdata("tabnames",Arrays.asList(childtabnames));
			Userdata data3= new Userdata("filters",Arrays.asList(maincolsearch));
			Userdata data4= new Userdata("deletetabs",Arrays.asList(deletetabs));
			Userdata data5= new Userdata("disablecols",Arrays.asList(maincoldisable));
			Userdata data6= new Userdata("tabletype",Arrays.asList(maintype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			userdata.add(data4);
			userdata.add(data5);
			userdata.add(data6);
			rows.setUserdata(userdata);
			return rows;
		}
	}
