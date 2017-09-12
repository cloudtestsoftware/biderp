
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

	public class EmployeeDao extends EmployeeImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"employee,contact,payrole,benifit,dependent,deduction,addition,substruction,vacation,earnedvacation,benifitchange,tax"};
		protected String []childtabs={"contact,payrole,benifit,dependent,deduction,addition,substruction,vacation,earnedvacation,benifitchange,tax"};
		protected String []childtabnames={"Contact,Payrole,Benifit,Dependent,Deduction,Addition,Substruction,Vacation,EarnedVacation,BenifitChange,Tax"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","employee2site","name","lastname","street","city","state","zipcode","countrycode","deptcode","jobcode","empcode","evalcode","reportto","phone","phone2","ssn","email","birthdate","joindate","lastdate","status","payroleheader"};
		protected String [] maincolcaption={"Id","Site","First Name","Last Name","Street","City","State","Zip Code","Country","Department","Role","Employee Type","Evaluation Type","Report To","Main Phone","Other Phone","SSN/ID","Email","Birth Date","Joining Date","Last Date","Status","Employer"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Int_t","Int_t","Name_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Code_t","Code_t","Code_t","Code_t","Name_t","Phone_t","Phone_t","String10_t","Email_t","Date_t","Date_t","Date_t","Status_t","String300_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,,,,,#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,,,,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","jobcode","empcode","evalcode","reportto","phone","birthdate","joindate","lastdate","status","payroleheader","name","lastname","street","city","state","zipcode","countrycode","deptcode"};
		protected String [] reportcolcaption={"Id","Role","Employee Type","Evaluation Type","Report To","Main Phone","Birth Date","Joining Date","Last Date","Status","Employer","First Name","Last Name","Street","City","State","Zip Code","Country","Department"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Code_t","Code_t","Code_t","Name_t","Phone_t","Date_t","Date_t","Date_t","Status_t","String300_t","Name_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Code_t"};
		
		protected String [] searchcol={"objid","jobcode","empcode","evalcode","birthdate","joindate","lastdate","status","payroleheader","name","lastname","countrycode","deptcode"};
		protected String [] searchcolcaption={"Id","Role","Employee Type","Evaluation Type","Birth Date","Joining Date","Last Date","Status","Employer","First Name","Last Name","Country","Department"};
		protected String [] searchcoltype={"integer","select","select","select","date","date","date","text","text","text","text","text","select"};
		protected String [] searchdatadomain={"Id_t","Code_t","Code_t","Code_t","Date_t","Date_t","Date_t","Status_t","String300_t","Name_t","Name_t","Code_t","Code_t"};

	protected String [] propEmployeelist={"status"};
		protected String [] codeEmployeelist={"countrycode","deptcode","jobcode","empcode","evalcode"};
		protected String [] relationEmployeelist={"site:employee2site:list:"};
		
		protected String [] contacttype={"table"};
		protected String [] contactcol={"objid","name","lastname","street","city","state","zipcode","countrycode","phone","phone2","contactcode"};
		protected String [] contactcolcaption={"Id","Name","Other/Last Name","Street","City","State","Zip Code","Country","Home Phone","Office Phone","Type"};
		protected String [] contactsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] contactdatadomain={"Int_t","Name_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Phone_t","Phone_t","Code_t"};
		protected String [] contactdisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		protected String [] contactcolsearch={"#text_filter,#text_filter,#text_filter,,,,,#text_filter,,,"};
		
		protected String [] payroletype={"table"};
		protected String [] payrolecol={"objid","name","startdate","enddate","paydate","amount","status"};
		protected String [] payrolecolcaption={"Id","Employee Name","Period Start Date","Period End Date","Pay Date","Total Amount","Status"};
		protected String [] payrolesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.VARCHAR};
		protected String [] payroledatadomain={"Int_t","Name_t","Date_t","Date_t","Date_t","Money_t","Status_t"};
		protected String [] payroledisable={"yes,no,no,no,no,yes,no"};
		protected String [] payrolecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,#select_filter"};
		
		protected String [] benifittype={"table"};
		protected String [] benifitcol={"objid","name","benifitcode","benifittype","monthlycost","annualcost","startdate","note"};
		protected String [] benifitcolcaption={"Id","Benifit Name","Benifit Code","Benifit Type","Monthly Cost","Annual Cost","Start Date","Note"};
		protected String [] benifitsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.DATE,DataType.VARCHAR};
		protected String [] benifitdatadomain={"Int_t","Name_t","Code_t","Type_t","Money_t","Money_t","Date_t","String500_t"};
		protected String [] benifitdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] benifitcolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,,#text_filter,#text_filter"};
		
		protected String [] dependenttype={"table"};
		protected String [] dependentcol={"objid","name","lastname","dependentcode","ssn","birthdate"};
		protected String [] dependentcolcaption={"Id","First Name","Last Name","Relation","SSN/ID","Birth Date"};
		protected String [] dependentsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] dependentdatadomain={"Int_t","Name_t","Name_t","Code_t","String10_t","Date_t"};
		protected String [] dependentdisable={"yes,no,no,no,no,no"};
		protected String [] dependentcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter"};
		
		protected String [] deductiontype={"table"};
		protected String [] deductioncol={"objid","name","deductioncode","taxtypecode","amount","startdate","enddate","note"};
		protected String [] deductioncolcaption={"Id","Deduction Name","Deduction Code","Taxed Type","Amount","Start Date","End Date","Note"};
		protected String [] deductionsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] deductiondatadomain={"Int_t","Name_t","Code_t","Code_t","Money_t","Date_t","Date_t","String500_t"};
		protected String [] deductiondisable={"yes,no,no,no,no,no,no,no"};
		protected String [] deductioncolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,#text_filter,#text_filter,#text_filter"};
		
		protected String [] additiontype={"table"};
		protected String [] additioncol={"objid","name","adjustcode","taxtypecode","amount","applydate","note"};
		protected String [] additioncolcaption={"Id","Addition Name","Adjustment Code","Taxed Type","Amount","Apply Date","Note"};
		protected String [] additionsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.VARCHAR};
		protected String [] additiondatadomain={"Int_t","Name_t","Code_t","Code_t","Money_t","Date_t","String500_t"};
		protected String [] additiondisable={"yes,no,no,no,no,no,no"};
		protected String [] additioncolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,#text_filter,#text_filter"};
		
		protected String [] substructiontype={"table"};
		protected String [] substructioncol={"objid","name","adjustcode","taxtypecode","amount","applydate","note"};
		protected String [] substructioncolcaption={"Id","Substruction Name","Adjustment Code","Taxed Type","Amount","Apply Date","Note"};
		protected String [] substructionsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.VARCHAR};
		protected String [] substructiondatadomain={"Int_t","Name_t","Code_t","Code_t","Money_t","Date_t","String500_t"};
		protected String [] substructiondisable={"yes,no,no,no,no,no,no"};
		protected String [] substructioncolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,,#text_filter,#text_filter"};
		
		protected String [] vacationtype={"table"};
		protected String [] vacationcol={"objid","name","yearcode","vacationcode","daytaken","startdate","enddate","note"};
		protected String [] vacationcolcaption={"Id","Vacation Name","Year","Vacation Code","Days Taken","Start Date","End Date","Note"};
		protected String [] vacationsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] vacationdatadomain={"Int_t","Name_t","Code_t","Code_t","Int_t","Date_t","Date_t","String500_t"};
		protected String [] vacationdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] vacationcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter"};
		
		protected String [] earnedvacationtype={"table"};
		protected String [] earnedvacationcol={"objid","name","yearcode","vacationcode","iscumulative","totalmonth","dayspermonth","totaldays","lastbalance","balancedays","note"};
		protected String [] earnedvacationcolcaption={"Id","Name","Year","Vacation Code","Is Cumulative","Months Worked","Days Per Month","Days This Year","Last Year Balance","Balance","Note"};
		protected String [] earnedvacationsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR};
		protected String [] earnedvacationdatadomain={"Int_t","Name_t","Code_t","Code_t","Type_t","Float_t","Float_t","Float_t","Float_t","Float_t","String500_t"};
		protected String [] earnedvacationdisable={"yes,no,no,no,no,no,no,yes,no,yes,no"};
		protected String [] earnedvacationcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#numeric_filter,,,,,#text_filter"};
		
		protected String [] benifitchangetype={"table"};
		protected String [] benifitchangecol={"objid","name","benifitcode","oldmonthlycost","monthlycost","oldannualcost","annualcost","startdate","note"};
		protected String [] benifitchangecolcaption={"Id","Change Title","Benifit","Old Monthly Cost","New Monthly Cost","Annual Cost","New Annual Cost","Start Date","Note"};
		protected String [] benifitchangesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER,DataType.DATE,DataType.VARCHAR};
		protected String [] benifitchangedatadomain={"Int_t","Name_t","Code_t","Money_t","Money_t","Money_t","Money_t","Date_t","String500_t"};
		protected String [] benifitchangedisable={"yes,no,no,yes,no,yes,no,no,no"};
		protected String [] benifitchangecolsearch={"#text_filter,#text_filter,#text_filter,,,,,#text_filter,#text_filter"};
		
		protected String [] taxtype={"table"};
		protected String [] taxcol={"objid","name","taxcode","pct"};
		protected String [] taxcolcaption={"Id","Name","Tax Code","Tax (%)"};
		protected String [] taxsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER};
		protected String [] taxdatadomain={"Int_t","Name_t","Code_t","Float_t"};
		protected String [] taxdisable={"yes,no,no,no"};
		protected String [] taxcolsearch={"#text_filter,#text_filter,#select_filter,"};

		public EmployeeDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Employee");
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
				if(entity.toLowerCase().contains("<employee>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<contact>")){
					this.setContactxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getContactxml());
					}
				}else if(entity.toLowerCase().contains("<payrole>")){
					this.setPayrolexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPayrolexml());
					}
				}else if(entity.toLowerCase().contains("<benifit>")){
					this.setBenifitxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBenifitxml());
					}
				}else if(entity.toLowerCase().contains("<dependent>")){
					this.setDependentxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getDependentxml());
					}
				}else if(entity.toLowerCase().contains("<deduction>")){
					this.setDeductionxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getDeductionxml());
					}
				}else if(entity.toLowerCase().contains("<addition>")){
					this.setAdditionxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAdditionxml());
					}
				}else if(entity.toLowerCase().contains("<substruction>")){
					this.setSubstructionxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSubstructionxml());
					}
				}else if(entity.toLowerCase().contains("<vacation>")){
					this.setVacationxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getVacationxml());
					}
				}else if(entity.toLowerCase().contains("<earnedvacation>")){
					this.setEarnedvacationxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEarnedvacationxml());
					}
				}else if(entity.toLowerCase().contains("<benifitchange>")){
					this.setBenifitchangexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBenifitchangexml());
					}
				}else if(entity.toLowerCase().contains("<tax>")){
					this.setTaxxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTaxxml());
					}
				}
			}
		}

		public Rows getContactRows(){
			TemplateTable tab=this.doSelectChild("contact", "contact2employee",this.getParentobjid(),contactcol,contactsqldatatype,this.ContactFilter);
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



		public Rows getPayroleRows(){
			TemplateTable tab=this.doSelectChild("payrole", "payrole2employee",this.getParentobjid(),payrolecol,payrolesqldatatype,this.PayroleFilter);
			String [] propPayrolelist={"status"};
			String [] codePayrolelist={};
			String [] relationPayrolelist={};
			Rows rows=tu.getXMLRows(tab, "payrole",codePayrolelist,propPayrolelist,relationPayrolelist,payrolecolcaption,payroledatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(payrolecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(payroledisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(payroletype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBenifitRows(){
			TemplateTable tab=this.doSelectChild("benifit", "benifit2employee",this.getParentobjid(),benifitcol,benifitsqldatatype,this.BenifitFilter);
			String [] propBenifitlist={"benifittype"};
			String [] codeBenifitlist={"benifitcode"};
			String [] relationBenifitlist={};
			Rows rows=tu.getXMLRows(tab, "benifit",codeBenifitlist,propBenifitlist,relationBenifitlist,benifitcolcaption,benifitdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(benifitcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(benifitdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(benifittype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getDependentRows(){
			TemplateTable tab=this.doSelectChild("dependent", "dependent2employee",this.getParentobjid(),dependentcol,dependentsqldatatype,this.DependentFilter);
			String [] propDependentlist={};
			String [] codeDependentlist={"dependentcode"};
			String [] relationDependentlist={};
			Rows rows=tu.getXMLRows(tab, "dependent",codeDependentlist,propDependentlist,relationDependentlist,dependentcolcaption,dependentdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(dependentcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(dependentdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(dependenttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getDeductionRows(){
			TemplateTable tab=this.doSelectChild("deduction", "deduction2employee",this.getParentobjid(),deductioncol,deductionsqldatatype,this.DeductionFilter);
			String [] propDeductionlist={};
			String [] codeDeductionlist={"deductioncode","taxtypecode"};
			String [] relationDeductionlist={};
			Rows rows=tu.getXMLRows(tab, "deduction",codeDeductionlist,propDeductionlist,relationDeductionlist,deductioncolcaption,deductiondatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(deductioncolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(deductiondisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(deductiontype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAdditionRows(){
			TemplateTable tab=this.doSelectChild("addition", "addition2employee",this.getParentobjid(),additioncol,additionsqldatatype,this.AdditionFilter);
			String [] propAdditionlist={};
			String [] codeAdditionlist={"adjustcode","taxtypecode"};
			String [] relationAdditionlist={};
			Rows rows=tu.getXMLRows(tab, "addition",codeAdditionlist,propAdditionlist,relationAdditionlist,additioncolcaption,additiondatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(additioncolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(additiondisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(additiontype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSubstructionRows(){
			TemplateTable tab=this.doSelectChild("substruction", "substruction2employee",this.getParentobjid(),substructioncol,substructionsqldatatype,this.SubstructionFilter);
			String [] propSubstructionlist={};
			String [] codeSubstructionlist={"adjustcode","taxtypecode"};
			String [] relationSubstructionlist={};
			Rows rows=tu.getXMLRows(tab, "substruction",codeSubstructionlist,propSubstructionlist,relationSubstructionlist,substructioncolcaption,substructiondatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(substructioncolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(substructiondisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(substructiontype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getVacationRows(){
			TemplateTable tab=this.doSelectChild("vacation", "vacation2employee",this.getParentobjid(),vacationcol,vacationsqldatatype,this.VacationFilter);
			String [] propVacationlist={};
			String [] codeVacationlist={"yearcode","vacationcode"};
			String [] relationVacationlist={};
			Rows rows=tu.getXMLRows(tab, "vacation",codeVacationlist,propVacationlist,relationVacationlist,vacationcolcaption,vacationdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(vacationcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(vacationdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(vacationtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEarnedvacationRows(){
			TemplateTable tab=this.doSelectChild("earnedvacation", "earnedvacation2employee",this.getParentobjid(),earnedvacationcol,earnedvacationsqldatatype,this.EarnedvacationFilter);
			String [] propEarnedvacationlist={"iscumulative"};
			String [] codeEarnedvacationlist={"yearcode","vacationcode"};
			String [] relationEarnedvacationlist={};
			Rows rows=tu.getXMLRows(tab, "earnedvacation",codeEarnedvacationlist,propEarnedvacationlist,relationEarnedvacationlist,earnedvacationcolcaption,earnedvacationdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(earnedvacationcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(earnedvacationdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(earnedvacationtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBenifitchangeRows(){
			TemplateTable tab=this.doSelectChild("benifitchange", "benifitchange2employee",this.getParentobjid(),benifitchangecol,benifitchangesqldatatype,this.BenifitchangeFilter);
			String [] propBenifitchangelist={};
			String [] codeBenifitchangelist={"benifitcode"};
			String [] relationBenifitchangelist={};
			Rows rows=tu.getXMLRows(tab, "benifitchange",codeBenifitchangelist,propBenifitchangelist,relationBenifitchangelist,benifitchangecolcaption,benifitchangedatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(benifitchangecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(benifitchangedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(benifitchangetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTaxRows(){
			TemplateTable tab=this.doSelectChild("tax", "tax2employee",this.getParentobjid(),taxcol,taxsqldatatype,this.TaxFilter);
			String [] propTaxlist={};
			String [] codeTaxlist={"taxcode"};
			String [] relationTaxlist={};
			Rows rows=tu.getXMLRows(tab, "tax",codeTaxlist,propTaxlist,relationTaxlist,taxcolcaption,taxdatadomain,this.getGroupuser(),bqn);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(taxcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(taxdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(taxtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEmployeeSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.EmployeeFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Employee");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Employee", tab, chartcol);
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

		public Rows getEmployeeRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null);
			Rows rows=tu.getXMLRows(tab, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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

		public Rows getEmployeeRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
			return rows;
		}

		public Rows getEmployeeRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public void postEmployeeContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				this.doInsert();
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getEmployeeByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters())){
				newfilter+=" and employee2"+this.getFilters();
			}
			String sql= "select * from table_Employee where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincol,maincolcaption,maindatadomain,this.getGroupuser(),bqn);
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
