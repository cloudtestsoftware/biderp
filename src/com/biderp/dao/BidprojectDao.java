
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

	public class BidprojectDao extends BidprojectImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"bidproject,bidnote"};
		protected String []childtabs={"bidnote"};
		protected String []childtabnames={"Note"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","description","criteria","estamount","bgtrangecode","typecode","instruction","startdate","listdate","bidduedate","bidduetime","address","zipcode","state","firstname","lastname","phone","phone2","fax","email","othercontact","otherinfo","status"};
		protected String [] maincolcaption={"Id","Project Name","Job Description","Key Information","Estimated Cost","Budget Range","Request Type","Instruction To Bidder","Work Start Date","ListDate","Bid Due Date","Bid Close Time(HH:MI)","Project Address","Project Zip Code","Location State(like CA,..)","Contact Fisrt Name","Last Name","Phone (xxx-xxx-xxxx)","Mobile/Other No (xxx-xxx-xxxx)","Fax (xxx-xxx-xxxx)","Contact Email","Additional Contact","Instruction To Softlean","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Name_t","String4000_t","String4000_t","Money_t","Code_t","Code_t","String4000_t","Date_t","Date_t","Date_t","Time_t","String400_t","ZipCode_t","StateCode_t","Name_t","Name_t","Phone_t","Phone_t","Phone_t","Email_t","String1000_t","String4000_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,,,,,,,,#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,,,,,,,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","description","estamount","phone","phone2","status","listdate"};
		protected String [] reportcolcaption={"Id","Project Name","Job Description","Estimated Cost","Phone (xxx-xxx-xxxx)","Mobile/Other No (xxx-xxx-xxxx)","Status","ListDate"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] reportdatadomain={"Id_t","Name_t","String4000_t","Money_t","Phone_t","Phone_t","Status_t","Date_t"};
		
		protected String [] searchcol={"objid","startdate","bidduedate","bidduetime","zipcode","state","firstname","lastname","status","listdate"};
		protected String [] searchcolcaption={"Id","Work Start Date","Bid Due Date","Bid Close Time(HH:MI)","Project Zip Code","Location State(like CA,..)","Contact Fisrt Name","Last Name","Status","ListDate"};
		protected String [] searchcoltype={"integer","date","date","timeofday","integer","text","text","text","text","date"};
		protected String [] searchdatadomain={"Id_t","Date_t","Date_t","Time_t","ZipCode_t","StateCode_t","Name_t","Name_t","Status_t","Date_t"};

	protected String [] propBidprojectlist={"status"};
		protected String [] codeBidprojectlist={"bgtrangecode","typecode"};
		protected String [] relationBidprojectlist={};
		
		protected String [] bidnotetype={"table"};
		protected String [] bidnotecol={"objid","name","discussion","createdate"};
		protected String [] bidnotecolcaption={"Id","Title","Discussion/Note","Create Date"};
		protected String [] bidnotesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] bidnotedatadomain={"Int_t","Name_t","String4000_t","Date_t"};
		protected String [] bidnotedisable={"yes,no,no,no"};
		protected String [] bidnotecolsearch={"#text_filter,#text_filter,,#text_filter"};

		public BidprojectDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Bidproject");
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
				if(entity.toLowerCase().contains("<bidproject>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<bidnote>")){
					this.setBidnotexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBidnotexml());
					}
				}
			}
		}

		public Rows getBidnoteRows(){
			TemplateTable tab=this.doSelectChild("bidnote", "bidnote2bidproject",this.getParentobjid(),bidnotecol,bidnotesqldatatype,this.BidnoteFilter);
			String [] propBidnotelist={};
			String [] codeBidnotelist={};
			String [] relationBidnotelist={};
			Rows rows=tu.getXMLRows(tab, "bidnote",codeBidnotelist,propBidnotelist,relationBidnotelist,bidnotecolcaption,bidnotedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bidnotecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bidnotedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bidnotetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBidprojectSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BidprojectFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Bidproject");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Bidproject", tab, chartcol);
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

		public Rows getBidprojectRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Bidproject",codeBidprojectlist,propBidprojectlist,relationBidprojectlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getBidprojectRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Bidproject",codeBidprojectlist,propBidprojectlist,relationBidprojectlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getBidprojectRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postBidprojectContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBidprojectByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and bidproject2"+this.getFilters();
			}
			String sql= "select * from table_Bidproject where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Bidproject",codeBidprojectlist,propBidprojectlist,relationBidprojectlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
