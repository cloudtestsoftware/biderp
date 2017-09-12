package com.auth.dao;

import java.io.File;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import javax.ws.rs.core.UriInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.auth.bean.EmailImpl;

import cms.service.app.ServiceManager;
import cms.service.exceptions.AuthenticationException;
import cms.service.template.TemplateTable;
import cms.service.template.TemplateUtility;
import cms.service.util.FileUtility;


public class EmailDao extends EmailImpl {

	static Log logger = LogFactory.getLog(EmailDao.class);
	@Context UriInfo uriInfo;
	@Context  HttpHeaders header;
	
	//For north virginia
	//final String username_n_virginia = "AKIAJGRQ5GEWKSQWIXOQ";
	//final String password_n_virginia = "AlV91FKPngN0to3s/BvwoCprrqs7zO/tm7A8zdZEzVWE";
	//final string smtp="email-smtp.us-east-1.amazonaws.com";

	//for oragon
	//final String username="AKIAJKIITXVYB7TTZSGQ";
	//final String password="Aqbd3CKaZH/1tcGY2kVzqtXMNhVCNrL1v3RmBD7LRDbW";
	//final String smtp="email-smtp.us-west-2.amazonaws.com";
    
	
	
	
	public  EmailDao(UriInfo uriInfo, HttpHeaders header){
		this.uriInfo=uriInfo;
		this.header=header;
		
	}
	
	public String sendRFQEmail() throws AuthenticationException{
		Map<String,String> userdata=null;
    	String token=null;
    	String reason="";
    	String username="";
    	String subject="";
    	String objid="";
    	String body="";
    	String message="";
    	String footer="";
    	String attachments="";
    	String result="Failed to send email";
        Element resourceElm;
        
        if(!tu.isEmptyValue(uriInfo.getPathParameters().getFirst("id"))){
			objid=uriInfo.getPathParameters().getFirst("id").replace("id-", "");
		}
    	if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("token"))){
			
			userdata=ServiceManager.verifyUserToken(uriInfo.getQueryParameters().getFirst("token"));
			token=uriInfo.getQueryParameters().getFirst("token");
		}
    	

		if(userdata!=null &&!userdata.isEmpty()){
			
			username=userdata.get("username");
			
		}else{
			throw new AuthenticationException("Authentication Failed for user="+sendto+" Token ="+ token);
		}
    	
    	String sql= "select r.*, d.attachments from table_RFQEmail r,table_RFQEmailDocs d where r.objid='"+objid+"' and r.objid=d.objid(+)" ;
    	TemplateTable data=new TemplateUtility().getResultSet(sql);
    	if(data!=null && data.getRowCount()>0){
    		sendto=data.getFieldValue("sendto", data.getRowCount()-1);
    		sendby=data.getFieldValue("sendby", data.getRowCount()-1);
    		subject=data.getFieldValue("name", data.getRowCount()-1);
    		message=data.getFieldValue("message", data.getRowCount()-1);
    		//message=message.replaceAll("  ", "\n\t");
    		footer=data.getFieldValue("footer", data.getRowCount()-1);
    		attachments=data.getFieldValue("attachments", data.getRowCount()-1);
    	}
    	
    	String email_template_path=this.getWebAppPath()+"src"+File.separator+"template"+File.separator+"rfq_email.txt";
		//logger.info("email template path="+email_template_path);
		body=FileUtility.readFileContent(email_template_path);
		String uri="\n"+uriInfo.getBaseUri().toString().replace("/biderp/rest", "")+"userdoc/";
		
		String docs="";
		if(!tu.isEmptyValue(attachments)){
			docs=attachments.replaceAll("userdoc/", uri);
		}
		
		body=body.replace("@email", sendto);
		body=body.replace("@taskname", subject);
		body=body.replace("@message", message);
		body=body.replace("@attachment", docs);
		
    	
			
		try{
			doSetup();
			if(sendmail(  subject, body)){
				result="Success! We sent your email message to "+sendto;
			}else{
				result="Failed to send email to "+sendto;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
      
        //logger.info(result);
 
        return result ;
	}
	

	
}
