package com.springcloud.eurekaclient01.controller;

import com.springcloud.eurekaclient01.converter.OrderForm2OrderVO;
import com.springcloud.eurekaclient01.enums.ResultEnum;
import com.springcloud.eurekaclient01.exception.OrderException;
import com.springcloud.eurekaclient01.form.OrderForm;
import com.springcloud.eurekaclient01.service.OrderService;
import com.springcloud.eurekaclient01.util.ResultVOUtil;
import com.springcloud.eurekaclient01.vo.OrderVO;
import com.springcloud.eurekaclient01.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:36 2019/11/19
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderFrom, BindingResult bindingResult){
        //表单认证
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，errorInfo={}",orderFrom);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //orderform -> orderVO
        OrderVO orderVO = OrderForm2OrderVO.convert(orderFrom);
        if(CollectionUtils.isEmpty(orderVO.getOrderDetailList())){
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderVO result = orderService.create(orderVO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return ResultVOUtil.success(map);
    }

}
