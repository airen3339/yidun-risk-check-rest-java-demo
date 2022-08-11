package com.netease.risk.checkrestdemo.risk;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.netease.risk.checkrestdemo.utils.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author 花名：天长 坐标：杭州
 * @version 2022-08-05
 */
@Slf4j
@Service
public class RiskCheckService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 反外挂嫌疑在线检测示例demo
     *
     * @param paramJson 请求参数
     * @param checkUrl  请求地址
     */
    public ResponseEntity<String> doCheck(JSONObject paramJson, String checkUrl) {

        //1、添加请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        //2、组装请求头和参数
        HttpEntity<String> formEntity = new HttpEntity<>(JSON.toJSONString(paramJson), headers);

        //3、发起post请求
        ResponseEntity<String> stringResponseEntity = null;
        try {
            stringResponseEntity = restTemplate.postForEntity(checkUrl, formEntity, String.class);
            log.info("返回实体: {}", stringResponseEntity);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        //4、获取http状态码
        assert stringResponseEntity != null;
        int statusCodeValue = stringResponseEntity.getStatusCodeValue();

        //5、获取返回体
        String body = stringResponseEntity.getBody();
        log.info("状态码: {}， 返回值: {}", statusCodeValue, body);

        return stringResponseEntity;
    }

    /**
     * 构造手游请求参数
     */
    public JSONObject buildRiskParamJson() throws UnsupportedEncodingException {
        JSONObject paramJson = new JSONObject();
        // 每个应用接入时，会分配AppID。这里的appId仅为测试专用，需要按实际填写
        paramJson.put("appId", "A008554977");
        // 嫌疑上报数据，游戏方需要从反外挂客户端SDK获取该数据，需要按实际填写
        paramJson.put("mrData", "12345");
        // 生成随机码
        paramJson.put("nonce", UUID.randomUUID().toString().replace("-", ""));
        // 当前请求时间
        paramJson.put("timestamp", System.currentTimeMillis());
        // 玩家的IP，需要按实际填写
        paramJson.put("ip", "180.101.49.12");
        // 角色ID，需要按实际填写
        paramJson.put("roleId", "xxx-xx");
        // 角色名称，需要按实际填写
        paramJson.put("roleName", "yyy-yy");
        // 服务器，需要按实际填写
        paramJson.put("roleServer", "zzz-zz");
        // 用于校验数据权限
        paramJson.put("token", SignatureUtils.genSignature("e86de37c1ff94c3fbd83c0055e1f4cdb1431",
                paramJson.getString("appId"),
                paramJson.getString("timestamp"),
                paramJson.getString("nonce")));
        // 打印请求参数
        log.info("请求参数: {}", paramJson.toJSONString());
        return paramJson;
    }
}
