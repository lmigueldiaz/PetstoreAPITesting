package tasks.api.PetStore;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeletePetTask implements Task {

    private final int petId;

    public DeletePetTask(int petId) {
        this.petId = petId;
    }

    public static DeletePetTask byId(int petId) {
        return instrumented(DeletePetTask.class, petId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/pet/{id}")
                        .with(request -> request.pathParam("id", petId))
        );
    }
}