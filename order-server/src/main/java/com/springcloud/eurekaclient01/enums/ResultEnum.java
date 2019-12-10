package com.springcloud.eurekaclient01.enums;

import lombok.Getter;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:31 2019/11/19
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    CART_EMPTY(2,"购物车为空");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
