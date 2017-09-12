
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

	public class PurchaseorderDao extends PurchaseorderImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"purchaseorder,partrequest,poinvoice"};
		protected String []childtabs={"purchaseorder,partrequest,purchaseindent,poinvoice"};
		protected String []childtabnames={"Purchaseorder Facts,PartRequest,Indent,PoInvoice"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","purchaseorder2pomaster","purchaseorder2supplier","purchaseorder2bidrequest","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","mrno","potype","status","maxlineno","deptcode","note","progresscode"};
		protected String [] maincolcaption={"Id","PoMaster","Supplier","BidRequest","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","MRN (Bom or None)","Order Process","Status","Max Line No","Department","Note","Progress Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","String30_t","Type_t","Status_t","Int_t","Code_t","String2000_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,#select_filter,,#select_filter,#text_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes"};
		
		protected String [] summarycol={"name","total"};
		protected String [] summarycolcaption={"Name","Total"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","suppliername","suppaddress","contactname","contactno","podate","completedate","mrno","potype","total","status","maxlineno","progresscode"};
		protected String [] reportcolcaption={"Id","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Order Date","Complete Date","MRN (Bom or None)","Order Process","Total","Status","Max Line No","Progress Code"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","String300_t","Name_t","Phone_t","Date_t","Date_t","String30_t","Type_t","Money_t","Status_t","Int_t","Code_t"};
		
		protected String [] searchcol={"objid","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","status","deptcode","note"};
		protected String [] searchcolcaption={"Id","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","Status","Department","Note"};
		protected String [] searchcoltype={"integer","text","text","text","text","text","text","text","date","date","select","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","Status_t","Code_t","String2000_t"};

	protected String [] propPurchaseorderlist={"potype","status"};
		protected String [] codePurchaseorderlist={"deptcode","progresscode"};
		protected String [] relationPurchaseorderlist={"pomaster:purchaseorder2pomaster:list:","supplier:purchaseorder2supplier:list:","bidrequest:purchaseorder2bidrequest:list:"};
		
		protected String [] partrequesttype={"table"};
		protected String [] partrequestcol={"objid","partrequest2parts","name","actualrate","pcttax","qntrequest","qntpurchased","qntused","qntbalance","qntinventory","totalqntaccepted","totalqntdispatched","status","pocode","startdate","purchasedate"};
		protected String [] partrequestcolcaption={"Id","Parts","Name","Actual Rate","(%) Tax","Quantity Request","Quantity Purchased","Quantity Used","Request-Used","In Inventory","Quantity Received","Quantity Dispatched","Status","Purchased by","Work Start Date","Purchased Date"};
		protected String [] partrequestsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE};
		protected String [] partrequestdatadomain={"Int_t","Int_t","Name_t","Money_t","Percent_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Status_t","Code_t","Date_t","Date_t"};
		protected String [] partrequestdisable={"yes,no,no,no,no,no,yes,no,yes,yes,yes,yes,no,no,no,no"};
		protected String [] partrequestcolsearch={"#text_filter,#select_filter,,,,,,,,,,,#select_filter,#select_filter,,"};
		
		protected String [] purchaseindenttype={"view"};
		protected String [] purchaseindentcol={"objid","name","indentno","mrno","partno","description","umcode","pocode","qntrequest","total"};
		protected String [] purchaseindentcolcaption={"Id","Part Name","Indent No","MRN (BOM)","Supplier Part No","Details","Um Code","Purchased By","Qnt. To Order","Total With Tax"};
		protected String [] purchaseindentsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER};
		protected String [] purchaseindentdatadomain={"Int_t","Name_t","String30_t","String30_t","String100_t","String500_t","Code_t","String20_t","Int_t","Int_t"};
		protected String [] purchaseindentdisable={"yes,no,no,no,no,no,no,no,no,no"};
		protected String [] purchaseindentcolsearch={"#text_filter,#text_filter,,,#select_filter,#select_filter,#select_filter,#text_filter,,"};
		
		protected String [] poinvoicetype={"table"};
		protected String [] poinvoicecol={"objid","name","poname","suppliername","cutoffdate","invoicedate","itemcode","deptcode","status","amount","tax","total"};
		protected String [] poinvoicecolcaption={"Id","Name","Po Name","Supplier Name","Cutoff Date","Invoice Date","Item Code","Department","Status","Amount","Tax","Total"};
		protected String [] poinvoicesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] poinvoicedatadomain={"Int_t","Name_t","Name_t","Name_t","Date_t","Date_t","Code_t","Code_t","Type_t","Money_t","Float_t","Money_t"};
		protected String [] poinvoicedisable={"yes,no,yes,yes,no,no,no,no,no,yes,no,yes"};
		protected String [] poinvoicecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,,,"};

		public PurchaseorderDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Purchaseorder");
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
				if(entity.toLowerCase().contains("<purchaseorder>")){
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
				}else if(entity.toLowerCase().contains("<purchaseindent>")){
					this.setPurchaseindentxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPurchaseindentxml());
					}
				}else if(entity.toLowerCase().contains("<poinvoice>")){
					this.setPoinvoicexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPoinvoicexml());
					}
				}
			}
		}

		public Rows getPartrequestRows(){
			TemplateTable tab=this.doSelectChild("partrequest", "partrequest2purchaseorder",this.getParentobjid(),partrequestcol,partrequestsqldatatype,this.PartrequestFilter);
			String [] propPartrequestlist={"status"};
			String [] codePartrequestlist={"pocode"};
			String [] relationPartrequestlist={"parts:partrequest2parts:list:"};
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



		public Rows getPurchaseindentRows(){
			TemplateTable tab=this.doSelectChild("purchaseindent", "purchaseindent2purchaseorder",this.getParentobjid(),purchaseindentcol,purchaseindentsqldatatype,this.PurchaseindentFilter);
			String [] propPurchaseindentlist={};
			String [] codePurchaseindentlist={"umcode"};
			String [] relationPurchaseindentlist={};
			Rows rows=tu.getXMLRows(tab, "purchaseindent",codePurchaseindentlist,propPurchaseindentlist,relationPurchaseindentlist,purchaseindentcolcaption,purchaseindentdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(purchaseindentcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(purchaseindentdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(purchaseindenttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPoinvoiceRows(){
			TemplateTable tab=this.doSelectChild("poinvoice", "poinvoice2purchaseorder",this.getParentobjid(),poinvoicecol,poinvoicesqldatatype,this.PoinvoiceFilter);
			String [] propPoinvoicelist={"status"};
			String [] codePoinvoicelist={"itemcode","deptcode"};
			String [] relationPoinvoicelist={};
			Rows rows=tu.getXMLRows(tab, "poinvoice",codePoinvoicelist,propPoinvoicelist,relationPoinvoicelist,poinvoicecolcaption,poinvoicedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(poinvoicecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(poinvoicedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(poinvoicetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPurchaseorderSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PurchaseorderFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Purchaseorder");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Purchaseorder", tab, chartcol);
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

		public Rows getPurchaseorderRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Purchaseorder",codePurchaseorderlist,propPurchaseorderlist,relationPurchaseorderlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getPurchaseorderRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Purchaseorder",codePurchaseorderlist,propPurchaseorderlist,relationPurchaseorderlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getPurchaseorderRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postPurchaseorderContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPurchaseorderByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and purchaseorder2"+this.getFilters();
			}
			String sql= "select * from table_Purchaseorder where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Purchaseorder",codePurchaseorderlist,propPurchaseorderlist,relationPurchaseorderlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
