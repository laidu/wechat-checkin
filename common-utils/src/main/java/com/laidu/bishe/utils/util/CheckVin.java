package com.laidu.bishe.utils.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwen on 17/2/24.
 */
public class CheckVin {


    public static Map<Character, Integer> kv = new HashMap<>();

    public static Map<Integer, Integer> wv = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            kv.put(String.valueOf(i).charAt(0), i);
        }

        kv.put('a', 1);
        kv.put('b', 2);
        kv.put('c', 3);
        kv.put('d', 4);
        kv.put('e', 5);
        kv.put('f', 6);
        kv.put('g', 7);
        kv.put('h', 8);
        kv.put('j', 1);
        kv.put('k', 2);
        kv.put('l', 3);
        kv.put('m', 4);
        kv.put('n', 5);
        kv.put('p', 7);
        kv.put('q', 8);
        kv.put('r', 9);
        kv.put('s', 2);
        kv.put('t', 3);
        kv.put('u', 4);
        kv.put('v', 5);
        kv.put('w', 6);
        kv.put('x', 7);
        kv.put('y', 8);
        kv.put('z', 9);


        wv.put(1, 8);
        wv.put(2, 7);
        wv.put(3, 6);
        wv.put(4, 5);
        wv.put(5, 4);
        wv.put(6, 3);
        wv.put(7, 2);
        wv.put(8, 10);
        wv.put(10, 9);
        wv.put(11, 8);
        wv.put(12, 7);
        wv.put(13, 6);
        wv.put(14, 5);
        wv.put(15, 4);
        wv.put(16, 3);
        wv.put(17, 2);

    }

    public static boolean isNewVinLegal(String vin){
        if (null == vin){
            return false;
        }
        return RegexpUtil.getRegexp("[a-zA-Z0-9]{17}",vin) != null;
    }

    public static boolean isLegal(String vin) {
        if (null == vin) {

            return false;

        } else if (vin.trim().length() == 17) {

            vin = vin.trim().toLowerCase();
            char[] codes = vin.toCharArray();


            int resultInCode = 0;

            if ("0123456789x".contains(vin.subSequence(8, 9))) {

                //重点哟
                if(vin.subSequence(8, 9).toString().equals("x")){

                    resultInCode = 10;

                }else {
                    resultInCode = Integer.valueOf(vin.subSequence(8, 9).toString());
                }
            } else {

                return false;
            }

            int total = 0;

            for (int i = 1; i < codes.length + 1; i++) {

                char code = codes[i - 1];

                if (kv.containsKey(code)) {

                    if (9 == i) {

                        continue;

                    } else {

                        total += kv.get(code) * wv.get(i);

                    }
                } else {

                    return false;

                }
            }
            return resultInCode == total % 11;

        } else {

            return false;
        }
    }

//    /**
//     * 检验VIN格式
//     * @param vin
//     * @return
//     */
//    public  boolean checkVIN(String vin) {
//        Map<Integer, Integer> vinMapWeighting = null;
//        Map<Character, Integer> vinMapValue = null;
//        vinMapWeighting = new HashMap<Integer, Integer>();
//        vinMapValue = new HashMap<Character, Integer>();
//        vinMapWeighting.put(1, 8);
//        vinMapWeighting.put(2, 7);
//        vinMapWeighting.put(3, 6);
//        vinMapWeighting.put(4, 5);
//        vinMapWeighting.put(5, 4);
//        vinMapWeighting.put(6, 3);
//        vinMapWeighting.put(7, 2);
//        vinMapWeighting.put(8, 10);
//        vinMapWeighting.put(9, 0);
//        vinMapWeighting.put(10, 9);
//        vinMapWeighting.put(11, 8);
//        vinMapWeighting.put(12, 7);
//        vinMapWeighting.put(13, 6);
//        vinMapWeighting.put(14, 5);
//        vinMapWeighting.put(15, 4);
//        vinMapWeighting.put(16, 3);
//        vinMapWeighting.put(17, 2);
//
//        vinMapValue.put('0', 0);
//        vinMapValue.put('1', 1);
//        vinMapValue.put('2', 2);
//        vinMapValue.put('3', 3);
//        vinMapValue.put('4', 4);
//        vinMapValue.put('5', 5);
//        vinMapValue.put('6', 6);
//        vinMapValue.put('7', 7);
//        vinMapValue.put('8', 8);
//        vinMapValue.put('9', 9);
//        vinMapValue.put('A', 1);
//        vinMapValue.put('B', 2);
//        vinMapValue.put('C', 3);
//        vinMapValue.put('D', 4);
//        vinMapValue.put('E', 5);
//        vinMapValue.put('F', 6);
//        vinMapValue.put('G', 7);
//        vinMapValue.put('H', 8);
//        vinMapValue.put('J', 1);
//        vinMapValue.put('K', 2);
//        vinMapValue.put('M', 4);
//        vinMapValue.put('L', 3);
//        vinMapValue.put('N', 5);
//        vinMapValue.put('P', 7);
//        vinMapValue.put('R', 9);
//        vinMapValue.put('S', 2);
//        vinMapValue.put('T', 3);
//        vinMapValue.put('U', 4);
//        vinMapValue.put('V', 5);
//        vinMapValue.put('W', 6);
//        vinMapValue.put('X', 7);
//        vinMapValue.put('Y', 8);
//        vinMapValue.put('Z', 9);
//        boolean reultFlag = false;
//        String uppervin = vin.toUpperCase();
//        //排除字母O、I
//        if (vin == null || uppervin.indexOf("O") >= 0|| uppervin.indexOf("I") >= 0) {
//            reultFlag = false;
//        } else {
//            //1:长度为17
//            if (vin.length() == 17) {
//                char[] vinArr = uppervin.toCharArray();
//                int amount = 0;
//                for (int i = 0; i < vinArr.length; i++) {
//                    //VIN码从从第一位开始，码数字的对应值×该位的加权值，计算全部17位的乘积值相加
//                    amount += vinMapValue.get(vinArr[i])*vinMapWeighting.get(i + 1);
//                }
//                //乘积值相加除以11、若余数为10，即为字母Ｘ
//                if (amount % 11 == 10) {
//                    if (vinArr[8] == 'X') {
//                        reultFlag = true;
//                    } else {
//                        reultFlag = false;
//                    }
//
//                } else {
//                    //VIN码从从第一位开始，码数字的对应值×该位的加权值，
//                    //计算全部17位的乘积值相加除以11，所得的余数，即为第九位校验值
//                    if (amount % 11 != vinMapValue.get(vinArr[8])) {
//                        reultFlag = false;
//                    } else {
//                        reultFlag = true;
//                    }
//                }
//            }
//            //1:长度不为17
//            if (!vin.equals("") && vin.length() != 17) {
//                reultFlag = false;
//            }
//        }
//        return reultFlag;
//    }

    public static void main(String[] args){
        String str = "LGXC14DG801001957";
        CheckVin  checkVin = new CheckVin();
        String[] tempStr = str.split(",");
        for(String temp:tempStr){
            System.out.println(checkVin.isLegal(temp)+"\r\n");
//            System.out.println(checkVin.checkVIN(temp)+"\r\n");
        }
    }
}
