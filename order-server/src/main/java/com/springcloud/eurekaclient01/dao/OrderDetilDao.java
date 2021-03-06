package com.springcloud.eurekaclient01.dao;

import com.springcloud.eurekaclient01.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:17 2019/11/19
 */
public interface OrderDetilDao extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
