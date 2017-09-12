
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

	public class PartpriceDao extends PartpriceImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"partprice,qrinfo"};
		protected String []childtabs={"partprice,orderpending,partcount,qrinfo"};
		protected String []childtabnames={"Partprice Facts,InstallPart,PartCount,QRInfo"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","partprice2partlist","name","domaincode","servicelife","umcode","unitprice","pcttax","partspec","note","partno"};
		protected String [] maincolcaption={"Id","PartList","Name","Part Group","Service Life (Yrs)","UM Code","Unit Price","(%) Tax","Details","Note","Supplier Part No"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Code_t","Float_t","Code_t","Money_t","Percent_t","String500_t","String500_t","String100_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,,#select_filter,,,,,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","partcode"};
		protected String [] summarycolcaption={"Name","Part Code"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","String30_t"};
		
		protected String [] reportcol={"objid","name","domaincode","partcode","umcode","unitprice","partno"};
		protected String [] reportcolcaption={"Id","Name","Part Group","Part Code","UM Code","Unit Price","Supplier Part No"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Code_t","String30_t","Code_t","Money_t","String100_t"};
		
		protected String [] searchcol={"objid","name","domaincode","partcode","umcode","partno"};
		protected String [] searchcolcaption={"Id","Name","Part Group","Part Code","UM Code","Supplier Part No"};
		protected String [] searchcoltype={"integer","text","select","text","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","String30_t","Code_t","String100_t"};

	protected String [] propPartpricelist={};
		protected String [] codePartpricelist={"domaincode","umcode"};
		protected String [] relationPartpricelist={"partlist:partprice2partlist:list:"};
		
		protected String [] orderpendingtype={"view"};
		protected String [] orderpendingcol={"objid","name","description","partno","partcode","umcode","qntpending","orderamount"};
		protected String [] orderpendingcolcaption={"Id","Part Name","Description","Supplier PartNo","Part Code","Um Code","Quantity To Order","Amount"};
		protected String [] orderpendingsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER};
		protected String [] orderpendingdatadomain={"Int_t","Name_t","Name_t","String100_t","Code_t","Code_t","Int_t","Money_t"};
		protected String [] orderpendingdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] orderpendingcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,"};
		
		protected String [] partcounttype={"view"};
		protected String [] partcountcol={"objid","name","umcode","qntrequest","qntused","qntbalance"};
		protected String [] partcountcolcaption={"Id","Part Name","Um Code","Quantity Request","Quantity Used","Request-Used"};
		protected String [] partcountsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] partcountdatadomain={"Int_t","Name_t","Code_t","Int_t","Int_t","Int_t"};
		protected String [] partcountdisable={"yes,no,no,no,no,no"};
		protected String [] partcountcolsearch={"#text_filter,#text_filter,#select_filter,,,"};
		
		protected String [] qrinfotype={"table"};
		protected String [] qrinfocol={"objid","name","infocode","url"};
		protected String [] qrinfocolcaption={"Id","Step Name","Info Type","Document"};
		protected String [] qrinfosqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] qrinfodatadomain={"Int_t","Name_t","Code_t","String200_t"};
		protected String [] qrinfodisable={"yes,no,no,no"};
		protected String [] qrinfocolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};

		public PartpriceDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Partprice");
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
				if(entity.toLowerCase().contains("<partprice>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<orderpending>")){
					this.setOrderpendingxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getOrderpendingxml());
					}
				}else if(entity.toLowerCase().contains("<partcount>")){
					this.setPartcountxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPartcountxml());
					}
				}else if(entity.toLowerCase().contains("<qrinfo>")){
					this.setQrinfoxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQrinfoxml());
					}
				}
			}
		}

		public Rows getOrderpendingRows(){
			TemplateTable tab=this.doSelectChild("orderpending", "orderpending2partprice",this.getParentobjid(),orderpendingcol,orderpendingsqldatatype,this.OrderpendingFilter);
			String [] propOrderpendinglist={};
			String [] codeOrderpendinglist={};
			String [] relationOrderpendinglist={};
			Rows rows=tu.getXMLRows(tab, "orderpending",codeOrderpendinglist,propOrderpendinglist,relationOrderpendinglist,orderpendingcolcaption,orderpendingdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(orderpendingcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(orderpendingdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(orderpendingtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPartcountRows(){
			TemplateTable tab=this.doSelectChild("partcount", "partcount2partprice",this.getParentobjid(),partcountcol,partcountsqldatatype,this.PartcountFilter);
			String [] propPartcountlist={};
			String [] codePartcountlist={"umcode"};
			String [] relationPartcountlist={};
			Rows rows=tu.getXMLRows(tab, "partcount",codePartcountlist,propPartcountlist,relationPartcountlist,partcountcolcaption,partcountdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(partcountcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(partcountdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(partcounttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQrinfoRows(){
			TemplateTable tab=this.doSelectChild("qrinfo", "qrinfo2partprice",this.getParentobjid(),qrinfocol,qrinfosqldatatype,this.QrinfoFilter);
			String [] propQrinfolist={};
			String [] codeQrinfolist={"infocode"};
			String [] relationQrinfolist={};
			Rows rows=tu.getXMLRows(tab, "qrinfo",codeQrinfolist,propQrinfolist,relationQrinfolist,qrinfocolcaption,qrinfodatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(qrinfocolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(qrinfodisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(qrinfotype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPartpriceSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PartpriceFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Partprice");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Partprice", tab, chartcol);
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

		public Rows getPartpriceRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Partprice",codePartpricelist,propPartpricelist,relationPartpricelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getPartpriceRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Partprice",codePartpricelist,propPartpricelist,relationPartpricelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getPartpriceRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postPartpriceContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPartpriceByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and partprice2"+this.getFilters();
			}
			String sql= "select * from table_Partprice where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Partprice",codePartpricelist,propPartpricelist,relationPartpricelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
