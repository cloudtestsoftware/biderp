
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

	public class ModuleDao extends ModuleImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"module,moduleobject,submodule"};
		protected String []childtabs={"moduleobject,submodule"};
		protected String []childtabnames={"ModuleObject,SubModule"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","module2privilegegroup","name","moduleindex"};
		protected String [] maincolcaption={"Id","PrivilegeGroup","Module Name","Index"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.INTEGER};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Int_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name"};
		protected String [] reportcolcaption={"Id","Module Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t"};
		
		protected String [] searchcol={"objid","name"};
		protected String [] searchcolcaption={"Id","Module Name"};
		protected String [] searchcoltype={"integer","select"};
		protected String [] searchdatadomain={"Id_t","Name_t"};

	protected String [] propModulelist={"name"};
		protected String [] codeModulelist={};
		protected String [] relationModulelist={"privilegegroup:module2privilegegroup:list:"};
		
		protected String [] moduleobjecttype={"table"};
		protected String [] moduleobjectcol={"objid","moduleobject2object","name","title","objectrole","designuse"};
		protected String [] moduleobjectcolcaption={"Id","Object","Table Name","Module Name","Object Role","Design Use"};
		protected String [] moduleobjectsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] moduleobjectdatadomain={"Int_t","Int_t","Name_t","Name_t","Type_t","Type_t"};
		protected String [] moduleobjectdisable={"yes,no,no,no,no,no"};
		protected String [] moduleobjectcolsearch={"#text_filter,#select_filter,,#text_filter,,"};
		
		protected String [] submoduletype={"table"};
		protected String [] submodulecol={"objid","name","title","scope","submoduleindex"};
		protected String [] submodulecolcaption={"Id","Module Name","Title","Scope","Sub Module Index"};
		protected String [] submodulesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] submoduledatadomain={"Int_t","Name_t","Name_t","Type_t","Int_t"};
		protected String [] submoduledisable={"yes,no,no,no,no"};
		protected String [] submodulecolsearch={"#text_filter,#select_filter,#select_filter,,"};

		public ModuleDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Module");
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
				if(entity.toLowerCase().contains("<module>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<moduleobject>")){
					this.setModuleobjectxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getModuleobjectxml());
					}
				}else if(entity.toLowerCase().contains("<submodule>")){
					this.setSubmodulexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSubmodulexml());
					}
				}
			}
		}

		public Rows getModuleobjectRows(){
			TemplateTable tab=this.doSelectChild("moduleobject", "moduleobject2module",this.getParentobjid(),moduleobjectcol,moduleobjectsqldatatype,this.ModuleobjectFilter);
			String [] propModuleobjectlist={"objectrole","designuse"};
			String [] codeModuleobjectlist={};
			String [] relationModuleobjectlist={"object:moduleobject2object:list:"};
			Rows rows=tu.getXMLRows(tab, "moduleobject",codeModuleobjectlist,propModuleobjectlist,relationModuleobjectlist,moduleobjectcolcaption,moduleobjectdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(moduleobjectcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(moduleobjectdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(moduleobjecttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSubmoduleRows(){
			TemplateTable tab=this.doSelectChild("submodule", "submodule2module",this.getParentobjid(),submodulecol,submodulesqldatatype,this.SubmoduleFilter);
			String [] propSubmodulelist={"name","scope"};
			String [] codeSubmodulelist={};
			String [] relationSubmodulelist={};
			Rows rows=tu.getXMLRows(tab, "submodule",codeSubmodulelist,propSubmodulelist,relationSubmodulelist,submodulecolcaption,submoduledatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(submodulecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(submoduledisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(submoduletype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getModuleSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ModuleFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Module");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Module", tab, chartcol);
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

		public Rows getModuleRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Module",codeModulelist,propModulelist,relationModulelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getModuleRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Module",codeModulelist,propModulelist,relationModulelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getModuleRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postModuleContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getModuleByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and module2"+this.getFilters();
			}
			String sql= "select * from table_Module where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Module",codeModulelist,propModulelist,relationModulelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
