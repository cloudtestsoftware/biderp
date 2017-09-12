
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
		import com.biderp.dao.SitetotalDao;
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

		//Use this URI resource with Base URL to access Sitetotal
		@Path("/sitetotal")
		public class SitetotalService {
			static Log logger = LogFactory.getLog(SitetotalService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Sitetotal
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getSitetotalRows() {
				Rows rows = null;
				try {
					rows=new SitetotalDao(uriInfo,header).getSitetotalRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSitetotalRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Sitetotal record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getSitetotalRecord() {
				Rows rows = null;
				try {
					rows=new SitetotalDao(uriInfo,header).getSitetotalRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSitetotalRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Sitetotal
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getSitetotalRowsByFilter() {
				Rows rows = null;
				try {
					rows=new SitetotalDao(uriInfo,header).getSitetotalByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSitetotalRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Sitetotal
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getSitetotalSummaryRows() {
				Rows rows = null;
				try {
					rows=new SitetotalDao(uriInfo,header).getSitetotalSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSitetotalRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postSitetotal(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				SitetotalDao post;
				try {
					post=new SitetotalDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postSitetotalContainer();
					rows=post.getSitetotalRowModified();
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
			public Rows postFormDataSitetotal(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				SitetotalDao post;
				try {
					post=new SitetotalDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postSitetotalContainer();
					rows=post.getSitetotalRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
