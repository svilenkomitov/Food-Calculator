# Food-Calculator

#What we use
  * SAP HANA Cloud Platform identity service is used for user authentication [sample](https://help.hana.ondemand.com/help/frameset.htm?e637f62abb571014857cb0232adc43a7.html#loioe637f62abb571014857cb0232adc43a7)
  * The amount of the recipe nutrients(calories, proteins, ...) is stored using SAP HANA Cloud Platform persistence service [sample](https://help.hana.ondemand.com/help/frameset.htm?e4aeacd2bb5710148ee99255136d96a5.html)
  * Consuming [National Nutrition Database Service](http://ndb.nal.usda.gov/ndb/doc/index) using SAP HANA Cloud Platform connectivity service [sample](https://help.hana.ondemand.com/help/frameset.htm?e592cf6cbb57101495d3c28507d20f1b.html)
  * User Interface powered by [SAPUI5](https://sapui5.netweaver.ondemand.com/#docs/guide/99ac68a5b1c3416ab5c84c99fefa250d.html). The application is compatible with mobile devices.
  * The whole communication process is handled via [CXF](https://cxf.apache.org/) REST services.

# Prerequisites
  * JDK 7 or higher
  * [SAP HANA Cloud Platform Tools and SDK(Java Web Tomcat 7)](https://tools.hana.ondemand.com/#cloud)
  * [Set up SDK location](https://help.hana.ondemand.com/help/frameset.htm?7613f514711e1014839a8273b0e91070.html)
  * [Maven](https://maven.apache.org/)
  
# Application startup
1. Running locally
  * import as Maven project
  * create new local server
  * double-click on it, open Users tab and create new user with the required properties. [click :)](https://cloud.githubusercontent.com/assets/7129907/10973037/6dfc4132-83e4-11e5-8a77-3c8509c6be07.jpg)
  * double-click on the created server, choose the Connectivity tab and create new Destination named "report-destination" and [properties](https://cloud.githubusercontent.com/assets/7129907/10973032/6ddfa900-83e4-11e5-9017-b508a0e1a7b5.jpg) ([API key](http://ndb.nal.usda.gov/ndb/doc/index#) -> click Sign up now)
  * double-click on the created server, choose the Connectivity tab and create new Destination named "search-destination" and [properties](https://cloud.githubusercontent.com/assets/7129907/10973035/6de53082-83e4-11e5-9c84-737d9ebf8736.jpg) ([API key](http://ndb.nal.usda.gov/ndb/doc/index#) -> click Sign up now)
  * if you work behind a proxy server, then you should configure your proxy settings (host and port). Double click on the server, go to Overview tab and press the Open launch configuration. In the tab (x)= Arguments, VM Arguments copy this: -Dhttp.proxyHost= -Dhttp.proxyPort= -Dhttps.proxyHost= -Dhttps.proxyPort= and set your proxy hosts and ports
  * Run the application

NOTE: If the option Run on Server is not in the menu, then right-click on the project -> Properties ->  Project Facets and make sure the Dynamic Web Module is checked and its version is 3.0.
 
# Authors
Tsvetislav Viktorov - https://github.com/cviktorov

Svilen Komitov - https://github.com/svilenkomitov

# Copyright and license
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
