package com.fiap.digidine.bdd.cucumber;

import com.fiap.digidine.DigidineApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = DigidineApplication.class)
public class CucumberSpringConfiguration {
}