//package com.fangzhizun.cowxgzh.util;
//
//import com.alibaba.fastjson.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Formatter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//public class WxJsUtil {
//
//	private static final Logger log = LoggerFactory.getLogger(WxJsUtil.class);
//
//	//获取accessToken
//	public static String getAccessToken(String appId,String appSecret){
//	    String accessTokenUrl= "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//	    String requestUrl = accessTokenUrl.replace("APPID",appId).replace("APPSECRET",appSecret);
//	    String result = HttpUtil.getUrl(requestUrl);
//	    JSONObject job = JSONObject.parseObject(result);
//	    System.out.println(job);
//	    String accessToken = job.getString("access_token");
//	    return accessToken ;
//	}
//
//	//获取ticket
//	public static String getJsApiTicket(String accessToken){
//	    String apiTicketUrl= "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
//	    String requestUrl = apiTicketUrl.replace("ACCESS_TOKEN", accessToken);
//	    System.out.println("获取ticket的url "+ requestUrl);
//	    String result = HttpUtil.getUrl(requestUrl);
//	    JSONObject job = JSONObject.parseObject(result);
//	    String ticket = job.getString("ticket");
//	    return ticket;
//	}
//
//	//生成微信权限验证的参数
//	public static Map<String, String> makeWXTicket(String jsApiTicket, String url,String appid) {
//	    Map<String, String> ret = new HashMap<String, String>();
//	    String nonceStr = createNonceStr();
//	    String timestamp = createTimestamp();
//	    String string1;
//	    String signature = "";
//
//	    //注意这里参数名必须全部小写，且必须有序
//	    string1 = "jsapi_ticket=" + jsApiTicket +
//	            "&noncestr=" + nonceStr +
//	            "&timestamp=" + timestamp +
//	            "&url=" + url;
//	    try
//	    {
//	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//	        crypt.reset();
//	        crypt.update(string1.getBytes("UTF-8"));
//	        signature = byteToHex(crypt.digest());
//	    }
//	    catch (NoSuchAlgorithmException e)
//	    {
//	        log.error(e.getMessage(),e);
//	    }
//	    catch (UnsupportedEncodingException e)
//	    {
//	        log.error(e.getMessage(),e);
//	    }
//
//	    ret.put("url", url);
//	    ret.put("jsapi_ticket", jsApiTicket);
//	    ret.put("nonceStr", nonceStr);
//	    ret.put("timestamp", timestamp);
//	    ret.put("signature", signature);
//	    ret.put("appid", appid);
//
//	    return ret;
//	}
//	//字节数组转换为十六进制字符串
//	private static String byteToHex(final byte[] hash) {
//	    Formatter formatter = new Formatter();
//	    for (byte b : hash)
//	    {
//	        formatter.format("%02x", b);
//	    }
//	    String result = formatter.toString();
//	    formatter.close();
//	    return result;
//	}
//	//生成随机字符串
//	private static String createNonceStr() {
//	    return UUID.randomUUID().toString();
//	}
//	//生成时间戳
//	private static String createTimestamp() {
//	    return Long.toString(System.currentTimeMillis() / 1000);
//	}
//}
