package tasks.api.PetStore;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetPetTask implements Task {

    private final int petId;

    public GetPetTask(int petId) {
        this.petId = petId;
    }

    public static GetPetTask byId(int petId) {
        return instrumented(GetPetTask.class, petId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/{id}")
                        .with(request -> request.pathParam("id", petId))
        );
    }
}