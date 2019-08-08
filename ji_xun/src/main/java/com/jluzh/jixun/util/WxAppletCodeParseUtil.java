package com.jluzh.jixun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WxAppletCodeParseUtil {
	public static JSONObject getSessionKeyOropenid(String code){
        //微信端登录code值
        String wxCode = code;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";	//请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String,String>requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", "");	//TODO:开发者设置中的appId
        requestUrlParam.put("secret", "");	//TODO:开发者设置中的appSecret
        requestUrlParam.put("js_code", code);	//小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");	//默认参数
        
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData.getBytes());
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey.getBytes());
        // 偏移量
        byte[] ivByte = Base64.decode(iv.getBytes());
        try {
               // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    } 
    
    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        
        String param = "";
		Iterator<String> it = paramMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			param += key + "=" + paramMap.get(key) + "&";
		}
		
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
