
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

	public class SupplierDao extends SupplierImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"supplier,bizprofile,contact,jobhistory,user"};
		protected String []childtabs={"bizprofile,contact,jobhistory,user"};
		protected String []childtabnames={"Biz Profile,Contact,Job History,User"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","bizcode","email","phone"};
		protected String [] maincolcaption={"Id","Name","Business Type","Login Email","Contact No"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Name_t","Code_t","String100_t","Phone_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,#select_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","bizcode","email","phone"};
		protected String [] reportcolcaption={"Id","Name","Business Type","Login Email","Contact No"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Code_t","String100_t","Phone_t"};
		
		protected String [] searchcol={"objid","name","bizcode","email","phone"};
		protected String [] searchcolcaption={"Id","Name","Business Type","Login Email","Contact No"};
		protected String [] searchcoltype={"integer","text","select","text","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","String100_t","Phone_t"};

	protected String [] propSupplierlist={};
		protected String [] codeSupplierlist={"bizcode"};
		protected String [] relationSupplierlist={};
		
		protected String [] bizprofiletype={"table"};
		protected String [] bizprofilecol={"objid","bizprofile2bizdomain","name","rating","years"};
		protected String [] bizprofilecolcaption={"Id","BizDomain","Description","Rating (%)","No Of Years"};
		protected String [] bizprofilesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER};
		protected String [] bizprofiledatadomain={"Int_t","Int_t","String300_t","Percent_t","Float_t"};
		protected String [] bizprofiledisable={"yes,no,no,no,no"};
		protected String [] bizprofilecolsearch={"#text_filter,#select_filter,#text_filter,#numeric_filter,#numeric_filter"};
		
		protected String [] contacttype={"table"};
		protected String [] contactcol={"objid","name","lastname","street","city","state","zipcode","countrycode","phone","phone2","contactcode"};
		protected String [] contactcolcaption={"Id","Name","Other/Last Name","Street","City","State","Zip Code","Country","Home Phone","Office Phone","Type"};
		protected String [] contactsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] contactdatadomain={"Int_t","Name_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Phone_t","Phone_t","Code_t"};
		protected String [] contactdisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		protected String [] contactcolsearch={"#text_filter,#text_filter,#text_filter,,,,,#text_filter,,,"};
		
		protected String [] jobhistorytype={"table"};
		protected String [] jobhistorycol={"objid","jobhistory2bizdomain","name","description","startdate","duration","remark","rating"};
		protected String [] jobhistorycolcaption={"Id","BizDomain","Project Name","Work Detail","Start Date","No Of Months","Remark","Rating (%)"};
		protected String [] jobhistorysqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER};
		protected String [] jobhistorydatadomain={"Int_t","Int_t","String200_t","String4000_t","Date_t","Int_t","String4000_t","Percent_t"};
		protected String [] jobhistorydisable={"yes,no,no,no,no,no,no,no"};
		protected String [] jobhistorycolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#numeric_filter"};
		
		protected String [] usertype={"table"};
		protected String [] usercol={"objid","user2company","user2privilegegroup","name","lastname","loginname","status","usertype","email"};
		protected String [] usercolcaption={"Id","Company","PrivilegeGroup","Name","Last Name","Login Name","Status","User Type","Email"};
		protected String [] usersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] userdatadomain={"Int_t","Int_t","Int_t","Name_t","Name_t","Name_t","Status_t","Type_t","Email_t"};
		protected String [] userdisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] usercolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,,,,,"};

		public SupplierDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Supplier");
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
				if(entity.toLowerCase().contains("<supplier>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<bizprofile>")){
					this.setBizprofilexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBizprofilexml());
					}
				}else if(entity.toLowerCase().contains("<contact>")){
					this.setContactxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getContactxml());
					}
				}else if(entity.toLowerCase().contains("<jobhistory>")){
					this.setJobhistoryxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getJobhistoryxml());
					}
				}else if(entity.toLowerCase().contains("<user>")){
					this.setUserxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getUserxml());
					}
				}
			}
		}

		public Rows getBizprofileRows(){
			TemplateTable tab=this.doSelectChild("bizprofile", "bizprofile2supplier",this.getParentobjid(),bizprofilecol,bizprofilesqldatatype,this.BizprofileFilter);
			String [] propBizprofilelist={};
			String [] codeBizprofilelist={};
			String [] relationBizprofilelist={"bizdomain:bizprofile2bizdomain:list:"};
			Rows rows=tu.getXMLRows(tab, "bizprofile",codeBizprofilelist,propBizprofilelist,relationBizprofilelist,bizprofilecolcaption,bizprofiledatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bizprofilecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bizprofiledisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bizprofiletype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getContactRows(){
			TemplateTable tab=this.doSelectChild("contact", "contact2supplier",this.getParentobjid(),contactcol,contactsqldatatype,this.ContactFilter);
			String [] propContactlist={};
			String [] codeContactlist={"countrycode","contactcode"};
			String [] relationContactlist={};
			Rows rows=tu.getXMLRows(tab, "contact",codeContactlist,propContactlist,relationContactlist,contactcolcaption,contactdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(contactcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(contactdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(contacttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getJobhistoryRows(){
			TemplateTable tab=this.doSelectChild("jobhistory", "jobhistory2supplier",this.getParentobjid(),jobhistorycol,jobhistorysqldatatype,this.JobhistoryFilter);
			String [] propJobhistorylist={};
			String [] codeJobhistorylist={};
			String [] relationJobhistorylist={"bizdomain:jobhistory2bizdomain:list:"};
			Rows rows=tu.getXMLRows(tab, "jobhistory",codeJobhistorylist,propJobhistorylist,relationJobhistorylist,jobhistorycolcaption,jobhistorydatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(jobhistorycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(jobhistorydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(jobhistorytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getUserRows(){
			TemplateTable tab=this.doSelectChild("user", "user2supplier",this.getParentobjid(),usercol,usersqldatatype,this.UserFilter);
			String [] propUserlist={"status","usertype"};
			String [] codeUserlist={};
			String [] relationUserlist={"company:user2company:list:","privilegegroup:user2privilegegroup:list:"};
			Rows rows=tu.getXMLRows(tab, "user",codeUserlist,propUserlist,relationUserlist,usercolcaption,userdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(usercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(userdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(usertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSupplierSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.SupplierFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Supplier");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Supplier", tab, chartcol);
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

		public Rows getSupplierRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Supplier",codeSupplierlist,propSupplierlist,relationSupplierlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getSupplierRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Supplier",codeSupplierlist,propSupplierlist,relationSupplierlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getSupplierRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postSupplierContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getSupplierByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and supplier2"+this.getFilters();
			}
			String sql= "select * from table_Supplier where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Supplier",codeSupplierlist,propSupplierlist,relationSupplierlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
