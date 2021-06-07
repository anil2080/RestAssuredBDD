Feature: GET Method - REST ASSURED

Scenario: Get API to fetch the data of page

Given A Get Request with some parameters
When I call Rest assured Get method with Get URL
Then I should be able to see the related data

Scenario: Get API to fetch the data of user

Given A Get Request with user parameters
When I call Rest assured Get method with Get URL & path parameter
Then I should be able to see the user related data