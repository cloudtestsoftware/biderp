
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
		import com.biderp.dao.JobestDao;
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

		//Use this URI resource with Base URL to access Jobest
		@Path("/jobest")
		public class JobestService {
			static Log logger = LogFactory.getLog(JobestService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Jobest
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getJobestRows() {
				Rows rows = null;
				try {
					rows=new JobestDao(uriInfo,header).getJobestRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getJobestRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Jobest record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getJobestRecord() {
				Rows rows = null;
				try {
					rows=new JobestDao(uriInfo,header).getJobestRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getJobestRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Jobest
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getJobestRowsByFilter() {
				Rows rows = null;
				try {
					rows=new JobestDao(uriInfo,header).getJobestByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getJobestRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Jobest
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getJobestSummaryRows() {
				Rows rows = null;
				try {
					rows=new JobestDao(uriInfo,header).getJobestSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getJobestRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postJobest(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				JobestDao post;
				try {
					post=new JobestDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postJobestContainer();
					rows=post.getJobestRowModified();
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
			public Rows postFormDataJobest(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				JobestDao post;
				try {
					post=new JobestDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postJobestContainer();
					rows=post.getJobestRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
