//package com.fangzhizun.cowxgzh.util;
//
//import com.qiniu.common.QiniuException;
//import com.qiniu.common.Zone;
//import com.qiniu.http.Response;
//import com.qiniu.storage.BucketManager;
//import com.qiniu.storage.Configuration;
//import com.qiniu.util.Auth;
//import com.qiniu.util.StringMap;
//
//public class UploadUtil {
//	//设置好账号的ACCESS_KEY和SECRET_KEY
//    static String ACCESS_KEY = "UNsQR-VxUCoEzgbUIhmqw7vlwbZt06AtiEr6HPOj";
//    static String SECRET_KEY = "oBvZsNPZmJnD32Edhvkm5J9pSR3xPb2CXu5mJM7I";
//
//    //要上传的空间
//    static String bucketname = "public";
//    String url = "http://pic-public.fangzhizun.com";
//
//    //密钥配置
//    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
//
//    //指定上传的Zone的信息
//    static Zone z = Zone.autoZone();
//    static Configuration c = new Configuration(z);
//
//    //服务器地址
//    String localhost = "http://www.fangzhizun.com/wxgzh2/";
//
//    public String getLocalhost() {
//		return localhost;
//	}
//
//	//PC端上传凭证
//    public String getUpTokenCallback() {
//        return auth.uploadToken(bucketname, null, 300, new StringMap()
//                .put("callbackUrl", localhost+"callback")
//                .put("callbackBody", "filename=$(fname)&key=$(key)&url="+url+"&jciId=$(x:jciId)&jrlId=$(x:jrlId)&jrrId=$(x:jrrId)&co=$(x:co)&att=$(x:att)&eaId=$(x:eaId)&saId=$(x:saId)&handlerId=$(x:handlerId)&handlerName=$(x:handlerName)&rcoId=$(x:rcoId)&wxKey=$(x:wxKey)")
//                .put("saveKey", "$(etag)"+ (int)((Math.random()*9+1)*1000) + "$(ext)")
//                .put("insertOnly", 1)
//                );
//    }
//
//    //手机端上传凭证
//    public String getMobUpTokenCallback() {
//        return auth.uploadToken(bucketname, null, 300, new StringMap()
//                .put("callbackUrl", localhost+"/upload/mobCallback.action")
//                .put("callbackBody", "filename=$(fname)&key=$(key)&url="+url+"&jrlId=$(x:jrlId)&jrrId=$(x:jrrId)&co=$(x:co)&qr=$(x:qr)&att=$(x:att)&eaId=$(x:eaId)&saId=$(x:saId)&handlerId=$(x:handlerId)&handlerName=$(x:handlerName)&rcoId=$(x:rcoId)")
//                .put("saveKey", "$(etag)"+ (int)((Math.random()*9+1)*1000) + "$(ext)")
//                .put("insertOnly", 1)
//                );
//    }
//
//    //普通上传
//    public String getUpToken(){
//    	return auth.uploadToken(bucketname, null, 300, new StringMap()
//    			.put("returnBody", "{\"filename\": $(fname), \"url\": \""+url+"$(key)\"}")
//		    	.put("saveKey", "$(etag)"+ (int)((Math.random()*9+1)*1000) + "$(ext)")
//		        .put("insertOnly", 1)
//    			);
//    }
//
//    //删除文件
//    public static void delete(String key){
//    	BucketManager bucketManager = new BucketManager(auth, c);
//    	try {
//            //调用delete方法移动文件
//            bucketManager.delete(bucketname, key);
//        } catch (QiniuException e) {
//            //捕获异常信息
//            Response r = e.response;
//            System.out.println(r.toString());
//        }
//    }
//
//    //下载凭证
//    public String getDownloadUrl(String baseUrl){
//    	return auth.privateDownloadUrl(baseUrl, 300);
//    }
//}
