package tasks.api.Store;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteOrderTask implements Task {

    private final int orderId;

    public DeleteOrderTask(int orderId) {
        this.orderId = orderId;
    }

    public static DeleteOrderTask byId(int orderId) {
        return instrumented(DeleteOrderTask.class, orderId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/store/order/{orderId}")
                        .with(request -> request.pathParam("orderId", orderId))
        );
    }
}