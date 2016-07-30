package com.example;

import java.util.ArrayList;

/**
 * @description: <描述当前版本功能>
 * <p>
 * </p>
 * @author: yjbo
 * @date: 2016-07-15 18:34
 */
public class Test1 {


    private ArrayList<Integer> listA, listB;
    private  int sum;

    public static void main(String[] args) {
        Test1 test=new Test1();
        test.listA = new ArrayList<>();
        test.listB = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            test.listA.add(i, i); //0-9
        }

        for (int i = 0; i < 10; i++) {

            if (i >= 4) {
                test.sum = 0;
                test.listB.add(i, test.fund(i - 4, i)); //【4-9，】0-5， 4-9
            } else {
                test.listB.add(i, 0);//【0-3，0】
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(test.listB.get(i) + "   ");
            //结果：0,0,0,0,10,15,20,25,30,35
        }

    }

    public  Integer fund(Integer a, Integer b) {

        for (int i = a; i <= b; i++) {
            sum += listA.get(i); //0+1+2+3+4 =10
        }
        return sum;
    }


}
