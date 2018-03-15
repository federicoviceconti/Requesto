package http;

import param.Param;
import request.Request;
import request.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseRequest implements Http, HttpObservable<BaseRequest> {
    protected Request request;
    private ObserverRequesto requesto;
    private final Object object = new Object();

    protected Function<Param<Object, Object, String>, HttpURLConnection> openConnection = myParam -> {
        try {
            URL url = new URL(request.getBaseUrl() +
                    (request.getRequestMethod() == RequestMethod.GET ? myParam : ""));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(request.getRequestMethod().name());
            con.addRequestProperty("User-Agent", request.getRequestUserAgent().getUserAgent());
            return con;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };

    BaseRequest(Request request) {
        this.request = request;
    }

    protected Function<HttpURLConnection, InputStream> sendStream = con -> {
        try {
            return con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };

    @Override
    public BaseRequest subscribe(Request request, Consumer<String> onNext, Consumer<Exception> onError) {
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
    public String notifyHttp(InputStream response) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(response));
            String inputLine;

            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            requesto.update(content.toString());
            return content.toString();
        } catch (Exception ignored) {
        }

        return "";
    }
}
