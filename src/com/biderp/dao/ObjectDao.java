
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

	public class ObjectDao extends ObjectImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"object,attribute,help,objectrule"};
		protected String []childtabs={"attribute,help,objectrule"};
		protected String []childtabnames={"Attribute,Help,ObjectRule"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","object2application","name","entityaccesstype","useraccesstype","defaultfilter"};
		protected String [] maincolcaption={"Id","Application","Object Name","Entity Access Type","User Access Type","Default Filter"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Type_t","Type_t","String300_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#select_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","entityaccesstype","useraccesstype","defaultfilter"};
		protected String [] reportcolcaption={"Id","Object Name","Entity Access Type","User Access Type","Default Filter"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Type_t","Type_t","String300_t"};
		
		protected String [] searchcol={"objid","name","entityaccesstype","useraccesstype","defaultfilter"};
		protected String [] searchcolcaption={"Id","Object Name","Entity Access Type","User Access Type","Default Filter"};
		protected String [] searchcoltype={"integer","text","select","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Type_t","Type_t","String300_t"};

	protected String [] propObjectlist={"entityaccesstype","useraccesstype"};
		protected String [] codeObjectlist={};
		protected String [] relationObjectlist={"application:object2application:list:"};
		
		protected String [] attributetype={"table"};
		protected String [] attributecol={"objid","name","tablename","hasproperty"};
		protected String [] attributecolcaption={"Id","Attribute Name","Table Name","Has Property"};
		protected String [] attributesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] attributedatadomain={"Int_t","Name_t","Name_t","Type_t"};
		protected String [] attributedisable={"yes,no,no,no"};
		protected String [] attributecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] helptype={"table"};
		protected String [] helpcol={"objid","name","instruction","helpindex"};
		protected String [] helpcolcaption={"Id","Title","Instruction","Index"};
		protected String [] helpsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] helpdatadomain={"Int_t","Name_t","String4000_t","Int_t"};
		protected String [] helpdisable={"yes,no,no,no"};
		protected String [] helpcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] objectruletype={"table"};
		protected String [] objectrulecol={"objid","name","tablename","effectedtable","reason","actionstate","condition","ruleindex","status"};
		protected String [] objectrulecolcaption={"Id","Rule Name","Table Name","Effected Table","Reason","Action State","Condition ($)","Rule Index","Status"};
		protected String [] objectrulesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR};
		protected String [] objectruledatadomain={"Int_t","Name_t","Name_t","Name_t","Type_t","Type_t","String500_t","Int_t","Status_t"};
		protected String [] objectruledisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] objectrulecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter"};

		public ObjectDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Object");
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
				if(entity.toLowerCase().contains("<object>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<attribute>")){
					this.setAttributexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAttributexml());
					}
				}else if(entity.toLowerCase().contains("<help>")){
					this.setHelpxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getHelpxml());
					}
				}else if(entity.toLowerCase().contains("<objectrule>")){
					this.setObjectrulexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getObjectrulexml());
					}
				}
			}
		}

		public Rows getAttributeRows(){
			TemplateTable tab=this.doSelectChild("attribute", "attribute2object",this.getParentobjid(),attributecol,attributesqldatatype,this.AttributeFilter);
			String [] propAttributelist={"hasproperty"};
			String [] codeAttributelist={};
			String [] relationAttributelist={};
			Rows rows=tu.getXMLRows(tab, "attribute",codeAttributelist,propAttributelist,relationAttributelist,attributecolcaption,attributedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(attributecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(attributedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(attributetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getHelpRows(){
			TemplateTable tab=this.doSelectChild("help", "help2object",this.getParentobjid(),helpcol,helpsqldatatype,this.HelpFilter);
			String [] propHelplist={};
			String [] codeHelplist={};
			String [] relationHelplist={};
			Rows rows=tu.getXMLRows(tab, "help",codeHelplist,propHelplist,relationHelplist,helpcolcaption,helpdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(helpcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(helpdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(helptype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getObjectruleRows(){
			TemplateTable tab=this.doSelectChild("objectrule", "objectrule2object",this.getParentobjid(),objectrulecol,objectrulesqldatatype,this.ObjectruleFilter);
			String [] propObjectrulelist={"reason","actionstate","status"};
			String [] codeObjectrulelist={};
			String [] relationObjectrulelist={};
			Rows rows=tu.getXMLRows(tab, "objectrule",codeObjectrulelist,propObjectrulelist,relationObjectrulelist,objectrulecolcaption,objectruledatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(objectrulecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(objectruledisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(objectruletype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getObjectSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ObjectFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Object");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Object", tab, chartcol);
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

		public Rows getObjectRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Object",codeObjectlist,propObjectlist,relationObjectlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getObjectRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Object",codeObjectlist,propObjectlist,relationObjectlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getObjectRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postObjectContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getObjectByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and object2"+this.getFilters();
			}
			String sql= "select * from table_Object where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Object",codeObjectlist,propObjectlist,relationObjectlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
