package com.sec421.controller.informationCollection;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec421.tools.HttpTool;

import java.io.IOException;

public class Quake360Controller {
    public static String quake360Search(String searchText, Integer start)  {
        if (start>1){
            start = (start-1)*100;
        }
        String domain = "";
        String url = "https://quake.360.net/api/v3/search/quake_service";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", searchText);
        jsonObject.put("start", start);
        jsonObject.put("size", 100);
        try {
            String s = HttpTool.quankeRequest(url, jsonObject);

            JSONObject jsonObject2 = JSONObject.parseObject(s);
            JSONArray dataArray = jsonObject2.getJSONArray("data");
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject item = dataArray.getJSONObject(i);
                String service_name = item.getJSONObject("service").getString("name");
                String ip = item.getString("ip");
                String port = item.getString("port");
                if (service_name.equals("http/ssl")) {
                    String host = item.getJSONObject("service").getJSONObject("http").getString("host");
                    host = !(host.equals("") || host == "") ? host : ip;
                    domain += "https://" + host + ":" + port + "\r\n";
                } else if (service_name.equals("http")) {
                    String host = item.getJSONObject("service").getJSONObject("http").getString("host");
                    host = !(host.equals("") || host == "") ? host : ip;
                    domain += "http://" + host + ":" + port + "\r\n";
                } else {
                    domain += service_name + "://" + ip + ":" + port + "\r\n";
                }
            }
        }catch (Exception e){
            domain = "查询失败,请仔细检查。。。";
        }
        return domain;
    }
}