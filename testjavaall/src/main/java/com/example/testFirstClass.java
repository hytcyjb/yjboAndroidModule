package com.example;

import java.util.Random;

public class testFirstClass {

    public static void main(String[] args) {
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
        System.out.println(s1.compareTo(s2)); // -1 (前面相等,s1长度小1)
        System.out.println(s1.compareTo(s3)); // -3 (前面相等,s1长度小3)
        System.out.println(s1.compareTo(s4)); // 48 ("a"的ASCII码是97,"1"的的ASCII码是49,所以返回48)
        System.out.println(s1.compareTo(s5)); // -2 ("a"的ASCII码是97,"c"的ASCII码是99,所以返回-2)
        //--获取的司机列表数据-getgps-{"isidentification":0,"IsTaxiNoPaymentOrder":0,"error":1,"gpsdataint":30,"gpsdata":[
        // {"juli":"8165","lng":"116.4198866","lat":"39.8071631","uid":"806938","name":"\u5f20\u6625\u96f7","iphoen":"18500837550","angle":"0.00","cardid":"\u4eacBR2942"},
        // {"juli":"4","lng":"116.3453080","lat":"39.8529480","uid":"706667","name":"\u738b\u79cb\u6708","iphoen":"13716082492","angle":"0.00","cardid":"\u4eacjsbdjd"},{"juli":"18141","lng":"116.4793660","lat":"39.9794170","uid":"807103","name":"\u5218\u519b","iphoen":"15911025484","angle":"0.00","cardid":"\u4eacbp4039"},{"juli":"12583","lng":"116.2064890","lat":"39.8153833","uid":"807225","name":"\u738b\u6765\u9501","iphoen":"18710017428","angle":"0.00","cardid":"\u4eacbq8719"},{"juli":"17294","lng":"116.3964941","lat":"40.0032994","uid":"807030","name":"\u5b8b\u7acb\u5b66","iphoen":"13126563674","angle":"0.00","cardid":"\u4eacBG8247"},{"juli":"12806","lng":"116.4497493","lat":"39.9355154","uid":"807141","name":"\u79e6\u4fca\u7acb","iphoen":"13520358630","angle":"0.00","cardid":"\u4eacBQ8801"},{"juli":"21016","lng":"116.1029600","lat":"39.8853080","uid":"807199","name":"\u9ad8\u91d1\u7ea2","iphoen":"13301163227","angle":"0.00","cardid":"\u4eacBU3806"},{"juli":"2942","lng":"116.3775930","lat":"39.8621520","uid":"807200","name":"\u9648\u5c0f\u660e","iphoen":"15201359330","angle":"0.00","cardid":"\u4eac  BQ2657"},{"juli":"10245","lng":"116.4212476","lat":"39.9242188","uid":"807228","name":"\u4e8e\u6d77\u6d9b","iphoen":"13511026228","angle":"0.00","cardid":"\u4eacBR1897"},{"juli":"8784","lng":"116.3138957","lat":"39.9281158","uid":"806726","name":"\u738b\u6625","iphoen":"15801029176","angle":"0.00","cardid":"BQ5121"},{"juli":"9869","lng":"116.3055138","lat":"39.9362150","uid":"807163","name":"\u77f3\u6d9b","iphoen":"15201137909","angle":"0.00","cardid":"\u4eacbr0249"},{"juli":"14502","lng":"116.4617944","lat":"39.9477713","uid":"806688","name":"\u5218\u971e","iphoen":"13673002007","angle":"0.00","cardid":"\u4eac456"},{"juli":"29048","lng":"116.3624380","lat":"40.1135960","uid":"807135","name":"\u5f20\u9e4f","iphoen":"13901075392","angle":"0.00","cardid":"\u4eacBT1846"},{"juli":"18909","lng":"116.4918530","lat":"39.9803420","uid":"807080","name":"\u6731\u5b9d\u826f","iphoen":"13366371085","angle":"0.00","cardid":"\u4eacBS5440"},{"juli":"14265","lng":"116.5117520","lat":"39.8627970","uid":"807155","name":"\u5e38\u5f6c","iphoen":"15811104212","angle":"0.00","cardid":"\u4eacBQ5109"},{"juli":"16332","lng":"116.3716780","lat":"39.9982940","uid":"807227","name":"\u5f20\u660e\u5229","iphoen":"18518309346","angle":"0.00","cardid":"\u4eacBR9292"},{"juli":"30968","lng":"116.5874860","lat":"40.0601740","uid":"806796","name":"\u6768\u68ee","iphoen":"13718460353","angle":"0.00","cardid":"BN9386"},{"juli":"8408","lng":"116.3528370","lat":"39.9282940","uid":"807128","name":"\u8d75\u5b89","iphoen":"13381032377","angle":"0.00","cardid":"\u4eacBP6222"},{"juli":"30056","lng":"116.6531260","lat":"39.7227940","uid":"807173","name":"\u674e\u6c38\u5efa","iphoen":"13611261931","angle":"0.00","cardid":"\u4eacbs7226"},{"juli":"7641740","lng":"0.0000000","lat":"0.0000000","uid":"806809","name":"\u51fa\u79df\u53f8\u673a","iphoen":"18310376073","angle":"0.00","cardid":"\u4eacB8888"},{"juli":"17082","lng":"116.5438550","lat":"39.8708640","uid":"807169","name":"\u5b8b\u9ece\u660e","iphoen":"15611953215","angle":"0.00","cardid":"\u4eacBN6988"},{"juli":"14725","lng":"116.4615390","lat":"39.9506870","uid":"807081","name":"\u6768\u884d\u519b","iphoen":"18611886080","angle":"0.00","cardid":"\u4eacBP0209"},{"juli":"6893","lng":"116.2753700","lat":"39.8838530","uid":"806970","name":"\u80e1\u5b97\u5efa","iphoen":"13488851983","angle":"0.00","cardid":"\u4eacBP8544"},{"juli":"29620","lng":"116.6916954","lat":"39.8440850","uid":"806916","name":"\u9a6c\u58eb\u5168","iphoen":"18610341807","angle":"0.00","cardid":"\u4eacBR9276"},{"juli":"7548","lng":"116.4332837","lat":"39.8590552","uid":"806692","name":"\u738b\u86df\u9f99","iphoen":"13811760797","angle":"0.00","cardid":"\u4eac
        Random random = new Random();
        //39.852963
        //116.345296
        //生成随机数
        for (int i = 0; i < 10; i++) {
            System.out.println("random.nextInt()=" + random.nextDouble());
        }
//        支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'
//                * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'
//                * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'
//                * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00'
//                * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am'


        System.out.println("--输出的时间是：--" + getFormatedDateTime(1085));
        System.out.println("--输出的时间是：--" + getFormatedDateTime2(60));
        System.out.println("--输出的时间是：--" + getFormatedDateTime3((60*60)));
        System.out.println("--输出的时间是：--" + getFormatedDateTime3((60*60*24)));
    }
    //这是超过1分钟，并且小于59分钟采用的方法：
    public static String getFormatedDateTime(int dateInt) {
        String dateTime = "";
        if (dateInt < 10) {
            dateTime = "00:0" + dateInt;
        } else if (dateInt < 60) {
            dateTime = "00:" + dateInt;
        } else if (dateInt < (60 * 60 +1)) {//60' * 60 +1
            int miaoInt = dateInt % 60 ;
            int fenInt = dateInt / 60;
            //分别判断分钟和秒钟是否大于10，进行赋值
            String fenStr = "";
            String miaoStr = "";
            if (fenInt < 10) {
                fenStr = "0" + fenInt;
            }else {
                fenStr = "" + fenInt;
            }
            if (miaoInt < 10) {
                miaoStr ="0" + miaoInt;
            } else {
                miaoStr ="" + miaoInt;
            }
            dateTime = fenStr + ":" + miaoStr;
        } else {
            dateTime = "时间到停止计时";
        }

        return dateTime + "s";
    }
    //这是1分钟之内（包含一分钟）采用的方法：
    public static String getFormatedDateTime2(int dateInt) {
        String dateTime = "";
        if (dateInt < 10) {
            dateTime = "00:0" + dateInt;
        } else if (dateInt < 60) {
            dateTime = "00:" + dateInt;
        } else {//一分钟到
            dateTime = "01:00";
            dateTime = "时间到停止计时";
        }

        return dateTime + "s";
    }
    //这是1小时以上24小时以内（不包含24小时）采用的方法：
    public static String getFormatedDateTime3(int dateInt) {
        String dateTime = "";
        if (dateInt < 10) {
            dateTime = "00:0" + dateInt;
        } else if (dateInt < 60) {
            dateTime = "00:" + dateInt;
        } else if (dateInt < (60 * 60 +1)) {//60' * 60 +1
            int miaoInt = dateInt % 60 ;
            int fenInt = dateInt / 60;
            //分别判断分钟和秒钟是否大于10，进行赋值；大于10则不需要前面放一个0了
            String fenStr = "";
            String miaoStr = "";
            if (fenInt < 10) {
                fenStr = "0" + fenInt;
            }else {
                fenStr = "" + fenInt;
            }
            if (miaoInt < 10) {
                miaoStr ="0" + miaoInt;
            } else {
                miaoStr ="" + miaoInt;
            }
            dateTime = fenStr + ":" + miaoStr;
        } else if (dateInt < (60 * 60 * 24 +1)) {//60' * 60' * 24 +1

            int shiInt = dateInt / 60 / 60;

            dateInt = dateInt - shiInt * 60 * 60;
            int miaoInt = dateInt % 60 ;
            int fenInt = dateInt / 60;

            //分别判断小时，分钟和秒钟是否大于10，进行赋值；大于10则不需要前面放一个0了
            String shiStr = "";
            String fenStr = "";
            String miaoStr = "";

            if (shiInt < 10) {
                shiStr = "0" + shiInt;//这个不会遇到，因为没有到一小时，不走这里
            }else {
                shiStr = "" + shiInt;
            }
            if (fenInt < 10) {
                fenStr = "0" + fenInt;
            }else {
                fenStr = "" + fenInt;
            }
            if (miaoInt < 10) {
                miaoStr ="0" + miaoInt;
            } else {
                miaoStr ="" + miaoInt;
            }

            dateTime =shiStr+":"+ fenStr + ":" + miaoStr;
        }else  {
            dateTime = "时间到停止计时";
        }

        return dateTime + "s";
    }
}
