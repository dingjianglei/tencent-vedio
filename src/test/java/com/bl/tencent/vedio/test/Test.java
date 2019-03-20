package com.bl.tencent.vedio.test;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;

public class Test {
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

    }
}
