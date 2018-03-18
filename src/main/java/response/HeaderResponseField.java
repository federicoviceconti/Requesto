package response;

public enum HeaderResponseField {
    CF_RAY("CF-RAY"), RESPONSE_TYPE(null), SERVER("Server"), CONTENT_TYPE_OPT("X-Content-Type-Options"),
    CONNECTION("Connection"), PRAGMA("Pragma"), DATE("Date"), VIA("Via"),
    ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"), ACCESS_CONTROL_ALLOW_CRED("Access-Control-Allow-Credentials"),
    ETAG("Etag"), CACHE_CONTROL("Cache-Control"), VARY("Vary"), SET_COOKIE("Set-Cookie"),
    EXPIRES("Expires"), CONTENT_LEN("Content-Length"), LOCATION("Location"), EXPECT_CT("Expect-CT"),
    X_POWERED_BY("X-Powered-By"), CONTENT_TYPE("Content-Type");

    private final String headerName;

    HeaderResponseField(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}
