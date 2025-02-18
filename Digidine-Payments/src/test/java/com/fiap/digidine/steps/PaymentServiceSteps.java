package com.fiap.digidine.steps;

import com.fiap.digidine.dto.PaymentDTO;
import com.fiap.digidine.model.Payment;
import com.fiap.digidine.model.PaymentRequest;
import com.fiap.digidine.repository.PaymentsRepository;
import com.fiap.digidine.service.PaymentsServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Transactional
public class PaymentServiceSteps {

    @Autowired
    private PaymentsServiceImpl paymentsService;

    @Autowired
    private PaymentsRepository paymentsRepository;

    private PaymentRequest paymentRequest;
    private PaymentDTO paymentDTO;

    Random random = new Random();
    long randomLong = random.nextLong();

    @Before
    public void setUp() {
        paymentsRepository.deleteAll();
    }

    @Given("a valid paymentRequest")
    public void a_valid_payment_request() {
        paymentRequest = new PaymentRequest(randomLong, 100.00);
    }

    @When("the processPayment method is called")
    public void the_process_payment_method_is_called() {
        paymentDTO = paymentsService.processPayment(paymentRequest);
    }

    @Then("an paymentDTO should be returned")
    public void an_payment_dto_should_be_returned() {
        Assertions.assertNotNull(paymentDTO);
    }

    @Then("the order should be saved in the repository")
    public void the_order_should_be_saved_in_the_repository() {
        Payment savedPayment = paymentsRepository.findByOrderNumber(randomLong);
        Assertions.assertNotNull(savedPayment);
    }
}


