
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

	public class ResourceorderDao extends ResourceorderImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"resourceorder,taskresource,poinvoice"};
		protected String []childtabs={"resourceorder,taskresource,poinvoice"};
		protected String []childtabnames={"Resourceorder Facts,Resource,PoInvoice"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","resourceorder2supplier","resourceorder2pomaster","resourceorder2bidrequest","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","tax","status","resourcecode","maxlineno","deptcode","note","progresscode"};
		protected String [] maincolcaption={"Id","Supplier","PoMaster","BidRequest","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","Tax (%)","Status","Resource Code","Max Line No","Department","Note","Progress Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Int_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","Float_t","Status_t","Code_t","Int_t","Code_t","String2000_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,#select_filter,#select_filter,,#select_filter,#text_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes"};
		
		protected String [] summarycol={"name","amount","total"};
		protected String [] summarycolcaption={"Name","Amount","Total"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t","Money_t"};
		
		protected String [] reportcol={"objid","name","suppliername","suppaddress","contactname","contactno","podate","completedate","amount","tax","total","status","maxlineno","progresscode"};
		protected String [] reportcolcaption={"Id","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Order Date","Complete Date","Amount","Tax (%)","Total","Status","Max Line No","Progress Code"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","String300_t","Name_t","Phone_t","Date_t","Date_t","Money_t","Float_t","Money_t","Status_t","Int_t","Code_t"};
		
		protected String [] searchcol={"objid","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","status","resourcecode","deptcode","note"};
		protected String [] searchcolcaption={"Id","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","Status","Resource Code","Department","Note"};
		protected String [] searchcoltype={"integer","text","text","text","text","text","text","text","date","date","select","select","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","Status_t","Code_t","Code_t","String2000_t"};

	protected String [] propResourceorderlist={"status"};
		protected String [] codeResourceorderlist={"resourcecode","deptcode","progresscode"};
		protected String [] relationResourceorderlist={"supplier:resourceorder2supplier:list:","pomaster:resourceorder2pomaster:list:","bidrequest:resourceorder2bidrequest:list:"};
		
		protected String [] taskresourcetype={"table"};
		protected String [] taskresourcecol={"objid","taskresource2partprice","name","taskcode","startdate","enddate","resourcecode","estunit","qntrequest","actualunit","qntbalance","umcode","rate","actualrate","origincode","pocode","deptcode"};
		protected String [] taskresourcecolcaption={"Id","Resources","Detail","Task Code","Start Date","End Date","Resource Code","Est. Unit","Quantity Requested","Actual Resource","Requested-Used","Um Code","Suggested Rate","Actual Rate","Origin","Executed by","Department"};
		protected String [] taskresourcesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] taskresourcedatadomain={"Int_t","Int_t","String300_t","Code_t","Date_t","Date_t","Code_t","Double_t","Double_t","Double_t","Int_t","Code_t","Money_t","Money_t","Code_t","Code_t","Code_t"};
		protected String [] taskresourcedisable={"yes,no,no,no,no,no,no,no,no,yes,yes,no,yes,no,no,no,no"};
		protected String [] taskresourcecolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,,,,,,,,#select_filter,#select_filter,#select_filter"};
		
		protected String [] poinvoicetype={"table"};
		protected String [] poinvoicecol={"objid","name","poname","suppliername","cutoffdate","invoicedate","itemcode","deptcode","status","amount","tax","total"};
		protected String [] poinvoicecolcaption={"Id","Name","Po Name","Supplier Name","Cutoff Date","Invoice Date","Item Code","Department","Status","Amount","Tax","Total"};
		protected String [] poinvoicesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] poinvoicedatadomain={"Int_t","Name_t","Name_t","Name_t","Date_t","Date_t","Code_t","Code_t","Type_t","Money_t","Float_t","Money_t"};
		protected String [] poinvoicedisable={"yes,no,yes,yes,no,no,no,no,no,yes,no,yes"};
		protected String [] poinvoicecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,,,"};

		public ResourceorderDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Resourceorder");
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
				if(entity.toLowerCase().contains("<resourceorder>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<taskresource>")){
					this.setTaskresourcexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTaskresourcexml());
					}
				}else if(entity.toLowerCase().contains("<poinvoice>")){
					this.setPoinvoicexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPoinvoicexml());
					}
				}
			}
		}

		public Rows getTaskresourceRows(){
			TemplateTable tab=this.doSelectChild("taskresource", "taskresource2resourceorder",this.getParentobjid(),taskresourcecol,taskresourcesqldatatype,this.TaskresourceFilter);
			String [] propTaskresourcelist={};
			String [] codeTaskresourcelist={"taskcode","resourcecode","umcode","origincode","pocode","deptcode"};
			String [] relationTaskresourcelist={"resources:taskresource2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "taskresource",codeTaskresourcelist,propTaskresourcelist,relationTaskresourcelist,taskresourcecolcaption,taskresourcedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(taskresourcecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(taskresourcedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(taskresourcetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPoinvoiceRows(){
			TemplateTable tab=this.doSelectChild("poinvoice", "poinvoice2resourceorder",this.getParentobjid(),poinvoicecol,poinvoicesqldatatype,this.PoinvoiceFilter);
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



		public Rows getResourceorderSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ResourceorderFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Resourceorder");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Resourceorder", tab, chartcol);
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

		public Rows getResourceorderRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Resourceorder",codeResourceorderlist,propResourceorderlist,relationResourceorderlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getResourceorderRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Resourceorder",codeResourceorderlist,propResourceorderlist,relationResourceorderlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getResourceorderRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postResourceorderContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getResourceorderByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and resourceorder2"+this.getFilters();
			}
			String sql= "select * from table_Resourceorder where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Resourceorder",codeResourceorderlist,propResourceorderlist,relationResourceorderlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
