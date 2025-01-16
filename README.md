# Technical-Test-Section3

## The main Frameworks included in the project:
- Selenium
- TestNG
- Allure Report
- Json Reader for Data management

## Project Design:
- Page Object Model (POM) design pattern
- Data Driven framework
- Have a supporting Utilities package in src/main/java file path, named "utils" that includes many wrapper methods which services the project like ElementsAction class

## Steps to Execute Code
- Clone the code from the Repository 
- Open POM.xml file then reload that file to install dependecies
- Go to "SubscriptionPackagesTest.java" class then run that class.
- You can access allure report by executing the following command "allure serve" in terminal after running the code

## Code Explanation
- in the src/main/java/org you will find a package called "pages" this package used to include all the pages that will be used in testing so for example the "homePage" class contain methods and locators that exist in home screen in order to apply POM design pattern
- in the src/main/java/org/pages you will find homePage class this class contains dynamic locators that helps in validating all packages existence, price and currency with only 3 methods 
- in the src/main/java/org folder there is a package called "utils" this package contain helper classes like:
    - "ElementsAction" which is designed to handel find element after applying waits then make interactions with that element.
    - "PropertiesManager" this class contains methods to read from a property file which exist under src/main/resources to access something like BaseUrl
    - "JsonFileManager" this class is used to read data from json file to inject these data in the test classes 
- in the src/test/java/org you will find a package called "testData" this package contains one json file per each test case to achieve isolation
- in the src/test/java/org you will find a package called "tests" this package contain one test classes "SubscriptionPackagesTest.java" this class have mainly one test case which interacts with the methods in homePage
