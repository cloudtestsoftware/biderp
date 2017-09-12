
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

	public class PartrequestDao extends PartrequestImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"partrequest,itemreceived,itemdispatch"};
		protected String []childtabs={"partrequest,itemreceived,itemdispatch"};
		protected String []childtabnames={"Partrequest Facts,ItemReceived,ItemDispatch"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","partrequest2parts","partrequest2pomaster","partrequest2purchaseorder","name","title","projectcode","description","actualrate","pcttax","qntrequest","qntpurchased","qntused","status","pocode","approvedate","startdate","purchasedate","note","progresscode"};
		protected String [] maincolcaption={"Id","Parts","PoMaster","PurchaseOrder","Name","Project Title","Project Code","Description","Actual Rate","(%) Tax","Quantity Request","Quantity Purchased","Quantity Used","Status","Purchased by","Issued Date","Work Start Date","Purchased Date","Note","Progress Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Name_t","Name_t","Code_t","String1000_t","Money_t","Percent_t","Int_t","Int_t","Int_t","Status_t","Code_t","Date_t","Date_t","Date_t","String1000_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,,#text_filter,#select_filter,#text_filter,,,,,,#select_filter,#select_filter,,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,yes,no,no,no,no,no,no,no,yes"};
		
		protected String [] summarycol={"name","indentno","qntbalance","qntinventory","totalqntaccepted","totalqntdispatched"};
		protected String [] summarycolcaption={"Name","Indent No","Request-Used","In Inventory","Quantity Received","Quantity Dispatched"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] summarydatadomain={"Name_t","Name_t","Int_t","Int_t","Int_t","Int_t"};
		
		protected String [] reportcol={"objid","name","indentno","title","projectcode","description","pcttax","qntrequest","qntpurchased","qntused","qntbalance","qntinventory","totalqntaccepted","totalqntdispatched","status","pocode","startdate","progresscode"};
		protected String [] reportcolcaption={"Id","Name","Indent No","Project Title","Project Code","Description","(%) Tax","Quantity Request","Quantity Purchased","Quantity Used","Request-Used","In Inventory","Quantity Received","Quantity Dispatched","Status","Purchased by","Work Start Date","Progress Code"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Name_t","Code_t","String1000_t","Percent_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t","Status_t","Code_t","Date_t","Code_t"};
		
		protected String [] searchcol={"objid","title","projectcode","description","status","pocode"};
		protected String [] searchcolcaption={"Id","Project Title","Project Code","Description","Status","Purchased by"};
		protected String [] searchcoltype={"integer","text","select","text","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","String1000_t","Status_t","Code_t"};

	protected String [] propPartrequestlist={"status"};
		protected String [] codePartrequestlist={"projectcode","pocode","progresscode"};
		protected String [] relationPartrequestlist={"parts:partrequest2parts:list:","pomaster:partrequest2pomaster:list:","purchaseorder:partrequest2purchaseorder:list:"};
		
		protected String [] itemreceivedtype={"table"};
		protected String [] itemreceivedcol={"objid","itemreceived2warehouseline","name","note","qntreceived","qntaccepted","receivedate"};
		protected String [] itemreceivedcolcaption={"Id","WarehouseLine","Name","Note","Quantity Received","Quantity Accepted","Received Date"};
		protected String [] itemreceivedsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.DATE};
		protected String [] itemreceiveddatadomain={"Int_t","Int_t","Name_t","String500_t","Int_t","Int_t","Date_t"};
		protected String [] itemreceiveddisable={"yes,no,no,no,no,no,no"};
		protected String [] itemreceivedcolsearch={"#text_filter,#select_filter,,,,,"};
		
		protected String [] itemdispatchtype={"table"};
		protected String [] itemdispatchcol={"objid","itemdispatch2warehouseline","name","note","qntdispatched","disptachdate"};
		protected String [] itemdispatchcolcaption={"Id","WarehouseLine","Name","Note","Quantity Dispatched","Dispatch Date"};
		protected String [] itemdispatchsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.DATE};
		protected String [] itemdispatchdatadomain={"Int_t","Int_t","Name_t","String500_t","Int_t","Date_t"};
		protected String [] itemdispatchdisable={"yes,no,no,no,no,no"};
		protected String [] itemdispatchcolsearch={"#text_filter,#select_filter,,,,"};

		public PartrequestDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Partrequest");
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
				if(entity.toLowerCase().contains("<partrequest>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<itemreceived>")){
					this.setItemreceivedxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getItemreceivedxml());
					}
				}else if(entity.toLowerCase().contains("<itemdispatch>")){
					this.setItemdispatchxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getItemdispatchxml());
					}
				}
			}
		}

		public Rows getItemreceivedRows(){
			TemplateTable tab=this.doSelectChild("itemreceived", "itemreceived2partrequest",this.getParentobjid(),itemreceivedcol,itemreceivedsqldatatype,this.ItemreceivedFilter);
			String [] propItemreceivedlist={};
			String [] codeItemreceivedlist={};
			String [] relationItemreceivedlist={"warehouseline:itemreceived2warehouseline:list:"};
			Rows rows=tu.getXMLRows(tab, "itemreceived",codeItemreceivedlist,propItemreceivedlist,relationItemreceivedlist,itemreceivedcolcaption,itemreceiveddatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(itemreceivedcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(itemreceiveddisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(itemreceivedtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getItemdispatchRows(){
			TemplateTable tab=this.doSelectChild("itemdispatch", "itemdispatch2partrequest",this.getParentobjid(),itemdispatchcol,itemdispatchsqldatatype,this.ItemdispatchFilter);
			String [] propItemdispatchlist={};
			String [] codeItemdispatchlist={};
			String [] relationItemdispatchlist={"warehouseline:itemdispatch2warehouseline:list:"};
			Rows rows=tu.getXMLRows(tab, "itemdispatch",codeItemdispatchlist,propItemdispatchlist,relationItemdispatchlist,itemdispatchcolcaption,itemdispatchdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(itemdispatchcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(itemdispatchdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(itemdispatchtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPartrequestSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PartrequestFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Partrequest");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Partrequest", tab, chartcol);
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

		public Rows getPartrequestRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Partrequest",codePartrequestlist,propPartrequestlist,relationPartrequestlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getPartrequestRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Partrequest",codePartrequestlist,propPartrequestlist,relationPartrequestlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getPartrequestRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postPartrequestContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPartrequestByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and partrequest2"+this.getFilters();
			}
			String sql= "select * from table_Partrequest where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Partrequest",codePartrequestlist,propPartrequestlist,relationPartrequestlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
