
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

	public class BoqDao extends BoqImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"boq,parts,taskresource,change,requirement"};
		protected String []childtabs={"boq,parts,taskresource,change,requirement"};
		protected String []childtabnames={"Boq Facts,Parts,Resource,Change,Requirement"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","boq2estimation","name","title","projectcode","maincode","subcode","description","umcode","qntcontract","rate","deptcode","status"};
		protected String [] maincolcaption={"Id","Estimation","Name","Project Title","Project Code","Main Code","Sub Code","Description","Um Code","Quantity Contracted","Rate","Department","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Name_t","Code_t","Code_t","Code_t","String500_t","Code_t","Int_t","Money_t","Code_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter,,#select_filter,,,#select_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","qntchange","qnttotal","actualrate","contracttoactual","contracttoest"};
		protected String [] summarycolcaption={"Name","Quantity Changed","Total Quantity","ActualRate","Contract-Used (Parts+Resource)","Contract-Est. (Parts+Resource)"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Int_t","Int_t","Money_t","Money_t","Money_t"};
		
		protected String [] reportcol={"objid","name","title","projectcode","maincode","subcode","umcode","qntcontract","qnttotal","rate"};
		protected String [] reportcolcaption={"Id","Name","Project Title","Project Code","Main Code","Sub Code","Um Code","Quantity Contracted","Total Quantity","Rate"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Code_t","Code_t","Int_t","Int_t","Money_t"};
		
		protected String [] searchcol={"objid","name","title","projectcode","maincode","subcode","umcode","deptcode","status"};
		protected String [] searchcolcaption={"Id","Name","Project Title","Project Code","Main Code","Sub Code","Um Code","Department","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select","select","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Code_t","Code_t","Code_t","Code_t","Code_t","Status_t"};

	protected String [] propBoqlist={"status"};
		protected String [] codeBoqlist={"projectcode","maincode","subcode","umcode","deptcode"};
		protected String [] relationBoqlist={"estimation:boq2estimation:list:"};
		
		protected String [] partstype={"table"};
		protected String [] partscol={"objid","parts2partprice","name","partcode","partno","description","unitprice","unitcount","umcode","deptcode","status","note"};
		protected String [] partscolcaption={"Id","BoqPart","Name","Part Code","Supplier Part No","Part Spec","Unit Price","Quantity Estimated","Um Code","Department","Status","Note"};
		protected String [] partssqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] partsdatadomain={"Int_t","Int_t","Name_t","String30_t","String100_t","String1000_t","Money_t","Int_t","Code_t","Code_t","Status_t","String4000_t"};
		protected String [] partsdisable={"yes,no,no,yes,yes,yes,yes,no,yes,no,no,no"};
		protected String [] partscolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,,,,,#select_filter,#select_filter,#select_filter,"};
		
		protected String [] taskresourcetype={"table"};
		protected String [] taskresourcecol={"objid","taskresource2partprice","name","taskcode","startdate","enddate","resourcecode","estunit","qntrequest","actualunit","qntbalance","umcode","rate","actualrate","origincode","pocode","deptcode"};
		protected String [] taskresourcecolcaption={"Id","Resources","Detail","Task Code","Start Date","End Date","Resource Code","Est. Unit","Quantity Requested","Actual Resource","Requested-Used","Um Code","Suggested Rate","Actual Rate","Origin","Executed by","Department"};
		protected String [] taskresourcesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] taskresourcedatadomain={"Int_t","Int_t","String300_t","Code_t","Date_t","Date_t","Code_t","Double_t","Double_t","Double_t","Int_t","Code_t","Money_t","Money_t","Code_t","Code_t","Code_t"};
		protected String [] taskresourcedisable={"yes,no,no,no,no,no,no,no,no,yes,yes,no,yes,no,no,no,no"};
		protected String [] taskresourcecolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,,,,,,,,#select_filter,#select_filter,#select_filter"};
		
		protected String [] changetype={"table"};
		protected String [] changecol={"objid","name","severitycode","status","qntchange","estcost","actualcost","changedetail","umcode","issuedby"};
		protected String [] changecolcaption={"Id","Name","Severity","Status","Boq Quantity","Estimated Cost","Actual Cost","Change Detail","Um Code","Issued By"};
		protected String [] changesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] changedatadomain={"Int_t","Name_t","Code_t","Status_t","Int_t","Money_t","Money_t","String4000_t","Code_t","Name_t"};
		protected String [] changedisable={"yes,no,no,no,no,yes,yes,no,yes,no"};
		protected String [] changecolsearch={"#text_filter,#text_filter,#select_filter,#select_filter,,,,,,"};
		
		protected String [] requirementtype={"table"};
		protected String [] requirementcol={"objid","name","detail","status"};
		protected String [] requirementcolcaption={"Id","Name","Requirements","Status"};
		protected String [] requirementsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] requirementdatadomain={"Int_t","Name_t","String4000_t","Status_t"};
		protected String [] requirementdisable={"yes,no,no,no"};
		protected String [] requirementcolsearch={"#text_filter,#text_filter,,#select_filter"};

		public BoqDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Boq");
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
				if(entity.toLowerCase().contains("<boq>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<parts>")){
					this.setPartsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPartsxml());
					}
				}else if(entity.toLowerCase().contains("<taskresource>")){
					this.setTaskresourcexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTaskresourcexml());
					}
				}else if(entity.toLowerCase().contains("<change>")){
					this.setChangexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getChangexml());
					}
				}else if(entity.toLowerCase().contains("<requirement>")){
					this.setRequirementxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getRequirementxml());
					}
				}
			}
		}

		public Rows getPartsRows(){
			TemplateTable tab=this.doSelectChild("parts", "parts2boq",this.getParentobjid(),partscol,partssqldatatype,this.PartsFilter);
			String [] propPartslist={"status"};
			String [] codePartslist={"umcode","deptcode"};
			String [] relationPartslist={"boqpart:parts2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "parts",codePartslist,propPartslist,relationPartslist,partscolcaption,partsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(partscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(partsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(partstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTaskresourceRows(){
			TemplateTable tab=this.doSelectChild("taskresource", "taskresource2boq",this.getParentobjid(),taskresourcecol,taskresourcesqldatatype,this.TaskresourceFilter);
			String [] propTaskresourcelist={};
			String [] codeTaskresourcelist={"taskcode","resourcecode","umcode","origincode","pocode","deptcode"};
			String [] relationTaskresourcelist={"resources:taskresource2partprice:list:"};
			Rows rows=tu.getXMLRows(tab, "taskresource",codeTaskresourcelist,propTaskresourcelist,relationTaskresourcelist,taskresourcecolcaption,taskresourcedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(taskresourcecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(taskresourcedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(taskresourcetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getChangeRows(){
			TemplateTable tab=this.doSelectChild("change", "change2boq",this.getParentobjid(),changecol,changesqldatatype,this.ChangeFilter);
			String [] propChangelist={"status"};
			String [] codeChangelist={"severitycode","umcode"};
			String [] relationChangelist={};
			Rows rows=tu.getXMLRows(tab, "change",codeChangelist,propChangelist,relationChangelist,changecolcaption,changedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(changecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(changedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(changetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getRequirementRows(){
			TemplateTable tab=this.doSelectChild("requirement", "requirement2boq",this.getParentobjid(),requirementcol,requirementsqldatatype,this.RequirementFilter);
			String [] propRequirementlist={"status"};
			String [] codeRequirementlist={};
			String [] relationRequirementlist={};
			Rows rows=tu.getXMLRows(tab, "requirement",codeRequirementlist,propRequirementlist,relationRequirementlist,requirementcolcaption,requirementdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(requirementcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(requirementdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(requirementtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBoqSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BoqFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Boq");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Boq", tab, chartcol);
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

		public Rows getBoqRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Boq",codeBoqlist,propBoqlist,relationBoqlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getBoqRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Boq",codeBoqlist,propBoqlist,relationBoqlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getBoqRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postBoqContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBoqByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and boq2"+this.getFilters();
			}
			String sql= "select * from table_Boq where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Boq",codeBoqlist,propBoqlist,relationBoqlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
