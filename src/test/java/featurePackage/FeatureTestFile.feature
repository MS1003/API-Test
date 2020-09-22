Feature: GetAPI Validation


  Scenario Outline:
    Given user calls GETAPI request
    When response status code should be <statusCode>
    Then user should get <networkCount> networks under response body
  Examples:
    |statusCode | networkCount|
    |    200    |     643     |


  Scenario Outline:
        Given user calls GETAPI request
        When response status code should be <statusCode>
        Then user should get the city "<city>" and "<country>"


    Examples:
      |statusCode |     city          | country |
      |    200    |     Frankfurt     | DE |


  Scenario Outline:
    Given user calls GETAPI request
    When response status code should be <statusCode>
    Then user should get <latitude> and <longitude> corresponding to city "<city>"

    Examples:
      |statusCode |   latitude      |     longitude         |     city          |
      |    200    |     50.1072     |       8.66375         |     Frankfurt     |