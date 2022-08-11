# 反外挂手游嫌疑在线检测示例demo说明

## 游戏方自助式透传（透传3.0）说明文档地址：
https://support.dun.163.com/documents/413846598587424768?docId=697945646425726976

### 方法说明
- 单元测试入口方法： CheckRestDemoApplicationTests.startCheck() 推荐通过该启动方式启动
- 检测参数构造方法： RiskCheckService.buildRiskParamJson()
- 执行检测核心方法： RiskCheckService.doCheck()
- 签名生成核心方法： SignatureUtils.genSignature()

### 请求参数示例:
{"appId":"A008554977","mrData":"12345","nonce":"8f6057047e40413486dcf10f9c8845d8","timestamp":1659940891557,"ip":"180.101.49.12","roleId":"xxx-xx","roleName":"yyy-yy","roleServer":"zzz-zz","token":"c82c0020bcd139457d4addb25e090c2f"}

### 返回值示例:
状态码: 200， 返回值: {"code":200,"msg":"ok!","data":{"action":0,"hitInfos":[{"tag1Id":"2","tag1Name":"RISK_DETECT","tag2Id":"885","tag2Name":"数据异常","tag3Id":"886","tag3Name":"解析异常"}]}}

### 返回码定义:
见说明文档

### 其它说明:
当传入不合法的或者随便填写的检测数据，即mrData，会返回数据异常检测结果，但此时已经代表请求成功。
当传入合法有效的检测数据，即mrData，会检测返回正确响应结果，如有问题请联系技术支持。
