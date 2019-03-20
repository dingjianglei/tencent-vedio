package com.bl.tencent.vedio.service;

import com.bl.tencent.vedio.params.CreateVedioClassParams;
import com.bl.tencent.vedio.response.CreateVedioClassResponse;
import com.bl.tencent.vedio.result.BaseResult;

/**
 * 视频分类服务接口
 * 
 * @author DJL76
 * @version $Id: VedioClassService.java, v 0.1 2019年3月20日 下午1:49:09 DJL76 Exp $
 */
public interface VedioClassService {
    /**
     * 创建视频分类
     * 
     * @param params
     * @return
     */
    BaseResult<CreateVedioClassResponse> createVedioClass(CreateVedioClassParams params);
}
