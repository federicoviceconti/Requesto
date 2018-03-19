package http;

import param.Param;
import request.Request;
import response.Response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class HttpRequest extends BaseRequest implements Http {
    private HttpRequest(Request request) {
        super(request);
        this.request = request;
    }

    public HttpRequest() {
        super(null);
    }

    @Override
    public BaseRequest subscribe(Request request, Consumer<Response> onNext, Consumer<Exception> onError) {
        return super.subscribe(request, onNext, onError);
    }

    @Override
    public <T extends Param> void doRequestWithBody(T bodyParam) {
        new Thread(() -> notifyHttp(makeRequest(bodyParam, connection -> {
            try {
                connection.setRequestProperty("Content-Type", request.getRequestContentType().name());
                connection.setRequestProperty("Content-Length", bodyParam.getLength());
                connection.setDoOutput(true);
                connection.getOutputStream().write(bodyParam.toString().getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }))).start();
    }

    @Override
    public <T extends Param> void doRequest(T queryParam) {
        new Thread(() -> notifyHttp(makeRequest(queryParam, connection -> connection))).start();
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                ", request=" + request +
                '}';
    }
}
