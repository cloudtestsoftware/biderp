
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

	public class WeeklyleadDao extends WeeklyleadImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={""};
		protected String []childtabs={"weekleadcontact,weekleadjobs"};
		protected String []childtabnames={"Contact,Jobs"};
		
		protected String [] maintype={"view"};
		protected String [] maincol={"objid","name","description","criteria","listdate","closedate","bgtrangecode","startcode","zipcode","address","state","contactname","phone","instruction"};
		protected String [] maincolcaption={"Id","Name","Job Description","Selection Criteria","List Date","Bid Close Date","Budget","Starts(Week)","ZipCode","Address","State","Owner Name","Owner Phone","Instruction"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Name_t","String4000_t","String4000_t","Date_t","Date_t","Code_t","Code_t","ZipCode_t","String200_t","AreaCode_t","Name_t","Phone_t","String4000_t"};
		protected String [] maincolsearch={"#text_filter,,,,#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,yes,yes,yes,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","description","criteria","listdate","closedate","bgtrangecode","zipcode","address","state"};
		protected String [] reportcolcaption={"Id","Name","Job Description","Selection Criteria","List Date","Bid Close Date","Budget","ZipCode","Address","State"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String4000_t","String4000_t","Date_t","Date_t","Code_t","ZipCode_t","String200_t","AreaCode_t"};
		
		protected String [] searchcol={"objid","listdate","closedate","zipcode","address","state"};
		protected String [] searchcolcaption={"Id","List Date","Bid Close Date","ZipCode","Address","State"};
		protected String [] searchcoltype={"integer","date","date","integer","text","text"};
		protected String [] searchdatadomain={"Id_t","Date_t","Date_t","ZipCode_t","String200_t","AreaCode_t"};

	protected String [] propWeeklyleadlist={};
		protected String [] codeWeeklyleadlist={"bgtrangecode","startcode"};
		protected String [] relationWeeklyleadlist={};
		
		protected String [] weekleadcontacttype={"view"};
		protected String [] weekleadcontactcol={"objid","name","street","phone","fax","email","type"};
		protected String [] weekleadcontactcolcaption={"Id","Name","Street","Phone","Fax","Email","Type"};
		protected String [] weekleadcontactsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] weekleadcontactdatadomain={"Int_t","Name_t","String200_t","Phone_t","Phone_t","Email_t","Type_t"};
		protected String [] weekleadcontactdisable={"yes,no,no,no,no,no,no"};
		protected String [] weekleadcontactcolsearch={"#text_filter,#text_filter,,,,,"};
		
		protected String [] weekleadjobstype={"view"};
		protected String [] weekleadjobscol={"objid","name","description","unitprice","unitcount","umcode","total"};
		protected String [] weekleadjobscolcaption={"Id","Title","Description","Unit Price","Quantity","Um Code","Total"};
		protected String [] weekleadjobssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER};
		protected String [] weekleadjobsdatadomain={"Int_t","Name_t","String4000_t","Money_t","Int_t","Code_t","Money_t"};
		protected String [] weekleadjobsdisable={"yes,no,no,no,no,no,yes"};
		protected String [] weekleadjobscolsearch={"#text_filter,#text_filter,,,,#select_filter,"};

		public WeeklyleadDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Weeklylead");
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
				if(entity.toLowerCase().contains("<weeklylead>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<weekleadcontact>")){
					this.setWeekleadcontactxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getWeekleadcontactxml());
					}
				}else if(entity.toLowerCase().contains("<weekleadjobs>")){
					this.setWeekleadjobsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getWeekleadjobsxml());
					}
				}
			}
		}

		public Rows getWeekleadcontactRows(){
			TemplateTable tab=this.doSelectChild("weekleadcontact", "weekleadcontact2weeklylead",this.getParentobjid(),weekleadcontactcol,weekleadcontactsqldatatype,this.WeekleadcontactFilter);
			String [] propWeekleadcontactlist={"type"};
			String [] codeWeekleadcontactlist={};
			String [] relationWeekleadcontactlist={};
			Rows rows=tu.getXMLRows(tab, "weekleadcontact",codeWeekleadcontactlist,propWeekleadcontactlist,relationWeekleadcontactlist,weekleadcontactcolcaption,weekleadcontactdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(weekleadcontactcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(weekleadcontactdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(weekleadcontacttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getWeekleadjobsRows(){
			TemplateTable tab=this.doSelectChild("weekleadjobs", "weekleadjobs2weeklylead",this.getParentobjid(),weekleadjobscol,weekleadjobssqldatatype,this.WeekleadjobsFilter);
			String [] propWeekleadjobslist={};
			String [] codeWeekleadjobslist={"umcode"};
			String [] relationWeekleadjobslist={};
			Rows rows=tu.getXMLRows(tab, "weekleadjobs",codeWeekleadjobslist,propWeekleadjobslist,relationWeekleadjobslist,weekleadjobscolcaption,weekleadjobsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(weekleadjobscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(weekleadjobsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(weekleadjobstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getWeeklyleadSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.WeeklyleadFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Weeklylead");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Weeklylead", tab, chartcol);
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

		public Rows getWeeklyleadRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Weeklylead",codeWeeklyleadlist,propWeeklyleadlist,relationWeeklyleadlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getWeeklyleadRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Weeklylead",codeWeeklyleadlist,propWeeklyleadlist,relationWeeklyleadlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getWeeklyleadRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postWeeklyleadContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getWeeklyleadByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and weeklylead2"+this.getFilters();
			}
			String sql= "select * from table_Weeklylead where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Weeklylead",codeWeeklyleadlist,propWeeklyleadlist,relationWeeklyleadlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
