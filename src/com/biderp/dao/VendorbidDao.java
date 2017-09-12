
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

	public class VendorbidDao extends VendorbidImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"vendorbid,itemprice,requestinfo,quizreply,attachment"};
		protected String []childtabs={"vendorbid,itemprice,bidartifacts,requestinfo,quizreply,attachment"};
		protected String []childtabnames={"Vendorbid Facts,ItemPrice,Artifacts,RFI,QuizReply,BidAttachment"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","vendorbid2messagequeue","name","bidderlogin","currencycode","biddate","status","note","validdays","phone","email","url","contact"};
		protected String [] maincolcaption={"Id","MessageQueue","Project Name","Bidder Login","Currency","Bid Date","Status","Any Note To Owner","Bid Valid in Days","Phone","Email","Url","Other Contact"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Email_t","Code_t","Date_t","Status_t","String4000_t","Int_t","Phone_t","Email_t","String100_t","String400_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#text_filter,#select_filter,,,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,yes,yes,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","bidamount"};
		protected String [] summarycolcaption={"Name","Bid Amount"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","bidderlogin"};
		protected String [] reportcolcaption={"Id","Project Name","Bidder Login"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Email_t"};
		
		protected String [] searchcol={"objid","name","bidderlogin","currencycode","biddate","status"};
		protected String [] searchcolcaption={"Id","Project Name","Bidder Login","Currency","Bid Date","Status"};
		protected String [] searchcoltype={"integer","text","email","select","date","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Email_t","Code_t","Date_t","Status_t"};

	protected String [] propVendorbidlist={"status"};
		protected String [] codeVendorbidlist={"currencycode"};
		protected String [] relationVendorbidlist={"messagequeue:vendorbid2messagequeue:list:"};
		
		protected String [] itempricetype={"table"};
		protected String [] itempricecol={"objid","name","description","unitprice","unitcount","umcode","total","bidder"};
		protected String [] itempricecolcaption={"Id","Title","Description","Unit Price","Quantity","Um Code","Total","Bidder"};
		protected String [] itempricesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] itempricedatadomain={"Int_t","Name_t","String4000_t","Money_t","Int_t","Code_t","Money_t","Email_t"};
		protected String [] itempricedisable={"yes,no,no,no,yes,yes,yes,yes"};
		protected String [] itempricecolsearch={"#text_filter,#text_filter,,,,#select_filter,,"};
		
		protected String [] bidartifactstype={"view"};
		protected String [] bidartifactscol={"objid","name","postingdate","description","formscode","url"};
		protected String [] bidartifactscolcaption={"Id","Name","Posting Date","Description","Forms Category","Document"};
		protected String [] bidartifactssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] bidartifactsdatadomain={"Int_t","String200_t","Date_t","String4000_t","Code_t","String500_t"};
		protected String [] bidartifactsdisable={"yes,no,no,no,no,yes"};
		protected String [] bidartifactscolsearch={"#text_filter,#text_filter,#text_filter,,#select_filter,#text_filter"};
		
		protected String [] requestinfotype={"table"};
		protected String [] requestinfocol={"objid","name","askeddoubt","replied","status","askedby","repliedby"};
		protected String [] requestinfocolcaption={"Id","Title","Your Doubt","Reply","Status","Asked By","Replied By"};
		protected String [] requestinfosqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] requestinfodatadomain={"Int_t","Name_t","String4000_t","String4000_t","Status_t","Name_t","Name_t"};
		protected String [] requestinfodisable={"yes,no,no,no,no,no,no"};
		protected String [] requestinfocolsearch={"#text_filter,#text_filter,,,#select_filter,#text_filter,#text_filter"};
		
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

		public VendorbidDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Vendorbid");
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
				if(entity.toLowerCase().contains("<vendorbid>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<itemprice>")){
					this.setItempricexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getItempricexml());
					}
				}else if(entity.toLowerCase().contains("<bidartifacts>")){
					this.setBidartifactsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBidartifactsxml());
					}
				}else if(entity.toLowerCase().contains("<requestinfo>")){
					this.setRequestinfoxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getRequestinfoxml());
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

		public Rows getItempriceRows(){
			TemplateTable tab=this.doSelectChild("itemprice", "itemprice2vendorbid",this.getParentobjid(),itempricecol,itempricesqldatatype,this.ItempriceFilter);
			String [] propItempricelist={};
			String [] codeItempricelist={"umcode"};
			String [] relationItempricelist={};
			Rows rows=tu.getXMLRows(tab, "itemprice",codeItempricelist,propItempricelist,relationItempricelist,itempricecolcaption,itempricedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(itempricecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(itempricedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(itempricetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBidartifactsRows(){
			TemplateTable tab=this.doSelectChild("bidartifacts", "bidartifacts2vendorbid",this.getParentobjid(),bidartifactscol,bidartifactssqldatatype,this.BidartifactsFilter);
			String [] propBidartifactslist={};
			String [] codeBidartifactslist={"formscode"};
			String [] relationBidartifactslist={};
			Rows rows=tu.getXMLRows(tab, "bidartifacts",codeBidartifactslist,propBidartifactslist,relationBidartifactslist,bidartifactscolcaption,bidartifactsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bidartifactscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bidartifactsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bidartifactstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getRequestinfoRows(){
			TemplateTable tab=this.doSelectChild("requestinfo", "requestinfo2vendorbid",this.getParentobjid(),requestinfocol,requestinfosqldatatype,this.RequestinfoFilter);
			String [] propRequestinfolist={"status"};
			String [] codeRequestinfolist={};
			String [] relationRequestinfolist={};
			Rows rows=tu.getXMLRows(tab, "requestinfo",codeRequestinfolist,propRequestinfolist,relationRequestinfolist,requestinfocolcaption,requestinfodatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(requestinfocolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(requestinfodisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(requestinfotype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQuizreplyRows(){
			TemplateTable tab=this.doSelectChild("quizreply", "quizreply2vendorbid",this.getParentobjid(),quizreplycol,quizreplysqldatatype,this.QuizreplyFilter);
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
			TemplateTable tab=this.doSelectChild("attachment", "attachment2vendorbid",this.getParentobjid(),attachmentcol,attachmentsqldatatype,this.AttachmentFilter);
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



		public Rows getVendorbidSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.VendorbidFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Vendorbid");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Vendorbid", tab, chartcol);
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

		public Rows getVendorbidRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Vendorbid",codeVendorbidlist,propVendorbidlist,relationVendorbidlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getVendorbidRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Vendorbid",codeVendorbidlist,propVendorbidlist,relationVendorbidlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getVendorbidRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postVendorbidContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getVendorbidByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and vendorbid2"+this.getFilters();
			}
			String sql= "select * from table_Vendorbid where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Vendorbid",codeVendorbidlist,propVendorbidlist,relationVendorbidlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
