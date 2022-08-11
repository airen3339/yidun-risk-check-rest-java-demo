package com.netease.risk.checkrestdemo.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;


/**
 * @author 花名：天长 坐标：杭州
 * @version 2022-08-05
 */
public class SignatureUtils {

    /**
     * 暴露获取token的方法
     * @param appKey 产品私钥，接入时会分配
     * @param appId appId，接入时会分配
     * @param timestamp 时间戳
     * @param nonce 随机码
     */

    public static String genSignature(String appKey, String appId, String timestamp, String nonce) throws UnsupportedEncodingException {
        Map<String, String> userParams = getTokenParams(appId, timestamp, nonce);
        return SignatureUtils.genSignature(appKey, userParams);
    }

    /**
     * 生成签名信息
     * @param appKey 产品私钥
     * @param params 接口请求参数名和参数值map，不包括signature参数名
     */
    private static String genSignature(String appKey, Map<String, String> params) throws UnsupportedEncodingException {
        // 1. 参数名按照ASCII码表升序排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        // 3. 将appKey拼接到最后
        sb.append(appKey);
        // 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
        return md5Hex(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 拼接参数
     * @param appId 每个应用接入时，会分配AppID
     * @param timestamp 时间戳
     * @param nonce 随机码
     */
    private static Map<String, String> getTokenParams(String appId, String timestamp, String nonce) {
        Map<String, String> userParams = new HashMap<>(16);
        userParams.put("appId", appId);
        userParams.put("timestamp", timestamp);
        userParams.put("nonce", nonce);
        return userParams;
    }
}
