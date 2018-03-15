package http;

import java.util.function.Consumer;

public class Requesto extends ObserverRequesto{
    private final Consumer<String> onNext;
    private final Consumer<Exception> onError;
    private String response;

    public Requesto(Consumer<String> onNext, Consumer<Exception> onError) {
        super();
        this.onNext = onNext;
        this.onError = onError;
    }

    @Override
    public void update(String s) {
        super.update(s);
        this.response = s;

        try {
            onNext.accept(response);
        } catch (Exception exception) {
            onError.accept(exception);
        }
    }

    public String getResponse() {
        return response;
    }
}
