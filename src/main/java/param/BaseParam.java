package param;

import utils.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseParam implements Param<String, String, String> {
    protected Map<String, String> params;
    private final Object object = new Object();

    public BaseParam(Map<String, String> params) {
        this.params = params;
    }

    public String addParam(String param, String value) {
        if(params == null) {
            synchronized (object) {
                if(params == null) {
                    params = Collections.synchronizedMap(new HashMap<String, String>());
                }
            }
        }

        return params.put(param, value);
    }

    @Override
    public String buildParam() {
        return this.toString();
    }

    @Override
    public String getLength() {
        return String.valueOf(toString().length());
    }

    @Override
    public String getParam(String param) {
        return params.get(param);
    }
}
