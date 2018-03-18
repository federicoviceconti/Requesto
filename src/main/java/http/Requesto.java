package http;

import response.Response;

import java.util.function.Consumer;

public class Requesto extends ObserverRequesto{
    private final Consumer<Response> onNext;
    private final Consumer<Exception> onError;

    Requesto(Consumer<Response> onNext, Consumer<Exception> onError) {
        super();
        this.onNext = onNext;
        this.onError = onError;
    }

    @Override
    public void update(Response response) {
        super.update(response);

        try {
            onNext.accept(response);
        } catch (Exception exception) {
            onError.accept(exception);
        }
    }
}
