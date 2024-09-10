
@tag
	Feature: Purchase the order from Ecommerce Website
  
	Background:
	Given I landed on ECommerce page


  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given logged in with username <name> and password <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | name  					| password 	| productName |
      | ajitkum@abc.com | @Ajit123 	| ZARA COAT 3 |
    
