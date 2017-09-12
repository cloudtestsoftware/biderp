
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

	public class ProjectDao extends ProjectImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"project,jobmaster,contact,estimation,projectplan,budget,tax,pomaster"};
		protected String []childtabs={"project,jobmaster,contact,estimation,projectplan,budget,tax,pomaster"};
		protected String []childtabnames={"Project Facts,Master Jobs,Contact,Estimation,ProjectPlan,Budget,Tax,Po Master"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","project2phase","project2messagequeue","name","title","projectcode","scope","leadsourcecode","startdate","enddate","status","currencycode","extracost"};
		protected String [] maincolcaption={"Id","Phase","MessageQueue","Name","Project Title","Project Code","Scope","How Do You Know Us","Start Date","End Date","Status","Currency Code","Extra Cost(Labor,Shipping..)"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Name_t","Name_t","Code_t","String1000_t","Code_t","Date_t","Date_t","Status_t","Code_t","Money_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,,,#text_filter,#text_filter,#select_filter,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,yes,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","projectno","totaltax","contractcost","totalcost","actualcost","achievedtarget","budgetused"};
		protected String [] summarycolcaption={"Name","Project No","Tax(%)","Total Job Cost","Job Cost + Tax","Expenditure To Date","Project Tagret(%)","Budget Used (%)"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Id_t","Float_t","Money_t","Money_t","Money_t","Float_t","Float_t"};
		
		protected String [] reportcol={"objid","name","projectno","projectcode","startdate","enddate","contractcost","totalcost","achievedtarget","budgetused"};
		protected String [] reportcolcaption={"Id","Name","Project No","Project Code","Start Date","End Date","Total Job Cost","Job Cost + Tax","Project Tagret(%)","Budget Used (%)"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Id_t","Code_t","Date_t","Date_t","Money_t","Money_t","Float_t","Float_t"};
		
		protected String [] searchcol={"objid","name","title","projectno","projectcode","startdate","enddate","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project No","Project Code","Start Date","End Date","Status"};
		protected String [] searchcoltype={"integer","text","text","integer","select","date","date","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Id_t","Code_t","Date_t","Date_t","Status_t"};

	protected String [] propProjectlist={"status"};
		protected String [] codeProjectlist={"projectcode","leadsourcecode","currencycode"};
		protected String [] relationProjectlist={"phase:project2phase:list:","messagequeue:project2messagequeue:list:"};
		
		protected String [] jobmastertype={"table"};
		protected String [] jobmastercol={"objid","name","maincode","description","startdate","enddate"};
		protected String [] jobmastercolcaption={"Id","Name","Main Job","Description","Start Date","End Date"};
		protected String [] jobmastersqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE};
		protected String [] jobmasterdatadomain={"Int_t","Name_t","Code_t","String4000_t","Date_t","Date_t"};
		protected String [] jobmasterdisable={"yes,no,no,no,no,no"};
		protected String [] jobmastercolsearch={"#text_filter,#text_filter,#select_filter,,#text_filter,#text_filter"};
		
		protected String [] contacttype={"table"};
		protected String [] contactcol={"objid","name","lastname","street","city","state","zipcode","countrycode","phone","phone2","contactcode"};
		protected String [] contactcolcaption={"Id","Name","Other/Last Name","Street","City","State","Zip Code","Country","Home Phone","Office Phone","Type"};
		protected String [] contactsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] contactdatadomain={"Int_t","Name_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Phone_t","Phone_t","Code_t"};
		protected String [] contactdisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		protected String [] contactcolsearch={"#text_filter,#text_filter,#text_filter,,,,,#text_filter,,,"};
		
		protected String [] estimationtype={"table"};
		protected String [] estimationcol={"objid","name","startdate","status","contractamount","estchangecost","actchangecost","estjobcost","actjobcost","netamount"};
		protected String [] estimationcolcaption={"Id","Name","Start Date","Status","Contract Amount","Est. Change Cost","Actual Change Cost","Est. Job Cost","Actual Job Cost","Profit/Loss"};
		protected String [] estimationsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] estimationdatadomain={"Int_t","Name_t","Date_t","Status_t","Money_t","Money_t","Money_t","Money_t","Money_t","Money_t"};
		protected String [] estimationdisable={"yes,no,yes,no,yes,yes,yes,yes,yes,yes"};
		protected String [] estimationcolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,,,,,"};
		
		protected String [] projectplantype={"table"};
		protected String [] projectplancol={"objid","name","startdate","enddate","schedule","achievedtarget","floatcount","status"};
		protected String [] projectplancolcaption={"Id","Name","Start Date","End Date","Schedule(-->indays)","Achieved Tagret(%)","Float Count","Status"};
		protected String [] projectplansqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] projectplandatadomain={"Int_t","Name_t","Date_t","Date_t","String20_t","Float_t","Float_t","Status_t"};
		protected String [] projectplandisable={"yes,no,no,no,no,no,yes,no"};
		protected String [] projectplancolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,,,#select_filter"};
		
		protected String [] budgettype={"table"};
		protected String [] budgetcol={"objid","budget2account","name","totalcontract","totalwithtax","fundused","achievedtarget","budgetused","status"};
		protected String [] budgetcolcaption={"Id","Account","Name","Total Job Cost","Total Job Cost + Tax","Approved To Plan","Project Tagret(%)","Budget Used (%)","Status"};
		protected String [] budgetsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] budgetdatadomain={"Int_t","Int_t","Name_t","Money_t","Money_t","Money_t","Float_t","Float_t","Status_t"};
		protected String [] budgetdisable={"yes,no,no,yes,yes,yes,no,yes,no"};
		protected String [] budgetcolsearch={"#text_filter,#select_filter,#text_filter,,,,,,#select_filter"};
		
		protected String [] taxtype={"table"};
		protected String [] taxcol={"objid","name","taxcode","pct"};
		protected String [] taxcolcaption={"Id","Name","Tax Code","Tax (%)"};
		protected String [] taxsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] taxdatadomain={"Int_t","Name_t","Code_t","Float_t"};
		protected String [] taxdisable={"yes,no,no,no"};
		protected String [] taxcolsearch={"#text_filter,#text_filter,#select_filter,"};
		
		protected String [] pomastertype={"table"};
		protected String [] pomastercol={"objid","name","pocode","itemcode","status","note"};
		protected String [] pomastercolcaption={"Id","Name","Po Code","Item Code","Status","Note"};
		protected String [] pomastersqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] pomasterdatadomain={"Int_t","Name_t","Code_t","Code_t","Status_t","String1000_t"};
		protected String [] pomasterdisable={"yes,no,yes,yes,no,no"};
		protected String [] pomastercolsearch={"#text_filter,#text_filter,#select_filter,#select_filter,#text_filter,"};

		public ProjectDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Project");
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
				if(entity.toLowerCase().contains("<project>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<jobmaster>")){
					this.setJobmasterxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getJobmasterxml());
					}
				}else if(entity.toLowerCase().contains("<contact>")){
					this.setContactxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getContactxml());
					}
				}else if(entity.toLowerCase().contains("<estimation>")){
					this.setEstimationxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEstimationxml());
					}
				}else if(entity.toLowerCase().contains("<projectplan>")){
					this.setProjectplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getProjectplanxml());
					}
				}else if(entity.toLowerCase().contains("<budget>")){
					this.setBudgetxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBudgetxml());
					}
				}else if(entity.toLowerCase().contains("<tax>")){
					this.setTaxxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTaxxml());
					}
				}else if(entity.toLowerCase().contains("<pomaster>")){
					this.setPomasterxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPomasterxml());
					}
				}
			}
		}

		public Rows getJobmasterRows(){
			TemplateTable tab=this.doSelectChild("jobmaster", "jobmaster2project",this.getParentobjid(),jobmastercol,jobmastersqldatatype,this.JobmasterFilter);
			String [] propJobmasterlist={};
			String [] codeJobmasterlist={"maincode"};
			String [] relationJobmasterlist={};
			Rows rows=tu.getXMLRows(tab, "jobmaster",codeJobmasterlist,propJobmasterlist,relationJobmasterlist,jobmastercolcaption,jobmasterdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(jobmastercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(jobmasterdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(jobmastertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getContactRows(){
			TemplateTable tab=this.doSelectChild("contact", "contact2project",this.getParentobjid(),contactcol,contactsqldatatype,this.ContactFilter);
			String [] propContactlist={};
			String [] codeContactlist={"countrycode","contactcode"};
			String [] relationContactlist={};
			Rows rows=tu.getXMLRows(tab, "contact",codeContactlist,propContactlist,relationContactlist,contactcolcaption,contactdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(contactcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(contactdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(contacttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEstimationRows(){
			TemplateTable tab=this.doSelectChild("estimation", "estimation2project",this.getParentobjid(),estimationcol,estimationsqldatatype,this.EstimationFilter);
			String [] propEstimationlist={"status"};
			String [] codeEstimationlist={};
			String [] relationEstimationlist={};
			Rows rows=tu.getXMLRows(tab, "estimation",codeEstimationlist,propEstimationlist,relationEstimationlist,estimationcolcaption,estimationdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(estimationcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(estimationdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(estimationtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getProjectplanRows(){
			TemplateTable tab=this.doSelectChild("projectplan", "projectplan2project",this.getParentobjid(),projectplancol,projectplansqldatatype,this.ProjectplanFilter);
			String [] propProjectplanlist={"status"};
			String [] codeProjectplanlist={};
			String [] relationProjectplanlist={};
			Rows rows=tu.getXMLRows(tab, "projectplan",codeProjectplanlist,propProjectplanlist,relationProjectplanlist,projectplancolcaption,projectplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(projectplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(projectplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(projectplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBudgetRows(){
			TemplateTable tab=this.doSelectChild("budget", "budget2project",this.getParentobjid(),budgetcol,budgetsqldatatype,this.BudgetFilter);
			String [] propBudgetlist={"status"};
			String [] codeBudgetlist={};
			String [] relationBudgetlist={"account:budget2account:list:"};
			Rows rows=tu.getXMLRows(tab, "budget",codeBudgetlist,propBudgetlist,relationBudgetlist,budgetcolcaption,budgetdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(budgetcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(budgetdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(budgettype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTaxRows(){
			TemplateTable tab=this.doSelectChild("tax", "tax2project",this.getParentobjid(),taxcol,taxsqldatatype,this.TaxFilter);
			String [] propTaxlist={};
			String [] codeTaxlist={"taxcode"};
			String [] relationTaxlist={};
			Rows rows=tu.getXMLRows(tab, "tax",codeTaxlist,propTaxlist,relationTaxlist,taxcolcaption,taxdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(taxcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(taxdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(taxtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPomasterRows(){
			TemplateTable tab=this.doSelectChild("pomaster", "pomaster2project",this.getParentobjid(),pomastercol,pomastersqldatatype,this.PomasterFilter);
			String [] propPomasterlist={"status"};
			String [] codePomasterlist={"pocode","itemcode"};
			String [] relationPomasterlist={};
			Rows rows=tu.getXMLRows(tab, "pomaster",codePomasterlist,propPomasterlist,relationPomasterlist,pomastercolcaption,pomasterdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(pomastercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(pomasterdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(pomastertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getProjectSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ProjectFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Project");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Project", tab, chartcol);
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

		public Rows getProjectRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Project",codeProjectlist,propProjectlist,relationProjectlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getProjectRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Project",codeProjectlist,propProjectlist,relationProjectlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getProjectRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postProjectContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getProjectByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and project2"+this.getFilters();
			}
			String sql= "select * from table_Project where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Project",codeProjectlist,propProjectlist,relationProjectlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
