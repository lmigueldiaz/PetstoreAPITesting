package tasks.api.User;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUserTask implements Task {

    private final String username;

    public DeleteUserTask(String username) {
        this.username = username;
    }

    public static DeleteUserTask byUsername(String username) {
        return instrumented(DeleteUserTask.class, username);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/user/{username}")
                        .with(request -> request.pathParam("username", username))
        );
    }
}