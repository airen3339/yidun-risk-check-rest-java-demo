package com.netease.risk.checkrestdemo;

import com.alibaba.fastjson2.JSONObject;
import com.netease.risk.checkrestdemo.risk.RiskCheckService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

/**
 * @author 花名：天长 坐标：杭州
 * @version 2022-08-05
 */
@Slf4j
@SpringBootTest
class CheckRestDemoApplicationTests {
    @Autowired
    private RiskCheckService riskCheckService;

    /**
     * 点击启动该单元测试即可运行
     * 反外挂嫌疑在线检测示例demo
     */
    @Test
    void startCheck() throws UnsupportedEncodingException {
        log.info("反外挂嫌疑在线检测示例demo开始运行");

        // 手游请求地址
        String checkUrl = "http://open-yb.dun.163.com/api/open/v1/risk/doubtful/check";

        // 构造请求参数
        JSONObject paramJson = riskCheckService.buildRiskParamJson();

        // 执行检测
        riskCheckService.doCheck(paramJson, checkUrl);
    }
}
