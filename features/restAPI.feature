Feature: Basic API authentication

  @Run
  Scenario: Basic API authentication
    Given API resource "https://postman-echo.com/basic-auth"
    When I form a basic authentication request with username "test" and password "testpassword"
    And Form payload
      | Payload    |
      | Json1.json |
    And Update the payload for "username" with "TestUser"
    And Update the payload for "address.street" with "1001 Common street"
    And Authenticate API with userid "postman" and password "password"
    And Post the request "https://jsonplaceholder.typicode.com/users"
      | Header       | Value            |
      | Content-Type | application/json |
    Then the response should have status code 201
    Then Response should have tag "id" and value "11"
