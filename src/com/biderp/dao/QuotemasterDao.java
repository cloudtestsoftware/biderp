
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

	public class QuotemasterDao extends QuotemasterImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"quotemaster,quotejobs"};
		protected String []childtabs={"quotemaster,quotejobs"};
		protected String []childtabnames={"Quotemaster Facts,Jobs"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","quotemaster2quote","name","projectcode","maincode","description","isbid"};
		protected String [] maincolcaption={"Id","Quote","Name","Project Code","Main Job","Description","Is For Bid"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Code_t","Code_t","String4000_t","Boolean_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#select_filter,,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","total"};
		protected String [] summarycolcaption={"Name","Total"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","projectcode","maincode","isbid","total"};
		protected String [] reportcolcaption={"Id","Name","Project Code","Main Job","Is For Bid","Total"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Code_t","Code_t","Boolean_t","Money_t"};
		
		protected String [] searchcol={"objid","name","projectcode","maincode","isbid"};
		protected String [] searchcolcaption={"Id","Name","Project Code","Main Job","Is For Bid"};
		protected String [] searchcoltype={"integer","text","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","Code_t","Boolean_t"};

	protected String [] propQuotemasterlist={"isbid"};
		protected String [] codeQuotemasterlist={"projectcode","maincode"};
		protected String [] relationQuotemasterlist={"quote:quotemaster2quote:list:"};
		
		protected String [] quotejobstype={"table"};
		protected String [] quotejobscol={"objid","name","maincode","subcode","description","unitcount","umcode","unitprice","estrate","profitrate","total"};
		protected String [] quotejobscolcaption={"Id","Name","Main Job","Sub Job","Description","Quantity","Um Code","Unit Rate","Estimated Rate","Estimated (Rate +Profit)","Total"};
		protected String [] quotejobssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] quotejobsdatadomain={"Int_t","Name_t","Code_t","Code_t","String4000_t","Int_t","Code_t","Money_t","Money_t","Money_t","Money_t"};
		protected String [] quotejobsdisable={"yes,yes,no,no,no,no,yes,yes,yes,yes,yes"};
		protected String [] quotejobscolsearch={"#text_filter,#text_filter,#select_filter,#select_filter,,,#select_filter,,,,"};

		public QuotemasterDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Quotemaster");
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
				if(entity.toLowerCase().contains("<quotemaster>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<quotejobs>")){
					this.setQuotejobsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQuotejobsxml());
					}
				}
			}
		}

		public Rows getQuotejobsRows(){
			TemplateTable tab=this.doSelectChild("quotejobs", "quotejobs2quotemaster",this.getParentobjid(),quotejobscol,quotejobssqldatatype,this.QuotejobsFilter);
			String [] propQuotejobslist={};
			String [] codeQuotejobslist={"maincode","subcode","umcode"};
			String [] relationQuotejobslist={};
			Rows rows=tu.getXMLRows(tab, "quotejobs",codeQuotejobslist,propQuotejobslist,relationQuotejobslist,quotejobscolcaption,quotejobsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(quotejobscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(quotejobsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(quotejobstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuotemasterSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.QuotemasterFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Quotemaster");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Quotemaster", tab, chartcol);
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

		public Rows getQuotemasterRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Quotemaster",codeQuotemasterlist,propQuotemasterlist,relationQuotemasterlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getQuotemasterRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Quotemaster",codeQuotemasterlist,propQuotemasterlist,relationQuotemasterlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getQuotemasterRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postQuotemasterContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getQuotemasterByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and quotemaster2"+this.getFilters();
			}
			String sql= "select * from table_Quotemaster where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Quotemaster",codeQuotemasterlist,propQuotemasterlist,relationQuotemasterlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
