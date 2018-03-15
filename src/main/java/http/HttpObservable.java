package http;

import request.Request;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public interface HttpObservable<T> {
    T subscribe(Request request, Consumer<String> onNext, Consumer<Exception> onError);

    void unsubscribe();

    String notifyHttp(InputStream response);
}
