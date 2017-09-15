package com.wyjf.app.utils.alipay;

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088...";
    public static String seller_email = "2088...";
    //APPID
    public static String alipay_appId = "2016**********12";
    // 商户的私钥
    public static String alipay_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKXc...";
    // 支付宝的公钥，无需修改该值
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVf...";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";
    // 接收通知的接口名
    public static String alipay_notify_url = "http://60.***.***.00/callbacks";
    //public static String service = "mobile.securitypay.pay";


}
