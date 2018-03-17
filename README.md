# Requesto
Simple HTTP Client.

To see how it works you can check the Main java class into src folder.

We can specify GET Url parameter in this manner:
```java
Param<String, String, String> queryParam = new QueryParam(new HashMap<>());
queryParam.addParam("q", "Hello+World!");
```

Also, body parameter for POST request
```java
Param<String, String, String> bodyParam = new BodyParam(new HashMap<>());
bodyParam.addParam("title", "foo");
bodyParam.addParam("body", "bar");
bodyParam.addParam("userId", "1");
```

The following example initialize requests:
```java
Request requestGet = new Request.Builder()
  .setBaseUrl("https://www.google.it/")
  .addSubPathToBaseUrl("search")
  .setRequestMethod(RequestMethod.GET)
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
  response -> System.out.println("Hello World ->" + response),
  e -> new RuntimeException("Hello, exception!")
);

baseRequest.doGet(queryParam);

Http baseRequestPost = new HttpRequest()
  .subscribe(
  requestPost,
  (response) -> System.out.println("Hello World ->" + response),
  e -> new RuntimeException("Hello, exception!")
);

baseRequestPost.doPost(bodyParam);
```
