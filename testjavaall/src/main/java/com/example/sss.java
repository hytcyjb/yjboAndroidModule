package com.example;

/**
 * @description: <描述当前版本功能>
 * <p>
 * </p>
 * @author: yjbo
 * @date: 2016-07-03 15:53
 */
public class sss {

    public static void main(String[] args) {

        double x = stringToDouble("01.555");

        if (x == 0) {
            System.out.println(x + "--xiangdeng---");
        } else {
            System.out.println(x + "--no---");
        }
        System.out.println("--00-" + x);
        System.out.println("-22----");

        String activityStr = "HomeMainActivity";
        if (!"HomeMainActivity".equals(activityStr)
                && !"SouGUMainActivity".equals(activityStr)) {
            System.out.println("--00-" + activityStr);
        }
    }

    /***
     * 将String型转换成double型
     * @param s
     * @return double
     */
    public static double stringToDouble(String s) {
        double x = 0;
        try {
             x = Double.valueOf(s);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return x;
    }

}
