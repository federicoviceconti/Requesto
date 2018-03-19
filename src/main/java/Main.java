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
import response.HeaderResponseField;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //Construct params in URL
        Param<String, String> queryParam = new QueryParam(new HashMap<>());
        queryParam.addParam("q", "Hello+World!");

        //Construct params in BODY
        Param<String, String> bodyParam = new BodyParam(new HashMap<>());
        bodyParam.addParam("title", "foo");
        bodyParam.addParam("body", "bar");
        bodyParam.addParam("userId", "1");

        //Create GET request
        Request requestGet = new Request.Builder()
                .setBaseUrl("https://jsonplaceholder.typicode.com")
                .addSubPathToBaseUrl("posts")
                .addSubPathToBaseUrl("1")
                .setRequestMethod(RequestMethod.DELETE)
                .setUserAgent(RequestUserAgent.MOZILLA_WIN_UA)
                .create();

        //Create POST request
        Request requestPost = new Request.Builder()
                .setBaseUrl("https://jsonplaceholder.typicode.com")
                .addSubPathToBaseUrl("posts")
                .setRequestMethod(RequestMethod.POST)
                .setUserAgent(RequestUserAgent.MOZILLA_WIN_UA)
                .setContentType(RequestContent.JSON)
                .create();

        //Subscribe to previous GET request
        Http baseRequest = new HttpRequest()
                .subscribe(
                        requestGet,
                        response -> System.out.println("Content ->" + response.getContent()),
                        e -> new RuntimeException("Hello, exception!")
                );

        //Subscribe to previous POST request
        Http baseRequestPost = new HttpRequest()
                .subscribe(
                        requestPost,
                        (response) -> System.out.println("Content-Length ->"
                                + response.getResponseHeader(HeaderResponseField.CONTENT_LEN)
                        ),
                        e -> new RuntimeException("Hello, exception!")
                );

        //Do requests
        baseRequest.doRequest(queryParam);
        baseRequestPost.doRequestWithBody(bodyParam);
    }
}
