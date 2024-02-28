package com.system.beverage_store;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/user.feature", // Location of your feature file
        glue = "com.system.beverage_store", // Package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"} // Optional: plugins for reporting
)
public class UserRunnerTest {
}
