
var console_xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
+"<console>"
+"<record id=\"0\">"
+"<objid isRequired=\"true\" type=\"RAW\"> </objid>"
+"<mqid isRequired=\"false\" type=\"RAW\">@mqid</mqid>"
+"<name isRequired=\"true\" type=\"VARCHAR\">@table</name>"
+"<title isRequired=\"true\" type=\"VARCHAR\">@name</title>"
+"<description isRequired=\"true\" type=\"VARCHAR\">@name</description>"
+"<status isRequired=\"true\" type=\"VARCHAR\">1</status>"
+"<keyobjid isRequired=\"false\" type=\"RAW\">@objid</keyobjid>"
+"<elapseday isRequired=\"true\" type=\"NUMBER\">0</elapseday>"
+"<entrydate isRequired=\"false\" type=\"DATE\">"+GetFormattedDate()+"</entrydate>"
+"</record>"
+"</console>";

function GetFormattedDate() {
	var todayTime = new Date();
	var month = todayTime.getMonth() ;
	var day = todayTime.getDate();
	var year = todayTime.getFullYear();
	return month + "/" + day + "/" + year;
}

function addBookmarkObjectToConsole(){
	if(last_grid && last_table){
		var name=getGridColumnValueForSelectedRow(last_grid,"name");
		var objid=getGridColumnValueForSelectedRow(last_grid,"objid");
		var data=console_xml;
		data=data.replace("@objid",objid);
		data=data.replace(/@name/g,name);
		data=data.replace("@mqid",mqid);
		data=data.replace("@table",last_table.charAt(0).toUpperCase()+last_table.substring(1));
		var posturl=www_url+"/rest/console/post?token="+token;
		ajax_post_handler(data, posturl);
		sleepnow(2000);
		addBottomActivityGrid();
	}
	
}


function openConsole(){
	master_grid_cell.setText("Console");
	workplace_toolbar_callback("console");
	
}