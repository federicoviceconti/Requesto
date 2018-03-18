package response;

import java.util.List;

public interface Response {
    List<String> getResponseHeader(HeaderResponseField headerResponseField);
    String getContent();
}
