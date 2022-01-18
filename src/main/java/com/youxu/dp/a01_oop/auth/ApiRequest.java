package com.youxu.dp.a01_oop.auth;

public class ApiRequest {
    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    private ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static ApiRequest createFromFullUrl(String fullUrl){
        // todo check url
        if (fullUrl == null || "".equals(fullUrl)){
            throw new RuntimeException("url is not valid");
        }
        String[] urlAndParams = fullUrl.split("\\?");
        if (urlAndParams.length != 2){
            throw new RuntimeException("url is not valid");
        }
        String baseUrl = urlAndParams[0];
        String token = "",appId = "",timestamp = "0";
        String params = urlAndParams[1];
        String[] paramArr = params.split("&");
        for (String param : paramArr) {
            String[] keyAndVal = param.split("=");
            switch (keyAndVal[0]){
                case "appId":
                    appId = keyAndVal[1];
                    break;
                case "token":
                    token = keyAndVal[1];
                    break;
                case "timestamp":
                    timestamp = keyAndVal[1];
                    break;
                default:
                    break;
            }
        }
        return new ApiRequest(baseUrl,token,appId,Long.valueOf(timestamp));
    }
}
