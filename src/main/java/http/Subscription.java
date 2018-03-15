package http;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Subscription<T, R> {
    R onNext(Function<T, R> action);
    void onError(Consumer<T> action);
}
