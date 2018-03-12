package request;

public enum RequestUserAgent {
    MOZILLA_WIN_UA("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0"),
    MOZILLA_MAC_UA("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0"),
    CHROME_UA("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"),
    OPERA_UA("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36 OPR/38.0.2220.41"),
    SAFARI_UA("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1"),
    IE_UA("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)"),
    BOT_UA("Googlebot/2.1 (+http://www.google.com/bot.html)");

    private final String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    RequestUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
