package http;

import request.Request;
import response.Response;

import java.io.InputStream;
import java.net.URLConnection;
import java.util.function.Consumer;
import java.util.function.Function;

public interface HttpObservable<T> {
    T subscribe(Request request, Consumer<Response> onNext, Consumer<Exception> onError);

    void unsubscribe();

    String notifyHttp(URLConnection response);
}
