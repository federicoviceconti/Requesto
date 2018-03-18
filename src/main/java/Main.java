import http.BaseRequest;
import http.Http;
import http.HttpRequest;
import param.BodyParam;
import param.Param;
import param.QueryParam;
import request.Request;
import request.RequestContent;
import request.RequestMethod;
import request.RequestUserAgent;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Param<String, String, String> queryParam = new QueryParam(new HashMap<>());
        queryParam.addParam("q", "Hello+World!");

        Param<String, String, String> bodyParam = new BodyParam(new HashMap<>());
        bodyParam.addParam("title", "foo");
        bodyParam.addParam("body", "bar");
        bodyParam.addParam("userId", "1");

        Request requestGet = new Request.Builder()
                .setBaseUrl("https://jsonplaceholder.typicode.com")
                .addSubPathToBaseUrl("posts")
                .addSubPathToBaseUrl("1")
                .setRequestMethod(RequestMethod.DELETE)
                .setUserAgent(RequestUserAgent.MOZILLA_WIN_UA)
                .create();

        Request requestPost = new Request.Builder()
                .setBaseUrl("https://jsonplaceholder.typicode.com")
                .addSubPathToBaseUrl("posts")
                .setRequestMethod(RequestMethod.POST)
                .setUserAgent(RequestUserAgent.MOZILLA_WIN_UA)
                .setContentType(RequestContent.JSON)
                .create();

        Http baseRequest = new HttpRequest()
                .subscribe(
                        requestGet,
                        response -> System.out.println("Hello World ->" + response.getContent()),
                        e -> new RuntimeException("Hello, exception!")
                );

        baseRequest.doRequest(queryParam);

        Http baseRequestPost = new HttpRequest()
                .subscribe(
                        requestPost,
                        (response) -> System.out.println("Content ->" + response.getContent()),
                        e -> new RuntimeException("Hello, exception!")
                );

        baseRequestPost.doRequestWithBody(bodyParam);
    }
}
