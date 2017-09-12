var debug=false;

//use this to add a custom button
var custombutton=  {
	            "artifacts": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
	            "quizreply": {"button":["Upload:doUploadMultiFile","Download:doDownload"],"upload_column":"url",
	            				"doctable":"attachment","relation1":"attachment2quizreply","relation2":"attachment2vendorbid",
	            				"namecols":"quizindex,name","description":"description"},
	            "rfqemail": {"button":["Upload:doUploadMultiFile","Download:doDownload"],"upload_column":"url",
		            		    "doctable":"emaildocs","relation1":"emaildocs2rfqemail","relation2":"emaildocs2rfq",
		            			"namecols":"name","description":"name"},				
	           
	            "bidartifacts": {"button":["Download:doDownload"],"upload_column":"url"},
	            "attachment": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
	            
	            "bids": {"button":["Compare Reply:compareAndExportBidQuizReply","Compare Cost:compareAndExportBidItemCost","Compare Points:compareAndExportBidPoints"]},
	            "qrinfo": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
				};



function addButton(toolbar,table){
	var pos=0;
	try{
		if(table &&toolbar){
			var buttons= custombutton[table].button; 
			if(buttons){
				
				toolbar.forEachItem(function(itemId){
				    // your code here
					var itemtext=toolbar.getItemText(itemId);
					if(itemtext.indexOf("Remove")>=0){
						 pos=toolbar.getPosition(itemId);
					}
				});
				
				for(var button in buttons){
					var vals=buttons[button].split(":");
					if(pos>=0){
						toolbar.addButton("custom:"+table+":"+buttons[button], ++pos, vals[0], "red.gif", null);
					}
					
				}
				
				//addEvent(toolbar,"action_button","onClick");
			}
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButton : "+table);
		}
	}
	return null;
}






function getUploadColumn(table){
	try{
		if(table){
			return custombutton[table].upload_column; 
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButtonHide : "+table);
		}
	}
	return null;
}


function doDownload(grid,name){
	//dhtmlx.message('doDownload');
	var table;
	var ww=www_url.split("/biderp")[0]+"/";
	var items=name.split(":");
	if(items &&items.length==4){
		table=items[1];
	}
	
	html_cell.attachHTMLString("<div id=\"downloadId\" style=\"display: none;\"></div>");
	
	var elm =document.getElementById("downloadId");

	var link = document.createElement("a");
    var col=getUploadColumn(table);
    var uri=getGridColumnValueForSelectedRow(grid,col);
    var docname=getGridColumnValueForSelectedRow(grid,"name");
    link.download = docname;
     var ext;
    var len=uri.split(".").length;
    if(uri&& len>1){
        ext= "."+uri.split(".")[len-1];
        link.download = docname+ext;
    }
    elm.appendChild(link);
    link.href = ww+uri;
    link.click();
}

function downloadLink(uri,docname){
	//dhtmlx.message('doDownload');
	var table;
	var ww=www_url.split("/biderp")[0]+"/";
	var items=name.split(":");
	if(items &&items.length==4){
		table=items[1];
	}
	
	//html_cell.attachHTMLString("<div id=\"downloadId\" style=\"display: none;\"></div>");
	
	var elm =document.getElementById("downloadId");

	var link = document.createElement("a");
    var col=getUploadColumn(table);
    
    link.download = docname;
     var ext;
    var len=uri.split(".").length;
    if(uri&& len>1){
        ext= "."+uri.split(".")[len-1];
        link.download = docname+ext;
    }
    elm.appendChild(link);
    link.href = ww+uri;
    link.click();
}

//https://forum.dhtmlx.com/viewtopic.php?f=17&t=37712
function doUpload(grid,name) {
	
	var table;
	var relation;
	var parent_objid;
	var items=name.split(":");
	if(items &&items.length==4){
		table=items[1];
	}
	
	if(parent &&grid&&grid.getSelectedRowId()){
		//relation=getRelation(menuid,table);
		parent_objid=getGridColumnValueForSelectedRow(grid,"objid");
	}else{
		dhtmlx.alert("please select a row in the parent grid="+parent);
		return;
	}
	var upload_url=www_url+'/rest/file/upload?token='+token+"&table="+table.toLowerCase();
	if( parent_objid){
		upload_url+="&id="+parent_objid+"&fld="+getUploadColumn(table);
		//upload_url+="&relation="+relation+"="+parent_objid;
	}
	var formData = [
		{type: "fieldset", label: "Uploader", list:[
			{type: "upload", name: "myFiles", inputWidth: 250, url: upload_url, autoStart: true, swfPath: "uploader.swf", swfUrl:upload_url }
		]}
	];
	
	
	var attachment_form=html_cell.attachForm(formData);
	
	
	attachment_form.attachEvent("onUploadFile",function(realName, serverName){
		//dhtmlx.alert("<b>onUploadFile</b>, real name: "+realName+", server name: "+serverName);
	});
	
	
	attachment_form.attachEvent("onUploadFail",function(realName){
		
		//reloadGrid(table);
	});
	
	
	
	attachment_form.attachEvent("onUploadComplete",function(count){
		/*
        attachment_form.getUploader('myFiles').buttons.info.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.upload.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.cancel.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.clear.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.browse.style.border = "solid green 2px";
        */
      //reloadGrid(table);
     });
     
     attachment_form.attachEvent("onFileAdd",function(realName){
    	 /*
        attachment_form.getUploader('myFiles').buttons.info.style.border = "solid red 2px";
        attachment_form.getUploader('myFiles').buttons.upload.style.border = "solid red 2px";
        attachment_form.getUploader('myFiles').buttons.cancel.style.border = "solid red 2px";
        attachment_form.getUploader('myFiles').buttons.clear.style.border = "solid red 2px";      
        attachment_form.getUploader('myFiles').buttons.browse.style.border = "solid red 2px"; 
        */
      //reloadGrid(table);
     });
	
	
}

