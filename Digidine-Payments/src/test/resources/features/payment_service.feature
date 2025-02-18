Feature: Process Payment

  Scenario: Successfully processing a payment
    Given a valid paymentRequest
    When the processPayment method is called
    Then an paymentDTO should be returned
    Then the order should be saved in the repository