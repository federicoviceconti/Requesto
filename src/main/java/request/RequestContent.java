package request;

public enum RequestContent {
    XML("application/xml"), JSON("application/json"), HTML("text/html");

    private String contentType;
    RequestContent(String type) {
        this.contentType = type;
    }

    public String getContentType() {
        return this.contentType;
    }
}
