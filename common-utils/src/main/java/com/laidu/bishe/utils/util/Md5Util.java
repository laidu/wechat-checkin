package com.laidu.bishe.utils.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hp on 15-11-16.
 */
public class Md5Util {
    /**
     *
     * @param str
     *            明文
     * @return 32位密文
     */
    public static String toMd5(String str) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static void main(String[] args) {
       System.out.print( Md5Util.toMd5("c86ba808-dd7e-4f24-af2a-c93f2bf2e1bf1477983880"));




        List<Integer> query =new ArrayList();
        query.add(1);
        query.add(2);
        query.add(3);

        List<Integer> added =new ArrayList();
        added.addAll(query);


        List<Integer> areaIds=new ArrayList<>();
        areaIds.add(2);
        areaIds.add(4);
        areaIds.add(5);


        //删除的
        query.removeAll(areaIds);

        //新增的
        areaIds.removeAll(added);


        Iterator<Integer> it=query.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("---- + -------");
        Iterator<Integer> its=areaIds.iterator();
        while (its.hasNext()) {
            System.out.println(its.next());
        }

        //并集
//        list1.addAll(list2);
//        Iterator<String> it=list1.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//
//        System.out.println("-----------");
//        //交集
//        list1.retainAll(list2)
//        Iterator<String> j=list1.iterator();
//        while (j.hasNext()) {
//            System.out.println(j.next());
//        }
//

//        list1.removeAll(list2);
//        Iterator<String> c=list1.iterator();
//        while (c.hasNext()) {
//            System.out.println(c.next());
//        }
//
//        System.out.println("args = [" + b + "]");
//
//        System.out.println("-----------");
//        list2.removeAll(b);
//        Iterator<String> c1=list2.iterator();
//        while (c1.hasNext()) {
//            System.out.println(c1.next());
//        }System.out.println("-----------");

        //System.out.println("-----------");

//        System.out.println("-----------");
//        //差集
//        list1.removeAll(list2);
//
//        Iterator<String> it22=list1.iterator();
//        while (it22.hasNext()) {
//            System.out.println(it22.next());
//        }
//        System.out.println("-----------");
//
//        //无重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);

//        list2.removeAll(list1);
//        list1.addAll(list2);
//
//
//        list1.removeAll(list2);
//        Iterator<String> it1=list1.iterator();
//        while (it1.hasNext()) {
//            System.out.println(it1.next());
//
//
//        }



    }
}
