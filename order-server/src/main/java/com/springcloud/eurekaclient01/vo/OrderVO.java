package com.springcloud.eurekaclient01.vo;

import com.springcloud.eurekaclient01.bean.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:39 2019/11/19
 */
@Data
public class OrderVO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
