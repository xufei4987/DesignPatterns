package com.youxu.dp.a01_oop.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken create(String baseUrl, long createTime, Map<String,String> params){
        String appId = params.get("appId");
        String passwd = params.get("passwd");
        String timestamp = params.get("timestamp");
        String encryptMaterial = String.join(".",baseUrl,appId,passwd,timestamp);
        //todo 对encryptMaterial进行加密
        String encrypted = md5Encrypt(encryptMaterial);
        return new AuthToken(encrypted, createTime);
    }

    public String getToken(){
        return token;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() > createTime + expiredTimeInterval;
    }

    public boolean match(AuthToken authToken){
        return this.token.equals(authToken.token);
    }

    /**
     * MD5简单加密
     * @param content 加密内容
     * @return String
     */
    private static String md5Encrypt(final String content) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        md5.update(text.getBytes());
        //digest()最后返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        //BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        BigInteger digest = new BigInteger(md5.digest(content.getBytes()));
        //32位
        return digest.toString(16);
    }

}
