package tasks.api.Store;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.json.JSONObject;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateOrderTask implements Task {

    private final int orderId;
    private final int petId;
    private final int quantity;
    private final String status;

    public CreateOrderTask(int orderId, int petId, int quantity, String status) {
        this.orderId = orderId;
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
    }

    public static CreateOrderTask withData(int orderId, int petId, int quantity, String status) {
        return instrumented(CreateOrderTask.class, orderId, petId, quantity, status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JSONObject body = new JSONObject();
        body.put("id", orderId);
        body.put("petId", petId);
        body.put("quantity", quantity);
        body.put("status", status);
        body.put("complete", true);

        actor.attemptsTo(
                Post.to("/store/order")
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(body.toString())
                        )
        );

        SerenityRest.lastResponse().prettyPrint();
    }
}
