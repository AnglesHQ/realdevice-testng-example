 ## Real Device Execution
 Unlike desktops real devices can only handle a single session at a time so you have to orchestrate your test execution more effectively.
 This project shows an example of how to orchestrate real device testing using custom tagging and a TestNG interceptor. 
  
  You can run the tests using various commands and below are a few examples. The code can very much be modified locally to suit your own test execution and tagging needs.
   
 #### Select devices by device name(s)
 You can pass a single device or multiple seperated by a comma.
 ```bash
 mvn clean test 
 -Dsaucelabs_endpoint=http://eu1.appium.testobject.com/wd/hub
 -Dtestobject_api_key=${add_app_specific_api_key}
 -Dtestobject_app_id=${add_app_specific_id}
 -Dsaucelabs_device_name=${device_name} 
 -DtestPhases=REGRESSION 
 -Dtags=github
 ```
 
 #### Select devices by platform & version
 You can also run a test e.g. against every iOS device (of a specific version)
```bash
mvn clean test 
 -Dsaucelabs_endpoint=http://eu1.appium.testobject.com/wd/hub
 -Dtestobject_api_key=${add_app_specific_api_key}
 -Dtestobject_app_id=${add_app_specific_id}
 -Dsaucelabs_platform_name=iOS 
 -Dsaucelabs_platform_version=11.2.5
 -DtestPhases=REGRESSION 
 -Dtags=github
 ```
 
#### Filtering test by tags
The remaining two arguments shown above ("tags", "testPhases") are specific for filtering and will select the test cases to be executed based on the tags added in the @TestTags annotation.
  
#### Running the exammples 
```bash
mvn clean test -Pexample_01_basic

mvn clean test -Pexample_01b_timeout

mvn clean test -Pexample_02_methods

mvn clean test -Pexample_02b_dataprovider

mvn clean test -Pexample_03_tests

mvn clean test -Pexample_04_classes

mvn clean test -Pexample_05_instances

mvn clean test -Pexample_06a_non_threadsafe

mvn clean test -Pexample_06b_threadsafe

mvn clean test -Pexample_07_interceptor

mvn clean test -Pmain_example_multibrowser -Dsaucelabs_username=${sl_username} -Dsaucelabs_accessKey=${sl_accesskey}

mvn clean test -Pmain_example_multidevice -Dtestobject_api_key=${to_api_key} -Dtestobject_app_id=${to_app_id}

mvn clean test -Pmain_example_combined -Dtestobject_api_key=${to_api_key} -Dtestobject_app_id=${to_app_id} -Dsaucelabs_username=${sl_username} -Dsaucelabs_accessKey=${sl_accesskey} -Dsupported_devices=true

```

  ## License
 This project is released under MIT license. Copyright (c) 2018 Gamesys Limited. All rights reserved.
