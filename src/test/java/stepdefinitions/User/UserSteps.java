package stepdefinitions.User;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import questions.api.ResponseCodeQuestion;
import tasks.api.User.DeleteUserTask;
import tasks.api.User.GetUserTask;
import tasks.api.User.UpdateUserTask;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserSteps {

    String username = "luisTest";
    String firstName = "Luis";
    String lastName = "Diaz";
    String email = "luis@example.com";

    @Given("I create a new user")
    public void createUser() {
        theActorInTheSpotlight().attemptsTo(tasks.api.User.CreateUserTask.withData(username, firstName, lastName, email));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @When("I get the user by username")
    public void getUser() {
        theActorInTheSpotlight().attemptsTo(GetUserTask.byUsername(username));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @And("The user information should be correct")
    public void verifyUserInfo() {
        var response = SerenityRest.lastResponse();
        assertThat(response.path("username"), equalTo(username));
        assertThat(response.path("firstName"), equalTo(firstName));
        assertThat(response.path("lastName"), equalTo(lastName));
        assertThat(response.path("email"), equalTo(email));
    }

    @And("I update the user")
    public void updateUser() {
        firstName = "Miguel";
        theActorInTheSpotlight().attemptsTo(UpdateUserTask.withData(username, firstName, lastName, email));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @And("The updated user information should be correct")
    public void verifyUpdatedUserInfo() {
        theActorInTheSpotlight().attemptsTo(GetUserTask.byUsername(username));
        var response = SerenityRest.lastResponse();
        assertThat(response.path("firstName"), equalTo(firstName));
    }

    @And("I delete the user")
    public void deleteUser() {
        theActorInTheSpotlight().attemptsTo(DeleteUserTask.byUsername(username));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @Then("The user should no longer exist")
    public void verifyUserDeleted() {
        theActorInTheSpotlight().attemptsTo(GetUserTask.byUsername(username));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(404));
    }
}