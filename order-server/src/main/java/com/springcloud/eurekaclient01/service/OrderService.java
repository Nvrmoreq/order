package com.springcloud.eurekaclient01.service;

import com.springcloud.eurekaclient01.vo.OrderVO;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:38 2019/11/19
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderVO
     * @return
     */
    OrderVO create(OrderVO orderVO);

    /**
     * 完成订单（卖家操作，置成完结状态）
     * @param orderId
     * @return
     */
    OrderVO finish(String orderId);
}
