package tasks.api.User;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserTask implements Task {

    private final String username;

    public GetUserTask(String username) {
        this.username = username;
    }

    public static GetUserTask byUsername(String username) {
        return instrumented(GetUserTask.class, username);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/user/{username}")
                        .with(request -> request.pathParam("username", username))
        );
    }
}