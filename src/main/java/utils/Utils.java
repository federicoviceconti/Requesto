package utils;

import response.HeaderResponseField;

import java.util.Map;

public class Utils {
    /**
     *
     * @param x
     * @return
     */
    public static String checkNullOrEmpty(String... x) {
        String errorParam = "";
        for(String toCheck: x) {
            errorParam = toCheck != null && !toCheck.isEmpty() ? "" : ", " + toCheck;
        }

        return !errorParam.equals("") ? (errorParam.substring(0, 2).contains(", ") ? errorParam.substring(2) : errorParam) : errorParam;
    }

    public static boolean checkNotNullOrEmpty(String x) {
        return x != null && !x.isEmpty();
    }

    public static <T> boolean checkNull(T x) {
        return x != null;
    }

    /**
     *
     * @param x
     * @return
     */
    public static boolean checkIfIntegerIsValid(int x) {
        return x >= 0;
    }

    public static String buildParams(StringBuilder resultBuilder, Map<String, String> params) {
        for(String param: params.keySet()) {
            resultBuilder.append(param).append("=").append(params.get(param)).append("&");
        }

        String result = resultBuilder.toString();
        return result.lastIndexOf("&") == result.length() - 1 ?
                result.substring(0, result.length() - 1) : result;
    }

    public static HeaderResponseField getHeaderByString(String key) {
        for(HeaderResponseField headerResponseField: HeaderResponseField.values()) {
            if(key != null && headerResponseField.getHeaderName().equals(key)) return headerResponseField;
        }

        return HeaderResponseField.RESPONSE_TYPE;
    }
}
