package tasks.api.Store;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetOrderTask implements Task {

    private final int orderId;

    public GetOrderTask(int orderId) {
        this.orderId = orderId;
    }

    public static GetOrderTask byId(int orderId) {
        return instrumented(GetOrderTask.class, orderId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/store/order/{orderId}")
                        .with(request -> request.pathParam("orderId", orderId))
        );
    }
}
