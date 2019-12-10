package com.springcloud.eurekaclient01.dao;

import com.springcloud.eurekaclient01.bean.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:16 2019/11/19
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {
}
