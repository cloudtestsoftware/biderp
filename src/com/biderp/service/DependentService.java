
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
		import com.biderp.dao.DependentDao;
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

		//Use this URI resource with Base URL to access Dependent
		@Path("/dependent")
		public class DependentService {
			static Log logger = LogFactory.getLog(DependentService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Dependent
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getDependentRows() {
				Rows rows = null;
				try {
					rows=new DependentDao(uriInfo,header).getDependentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getDependentRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Dependent record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getDependentRecord() {
				Rows rows = null;
				try {
					rows=new DependentDao(uriInfo,header).getDependentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getDependentRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Dependent
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getDependentRowsByFilter() {
				Rows rows = null;
				try {
					rows=new DependentDao(uriInfo,header).getDependentByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getDependentRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Dependent
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getDependentSummaryRows() {
				Rows rows = null;
				try {
					rows=new DependentDao(uriInfo,header).getDependentSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDependentRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Dependent record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getDependentRowDeleted() {
				Rows rows = null;
				try {
					rows=new DependentDao(uriInfo,header).getDependentRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDependentRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postDependent(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				DependentDao post;
				try {
					post=new DependentDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postDependentContainer();
					rows=post.getDependentRowModified();
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
			public Rows postFormDataDependent(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				DependentDao post;
				try {
					post=new DependentDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postDependentContainer();
					rows=post.getDependentRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
