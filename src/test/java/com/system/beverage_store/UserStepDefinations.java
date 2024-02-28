package com.system.beverage_store;

import com.system.beverage_store.repo.UserRepo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class UserStepDefinations {

    private final UserRepo userRepo;

    private long count;

    @Autowired
    public UserStepDefinations(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Given("the user repository is initialized")
    public void givenTheUserRepositoryIsInitialized() {
        // No action needed, userRepo is already autowired
    }

    @When("I count all users")
    public void whenICountAllUsers() {
        count = userRepo.countAllRows(); // Assuming countAllRows() is a method in UserRepo
    }

    @Then("the count should be greater than or equal to {int}")
    public void thenTheCountShouldBeGreaterThanOrEqualTo(int expectedCount) {
        assertThat(count).isGreaterThanOrEqualTo(expectedCount);
    }
}
