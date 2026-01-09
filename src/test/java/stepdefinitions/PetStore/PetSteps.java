package stepdefinitions.PetStore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import questions.api.ResponseCodeQuestion;
import tasks.api.PetStore.CreatePetTask;
import tasks.api.PetStore.DeletePetTask;
import tasks.api.PetStore.GetPetTask;
import tasks.api.PetStore.UpdatePetTask;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetSteps {

    int petId = 12345;
    String petName = "Rex";
    String petStatus = "available";

    @Given("The user creates a new pet")
    public void createPet() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(CreatePetTask.withData(petId, petName, petStatus));

        assertThat(OnStage.theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @When("The user retrieves the pet by id")
    public void getPet() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetPetTask.byId(petId));

        assertThat(OnStage.theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @And("The pet information should be correct")
    public void verifyPetInfo() {
        var response = SerenityRest.lastResponse();
        assertThat(response.path("id"), equalTo(petId));
        assertThat(response.path("name"), equalTo(petName));
        assertThat(response.path("status"), equalTo(petStatus));
    }

    @And("The user updates the pet information")
    public void updatePet() {
        petName = "RexUpdated";
        petStatus = "sold";
        OnStage.theActorInTheSpotlight()
                .attemptsTo(UpdatePetTask.withData(petId, petName, petStatus));
        assertThat(OnStage.theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @And("The updated pet data should be correct")
    public void verifyUpdatedPetInfo() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetPetTask.byId(petId));
        var response = SerenityRest.lastResponse();
        assertThat(response.path("id"), equalTo(petId));
        assertThat(response.path("name"), equalTo(petName));
        assertThat(response.path("status"), equalTo(petStatus));
    }

    @And("The user deletes the pet")
    public void deletePet() {
        OnStage.theActorInTheSpotlight().attemptsTo(DeletePetTask.byId(petId));
        assertThat(OnStage.theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(200));
    }

    @Then("The pet should no longer exist")
    public void verifyPetDeleted() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetPetTask.byId(petId));
        assertThat(OnStage.theActorInTheSpotlight().asksFor(ResponseCodeQuestion.was()), equalTo(404));
    }
}