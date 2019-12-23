package com.springcloud.eurekaclient01.util;

import java.util.Random;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:29 2019/11/19
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
