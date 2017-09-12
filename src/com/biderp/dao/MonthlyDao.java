
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

	public class MonthlyDao extends MonthlyImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"monthly,"};
		protected String []childtabs={"monthly,approval"};
		protected String []childtabnames={"Monthly Facts,Approval"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","monthly2budget","name","title","projectcode","startdate","monthcode","yearcode"};
		protected String [] maincolcaption={"Id","Budget","Name","Project Title","Project Code","Start Date","Month Code","Year Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Name_t","Code_t","Date_t","Code_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#text_filter,#select_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","fundrequested","fundapproved","pctprojectest","alotedpercent"};
		protected String [] summarycolcaption={"Name","Fund Requested This Month","Fund Approved This Month","(%) Project Fund Estimated","(%) Project Fund Approved"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t","Money_t","Percent_t","Percent_t"};
		
		protected String [] reportcol={"objid","name","title","projectcode","startdate","monthcode","yearcode","fundrequested","fundapproved","pctprojectest","alotedpercent"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Project Code","Start Date","Month Code","Year Code","Fund Requested This Month","Fund Approved This Month","(%) Project Fund Estimated","(%) Project Fund Approved"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Code_t","Date_t","Code_t","Code_t","Money_t","Money_t","Percent_t","Percent_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","startdate","monthcode","yearcode"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Start Date","Month Code","Year Code"};
		protected String [] searchcoltype={"integer","text","text","select","date","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Date_t","Code_t","Code_t"};

	protected String [] propMonthlylist={};
		protected String [] codeMonthlylist={"projectcode","monthcode","yearcode"};
		protected String [] relationMonthlylist={"budget:monthly2budget:list:"};
		
		protected String [] approvaltype={"view"};
		protected String [] approvalcol={"objid","name","startdate","monthcode","yearcode","quantity","estamount","status"};
		protected String [] approvalcolcaption={"Id","Name","1st Day Of Month","Month Code","Year Code","Quantity Planned","Estimated Amount","Status"};
		protected String [] approvalsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] approvaldatadomain={"Int_t","Name_t","Date_t","Code_t","Code_t","Float_t","Money_t","Status_t"};
		protected String [] approvaldisable={"yes,no,no,no,no,no,no,no"};
		protected String [] approvalcolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,,,#select_filter"};

		public MonthlyDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Monthly");
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
				if(entity.toLowerCase().contains("<monthly>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<approval>")){
					this.setApprovalxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getApprovalxml());
					}
				}
			}
		}

		public Rows getApprovalRows(){
			TemplateTable tab=this.doSelectChild("approval", "approval2monthly",this.getParentobjid(),approvalcol,approvalsqldatatype,this.ApprovalFilter);
			String [] propApprovallist={"status"};
			String [] codeApprovallist={"monthcode","yearcode"};
			String [] relationApprovallist={};
			Rows rows=tu.getXMLRows(tab, "approval",codeApprovallist,propApprovallist,relationApprovallist,approvalcolcaption,approvaldatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(approvalcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(approvaldisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(approvaltype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMonthlySummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.MonthlyFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Monthly");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Monthly", tab, chartcol);
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

		public Rows getMonthlyRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Monthly",codeMonthlylist,propMonthlylist,relationMonthlylist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getMonthlyRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Monthly",codeMonthlylist,propMonthlylist,relationMonthlylist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getMonthlyRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postMonthlyContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getMonthlyByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and monthly2"+this.getFilters();
			}
			String sql= "select * from table_Monthly where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Monthly",codeMonthlylist,propMonthlylist,relationMonthlylist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
