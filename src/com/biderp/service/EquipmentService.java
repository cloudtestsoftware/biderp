
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
		import com.biderp.dao.EquipmentDao;
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

		//Use this URI resource with Base URL to access Equipment
		@Path("/equipment")
		public class EquipmentService {
			static Log logger = LogFactory.getLog(EquipmentService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Equipment
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getEquipmentRows() {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getEquipmentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getEquipmentRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Equipment record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getEquipmentRecord() {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getEquipmentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getEquipmentRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Equipment
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getEquipmentRowsByFilter() {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getEquipmentByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getEquipmentRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Equipment
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getEquipmentSummaryRows() {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getEquipmentSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEquipmentRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Equipment record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getEquipmentRowDeleted() {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getEquipmentRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEquipmentRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Findasset rows against object ID for Equipment
			@GET
			@Path("/{id}/findasset")
			@Produces({"application/xml"})
			public Rows getFindassetRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getFindassetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getFindassetRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Maintenance rows against object ID for Equipment
			@GET
			@Path("/{id}/maintenance")
			@Produces({"application/xml"})
			public Rows getMaintenanceRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getMaintenanceRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getMaintenanceRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Yearlycost rows against object ID for Equipment
			@GET
			@Path("/{id}/yearlycost")
			@Produces({"application/xml"})
			public Rows getYearlycostRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getYearlycostRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getYearlycostRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Depricated rows against object ID for Equipment
			@GET
			@Path("/{id}/depricated")
			@Produces({"application/xml"})
			public Rows getDepricatedRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getDepricatedRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDepricatedRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Accountdebit rows against object ID for Equipment
			@GET
			@Path("/{id}/accountdebit")
			@Produces({"application/xml"})
			public Rows getAccountdebitRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EquipmentDao(uriInfo,header).getAccountdebitRows();
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
			public Rows postEquipment(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				EquipmentDao post;
				try {
					post=new EquipmentDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postEquipmentContainer();
					rows=post.getEquipmentRowModified();
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
			public Rows postFormDataEquipment(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				EquipmentDao post;
				try {
					post=new EquipmentDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postEquipmentContainer();
					rows=post.getEquipmentRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
