package com.bl.tencent.vedio.test;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;

public class VedioTest {
    public static void main(String[] args) {
        String region = "ap-shanghai";
        String secretId = "AKIDxfCHArGWXHCsJVl7amFG0sZ1EDwBniZ1";
        String secretKey = "bzHK3XmneGivtlOnIsNm4GYgfUIyX71o";
        VodUploadClient vodClient = new VodUploadClient(secretId, secretKey);
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath("D:\\bl-offices\\tengx_db\\sc\\douyin.mp4");
        request.setCoverFilePath("D:\\bl-offices\\tengx_db\\sc\\doudou.jpg");

        try {
            VodUploadResponse response = vodClient.upload(region, request);
            System.out.println(JSONObject.toJSONString(response));
            // log.info("Upload FileId = {}", gson.toJson(response));
        } catch (Exception e) {
            // 业务方进行异常处理
        }
        //{"coverUrl":"http://1258859120.vod2.myqcloud.com/86395672vodsh1258859120/3706725e5285890786998923322/5285890786998923323.jpg","fileId":"5285890786998923322","mediaUrl":"http://1258859120.vod2.myqcloud.com/86395672vodsh1258859120/3706725e5285890786998923322/f0.mp4","requestId":"5f5025da-53db-4ee8-abae-8d471c7a4e20"}

    }

    public static COSClient getCOSClient(String secretId, String secretKey, String region) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
}
