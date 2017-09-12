
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

	public class ProjectleadDao extends ProjectleadImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={""};
		protected String []childtabs={"leadcontact,leadjobs"};
		protected String []childtabnames={"Contact,Jobs"};
		
		protected String [] maintype={"view"};
		protected String [] maincol={"objid","name","description","criteria","listdate","closedate","bgtrangecode","startcode","zipcode","address","state","contactname","phone","instruction"};
		protected String [] maincolcaption={"Id","Name","Job Description","Selection Criteria","List Date","Bid Close Date","Budget","Starts(Week)","Zip Code","Address","State","Owner Name","Owner Phone","Instruction"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Name_t","String4000_t","String4000_t","Date_t","Date_t","Code_t","Code_t","ZipCode_t","String200_t","AreaCode_t","Name_t","Phone_t","String4000_t"};
		protected String [] maincolsearch={"#text_filter,,,,#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,yes,yes,yes,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","description","criteria","listdate","closedate","bgtrangecode","zipcode","state"};
		protected String [] reportcolcaption={"Id","Name","Job Description","Selection Criteria","List Date","Bid Close Date","Budget","Zip Code","State"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String4000_t","String4000_t","Date_t","Date_t","Code_t","ZipCode_t","AreaCode_t"};
		
		protected String [] searchcol={"objid","listdate","closedate","zipcode","address","state"};
		protected String [] searchcolcaption={"Id","List Date","Bid Close Date","Zip Code","Address","State"};
		protected String [] searchcoltype={"integer","date","date","integer","text","text"};
		protected String [] searchdatadomain={"Id_t","Date_t","Date_t","ZipCode_t","String200_t","AreaCode_t"};

	protected String [] propProjectleadlist={};
		protected String [] codeProjectleadlist={"bgtrangecode","startcode"};
		protected String [] relationProjectleadlist={};
		
		protected String [] leadcontacttype={"view"};
		protected String [] leadcontactcol={"objid","name","street","phone","state","zipcode"};
		protected String [] leadcontactcolcaption={"Id","Name","Street","Phone","State","ZipCode"};
		protected String [] leadcontactsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] leadcontactdatadomain={"Int_t","Name_t","String200_t","Phone_t","String10_t","Code_t"};
		protected String [] leadcontactdisable={"yes,no,no,no,no,no"};
		protected String [] leadcontactcolsearch={"#text_filter,#text_filter,,,,"};
		
		protected String [] leadjobstype={"view"};
		protected String [] leadjobscol={"objid","name","description","unitprice","unitcount","umcode","total"};
		protected String [] leadjobscolcaption={"Id","Title","Description","Unit Price","Quantity","Um Code","Total"};
		protected String [] leadjobssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER};
		protected String [] leadjobsdatadomain={"Int_t","Name_t","String4000_t","Money_t","Int_t","Code_t","Money_t"};
		protected String [] leadjobsdisable={"yes,no,no,no,no,no,yes"};
		protected String [] leadjobscolsearch={"#text_filter,#text_filter,,,,#select_filter,"};

		public ProjectleadDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Projectlead");
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
				if(entity.toLowerCase().contains("<projectlead>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<leadcontact>")){
					this.setLeadcontactxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getLeadcontactxml());
					}
				}else if(entity.toLowerCase().contains("<leadjobs>")){
					this.setLeadjobsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getLeadjobsxml());
					}
				}
			}
		}

		public Rows getLeadcontactRows(){
			TemplateTable tab=this.doSelectChild("leadcontact", "leadcontact2projectlead",this.getParentobjid(),leadcontactcol,leadcontactsqldatatype,this.LeadcontactFilter);
			String [] propLeadcontactlist={};
			String [] codeLeadcontactlist={};
			String [] relationLeadcontactlist={};
			Rows rows=tu.getXMLRows(tab, "leadcontact",codeLeadcontactlist,propLeadcontactlist,relationLeadcontactlist,leadcontactcolcaption,leadcontactdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(leadcontactcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(leadcontactdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(leadcontacttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getLeadjobsRows(){
			TemplateTable tab=this.doSelectChild("leadjobs", "leadjobs2projectlead",this.getParentobjid(),leadjobscol,leadjobssqldatatype,this.LeadjobsFilter);
			String [] propLeadjobslist={};
			String [] codeLeadjobslist={"umcode"};
			String [] relationLeadjobslist={};
			Rows rows=tu.getXMLRows(tab, "leadjobs",codeLeadjobslist,propLeadjobslist,relationLeadjobslist,leadjobscolcaption,leadjobsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(leadjobscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(leadjobsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(leadjobstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getProjectleadSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ProjectleadFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Projectlead");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Projectlead", tab, chartcol);
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

		public Rows getProjectleadRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Projectlead",codeProjectleadlist,propProjectleadlist,relationProjectleadlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getProjectleadRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Projectlead",codeProjectleadlist,propProjectleadlist,relationProjectleadlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getProjectleadRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postProjectleadContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getProjectleadByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and projectlead2"+this.getFilters();
			}
			String sql= "select * from table_Projectlead where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Projectlead",codeProjectleadlist,propProjectleadlist,relationProjectleadlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
