
		package com.biderp.service;

		import javax.ws.rs.GET;
		import javax.ws.rs.Consumes;
		import javax.ws.rs.FormParam;
		import com.sun.jersey.multipart.FormDataParam;
		import javax.ws.rs.POST;
		import javax.ws.rs.Path;
		import javax.ws.rs.Produces;
		import javax.ws.rs.core.Context;
		import javax.ws.rs.core.HttpHeaders;
		import javax.ws.rs.core.MediaType;
		import javax.ws.rs.core.UriInfo;
		import org.apache.commons.logging.Log;
		import org.apache.commons.logging.LogFactory;
		import cms.service.dhtmlx.Rows;
		import cms.service.template.TemplateUtility;
		import cms.service.exceptions.DaoException;
		import cms.service.exceptions.AuthenticationException;
		import com.biderp.dao.BudgetheadDao;
		/*
		*  URL Parameters:
		*  
		*  Mandatory : loginname, groupuser, token i.e  {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444
		*  
		*  Optional : id= parent objid for any child url i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444
		*  
		*  Optional: page, pagesize for search i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444&page=1&pagesize=50
		*  
		*  Optional: name for filter i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444&page=1&pagesize=50&name=Alex
		*  
		*  Optional: fields=column1,column2,...  i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&
		*  				token=2343434334444&page=1&pagesize=50&name=Alex&fields=name,title,projectcode...
		*  
		*/

		//Use this URI resource with Base URL to access Budgethead
		@Path("/budgethead")
		public class BudgetheadService {
			static Log logger = LogFactory.getLog(BudgetheadService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Budgethead
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getBudgetheadRows() {
				Rows rows = null;
				try {
					rows=new BudgetheadDao(uriInfo,header).getBudgetheadRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBudgetheadRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Budgethead record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getBudgetheadRecord() {
				Rows rows = null;
				try {
					rows=new BudgetheadDao(uriInfo,header).getBudgetheadRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBudgetheadRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Budgethead
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getBudgetheadRowsByFilter() {
				Rows rows = null;
				try {
					rows=new BudgetheadDao(uriInfo,header).getBudgetheadByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBudgetheadRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Budgethead
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getBudgetheadSummaryRows() {
				Rows rows = null;
				try {
					rows=new BudgetheadDao(uriInfo,header).getBudgetheadSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetheadRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Budgethead record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getBudgetheadRowDeleted() {
				Rows rows = null;
				try {
					rows=new BudgetheadDao(uriInfo,header).getBudgetheadRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetheadRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Allocation rows against object ID for Budgethead
			@GET
			@Path("/{id}/allocation")
			@Produces({"application/xml"})
			public Rows getAllocationRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetheadDao(uriInfo,header).getAllocationRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAllocationRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postBudgethead(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				BudgetheadDao post;
				try {
					post=new BudgetheadDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBudgetheadContainer();
					rows=post.getBudgetheadRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}

			// Post all data changes in using form
			@POST
			@Path("/formdata")
			@Consumes(MediaType.MULTIPART_FORM_DATA)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postFormDataBudgethead(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				BudgetheadDao post;
				try {
					post=new BudgetheadDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBudgetheadContainer();
					rows=post.getBudgetheadRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
