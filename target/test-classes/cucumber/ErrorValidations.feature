
@tag
Feature: Error Validation
  


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on ECommerce page
    When logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    

    Examples: 
      | name  						| password 	|
      | ajitkum@@abc.com 	| @Ajit123 	| 