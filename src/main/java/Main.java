import http.BaseRequest;
import http.Http;
import http.HttpRequest;
import param.QueryParam;
import request.Request;
import request.RequestMethod;
import request.RequestUserAgent;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        QueryParam queryParam = new QueryParam(new HashMap<>());
        queryParam.addParam("q", "Hello+World!");

        Request request = new Request.Builder()
                .setBaseUrl("https://www.google.it/")
                .addSubPathToBaseUrl("search")
                .setRequestMethod(RequestMethod.GET)
                .setUserAgent(RequestUserAgent.MOZILLA_WIN_UA)
                .create();

        Http baseRequest = new HttpRequest()
                .subscribe(request);

        baseRequest.doGet(queryParam);
    }
}
