package com.springcloud.eurekaclient01.form;

import com.springcloud.common.ProductInfoOutput;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 9:49 2019/12/13
 */
@Data
public class JsonFrom implements Serializable {

    private static final long serialVersionUID = -3221079730416682909L;

    private List<ProductInfoOutput> productInfoOutputList;
}
