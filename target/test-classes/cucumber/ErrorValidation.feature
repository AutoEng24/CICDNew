
@ErrorValidation
Feature: verify Incorrect credentials error message

 @IncorrectCredentials
  Scenario Outline: Submit order
    Given User landed on Ecommerce app
    When Login to the app with <username> and <password>
    Then "Incorrect email or password." error message is displayed in Confirmation page

    Examples: 
      | username           | password     | 
      | pushpa.v@gmail.com |     A232829@ | 
      
