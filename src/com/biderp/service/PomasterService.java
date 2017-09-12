
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
		import com.biderp.dao.PomasterDao;
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

		//Use this URI resource with Base URL to access Pomaster
		@Path("/pomaster")
		public class PomasterService {
			static Log logger = LogFactory.getLog(PomasterService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Pomaster
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getPomasterRows() {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPomasterRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPomasterRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Pomaster record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getPomasterRecord() {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPomasterRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPomasterRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Pomaster
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getPomasterRowsByFilter() {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPomasterByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPomasterRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Pomaster
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getPomasterSummaryRows() {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPomasterSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPomasterRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Pomaster record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getPomasterRowDeleted() {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPomasterRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPomasterRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Purchaseorder rows against object ID for Pomaster
			@GET
			@Path("/{id}/purchaseorder")
			@Produces({"application/xml"})
			public Rows getPurchaseorderRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPurchaseorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPurchaseorderRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Posupplier rows against object ID for Pomaster
			@GET
			@Path("/{id}/posupplier")
			@Produces({"application/xml"})
			public Rows getPosupplierRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getPosupplierRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPosupplierRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Resourceorder rows against object ID for Pomaster
			@GET
			@Path("/{id}/resourceorder")
			@Produces({"application/xml"})
			public Rows getResourceorderRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getResourceorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getResourceorderRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Resourcesupplier rows against object ID for Pomaster
			@GET
			@Path("/{id}/resourcesupplier")
			@Produces({"application/xml"})
			public Rows getResourcesupplierRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getResourcesupplierRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getResourcesupplierRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Bompurchase rows against object ID for Pomaster
			@GET
			@Path("/{id}/bompurchase")
			@Produces({"application/xml"})
			public Rows getBompurchaseRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getBompurchaseRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBompurchaseRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Deptpobudget rows against object ID for Pomaster
			@GET
			@Path("/{id}/deptpobudget")
			@Produces({"application/xml"})
			public Rows getDeptpobudgetRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PomasterDao(uriInfo,header).getDeptpobudgetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDeptpobudgetRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postPomaster(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				PomasterDao post;
				try {
					post=new PomasterDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postPomasterContainer();
					rows=post.getPomasterRowModified();
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
			public Rows postFormDataPomaster(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				PomasterDao post;
				try {
					post=new PomasterDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postPomasterContainer();
					rows=post.getPomasterRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
