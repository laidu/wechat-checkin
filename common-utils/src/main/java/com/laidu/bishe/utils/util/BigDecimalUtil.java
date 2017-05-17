package com.laidu.bishe.utils.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class BigDecimalUtil {
	
	/**
	 * 
	  * getSum(相加)
	  * @Description: Double类型相加
	 */
	public static BigDecimal getSum(BigDecimal b1,Double d2){
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		BigDecimal sum = null;
		if(b1!=null){
			sum = b1.add(bigDecimal2);
		}else{
			sum = bigDecimal2;
		}
		return sum;
	}
	
	public static Double getSum(Double b1,Double d2){
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(b1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		BigDecimal sum = bigDecimal1.add(bigDecimal2);
		return sum.doubleValue();
	}
	
	//乘 并且保留指定长度
	public static Double getMultipyWithLength(Double b1,BigDecimal d2,int len){
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(b1)); 
		BigDecimal sum = bigDecimal1.multiply(d2);
		BigDecimal b2 = new BigDecimal(1);
		return sum.divide(b2, len,BigDecimal.ROUND_HALF_UP).doubleValue();//四舍五入操作
	}
	
	//减 并且保留指定长度
	public static Double getMultipyWithLength(String b1,BigDecimal d2,int len){
		BigDecimal bigDecimal1 = new BigDecimal(b1); 
		BigDecimal sum = bigDecimal1.multiply(d2);
		BigDecimal b2 = new BigDecimal(1);
		BigDecimal result = sum.divide(b2, len,BigDecimal.ROUND_HALF_UP);
		return result.doubleValue();//四舍五入操作
	}
	
	//乘
	public static Double getDoubleMultipy(Double b1,Double b2){
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(b1)); 
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(b2));
		
		return bigDecimal1.multiply(bigDecimal2).doubleValue();
	}
	
	//减 四舍五入操作
	private static double round(double d,int len){
		BigDecimal bd = new BigDecimal(String.valueOf(d));
		BigDecimal b2 = new BigDecimal(1);
		return bd.divide(b2, len,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 
	  * daysBetween(计算两个时间间隔天数,多于整数天的按照+1计算)
	  *
	  * @Title: daysBetween
	  * @Description: TODO
	  * @param @param smdate 较小的时间
	  * @param @param bdate	 较大的时间
	  * @param @return    间隔天数
	  * @return int    返回类型
	  * @throws
	 */
	public static int daysBetween(Date smdate,Date bdate){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);
        if((time2-time1)%(1000*3600*24)>0){
        	between_days+=1;
        }
        
        return Integer.parseInt(String.valueOf(between_days)); 
	}
	
	//间隔整天  还款当天不算逾期  
	public static int daysBetween24(Date smdate,Date bdate){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        Long between_days=(time2-time1)/(1000*3600*24);
        
        return between_days.intValue(); 
	}
	
	/**
	 * 
	  * isInnerTime(判断前一个时间是否》=后一个时间 - 若干分钟)
	  *
	  * @Title: isInnerTime
	  * @Description: TODO
	  * @param @return     设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public static boolean isInnerTime(Date smdate,Date bgdate,int delayminutes){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);
        cal.add(Calendar.MINUTE, delayminutes);
        long time1 = cal.getTimeInMillis();
        
        cal.setTime(bgdate);    
        long time2 = cal.getTimeInMillis(); 
        
        if(time1<=time2){
        	return true;
        }
		return false;
	}
	
	/**
	  * getDoubleSubtract(计算两个Double类型的减法)
	 */
	public static double getDoubleSubtract(double big,double small){
		BigDecimal b1 = new BigDecimal(String.valueOf(big));
		BigDecimal b2 = new BigDecimal(String.valueOf(small));
		return b1.subtract(b2).doubleValue();
	}

}
