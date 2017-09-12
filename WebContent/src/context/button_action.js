//use this file to add a custom action to the existing button

var debug=false;

    // [button id].method

var customaction=  {
	            "artifacts": {"action":["Upload:doUpload.notify_all_bidder"]},
	            "invitation": {"action":["invitation:status:2.send_email_to_bidder"]},
	            "rfqemail": {"action":["rfqemail:stage:100.send_rfq_email"]},
	           
	        };



function callCustomAction(grid,name,table){
	var pos=0;
	try{
		if(table &&grid){
			var actions= customaction[table].action; 
			if(actions){				
				
				for(var action in actions){
					var vals=actions[action].split(".");
					var len=vals.length;
					if(len>=0 && name.indexOf(vals[0])>=0){
						eval(vals[len-1]+"(grid,table)");
					}
					
				}
			}
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" "+vals[len-1]+"(grid,table) not implemented!");
		}
	}
	return null;
}



function getButtonHide(table){
	try{
		if(table){
			return customaction[table].buttonhide; 
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButtonHide : "+table);
		}
	}
	return null;
}

function getRowSelectAction(table){
	try{
		if(table){
			return customaction[table].rowselect; 
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getRowSelectAction : "+table);
		}
	}
	return null;
}






