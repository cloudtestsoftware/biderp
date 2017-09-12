
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

	public class PomasterDao extends PomasterImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"pomaster,purchaseorder,resourceorder"};
		protected String []childtabs={"purchaseorder,posupplier,resourceorder,resourcesupplier,bompurchase,deptpobudget"};
		protected String []childtabnames={"PurchaseOrder,PoSupplier,ResourceOrder,ResourceSupplier,Bom,DeptBudget"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","pomaster2project","name","pocode","itemcode","status","note"};
		protected String [] maincolcaption={"Id","Project","Name","Po Code","Item Code","Status","Note"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Code_t","Code_t","Status_t","String1000_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#select_filter,#text_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,yes,yes,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","pocode","itemcode","status"};
		protected String [] reportcolcaption={"Id","Name","Po Code","Item Code","Status"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Code_t","Code_t","Status_t"};
		
		protected String [] searchcol={"objid","name","pocode","itemcode","status"};
		protected String [] searchcolcaption={"Id","Name","Po Code","Item Code","Status"};
		protected String [] searchcoltype={"integer","text","select","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","Code_t","Status_t"};

	protected String [] propPomasterlist={"status"};
		protected String [] codePomasterlist={"pocode","itemcode"};
		protected String [] relationPomasterlist={"project:pomaster2project:list:"};
		
		protected String [] purchaseordertype={"table"};
		protected String [] purchaseordercol={"objid","purchaseorder2supplier","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","mrno","potype","total","status","maxlineno","deptcode","note"};
		protected String [] purchaseordercolcaption={"Id","Supplier","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","MRN (Bom or None)","Order Process","Total","Status","Max Line No","Department","Note"};
		protected String [] purchaseordersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] purchaseorderdatadomain={"Int_t","Int_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","String30_t","Type_t","Money_t","Status_t","Int_t","Code_t","String2000_t"};
		protected String [] purchaseorderdisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,yes,no,no,no,no"};
		protected String [] purchaseordercolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#select_filter,,#select_filter,#text_filter"};
		
		protected String [] posuppliertype={"view"};
		protected String [] posuppliercol={"objid","name","address","contactcode","linecount"};
		protected String [] posuppliercolcaption={"Id","Supplier Name","Address","Type","Line Count"};
		protected String [] posuppliersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] posupplierdatadomain={"Int_t","Int_t","String200_t","Code_t","Int_t"};
		protected String [] posupplierdisable={"yes,no,no,no,no"};
		protected String [] posuppliercolsearch={"#text_filter,,,,"};
		
		protected String [] resourceordertype={"table"};
		protected String [] resourceordercol={"objid","resourceorder2supplier","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","amount","tax","total","status","resourcecode","maxlineno","deptcode","note"};
		protected String [] resourceordercolcaption={"Id","Supplier","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","Amount","Tax (%)","Total","Status","Resource Code","Max Line No","Department","Note"};
		protected String [] resourceordersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] resourceorderdatadomain={"Int_t","Int_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","Money_t","Float_t","Money_t","Status_t","Code_t","Int_t","Code_t","String2000_t"};
		protected String [] resourceorderdisable={"yes,no,no,no,no,no,no,no,no,no,no,yes,no,yes,no,no,no,no,no"};
		protected String [] resourceordercolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#select_filter,#select_filter,,#select_filter,#text_filter"};
		
		protected String [] resourcesuppliertype={"view"};
		protected String [] resourcesuppliercol={"objid","name","address","contactcode","taskcount"};
		protected String [] resourcesuppliercolcaption={"Id","Supplier Name","Address","Type","TaskCount"};
		protected String [] resourcesuppliersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] resourcesupplierdatadomain={"Int_t","Int_t","String200_t","Code_t","Int_t"};
		protected String [] resourcesupplierdisable={"yes,no,no,no,no"};
		protected String [] resourcesuppliercolsearch={"#text_filter,,,,"};
		
		protected String [] bompurchasetype={"view"};
		protected String [] bompurchasecol={"objid","name","mrno","title","deptcode","pocode","qntrequest","qntused","qntbalance","qnttopurchase","linecount","startdate"};
		protected String [] bompurchasecolcaption={"Id","Part Name","Requisition No (MRN)","Project Title","Department","Purchased By","Quantity Request","Quantity Used","Request-Used","To Purchase","Line Count","Start Date"};
		protected String [] bompurchasesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] bompurchasedatadomain={"Int_t","Name_t","String30_t","Name_t","Code_t","String30_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t"};
		protected String [] bompurchasedisable={"yes,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] bompurchasecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,,,,,,"};
		
		protected String [] deptpobudgettype={"view"};
		protected String [] deptpobudgetcol={"objid","name","deptcode","total","amountspent","balance","pctspent","pctbalance"};
		protected String [] deptpobudgetcolcaption={"Id","Name","Department","Head Total","Amount Spent","Amount Balance","(%) Spent","(%) Balance"};
		protected String [] deptpobudgetsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] deptpobudgetdatadomain={"Int_t","Name_t","Code_t","Money_t","Money_t","Money_t","Money_t","Money_t"};
		protected String [] deptpobudgetdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] deptpobudgetcolsearch={"#text_filter,#text_filter,#text_filter,,,,,"};

		public PomasterDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Pomaster");
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
				if(entity.toLowerCase().contains("<pomaster>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<purchaseorder>")){
					this.setPurchaseorderxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPurchaseorderxml());
					}
				}else if(entity.toLowerCase().contains("<posupplier>")){
					this.setPosupplierxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPosupplierxml());
					}
				}else if(entity.toLowerCase().contains("<resourceorder>")){
					this.setResourceorderxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getResourceorderxml());
					}
				}else if(entity.toLowerCase().contains("<resourcesupplier>")){
					this.setResourcesupplierxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getResourcesupplierxml());
					}
				}else if(entity.toLowerCase().contains("<bompurchase>")){
					this.setBompurchasexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBompurchasexml());
					}
				}else if(entity.toLowerCase().contains("<deptpobudget>")){
					this.setDeptpobudgetxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getDeptpobudgetxml());
					}
				}
			}
		}

		public Rows getPurchaseorderRows(){
			TemplateTable tab=this.doSelectChild("purchaseorder", "purchaseorder2pomaster",this.getParentobjid(),purchaseordercol,purchaseordersqldatatype,this.PurchaseorderFilter);
			String [] propPurchaseorderlist={"potype","status"};
			String [] codePurchaseorderlist={"deptcode"};
			String [] relationPurchaseorderlist={"supplier:purchaseorder2supplier:list:"};
			Rows rows=tu.getXMLRows(tab, "purchaseorder",codePurchaseorderlist,propPurchaseorderlist,relationPurchaseorderlist,purchaseordercolcaption,purchaseorderdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(purchaseordercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(purchaseorderdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(purchaseordertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPosupplierRows(){
			TemplateTable tab=this.doSelectChild("posupplier", "posupplier2pomaster",this.getParentobjid(),posuppliercol,posuppliersqldatatype,this.PosupplierFilter);
			String [] propPosupplierlist={};
			String [] codePosupplierlist={"contactcode"};
			String [] relationPosupplierlist={};
			Rows rows=tu.getXMLRows(tab, "posupplier",codePosupplierlist,propPosupplierlist,relationPosupplierlist,posuppliercolcaption,posupplierdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(posuppliercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(posupplierdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(posuppliertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getResourceorderRows(){
			TemplateTable tab=this.doSelectChild("resourceorder", "resourceorder2pomaster",this.getParentobjid(),resourceordercol,resourceordersqldatatype,this.ResourceorderFilter);
			String [] propResourceorderlist={"status"};
			String [] codeResourceorderlist={"resourcecode","deptcode"};
			String [] relationResourceorderlist={"supplier:resourceorder2supplier:list:"};
			Rows rows=tu.getXMLRows(tab, "resourceorder",codeResourceorderlist,propResourceorderlist,relationResourceorderlist,resourceordercolcaption,resourceorderdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(resourceordercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(resourceorderdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(resourceordertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getResourcesupplierRows(){
			TemplateTable tab=this.doSelectChild("resourcesupplier", "resourcesupplier2pomaster",this.getParentobjid(),resourcesuppliercol,resourcesuppliersqldatatype,this.ResourcesupplierFilter);
			String [] propResourcesupplierlist={};
			String [] codeResourcesupplierlist={"contactcode"};
			String [] relationResourcesupplierlist={};
			Rows rows=tu.getXMLRows(tab, "resourcesupplier",codeResourcesupplierlist,propResourcesupplierlist,relationResourcesupplierlist,resourcesuppliercolcaption,resourcesupplierdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(resourcesuppliercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(resourcesupplierdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(resourcesuppliertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBompurchaseRows(){
			TemplateTable tab=this.doSelectChild("bompurchase", "bompurchase2pomaster",this.getParentobjid(),bompurchasecol,bompurchasesqldatatype,this.BompurchaseFilter);
			String [] propBompurchaselist={};
			String [] codeBompurchaselist={"deptcode"};
			String [] relationBompurchaselist={};
			Rows rows=tu.getXMLRows(tab, "bompurchase",codeBompurchaselist,propBompurchaselist,relationBompurchaselist,bompurchasecolcaption,bompurchasedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bompurchasecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bompurchasedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bompurchasetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getDeptpobudgetRows(){
			TemplateTable tab=this.doSelectChild("deptpobudget", "deptpobudget2pomaster",this.getParentobjid(),deptpobudgetcol,deptpobudgetsqldatatype,this.DeptpobudgetFilter);
			String [] propDeptpobudgetlist={};
			String [] codeDeptpobudgetlist={"deptcode"};
			String [] relationDeptpobudgetlist={};
			Rows rows=tu.getXMLRows(tab, "deptpobudget",codeDeptpobudgetlist,propDeptpobudgetlist,relationDeptpobudgetlist,deptpobudgetcolcaption,deptpobudgetdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(deptpobudgetcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(deptpobudgetdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(deptpobudgettype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPomasterSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PomasterFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Pomaster");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Pomaster", tab, chartcol);
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

		public Rows getPomasterRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Pomaster",codePomasterlist,propPomasterlist,relationPomasterlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getPomasterRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Pomaster",codePomasterlist,propPomasterlist,relationPomasterlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getPomasterRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postPomasterContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPomasterByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and pomaster2"+this.getFilters();
			}
			String sql= "select * from table_Pomaster where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Pomaster",codePomasterlist,propPomasterlist,relationPomasterlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
