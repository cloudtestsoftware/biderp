
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

	public class LeadDao extends LeadImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"lead,leadnote,leademail,leadaccess"};
		protected String []childtabs={"lead,leadnote,leademail,leadaccess"};
		protected String []childtabnames={"Lead Facts,Note,Email,Access"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","lead2messagequeue","name","firstname","lastname","company","url","street","city","state","zipcode","countrycode","phone","phone2","fax","reasoncode","mediacode","createdate","status"};
		protected String [] maincolcaption={"Id","MessageQueue","Email","First Name","Other/Last Name","Company/Owner Name","Web Site(http:..)","Street","City","State(Like CA,..)","Zip Code","Country","Phone","Cell Phone","Fax","Type","How Do You Know Us","Create Date","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Email_t","Name_t","Name_t","String100_t","String200_t","String200_t","String100_t","StateCode_t","ZipCode_t","Code_t","Phone_t","Phone_t","Phone_t","Code_t","Code_t","Date_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#text_filter,#text_filter,,,,#select_filter,,#text_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","assignto"};
		protected String [] summarycolcaption={"Name","Assign To"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","Name_t"};
		
		protected String [] reportcol={"objid","name","firstname","lastname","phone","phone2","fax","reasoncode","createdate","status","assignto"};
		protected String [] reportcolcaption={"Id","Email","First Name","Other/Last Name","Phone","Cell Phone","Fax","Type","Create Date","Status","Assign To"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Email_t","Name_t","Name_t","Phone_t","Phone_t","Phone_t","Code_t","Date_t","Status_t","Name_t"};
		
		protected String [] searchcol={"objid","name","firstname","lastname","company","url","zipcode","countrycode","reasoncode","createdate","status","assignto"};
		protected String [] searchcolcaption={"Id","Email","First Name","Other/Last Name","Company/Owner Name","Web Site(http:..)","Zip Code","Country","Type","Create Date","Status","Assign To"};
		protected String [] searchcoltype={"integer","email","text","text","text","text","integer","text","select","date","select","text"};
		protected String [] searchdatadomain={"Id_t","Email_t","Name_t","Name_t","String100_t","String200_t","ZipCode_t","Code_t","Code_t","Date_t","Status_t","Name_t"};

	protected String [] propLeadlist={"status"};
		protected String [] codeLeadlist={"countrycode","reasoncode","mediacode"};
		protected String [] relationLeadlist={"messagequeue:lead2messagequeue:list:"};
		
		protected String [] leadnotetype={"table"};
		protected String [] leadnotecol={"objid","name","discussion","createdate"};
		protected String [] leadnotecolcaption={"Id","Title","Discussion/Note","Create Date"};
		protected String [] leadnotesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] leadnotedatadomain={"Int_t","Name_t","String4000_t","Date_t"};
		protected String [] leadnotedisable={"yes,no,no,no"};
		protected String [] leadnotecolsearch={"#text_filter,#text_filter,,#text_filter"};
		
		protected String [] leademailtype={"table"};
		protected String [] leademailcol={"objid","name","messagetitle","header","footer","createdate","status"};
		protected String [] leademailcolcaption={"Id","Email","Message Title","Header","Footer","Create Date","Status"};
		protected String [] leademailsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] leademaildatadomain={"Int_t","Email_t","String200_t","String4000_t","String4000_t","Date_t","Status_t"};
		protected String [] leademaildisable={"yes,no,no,no,no,no,no"};
		protected String [] leademailcolsearch={"#text_filter,#text_filter,,,,#text_filter,#select_filter"};
		
		protected String [] leadaccesstype={"table"};
		protected String [] leadaccesscol={"objid","name","accessallowed","accesstaken","reason"};
		protected String [] leadaccesscolcaption={"Id","Login Name","Access Allowed","Access Taken","Reason"};
		protected String [] leadaccesssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR};
		protected String [] leadaccessdatadomain={"Int_t","Email_t","Int_t","Int_t","String400_t"};
		protected String [] leadaccessdisable={"yes,no,no,no,no"};
		protected String [] leadaccesscolsearch={"#text_filter,#text_filter,,,"};

		public LeadDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Lead");
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
				if(entity.toLowerCase().contains("<lead>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<leadnote>")){
					this.setLeadnotexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getLeadnotexml());
					}
				}else if(entity.toLowerCase().contains("<leademail>")){
					this.setLeademailxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getLeademailxml());
					}
				}else if(entity.toLowerCase().contains("<leadaccess>")){
					this.setLeadaccessxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getLeadaccessxml());
					}
				}
			}
		}

		public Rows getLeadnoteRows(){
			TemplateTable tab=this.doSelectChild("leadnote", "leadnote2lead",this.getParentobjid(),leadnotecol,leadnotesqldatatype,this.LeadnoteFilter);
			String [] propLeadnotelist={};
			String [] codeLeadnotelist={};
			String [] relationLeadnotelist={};
			Rows rows=tu.getXMLRows(tab, "leadnote",codeLeadnotelist,propLeadnotelist,relationLeadnotelist,leadnotecolcaption,leadnotedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(leadnotecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(leadnotedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(leadnotetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getLeademailRows(){
			TemplateTable tab=this.doSelectChild("leademail", "leademail2lead",this.getParentobjid(),leademailcol,leademailsqldatatype,this.LeademailFilter);
			String [] propLeademaillist={"status"};
			String [] codeLeademaillist={};
			String [] relationLeademaillist={};
			Rows rows=tu.getXMLRows(tab, "leademail",codeLeademaillist,propLeademaillist,relationLeademaillist,leademailcolcaption,leademaildatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(leademailcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(leademaildisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(leademailtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getLeadaccessRows(){
			TemplateTable tab=this.doSelectChild("leadaccess", "leadaccess2lead",this.getParentobjid(),leadaccesscol,leadaccesssqldatatype,this.LeadaccessFilter);
			String [] propLeadaccesslist={};
			String [] codeLeadaccesslist={};
			String [] relationLeadaccesslist={};
			Rows rows=tu.getXMLRows(tab, "leadaccess",codeLeadaccesslist,propLeadaccesslist,relationLeadaccesslist,leadaccesscolcaption,leadaccessdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(leadaccesscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(leadaccessdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(leadaccesstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getLeadSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.LeadFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Lead");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Lead", tab, chartcol);
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

		public Rows getLeadRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getLeadRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getLeadRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postLeadContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getLeadByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and lead2"+this.getFilters();
			}
			String sql= "select * from table_Lead where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
