package com.bl.tencent.vedio.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bl.tencent.vedio.client.VodClient;
import com.bl.tencent.vedio.result.BaseResult;
import com.google.common.base.Throwables;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.vod.v20180717.models.ApplyUploadRequest;
import com.tencentcloudapi.vod.v20180717.models.ApplyUploadResponse;
import com.tencentcloudapi.vod.v20180717.models.DescribeAllClassRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeAllClassResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bl-tencent/vedio")
public class UploadVedioController {
    @Autowired
    VodClient       vodClient;
    @Autowired
    VodUploadClient vodUploadClient;

    @RequestMapping(value = "/getVedioClass", method = { RequestMethod.GET, RequestMethod.POST })
    public BaseResult<DescribeAllClassResponse> getVedioClass() {
        BaseResult<DescribeAllClassResponse> returnResult = new BaseResult<DescribeAllClassResponse>();
        try {
            DescribeAllClassResponse resp = vodClient
                .DescribeAllClass(new DescribeAllClassRequest());
            returnResult.setSuccess(true);
            returnResult.setData(resp);
        } catch (Exception e) {
            log.error("getVedioClass error. msg:{}", Throwables.getRootCause(e));
            returnResult.setMsg("获取视频分类列表失败");
        }
        return returnResult;
    }

    @RequestMapping(value = "/applyUpload", method = { RequestMethod.GET, RequestMethod.POST })
    public BaseResult<ApplyUploadResponse> applyUpload(ApplyUploadRequest request) {

        BaseResult<ApplyUploadResponse> returnResult = new BaseResult<ApplyUploadResponse>();
        if (request == null || request.getMediaType() == null) {
            returnResult.setMsg("mdiaType不能为空");
            return returnResult;
        }
        try {
            ApplyUploadResponse resp = vodClient.ApplyUpload(request);
            returnResult.setSuccess(true);
            returnResult.setData(resp);
        } catch (Exception e) {
            log.error("applyUpload error. msg:{}", Throwables.getRootCause(e));
            returnResult.setMsg("申请上传失败");
        }
        return returnResult;

    }

    @RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
    public BaseResult<VodUploadResponse> upload(@RequestParam("file") MultipartFile file) {
        BaseResult<VodUploadResponse> returnResult = new BaseResult<VodUploadResponse>();
        if (file.isEmpty()) {
            returnResult.setMsg("上传失败，请选择文件");
            return returnResult;
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:\\bl-offices\\tengx_db\\temp";
        File dest = null;
        try {
            dest = new File(filePath + fileName);
            file.transferTo(dest);
            log.info("上传成功");
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(dest.getAbsolutePath());
        try {
            VodUploadResponse response = vodUploadClient.upload(vodClient.getRegion(), request);
            returnResult.setSuccess(true);
            returnResult.setData(response);
        } catch (Exception e) {
            log.error("upload error. msg:{}", Throwables.getRootCause(e));
            returnResult.setMsg("上传失败");
        }
        return returnResult;
    }
}
