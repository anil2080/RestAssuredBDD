Feature: POST Method - REST ASSURED

@post @all
Scenario: Post API to create a user in database

Given A post Request with some parameters
When I call Rest assured post method with request payload to create user
Then I should be able to create a user successfully