package stepdefinitions.Store;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import questions.api.ResponseCodeQuestion;
import tasks.api.Store.CreateOrderTask;
import tasks.api.Store.DeleteOrderTask;
import tasks.api.Store.GetOrderTask;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StoreSteps {

    int orderId = 10;

    @Given("I create a new order")
    public void createOrder() {
        theActorInTheSpotlight().attemptsTo(CreateOrderTask.withData(orderId, 12345, 2, "placed"));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @When("I get the order by id")
    public void getOrder() {
        theActorInTheSpotlight().attemptsTo(GetOrderTask.byId(orderId));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @Then("The order information should be correct")
    public void verifyOrderInfo() {
        var response = SerenityRest.lastResponse();
        assertThat(response.path("id"), equalTo(orderId));
        assertThat(response.path("status"), equalTo("placed"));
    }

    @When("I delete the order")
    public void deleteOrder() {
        theActorInTheSpotlight().attemptsTo(DeleteOrderTask.byId(orderId));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @Then("The order should no longer exist")
    public void verifyOrderDeleted() {
        theActorInTheSpotlight().attemptsTo(GetOrderTask.byId(orderId));
        assertThat(theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(404));
    }
}