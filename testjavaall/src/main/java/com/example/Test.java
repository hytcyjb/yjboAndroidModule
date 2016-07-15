package com.example;

public class Test {

       public boolean foo(char c) {
           System.out.print(c);
           //ABDCBDCB
           return true;
       }
       public static void main(String[] argv) {
           Test test=new Test();

           int i = 0;
           for (test.foo('A'); test.foo('B') && (i < 2); test.foo('C')) {
               i++;
               System.out.print("=====");
               test.foo('D');
           }
//           int i = 0;
//           for (test.foo1(1); test.foo('2')&& (i < 2); test.foo1(2)) {
//               i++;
//               System.out.print("=====");
//               test.foo('D');
//           }
       }
    public int foo1(int c) {
        System.out.print(c);
        //ABDCBDCB
        return c;
    }
}

//这是网友发的一道很常见的面试题， for(foo(‘A’);foo(‘B’)&&(i<2);foo(‘C’)) ，其实考的就是for (初始化语句; 判断条件; 循环条件) ，现在详细解析一下！
//        2014年09月05日 ? 综合 ? 共 725字	? 字号 小 中 大 ? 评论关闭
//        1. package com.zzk.test;
//        2.
//        3. public class Test {
//    4.     static boolean foo(char c) {
//        5.         System.out.print(c);
//        6.         return true;
//        7.     }
//    8.
//            9.     public static void main(String[] args) {
//        10.         int i=0;
//        11.
//        12.         for(foo('A');foo('B')&&(i<2);foo('C')) {
//            13.             i++;
//            14.             foo('D');
//            15.         }
//        16.     }
//    17. }
//
//1.其实foo('A');就是初始化条件，只会执行一次，所以第一个打印的肯定是A
//        2.因为i=0;循环条件是i<2 （由此可知循环i等于2的时候就会停止循环，)所有0<2满足条件，接着会输出B，然后执行i++;i就变成1了，在输出D
//        ，在最后输出C，
//        一次循环后的结果是：ABDC
//        3.第二次循环的开始是foo('A');是初始条件所以不会执行，直接从foo('B')开始，输出B，然后i为1，且小于2，此时循环体内再次执行i++；i的值为2了，再次输出D，最后输出C
//        第二次循环输出：BDC
//        4.然后循环再次执行for(foo('A');foo('B')&&(i<2);foo('C'))
//        直接输出B，i的值在第二轮循环后的值变成了2，2<2不成立，终止循环，输出B
//
//        最后答案为：ABDCBDCB
//        希望对初学者有所帮助！