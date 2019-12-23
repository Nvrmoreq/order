package com.springcloud.eurekaclient01.enums;

import lombok.Getter;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:31 2019/11/19
 */
@Getter
public enum PayStatus {
    WAIT(0, "等待支付"),
    FINISHED(1, "支付成功");

    private Integer code;
    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
