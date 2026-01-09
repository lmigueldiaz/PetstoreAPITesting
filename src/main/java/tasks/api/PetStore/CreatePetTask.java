package tasks.api.PetStore;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.json.JSONObject;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreatePetTask implements Task {

    private final int petId;
    private final String petName;
    private final String petStatus;

    public CreatePetTask(int petId, String petName, String petStatus) {
        this.petId = petId;
        this.petName = petName;
        this.petStatus = petStatus;
    }

    public static CreatePetTask withData(int petId, String petName, String petStatus) {
        return instrumented(CreatePetTask.class, petId, petName, petStatus);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JSONObject petData = new JSONObject();
        petData.put("id", petId);
        petData.put("name", petName);
        petData.put("photoUrls", new String[]{"string"});
        petData.put("status", petStatus);

        actor.attemptsTo(
                Post.to("/pet")
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(petData.toString())
                        )
        );
    }
}