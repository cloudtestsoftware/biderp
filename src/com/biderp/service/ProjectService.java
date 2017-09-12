
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
		import com.biderp.dao.ProjectDao;
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

		//Use this URI resource with Base URL to access Project
		@Path("/project")
		public class ProjectService {
			static Log logger = LogFactory.getLog(ProjectService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Project
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getProjectRows() {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getProjectRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getProjectRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Project record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getProjectRecord() {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getProjectRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getProjectRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Project
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getProjectRowsByFilter() {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getProjectByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getProjectRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Project
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getProjectSummaryRows() {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getProjectSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getProjectRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Project record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getProjectRowDeleted() {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getProjectRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getProjectRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Jobmaster rows against object ID for Project
			@GET
			@Path("/{id}/jobmaster")
			@Produces({"application/xml"})
			public Rows getJobmasterRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getJobmasterRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getJobmasterRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Contact rows against object ID for Project
			@GET
			@Path("/{id}/contact")
			@Produces({"application/xml"})
			public Rows getContactRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getContactRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getContactRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Estimation rows against object ID for Project
			@GET
			@Path("/{id}/estimation")
			@Produces({"application/xml"})
			public Rows getEstimationRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getEstimationRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEstimationRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Projectplan rows against object ID for Project
			@GET
			@Path("/{id}/projectplan")
			@Produces({"application/xml"})
			public Rows getProjectplanRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getProjectplanRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getProjectplanRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Budget rows against object ID for Project
			@GET
			@Path("/{id}/budget")
			@Produces({"application/xml"})
			public Rows getBudgetRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getBudgetRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBudgetRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Tax rows against object ID for Project
			@GET
			@Path("/{id}/tax")
			@Produces({"application/xml"})
			public Rows getTaxRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getTaxRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getTaxRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Pomaster rows against object ID for Project
			@GET
			@Path("/{id}/pomaster")
			@Produces({"application/xml"})
			public Rows getPomasterRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new ProjectDao(uriInfo,header).getPomasterRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPomasterRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postProject(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				ProjectDao post;
				try {
					post=new ProjectDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postProjectContainer();
					rows=post.getProjectRowModified();
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
			public Rows postFormDataProject(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				ProjectDao post;
				try {
					post=new ProjectDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postProjectContainer();
					rows=post.getProjectRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
