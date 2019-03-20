package com.bl.tencent.vedio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bl.tencent.vedio.client.VodClient;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;

@Configuration
public class BeanConfig {
    @Value("${tecent.region}")
    String region;
    @Value("${tecent.secretId}")
    String secretId;
    @Value("${tecent.secretKey}")
    String secretKey;

    @Bean
    public VodClient getVodClient() {
        Credential credential = new Credential(secretId, secretKey);
        ClientProfile profile = new ClientProfile();
        VodClient client = new VodClient(credential, region, profile);
        return client;
    }

    @Bean
    public COSClient getCOSClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    @Bean
    public VodUploadClient getVodUploadClient() {
        VodUploadClient vodClient = new VodUploadClient(secretId, secretKey);
        return vodClient;
    }

}
