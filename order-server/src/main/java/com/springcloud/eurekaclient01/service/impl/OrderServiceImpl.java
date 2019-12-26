package com.springcloud.eurekaclient01.service.impl;

import com.springcloud.client.ProductClient;
import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.eurekaclient01.bean.OrderDetail;
import com.springcloud.eurekaclient01.bean.OrderMaster;
import com.springcloud.eurekaclient01.dao.OrderDetilDao;
import com.springcloud.eurekaclient01.dao.OrderMasterDao;
import com.springcloud.eurekaclient01.enums.OrderStatus;
import com.springcloud.eurekaclient01.enums.PayStatus;
import com.springcloud.eurekaclient01.enums.ResultEnum;
import com.springcloud.eurekaclient01.exception.OrderException;
import com.springcloud.eurekaclient01.service.OrderService;
import com.springcloud.eurekaclient01.util.KeyUtil;
import com.springcloud.eurekaclient01.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:41 2019/11/19
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetilDao orderDetilDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderVO create(OrderVO orderVO) {
        String orderId = KeyUtil.getUniqueKey();
        //查询商品信息（调用商品服务）
        List<String> productIdList = orderVO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderVO.getOrderDetailList()) {
            //单价乘以数量
            for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
                if (productInfoOutput.getProductId().equals(orderDetail.getProductId())) {
                    //累计单价*数量
                    orderAmount = productInfoOutput.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfoOutput, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetilDao.save(orderDetail);
                }
            }
        }

        //扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderVO.getOrderDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderVO.setOrderId(orderId);
        BeanUtils.copyProperties(orderVO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMasterDao.save(orderMaster);
        return orderVO;
    }

    @Override
    @Transactional
    public OrderVO finish(String orderId) {
        //1.查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterDao.findById(orderId);
        if(!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if(OrderStatus.NEW.getCode() != orderMaster.getOrderStatus()){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatus.FINISHED.getCode());
        orderMasterDao.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetilDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderMaster,orderVO);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }
}
