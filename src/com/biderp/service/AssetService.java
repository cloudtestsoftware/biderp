
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
		import com.biderp.dao.AssetDao;
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

		//Use this URI resource with Base URL to access Asset
		@Path("/asset")
		public class AssetService {
			static Log logger = LogFactory.getLog(AssetService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Asset
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getAssetRows() {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAssetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getAssetRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Asset record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getAssetRecord() {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAssetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getAssetRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Asset
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getAssetRowsByFilter() {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAssetByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getAssetRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Asset
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getAssetSummaryRows() {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAssetSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAssetRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Asset record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getAssetRowDeleted() {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAssetRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAssetRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Maintenance rows against object ID for Asset
			@GET
			@Path("/{id}/maintenance")
			@Produces({"application/xml"})
			public Rows getMaintenanceRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getMaintenanceRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getMaintenanceRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Servicecost rows against object ID for Asset
			@GET
			@Path("/{id}/servicecost")
			@Produces({"application/xml"})
			public Rows getServicecostRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getServicecostRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getServicecostRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Depricated rows against object ID for Asset
			@GET
			@Path("/{id}/depricated")
			@Produces({"application/xml"})
			public Rows getDepricatedRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getDepricatedRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDepricatedRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Address rows against object ID for Asset
			@GET
			@Path("/{id}/address")
			@Produces({"application/xml"})
			public Rows getAddressRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAddressRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAddressRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Accountdebit rows against object ID for Asset
			@GET
			@Path("/{id}/accountdebit")
			@Produces({"application/xml"})
			public Rows getAccountdebitRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new AssetDao(uriInfo,header).getAccountdebitRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAccountdebitRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postAsset(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				AssetDao post;
				try {
					post=new AssetDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postAssetContainer();
					rows=post.getAssetRowModified();
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
			public Rows postFormDataAsset(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				AssetDao post;
				try {
					post=new AssetDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postAssetContainer();
					rows=post.getAssetRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
