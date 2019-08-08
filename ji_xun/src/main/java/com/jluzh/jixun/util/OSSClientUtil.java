package com.jluzh.jixun.util;

import java.net.URL;
import java.util.Date;

import com.alibaba.druid.util.StringUtils;
import com.aliyun.oss.OSSClient;

public class OSSClientUtil {
	// Endpoint以杭州为例，其它Region请按实际情况填写。
	private String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
	//以下按需填写
	private String accessKeyId = "";
	private String accessKeySecret = "";
	private String bucketName = "";
	private OSSClient ossClient;
	 
	public OSSClientUtil() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}
	
	public void destory() {
	    ossClient.shutdown();
	}
	
	public String getImgUrl(String fileUrl) {
	    if (!StringUtils.isEmpty(fileUrl)) {
	      return this.getUrl(fileUrl);
	    }
	    return null;
	}
	public String getUrl(String key) {
	    // 设置URL过期时间为1年  3600l* 1000*24*365*1
	    Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 1);
	    // 生成URL
	    URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
	    if (url != null) {
	      return url.toString();
	    }
	    return null;
	 }	
	
}
