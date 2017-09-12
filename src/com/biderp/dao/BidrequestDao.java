
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

	public class BidrequestDao extends BidrequestImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"bidrequest,general,invitation,artifacts,bidquiz,requestinfo,purchaseorder,resourceorder"};
		protected String []childtabs={"bidrequest,general,invitation,artifacts,bidquiz,partbid,requestinfo,bids,lowbid,purchaseorder,resourceorder"};
		protected String []childtabnames={"Bidrequest Facts,General,Invitation,Artifacts,BidQuiz,BidWorks,RFI,Bids,LowBid,PurchaseOrder,ResourceOrder"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","bidrequest2quote","bidrequest2messagequeue","name","description","projectcode","criteria","rating","listdate","deliverydate","currencycode","bgtrangecode","startcode","status","instruction"};
		protected String [] maincolcaption={"Id","Quote","MessageQueue","Name","Job Description","Project Code","Selection Criteria","Bidder Min Rating(%)","List Date","Delivery Date","Currency","Budget Range","Starts Within","Status","Instruction To Bidder"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Int_t","Name_t","String4000_t","Code_t","String4000_t","Percent_t","Date_t","Date_t","Code_t","Code_t","Code_t","Status_t","String4000_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,,,#select_filter,,,#text_filter,#text_filter,#select_filter,,,#select_filter,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name","estamount","minbid","maxbid","offeringbid","responsecount","bidcount","rficount"};
		protected String [] summarycolcaption={"Name","Total","Lowest Bid","Highest Bid","Winning Bid","Response Count","Bid Count","RFI Count"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] summarydatadomain={"Name_t","Money_t","Money_t","Money_t","Money_t","Int_t","Int_t","Int_t"};
		
		protected String [] reportcol={"objid","name","description","criteria","rating","listdate","deliverydate","responsecount","bidcount","rficount","status","instruction"};
		protected String [] reportcolcaption={"Id","Name","Job Description","Selection Criteria","Bidder Min Rating(%)","List Date","Delivery Date","Response Count","Bid Count","RFI Count","Status","Instruction To Bidder"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.DATE,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String4000_t","String4000_t","Percent_t","Date_t","Date_t","Int_t","Int_t","Int_t","Status_t","String4000_t"};
		
		protected String [] searchcol={"objid","projectcode","listdate","deliverydate","currencycode","rficount","status"};
		protected String [] searchcolcaption={"Id","Project Code","List Date","Delivery Date","Currency","RFI Count","Status"};
		protected String [] searchcoltype={"integer","select","date","date","select","integer","select"};
		protected String [] searchdatadomain={"Id_t","Code_t","Date_t","Date_t","Code_t","Int_t","Status_t"};

	protected String [] propBidrequestlist={"status"};
		protected String [] codeBidrequestlist={"projectcode","currencycode","bgtrangecode","startcode"};
		protected String [] relationBidrequestlist={"quote:bidrequest2quote:list:","messagequeue:bidrequest2messagequeue:list:"};
		
		protected String [] generaltype={"table"};
		protected String [] generalcol={"objid","name","startdate","enddate","bidduedate","bidduetime","address","zipcode","state","contactname","phone"};
		protected String [] generalcolcaption={"Id","Name","Work Start Date","Work End Date","Bid Close Date","Bid Close Time(HH:MI)","Address","Your Zip Code","State","Contact Name","Phone"};
		protected String [] generalsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] generaldatadomain={"Int_t","String100_t","Date_t","Date_t","Date_t","Time_t","String200_t","ZipCode_t","StateCode_t","Name_t","Phone_t"};
		protected String [] generaldisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		protected String [] generalcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] invitationtype={"table"};
		protected String [] invitationcol={"objid","name","email","phone","rating","status"};
		protected String [] invitationcolcaption={"Id","Name","Email","Phone","Rating","Status"};
		protected String [] invitationsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] invitationdatadomain={"Int_t","String100_t","Email_t","Phone_t","Percent_t","Status_t"};
		protected String [] invitationdisable={"yes,no,no,no,no,no"};
		protected String [] invitationcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#numeric_filter,#select_filter"};
		
		protected String [] artifactstype={"table"};
		protected String [] artifactscol={"objid","name","postingdate","description","formscode","url"};
		protected String [] artifactscolcaption={"Id","Name","Posting Date","Description","Forms Category","Document"};
		protected String [] artifactssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] artifactsdatadomain={"Int_t","String200_t","Date_t","String4000_t","Code_t","String500_t"};
		protected String [] artifactsdisable={"yes,no,no,no,no,yes"};
		protected String [] artifactscolsearch={"#text_filter,#text_filter,#text_filter,,#select_filter,#text_filter"};
		
		protected String [] bidquiztype={"table"};
		protected String [] bidquizcol={"objid","bidquiz2quotejobs","description","requirecode","umcode","unitcount","criteria","point","quizindex","name"};
		protected String [] bidquizcolcaption={"Id","QuoteJobs","Description","Is Require","Unit Code","Unit Count","Evaluation Criteria","Evaluation Point","Index","Title"};
		protected String [] bidquizsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] bidquizdatadomain={"Int_t","Int_t","String4000_t","Code_t","Code_t","Int_t","String500_t","Float_t","String20_t","Name_t"};
		protected String [] bidquizdisable={"yes,no,no,no,no,no,no,no,yes,yes"};
		protected String [] bidquizcolsearch={"#text_filter,#select_filter,,,,#text_filter,#text_filter,#numeric_filter,,"};
		
		protected String [] partbidtype={"view"};
		protected String [] partbidcol={"objid","name","description","projectname","projectcode","partcode","qntrequest","unitprice","umcode","total","note"};
		protected String [] partbidcolcaption={"Id","Name","Description","Project Name","Project Code","Part Code","Quantity Request","Est. Unit Price","Um Code","Total","Note"};
		protected String [] partbidsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] partbiddatadomain={"Int_t","Name_t","String1000_t","Name_t","Code_t","Code_t","Int_t","Money_t","Code_t","Money_t","String1000_t"};
		protected String [] partbiddisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		protected String [] partbidcolsearch={"#text_filter,#text_filter,,#text_filter,#select_filter,#select_filter,,,#select_filter,,"};
		
		protected String [] requestinfotype={"table"};
		protected String [] requestinfocol={"objid","name","askeddoubt","replied","status","askedby","repliedby"};
		protected String [] requestinfocolcaption={"Id","Title","Your Doubt","Reply","Status","Asked By","Replied By"};
		protected String [] requestinfosqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] requestinfodatadomain={"Int_t","Name_t","String4000_t","String4000_t","Status_t","Name_t","Name_t"};
		protected String [] requestinfodisable={"yes,no,no,no,no,no,no"};
		protected String [] requestinfocolsearch={"#text_filter,#text_filter,,,#select_filter,#text_filter,#text_filter"};
		
		protected String [] bidstype={"view"};
		protected String [] bidscol={"objid","firstname","lastname","email","phone","url","bidamount","totalpoint","totalearned","totalpct","totalcomplete"};
		protected String [] bidscolcaption={"Id","First Name","Last Name","Email","Phone","Website","Bid Amount","Total Point","Earned Points","Earned Points (%)","Completed (%)"};
		protected String [] bidssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] bidsdatadomain={"Int_t","Name_t","Name_t","Email_t","Name_t","String500_t","Money_t","Float_t","Float_t","Percent_t","Percent_t"};
		protected String [] bidsdisable={"yes,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes"};
		protected String [] bidscolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,"};
		
		protected String [] lowbidtype={"view"};
		protected String [] lowbidcol={"objid","name","description","projectname","partcode","qntrequest","unitprice","lowestbid","highestbid","umcode","total","supplier"};
		protected String [] lowbidcolcaption={"Id","Name","Description","Project Name","Part Code","Quantity","Est. Price","Low Bid","High Bid","Um Code","Low Bid Total","Supplier"};
		protected String [] lowbidsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] lowbiddatadomain={"Int_t","Name_t","String1000_t","Name_t","Code_t","Int_t","Money_t","Money_t","Money_t","Code_t","Money_t","String100_t"};
		protected String [] lowbiddisable={"yes,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] lowbidcolsearch={"#text_filter,#text_filter,,#text_filter,#select_filter,,,,,#select_filter,,"};
		
		protected String [] purchaseordertype={"table"};
		protected String [] purchaseordercol={"objid","purchaseorder2supplier","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","mrno","potype","total","status","maxlineno","deptcode","note","progresscode"};
		protected String [] purchaseordercolcaption={"Id","Supplier","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","MRN (Bom or None)","Order Process","Total","Status","Max Line No","Department","Note","Progress Code"};
		protected String [] purchaseordersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] purchaseorderdatadomain={"Int_t","Int_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","String30_t","Type_t","Money_t","Status_t","Int_t","Code_t","String2000_t","Code_t"};
		protected String [] purchaseorderdisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,yes,no,no,no,no,yes"};
		protected String [] purchaseordercolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#select_filter,,#select_filter,#text_filter,"};
		
		protected String [] resourceordertype={"table"};
		protected String [] resourceordercol={"objid","resourceorder2supplier","name","suppliername","suppaddress","contactname","contactno","billto","shipto","podate","completedate","amount","tax","total","status","resourcecode","maxlineno","deptcode","note","progresscode"};
		protected String [] resourceordercolcaption={"Id","Supplier","Name","Supplier Name","Supplier Address","Po Contact Name","Po Contact Phone","Bill To","Ship To","Order Date","Complete Date","Amount","Tax (%)","Total","Status","Resource Code","Max Line No","Department","Note","Progress Code"};
		protected String [] resourceordersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] resourceorderdatadomain={"Int_t","Int_t","Name_t","Name_t","String300_t","Name_t","Phone_t","String300_t","String300_t","Date_t","Date_t","Money_t","Float_t","Money_t","Status_t","Code_t","Int_t","Code_t","String2000_t","Code_t"};
		protected String [] resourceorderdisable={"yes,no,no,no,no,no,no,no,no,no,no,yes,no,yes,no,no,no,no,no,yes"};
		protected String [] resourceordercolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#select_filter,#select_filter,,#select_filter,#text_filter,"};

		public BidrequestDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Bidrequest");
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
				if(entity.toLowerCase().contains("<bidrequest>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<general>")){
					this.setGeneralxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getGeneralxml());
					}
				}else if(entity.toLowerCase().contains("<invitation>")){
					this.setInvitationxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getInvitationxml());
					}
				}else if(entity.toLowerCase().contains("<artifacts>")){
					this.setArtifactsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getArtifactsxml());
					}
				}else if(entity.toLowerCase().contains("<bidquiz>")){
					this.setBidquizxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBidquizxml());
					}
				}else if(entity.toLowerCase().contains("<partbid>")){
					this.setPartbidxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPartbidxml());
					}
				}else if(entity.toLowerCase().contains("<requestinfo>")){
					this.setRequestinfoxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getRequestinfoxml());
					}
				}else if(entity.toLowerCase().contains("<bids>")){
					this.setBidsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBidsxml());
					}
				}else if(entity.toLowerCase().contains("<lowbid>")){
					this.setLowbidxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getLowbidxml());
					}
				}else if(entity.toLowerCase().contains("<purchaseorder>")){
					this.setPurchaseorderxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPurchaseorderxml());
					}
				}else if(entity.toLowerCase().contains("<resourceorder>")){
					this.setResourceorderxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getResourceorderxml());
					}
				}
			}
		}

		public Rows getGeneralRows(){
			TemplateTable tab=this.doSelectChild("general", "general2bidrequest",this.getParentobjid(),generalcol,generalsqldatatype,this.GeneralFilter);
			String [] propGenerallist={};
			String [] codeGenerallist={};
			String [] relationGenerallist={};
			Rows rows=tu.getXMLRows(tab, "general",codeGenerallist,propGenerallist,relationGenerallist,generalcolcaption,generaldatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(generalcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(generaldisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(generaltype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getInvitationRows(){
			TemplateTable tab=this.doSelectChild("invitation", "invitation2bidrequest",this.getParentobjid(),invitationcol,invitationsqldatatype,this.InvitationFilter);
			String [] propInvitationlist={"status"};
			String [] codeInvitationlist={};
			String [] relationInvitationlist={};
			Rows rows=tu.getXMLRows(tab, "invitation",codeInvitationlist,propInvitationlist,relationInvitationlist,invitationcolcaption,invitationdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(invitationcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(invitationdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(invitationtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getArtifactsRows(){
			TemplateTable tab=this.doSelectChild("artifacts", "artifacts2bidrequest",this.getParentobjid(),artifactscol,artifactssqldatatype,this.ArtifactsFilter);
			String [] propArtifactslist={};
			String [] codeArtifactslist={"formscode"};
			String [] relationArtifactslist={};
			Rows rows=tu.getXMLRows(tab, "artifacts",codeArtifactslist,propArtifactslist,relationArtifactslist,artifactscolcaption,artifactsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(artifactscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(artifactsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(artifactstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBidquizRows(){
			TemplateTable tab=this.doSelectChild("bidquiz", "bidquiz2bidrequest",this.getParentobjid(),bidquizcol,bidquizsqldatatype,this.BidquizFilter);
			String [] propBidquizlist={};
			String [] codeBidquizlist={"requirecode","umcode"};
			String [] relationBidquizlist={"quotejobs:bidquiz2quotejobs:list:"};
			Rows rows=tu.getXMLRows(tab, "bidquiz",codeBidquizlist,propBidquizlist,relationBidquizlist,bidquizcolcaption,bidquizdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bidquizcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bidquizdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bidquiztype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPartbidRows(){
			TemplateTable tab=this.doSelectChild("partbid", "partbid2bidrequest",this.getParentobjid(),partbidcol,partbidsqldatatype,this.PartbidFilter);
			String [] propPartbidlist={};
			String [] codePartbidlist={"projectcode","partcode","umcode"};
			String [] relationPartbidlist={};
			Rows rows=tu.getXMLRows(tab, "partbid",codePartbidlist,propPartbidlist,relationPartbidlist,partbidcolcaption,partbiddatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(partbidcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(partbiddisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(partbidtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getRequestinfoRows(){
			TemplateTable tab=this.doSelectChild("requestinfo", "requestinfo2bidrequest",this.getParentobjid(),requestinfocol,requestinfosqldatatype,this.RequestinfoFilter);
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



		public Rows getBidsRows(){
			TemplateTable tab=this.doSelectChild("bids", "bids2bidrequest",this.getParentobjid(),bidscol,bidssqldatatype,this.BidsFilter);
			String [] propBidslist={};
			String [] codeBidslist={};
			String [] relationBidslist={};
			Rows rows=tu.getXMLRows(tab, "bids",codeBidslist,propBidslist,relationBidslist,bidscolcaption,bidsdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bidscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bidsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bidstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getLowbidRows(){
			TemplateTable tab=this.doSelectChild("lowbid", "lowbid2bidrequest",this.getParentobjid(),lowbidcol,lowbidsqldatatype,this.LowbidFilter);
			String [] propLowbidlist={};
			String [] codeLowbidlist={"partcode","umcode"};
			String [] relationLowbidlist={};
			Rows rows=tu.getXMLRows(tab, "lowbid",codeLowbidlist,propLowbidlist,relationLowbidlist,lowbidcolcaption,lowbiddatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(lowbidcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(lowbiddisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(lowbidtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPurchaseorderRows(){
			TemplateTable tab=this.doSelectChild("purchaseorder", "purchaseorder2bidrequest",this.getParentobjid(),purchaseordercol,purchaseordersqldatatype,this.PurchaseorderFilter);
			String [] propPurchaseorderlist={"potype","status"};
			String [] codePurchaseorderlist={"deptcode","progresscode"};
			String [] relationPurchaseorderlist={"supplier:purchaseorder2supplier:list:"};
			Rows rows=tu.getXMLRows(tab, "purchaseorder",codePurchaseorderlist,propPurchaseorderlist,relationPurchaseorderlist,purchaseordercolcaption,purchaseorderdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(purchaseordercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(purchaseorderdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(purchaseordertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getResourceorderRows(){
			TemplateTable tab=this.doSelectChild("resourceorder", "resourceorder2bidrequest",this.getParentobjid(),resourceordercol,resourceordersqldatatype,this.ResourceorderFilter);
			String [] propResourceorderlist={"status"};
			String [] codeResourceorderlist={"resourcecode","deptcode","progresscode"};
			String [] relationResourceorderlist={"supplier:resourceorder2supplier:list:"};
			Rows rows=tu.getXMLRows(tab, "resourceorder",codeResourceorderlist,propResourceorderlist,relationResourceorderlist,resourceordercolcaption,resourceorderdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(resourceordercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(resourceorderdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(resourceordertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBidrequestSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BidrequestFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Bidrequest");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Bidrequest", tab, chartcol);
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

		public Rows getBidrequestRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Bidrequest",codeBidrequestlist,propBidrequestlist,relationBidrequestlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getBidrequestRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Bidrequest",codeBidrequestlist,propBidrequestlist,relationBidrequestlist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getBidrequestRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postBidrequestContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBidrequestByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and bidrequest2"+this.getFilters();
			}
			String sql= "select * from table_Bidrequest where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Bidrequest",codeBidrequestlist,propBidrequestlist,relationBidrequestlist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
