sap.ui
	.controller(
		"imdb.NewRecipeView",
		{

		    save : function() {

			var controller = sap.ui
				.controller("imdb.NewRecipeView");
			var report = controller.getReport();

			$.ajax({
			    type : "POST",
			    url : "rest/recipe/add",
			    contentType : "application/json",
			    dataType : "application/json",
			    traditional : true,
			    data : report.getJSON(),
			    statusCode : {
				200 : function(data) {
				    jQuery.sap.require("sap.m.MessageToast");
				    sap.m.MessageToast.show(data.responseText);
				    location.reload();
				}
			    }

			})
		    },

		    getProducts : function() {
			var productName = newRecipeModel
				.getProperty("/productNameInput");
			var names = new Array();
			var ids = new Array();
			$
				.ajax({
				    url : "rest/products?name=" + productName,
				    type : "GET",
				    contentType : "json",
				    async : false,
				    success : function(json) {
					for (var i = 0; i < json.length; i++) {
					    var obj = json[i];
					    names.push(obj["name"]);
					    ids.push(obj["id"]);
					}
				    },
				    statusCode : {
					200 : function() {
					    var dialog = new sap.m.Dialog(
						    {
							title : "Choose product",
							endButton : new sap.m.Button(
								{
								    text : "Back",
								    icon : "sap-icon://sys-cancel-2",
								    press : function() {
									dialog
										.close();
								    }
								})
						    });
					    var productValueInput = newRecipeModel
						    .getProperty("/productValueInput");
					    var productUnitInput = sap.ui
						    .getCore().byId(
							    "productUnitInput")
						    .getSelectedItem()
						    .getText();

					    var list4 = new sap.m.List({});
					    for (var i = 0; i < ids.length; i++) {
						var name = names[i];
						var id = ids[i];
						list4
							.addItem(new sap.m.StandardListItem(
								{
								    type : "Active",
								    title : name,
								    description : id,
								    info : productValueInput
									    + " "
									    + productUnitInput,
								    press : function() {
									var info = this
										.getInfo()
										.split(
											" ");
									var unit = info[1];
									var value = info[0];
									var ndbno = this
										.getDescription();

									var dataJSON = {
									    name : "no",
									    unit : {
										"unit" : unit
									    },
									    value : value,
									    serving_unit : {
										"unit" : unit
									    },
									    serving_value : value,
									    thermal_treatment : {
										"type" : "none"
									    },
									    products : [ {
										name : name,
										value : value,
										unit : {
										    "unit" : unit
										},
										ndbno : ndbno
									    } ]
									};
									var oModel = new sap.ui.model.json.JSONModel(
										dataJSON);
									var report;
									var nutrients;
									$
										.ajax({
										    type : "POST",
										    url : "rest/newRecipe/create",
										    contentType : "application/json",
										    dataType : "json",
										    mimeType : 'application/json',
										    async : false,
										    data : oModel
											    .getJSON(),
										    success : function(
											    data) {
											report = new sap.ui.model.json.JSONModel(
												data);
											nutrients = report
												.getProperty("/nutrients");
										    }
										});
									var dialog2 = new sap.m.Dialog(
										{
										    title : this
											    .getTitle()
											    + "|"
											    + this
												    .getDescription(),
										    icon : "sap-icon://activity-items",
										    endButton : new sap.m.Button(
											    {
												text : "Close",
												icon : "sap-icon://sys-cancel-2",
												press : function() {
												    dialog2
													    .close();
												}
											    }),
										    beginButton : new sap.m.Button(
											    {
												text : "Accept",
												icon : "sap-icon://accept",
												type : "Accept",
												press : function() {
												    var newRecipeModel = sap.ui
													    .getCore()
													    .getModel(
														    "newRecipeModel");
												    var productName2 = newRecipeModel
													    .getProperty("/productNameInput");
												    var list2 = sap.ui
													    .getCore()
													    .byId(
														    "customListItem");
												    var productName = this
													    .getParent()
													    .getContent()[1];
												    var productNDB = this
													    .getParent()
													    .getContent()[2];
												    var nameID = this
													    .getParent()
													    .getParent()
													    .getTitle()
													    .split(
														    "|");
												    list2
													    .addContent(new sap.ui.layout.HorizontalLayout(
														    {
															content : [
																new sap.m.Button(
																	{
																	    icon : "sap-icon://nutrition-activity",
																	    type : "Transparent",
																	    enabled : false,
																	}),
																new sap.m.Text(
																	{
																	    width : "200px",
																	    text : nameID[0]
																	}),
																new sap.m.Text(
																	{
																	    width : "50px",
																	    text : productValueInput,
																	    textAlign : "Right"
																	}),
																new sap.m.Text(
																	{
																	    width : "30px",
																	    text : productUnitInput,
																	    textAlign : "Right",
																	}),
																new sap.m.Text(
																	{
																	    visible : false,
																	    text : nameID[1]
																	}),
																new sap.m.Button(
																	{
																	    icon : "sap-icon://sys-cancel",
																	    type : "Transparent",
																	    press : function() {
																		this
																			.getParent()
																			.getParent()
																			.removeContent(
																				this
																					.getParent());
																	    }
																	}), ]
														    }));
												    this
													    .getParent()
													    .close();
												}
											    })
										});
									var list = new sap.m.List(
										{});

									for (var i = nutrients.length - 1; i >= 0; i--) {
									    var stringNutrient = "/nutrients/"
										    + i
										    + "/nutrient/name";
									    var stringValue = "/nutrients/"
										    + i
										    + "/value";
									    var stringUnit = "/nutrients/"
										    + i
										    + "/nutrient/unit/unit";
									    var nutrientNutrient = report
										    .getProperty(stringNutrient);
									    var nutrientValue = report
										    .getProperty(stringValue);
									    var nutrientUnit = report
										    .getProperty(stringUnit);

									    list
										    .addItem(new sap.m.StandardListItem(
											    {
												title : nutrientNutrient,
												info : nutrientValue
													+ " "
													+ nutrientUnit
											    }));
									}
									dialog2
										.addContent(
											list)
										.open();

								    }
								}))
					    }

					    dialog.addContent(list4).open();
					},

					404 : function() {
					    jQuery.sap
						    .require("sap.m.MessageToast");
					    sap.m.MessageToast
						    .show("No such product in the database");
					}
				    }

				})

		    },

		    report : function() {

			var controller = sap.ui
				.controller("imdb.NewRecipeView");
			var report = controller.getReport();
			if (report === false) {
			    jQuery.sap.require("sap.m.MessageToast");
			    sap.m.MessageToast.show("Invalid value");
			    return;
			}
			sap.ui
				.getCore()
				.byId("fat-saturated")
				.setText(
					report
						.getProperty("/nutrients/8/value")
						+ " "
						+ report
							.getProperty("/nutrients/8/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("fat-unsaturated")
				.setText(
					report
						.getProperty("/nutrients/7/value")
						+ " "
						+ report
							.getProperty("/nutrients/7/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("fat-trans")
				.setText(
					report
						.getProperty("/nutrients/6/value")
						+ " "
						+ report
							.getProperty("/nutrients/6/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("fiber")
				.setText(
					report
						.getProperty("/nutrients/5/value")
						+ " "
						+ report
							.getProperty("/nutrients/5/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("sugar")
				.setText(
					report
						.getProperty("/nutrients/4/value")
						+ " "
						+ report
							.getProperty("/nutrients/4/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("protein")
				.setText(
					report
						.getProperty("/nutrients/3/value")
						+ " "
						+ report
							.getProperty("/nutrients/3/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("sodium")
				.setText(
					report
						.getProperty("/nutrients/2/value")
						+ " "
						+ report
							.getProperty("/nutrients/2/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("cholesterol")
				.setText(
					report
						.getProperty("/nutrients/1/value")
						+ " "
						+ report
							.getProperty("/nutrients/1/nutrient/unit/unit"));
			sap.ui
				.getCore()
				.byId("calories")
				.setText(
					report
						.getProperty("/nutrients/0/value")
						+ " "
						+ report
							.getProperty("/nutrients/0/nutrient/unit/unit"));
			sap.ui.getCore().byId("save").setEnabled(true);
		    },

		    addProperty : function(key, value) {

			var property = '"' + key + '":"' + value + '"';

			return property;
		    },

		    createRecipe2 : function() {

			var newRecipeModel = sap.ui.getCore().getModel(
				"newRecipeModel");
			var recipeName = newRecipeModel
				.getProperty("/recipeName");
			var servingValueInput = newRecipeModel
				.getProperty("/servingValueInput");
			var servingUnitInput = sap.ui.getCore().byId(
				"servingUnitInput").getSelectedItem().getText();
			var thermalThreatment = sap.ui.getCore().byId(
				"treatmentInput").getSelectedItem().getText();
			var totalValueInput = newRecipeModel
				.getProperty("/totalValueInput");
			var totalUnitInput = sap.ui.getCore().byId(
				"totalUnitInput").getSelectedItem().getText();
			var allProducts = sap.ui.getCore().byId(
				"customListItem").getContent();
			var controller = sap.ui
				.controller("imdb.NewRecipeView");
			if (!controller.isValid(servingValueInput)
				|| !controller.isValid(totalValueInput)) {
			    jQuery.sap.require("sap.m.MessageToast");
			    sap.m.MessageToast.show("Invalid value");
			    return false;
			}
			var productS = [];

			for (var i = 0; i < allProducts.length; i++) {
			    var product = allProducts[i].getContent();
			    var pr = {
				name : product[1].getText(),
				value : product[2].getText(),
				unit : {
				    "unit" : product[3].getText()
				},
				ndbno : product[4].getText()
			    }
			    productS.push(pr);
			}

			var dataJSON = {
			    name : recipeName,
			    unit : {
				"unit" : totalUnitInput
			    },
			    value : totalValueInput,
			    serving_unit : {
				"unit" : servingUnitInput
			    },
			    serving_value : servingValueInput,
			    thermal_treatment : {
				"type" : thermalThreatment
			    },
			    products : productS
			};
			return dataJSON;
		    },
		    getReport : function() {

			var controller = sap.ui
				.controller("imdb.NewRecipeView");
			var json = controller.createRecipe2();
			if (json === false) {
			    return false;
			}

			var oModel = new sap.ui.model.json.JSONModel(json);
			var report;
			$.ajax({
			    type : "POST",
			    url : "rest/newRecipe/create",
			    contentType : "application/json",
			    dataType : "json",
			    mimeType : 'application/json',
			    async : false,
			    data : oModel.getJSON(),
			    success : function(data) {
				report = new sap.ui.model.json.JSONModel(data);
			    },
			});

			return report;
		    },

		    isValid : function(n) {

			return (parseFloat(n) == n || parseInt(n) == n)
				&& n >= 0;
		    }
		});