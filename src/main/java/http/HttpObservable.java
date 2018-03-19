package http;

import request.Request;
import response.Response;
import java.net.URLConnection;
import java.util.function.Consumer;

/**
 * This interface is used to subscribe to request.
 * If it complete without errors, it'll calls onNext's consumer.
 * Otherwhise goes in onError.
 * @param <T> the caller class
 */
public interface HttpObservable<T> {
    /**
     * Subscription method used to subscribe to request.
     * @param request the request type, built before
     * @param onNext has a response parameter, that could be when request is finished
     * @param onError with exception thrown
     * @return the caller class
     */
    T subscribe(Request request, Consumer<Response> onNext, Consumer<Exception> onError);

    /**
     * To unsubscribe to request
     */
    void unsubscribe();

    /**
     * This method is used to notify observer when request is finished
     * @param response contains the response
     * @return return content of the request, if it is necessary
     */
    String notifyHttp(URLConnection response);
}
