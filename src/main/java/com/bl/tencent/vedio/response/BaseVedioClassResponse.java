package com.bl.tencent.vedio.response;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseVedioClassResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            requestId;
}
