
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

	public class BoqplanDao extends BoqplanImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"boqplan,inspection"};
		protected String []childtabs={"boqplan,material,resourceaudit,inspection"};
		protected String []childtabnames={"Boqplan Facts,Parts,TaskResource,Inspection"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","boqplan2boq","boqplan2milestone","name","title","projectcode","monthcode","yearcode","quantity","qntfinished","status","note"};
		protected String [] maincolcaption={"Id","Boq","MileStone","Name","Project Title","Project Code","Month Code","Year Code","Quantity Planned","Quantity Finished","Status","Note"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Name_t","Name_t","Code_t","Code_t","Code_t","Float_t","Float_t","Status_t","String4000_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,,,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","boqqnt","qntinspect"};
		protected String [] summarycolcaption={"Name","Total Boq Quantity","Quantity Scheduled"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Float_t","Float_t"};
		
		protected String [] reportcol={"objid","name","title","projectcode","monthcode","yearcode","boqqnt","quantity","qntfinished","qntinspect"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Project Code","Month Code","Year Code","Total Boq Quantity","Quantity Planned","Quantity Finished","Quantity Scheduled"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Code_t","Float_t","Float_t","Float_t","Float_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","monthcode","yearcode","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Month Code","Year Code","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Code_t","Status_t"};

	protected String [] propBoqplanlist={"status"};
		protected String [] codeBoqplanlist={"projectcode","monthcode","yearcode"};
		protected String [] relationBoqplanlist={"boq:boqplan2boq:list:","milestone:boqplan2milestone:list:"};
		
		protected String [] materialtype={"view"};
		protected String [] materialcol={"objid","name","partno","unitcount","qntrequest","qntused","qntpurchased","umcode","note"};
		protected String [] materialcolcaption={"Id","Name","Supplier Model No","Quantity Estimated","Quantity Request","Quantity Used","Purchased","Um Code","Uses"};
		protected String [] materialsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] materialdatadomain={"Int_t","Name_t","String100_t","Int_t","Int_t","Int_t","Int_t","Code_t","String4000_t"};
		protected String [] materialdisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] materialcolsearch={"#text_filter,#text_filter,,,,,,#select_filter,"};
		
		protected String [] resourceaudittype={"view"};
		protected String [] resourceauditcol={"objid","name","taskcode","resourcecode","estunit","actualunit","umcode","rate","origincode"};
		protected String [] resourceauditcolcaption={"Id","Name","Task Code","Resource Code","Est. Unit","Unit Used","Um Code","Rate","Origin"};
		protected String [] resourceauditsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] resourceauditdatadomain={"Int_t","Name_t","Code_t","Code_t","Double_t","Double_t","Code_t","Money_t","Code_t"};
		protected String [] resourceauditdisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] resourceauditcolsearch={"#text_filter,#text_filter,#select_filter,#select_filter,,,#select_filter,,#select_filter"};
		
		protected String [] inspectiontype={"table"};
		protected String [] inspectioncol={"objid","name","note","inspectdate","qntinspected","status"};
		protected String [] inspectioncolcaption={"Id","Name","Note","Inspection Date","Quantity","Status"};
		protected String [] inspectionsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.INTEGER,DataType.VARCHAR};
		protected String [] inspectiondatadomain={"Int_t","Name_t","String4000_t","Date_t","Int_t","Status_t"};
		protected String [] inspectiondisable={"yes,no,no,no,no,no"};
		protected String [] inspectioncolsearch={"#text_filter,#text_filter,,#text_filter,,#select_filter"};

		public BoqplanDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Boqplan");
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
				if(entity.toLowerCase().contains("<boqplan>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<material>")){
					this.setMaterialxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMaterialxml());
					}
				}else if(entity.toLowerCase().contains("<resourceaudit>")){
					this.setResourceauditxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getResourceauditxml());
					}
				}else if(entity.toLowerCase().contains("<inspection>")){
					this.setInspectionxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getInspectionxml());
					}
				}
			}
		}

		public Rows getMaterialRows(){
			TemplateTable tab=this.doSelectChild("material", "material2boqplan",this.getParentobjid(),materialcol,materialsqldatatype,this.MaterialFilter);
			String [] propMateriallist={};
			String [] codeMateriallist={"umcode"};
			String [] relationMateriallist={};
			Rows rows=tu.getXMLRows(tab, "material",codeMateriallist,propMateriallist,relationMateriallist,materialcolcaption,materialdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(materialcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(materialdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(materialtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getResourceauditRows(){
			TemplateTable tab=this.doSelectChild("resourceaudit", "resourceaudit2boqplan",this.getParentobjid(),resourceauditcol,resourceauditsqldatatype,this.ResourceauditFilter);
			String [] propResourceauditlist={};
			String [] codeResourceauditlist={"taskcode","resourcecode","umcode","origincode"};
			String [] relationResourceauditlist={};
			Rows rows=tu.getXMLRows(tab, "resourceaudit",codeResourceauditlist,propResourceauditlist,relationResourceauditlist,resourceauditcolcaption,resourceauditdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(resourceauditcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(resourceauditdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(resourceaudittype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getInspectionRows(){
			TemplateTable tab=this.doSelectChild("inspection", "inspection2boqplan",this.getParentobjid(),inspectioncol,inspectionsqldatatype,this.InspectionFilter);
			String [] propInspectionlist={"status"};
			String [] codeInspectionlist={};
			String [] relationInspectionlist={};
			Rows rows=tu.getXMLRows(tab, "inspection",codeInspectionlist,propInspectionlist,relationInspectionlist,inspectioncolcaption,inspectiondatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(inspectioncolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(inspectiondisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(inspectiontype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBoqplanSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BoqplanFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Boqplan");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Boqplan", tab, chartcol);
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

		public Rows getBoqplanRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Boqplan",codeBoqplanlist,propBoqplanlist,relationBoqplanlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getBoqplanRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Boqplan",codeBoqplanlist,propBoqplanlist,relationBoqplanlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getBoqplanRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postBoqplanContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBoqplanByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and boqplan2"+this.getFilters();
			}
			String sql= "select * from table_Boqplan where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Boqplan",codeBoqplanlist,propBoqplanlist,relationBoqplanlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
