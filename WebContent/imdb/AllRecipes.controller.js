sap.ui
	.controller(
		"imdb.AllRecipes",
		{

		    getRecipes : function() {

			var result;
			$.ajax({
			    type : "GET",
			    url : "rest/recipe/findAll",
			    dataType : "json",
			    async : false,
			    statusCode : {
				200 : function(data) {
				    result = data;
				}
			    }
			})
			return new sap.ui.model.json.JSONModel(result);
		    },

		    render : function() {

			var aControls = [];
			var allRecipesModel = sap.ui.getCore().getModel(
				"allRecipesModel");
			var controller = sap.ui.controller("imdb.AllRecipes");
			var data = controller.getRecipes();
			for (var i = 0; i < data.getProperty("/").length; i++) {

			    var tile = new sap.m.StandardTile({
				icon : "sap-icon://meal",
				title : data.getProperty("/" + i + "/name"),
				type : "None",
				number : data.getProperty("/" + i
					+ "/servingValue"),
				numberUnit : data.getProperty("/" + i
					+ "/servingUnit/unit"),
				infoState : "Success"
			    });
			    tile
				    .attachPress(
					    {
						data : data
							.getProperty("/" + i)
					    },
					    function(oEvent, oData) {
						var oModel = new sap.ui.model.json.JSONModel(
							oData);
						var dialog = new sap.m.Dialog(
							{
							    title : "Nutrien Report",
							    icon : "sap-icon://activity-items",
							    endButton : new sap.m.Button(
								    {
									text : "Close",
									icon : "sap-icon://sys-cancel-2",
									press : function() {
									    dialog
										    .close();
									}
								    }),
							    beginButton : new sap.m.Button(
								    {
									text : "Delete",
									type : "Reject",
									icon : "sap-icon://delete",
									press : function() {
									    $
										    .ajax({
											type : "DELETE",
											url : "rest/recipe/delete?id="
												+ oModel
													.getProperty("/data/id"),
											dataType : "text/plain",
											statusCode : {
											    200 : function(
												    data) {
												jQuery.sap
													.require("sap.m.MessageToast");
												sap.m.MessageToast
													.show(data.responseText);
												dialog
													.close();
												location
													.reload();
											    }
											}
										    })

									}
								    })
							});
						var list = new sap.m.List({});

						console.log(oModel.getJSON());
						console
							.log(oModel
								.getProperty("/data/nutrients/"
									+ 0
									+ "/nutrient/name"));
						for (var i = oModel
							.getProperty("/data/nutrients/").length - 1; i >= 0; i--) {
						    list
							    .addItem(new sap.m.StandardListItem(
								    {
									title : oModel
										.getProperty("/data/nutrients/"
											+ i
											+ "/nutrient/name"),
									info : oModel
										.getProperty("/data/nutrients/"
											+ i
											+ "/value")
										+ oModel
											.getProperty("/data/nutrients/"
												+ i
												+ "/nutrient/unit/unit")
								    }));
						}
						dialog.addContent(list).open();

					    })
			    aControls.push(tile);
			}
			return aControls;
		    }
		});