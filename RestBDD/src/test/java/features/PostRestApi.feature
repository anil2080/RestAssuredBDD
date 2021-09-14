Feature: POST Method - REST ASSURED

@post @all
Scenario Outline: Post API to create a user in database

Given A post Request with some parameters
When I call Rest assured post method with request payload to create user "<name>" and job as 1
Then I should be able to create a user successfully "<name>"

Examples:
	|name|1|
	|ABCD|132|
	|9800|12345|