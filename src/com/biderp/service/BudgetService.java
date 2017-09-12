
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
		import com.biderp.dao.BudgetDao;
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

		//Use this URI resource with Base URL to access Budget
		@Path("/budget")
		public class BudgetService {
			static Log logger = LogFactory.getLog(BudgetService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Budget
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getBudgetRows() {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBudgetRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Budget record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getBudgetRecord() {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBudgetRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Budget
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getBudgetRowsByFilter() {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBudgetRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Budget
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getBudgetSummaryRows() {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Budget record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getBudgetRowDeleted() {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Budgethead rows against object ID for Budget
			@GET
			@Path("/{id}/budgethead")
			@Produces({"application/xml"})
			public Rows getBudgetheadRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetheadRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetheadRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Budgetplan rows against object ID for Budget
			@GET
			@Path("/{id}/budgetplan")
			@Produces({"application/xml"})
			public Rows getBudgetplanRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getBudgetplanRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetplanRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Invoice rows against object ID for Budget
			@GET
			@Path("/{id}/invoice")
			@Produces({"application/xml"})
			public Rows getInvoiceRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getInvoiceRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getInvoiceRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Payment rows against object ID for Budget
			@GET
			@Path("/{id}/payment")
			@Produces({"application/xml"})
			public Rows getPaymentRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getPaymentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPaymentRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Monthly rows against object ID for Budget
			@GET
			@Path("/{id}/monthly")
			@Produces({"application/xml"})
			public Rows getMonthlyRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getMonthlyRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getMonthlyRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Yearly rows against object ID for Budget
			@GET
			@Path("/{id}/yearly")
			@Produces({"application/xml"})
			public Rows getYearlyRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BudgetDao(uriInfo,header).getYearlyRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getYearlyRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postBudget(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				BudgetDao post;
				try {
					post=new BudgetDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBudgetContainer();
					rows=post.getBudgetRowModified();
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
			public Rows postFormDataBudget(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				BudgetDao post;
				try {
					post=new BudgetDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBudgetContainer();
					rows=post.getBudgetRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
