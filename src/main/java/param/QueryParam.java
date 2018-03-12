package param;

import utils.Utils;

import java.util.Map;

public final class QueryParam extends BaseParam {
    public QueryParam(Map<String, String> params) {
        super(params);
    }

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder("?");
        return Utils.buildParams(resultBuilder, params);
    }
}
