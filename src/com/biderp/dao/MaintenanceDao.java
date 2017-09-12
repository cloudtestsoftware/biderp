
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

	public class MaintenanceDao extends MaintenanceImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"maintenance,serviceparts,serviceresource,maintenancelog"};
		protected String []childtabs={"maintenance,serviceparts,serviceresource,maintenancelog"};
		protected String []childtabnames={"Maintenance Facts,Parts,Resource,Log"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","maintenance2asset","maintenance2equipment","name","description","servicetype","category","deptcode","servicedate","status"};
		protected String [] maincolcaption={"Id","Asset","Equipment","Title","Description","Type","Category","Department","Service Date","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Name_t","String500_t","Type_t","Type_t","Code_t","Date_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,,#select_filter,#select_filter,#select_filter,#text_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","totalcost"};
		protected String [] summarycolcaption={"Name","Total Cost"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","servicetype","category","deptcode","servicedate","status","totalcost"};
		protected String [] reportcolcaption={"Id","Title","Type","Category","Department","Service Date","Status","Total Cost"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Type_t","Type_t","Code_t","Date_t","Status_t","Money_t"};
		
		protected String [] searchcol={"objid","name","servicetype","category","deptcode","servicedate","status"};
		protected String [] searchcolcaption={"Id","Title","Type","Category","Department","Service Date","Status"};
		protected String [] searchcoltype={"integer","text","select","select","select","date","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Type_t","Type_t","Code_t","Date_t","Status_t"};

	protected String [] propMaintenancelist={"servicetype","category","status"};
		protected String [] codeMaintenancelist={"deptcode"};
		protected String [] relationMaintenancelist={"asset:maintenance2asset:list:","equipment:maintenance2equipment:list:"};
		
		protected String [] servicepartstype={"table"};
		protected String [] servicepartscol={"objid","serviceparts2partprice","name","partcode","partno","description","unitprice","unitcount","umcode","partcost","note"};
		protected String [] servicepartscolcaption={"Id","ServicePart","Name","Part Code","Supplier Part No","Part Spec","Unit Price","Quantity","Um Code","Total","Note"};
		protected String [] servicepartssqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] servicepartsdatadomain={"Int_t","Int_t","Name_t","String30_t","String100_t","String1000_t","Money_t","Int_t","Code_t","Money_t","String4000_t"};
		protected String [] servicepartsdisable={"yes,no,no,yes,yes,yes,yes,no,yes,yes,no"};
		protected String [] servicepartscolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,,,,,#select_filter,#numeric_filter,"};
		
		protected String [] serviceresourcetype={"table"};
		protected String [] serviceresourcecol={"objid","serviceresource2partprice","name","startdate","enddate","resourcecode","quantity","umcode","rate","total"};
		protected String [] serviceresourcecolcaption={"Id","ServiceResources","Detail","Start Date","End Date","Resource Code","Quantity","Um Code","Rate","Total"};
		protected String [] serviceresourcesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER};
		protected String [] serviceresourcedatadomain={"Int_t","Int_t","String300_t","Date_t","Date_t","Code_t","Double_t","Code_t","Money_t","Money_t"};
		protected String [] serviceresourcedisable={"yes,no,no,no,no,no,no,yes,yes,yes"};
		protected String [] serviceresourcecolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#select_filter,,,,"};
		
		protected String [] maintenancelogtype={"table"};
		protected String [] maintenancelogcol={"objid","name","note"};
		protected String [] maintenancelogcolcaption={"Id","Title","Note"};
		protected String [] maintenancelogsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maintenancelogdatadomain={"Int_t","Name_t","String4000_t"};
		protected String [] maintenancelogdisable={"yes,no,no"};
		protected String [] maintenancelogcolsearch={"#text_filter,#text_filter,"};

		public MaintenanceDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Maintenance");
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
				if(entity.toLowerCase().contains("<maintenance>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<serviceparts>")){
					this.setServicepartsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getServicepartsxml());
					}
				}else if(entity.toLowerCase().contains("<serviceresource>")){
					this.setServiceresourcexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getServiceresourcexml());
					}
				}else if(entity.toLowerCase().contains("<maintenancelog>")){
					this.setMaintenancelogxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMaintenancelogxml());
					}
				}
			}
		}

		public Rows getServicepartsRows(){
			TemplateTable tab=this.doSelectChild("serviceparts", "serviceparts2maintenance",this.getParentobjid(),servicepartscol,servicepartssqldatatype,this.ServicepartsFilter);
			String [] propServicepartslist={};
			String [] codeServicepartslist={"umcode"};
			String [] relationServicepartslist={"servicepart:serviceparts2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "serviceparts",codeServicepartslist,propServicepartslist,relationServicepartslist,servicepartscolcaption,servicepartsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(servicepartscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(servicepartsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(servicepartstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getServiceresourceRows(){
			TemplateTable tab=this.doSelectChild("serviceresource", "serviceresource2maintenance",this.getParentobjid(),serviceresourcecol,serviceresourcesqldatatype,this.ServiceresourceFilter);
			String [] propServiceresourcelist={};
			String [] codeServiceresourcelist={"resourcecode","umcode"};
			String [] relationServiceresourcelist={"serviceresources:serviceresource2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "serviceresource",codeServiceresourcelist,propServiceresourcelist,relationServiceresourcelist,serviceresourcecolcaption,serviceresourcedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(serviceresourcecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(serviceresourcedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(serviceresourcetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMaintenancelogRows(){
			TemplateTable tab=this.doSelectChild("maintenancelog", "maintenancelog2maintenance",this.getParentobjid(),maintenancelogcol,maintenancelogsqldatatype,this.MaintenancelogFilter);
			String [] propMaintenanceloglist={};
			String [] codeMaintenanceloglist={};
			String [] relationMaintenanceloglist={};
			Rows rows=tu.getXMLRows(tab, "maintenancelog",codeMaintenanceloglist,propMaintenanceloglist,relationMaintenanceloglist,maintenancelogcolcaption,maintenancelogdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(maintenancelogcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(maintenancelogdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(maintenancelogtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMaintenanceSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.MaintenanceFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Maintenance");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Maintenance", tab, chartcol);
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

		public Rows getMaintenanceRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Maintenance",codeMaintenancelist,propMaintenancelist,relationMaintenancelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getMaintenanceRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Maintenance",codeMaintenancelist,propMaintenancelist,relationMaintenancelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getMaintenanceRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postMaintenanceContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getMaintenanceByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and maintenance2"+this.getFilters();
			}
			String sql= "select * from table_Maintenance where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Maintenance",codeMaintenancelist,propMaintenancelist,relationMaintenancelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
