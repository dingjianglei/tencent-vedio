package com.bl.tencent.vedio.params;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseVedioClassParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            requestId;
}
