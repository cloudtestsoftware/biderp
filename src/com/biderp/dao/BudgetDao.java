
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

	public class BudgetDao extends BudgetImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"budget,budgethead,budgetplan,invoice,payment,monthly,yearly"};
		protected String []childtabs={"budget,budgethead,budgetplan,invoice,payment,monthly,yearly"};
		protected String []childtabnames={"Budget Facts,BudgetHead,BudgetPlan,Invoice,Payment,MonthlyBOQ,YearlyBOQ"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","budget2account","budget2project","name","title","projectcode","status"};
		protected String [] maincolcaption={"Id","Account","Project","Name","Project Title","Project Code","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Name_t","Name_t","Code_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","totalcontract","totalwithtax","initialestimate","cumulativecost","fundreleased","fundbalance","fundused","fundunused","contracttocost","achievedtarget","budgetused"};
		protected String [] summarycolcaption={"Name","Total Job Cost","Total Job Cost + Tax","Estimated Amount","Current Cumulative Cost","Fund Paid","Fund To Be Paid","Approved To Plan","(Received-Approved)","(Contract-Cumulative)","Project Tagret(%)","Budget Used (%)"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t","Money_t","Money_t","Money_t","Money_t","Money_t","Money_t","Money_t","Money_t","Float_t","Float_t"};
		
		protected String [] reportcol={"objid","name","title","totalcontract","totalwithtax","fundused","achievedtarget","budgetused","status"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Total Job Cost","Total Job Cost + Tax","Approved To Plan","Project Tagret(%)","Budget Used (%)","Status"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Money_t","Money_t","Money_t","Float_t","Float_t","Status_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Status_t"};

	protected String [] propBudgetlist={"status"};
		protected String [] codeBudgetlist={"projectcode"};
		protected String [] relationBudgetlist={"account:budget2account:list:","project:budget2project:list:"};
		
		protected String [] budgetheadtype={"table"};
		protected String [] budgetheadcol={"objid","name","deptcode","description","pctspent","pctbalance","total","amountspent","balance"};
		protected String [] budgetheadcolcaption={"Id","Name","Department","Note","Allocation Spent(%)","Allocation Balance (%)","Allocated Amount","Amount Spent","Amount Balance"};
		protected String [] budgetheadsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] budgetheaddatadomain={"Int_t","Name_t","Code_t","String500_t","Percent_t","Percent_t","Money_t","Money_t","Money_t"};
		protected String [] budgetheaddisable={"yes,no,no,no,yes,yes,yes,yes,yes"};
		protected String [] budgetheadcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,,,,"};
		
		protected String [] budgetplantype={"table"};
		protected String [] budgetplancol={"objid","name","yearcode","monthcode","planpct","condition","fundduedate","plannedamount","received","notreceived"};
		protected String [] budgetplancolcaption={"Id","Name","Year","Month","Plan Percentage(%)","Condition","Fund Due Date","Planned Amount","Amount Invoiced","Not Received"};
		protected String [] budgetplansqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] budgetplandatadomain={"Int_t","Name_t","Code_t","Code_t","Percent_t","String4000_t","Date_t","Money_t","Money_t","Money_t"};
		protected String [] budgetplandisable={"yes,no,no,no,no,no,no,yes,yes,yes"};
		protected String [] budgetplancolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,,#text_filter,,,"};
		
		protected String [] invoicetype={"table"};
		protected String [] invoicecol={"objid","name","fromdate","uptodate","amount","tax","total"};
		protected String [] invoicecolcaption={"Id","Name","From Date","To Date","Amount","Tax","Total"};
		protected String [] invoicesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] invoicedatadomain={"Int_t","Name_t","Date_t","Date_t","Money_t","Float_t","Money_t"};
		protected String [] invoicedisable={"yes,no,no,no,yes,yes,yes"};
		protected String [] invoicecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,,"};
		
		protected String [] paymenttype={"table"};
		protected String [] paymentcol={"objid","name","invoicedamount","description","paymentmethod","financecode","fundduedate","fundpaydate","status"};
		protected String [] paymentcolcaption={"Id","Name","Invoiced Amount","Description","Check/Bill No","Finance Code","Fund Due Date","Fund Payment Date","Status"};
		protected String [] paymentsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] paymentdatadomain={"Int_t","Name_t","Money_t","String400_t","String200_t","Code_t","Date_t","Date_t","Status_t"};
		protected String [] paymentdisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] paymentcolsearch={"#text_filter,#text_filter,,,,#select_filter,#text_filter,#text_filter,#select_filter"};
		
		protected String [] monthlytype={"table"};
		protected String [] monthlycol={"objid","name","startdate","monthcode","yearcode","fundrequested","fundapproved","pctprojectest","alotedpercent"};
		protected String [] monthlycolcaption={"Id","Name","Start Date","Month Code","Year Code","Fund Requested This Month","Fund Approved This Month","(%) Project Fund Estimated","(%) Project Fund Approved"};
		protected String [] monthlysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] monthlydatadomain={"Int_t","Name_t","Date_t","Code_t","Code_t","Money_t","Money_t","Percent_t","Percent_t"};
		protected String [] monthlydisable={"yes,no,no,no,no,yes,yes,yes,yes"};
		protected String [] monthlycolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,,,,"};
		
		protected String [] yearlytype={"table"};
		protected String [] yearlycol={"objid","name","yearcode","fundrequested","fundapproved","pctprojectest","alotedpercent"};
		protected String [] yearlycolcaption={"Id","Name","Year Code","Fund Requested This Year","Fund Approved This Year","(%) Of Project Fund","(%) of Project Fund Approved"};
		protected String [] yearlysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] yearlydatadomain={"Int_t","Name_t","Code_t","Money_t","Money_t","Percent_t","Percent_t"};
		protected String [] yearlydisable={"yes,no,no,yes,yes,yes,yes"};
		protected String [] yearlycolsearch={"#text_filter,#text_filter,#select_filter,,,,"};

		public BudgetDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Budget");
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
				if(entity.toLowerCase().contains("<budget>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<budgethead>")){
					this.setBudgetheadxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBudgetheadxml());
					}
				}else if(entity.toLowerCase().contains("<budgetplan>")){
					this.setBudgetplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBudgetplanxml());
					}
				}else if(entity.toLowerCase().contains("<invoice>")){
					this.setInvoicexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getInvoicexml());
					}
				}else if(entity.toLowerCase().contains("<payment>")){
					this.setPaymentxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPaymentxml());
					}
				}else if(entity.toLowerCase().contains("<monthly>")){
					this.setMonthlyxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMonthlyxml());
					}
				}else if(entity.toLowerCase().contains("<yearly>")){
					this.setYearlyxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getYearlyxml());
					}
				}
			}
		}

		public Rows getBudgetheadRows(){
			TemplateTable tab=this.doSelectChild("budgethead", "budgethead2budget",this.getParentobjid(),budgetheadcol,budgetheadsqldatatype,this.BudgetheadFilter);
			String [] propBudgetheadlist={};
			String [] codeBudgetheadlist={"deptcode"};
			String [] relationBudgetheadlist={};
			Rows rows=tu.getXMLRows(tab, "budgethead",codeBudgetheadlist,propBudgetheadlist,relationBudgetheadlist,budgetheadcolcaption,budgetheaddatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(budgetheadcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(budgetheaddisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(budgetheadtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBudgetplanRows(){
			TemplateTable tab=this.doSelectChild("budgetplan", "budgetplan2budget",this.getParentobjid(),budgetplancol,budgetplansqldatatype,this.BudgetplanFilter);
			String [] propBudgetplanlist={};
			String [] codeBudgetplanlist={"yearcode","monthcode"};
			String [] relationBudgetplanlist={};
			Rows rows=tu.getXMLRows(tab, "budgetplan",codeBudgetplanlist,propBudgetplanlist,relationBudgetplanlist,budgetplancolcaption,budgetplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(budgetplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(budgetplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(budgetplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getInvoiceRows(){
			TemplateTable tab=this.doSelectChild("invoice", "invoice2budget",this.getParentobjid(),invoicecol,invoicesqldatatype,this.InvoiceFilter);
			String [] propInvoicelist={};
			String [] codeInvoicelist={};
			String [] relationInvoicelist={};
			Rows rows=tu.getXMLRows(tab, "invoice",codeInvoicelist,propInvoicelist,relationInvoicelist,invoicecolcaption,invoicedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(invoicecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(invoicedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(invoicetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPaymentRows(){
			TemplateTable tab=this.doSelectChild("payment", "payment2budget",this.getParentobjid(),paymentcol,paymentsqldatatype,this.PaymentFilter);
			String [] propPaymentlist={"status"};
			String [] codePaymentlist={"financecode"};
			String [] relationPaymentlist={};
			Rows rows=tu.getXMLRows(tab, "payment",codePaymentlist,propPaymentlist,relationPaymentlist,paymentcolcaption,paymentdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(paymentcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(paymentdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(paymenttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMonthlyRows(){
			TemplateTable tab=this.doSelectChild("monthly", "monthly2budget",this.getParentobjid(),monthlycol,monthlysqldatatype,this.MonthlyFilter);
			String [] propMonthlylist={};
			String [] codeMonthlylist={"monthcode","yearcode"};
			String [] relationMonthlylist={};
			Rows rows=tu.getXMLRows(tab, "monthly",codeMonthlylist,propMonthlylist,relationMonthlylist,monthlycolcaption,monthlydatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(monthlycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(monthlydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(monthlytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getYearlyRows(){
			TemplateTable tab=this.doSelectChild("yearly", "yearly2budget",this.getParentobjid(),yearlycol,yearlysqldatatype,this.YearlyFilter);
			String [] propYearlylist={};
			String [] codeYearlylist={"yearcode"};
			String [] relationYearlylist={};
			Rows rows=tu.getXMLRows(tab, "yearly",codeYearlylist,propYearlylist,relationYearlylist,yearlycolcaption,yearlydatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(yearlycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(yearlydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(yearlytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBudgetSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BudgetFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Budget");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Budget", tab, chartcol);
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

		public Rows getBudgetRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Budget",codeBudgetlist,propBudgetlist,relationBudgetlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getBudgetRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Budget",codeBudgetlist,propBudgetlist,relationBudgetlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getBudgetRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postBudgetContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBudgetByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and budget2"+this.getFilters();
			}
			String sql= "select * from table_Budget where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Budget",codeBudgetlist,propBudgetlist,relationBudgetlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
