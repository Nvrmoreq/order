package com.springcloud.eurekaclient01.vo;

import lombok.Data;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 16:53 2019/11/20
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
