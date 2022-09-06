package com.youxu.dp.a02_principle.version2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestInfo {
    private String apiName;
    private double responseTime;
    private long timestamp;
}
