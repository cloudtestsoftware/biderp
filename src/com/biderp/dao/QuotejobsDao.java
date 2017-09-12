
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

	public class QuotejobsDao extends QuotejobsImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"quotejobs,bidquiz,quoteparts,quoteresource"};
		protected String []childtabs={"quotejobs,bidquiz,quoteparts,quoteresource"};
		protected String []childtabnames={"Quotejobs Facts,BidQuiz,QuoteParts,QuoteResource"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","quotejobs2quotemaster","name","projectcode","maincode","subcode","description","unitcount"};
		protected String [] maincolcaption={"Id","QuoteMaster","Name","Project Code","Main Job","Sub Job","Description","Quantity"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Code_t","Code_t","Code_t","String4000_t","Int_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#select_filter,#select_filter,,"};
		
		protected String [] maincoldisable={"yes,no,yes,no,no,no,no,no"};
		
		protected String [] summarycol={"name","umcode","unitprice","estrate","profitrate","total"};
		protected String [] summarycolcaption={"Name","Um Code","Unit Rate","Estimated Rate","Estimated (Rate +Profit)","Total"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Code_t","Money_t","Money_t","Money_t","Money_t"};
		
		protected String [] reportcol={"objid","name","projectcode","maincode","subcode","unitcount","umcode","unitprice","estrate","profitrate","total"};
		protected String [] reportcolcaption={"Id","Name","Project Code","Main Job","Sub Job","Quantity","Um Code","Unit Rate","Estimated Rate","Estimated (Rate +Profit)","Total"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Code_t","Code_t","Code_t","Int_t","Code_t","Money_t","Money_t","Money_t","Money_t"};
		
		protected String [] searchcol={"objid","name","projectcode","maincode","subcode","umcode"};
		protected String [] searchcolcaption={"Id","Name","Project Code","Main Job","Sub Job","Um Code"};
		protected String [] searchcoltype={"integer","text","select","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","Code_t","Code_t","Code_t"};

	protected String [] propQuotejobslist={};
		protected String [] codeQuotejobslist={"projectcode","maincode","subcode"};
		protected String [] relationQuotejobslist={"quotemaster:quotejobs2quotemaster:list:"};
		
		protected String [] bidquiztype={"table"};
		protected String [] bidquizcol={"objid","description","requirecode","umcode","unitcount","criteria","point","quizindex","name"};
		protected String [] bidquizcolcaption={"Id","Description","Is Require","Unit Code","Unit Count","Evaluation Criteria","Evaluation Point","Index","Title"};
		protected String [] bidquizsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] bidquizdatadomain={"Int_t","String4000_t","Code_t","Code_t","Int_t","String500_t","Float_t","String20_t","Name_t"};
		protected String [] bidquizdisable={"yes,no,no,no,no,no,no,yes,yes"};
		protected String [] bidquizcolsearch={"#text_filter,,,,#text_filter,#text_filter,#numeric_filter,,"};
		
		protected String [] quotepartstype={"table"};
		protected String [] quotepartscol={"objid","quoteparts2partprice","name","partcode","partno","description","coveredprice","unitprice","unitcount","umcode","note"};
		protected String [] quotepartscolcaption={"Id","BoqPart","Name","Part Code","Supplier Part No","Part Spec","Covered Price","Unit Price","Quantity Estimated","Um Code","Note"};
		protected String [] quotepartssqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] quotepartsdatadomain={"Int_t","Int_t","Name_t","String30_t","String100_t","String1000_t","Money_t","Money_t","Int_t","Code_t","String4000_t"};
		protected String [] quotepartsdisable={"yes,no,no,yes,yes,yes,no,yes,no,yes,no"};
		protected String [] quotepartscolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,,,,,,#select_filter,"};
		
		protected String [] quoteresourcetype={"table"};
		protected String [] quoteresourcecol={"objid","quoteresource2partprice","name","taskcode","resourcecode","estunit","umcode","rate"};
		protected String [] quoteresourcecolcaption={"Id","Resources","Detail","Task Code","Resource Code","Est. Unit","Um Code","Rate"};
		protected String [] quoteresourcesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER};
		protected String [] quoteresourcedatadomain={"Int_t","Int_t","String300_t","Code_t","Code_t","Double_t","Code_t","Money_t"};
		protected String [] quoteresourcedisable={"yes,no,no,no,no,no,no,yes"};
		protected String [] quoteresourcecolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#select_filter,,,"};

		public QuotejobsDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Quotejobs");
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
				if(entity.toLowerCase().contains("<quotejobs>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<bidquiz>")){
					this.setBidquizxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBidquizxml());
					}
				}else if(entity.toLowerCase().contains("<quoteparts>")){
					this.setQuotepartsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQuotepartsxml());
					}
				}else if(entity.toLowerCase().contains("<quoteresource>")){
					this.setQuoteresourcexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQuoteresourcexml());
					}
				}
			}
		}

		public Rows getBidquizRows(){
			TemplateTable tab=this.doSelectChild("bidquiz", "bidquiz2quotejobs",this.getParentobjid(),bidquizcol,bidquizsqldatatype,this.BidquizFilter);
			String [] propBidquizlist={};
			String [] codeBidquizlist={"requirecode","umcode"};
			String [] relationBidquizlist={};
			Rows rows=tu.getXMLRows(tab, "bidquiz",codeBidquizlist,propBidquizlist,relationBidquizlist,bidquizcolcaption,bidquizdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bidquizcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bidquizdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bidquiztype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuotepartsRows(){
			TemplateTable tab=this.doSelectChild("quoteparts", "quoteparts2quotejobs",this.getParentobjid(),quotepartscol,quotepartssqldatatype,this.QuotepartsFilter);
			String [] propQuotepartslist={};
			String [] codeQuotepartslist={};
			String [] relationQuotepartslist={"boqpart:quoteparts2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "quoteparts",codeQuotepartslist,propQuotepartslist,relationQuotepartslist,quotepartscolcaption,quotepartsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(quotepartscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(quotepartsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(quotepartstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuoteresourceRows(){
			TemplateTable tab=this.doSelectChild("quoteresource", "quoteresource2quotejobs",this.getParentobjid(),quoteresourcecol,quoteresourcesqldatatype,this.QuoteresourceFilter);
			String [] propQuoteresourcelist={};
			String [] codeQuoteresourcelist={"taskcode","resourcecode","umcode"};
			String [] relationQuoteresourcelist={"resources:quoteresource2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "quoteresource",codeQuoteresourcelist,propQuoteresourcelist,relationQuoteresourcelist,quoteresourcecolcaption,quoteresourcedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(quoteresourcecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(quoteresourcedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(quoteresourcetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuotejobsSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.QuotejobsFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Quotejobs");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Quotejobs", tab, chartcol);
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

		public Rows getQuotejobsRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Quotejobs",codeQuotejobslist,propQuotejobslist,relationQuotejobslist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getQuotejobsRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Quotejobs",codeQuotejobslist,propQuotejobslist,relationQuotejobslist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getQuotejobsRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postQuotejobsContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getQuotejobsByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and quotejobs2"+this.getFilters();
			}
			String sql= "select * from table_Quotejobs where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Quotejobs",codeQuotejobslist,propQuotejobslist,relationQuotejobslist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
