package http;

import param.Param;
import request.Request;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

    private <T extends Param> InputStream makeRequest(T param, UnaryOperator<URLConnection> action) {
        HttpURLConnection connection = openConnection.apply(param);
        action.apply(connection);
        return sendStream.apply(connection);
    }

    @Override
    public BaseRequest subscribe(Request request, Consumer<String> onNext, Consumer<Exception> onError) {
        return super.subscribe(request, onNext, onError);
    }

    @Override
    public <T extends Param> void doPost(T bodyParam) {
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
    public <T extends Param> void doGet(T queryParam) {
        new Thread(() -> notifyHttp(makeRequest(queryParam, connection -> connection))).start();
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                ", request=" + request +
                '}';
    }
}
