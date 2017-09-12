
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
		import com.biderp.dao.ChangeDao;
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

		//Use this URI resource with Base URL to access Change
		@Path("/change")
		public class ChangeService {
			static Log logger = LogFactory.getLog(ChangeService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Change
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getChangeRows() {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getChangeRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getChangeRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Change record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getChangeRecord() {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getChangeRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getChangeRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Change
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getChangeRowsByFilter() {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getChangeByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getChangeRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Change
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getChangeSummaryRows() {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getChangeSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getChangeRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Change record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getChangeRowDeleted() {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getChangeRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getChangeRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Lognote rows against object ID for Change
			@GET
			@Path("/{id}/lognote")
			@Produces({"application/xml"})
			public Rows getLognoteRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getLognoteRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getLognoteRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Parts rows against object ID for Change
			@GET
			@Path("/{id}/parts")
			@Produces({"application/xml"})
			public Rows getPartsRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getPartsRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPartsRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Taskresource rows against object ID for Change
			@GET
			@Path("/{id}/taskresource")
			@Produces({"application/xml"})
			public Rows getTaskresourceRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getTaskresourceRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getTaskresourceRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Whattodo rows against object ID for Change
			@GET
			@Path("/{id}/whattodo")
			@Produces({"application/xml"})
			public Rows getWhattodoRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ChangeDao(uriInfo,header).getWhattodoRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getWhattodoRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postChange(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				ChangeDao post;
				try {
					post=new ChangeDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postChangeContainer();
					rows=post.getChangeRowModified();
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
			public Rows postFormDataChange(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				ChangeDao post;
				try {
					post=new ChangeDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postChangeContainer();
					rows=post.getChangeRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
