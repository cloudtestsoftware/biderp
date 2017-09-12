
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

	public class MessagequeueDao extends MessagequeueImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"messagequeue,participants"};
		protected String []childtabs={"participants"};
		protected String []childtabnames={"Participants"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","messagequeue2user","name","firstname","lastname","deptcode","email","status","login"};
		protected String [] maincolcaption={"Id","User","Name","First Name","Last Name","Dept Code","Email","Status","Login"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Name_t","Name_t","Code_t","Email_t","Status_t","Name_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#select_filter,,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","firstname","lastname"};
		protected String [] reportcolcaption={"Id","Name","First Name","Last Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Name_t"};
		
		protected String [] searchcol={"objid","name","firstname","lastname","status","deptcode"};
		protected String [] searchcolcaption={"Id","Name","First Name","Last Name","Status","Dept Code"};
		protected String [] searchcoltype={"integer","text","text","text","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Name_t","Status_t","Code_t"};

	protected String [] propMessagequeuelist={"status"};
		protected String [] codeMessagequeuelist={"deptcode"};
		protected String [] relationMessagequeuelist={"user:messagequeue2user:list:"};
		
		protected String [] participantstype={"table"};
		protected String [] participantscol={"objid","name","lastname","empno","reportto","joindate","evalcode","jobcode","instruction"};
		protected String [] participantscolcaption={"Id","First Name","Last Name","Employee No","Reporting To","Join Date","Evaluation Type","Job Code","Instruction"};
		protected String [] participantssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] participantsdatadomain={"Int_t","Name_t","Name_t","Id_t","Name_t","Date_t","Code_t","Code_t","String500_t"};
		protected String [] participantsdisable={"yes,yes,yes,yes,no,yes,no,no,no"};
		protected String [] participantscolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,#text_filter"};

		public MessagequeueDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Messagequeue");
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
				if(entity.toLowerCase().contains("<messagequeue>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<participants>")){
					this.setParticipantsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getParticipantsxml());
					}
				}
			}
		}

		public Rows getParticipantsRows(){
			TemplateTable tab=this.doSelectChild("participants", "participants2messagequeue",this.getParentobjid(),participantscol,participantssqldatatype,this.ParticipantsFilter);
			String [] propParticipantslist={};
			String [] codeParticipantslist={"evalcode","jobcode"};
			String [] relationParticipantslist={};
			Rows rows=tu.getXMLRows(tab, "participants",codeParticipantslist,propParticipantslist,relationParticipantslist,participantscolcaption,participantsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(participantscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(participantsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(participantstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMessagequeueSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.MessagequeueFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Messagequeue");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Messagequeue", tab, chartcol);
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

		public Rows getMessagequeueRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Messagequeue",codeMessagequeuelist,propMessagequeuelist,relationMessagequeuelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getMessagequeueRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Messagequeue",codeMessagequeuelist,propMessagequeuelist,relationMessagequeuelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getMessagequeueRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postMessagequeueContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getMessagequeueByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and messagequeue2"+this.getFilters();
			}
			String sql= "select * from table_Messagequeue where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Messagequeue",codeMessagequeuelist,propMessagequeuelist,relationMessagequeuelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
