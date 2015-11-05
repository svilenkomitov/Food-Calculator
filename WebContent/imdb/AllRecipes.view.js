sap.ui.jsview("imdb.AllRecipes", {

    /**
     * Specifies the Controller belonging to this View. In the case that it is
     * not implemented, or that "null" is returned, this View does not have a
     * Controller.
     * 
     * @memberOf imdb.AllRecipes
     */
    getControllerName : function() {
	return "imdb.AllRecipes";
    },

    /**
     * Is initially called once after the Controller has been instantiated. It
     * is the place where the UI is constructed. Since the Controller is given
     * to this method, its event handlers can be attached right away.
     * 
     * @memberOf imdb.AllRecipes
     */
    createContent : function(oController) {

	return oController.render();
    }
});