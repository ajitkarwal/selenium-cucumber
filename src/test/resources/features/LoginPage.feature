Feature: Login Page functionality

  Scenario: Navi gate to login page
    Given user is pn home page
    When user clicks login button
    Then user navigates to login page

    Scenario: Login With Valid Credentials
      Given user is pn home page
      And user clicks login button
      And user enters username