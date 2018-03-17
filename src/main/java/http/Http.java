package http;

import param.Param;

public interface Http {
    <T extends Param> void doRequest(T bodyParam);
    <T extends Param> void doRequestWithBody(T queryParam);
}
