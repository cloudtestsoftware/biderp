
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
		import com.biderp.dao.VendorbidDao;
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

		//Use this URI resource with Base URL to access Vendorbid
		@Path("/vendorbid")
		public class VendorbidService {
			static Log logger = LogFactory.getLog(VendorbidService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Vendorbid
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getVendorbidRows() {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getVendorbidRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getVendorbidRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Vendorbid record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getVendorbidRecord() {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getVendorbidRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getVendorbidRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Vendorbid
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getVendorbidRowsByFilter() {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getVendorbidByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getVendorbidRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Vendorbid
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getVendorbidSummaryRows() {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getVendorbidSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getVendorbidRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Vendorbid record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getVendorbidRowDeleted() {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getVendorbidRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getVendorbidRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Itemprice rows against object ID for Vendorbid
			@GET
			@Path("/{id}/itemprice")
			@Produces({"application/xml"})
			public Rows getItempriceRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getItempriceRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getItempriceRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Bidartifacts rows against object ID for Vendorbid
			@GET
			@Path("/{id}/bidartifacts")
			@Produces({"application/xml"})
			public Rows getBidartifactsRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getBidartifactsRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBidartifactsRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Requestinfo rows against object ID for Vendorbid
			@GET
			@Path("/{id}/requestinfo")
			@Produces({"application/xml"})
			public Rows getRequestinfoRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getRequestinfoRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRequestinfoRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Quizreply rows against object ID for Vendorbid
			@GET
			@Path("/{id}/quizreply")
			@Produces({"application/xml"})
			public Rows getQuizreplyRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getQuizreplyRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getQuizreplyRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Attachment rows against object ID for Vendorbid
			@GET
			@Path("/{id}/attachment")
			@Produces({"application/xml"})
			public Rows getAttachmentRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new VendorbidDao(uriInfo,header).getAttachmentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAttachmentRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postVendorbid(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				VendorbidDao post;
				try {
					post=new VendorbidDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postVendorbidContainer();
					rows=post.getVendorbidRowModified();
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
			public Rows postFormDataVendorbid(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				VendorbidDao post;
				try {
					post=new VendorbidDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postVendorbidContainer();
					rows=post.getVendorbidRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
