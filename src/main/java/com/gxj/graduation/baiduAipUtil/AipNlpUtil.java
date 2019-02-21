package com.gxj.graduation.baiduAipUtil;

import com.baidu.aip.nlp.AipNlp;
import com.mysql.cj.x.protobuf.MysqlxExpect;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AipNlpUtil {
    //设置APPID/AK/SK
    private static final String APP_ID = "15574822";
    private static final String API_KEY = "4Aa8gZOnxcGQh3nd9CEebzmb";
    private static final String SECRET_KEY = "EEv2IFwd7NVwYs3TQkriCwpR9LCSshQk";
    private static AipNlpUtil instance = new AipNlpUtil();
    private AipNlp nplClient;

    private AipNlpUtil(){
        nplClient = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipNlpUtil getInstance() {
        return instance;
    }


    /* Nlp相关方法 */
    /*
    * desc：短文本相似度
     */
    public JSONObject simnet(String text1,String text2)  {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "CNN");

        // 短文本相似度
         JSONObject res = nplClient.simnet(text1, text2, options);
         if(res.toString().contains("Open api qps request limit reached")) {
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             res = nplClient.simnet(text1, text2, options);
         }
        return res;

    }


    public static void main(String args[]) throws InterruptedException {
        JSONObject res1 = AipNlpUtil.getInstance().simnet("查询明天天气","根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天天气","获得天气预报数据");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天天气","查询本天气预报Web Services支持的国内外城市或地区信息");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天天气","2400多个城市天气预报Web服务");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天天气","400个国内外主要城市天气预报Web服务");
        System.out.println(res1.toString());
        TimeUnit.SECONDS.sleep(1);//秒

        res1 = AipNlpUtil.getInstance().simnet("查询明天天气","天气预报 Web 服务");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天天气","获得航班时刻表");
        System.out.println(res1.toString());
        TimeUnit.SECONDS.sleep(1);//秒

        System.out.println(" ");
        res1 = AipNlpUtil.getInstance().simnet("查询明天是否下雨","天气预报 Web 服务");
        System.out.println(res1.toString());
        res1 =  AipNlpUtil.getInstance().simnet("查询明天是否下雨","获得航班时刻表");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天是否下雨","查询本天气预报Web Services支持的国内外城市或地区信息");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天是否下雨","2400多个城市天气预报Web服务");
        System.out.println(res1.toString());
        res1 = AipNlpUtil.getInstance().simnet("查询明天是否下雨","400个国内外主要城市天气预报Web服务");
        System.out.println(res1.toString());res1.getDouble("score");

        TimeUnit.SECONDS.sleep(1);//秒


    }

}
