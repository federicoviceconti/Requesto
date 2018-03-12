package http;

import param.Param;

public interface Http {
    <T extends Param> void doPost(T bodyParam);
    <T extends Param> void doGet(T queryParam);
}
