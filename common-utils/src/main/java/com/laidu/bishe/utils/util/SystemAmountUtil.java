package com.laidu.bishe.utils.util;

import java.math.BigDecimal;

/**
 * Created by chenwen on 16/11/22.
 */
public class SystemAmountUtil {
    /**
     * 金额保留小数位数
     */
    public final static int amountScale = 2;

    /**
     * 金额取值方式
     */
    public final static int amountMath = BigDecimal.ROUND_HALF_UP;


    /**
     * 金额保留小数位数
     */
    public final static int feeScale = 6;


    /**
     * 费率取值方式
     */
    public final static int feeMath = BigDecimal.ROUND_HALF_UP;
}
