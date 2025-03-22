
@OnlineShopping
Feature: Purchase order from Ecommerce app

  Background:
  Given User landed on Ecommerce app

 @submitOrder
  Scenario Outline: Submit order
    Given Login to the app with <username> and <password>
    When Add product <product> to the cart
    And Checkout product <product> and submit the order
    Then "THANKYOU FOR THE ORDER." confirmation message is displayed in Confirmation page

    Examples: 
      | username           | password     | product     |
      | pushpa.v@gmail.com |     A232829@a| IPHONE 13 PRO |
      
