
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
		import com.biderp.dao.EmployeeDao;
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

		//Use this URI resource with Base URL to access Employee
		@Path("/employee")
		public class EmployeeService {
			static Log logger = LogFactory.getLog(EmployeeService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Employee
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getEmployeeRows() {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getEmployeeRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getEmployeeRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Employee record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getEmployeeRecord() {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getEmployeeRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getEmployeeRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get all rows with filter for Employee
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getEmployeeRowsByFilter() {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getEmployeeByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getEmployeeRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get summary row against object ID for Employee
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getEmployeeSummaryRows() {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getEmployeeSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEmployeeRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Employee record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getEmployeeRowDeleted() {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getEmployeeRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEmployeeRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Contact rows against object ID for Employee
			@GET
			@Path("/{id}/contact")
			@Produces({"application/xml"})
			public Rows getContactRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getContactRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getContactRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Payrole rows against object ID for Employee
			@GET
			@Path("/{id}/payrole")
			@Produces({"application/xml"})
			public Rows getPayroleRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getPayroleRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPayroleRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Benifit rows against object ID for Employee
			@GET
			@Path("/{id}/benifit")
			@Produces({"application/xml"})
			public Rows getBenifitRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getBenifitRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBenifitRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Dependent rows against object ID for Employee
			@GET
			@Path("/{id}/dependent")
			@Produces({"application/xml"})
			public Rows getDependentRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getDependentRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDependentRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Deduction rows against object ID for Employee
			@GET
			@Path("/{id}/deduction")
			@Produces({"application/xml"})
			public Rows getDeductionRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getDeductionRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getDeductionRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Addition rows against object ID for Employee
			@GET
			@Path("/{id}/addition")
			@Produces({"application/xml"})
			public Rows getAdditionRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getAdditionRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAdditionRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Substruction rows against object ID for Employee
			@GET
			@Path("/{id}/substruction")
			@Produces({"application/xml"})
			public Rows getSubstructionRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getSubstructionRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSubstructionRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Vacation rows against object ID for Employee
			@GET
			@Path("/{id}/vacation")
			@Produces({"application/xml"})
			public Rows getVacationRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getVacationRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getVacationRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Earnedvacation rows against object ID for Employee
			@GET
			@Path("/{id}/earnedvacation")
			@Produces({"application/xml"})
			public Rows getEarnedvacationRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getEarnedvacationRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEarnedvacationRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Benifitchange rows against object ID for Employee
			@GET
			@Path("/{id}/benifitchange")
			@Produces({"application/xml"})
			public Rows getBenifitchangeRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getBenifitchangeRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBenifitchangeRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Tax rows against object ID for Employee
			@GET
			@Path("/{id}/tax")
			@Produces({"application/xml"})
			public Rows getTaxRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new EmployeeDao(uriInfo,header).getTaxRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getTaxRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postEmployee(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				EmployeeDao post;
				try {
					post=new EmployeeDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postEmployeeContainer();
					rows=post.getEmployeeRowModified();
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
			public Rows postFormDataEmployee(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				EmployeeDao post;
				try {
					post=new EmployeeDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postEmployeeContainer();
					rows=post.getEmployeeRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
