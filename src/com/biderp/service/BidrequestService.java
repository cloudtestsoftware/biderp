
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
		import com.biderp.dao.BidrequestDao;
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

		//Use this URI resource with Base URL to access Bidrequest
		@Path("/bidrequest")
		public class BidrequestService {
			static Log logger = LogFactory.getLog(BidrequestService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Bidrequest
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getBidrequestRows() {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidrequestRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBidrequestRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Bidrequest record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getBidrequestRecord() {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidrequestRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBidrequestRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Bidrequest
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getBidrequestRowsByFilter() {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidrequestByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBidrequestRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Bidrequest
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getBidrequestSummaryRows() {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidrequestSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBidrequestRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Bidrequest record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getBidrequestRowDeleted() {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidrequestRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBidrequestRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all General rows against object ID for Bidrequest
			@GET
			@Path("/{id}/general")
			@Produces({"application/xml"})
			public Rows getGeneralRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getGeneralRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getGeneralRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Invitation rows against object ID for Bidrequest
			@GET
			@Path("/{id}/invitation")
			@Produces({"application/xml"})
			public Rows getInvitationRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getInvitationRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getInvitationRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Artifacts rows against object ID for Bidrequest
			@GET
			@Path("/{id}/artifacts")
			@Produces({"application/xml"})
			public Rows getArtifactsRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getArtifactsRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getArtifactsRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Bidquiz rows against object ID for Bidrequest
			@GET
			@Path("/{id}/bidquiz")
			@Produces({"application/xml"})
			public Rows getBidquizRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidquizRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBidquizRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Partbid rows against object ID for Bidrequest
			@GET
			@Path("/{id}/partbid")
			@Produces({"application/xml"})
			public Rows getPartbidRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getPartbidRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPartbidRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Requestinfo rows against object ID for Bidrequest
			@GET
			@Path("/{id}/requestinfo")
			@Produces({"application/xml"})
			public Rows getRequestinfoRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getRequestinfoRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRequestinfoRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Bids rows against object ID for Bidrequest
			@GET
			@Path("/{id}/bids")
			@Produces({"application/xml"})
			public Rows getBidsRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getBidsRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBidsRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Lowbid rows against object ID for Bidrequest
			@GET
			@Path("/{id}/lowbid")
			@Produces({"application/xml"})
			public Rows getLowbidRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getLowbidRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getLowbidRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Purchaseorder rows against object ID for Bidrequest
			@GET
			@Path("/{id}/purchaseorder")
			@Produces({"application/xml"})
			public Rows getPurchaseorderRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getPurchaseorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPurchaseorderRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Resourceorder rows against object ID for Bidrequest
			@GET
			@Path("/{id}/resourceorder")
			@Produces({"application/xml"})
			public Rows getResourceorderRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new BidrequestDao(uriInfo,header).getResourceorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getResourceorderRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postBidrequest(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				BidrequestDao post;
				try {
					post=new BidrequestDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBidrequestContainer();
					rows=post.getBidrequestRowModified();
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
			public Rows postFormDataBidrequest(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				BidrequestDao post;
				try {
					post=new BidrequestDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBidrequestContainer();
					rows=post.getBidrequestRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
