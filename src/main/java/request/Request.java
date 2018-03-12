package request;

import exception.MalformedBuilderParameterException;
import utils.Utils;

public final class Request {
    private String baseUrl;
    private RequestContent requestContentType;
    private RequestMethod requestMethod;
    private RequestUserAgent requestUserAgent;
    private int contentLength;

    public Request(Builder builder) {
        builder._requestContentType = builder._requestContentType == null ? RequestContent.JSON : builder._requestContentType;
        builder._requestMethod = builder._requestMethod == null ? RequestMethod.GET : builder._requestMethod;
        builder._requestUserAgent = builder._requestUserAgent == null ? RequestUserAgent.MOZILLA_WIN_UA : builder._requestUserAgent;


        baseUrl = builder._baseUrl;
        requestMethod = builder._requestMethod;
        requestUserAgent = builder._requestUserAgent;
        requestContentType = builder._requestContentType;

        if (Utils.checkIfIntegerIsValid(builder._contentLength)) {
            contentLength = builder._contentLength;
        } else throw new MalformedBuilderParameterException("contentLength");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public RequestContent getRequestContentType() {
        return requestContentType;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public int getContentLength() {
        return contentLength;
    }

    public RequestUserAgent getRequestUserAgent() { return requestUserAgent; }

    public static class Builder {
        private String _baseUrl;
        private RequestContent _requestContentType;
        private RequestMethod _requestMethod;
        private RequestUserAgent _requestUserAgent;
        private int _contentLength;

        public Builder setBaseUrl(String _baseUrl) {
            this._baseUrl = _baseUrl;
            return this;
        }

        public Builder addSubPathToBaseUrl(String subPath) {
            if(!Utils.checkNotNullOrEmpty(_baseUrl)) throw  new MalformedBuilderParameterException("BASE_URL is null or empty");
            if(subPath.contains("/")) throw new MalformedBuilderParameterException("Not use '/'. It's permitted only one subpath per time!");
            if(_baseUrl.lastIndexOf("/") != _baseUrl.length() - 1) _baseUrl += "/";
            _baseUrl += subPath;

            System.out.println(_baseUrl);
            return  this;
        }

        public Builder setContentType(RequestContent _requestContentType) {
            this._requestContentType = _requestContentType;
            return this;
        }

        public Builder setRequestMethod(RequestMethod _requestMethod) {
            this._requestMethod = _requestMethod;
            return this;
        }

        public Builder setContentLength(int _contentLength) {
            this._contentLength = _contentLength;
            return this;
        }

        public Builder setUserAgent(RequestUserAgent requestUserAgent) {
            this._requestUserAgent = requestUserAgent;
            return this;
        }

        public Request create() {
            return new Request(this);
        }
    }
}
