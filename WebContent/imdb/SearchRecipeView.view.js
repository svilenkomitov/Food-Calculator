sap.ui
	.jsview(
		"imdb.SearchRecipeView",
		{

		    /**
		     * Specifies the Controller belonging to this View. In the
		     * case that it is not implemented, or that "null" is
		     * returned, this View does not have a Controller.
		     * 
		     * @memberOf imdb.SearchRecipeView
		     */
		    getControllerName : function() {
			return "imdb.SearchRecipeView";
		    },

		    /**
		     * Is initially called once after the Controller has been
		     * instantiated. It is the place where the UI is
		     * constructed. Since the Controller is given to this
		     * method, its event handlers can be attached right away.
		     * 
		     * @memberOf imdb.SearchRecipeView
		     */
		    createContent : function(oController) {

			var aControls = [];
			var list = new sap.m.ListBase("list", {});
			aControls
				.push(new sap.m.SearchField(
					"search",
					{
					    showRefreshButton : true,
					    search : function() {
						sap.ui.getCore().byId("list")
							.destroyItems();
						var data = oController.search();
						for (var i = 0; i < data
							.getProperty("/").length; i++) {
						    sap.ui
							    .getCore()
							    .byId("list")
							    .addItem(
								    new sap.m.ObjectListItem(
									    {
										icon : "sap-icon://meal",
										number : data
											.getProperty("/"
												+ i
												+ "/servingValue"),
										numberUnit : data
											.getProperty("/"
												+ i
												+ "/servingUnit/unit"),
										title : data
											.getProperty("/"
												+ i
												+ "/name"),
										firstStatus : new sap.m.ObjectStatus(
											{
											    text : data
												    .getProperty("/"
													    + i
													    + "/thermalTreatment/type"),
											    state : "Success"
											}),
										attributes : [
											new sap.m.ObjectAttribute(
												{
												    title : "Calories",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/8/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/8/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Cholesterol",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/7/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/7/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Sodium",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/6/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/6/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Protein",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/5/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/5/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Sugar",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/4/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/4/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Fiber",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/3/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/3/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Trans-fat",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/2/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/2/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Fat-unsaturated",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/1/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/1/nutrient/unit/unit")
												}),
											new sap.m.ObjectAttribute(
												{
												    title : "Fat-saturatred",
												    text : data
													    .getProperty("/"
														    + i
														    + "/nutrients/0/value")
													    + " "
													    + data
														    .getProperty("/"
															    + i
															    + "/nutrients/0/nutrient/unit/unit")
												}) ]
									    }));

						}
					    }
					}));
			aControls.push(list);
			return aControls;
		    }

		});