package http;

import param.Param;
import request.Request;
import request.RequestMethod;
import response.HeaderResponseField;
import response.HttpResponse;
import response.Response;
import utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Base request class could be inherited to different kind of request.
 */
public abstract class BaseRequest implements Http, HttpObservable<BaseRequest> {
    protected Request request;
    private ObserverRequesto requesto;
    private final Object object = new Object();

    BaseRequest(Request request) {
        this.request = request;
    }

    /**
     * Contains steps to establish connection and return it when it's finished.
     * User-agent it's mandatory!
     */
    protected Function<Param<Object, Object>, HttpURLConnection> openConnection = myParam -> {
        try {
            //If is a GET request, params must be concatenated with URL
            URL url = new URL(request.getBaseUrl() +
                    (request.getRequestMethod() == RequestMethod.GET ? myParam : ""));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(request.getRequestMethod().name());

            assert request.getRequestUserAgent().getUserAgent() != null : "User-Agent must be null!";
            con.addRequestProperty("User-Agent", request.getRequestUserAgent().getUserAgent());

            return con;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };

    /**
     * Send connection stream.
     */
    protected Function<HttpURLConnection, URLConnection> sendStream = con -> con;

    /**
     * Use the previous variable to make request
     * @param param the param of the request
     * @param action is used if we want to customize request
     * @param <T> first argument should be a param or a subclass
     * @return
     */
    final <T extends Param> URLConnection makeRequest(T param, UnaryOperator<URLConnection> action) {
        HttpURLConnection connection = openConnection.apply(param);
        action.apply(connection);
        return sendStream.apply(connection);
    }

    @Override
    public BaseRequest subscribe(Request request, Consumer<Response> onNext, Consumer<Exception> onError) {
        if (this.request == null) {

            synchronized (object) {
                if(this.request == null) {
                    this.request = request;
                    this.requesto = new Requesto(onNext, onError);
                }
            }
        }

        return this;
    }

    @Override
    public void unsubscribe() {
        this.request = null;
        this.requesto = null;
    }

    @Override
    public String notifyHttp(URLConnection connection) {
        Map<HeaderResponseField, List<String>> headerResponseFieldStringMap = new HashMap<>();

        try {
            //Set header fields
            for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
                headerResponseFieldStringMap.put(Utils.getHeaderByString(entry.getKey()), entry.getValue());
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;

            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //Set response with header and content and send to observer update
            Response response = new HttpResponse(headerResponseFieldStringMap, content.toString());
            requesto.update(response);
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
