package tasks.api.User;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.json.JSONObject;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUserTask implements Task {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;

    public UpdateUserTask(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static UpdateUserTask withData(String username, String firstName, String lastName, String email) {
        return instrumented(UpdateUserTask.class, username, firstName, lastName, email);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", "12345");
        body.put("phone", "555-1234");
        body.put("userStatus", 1);

        actor.attemptsTo(
                Put.to("/user/{username}")
                        .with(request -> request
                                .pathParam("username", username)
                                .header("Content-Type", "application/json")
                                .body(body.toString())
                        )
        );

        SerenityRest.lastResponse().prettyPrint();
    }
}