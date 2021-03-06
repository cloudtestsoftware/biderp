
package com.auth.dao; 

import java.util.Map; 
import java.util.ArrayList; 
import java.util.Arrays; 
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders; 
import javax.ws.rs.core.UriInfo;

import com.biderp.bean.ConsoleImpl;

import cms.service.app.ServiceManager;
import cms.service.dhtmlx.*;
import cms.service.exceptions.AuthenticationException;
import cms.service.exceptions.DaoException; 
import cms.service.jdbc.DataType; 
import cms.service.template.TemplateTable; 

 
 	 /** A simple bean that has a single String property 
	 *  called message. 
 	 *  
	 * @author S.K Jana Version 1.0 
 	 * @Copyright : This code belongs to SoftleanErp.com. All right reserved! 
 	 * @since 2005-2013 
 	 */ 

	public class ConsoleDao extends ConsoleImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		private String []deletetabs={"console,"};
		private String []childtabs={""};
		private String []childtabnames={""};
		
		private String [] maincol={"objid","mqid","name","title","description","status","keyobjid","elapseday","entrydate"};
		private String [] maincolcaption={"Id","MessageQueue","Object","Project Title","Description","Status","Key Objid","Elapse Days","Entry Date"};
		private String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.DATE};
		private String [] maindatadomain={"Int_t","Int_t","Name_t","Name_t","String200_t","Status_t","Id_t","Float_t","Date_t"};
		private String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,#numeric_filter,#text_filter"};
		
		private String [] summarycol={"name"};
		private String [] summarycolcaption={"Name"};
		private String [] summarysqldatatype={DataType.VARCHAR};
		private String [] summarydatadomain={"Name_t"};
		
		private String [] reportcol={"objid","name","title","description","status","elapseday","entrydate"};
		private String [] reportcolcaption={"Id","Object","Project Title","Description","Status","Elapse Days","Entry Date"};
		private String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE};
		private String [] reportdatadomain={"Id_t","Name_t","Name_t","String200_t","Status_t","Float_t","Date_t"};
		
		private String [] searchcol={"objid","name","title","description","status","keyobjid","elapseday","entrydate"};
		private String [] searchcolcaption={"Id","Object","Project Title","Description","Status","Key Objid","Elapse Days","Entry Date"};
		private String [] searchcoltype={"integer","text","text","text","select","integer","float","date"};
		private String [] searchdatadomain={"Id_t","Name_t","Name_t","String200_t","Status_t","Id_t","Float_t","Date_t"};

		private String [] propConsolelist={"status"};
		private String [] codeConsolelist={};
		private String [] relationConsolelist={};

		public ConsoleDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Console");
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("generate_log"))){
					ACONST.GENERATE_LOG=true;
			}
			if(!tu.isEmptyValue(uriInfo.getPathParameters().getFirst("id"))){
				this.setParentobjid(uriInfo.getPathParameters().getFirst("id").replace("id-", ""));
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
				if(entity.toLowerCase().contains("<console>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}
			}
		}

		public Rows getConsoleSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ConsoleFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Console");
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Console", tab, chartcol);
				ArrayList<String> data2= new ArrayList<String>();
				data2.add(datas.get(0));
				Userdata chart= new Userdata(chartcol+".chart",data2);
				userdata.add(chart);
				ArrayList<String> data3= new ArrayList<String>();
				data3.add(datas.get(1));
				Userdata griddata= new Userdata(chartcol+".data",data3);
				userdata.add(griddata);
			}
			rows.setUserdata(userdata);
			return rows;
		}

		public Rows getConsoleRows(){
			return(tu.getXMLConsoleRows(this.getUsername()));
			
		}

		public Rows getConsoleRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Console",codeConsolelist,propConsolelist,relationConsolelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public void postConsoleContainer() throws DaoException{
			this.doInsert();
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getConsoleByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and console2"+this.getFilters();
			}
			String sql= "select * from table_Console where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Console",codeConsolelist,propConsolelist,relationConsolelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("tabs",Arrays.asList(childtabs));
			Userdata data2= new Userdata("tabnames",Arrays.asList(childtabnames));
			userdata.add(data1);
			userdata.add(data2);
			rows.setUserdata(userdata);
			return rows;
		}
	}
