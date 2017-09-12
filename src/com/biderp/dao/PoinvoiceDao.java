
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

	public class PoinvoiceDao extends PoinvoiceImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"poinvoice,"};
		protected String []childtabs={"invoiceditems,invoicedresource"};
		protected String []childtabnames={"InvoicedItems,InvoicedResource"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","poinvoice2resourceorder","poinvoice2account","poinvoice2purchaseorder","poinvoice2fromaccount","name","poname","suppliername","paidto","cutoffdate","invoicedate","paydate","itemcode","deptcode","status","amount","tax","total","note","progresscode"};
		protected String [] maincolcaption={"Id","ResourceOrder","Account","PurchaseOrder","FromAccount","Name","Po Name","Supplier Name","Paid To","Cutoff Date","Invoice Date","Pay Date","Item Code","Department","Status","Amount","Tax","Total","Note","Progress Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Int_t","Name_t","Name_t","Name_t","Name_t","Date_t","Date_t","Date_t","Code_t","Code_t","Type_t","Money_t","Float_t","Money_t","String500_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,yes,yes,no,no,no,no,no,no,no,yes,no,yes,no,yes"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","poname","suppliername","paidto","cutoffdate","invoicedate","paydate","itemcode","deptcode","status","amount","tax","progresscode"};
		protected String [] reportcolcaption={"Id","Name","Po Name","Supplier Name","Paid To","Cutoff Date","Invoice Date","Pay Date","Item Code","Department","Status","Amount","Tax","Progress Code"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Name_t","Name_t","Date_t","Date_t","Date_t","Code_t","Code_t","Type_t","Money_t","Float_t","Code_t"};
		
		protected String [] searchcol={"objid","name","poname","suppliername","paidto","cutoffdate","invoicedate","paydate","itemcode","deptcode","status"};
		protected String [] searchcolcaption={"Id","Name","Po Name","Supplier Name","Paid To","Cutoff Date","Invoice Date","Pay Date","Item Code","Department","Status"};
		protected String [] searchcoltype={"integer","text","text","text","text","date","date","date","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Name_t","Name_t","Date_t","Date_t","Date_t","Code_t","Code_t","Type_t"};

	protected String [] propPoinvoicelist={"status"};
		protected String [] codePoinvoicelist={"itemcode","deptcode","progresscode"};
		protected String [] relationPoinvoicelist={"resourceorder:poinvoice2resourceorder:list:","account:poinvoice2account:list:","purchaseorder:poinvoice2purchaseorder:list:","fromaccount:poinvoice2fromaccount:list:"};
		
		protected String [] invoiceditemstype={"view"};
		protected String [] invoiceditemscol={"objid","name","note","receivedate","actualrate","amount","withtax"};
		protected String [] invoiceditemscolcaption={"Id","Item Name","Note","Received Date","Acutual rate","Amount","With Tax"};
		protected String [] invoiceditemssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] invoiceditemsdatadomain={"Int_t","Name_t","String200_t","Date_t","Money_t","Money_t","Money_t"};
		protected String [] invoiceditemsdisable={"yes,no,no,no,no,no,no"};
		protected String [] invoiceditemscolsearch={"#text_filter,,,,,,"};
		
		protected String [] invoicedresourcetype={"view"};
		protected String [] invoicedresourcecol={"objid","name","title","taskcode","servicedate","actualrate","estunit","regulartime","overtime","totaltime","amount","withtax"};
		protected String [] invoicedresourcecolcaption={"Id","Item Name","Title","Task Code","Service Date","Acutual rate","Total Unit Estimated","Regular Unit","Overtime Unit","Total Unit","Amount","With Tax"};
		protected String [] invoicedresourcesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] invoicedresourcedatadomain={"Int_t","Name_t","Name_t","Code_t","Date_t","Money_t","Float_t","Float_t","Float_t","Float_t","Money_t","Money_t"};
		protected String [] invoicedresourcedisable={"yes,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] invoicedresourcecolsearch={"#text_filter,,,,,,,,,,,"};

		public PoinvoiceDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Poinvoice");
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
				if(entity.toLowerCase().contains("<poinvoice>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<invoiceditems>")){
					this.setInvoiceditemsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getInvoiceditemsxml());
					}
				}else if(entity.toLowerCase().contains("<invoicedresource>")){
					this.setInvoicedresourcexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getInvoicedresourcexml());
					}
				}
			}
		}

		public Rows getInvoiceditemsRows(){
			TemplateTable tab=this.doSelectChild("invoiceditems", "invoiceditems2poinvoice",this.getParentobjid(),invoiceditemscol,invoiceditemssqldatatype,this.InvoiceditemsFilter);
			String [] propInvoiceditemslist={};
			String [] codeInvoiceditemslist={};
			String [] relationInvoiceditemslist={};
			Rows rows=tu.getXMLRows(tab, "invoiceditems",codeInvoiceditemslist,propInvoiceditemslist,relationInvoiceditemslist,invoiceditemscolcaption,invoiceditemsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(invoiceditemscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(invoiceditemsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(invoiceditemstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getInvoicedresourceRows(){
			TemplateTable tab=this.doSelectChild("invoicedresource", "invoicedresource2poinvoice",this.getParentobjid(),invoicedresourcecol,invoicedresourcesqldatatype,this.InvoicedresourceFilter);
			String [] propInvoicedresourcelist={};
			String [] codeInvoicedresourcelist={"taskcode"};
			String [] relationInvoicedresourcelist={};
			Rows rows=tu.getXMLRows(tab, "invoicedresource",codeInvoicedresourcelist,propInvoicedresourcelist,relationInvoicedresourcelist,invoicedresourcecolcaption,invoicedresourcedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(invoicedresourcecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(invoicedresourcedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(invoicedresourcetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPoinvoiceSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PoinvoiceFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Poinvoice");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Poinvoice", tab, chartcol);
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

		public Rows getPoinvoiceRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Poinvoice",codePoinvoicelist,propPoinvoicelist,relationPoinvoicelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getPoinvoiceRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Poinvoice",codePoinvoicelist,propPoinvoicelist,relationPoinvoicelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getPoinvoiceRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postPoinvoiceContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPoinvoiceByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and poinvoice2"+this.getFilters();
			}
			String sql= "select * from table_Poinvoice where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Poinvoice",codePoinvoicelist,propPoinvoicelist,relationPoinvoicelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
