# Food-Calculator
Food Calculator is а web application that can be used to calculate the nutrition report of a recipe. The idea of the application is to inform consumers what are nutrients (calories, protein, carbohydrates, fat, and so on) for a recipe. These nutrients depend on the basic products that the recipe contains.

For more information about the idea behind the project you can check our blog: [link of the blog](https://www.google.bg/)

# What do existing solutions offer?
With the existing applications that enable tracking the food you eat, you either can’t create new recipes, or it’s very difficult to do that due to differences in measurement units. Or if such an application is user-friendly, it is not free. 
The measurement units depend on the product condition. And, it is hard to calculate the nutrients of a single meal portion.

# What are the advantages of the Food Calculator Application?
This is where Food Calculator Application comes to help. It is designed to calculate the amount of nutrients in a recipe. The information about these nutrients is based on the basic products extracted from National Nutrient Database API. This database provides the amount of nutrients for a basic product. The application uses this database because it is supported by the United States Department of Agriculture and this is a guarantee that the data is reliable and its content is updated each year. Another reason to choose this database is the detailed nutrient information on over 8,000 generic and proprietary-branded types of food. 
The recipe nutrition information is saved in the database and this data is provided in a structured way to consumers.

# Who can use the Food Calculator Application? 
These are the use cases that the Food Calculator Application is intended for:

*	In restaurants. The menus of the restaurants can include a nutrients report for every meal they offer without listing the whole recipes. In this way customers that use special diets can choose an appropriate meal based on these reports.
*	At home. Anyone who prepared meals at home can take advantage of this application.

# What is included in the application implementation?
These are the main components of the application:

*	[Identity service](https://help.hana.ondemand.com/cloud_identity/frameset.htm) for authentication.
*	[Persistence Service](https://help.hana.ondemand.com/help/frameset.htm?e7b3c275bb571014a910b3fb4329cf09.html) for the recipe nutrient report.
*	[Connectivity service](https://help.hana.ondemand.com/help/frameset.htm?e54cc8fbbb571014beb5caaf6aa31280.html) to set destinations for  [National Nutrition Database API](http://ndb.nal.usda.gov/ndb/doc/index)
*	[SAPUI5](https://sapui5.netweaver.ondemand.com/#docs/guide/99ac68a5b1c3416ab5c84c99fefa250d.html) for the user interface.     The application is compatible with mobile devices.
*	REST services for the whole communication.

# What tools do you need to run the application?
*	[JDK 7 or higher](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
*	[Eclipse IDE](https://eclipse.org/downloads/)
*	[SAP HANA Cloud Platform SDK](https://help.hana.ondemand.com/help/frameset.htm?7613843c711e1014839a8273b0e91070.html) ([Java WEB Tomcat 7](https://tools.hana.ondemand.com/))
*	[Maven 3.x](https://maven.apache.org/)

# How can you run the Food Calculator Application?
To run the application locally you need to: 

1. Download the Food Calculator Application from [here](https://github.com/svilenkomitov/Food-Calculator).
    The application is licensed under the Apache License, Version 2.0.
2. Import the project as an existing Maven project.
    Open the Eclipse IDE. Choose *File → Import →Existing Maven Projects*. Choose *Next*.
    In the *Root Directory* field, browse to select the root folder. In the *Projects* field select the checkbox with the Food Calculator Application project. Choose *Finish*.
3. Create new Java Web Tomcat 7 Server.

*   In the *Servers* view, double-click on the Java Web Tomcat 7 Server. Open the *Users* tab and create new user with the           required properties. 
    ![click :)](https://cloud.githubusercontent.com/assets/7129907/10973037/6dfc4132-83e4-11e5-8a77-3c8509c6be07.jpg)
*   Get an API key from this site: http://ndb.nal.usda.gov/ndb/doc/index. In the *Gaining Access* section, choose *Sign up          now*, fill in the form and copy the generated key.
*   Open the *Connectivity* tab and create a new destination called **report-destination**. 
    In the *URL* field, enter **http://api.nal.usda.gov**.
    Set these additional properties:


|Name|Value|
|:------------:|:------------:|
|api_key|generated_api_key|
|format|json|
|path|/ndb/nutrients/|
|ProxyType|Internet|
|scheme|http|


**NOTE**: For the *api_key* name, you can use the value **DEMO_KEY**, instead of your generated key. There is a difference though. If you use the **DEMO_KEY** value, you will have access restrictions.
![properties](https://cloud.githubusercontent.com/assets/7129907/10996152/0d028088-848b-11e5-889c-ec93bca62c15.png)
*   Open the *Connectivity* tab and create a new destination called **search-destination**. 
    In the *URL* field, enter **http://api.nal.usda.gov**.
    Set these additional properties:

|Name|Value|
|:-----------:|:------------:|
|api_key|generated_api_key|
|format|json|
|path|/ndb/search/|
|ProxyType|Internet|
|scheme|http| 

![properties](https://cloud.githubusercontent.com/assets/7129907/10996120/d518b2aa-848a-11e5-8a3d-d8deff31a0c2.png)
*   Run the application on Java Web Tomcat 7 Server
    Right-click on the *com.food.calculator* project and choose *Run As → Run on Server*. Select the *Manually define* a new           server option and then choose *SAP → Java Web Tomcat 7 Server*. Choose *Finish*.

NOTE: If the option *Run on Server* is missing, then right-click on the project and choose *Properties → Project Facets*         and make sure the *Dynamic Web Module* checkbox is selected and its version is **3.0**.

# Try out the Food Calculator Application.
1. Open the application URL. It looks like this: ```http://<host>:<port>/com.food.calculator```
2. Enter the username and password you specified in the server configuration.
![](https://cloud.githubusercontent.com/assets/7129907/10973033/6de23b52-83e4-11e5-9e56-13bc15ab8706.jpg)

3. You can choose between these options:

*	Enter some information about your recipe.
*	List all recipes from the database.
*   Search a recipe by name.
![options](https://cloud.githubusercontent.com/assets/7129907/10996387/2e7c70a0-848d-11e5-8969-ecfa0106a9f2.png)

4. Choose the *New Recipe* tile. Add the ingredients of the recipe. Later on you will get a nutrient report based on the ingredients you specified. Save all the recipe settings. All other users of the application will be able to read your recipe.
![salad1](https://cloud.githubusercontent.com/assets/3735031/11435676/beb6ec8c-94e5-11e5-901e-b89328d7d443.jpg)

5. Choose the *Recipes* tile to list all the recipes and their nutrients reports. 
![salad2](https://cloud.githubusercontent.com/assets/3735031/11435678/beb82fde-94e5-11e5-93ee-0f6d7cf06bd8.jpg)

6. Choose the *Search* tile to find a recipe by name. In this way you obtain the nutrient report of this recipe.
![salad3](https://cloud.githubusercontent.com/assets/3735031/11435677/beb7370a-94e5-11e5-8657-7ebc20eeb548.jpg)

# What is more?
With the current release of the application, you can use it not only locally but also on the SAP HANA Cloud Platform. You can take the source code of the application, build it and deploy it on the SAP HANA Cloud Platform. 

# Authors
Tsvetislav Viktorov - https://github.com/cviktorov

Svilen Komitov - https://github.com/svilenkomitov

# Copyright and license
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
