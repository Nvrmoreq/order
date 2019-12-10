package com.springcloud.eurekaclient01.vo;

import lombok.Data;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 15:37 2019/11/25
 */
@Data
public class CartVO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartVO() {
    }

    public CartVO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
