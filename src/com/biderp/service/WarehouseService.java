
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
		import com.biderp.dao.WarehouseDao;
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

		//Use this URI resource with Base URL to access Warehouse
		@Path("/warehouse")
		public class WarehouseService {
			static Log logger = LogFactory.getLog(WarehouseService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Warehouse
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getWarehouseRows() {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getWarehouseRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getWarehouseRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Warehouse record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getWarehouseRecord() {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getWarehouseRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getWarehouseRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Warehouse
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getWarehouseRowsByFilter() {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getWarehouseByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getWarehouseRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Warehouse
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getWarehouseSummaryRows() {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getWarehouseSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getWarehouseRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Warehouse record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getWarehouseRowDeleted() {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getWarehouseRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getWarehouseRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Address rows against object ID for Warehouse
			@GET
			@Path("/{id}/address")
			@Produces({"application/xml"})
			public Rows getAddressRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getAddressRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAddressRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Warehouseline rows against object ID for Warehouse
			@GET
			@Path("/{id}/warehouseline")
			@Produces({"application/xml"})
			public Rows getWarehouselineRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new WarehouseDao(uriInfo,header).getWarehouselineRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getWarehouselineRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postWarehouse(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				WarehouseDao post;
				try {
					post=new WarehouseDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postWarehouseContainer();
					rows=post.getWarehouseRowModified();
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
			public Rows postFormDataWarehouse(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				WarehouseDao post;
				try {
					post=new WarehouseDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postWarehouseContainer();
					rows=post.getWarehouseRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