function doUploadMultiFile(grid,name) {
	
	var table;
	var relation;
	var parent_objid;
	var items=name.split(":");
	
	var name;
	var rel2objid;
	
	if(items &&items.length==4){
		table=items[1];
	}

	var doctable=custombutton[table].doctable; 
	var relation1=custombutton[table].relation1; 
	var relation2=custombutton[table].relation2; 
	var namecols=custombutton[table].namecols; 
	var description=custombutton[table].description; 
	
	if(doctable==null && relation1==null){
		dhtmlx.alert("Can not upload multi file. Please add attribute [doctable] and [relation] in the custombutton JSON object!");
		return;
	}
	
	if(parent &&grid&&grid.getSelectedRowId()){
		//relation=getRelation(menuid,table);
		parent_objid=getGridColumnValueForSelectedRow(grid,"objid");
		if(namecols){
			var items=namecols.split(",");
			name="";
			if(items && items.length>0){
				for(var i=0; i<items.length; i++){
					if(i==0){
						name+= getGridColumnValueForSelectedRowWithoutColon(grid,items[i]);
					}else{
						name+=" - "+ getGridColumnValueForSelectedRowWithoutColon(grid,items[i]);
					}
					
				}
			}
		}else{
			name=getGridColumnValueForSelectedRow(grid,"name");
		}
		if(!description){
			description="any valid description";
		}else{
			description= getGridColumnValueForSelectedRowWithoutColon(grid,description);
		}
		rel2objid=getGridColumnValueForSelectedRow(grid,relation2);
		if(!rel2objid){
			rel2objid=getGridColumnValueForSelectedRow(master_grid,"objid");
		}
	}else{
		dhtmlx.alert("please select a row in the parent grid="+parent);
		return;
	}
	var upload_url=www_url+'/rest/file/multiupload?token='+token+"&table="+doctable.toLowerCase()+
			"&relation1="+relation1+"&name="+name+"&relation2="+relation2+"&rel2objid="+rel2objid +
			"&description="+description;
	if( parent_objid){
		upload_url+="&id="+parent_objid+"&fld="+getUploadColumn(table);
		//upload_url+="&relation="+relation+"="+parent_objid;
	}
	var formData = [
		{type: "fieldset", label: "Uploader", list:[
			{type: "upload", name: "myFiles", inputWidth: 250, url: upload_url, autoStart: true, swfPath: "uploader.swf", swfUrl:upload_url }
		]}
	];
	
	
	var attachment_form=html_cell.attachForm(formData);
	
	
	attachment_form.attachEvent("onUploadFile",function(realName, serverName){
		//dhtmlx.alert("<b>onUploadFile</b>, real name: "+realName+", server name: "+serverName);
	});
	
	
	attachment_form.attachEvent("onUploadFail",function(realName){
		
		//reloadGrid(table);
	});
	
	
	
	attachment_form.attachEvent("onUploadComplete",function(count){
		/*
        attachment_form.getUploader('myFiles').buttons.info.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.upload.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.cancel.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.clear.style.border = "solid green 2px";
        attachment_form.getUploader('myFiles').buttons.browse.style.border = "solid green 2px";
        */
      //reloadGrid(table);
     });
     
     attachment_form.attachEvent("onFileAdd",function(realName){
    	 /*
        attachment_form.getUploader('myFiles').buttons.info.style.border = "solid red 2px";
        attachment_form.getUploader('myFiles').buttons.upload.style.border = "solid red 2px";
        attachment_form.getUploader('myFiles').buttons.cancel.style.border = "solid red 2px";
        attachment_form.getUploader('myFiles').buttons.clear.style.border = "solid red 2px";      
        attachment_form.getUploader('myFiles').buttons.browse.style.border = "solid red 2px"; 
        */
      //reloadGrid(table);
     });
	
	
}

function deleteAttachedFile(table,grid,objid){
	if(table && grid){
		var  filepath=getGridColumnValueForSelectedRow(grid,"url");
	    var url=www_url+'/rest/file/delete?token='+token+"&table="+table.toLowerCase() +"&filename="+filepath;
		var xml=getSyncResponse(url);
	}

}


