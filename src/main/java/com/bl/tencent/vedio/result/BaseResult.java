package com.bl.tencent.vedio.result;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean           success          = false;
    private String            msg;
    private T                 data;
}
