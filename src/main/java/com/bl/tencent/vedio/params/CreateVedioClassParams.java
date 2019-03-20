package com.bl.tencent.vedio.params;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateVedioClassParams implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            className;
    private Integer           parentId;
    private Integer           subAppId;
}
