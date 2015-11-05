sap.ui.controller("imdb.SearchRecipeView", {

    search : function() {

	var aControls = [];
	var searchValue = sap.ui.getCore().byId("search").getValue();
	var result;
	$.ajax({
	    type : "GET",
	    url : "rest/recipe/findByName?name=" + searchValue,
	    dataType : "json",
	    async : false
	}).success(function(data) {
	    if (data.length <= 0) {
		jQuery.sap.require("sap.m.MessageToast");
		sap.m.MessageToast.show("No such element in the database");
	    } else {
		result = data;

	    }
	})
	return new sap.ui.model.json.JSONModel(result);

    }
});