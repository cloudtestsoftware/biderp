
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

	public class TaskresourceDao extends TaskresourceImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"taskresource,timesheet"};
		protected String []childtabs={"taskresource,timesheet"};
		protected String []childtabnames={"Taskresource Facts,TimeSheet"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","taskresource2partprice","taskresource2change","taskresource2resourceorder","taskresource2joblist","taskresource2milestone","taskresource2boq","name","title","projectcode","maincode","subcode","taskcode","startdate","enddate","resourcecode","estunit","qntrequest","actualrate","origincode","pocode","deptcode","progresscode"};
		protected String [] maincolcaption={"Id","Resources","Change","ResourceOrder","JobList","MileStone","Boq","Detail","Project Title","Project Code","Main Code","Sub Code","Task Code","Start Date","End Date","Resource Code","Est. Unit","Quantity Requested","Actual Rate","Origin","Executed by","Department","Progress Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","String300_t","Name_t","Code_t","Code_t","Code_t","Code_t","Date_t","Date_t","Code_t","Double_t","Double_t","Money_t","Code_t","Code_t","Code_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,,,,#select_filter,#select_filter,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,yes,no,no,no,no,no,no,no,no,no,no,no,no,no,yes"};
		
		protected String [] summarycol={"name","actualunit","qntbalance","umcode","rate"};
		protected String [] summarycolcaption={"Name","Actual Resource","Requested-Used","Um Code","Suggested Rate"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Double_t","Int_t","Code_t","Money_t"};
		
		protected String [] reportcol={"objid","name","projectcode","taskcode","startdate","enddate","estunit","qntrequest","qntbalance","umcode","rate","actualrate","pocode","deptcode","progresscode"};
		protected String [] reportcolcaption={"Id","Detail","Project Code","Task Code","Start Date","End Date","Est. Unit","Quantity Requested","Requested-Used","Um Code","Suggested Rate","Actual Rate","Executed by","Department","Progress Code"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","String300_t","Code_t","Code_t","Date_t","Date_t","Double_t","Double_t","Int_t","Code_t","Money_t","Money_t","Code_t","Code_t","Code_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","maincode","subcode","taskcode","startdate","enddate","resourcecode","origincode","pocode","deptcode"};
		protected String [] searchcolcaption={"Id","Detail","Project Title","Project Code","Main Code","Sub Code","Task Code","Start Date","End Date","Resource Code","Origin","Executed by","Department"};
		protected String [] searchcoltype={"integer","text","text","select","select","select","select","date","date","select","select","select","select"};
		protected String [] searchdatadomain={"Id_t","String300_t","Name_t","Code_t","Code_t","Code_t","Code_t","Date_t","Date_t","Code_t","Code_t","Code_t","Code_t"};

	protected String [] propTaskresourcelist={};
		protected String [] codeTaskresourcelist={"projectcode","maincode","subcode","taskcode","resourcecode","origincode","pocode","deptcode","progresscode"};
		protected String [] relationTaskresourcelist={"resources:taskresource2partprice:list:","change:taskresource2change:list:","resourceorder:taskresource2resourceorder:list:","joblist:taskresource2joblist:list:","milestone:taskresource2milestone:list:","boq:taskresource2boq:list:"};
		
		protected String [] timesheettype={"table"};
		protected String [] timesheetcol={"objid","name","servicedate","umcode","regulartime","overtime","status"};
		protected String [] timesheetcolcaption={"Id","Name","Service Date","Unit Code","Regular","Over Time","Status"};
		protected String [] timesheetsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] timesheetdatadomain={"Int_t","Name_t","Date_t","Code_t","Float_t","Float_t","Type_t"};
		protected String [] timesheetdisable={"yes,no,no,no,no,no,no"};
		protected String [] timesheetcolsearch={"#text_filter,#text_filter,,#select_filter,#numeric_filter,#numeric_filter,#select_filter"};

		public TaskresourceDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Taskresource");
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
				if(entity.toLowerCase().contains("<taskresource>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<timesheet>")){
					this.setTimesheetxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTimesheetxml());
					}
				}
			}
		}

		public Rows getTimesheetRows(){
			TemplateTable tab=this.doSelectChild("timesheet", "timesheet2taskresource",this.getParentobjid(),timesheetcol,timesheetsqldatatype,this.TimesheetFilter);
			String [] propTimesheetlist={"status"};
			String [] codeTimesheetlist={"umcode"};
			String [] relationTimesheetlist={};
			Rows rows=tu.getXMLRows(tab, "timesheet",codeTimesheetlist,propTimesheetlist,relationTimesheetlist,timesheetcolcaption,timesheetdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(timesheetcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(timesheetdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(timesheettype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTaskresourceSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.TaskresourceFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Taskresource");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Taskresource", tab, chartcol);
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

		public Rows getTaskresourceRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Taskresource",codeTaskresourcelist,propTaskresourcelist,relationTaskresourcelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getTaskresourceRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Taskresource",codeTaskresourcelist,propTaskresourcelist,relationTaskresourcelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getTaskresourceRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postTaskresourceContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getTaskresourceByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and taskresource2"+this.getFilters();
			}
			String sql= "select * from table_Taskresource where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Taskresource",codeTaskresourcelist,propTaskresourcelist,relationTaskresourcelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
