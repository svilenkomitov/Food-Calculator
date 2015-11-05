sap.ui
	.jsview(
		"imdb.NewRecipeView",
		{

		    /**
		     * Specifies the Controller belonging to this View. In the
		     * case that it is not implemented, or that "null" is
		     * returned, this View does not have a Controller.
		     * 
		     * @memberOf imdb.NewRecipeView
		     */
		    getControllerName : function() {
			return "imdb.NewRecipeView";
		    },

		    /**
		     * Is initially called once after the Controller has been
		     * instantiated. It is the place where the UI is
		     * constructed. Since the Controller is given to this
		     * method, its event handlers can be attached right away.
		     * 
		     * @memberOf imdb.NewRecipeView
		     */
		    createContent : function(oController) {

			var aControls = [];
			var newRecipeModel = sap.ui.getCore().getModel(
				"newRecipeModel");

			var oLayout1 = new sap.ui.layout.form.ResponsiveGridLayout(
				"L1");
			var oForm1 = new sap.ui.layout.form.Form(
				"F1",
				{
				    title : new sap.ui.core.Title({
					text : "New recipe"
				    }),
				    editable : true,
				    visible : true,
				    layout : oLayout1,
				    formContainers : [
					    new sap.ui.layout.form.FormContainer(
						    "F1C1",
						    {
							title : "Recipe Details",
							formElements : [
								new sap.ui.layout.form.FormElement(
									{
									    label : "Recipe name",
									    fields : [ new sap.m.Input(
										    "nameInput",
										    {
											value : "{newRecipeModel>/recipeName}"
										    }) ]
									}),
								new sap.ui.layout.form.FormElement(
									{
									    label : "Serving",
									    fields : [ new sap.m.HBox(
										    {
											items : [
												new sap.m.Input(
													"servingValueInput",
													{
													    value : "{newRecipeModel>/servingValueInput}",
													    liveChange : function(
														    oEvent) {
														var isValid = oController
															.isValid(this
																.getValue());
														this
															.setValueState(isValid === false ? sap.ui.core.ValueState.Error
																: sap.ui.core.ValueState.Success);
													    }
													}),
												new sap.m.Select(
													"servingUnitInput",
													{
													    items : [
														    new sap.ui.core.ListItem(
															    "ml",
															    {
																text : "ml",
																key : "ml"
															    }),
														    new sap.ui.core.ListItem(
															    "mg",
															    {
																text : "mg",
																key : "mg"
															    }),
														    new sap.ui.core.ListItem(
															    "tsp",
															    {
																text : "tsp",
																key : "tsp"
															    }),
														    new sap.ui.core.ListItem(
															    "tbsp",
															    {
																text : "tbsp",
																key : "tbsp"
															    }),
														    new sap.ui.core.ListItem(
															    "cup",
															    {
																text : "cup",
																key : "cup"
															    }),
														    new sap.ui.core.ListItem(
															    "liter",
															    {
																text : "liter",
																key : "liter"
															    }),
														    new sap.ui.core.ListItem(
															    "g",
															    {
																text : "g",
																key : "g"
															    }),
														    new sap.ui.core.ListItem(
															    "oz",
															    {
																text : "oz",
																key : "oz"
															    }),
														    new sap.ui.core.ListItem(
															    "etto",
															    {
																text : "etto",
																key : "etto"
															    }),
														    new sap.ui.core.ListItem(
															    "lb",
															    {
																text : "lb",
																key : "lb"
															    }),
														    new sap.ui.core.ListItem(
															    "kg",
															    {
																text : "kg",
																key : "kg"
															    }),
														    new sap.ui.core.ListItem(
															    "kcal",
															    {
																text : "kcal",
																key : "kcal"
															    }),
														    new sap.ui.core.ListItem(
															    "cal",
															    {
																text : "cal",
																key : "cal"
															    }), ],
													}) ]
										    }) ]
									}),
								new sap.ui.layout.form.FormElement(
									{
									    label : "Thermal treatment",
									    fields : [ new sap.m.Select(
										    "treatmentInput",
										    {
											items : [
												new sap.ui.core.ListItem(
													"BOILING",
													{
													    text : "BOILING",
													    key : "BOILING"
													}),
												new sap.ui.core.ListItem(
													"FRYING",
													{
													    text : "FRYING",
													    key : "FRYING"
													}),
												new sap.ui.core.ListItem(
													"ROASTING",
													{
													    text : "ROASTING",
													    key : "ROASTING"
													}),
												new sap.ui.core.ListItem(
													"GRILLING",
													{
													    text : "GRILLING",
													    key : "GRILLING"
													}),
												new sap.ui.core.ListItem(
													"NONE",
													{
													    text : "NONE",
													    key : "NONE"
													}), ],
										    }) ]
									}),
								new sap.ui.layout.form.FormElement(
									{
									    label : "Total",
									    fields : [ new sap.m.HBox(
										    {
											items : [
												new sap.m.Input(
													"totalValueInput",
													{
													    value : "{newRecipeModel>/totalValueInput}",
													    liveChange : function(
														    oEvent) {
														var isValid = oController
															.isValid(this
																.getValue());
														this
															.setValueState(isValid === false ? sap.ui.core.ValueState.Error
																: sap.ui.core.ValueState.Success);
													    }
													}),
												new sap.m.Select(
													"totalUnitInput",
													{
													    items : [
														    new sap.ui.core.ListItem(
															    "ml2",
															    {
																text : "ml",
																key : "ml"
															    }),
														    new sap.ui.core.ListItem(
															    "mg2",
															    {
																text : "mg",
																key : "mg"
															    }),
														    new sap.ui.core.ListItem(
															    "tsp2",
															    {
																text : "tsp",
																key : "tsp"
															    }),
														    new sap.ui.core.ListItem(
															    "tbsp2",
															    {
																text : "tbsp",
																key : "tbsp"
															    }),
														    new sap.ui.core.ListItem(
															    "cup2",
															    {
																text : "cup",
																key : "cup"
															    }),
														    new sap.ui.core.ListItem(
															    "liter2",
															    {
																text : "liter",
																key : "liter"
															    }),
														    new sap.ui.core.ListItem(
															    "g2",
															    {
																text : "g",
																key : "g"
															    }),
														    new sap.ui.core.ListItem(
															    "oz2",
															    {
																text : "oz",
																key : "oz"
															    }),
														    new sap.ui.core.ListItem(
															    "etto2",
															    {
																text : "etto",
																key : "etto"
															    }),
														    new sap.ui.core.ListItem(
															    "lb2",
															    {
																text : "lb",
																key : "lb"
															    }),
														    new sap.ui.core.ListItem(
															    "kg2",
															    {
																text : "kg",
																key : "kg"
															    }),
														    new sap.ui.core.ListItem(
															    "kcal2",
															    {
																text : "kcal",
																key : "kcal"
															    }),
														    new sap.ui.core.ListItem(
															    "cal2",
															    {
																text : "cal",
																key : "cal"
															    }), ],
													}) ]
										    }) ]
									}) ]
						    }),
					    new sap.ui.layout.form.FormContainer(
						    "F1C2",
						    {
							title : "Add Product",
							formElements : [
								new sap.ui.layout.form.FormElement(
									{
									    label : "Product Name",
									    fields : [ new sap.m.Input(
										    "productNameInput",
										    {
											value : "{newRecipeModel>/productNameInput}"
										    }) ]

									}),
								new sap.ui.layout.form.FormElement(
									{
									    label : "Value",
									    fields : [ new sap.m.HBox(
										    {
											items : [
												new sap.m.Input(
													"productValueInput",
													{
													    value : "{newRecipeModel>/productValueInput}",
													    liveChange : function(
														    oEvent) {
														var isValid = oController
															.isValid(this
																.getValue());
														this
															.setValueState(isValid === false ? sap.ui.core.ValueState.Error
																: sap.ui.core.ValueState.Success);
													    }
													}),
												new sap.m.Select(
													"productUnitInput",
													{
													    items : [
														    new sap.ui.core.ListItem(
															    "ml1",
															    {
																text : "ml",
																key : "ml"
															    }),
														    new sap.ui.core.ListItem(
															    "mg1",
															    {
																text : "mg",
																key : "mg"
															    }),
														    new sap.ui.core.ListItem(
															    "tsp1",
															    {
																text : "tsp",
																key : "tsp"
															    }),
														    new sap.ui.core.ListItem(
															    "tbsp1",
															    {
																text : "tbsp",
																key : "tbsp"
															    }),
														    new sap.ui.core.ListItem(
															    "cup1",
															    {
																text : "cup",
																key : "cup"
															    }),
														    new sap.ui.core.ListItem(
															    "liter1",
															    {
																text : "liter",
																key : "liter"
															    }),
														    new sap.ui.core.ListItem(
															    "g1",
															    {
																text : "g",
																key : "g"
															    }),
														    new sap.ui.core.ListItem(
															    "oz1",
															    {
																text : "oz",
																key : "oz"
															    }),
														    new sap.ui.core.ListItem(
															    "etto1",
															    {
																text : "etto",
																key : "etto"
															    }),
														    new sap.ui.core.ListItem(
															    "lb1",
															    {
																text : "lb",
																key : "lb"
															    }),
														    new sap.ui.core.ListItem(
															    "kg1",
															    {
																text : "kg",
																key : "kg"
															    }),
														    new sap.ui.core.ListItem(
															    "kcal1",
															    {
																text : "kcal",
																key : "kcal"
															    }),
														    new sap.ui.core.ListItem(
															    "cal1",
															    {
																text : "cal",
																key : "cal"
															    }), ],
													}) ]
										    }) ]

									}),
								new sap.ui.layout.form.FormElement(
									{
									    label : "",
									    fields : [ new sap.m.Button(
										    "add",
										    {
											text : "Add product",
											type : "Emphasized",
											width : "130px",
											icon : "sap-icon://add",
											press : oController.getProducts
										    }) ]
									}) ]
						    }), ]
				});

			var oLayout2 = new sap.ui.layout.form.ResponsiveGridLayout(
				"L2");
			var oForm2 = new sap.ui.layout.form.Form(
				"F2",
				{
				    title : new sap.ui.core.Title({
					text : "Nutrient Report"
				    }),
				    editable : true,
				    visible : true,
				    layout : oLayout2,
				    formContainers : [
					    new sap.ui.layout.form.FormContainer(
						    "F2C1",
						    {
							title : "Nutrient report",
							formElements : [
								new sap.ui.layout.form.FormElement(
									{
									    label : "",
									    fields : [ new sap.m.HBox(
										    {
											items : [
												new sap.m.VBox(
													{
													    alignItems : "Baseline",
													    width : "500px",
													    items : [
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Calories"
															    }),
														    new sap.m.Text(
															    "calories",
															    {
																text : "{newRecipeModel>/calories}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Fiber"
															    }),
														    new sap.m.Text(
															    "fiber",
															    {
																text : "{newRecipeModel>/fiber}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Protein"
															    }),
														    new sap.m.Text(
															    "protein",
															    {
																text : "{newRecipeModel>/protein}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Sugar"
															    }),
														    new sap.m.Text(
															    "sugar",
															    {
																text : "{newRecipeModel>/sugar}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Cholesterol"
															    }),
														    new sap.m.Text(
															    "cholesterol",
															    {
																text : "{newRecipeModel>/cholesterol}"
															    }) ]
													}),
												new sap.m.VBox(
													{
													    alignItems : "Baseline",
													    width : "500px",
													    items : [
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Sodium"
															    }),
														    new sap.m.Text(
															    "sodium",
															    {
																text : "{newRecipeModel>/sodium}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Fat saturated"
															    }),
														    new sap.m.Text(
															    "fat-saturated",
															    {
																text : "{newRecipeModel>/fat-saturated}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Fat unsaturated"
															    }),
														    new sap.m.Text(
															    "fat-unsaturated",
															    {
																text : "{newRecipeModel>/fat-unsaturated}"
															    }),
														    new sap.m.Label(
															    {
																design : "Bold",
																text : "Fat trans"
															    }),
														    new sap.m.Text(
															    "fat-trans",
															    {
																text : "{newRecipeModel>/fat-trans}"
															    }) ]
													}) ]
										    }) ]
									}),
								new sap.ui.layout.form.FormElement(
									{
									    label : "",
									    fields : [ new sap.m.HBox(
										    {
											items : [
												new sap.m.Button(
													"report",
													{
													    text : "Calculate",
													    type : "Emphasized",
													    width : "130px",
													    icon : "sap-icon://meal",
													    press : oController.report
													}),
												new sap.m.Label(
													{
													    width : "50px"
													}),
												new sap.m.Button(
													"save",
													{
													    text : "Save",
													    type : "Emphasized",
													    enabled : false,
													    width : "130px",
													    icon : "sap-icon://save",
													    press : oController.save
													}) ]
										    }) ]
									}) ]
						    }),
					    new sap.ui.layout.form.FormContainer(
						    "F4C2",
						    {
							title : "",
							formElements : [ new sap.ui.layout.form.FormElement(
								{
								    label : "",
								    fields : [ new sap.m.Panel(
									    {
										headerText : "Products",
										backgroundDesign : "Transparent",
										content : [ new sap.m.ScrollContainer(
											{
											    height : "300px",
											    vertical : true,
											    horizontal : false,
											    content : [ new sap.m.ListBase(
												    {
													items : [ new sap.m.CustomListItem(
														"customListItem",
														{

														}) ]
												    }) ]
											}) ]
									    }) ]

								}) ]
						    }) ]
				});

			var oType = new sap.ui.model.type.Float();

			oType = new sap.ui.model.type.Float({
			    minIntegerDigits : 1,
			    maxIntegerDigits : 99,
			    minFractionDigits : 0,
			    maxFractionDigits : 99,
			    groupingEnabled : true,
			    groupingSeparator : ",",
			    decimalSeparator : "."
			});
			aControls.push(oForm1);
			aControls.push(oForm2);
			return aControls;
		    }

		});