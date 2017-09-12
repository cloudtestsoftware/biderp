
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

	public class BidsDao extends BidsImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"quizreply,attachment"};
		protected String []childtabs={"bids,itemcost,quizreply,attachment"};
		protected String []childtabnames={"Bids Facts,ItemCost,QuizReply,BidAttachment"};
		
		protected String [] maintype={"view"};
		protected String [] maincol={"objid","firstname","lastname","email","phone","url","bidamount"};
		protected String [] maincolcaption={"Id","First Name","Last Name","Email","Phone","Website","Bid Amount"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] maindatadomain={"Int_t","Name_t","Name_t","Email_t","Name_t","String500_t","Money_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,"};
		
		protected String [] maincoldisable={"yes,yes,yes,yes,yes,yes,yes"};
		
		protected String [] summarycol={"name","totalpoint","totalearned","totalpct","totalcomplete"};
		protected String [] summarycolcaption={"Name","Total Point","Earned Points","Earned Points (%)","Completed (%)"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Float_t","Float_t","Percent_t","Percent_t"};
		
		protected String [] reportcol={"objid","email","phone","url","totalpoint","totalearned","totalpct","totalcomplete","firstname","lastname"};
		protected String [] reportcolcaption={"Id","Email","Phone","Website","Total Point","Earned Points","Earned Points (%)","Completed (%)","First Name","Last Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Email_t","Name_t","String500_t","Float_t","Float_t","Percent_t","Percent_t","Name_t","Name_t"};
		
		protected String [] searchcol={"objid","email","phone","url","firstname","lastname"};
		protected String [] searchcolcaption={"Id","Email","Phone","Website","First Name","Last Name"};
		protected String [] searchcoltype={"integer","email","text","text","text","text"};
		protected String [] searchdatadomain={"Id_t","Email_t","Name_t","String500_t","Name_t","Name_t"};

	protected String [] propBidslist={};
		protected String [] codeBidslist={};
		protected String [] relationBidslist={};
		
		protected String [] itemcosttype={"view"};
		protected String [] itemcostcol={"objid","name","description","umcode","unitcount","unitprice","total","bidder"};
		protected String [] itemcostcolcaption={"Id","Item Name","Description","Um Code","Quantity","Unit Price","Unit Total","Bidder"};
		protected String [] itemcostsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] itemcostdatadomain={"Int_t","Name_t","String4000_t","Code_t","Int_t","Money_t","Money_t","Email_t"};
		protected String [] itemcostdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] itemcostcolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,,,#text_filter"};
		
		protected String [] quizreplytype={"table"};
		protected String [] quizreplycol={"objid","quizindex","name","description","requirecode","umcode","unitcount","criteria","point","reply","iscomply","url","pointearned"};
		protected String [] quizreplycolcaption={"Id","Index","Item","Quiz","Is Required","Unit Code","Unit Count","Evaluation Criteria","Weightage","Note","Is Comply","Document","Point Earned"};
		protected String [] quizreplysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] quizreplydatadomain={"Int_t","String20_t","Name_t","String4000_t","String20_t","String20_t","Int_t","String1000_t","Float_t","String1000_t","Type_t","String300_t","Float_t"};
		protected String [] quizreplydisable={"yes,yes,yes,no,no,no,no,no,no,no,no,yes,no"};
		protected String [] quizreplycolsearch={"#text_filter,,#text_filter,,#text_filter,#text_filter,#text_filter,,#numeric_filter,,#select_filter,#text_filter,#numeric_filter"};
		
		protected String [] attachmenttype={"table"};
		protected String [] attachmentcol={"objid","name","description","url"};
		protected String [] attachmentcolcaption={"Id","Name","Description","URL(htpp:..)"};
		protected String [] attachmentsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] attachmentdatadomain={"Int_t","String200_t","String4000_t","String500_t"};
		protected String [] attachmentdisable={"yes,no,no,yes"};
		protected String [] attachmentcolsearch={"#text_filter,#text_filter,,#text_filter"};

		public BidsDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Bids");
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
				if(entity.toLowerCase().contains("<bids>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<itemcost>")){
					this.setItemcostxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getItemcostxml());
					}
				}else if(entity.toLowerCase().contains("<quizreply>")){
					this.setQuizreplyxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQuizreplyxml());
					}
				}else if(entity.toLowerCase().contains("<attachment>")){
					this.setAttachmentxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAttachmentxml());
					}
				}
			}
		}

		public Rows getItemcostRows(){
			TemplateTable tab=this.doSelectChild("itemcost", "itemcost2bids",this.getParentobjid(),itemcostcol,itemcostsqldatatype,this.ItemcostFilter);
			String [] propItemcostlist={};
			String [] codeItemcostlist={"umcode"};
			String [] relationItemcostlist={};
			Rows rows=tu.getXMLRows(tab, "itemcost",codeItemcostlist,propItemcostlist,relationItemcostlist,itemcostcolcaption,itemcostdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(itemcostcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(itemcostdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(itemcosttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuizreplyRows(){
			TemplateTable tab=this.doSelectChild("quizreply", "quizreply2bids",this.getParentobjid(),quizreplycol,quizreplysqldatatype,this.QuizreplyFilter);
			String [] propQuizreplylist={"iscomply"};
			String [] codeQuizreplylist={};
			String [] relationQuizreplylist={};
			Rows rows=tu.getXMLRows(tab, "quizreply",codeQuizreplylist,propQuizreplylist,relationQuizreplylist,quizreplycolcaption,quizreplydatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(quizreplycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(quizreplydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(quizreplytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAttachmentRows(){
			TemplateTable tab=this.doSelectChild("attachment", "attachment2bids",this.getParentobjid(),attachmentcol,attachmentsqldatatype,this.AttachmentFilter);
			String [] propAttachmentlist={};
			String [] codeAttachmentlist={};
			String [] relationAttachmentlist={};
			Rows rows=tu.getXMLRows(tab, "attachment",codeAttachmentlist,propAttachmentlist,relationAttachmentlist,attachmentcolcaption,attachmentdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(attachmentcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(attachmentdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(attachmenttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBidsSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BidsFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Bids");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Bids", tab, chartcol);
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

		public Rows getBidsRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Bids",codeBidslist,propBidslist,relationBidslist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getBidsRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Bids",codeBidslist,propBidslist,relationBidslist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getBidsRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postBidsContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBidsByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and bids2"+this.getFilters();
			}
			String sql= "select * from table_Bids where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Bids",codeBidslist,propBidslist,relationBidslist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
