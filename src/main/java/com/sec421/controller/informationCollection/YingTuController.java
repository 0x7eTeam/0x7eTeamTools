package com.sec421.controller.informationCollection;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec421.tools.HttpTool;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class YingTuController {
    Date date = new Date();
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd%20hh:mm:ss");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String now = simpleDateFormat.format(date);
    private String month = simpleDateFormat.format(new Date(date.getTime() - 31L * 24 * 60 * 60 * 1000));
    private String half_year = simpleDateFormat.format(new Date(date.getTime() - 186L * 24 * 60 * 60 * 1000));
    private  String one_year = simpleDateFormat.format(new Date(date.getTime() - 366L * 24 * 60 * 60 * 1000));
    public String yintuSearch(String searchText, String apiKey, String page, String is_web) throws Exception {
        String res = "";
        try {
            String qbase64 = Base64.getUrlEncoder().encodeToString(searchText.getBytes(StandardCharsets.UTF_8));
            if (is_web == "否") {
                is_web = "2";
            } else {
                is_web = "1";
            }
            String url = "https://hunter.qianxin.com/openApi/search?" +
                    "api-key=" + apiKey +
                    "&search=" + qbase64 +
                    "&page=" + page +
                    "&page_size=100" +
                    "&is_web=" + is_web +
                    "&port_filter=false&start_time=" + one_year +
                    "&end_time=" + now;
            String result = HttpTool.getHttpReuest(url);
            JSONObject object = (JSONObject) JSONObject.parse(result);
            JSONArray jsonArray = object.getJSONObject("data").getJSONArray("arr");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String httpurl = item.getString("url");
                res += httpurl + "\r\n";
            }
        } catch (Exception e){
            res = "查询异常";
        }
        return res;
    }
}
