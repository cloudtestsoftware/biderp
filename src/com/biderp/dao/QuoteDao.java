
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

	public class QuoteDao extends QuoteImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"quote,quotemaster,quotelog"};
		protected String []childtabs={"quote,quotemaster,quotelog"};
		protected String []childtabnames={"Quote Facts,Master Jobs,QuoteLog"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","quote2phase","quote2messagequeue","quote2partlist","name","projectcode","description","bgtrangecode","quotetype","startdate","address","city","zipcode","state","firstname","lastname","phone","phone2","fax","email","othercontact","otherinfo","leadsourcecode","currencycode","pctwaste","pctprofit","status","bidquoteno"};
		protected String [] maincolcaption={"Id","Phase","MessageQueue","PartList","Project Name","Project Code","Brief Description","Budget Range","Type (Rate/Cost)","Work Start Date","Project Address","City","Project Zip Code","State(like CA,..)","First Name","Last Name","Phone (xxx-xxx-xxxx)","Mobile/Other No (xxx-xxx-xxxx)","Fax (xxx-xxx-xxxx)","Contact Email","Additional Contact","Any Instruction","How Do You Know Us","Currency","Est. Wastage(%)","Est. Profit(%)","Status","Bid Quote No (BQN)"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Name_t","Code_t","String4000_t","Code_t","Type_t","Date_t","String400_t","String50_t","ZipCode_t","StateCode_t","Name_t","Name_t","Phone_t","Phone_t","Phone_t","Email_t","String1000_t","String4000_t","Code_t","Code_t","Float_t","Float_t","Status_t","String20_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#select_filter,,,,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,,,,,,,#select_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes"};
		
		protected String [] summarycol={"name","estamount"};
		protected String [] summarycolcaption={"Name","Estimated Cost"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","projectcode","estamount","startdate","address","phone","phone2","pctwaste","pctprofit","bidquoteno"};
		protected String [] reportcolcaption={"Id","Project Name","Project Code","Estimated Cost","Work Start Date","Project Address","Phone (xxx-xxx-xxxx)","Mobile/Other No (xxx-xxx-xxxx)","Est. Wastage(%)","Est. Profit(%)","Bid Quote No (BQN)"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Code_t","Money_t","Date_t","String400_t","Phone_t","Phone_t","Float_t","Float_t","String20_t"};
		
		protected String [] searchcol={"objid","name","projectcode","startdate","city","zipcode","state","firstname","lastname","status","bidquoteno"};
		protected String [] searchcolcaption={"Id","Project Name","Project Code","Work Start Date","City","Project Zip Code","State(like CA,..)","First Name","Last Name","Status","Bid Quote No (BQN)"};
		protected String [] searchcoltype={"integer","text","select","date","text","integer","text","text","text","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","Date_t","String50_t","ZipCode_t","StateCode_t","Name_t","Name_t","Status_t","String20_t"};

	protected String [] propQuotelist={"quotetype","status"};
		protected String [] codeQuotelist={"projectcode","bgtrangecode","leadsourcecode","currencycode"};
		protected String [] relationQuotelist={"phase:quote2phase:list:","messagequeue:quote2messagequeue:list:","partlist:quote2partlist:list:"};
		
		protected String [] quotemastertype={"table"};
		protected String [] quotemastercol={"objid","name","maincode","description","isbid","total"};
		protected String [] quotemastercolcaption={"Id","Name","Main Job","Description","Is For Bid","Total"};
		protected String [] quotemastersqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] quotemasterdatadomain={"Int_t","Name_t","Code_t","String4000_t","Boolean_t","Money_t"};
		protected String [] quotemasterdisable={"yes,no,no,no,no,yes"};
		protected String [] quotemastercolsearch={"#text_filter,#text_filter,#select_filter,,#select_filter,"};
		
		protected String [] quotelogtype={"table"};
		protected String [] quotelogcol={"objid","name","note","createdate"};
		protected String [] quotelogcolcaption={"Id","Name","Note","Create Date"};
		protected String [] quotelogsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] quotelogdatadomain={"Int_t","Name_t","String4000_t","Date_t"};
		protected String [] quotelogdisable={"yes,no,no,no"};
		protected String [] quotelogcolsearch={"#text_filter,#text_filter,,#text_filter"};

		public QuoteDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Quote");
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
				if(entity.toLowerCase().contains("<quote>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<quotemaster>")){
					this.setQuotemasterxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQuotemasterxml());
					}
				}else if(entity.toLowerCase().contains("<quotelog>")){
					this.setQuotelogxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQuotelogxml());
					}
				}
			}
		}

		public Rows getQuotemasterRows(){
			TemplateTable tab=this.doSelectChild("quotemaster", "quotemaster2quote",this.getParentobjid(),quotemastercol,quotemastersqldatatype,this.QuotemasterFilter);
			String [] propQuotemasterlist={"isbid"};
			String [] codeQuotemasterlist={"maincode"};
			String [] relationQuotemasterlist={};
			Rows rows=tu.getXMLRows(tab, "quotemaster",codeQuotemasterlist,propQuotemasterlist,relationQuotemasterlist,quotemastercolcaption,quotemasterdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(quotemastercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(quotemasterdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(quotemastertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuotelogRows(){
			TemplateTable tab=this.doSelectChild("quotelog", "quotelog2quote",this.getParentobjid(),quotelogcol,quotelogsqldatatype,this.QuotelogFilter);
			String [] propQuoteloglist={};
			String [] codeQuoteloglist={};
			String [] relationQuoteloglist={};
			Rows rows=tu.getXMLRows(tab, "quotelog",codeQuoteloglist,propQuoteloglist,relationQuoteloglist,quotelogcolcaption,quotelogdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(quotelogcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(quotelogdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(quotelogtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuoteSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.QuoteFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Quote");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Quote", tab, chartcol);
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

		public Rows getQuoteRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Quote",codeQuotelist,propQuotelist,relationQuotelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getQuoteRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Quote",codeQuotelist,propQuotelist,relationQuotelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getQuoteRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postQuoteContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getQuoteByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and quote2"+this.getFilters();
			}
			String sql= "select * from table_Quote where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Quote",codeQuotelist,propQuotelist,relationQuotelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
