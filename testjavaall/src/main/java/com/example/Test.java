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

//�������ѷ���һ���ܳ����������⣬ for(foo(��A��);foo(��B��)&&(i<2);foo(��C��)) ����ʵ���ľ���for (��ʼ�����; �ж�����; ѭ������) ��������ϸ����һ�£�
//        2014��09��05�� ? �ۺ� ? �� 725��	? �ֺ� С �� �� ? ���۹ر�
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
//1.��ʵfoo('A');���ǳ�ʼ��������ֻ��ִ��һ�Σ����Ե�һ����ӡ�Ŀ϶���A
//        2.��Ϊi=0;ѭ��������i<2 ���ɴ˿�֪ѭ��i����2��ʱ��ͻ�ֹͣѭ����)����0<2�������������Ż����B��Ȼ��ִ��i++;i�ͱ��1�ˣ������D
//        ����������C��
//        һ��ѭ����Ľ���ǣ�ABDC
//        3.�ڶ���ѭ���Ŀ�ʼ��foo('A');�ǳ�ʼ�������Բ���ִ�У�ֱ�Ӵ�foo('B')��ʼ�����B��Ȼ��iΪ1����С��2����ʱѭ�������ٴ�ִ��i++��i��ֵΪ2�ˣ��ٴ����D��������C
//        �ڶ���ѭ�������BDC
//        4.Ȼ��ѭ���ٴ�ִ��for(foo('A');foo('B')&&(i<2);foo('C'))
//        ֱ�����B��i��ֵ�ڵڶ���ѭ�����ֵ�����2��2<2����������ֹѭ�������B
//
//        ����Ϊ��ABDCBDCB
//        ϣ���Գ�ѧ������������