function updateCurrencyMenu(grid){
		var url=www_url+"/rest/authorization/modules?token="+token+"&username="+username;
		modules=getSyncJsonResponse(url);
		biderp_toolbar.removeItem("btn_currency");
		setupCurrency();
		addCurrencyButtonSelect();
		
}