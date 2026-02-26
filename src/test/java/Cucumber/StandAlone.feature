

@tag
Feature: Purchase the order from ecommerce website

Background:
Given I landed on Ecommerce Page

	@Regression
  	Scenario Outline: Positive Test of submitting the order

  	
    Given Logged with username <name> and <password>
    When  I add product <productName> to Cart
    And Checkout product <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
    Examples:
		|name					| password		|	productName			|
		|nifasath004@gmail.com 	| Nifasath@123	|	ZARA COAT 3			|
		|nifariya@gmail.com		| NifaRiya@123	|	iphone 13 pro		|
		|nifasath004@gmail.com  	| Nifasath@123	|	ADIDAS ORIGINAL		|
		|nifariya@gmail.com		| NifaRiya@123	|	ZARA COAT 3			|