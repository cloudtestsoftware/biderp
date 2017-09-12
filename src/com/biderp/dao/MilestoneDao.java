
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

	public class MilestoneDao extends MilestoneImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"milestone,taskresource,boqplan,whattodo"};
		protected String []childtabs={"milestone,taskresource,boqplan,mslink,whattodo"};
		protected String []childtabnames={"Milestone Facts,Resource,BoqPlan,Predecessor,WhatToDo"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","milestone2projectplan","milestone2boq","milestone2messagequeue","name","title","projectcode","maincode","subcode","startdate","enddate","status","note"};
		protected String [] maincolcaption={"Id","ProjectPlan","Boq","MessageQueue","Name","Project Title","Project Code","Main Code","Sub Code","Start Date","End Date","Status","Note"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Name_t","Name_t","Code_t","Code_t","Code_t","Date_t","Date_t","Status_t","String400_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","schedule","achievedtarget","floatcount","boqqnt","qntfinished","qntplanned"};
		protected String [] summarycolcaption={"Name","Schedule(-->indays)","Achieved Tagret(%)","Float Count","Boq Quantity","Boq Quantity Finished","Planned Boq Quantity"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] summarydatadomain={"Name_t","String20_t","Float_t","Float_t","Int_t","Int_t","Int_t"};
		
		protected String [] reportcol={"objid","name","title","startdate","enddate","schedule","achievedtarget","floatcount","boqqnt","qntfinished","qntplanned"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Start Date","End Date","Schedule(-->indays)","Achieved Tagret(%)","Float Count","Boq Quantity","Boq Quantity Finished","Planned Boq Quantity"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Date_t","Date_t","String20_t","Float_t","Float_t","Int_t","Int_t","Int_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","maincode","subcode","startdate","enddate","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Main Code","Sub Code","Start Date","End Date","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select","select","date","date","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Code_t","Date_t","Date_t","Status_t"};

	protected String [] propMilestonelist={"status"};
		protected String [] codeMilestonelist={"projectcode","maincode","subcode"};
		protected String [] relationMilestonelist={"projectplan:milestone2projectplan:list:","boq:milestone2boq:list:","messagequeue:milestone2messagequeue:list:"};
		
		protected String [] taskresourcetype={"table"};
		protected String [] taskresourcecol={"objid","taskresource2partprice","name","taskcode","startdate","enddate","resourcecode","estunit","qntrequest","actualunit","qntbalance","umcode","rate","actualrate","origincode","pocode","deptcode"};
		protected String [] taskresourcecolcaption={"Id","Resources","Detail","Task Code","Start Date","End Date","Resource Code","Est. Unit","Quantity Requested","Actual Resource","Requested-Used","Um Code","Suggested Rate","Actual Rate","Origin","Executed by","Department"};
		protected String [] taskresourcesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] taskresourcedatadomain={"Int_t","Int_t","String300_t","Code_t","Date_t","Date_t","Code_t","Double_t","Double_t","Double_t","Int_t","Code_t","Money_t","Money_t","Code_t","Code_t","Code_t"};
		protected String [] taskresourcedisable={"yes,no,no,no,no,no,no,no,no,yes,yes,no,yes,no,no,no,no"};
		protected String [] taskresourcecolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,,,,,,,,#select_filter,#select_filter,#select_filter"};
		
		protected String [] boqplantype={"table"};
		protected String [] boqplancol={"objid","name","monthcode","yearcode","boqqnt","quantity","qntfinished","qntinspect","status","note"};
		protected String [] boqplancolcaption={"Id","Name","Month Code","Year Code","Total Boq Quantity","Quantity Planned","Quantity Finished","Quantity Scheduled","Status","Note"};
		protected String [] boqplansqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] boqplandatadomain={"Int_t","Name_t","Code_t","Code_t","Float_t","Float_t","Float_t","Float_t","Status_t","String4000_t"};
		protected String [] boqplandisable={"yes,no,no,no,yes,no,no,yes,no,no"};
		protected String [] boqplancolsearch={"#text_filter,#text_filter,#select_filter,#select_filter,,,,,#select_filter,"};
		
		protected String [] mslinktype={"view"};
		protected String [] mslinkcol={"objid","name","startbefore","startactual","floatcount","startdate","enddate"};
		protected String [] mslinkcolcaption={"Id","Name","Starting Before(in days)","Starting Actual(in days)","Float(in days)","Start Date","End Date"};
		protected String [] mslinksqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.DATE,DataType.DATE};
		protected String [] mslinkdatadomain={"Int_t","Name_t","Int_t","Int_t","Int_t","Date_t","Date_t"};
		protected String [] mslinkdisable={"yes,no,no,no,no,no,no"};
		protected String [] mslinkcolsearch={"#text_filter,#text_filter,,,,#text_filter,#text_filter"};
		
		protected String [] whattodotype={"table"};
		protected String [] whattodocol={"objid","whattodo2messagequeue","name","youshould","status","relatedname","whatyoudid","expirydate"};
		protected String [] whattodocolcaption={"Id","MessageQueue","ToDo","What Should Do","Your Status","Related Object","What You Did","Expiry Data"};
		protected String [] whattodosqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] whattododatadomain={"Int_t","Int_t","Name_t","String4000_t","Status_t","Type_t","String4000_t","Date_t"};
		protected String [] whattododisable={"yes,no,no,no,no,no,no,no"};
		protected String [] whattodocolsearch={"#text_filter,#select_filter,#text_filter,,#select_filter,#select_filter,,"};

		public MilestoneDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Milestone");
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
				if(entity.toLowerCase().contains("<milestone>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<taskresource>")){
					this.setTaskresourcexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTaskresourcexml());
					}
				}else if(entity.toLowerCase().contains("<boqplan>")){
					this.setBoqplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBoqplanxml());
					}
				}else if(entity.toLowerCase().contains("<mslink>")){
					this.setMslinkxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMslinkxml());
					}
				}else if(entity.toLowerCase().contains("<whattodo>")){
					this.setWhattodoxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getWhattodoxml());
					}
				}
			}
		}

		public Rows getTaskresourceRows(){
			TemplateTable tab=this.doSelectChild("taskresource", "taskresource2milestone",this.getParentobjid(),taskresourcecol,taskresourcesqldatatype,this.TaskresourceFilter);
			String [] propTaskresourcelist={};
			String [] codeTaskresourcelist={"taskcode","resourcecode","umcode","origincode","pocode","deptcode"};
			String [] relationTaskresourcelist={"resources:taskresource2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "taskresource",codeTaskresourcelist,propTaskresourcelist,relationTaskresourcelist,taskresourcecolcaption,taskresourcedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(taskresourcecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(taskresourcedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(taskresourcetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBoqplanRows(){
			TemplateTable tab=this.doSelectChild("boqplan", "boqplan2milestone",this.getParentobjid(),boqplancol,boqplansqldatatype,this.BoqplanFilter);
			String [] propBoqplanlist={"status"};
			String [] codeBoqplanlist={"monthcode","yearcode"};
			String [] relationBoqplanlist={};
			Rows rows=tu.getXMLRows(tab, "boqplan",codeBoqplanlist,propBoqplanlist,relationBoqplanlist,boqplancolcaption,boqplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(boqplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(boqplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(boqplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMslinkRows(){
			TemplateTable tab=this.doSelectChild("mslink", "mslink2milestone",this.getParentobjid(),mslinkcol,mslinksqldatatype,this.MslinkFilter);
			String [] propMslinklist={};
			String [] codeMslinklist={};
			String [] relationMslinklist={};
			Rows rows=tu.getXMLRows(tab, "mslink",codeMslinklist,propMslinklist,relationMslinklist,mslinkcolcaption,mslinkdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(mslinkcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(mslinkdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(mslinktype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getWhattodoRows(){
			TemplateTable tab=this.doSelectChild("whattodo", "whattodo2milestone",this.getParentobjid(),whattodocol,whattodosqldatatype,this.WhattodoFilter);
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



		public Rows getMilestoneSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.MilestoneFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Milestone");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Milestone", tab, chartcol);
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

		public Rows getMilestoneRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Milestone",codeMilestonelist,propMilestonelist,relationMilestonelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getMilestoneRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Milestone",codeMilestonelist,propMilestonelist,relationMilestonelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getMilestoneRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postMilestoneContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getMilestoneByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and milestone2"+this.getFilters();
			}
			String sql= "select * from table_Milestone where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Milestone",codeMilestonelist,propMilestonelist,relationMilestonelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
