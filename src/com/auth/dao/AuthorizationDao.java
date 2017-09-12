package com.auth.dao;

import java.util.HashMap;
import java.util.Map;
import com.auth.*;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;

import com.auth.bean.Authorization;

import javax.ws.rs.core.UriInfo;

import cms.service.app.ApplicationConstants;
import cms.service.app.ServiceManager;
import cms.service.app.AccessToken;
import cms.service.template.*;




public enum AuthorizationDao {
       instance;
     
       private Map<String, Authorization> contentProvider;
      
       
       private AuthorizationDao() {
    	 
       }
      
       public Map<String, Authorization> getModel(String loginname, String password,String remoteclient){

    	   Authorization auth=new Authorization();
    	   AccessToken access=ServiceManager.verifyLogin(loginname,password,remoteclient);
    	   contentProvider = new HashMap<String, Authorization>();
            if(access!=null){
            	auth.setLoginname(loginname);
            	auth.setToken(access.getToken()+ApplicationConstants.IPSEPERATOR+remoteclient+ApplicationConstants.IPSEPERATOR+loginname);
            	auth.setModules(access.getModules());
            	auth.setMsg("Authorization Success!");
            }else{
            	auth.setLoginname(loginname);
            	auth.setToken("");
            	auth.setModules("");
            	auth.setMsg("Authorization Failed!");
            }
            contentProvider.put("1", auth);
              return contentProvider;
       }
       
       public Map<String, Authorization> getModules(String token,String username,String groupuser){

    	   Authorization auth=new Authorization();
    	   String modules="";
    	   String currency=ServiceManager.getUserCurrency(groupuser);
    	   if(username.equals("admin@biderp.com")){
    		   modules="app_groupadmin,app_useradmin,";
    	   }else if(username.equals(groupuser)){
    		   modules="app_useradmin,app_erpadmin";
    	   }else{
    		   modules="app_erpadmin,app_groupadmin,"+ServiceManager.getUserModules(username);
    	   }
    	  
    	   contentProvider = new HashMap<String, Authorization>();
    	   auth.setToken(token);
    	   auth.setLoginname(username);
    	   auth.setModules(modules);
    	   auth.setCurrency(currency);
    	   auth.setMsg("Success!");
            
            contentProvider.put("1", auth);
            return contentProvider;
       }
       
      
}