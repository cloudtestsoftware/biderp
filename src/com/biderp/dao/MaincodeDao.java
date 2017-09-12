
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

	public class MaincodeDao extends MaincodeImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"maincode,subcode"};
		protected String []childtabs={"subcode"};
		protected String []childtabnames={"SubCode"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","maincode2projectcode","name","projectcode","mainjobcode","deptcode","umcode","description","status","approvedby"};
		protected String [] maincolcaption={"Id","ProjectCode","Name","Project Code","Main Code","Dept Code","UM Code","Description","Status","Approved By"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Code_t","Int_t","Code_t","Code_t","Description_t","Status_t","Name_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,,#select_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,yes,yes,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","mainjobcode","deptcode","umcode","status","approvedby"};
		protected String [] reportcolcaption={"Id","Name","Main Code","Dept Code","UM Code","Status","Approved By"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Int_t","Code_t","Code_t","Status_t","Name_t"};
		
		protected String [] searchcol={"objid","name","projectcode","mainjobcode","deptcode","umcode","status","approvedby"};
		protected String [] searchcolcaption={"Id","Name","Project Code","Main Code","Dept Code","UM Code","Status","Approved By"};
		protected String [] searchcoltype={"integer","text","text","integer","select","select","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","Int_t","Code_t","Code_t","Status_t","Name_t"};

	protected String [] propMaincodelist={"status"};
		protected String [] codeMaincodelist={"projectcode","deptcode","umcode"};
		protected String [] relationMaincodelist={"projectcode:maincode2projectcode:list:"};
		
		protected String [] subcodetype={"table"};
		protected String [] subcodecol={"objid","name","subjobcode","umcode","unitrate","description","status"};
		protected String [] subcodecolcaption={"Id","Name","Sub Code","UM Code","Unit Rate","Description","Status"};
		protected String [] subcodesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] subcodedatadomain={"Int_t","Name_t","Int_t","Code_t","Money_t","String500_t","Status_t"};
		protected String [] subcodedisable={"yes,no,yes,no,no,no,no"};
		protected String [] subcodecolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,,#select_filter"};

		public MaincodeDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Maincode");
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
				if(entity.toLowerCase().contains("<maincode>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<subcode>")){
					this.setSubcodexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSubcodexml());
					}
				}
			}
		}

		public Rows getSubcodeRows(){
			TemplateTable tab=this.doSelectChild("subcode", "subcode2maincode",this.getParentobjid(),subcodecol,subcodesqldatatype,this.SubcodeFilter);
			String [] propSubcodelist={"status"};
			String [] codeSubcodelist={"umcode"};
			String [] relationSubcodelist={};
			Rows rows=tu.getXMLRows(tab, "subcode",codeSubcodelist,propSubcodelist,relationSubcodelist,subcodecolcaption,subcodedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(subcodecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(subcodedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(subcodetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMaincodeSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.MaincodeFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Maincode");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Maincode", tab, chartcol);
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

		public Rows getMaincodeRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Maincode",codeMaincodelist,propMaincodelist,relationMaincodelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getMaincodeRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Maincode",codeMaincodelist,propMaincodelist,relationMaincodelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getMaincodeRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postMaincodeContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getMaincodeByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and maincode2"+this.getFilters();
			}
			String sql= "select * from table_Maincode where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Maincode",codeMaincodelist,propMaincodelist,relationMaincodelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
