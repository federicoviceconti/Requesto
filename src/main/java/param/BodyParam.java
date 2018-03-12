package param;

import utils.Utils;

import java.util.Map;

public class BodyParam extends BaseParam{
    public BodyParam(Map<String, String> params) {
        super(params);
    }

    @Override
    public String toString() {
        return Utils.buildParams(new StringBuilder(""), params);
    }
}
