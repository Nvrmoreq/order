package com.springcloud.eurekaclient01.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.springcloud.eurekaclient01.bean.OrderDetail;
import com.springcloud.eurekaclient01.enums.ResultEnum;
import com.springcloud.eurekaclient01.exception.OrderException;
import com.springcloud.eurekaclient01.form.OrderForm;
import com.springcloud.eurekaclient01.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 15:44 2019/11/20
 */
@Slf4j
public class OrderForm2OrderVO {

    public static OrderVO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderVO orderVO = new OrderVO();
        orderVO.setBuyerName(orderForm.getName());
        orderVO.setBuyerPhone(orderForm.getPhone());
        orderVO.setBuyerAddress(orderForm.getAddress());
        orderVO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();

        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【json转换】错误，string{}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderVO.setOrderDetailList(orderDetailList);

        return orderVO;
    }
}
