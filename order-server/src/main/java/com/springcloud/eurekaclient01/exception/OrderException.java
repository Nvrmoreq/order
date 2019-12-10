package com.springcloud.eurekaclient01.exception;

import com.springcloud.eurekaclient01.enums.ResultEnum;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 15:37 2019/11/20
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
