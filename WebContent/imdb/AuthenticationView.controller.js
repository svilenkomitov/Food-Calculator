sap.ui.controller("imdb.AuthenticationView", {

    login : function() {

	var username;
	$.ajax({
	    url : "rest/user/getAttributes",
	    type : "GET",
	    dataType : "json",
	    async : false,
	}).success(function(userData) {
	    username = userData["firstName"] + " " + userData["lastName"];

	}).fail(function(data) {
	    alert("Authentication failed")
	});

	return username;
    }
});