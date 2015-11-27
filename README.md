# Food-Calculator
Food calculator is web application that generates a nutrition report providing information about calories, proteins, carbohydrates and more nutritions for a particular recipe. This data is based on the user input for the ingredients used. The report is stored in the database and can later on be viewed by other people.

For more information about the idea behind the project you can check our blog: [link of the blog](https://www.google.bg/)
#What we use
  * SAP HANA Cloud Platform [identity service](https://help.hana.ondemand.com/cloud_identity/frameset.htm) is used for user authentication.
  * The amount of the recipe nutrients(calories, proteins, ...) is stored using SAP HANA Cloud Platform [persistence service](https://help.hana.ondemand.com/help/frameset.htm?e7b3c275bb571014a910b3fb4329cf09.html).
  * Consuming [National Nutrition Database Service](http://ndb.nal.usda.gov/ndb/doc/index) using SAP HANA Cloud Platform [connectivity service](https://help.hana.ondemand.com/help/frameset.htm?e54cc8fbbb571014beb5caaf6aa31280.html).
  * User Interface powered by [SAPUI5](https://sapui5.netweaver.ondemand.com/#docs/guide/99ac68a5b1c3416ab5c84c99fefa250d.html). The application is compatible with mobile devices.
  * The whole communication process is handled via [CXF](https://cxf.apache.org/) REST services.

# Prerequisites
  * [Eclipse IDE for JavaEE developers](https://eclipse.org/downloads/)
  * [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) or higher
  * [SAP HANA Cloud Platform Tools and SDK(Java Web Tomcat 7)](https://tools.hana.ondemand.com/#cloud)
  * [Set up SDK location](https://help.hana.ondemand.com/help/frameset.htm?7613f514711e1014839a8273b0e91070.html)
  * [Maven](https://maven.apache.org/)
  
# Application startup
Clone the repo, ``git clone https://github.com/svilenkomitov/Food-Calculator.git``

1. Running locally
  * Open Eclipse IDE
  * import as Maven project: File -> Import -> Existing Maven project
  * create new Server: File -> New -> Other -> Server -> SAP -> Java Web Tomcat 7 Server
  * double-click on the created server, open Users tab and create new user with the required properties. ![click :)](https://cloud.githubusercontent.com/assets/7129907/10973037/6dfc4132-83e4-11e5-8a77-3c8509c6be07.jpg)
  * double-click on the created server, choose the Connectivity tab and create new Destination named "report-destination" and ![properties](https://cloud.githubusercontent.com/assets/7129907/10996152/0d028088-848b-11e5-889c-ec93bca62c15.png) (get [API key](http://ndb.nal.usda.gov/ndb/doc/index#) -> click Sign up now –> fill the form)
  * double-click on the created server, choose the Connectivity tab and create new Destination named "search-destination" and ![properties](https://cloud.githubusercontent.com/assets/7129907/10996120/d518b2aa-848a-11e5-8a3d-d8deff31a0c2.png) (get [API key](http://ndb.nal.usda.gov/ndb/doc/index#) -> click Sign up now –> fill the form)
  * if you work behind a proxy server, then you should configure your proxy settings (host and port). Double click on the server, go to Overview tab and press the Open launch configuration. In the tab (x)= Arguments, VM Arguments copy this: -Dhttp.proxyHost= -Dhttp.proxyPort= -Dhttps.proxyHost= -Dhttps.proxyPort= and set your proxy hosts and ports
  * Run the application

NOTE: If the option Run on Server is not in the menu, then right-click on the project -> Properties ->  Project Facets and make sure the Dynamic Web Module is checked and its version is 3.0.
 
#Demo
 * Enter your credentials from server configuration. ![](https://cloud.githubusercontent.com/assets/7129907/10973033/6de23b52-83e4-11e5-9e56-13bc15ab8706.jpg)
 * You can choose between three options:
      1. Enter information about your recipe and calculate its nutrition amount
      2. See all recipes stored in our database
      3. Search recipe by name
 ![options](https://cloud.githubusercontent.com/assets/7129907/10996387/2e7c70a0-848d-11e5-8969-ecfa0106a9f2.png)
 * In the fields provided you can enter data for the recipe: name, amount for one serving and all products contained in the recipe. Than by clicking the "Calculate" button you get a report about the nutrients quantity. You can save the final result so that everyone who uses the application can see it. ![salad1](https://cloud.githubusercontent.com/assets/3735031/11435676/beb6ec8c-94e5-11e5-901e-b89328d7d443.jpg)
 * Here you can see the whole nutrient information for the recipes stored in the database and delete it if you think it is not correct. ![salad2](https://cloud.githubusercontent.com/assets/3735031/11435678/beb82fde-94e5-11e5-93ee-0f6d7cf06bd8.jpg)
 * The search option is to find recipes by name and see its nutrient information ![salad3](https://cloud.githubusercontent.com/assets/3735031/11435677/beb7370a-94e5-11e5-8657-7ebc20eeb548.jpg)
 
 
# Authors
Tsvetislav Viktorov - https://github.com/cviktorov

Svilen Komitov - https://github.com/svilenkomitov

# Copyright and license
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
