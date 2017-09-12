
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

	public class EquipmentDao extends EquipmentImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"equipment,maintenance,depricated,accountdebit"};
		protected String []childtabs={"equipment,findasset,maintenance,yearlycost,depricated,accountdebit"};
		protected String []childtabnames={"Equipment Facts,FindAsset,Maintenance,YearlyCost,Depricated,AccountDebit"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","equipment2addequipment","name","description","tagno","expectedlife","purchasecost","currentvalue","status","note","accountgroup","ownercode"};
		protected String [] maincolcaption={"Id","AddEquipment","Name","Description","Tag No","Expected Life(in UM)","Purchase Cost","Current Value","Status","Note","Account Group","Ownership"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","String4000_t","String20_t","Int_t","Money_t","Money_t","Status_t","String2000_t","Code_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,,#text_filter,,,,#select_filter,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","partno","umcode","unituses","pendinguses","totaluses","revenueearned","revenueexpected","totalrevenue"};
		protected String [] summarycolcaption={"Name","Supplier Part No","Um Code","Uses Till Now","Pending Uses","Pending + Used","Revenue Earned","Future Expected Revenue","(Earned+ Expected) Revenue"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","String100_t","Code_t","Int_t","Int_t","Int_t","Money_t","Money_t","Money_t"};
		
		protected String [] reportcol={"objid","name","partno","umcode","expectedlife","unituses","totaluses","purchasecost","currentvalue","totalrevenue"};
		protected String [] reportcolcaption={"Id","Name","Supplier Part No","Um Code","Expected Life(in UM)","Uses Till Now","Pending + Used","Purchase Cost","Current Value","(Earned+ Expected) Revenue"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","String100_t","Code_t","Int_t","Int_t","Int_t","Money_t","Money_t","Money_t"};
		
		protected String [] searchcol={"objid","name","partno","tagno","status"};
		protected String [] searchcolcaption={"Id","Name","Supplier Part No","Tag No","Status"};
		protected String [] searchcoltype={"integer","text","text","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","String100_t","String20_t","Status_t"};

	protected String [] propEquipmentlist={"status"};
		protected String [] codeEquipmentlist={"accountgroup","ownercode"};
		protected String [] relationEquipmentlist={"addequipment:equipment2addequipment:list:"};
		
		protected String [] findassettype={"view"};
		protected String [] findassetcol={"objid","name","availdate","tagno","privlocation"};
		protected String [] findassetcolcaption={"Id","Asset Name","Avail Date","Asset Tag","Priv Location"};
		protected String [] findassetsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] findassetdatadomain={"Int_t","Name_t","Date_t","String50_t","String300_t"};
		protected String [] findassetdisable={"yes,no,no,no,no"};
		protected String [] findassetcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maintenancetype={"table"};
		protected String [] maintenancecol={"objid","name","description","servicetype","category","servicedate","status","totalcost"};
		protected String [] maintenancecolcaption={"Id","Title","Description","Type","Category","Service Date","Status","Total Cost"};
		protected String [] maintenancesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.NUMBER};
		protected String [] maintenancedatadomain={"Int_t","Name_t","String500_t","Type_t","Type_t","Date_t","Status_t","Money_t"};
		protected String [] maintenancedisable={"yes,no,no,no,no,no,no,yes"};
		protected String [] maintenancecolsearch={"#text_filter,#text_filter,,#select_filter,#select_filter,#text_filter,#select_filter,"};
		
		protected String [] yearlycosttype={"view"};
		protected String [] yearlycostcol={"objid","name","description","laborcost","partcost","totoalcost","year"};
		protected String [] yearlycostcolcaption={"Id","Name","Description","Labor Cost","Part Cost","Total Service Cost","Year"};
		protected String [] yearlycostsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER};
		protected String [] yearlycostdatadomain={"Int_t","Name_t","String500_t","Money_t","Money_t","Money_t","Int_t"};
		protected String [] yearlycostdisable={"yes,no,no,no,no,no,no"};
		protected String [] yearlycostcolsearch={"#text_filter,#text_filter,#text_filter,,,,#text_filter"};
		
		protected String [] depricatedtype={"table"};
		protected String [] depricatedcol={"objid","name","yearcode","amount","note"};
		protected String [] depricatedcolcaption={"Id","Title","Year","Amount","Note"};
		protected String [] depricatedsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] depricateddatadomain={"Int_t","Name_t","Code_t","Money_t","String300_t"};
		protected String [] depricateddisable={"yes,no,no,no,no"};
		protected String [] depricatedcolsearch={"#text_filter,#text_filter,#text_filter,,"};
		
		protected String [] accountdebittype={"table"};
		protected String [] accountdebitcol={"objid","accountdebit2account","name","amount","accountgroup","voucher","postdate","note"};
		protected String [] accountdebitcolcaption={"Id","Account","Title","Amount","Account Group","Voucher No","Post Date","Note"};
		protected String [] accountdebitsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] accountdebitdatadomain={"Int_t","Int_t","Name_t","Money_t","Code_t","String20_t","Date_t","String300_t"};
		protected String [] accountdebitdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] accountdebitcolsearch={"#text_filter,#select_filter,#text_filter,,,,#text_filter,"};

		public EquipmentDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Equipment");
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
				if(entity.toLowerCase().contains("<equipment>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<findasset>")){
					this.setFindassetxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getFindassetxml());
					}
				}else if(entity.toLowerCase().contains("<maintenance>")){
					this.setMaintenancexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMaintenancexml());
					}
				}else if(entity.toLowerCase().contains("<yearlycost>")){
					this.setYearlycostxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getYearlycostxml());
					}
				}else if(entity.toLowerCase().contains("<depricated>")){
					this.setDepricatedxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getDepricatedxml());
					}
				}else if(entity.toLowerCase().contains("<accountdebit>")){
					this.setAccountdebitxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAccountdebitxml());
					}
				}
			}
		}

		public Rows getFindassetRows(){
			TemplateTable tab=this.doSelectChild("findasset", "findasset2equipment",this.getParentobjid(),findassetcol,findassetsqldatatype,this.FindassetFilter);
			String [] propFindassetlist={};
			String [] codeFindassetlist={};
			String [] relationFindassetlist={};
			Rows rows=tu.getXMLRows(tab, "findasset",codeFindassetlist,propFindassetlist,relationFindassetlist,findassetcolcaption,findassetdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(findassetcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(findassetdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(findassettype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMaintenanceRows(){
			TemplateTable tab=this.doSelectChild("maintenance", "maintenance2equipment",this.getParentobjid(),maintenancecol,maintenancesqldatatype,this.MaintenanceFilter);
			String [] propMaintenancelist={"servicetype","category","status"};
			String [] codeMaintenancelist={};
			String [] relationMaintenancelist={};
			Rows rows=tu.getXMLRows(tab, "maintenance",codeMaintenancelist,propMaintenancelist,relationMaintenancelist,maintenancecolcaption,maintenancedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(maintenancecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(maintenancedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(maintenancetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getYearlycostRows(){
			TemplateTable tab=this.doSelectChild("yearlycost", "yearlycost2equipment",this.getParentobjid(),yearlycostcol,yearlycostsqldatatype,this.YearlycostFilter);
			String [] propYearlycostlist={};
			String [] codeYearlycostlist={};
			String [] relationYearlycostlist={};
			Rows rows=tu.getXMLRows(tab, "yearlycost",codeYearlycostlist,propYearlycostlist,relationYearlycostlist,yearlycostcolcaption,yearlycostdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(yearlycostcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(yearlycostdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(yearlycosttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getDepricatedRows(){
			TemplateTable tab=this.doSelectChild("depricated", "depricated2equipment",this.getParentobjid(),depricatedcol,depricatedsqldatatype,this.DepricatedFilter);
			String [] propDepricatedlist={};
			String [] codeDepricatedlist={"yearcode"};
			String [] relationDepricatedlist={};
			Rows rows=tu.getXMLRows(tab, "depricated",codeDepricatedlist,propDepricatedlist,relationDepricatedlist,depricatedcolcaption,depricateddatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(depricatedcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(depricateddisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(depricatedtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAccountdebitRows(){
			TemplateTable tab=this.doSelectChild("accountdebit", "accountdebit2equipment",this.getParentobjid(),accountdebitcol,accountdebitsqldatatype,this.AccountdebitFilter);
			String [] propAccountdebitlist={};
			String [] codeAccountdebitlist={"accountgroup"};
			String [] relationAccountdebitlist={"account:accountdebit2account:list:"};
			Rows rows=tu.getXMLRows(tab, "accountdebit",codeAccountdebitlist,propAccountdebitlist,relationAccountdebitlist,accountdebitcolcaption,accountdebitdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(accountdebitcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(accountdebitdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(accountdebittype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEquipmentSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.EquipmentFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Equipment");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Equipment", tab, chartcol);
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

		public Rows getEquipmentRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Equipment",codeEquipmentlist,propEquipmentlist,relationEquipmentlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getEquipmentRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Equipment",codeEquipmentlist,propEquipmentlist,relationEquipmentlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getEquipmentRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postEquipmentContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getEquipmentByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and equipment2"+this.getFilters();
			}
			String sql= "select * from table_Equipment where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Equipment",codeEquipmentlist,propEquipmentlist,relationEquipmentlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
