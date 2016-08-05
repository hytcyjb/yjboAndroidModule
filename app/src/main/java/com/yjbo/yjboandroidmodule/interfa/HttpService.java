package com.yjbo.yjboandroidmodule.interfa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @description: <描述当前版本功能>
 * <p>
 * 1.直接get就好 http://ip.taobao.com/service/getIpInfo.php?ip=202.202.33.33
 * </p>
 * @author: yjbo
 * @date: 2016-07-12 16:13
 */
public interface HttpService {
    public final static String baseHttp = "http://lbs.sougu.net.cn/";
    public final static String baseHttp2 = "http://www.jianshu.com/p/7dcfd243b1df";


    //缓存网页http://www.jianshu.com/p/7dcfd243b1df
    @GET("{ip}")
    Call<okhttp3.ResponseBody> getFirstBlog10(@Path("ip") String ip);

}
