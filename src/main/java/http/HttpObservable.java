package http;

import request.Request;

import java.io.InputStream;

public interface HttpObservable<T> {
    <T> T subscribe(Request request);
    void unsubscribe();
    String notifyHttp(InputStream response);
}
