package com.springcloud.eurekaclient01.util;

import com.springcloud.eurekaclient01.vo.ResultVO;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 16:55 2019/11/20
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);

        return resultVO;
    }
}
