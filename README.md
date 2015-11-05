# Food-Calculator
# Application startup
1. Running locally
  * create new local server
  * double-click on it, open Users tab and create new user with the required properties. [click :)](https://cloud.githubusercontent.com/assets/7129907/10973037/6dfc4132-83e4-11e5-8a77-3c8509c6be07.jpg)
  * double-click on the created server, choose the Connectivity tab and create new Destination named "report-destination" and [properties](https://cloud.githubusercontent.com/assets/7129907/10973032/6ddfa900-83e4-11e5-9017-b508a0e1a7b5.jpg)
  * double-click on the created server, choose the Connectivity tab and create new Destination named "search-destination" and [properties](https://cloud.githubusercontent.com/assets/7129907/10973035/6de53082-83e4-11e5-9c84-737d9ebf8736.jpg)
  * if you work behind a proxy server, then you should configure your proxy settings (host and port). Double click on the server, go to Overview tab and press the Open launch configuration. In the tab (x)= Arguments, VM Arguments copy this: -Dhttp.proxyHost= -Dhttp.proxyPort= -Dhttps.proxyHost= -Dhttps.proxyPort= and set your proxy hosts and ports
  * Run the application
NOTE: If the option Run on Server is not in the menu, then right-click on the project -> Properties ->  Project Facets and make sure the Dynamic Web Module is checked and its version is 3.0.
 
# Authors
Tsvetislav Victorov - https://github.com/cviktorov

Svilen Komitov - https://github.com/svilenkomitov

# Copyright and license
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
