
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

	public class SiteDao extends SiteImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"site,phase,warehouse,assetplan,asset"};
		protected String []childtabs={"site,phase,warehouse,machinary,assetplan,asset"};
		protected String []childtabnames={"Site Facts,Phase,Warehouse,Machinary,AssetPlan,Asset"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","site2messagequeue","name","description","startdate","completedate","address","city","zipcode","state","siteincharge","phone","phone2","fax","email","othercontact","otherinfo","status"};
		protected String [] maincolcaption={"Id","MessageQueue","Site Name","Site Description","Start Date","Complete Date","Site Address","City","Site Zip Code","State(like CA,..)","Site  Incharge","Phone","Mobile/Other No","Fax","Contact Email","Additional Contact","Additional Info","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","String4000_t","Date_t","Date_t","String400_t","String50_t","ZipCode_t","StateCode_t","String100_t","Phone_t","Phone_t","Phone_t","Email_t","String1000_t","String4000_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,,,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,,,,,,,,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","total"};
		protected String [] summarycolcaption={"Name","Total"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","startdate","completedate","address","phone","phone2"};
		protected String [] reportcolcaption={"Id","Site Name","Start Date","Complete Date","Site Address","Phone","Mobile/Other No"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Date_t","Date_t","String400_t","Phone_t","Phone_t"};
		
		protected String [] searchcol={"objid","startdate","completedate","city","zipcode","state","status","total"};
		protected String [] searchcolcaption={"Id","Start Date","Complete Date","City","Site Zip Code","State(like CA,..)","Status","Total"};
		protected String [] searchcoltype={"integer","date","date","text","integer","text","select","currency"};
		protected String [] searchdatadomain={"Id_t","Date_t","Date_t","String50_t","ZipCode_t","StateCode_t","Status_t","Money_t"};

	protected String [] propSitelist={"status"};
		protected String [] codeSitelist={};
		protected String [] relationSitelist={"messagequeue:site2messagequeue:list:"};
		
		protected String [] phasetype={"table"};
		protected String [] phasecol={"objid","name","description","phaseincharge","phone","phone2","email","total"};
		protected String [] phasecolcaption={"Id","Phase Name","Phase Description","Phase  Incharge","Phone","Mobile/Other No","Contact Email","Total"};
		protected String [] phasesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] phasedatadomain={"Int_t","Name_t","String4000_t","String100_t","Phone_t","Phone_t","Email_t","Money_t"};
		protected String [] phasedisable={"yes,no,no,no,no,no,no,yes"};
		protected String [] phasecolsearch={"#text_filter,,,,,,,#numeric_filter"};
		
		protected String [] warehousetype={"table"};
		protected String [] warehousecol={"objid","name","location"};
		protected String [] warehousecolcaption={"Id","Title","Location"};
		protected String [] warehousesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] warehousedatadomain={"Int_t","Name_t","String300_t"};
		protected String [] warehousedisable={"yes,no,no"};
		protected String [] warehousecolsearch={"#text_filter,#text_filter,"};
		
		protected String [] machinarytype={"view"};
		protected String [] machinarycol={"objid","name","sitename","umcode","estunit","qntrequest","startdate","enddate","usefactor"};
		protected String [] machinarycolcaption={"Id","Asset Name","Site Name","Unit Code","Est. Unit","Requested Unit","Start Date","End Date","Use Factor"};
		protected String [] machinarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] machinarydatadomain={"Int_t","Name_t","Name_t","Um_t","Int_t","Int_t","Float_t","Float_t","Float_t"};
		protected String [] machinarydisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] machinarycolsearch={"#text_filter,#text_filter,#text_filter,,,,,,"};
		
		protected String [] assetplantype={"table"};
		protected String [] assetplancol={"objid","assetplan2findasset","assetplan2machinary","name","tagno","privlocation","curlocation","note","status","availdate","startdate","enddate"};
		protected String [] assetplancolcaption={"Id","FindAsset","Machinary","Name","Asset Tag","Privious Location","Current Location","Note","Status","Available Date","Start Date","End Date"};
		protected String [] assetplansqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE};
		protected String [] assetplandatadomain={"Int_t","Int_t","Int_t","Name_t","String50_t","String300_t","String300_t","String500_t","Status_t","Date_t","Date_t","Date_t"};
		protected String [] assetplandisable={"yes,no,no,no,yes,no,no,no,no,yes,no,no"};
		protected String [] assetplancolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,,,,#select_filter,#text_filter,,"};
		
		protected String [] assettype={"table"};
		protected String [] assetcol={"objid","name","location","description","type","purchasecost","assetvalue","purchasedate","assettag","status","accountgroup","ownercode"};
		protected String [] assetcolcaption={"Id","Title","Location","Description","Type","Purchase Cost","Current Value","Purchase Date","Tag","Status","Account Group","Ownership"};
		protected String [] assetsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] assetdatadomain={"Int_t","Name_t","String300_t","String500_t","Type_t","Money_t","Money_t","Date_t","String50_t","Status_t","Code_t","Code_t"};
		protected String [] assetdisable={"yes,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] assetcolsearch={"#text_filter,#text_filter,,,#select_filter,,,#text_filter,#text_filter,,,"};

		public SiteDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Site");
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
				if(entity.toLowerCase().contains("<site>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<phase>")){
					this.setPhasexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPhasexml());
					}
				}else if(entity.toLowerCase().contains("<warehouse>")){
					this.setWarehousexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getWarehousexml());
					}
				}else if(entity.toLowerCase().contains("<machinary>")){
					this.setMachinaryxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMachinaryxml());
					}
				}else if(entity.toLowerCase().contains("<assetplan>")){
					this.setAssetplanxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAssetplanxml());
					}
				}else if(entity.toLowerCase().contains("<asset>")){
					this.setAssetxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAssetxml());
					}
				}
			}
		}

		public Rows getPhaseRows(){
			TemplateTable tab=this.doSelectChild("phase", "phase2site",this.getParentobjid(),phasecol,phasesqldatatype,this.PhaseFilter);
			String [] propPhaselist={};
			String [] codePhaselist={};
			String [] relationPhaselist={};
			Rows rows=tu.getXMLRows(tab, "phase",codePhaselist,propPhaselist,relationPhaselist,phasecolcaption,phasedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(phasecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(phasedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(phasetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getWarehouseRows(){
			TemplateTable tab=this.doSelectChild("warehouse", "warehouse2site",this.getParentobjid(),warehousecol,warehousesqldatatype,this.WarehouseFilter);
			String [] propWarehouselist={};
			String [] codeWarehouselist={};
			String [] relationWarehouselist={};
			Rows rows=tu.getXMLRows(tab, "warehouse",codeWarehouselist,propWarehouselist,relationWarehouselist,warehousecolcaption,warehousedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(warehousecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(warehousedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(warehousetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMachinaryRows(){
			TemplateTable tab=this.doSelectChild("machinary", "machinary2site",this.getParentobjid(),machinarycol,machinarysqldatatype,this.MachinaryFilter);
			String [] propMachinarylist={};
			String [] codeMachinarylist={};
			String [] relationMachinarylist={};
			Rows rows=tu.getXMLRows(tab, "machinary",codeMachinarylist,propMachinarylist,relationMachinarylist,machinarycolcaption,machinarydatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(machinarycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(machinarydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(machinarytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAssetplanRows(){
			TemplateTable tab=this.doSelectChild("assetplan", "assetplan2site",this.getParentobjid(),assetplancol,assetplansqldatatype,this.AssetplanFilter);
			String [] propAssetplanlist={"status"};
			String [] codeAssetplanlist={};
			String [] relationAssetplanlist={"findasset:assetplan2findasset:list:","machinary:assetplan2machinary:list:"};
			Rows rows=tu.getXMLRows(tab, "assetplan",codeAssetplanlist,propAssetplanlist,relationAssetplanlist,assetplancolcaption,assetplandatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(assetplancolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(assetplandisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(assetplantype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAssetRows(){
			TemplateTable tab=this.doSelectChild("asset", "asset2site",this.getParentobjid(),assetcol,assetsqldatatype,this.AssetFilter);
			String [] propAssetlist={"type","status"};
			String [] codeAssetlist={"accountgroup","ownercode"};
			String [] relationAssetlist={};
			Rows rows=tu.getXMLRows(tab, "asset",codeAssetlist,propAssetlist,relationAssetlist,assetcolcaption,assetdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(assetcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(assetdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(assettype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSiteSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.SiteFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Site");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Site", tab, chartcol);
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

		public Rows getSiteRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Site",codeSitelist,propSitelist,relationSitelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getSiteRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Site",codeSitelist,propSitelist,relationSitelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getSiteRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postSiteContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getSiteByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and site2"+this.getFilters();
			}
			String sql= "select * from table_Site where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Site",codeSitelist,propSitelist,relationSitelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
