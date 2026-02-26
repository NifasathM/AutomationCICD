@tag
Feature: Error Validation 


	@ErrorValidation
  	Scenario Outline: Incorrect Email or Password
  	Given I landed on Ecommerce Page
    When  Logged with username <name> and <password>
    Then "Incorrect email or password." message is displayed
    
    Examples:
		|name					| password		|
		|nifasath004@gmail.com 	| Nifasath@12	|	
				