package com.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvokerProtocol implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] params;//参数类型列表
    private Object[] values;//参数列表
}
