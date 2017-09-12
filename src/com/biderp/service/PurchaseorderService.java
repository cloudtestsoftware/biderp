
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
		import com.biderp.dao.PurchaseorderDao;
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

		//Use this URI resource with Base URL to access Purchaseorder
		@Path("/purchaseorder")
		public class PurchaseorderService {
			static Log logger = LogFactory.getLog(PurchaseorderService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Purchaseorder
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getPurchaseorderRows() {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPurchaseorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPurchaseorderRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Purchaseorder record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getPurchaseorderRecord() {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPurchaseorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPurchaseorderRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Purchaseorder
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getPurchaseorderRowsByFilter() {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPurchaseorderByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPurchaseorderRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Purchaseorder
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getPurchaseorderSummaryRows() {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPurchaseorderSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPurchaseorderRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Purchaseorder record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getPurchaseorderRowDeleted() {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPurchaseorderRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPurchaseorderRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Partrequest rows against object ID for Purchaseorder
			@GET
			@Path("/{id}/partrequest")
			@Produces({"application/xml"})
			public Rows getPartrequestRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPartrequestRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPartrequestRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Purchaseindent rows against object ID for Purchaseorder
			@GET
			@Path("/{id}/purchaseindent")
			@Produces({"application/xml"})
			public Rows getPurchaseindentRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPurchaseindentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPurchaseindentRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Poinvoice rows against object ID for Purchaseorder
			@GET
			@Path("/{id}/poinvoice")
			@Produces({"application/xml"})
			public Rows getPoinvoiceRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PurchaseorderDao(uriInfo,header).getPoinvoiceRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPoinvoiceRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postPurchaseorder(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				PurchaseorderDao post;
				try {
					post=new PurchaseorderDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postPurchaseorderContainer();
					rows=post.getPurchaseorderRowModified();
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
			public Rows postFormDataPurchaseorder(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				PurchaseorderDao post;
				try {
					post=new PurchaseorderDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postPurchaseorderContainer();
					rows=post.getPurchaseorderRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
