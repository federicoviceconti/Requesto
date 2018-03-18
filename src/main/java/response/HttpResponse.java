package response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponse implements Response {
    private Map<HeaderResponseField, List<String>> mapHeader;
    private String content;

    public HttpResponse(Map<HeaderResponseField, List<String>> mapHeader, String content) {
        this.mapHeader = new HashMap<>(mapHeader);
        this.content = content;
    }

    @Override
    public List<String> getResponseHeader(HeaderResponseField headerResponseField) {
        return mapHeader.get(headerResponseField);
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "mapHeader=" + mapHeader +
                ", content='" + content + '\'' +
                '}';
    }
}
