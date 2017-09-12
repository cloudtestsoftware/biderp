
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

	public class PartsDao extends PartsImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"parts,partrequest"};
		protected String []childtabs={"parts,partrequest,indent"};
		protected String []childtabnames={"Parts Facts,PartRequest,Indent"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","parts2partprice","parts2change","parts2joblist","parts2boq","name","projectcode","maincode","subcode","unitcount","coveredprice","origincode","deptcode","status","note"};
		protected String [] maincolcaption={"Id","BoqPart","Change","JobList","Boq","Name","Project Code","Main Code","Sub Code","Quantity Estimated","Covered Price","Origin","Department","Status","Note"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Int_t","Name_t","Code_t","Code_t","Code_t","Int_t","Money_t","Code_t","Code_t","Status_t","String4000_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,#text_filter,#select_filter,#select_filter,#select_filter,,,#select_filter,#select_filter,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","title","domaincode","partcode","partno","description","startdate","enddate","unitprice","umcode"};
		protected String [] summarycolcaption={"Name","Project Title","Domain","Part Code","Supplier Part No","Part Spec","Milestone Start Date","Milestone End Date","Unit Price","Um Code"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","Name_t","Code_t","String30_t","String100_t","String1000_t","Date_t","Date_t","Money_t","Code_t"};
		
		protected String [] reportcol={"objid","name","title","projectcode","domaincode","partcode","startdate","unitprice","unitcount","umcode","coveredprice"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Project Code","Domain","Part Code","Milestone Start Date","Unit Price","Quantity Estimated","Um Code","Covered Price"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","String30_t","Date_t","Money_t","Int_t","Code_t","Money_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","maincode","subcode","domaincode","partcode","umcode","origincode","deptcode","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Main Code","Sub Code","Domain","Part Code","Um Code","Origin","Department","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select","select","select","text","select","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Code_t","Code_t","String30_t","Code_t","Code_t","Code_t","Status_t"};

	protected String [] propPartslist={"status"};
		protected String [] codePartslist={"projectcode","maincode","subcode","origincode","deptcode"};
		protected String [] relationPartslist={"boqpart:parts2partprice:list:","change:parts2change:list:","joblist:parts2joblist:list:","boq:parts2boq:list:"};
		
		protected String [] partrequesttype={"table"};
		protected String [] partrequestcol={"objid","name","actualrate","pcttax","qntrequest","qntpurchased","qntused","qntbalance","qntinventory","totalqntaccepted","totalqntdispatched","status","pocode","startdate","purchasedate"};
		protected String [] partrequestcolcaption={"Id","Name","Actual Rate","(%) Tax","Quantity Request","Quantity Purchased","Quantity Used","Request-Used","In Inventory","Quantity Received","Quantity Dispatched","Status","Purchased by","Work Start Date","Purchased Date"};
		protected String [] partrequestsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE};
		protected String [] partrequestdatadomain={"Int_t","Name_t","Money_t","Percent_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Status_t","Code_t","Date_t","Date_t"};
		protected String [] partrequestdisable={"yes,no,no,no,no,yes,no,yes,yes,yes,yes,no,no,no,no"};
		protected String [] partrequestcolsearch={"#text_filter,,,,,,,,,,,#select_filter,#select_filter,,"};
		
		protected String [] indenttype={"view"};
		protected String [] indentcol={"objid","name","indentno","mrno","partno","description","umcode","pocode","qntrequest","total"};
		protected String [] indentcolcaption={"Id","Part Name","Indent No","MRN (BOM)","Supplier Part No","Details","Um Code","Purchased By","Qnt. To Order","Total With Tax"};
		protected String [] indentsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER};
		protected String [] indentdatadomain={"Int_t","Name_t","String30_t","String30_t","String100_t","String500_t","Code_t","String20_t","Int_t","Int_t"};
		protected String [] indentdisable={"yes,no,no,no,no,no,no,no,no,no"};
		protected String [] indentcolsearch={"#text_filter,#text_filter,,,#select_filter,#select_filter,#select_filter,#text_filter,,"};

		public PartsDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Parts");
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
				if(entity.toLowerCase().contains("<parts>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<partrequest>")){
					this.setPartrequestxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPartrequestxml());
					}
				}else if(entity.toLowerCase().contains("<indent>")){
					this.setIndentxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getIndentxml());
					}
				}
			}
		}

		public Rows getPartrequestRows(){
			TemplateTable tab=this.doSelectChild("partrequest", "partrequest2parts",this.getParentobjid(),partrequestcol,partrequestsqldatatype,this.PartrequestFilter);
			String [] propPartrequestlist={"status"};
			String [] codePartrequestlist={"pocode"};
			String [] relationPartrequestlist={};
			Rows rows=tu.getXMLRows(tab, "partrequest",codePartrequestlist,propPartrequestlist,relationPartrequestlist,partrequestcolcaption,partrequestdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(partrequestcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(partrequestdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(partrequesttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getIndentRows(){
			TemplateTable tab=this.doSelectChild("indent", "indent2parts",this.getParentobjid(),indentcol,indentsqldatatype,this.IndentFilter);
			String [] propIndentlist={};
			String [] codeIndentlist={"umcode"};
			String [] relationIndentlist={};
			Rows rows=tu.getXMLRows(tab, "indent",codeIndentlist,propIndentlist,relationIndentlist,indentcolcaption,indentdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(indentcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(indentdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(indenttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPartsSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PartsFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Parts");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Parts", tab, chartcol);
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

		public Rows getPartsRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Parts",codePartslist,propPartslist,relationPartslist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getPartsRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Parts",codePartslist,propPartslist,relationPartslist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getPartsRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postPartsContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPartsByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and parts2"+this.getFilters();
			}
			String sql= "select * from table_Parts where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Parts",codePartslist,propPartslist,relationPartslist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
