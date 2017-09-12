
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

	public class ProjectcontrolDao extends ProjectcontrolImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={""};
		protected String []childtabs={"currentplan,partplan,resourceplan"};
		protected String []childtabnames={"Boqplan,Partrequest,TaskResource"};
		
		protected String [] maintype={"view"};
		protected String [] maincol={"objid","objid","name","title","projectcode","startdate","contractcost","actualcost","achievedtarget","budgetused"};
		protected String [] maincolcaption={"Id","Objid","Name","Project Title","Project Code","1st Day Of Month","Contract Cost","Expenditure To Date","Completed (%)","Budget Used (%)"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] maindatadomain={"Int_t","Id_t","Name_t","Name_t","Code_t","Date_t","Money_t","Money_t","Float_t","Float_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,yes,yes,yes,yes"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","objid","name","title","projectcode","startdate","achievedtarget","budgetused"};
		protected String [] reportcolcaption={"Id","Objid","Name","Project Title","Project Code","1st Day Of Month","Completed (%)","Budget Used (%)"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Id_t","Name_t","Name_t","Code_t","Date_t","Float_t","Float_t"};
		
		protected String [] searchcol={"objid","objid","name","title","projectcode","startdate"};
		protected String [] searchcolcaption={"Id","Objid","Name","Project Title","Project Code","1st Day Of Month"};
		protected String [] searchcoltype={"integer","integer","text","text","select","date"};
		protected String [] searchdatadomain={"Id_t","Id_t","Name_t","Name_t","Code_t","Date_t"};

	protected String [] propProjectcontrollist={};
		protected String [] codeProjectcontrollist={"projectcode"};
		protected String [] relationProjectcontrollist={};
		
		protected String [] currentplantype={"view"};
		protected String [] currentplancol={"objid","name","projectname","monthcode","yearcode","quantity","qntfinished","status"};
		protected String [] currentplancolcaption={"Id","Plan Title","Project Name","Month","Year Code","Quantity Planned","Quantity Finished","Status"};
		protected String [] currentplansqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] currentplandatadomain={"Int_t","Name_t","Name_t","Code_t","Code_t","Float_t","Float_t","Status_t"};
		protected String [] currentplandisable={"yes,no,no,no,no,no,no,no"};
		protected String [] currentplancolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,,,#select_filter"};
		
		protected String [] partplantype={"view"};
		protected String [] partplancol={"objid","name","qntrequest","qntused","qntpurchased","qntbalance","status","startdate","purchasedate","note"};
		protected String [] partplancolcaption={"Id","Plan Title","Quantity Request","Quantity Used","Purchased","Request-Used","Status","Request Start Date","End Date","Note"};
		protected String [] partplansqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] partplandatadomain={"Int_t","Name_t","Int_t","Int_t","Int_t","Int_t","Status_t","Date_t","Date_t","String1000_t"};
		protected String [] partplandisable={"yes,no,no,no,no,no,no,no,no,no"};
		protected String [] partplancolsearch={"#text_filter,#text_filter,,,,,#select_filter,,#text_filter,"};
		
		protected String [] resourceplantype={"view"};
		protected String [] resourceplancol={"objid","name","qntrequest","qntused","qntbalance","status","startdate","enddate","note"};
		protected String [] resourceplancolcaption={"Id","Plan Title","Quantity Request","Quantity Used","Request-Used","Status","Request Start Date","End Date","Note"};
		protected String [] resourceplansqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] resourceplandatadomain={"Int_t","Name_t","Int_t","Int_t","Int_t","Status_t","Date_t","Date_t","String1000_t"};
		protected String [] resourceplandisable={"yes,no,no,yes,yes,no,no,no,no"};
		protected String [] resourceplancolsearch={"#text_filter,#text_filter,,,,#select_filter,,#text_filter,"};

		public ProjectcontrolDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Projectcontrol");
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
				if(entity.toLowerCase().contains("<projectcontrol>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<currentplan>")){
					this.setCurrentplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getCurrentplanxml());
					}
				}else if(entity.toLowerCase().contains("<partplan>")){
					this.setPartplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPartplanxml());
					}
				}else if(entity.toLowerCase().contains("<resourceplan>")){
					this.setResourceplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getResourceplanxml());
					}
				}
			}
		}

		public Rows getCurrentplanRows(){
			TemplateTable tab=this.doSelectChild("currentplan", "currentplan2projectcontrol",this.getParentobjid(),currentplancol,currentplansqldatatype,this.CurrentplanFilter);
			String [] propCurrentplanlist={"status"};
			String [] codeCurrentplanlist={"monthcode","yearcode"};
			String [] relationCurrentplanlist={};
			Rows rows=tu.getXMLRows(tab, "currentplan",codeCurrentplanlist,propCurrentplanlist,relationCurrentplanlist,currentplancolcaption,currentplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(currentplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(currentplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(currentplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPartplanRows(){
			TemplateTable tab=this.doSelectChild("partplan", "partplan2projectcontrol",this.getParentobjid(),partplancol,partplansqldatatype,this.PartplanFilter);
			String [] propPartplanlist={"status"};
			String [] codePartplanlist={};
			String [] relationPartplanlist={};
			Rows rows=tu.getXMLRows(tab, "partplan",codePartplanlist,propPartplanlist,relationPartplanlist,partplancolcaption,partplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(partplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(partplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(partplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getResourceplanRows(){
			TemplateTable tab=this.doSelectChild("resourceplan", "resourceplan2projectcontrol",this.getParentobjid(),resourceplancol,resourceplansqldatatype,this.ResourceplanFilter);
			String [] propResourceplanlist={"status"};
			String [] codeResourceplanlist={};
			String [] relationResourceplanlist={};
			Rows rows=tu.getXMLRows(tab, "resourceplan",codeResourceplanlist,propResourceplanlist,relationResourceplanlist,resourceplancolcaption,resourceplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(resourceplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(resourceplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(resourceplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getProjectcontrolSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ProjectcontrolFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Projectcontrol");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Projectcontrol", tab, chartcol);
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

		public Rows getProjectcontrolRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Projectcontrol",codeProjectcontrollist,propProjectcontrollist,relationProjectcontrollist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getProjectcontrolRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Projectcontrol",codeProjectcontrollist,propProjectcontrollist,relationProjectcontrollist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getProjectcontrolRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postProjectcontrolContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getProjectcontrolByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and projectcontrol2"+this.getFilters();
			}
			String sql= "select * from table_Projectcontrol where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Projectcontrol",codeProjectcontrollist,propProjectcontrollist,relationProjectcontrollist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
