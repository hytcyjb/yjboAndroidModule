package com.example;

public class testFirstClass {

    public static  void  main(String[] args){
//        System.out.print("---sss--");
//        System.out.print("---sss-2-");
//        System.out.print("---sss-3-");
//        System.out.print("---sss-3-");
//        System.out.print("---sss-4-");
//        String ewmString = "http://lbs.sougu.net.cn/fuwu/isopen.php?type=1&id=SOUGU8882099";
//        System.out.print("---sss-111-"+ewmString);
//        String id= ewmString.substring(ewmString.indexOf("&id=")+"&id=".length(), ewmString.length());
//        System.out.println("---sss-222-"+id);

        String nowVersion = "1.0.205";
        String new_version = "1.0.208";
        int resultNew = nowVersion.compareTo(new_version);
        System.out.println("--version--is->" + resultNew);
        String s1 = "abc";
        String s2 = "abcd";
        String s3 = "abcdfg";
        String s4 = "1bcdfg";
        String s5 = "cdfg";
        System.out.println( s1.compareTo(s2) ); // -1 (前面相等,s1长度小1)
        System.out.println( s1.compareTo(s3) ); // -3 (前面相等,s1长度小3)
        System.out.println( s1.compareTo(s4) ); // 48 ("a"的ASCII码是97,"1"的的ASCII码是49,所以返回48)
        System.out.println( s1.compareTo(s5) ); // -2 ("a"的ASCII码是97,"c"的ASCII码是99,所以返回-2)
    }
}
