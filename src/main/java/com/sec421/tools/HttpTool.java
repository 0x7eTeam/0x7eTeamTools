package com.sec421.tools;

/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.sec421.controller.ui.MainController;

import java.io.IOException;
import java.net.Proxy;
import java.net.URI;

public class HttpTool {
    public static String getHttpReuest(String requestUrl) {
        String body=null;
        Proxy proxy = (Proxy) MainController.settingInfo.get("proxy");
        try {
            if (proxy != null) {
                body = HttpRequest.get(requestUrl).setProxy(proxy).execute().body();

            } else {
                body = HttpRequest.get(requestUrl).execute().body();
            }
        } catch (Exception e){
            body = "请检查网络或者代理";
        }finally {
            return body;
        }
    }

    public static HttpResponse sendHttpReuest(String requestUrl,String reqMethod) {
        String body=null;
        HttpResponse execute=null;
        Proxy proxy = (Proxy) MainController.settingInfo.get("proxy");
        try {
            if (proxy != null) {
                if (reqMethod.equals("get")) {
                    execute = HttpRequest.get(requestUrl).setProxy(proxy).execute();
                } else if (reqMethod.equals("post")) {
                    execute = HttpRequest.post(requestUrl).setProxy(proxy).execute();
                }
            } else {
                if (reqMethod.equals("get")) {
                    execute = HttpRequest.get(requestUrl).execute();
                } else if (reqMethod.equals("post")) {
                    execute = HttpRequest.post(requestUrl).execute();
                }
            }
        } catch (Exception e){
            Tools.alert("错误","请检测网络情况");
        }finally {
            return execute;
        }
    }

    public static String quankeRequest(String requestUrl, JSONObject jsonObject) throws IOException {
        String body=null;
        String quankeAPI = Tools.getProperty("quake");
        Proxy proxy = (Proxy) MainController.settingInfo.get("proxy");
        try {
            if (proxy != null) {
                body = HttpRequest.post(requestUrl).
                        header("Content-Type","application/json").
                        header("X-QuakeToken",quankeAPI,false).
                        body(jsonObject.toJSONString()).
                        setProxy(proxy).execute().body();
            } else {
                body = HttpRequest.post(requestUrl).
                        header("Content-Type","application/json").
                        header("X-QuakeToken",quankeAPI,false).
                        body(jsonObject.toJSONString()).
                        execute().body();
            }
        } catch (Exception e){
            body = "请检查网络或者代理";
        }finally {
            return body;
        }
    }
    public static String generateURL(String host,String port,String protocol){
        String domain = extractDomain(host);
        if ("80".equals(port) || "443".equals(port)){
            return protocol+"://"+domain;
        }else {
            return protocol+"://"+domain+":"+port;
        }
    }


    public static String extractDomain(String url) {
        String domain = "";
        try {
            URI uri = new URI(url);
            domain = uri.getHost();
            if (domain == null) {
                String[] parts = url.split(":");
                if (parts.length > 0) {
                    domain = parts[0];
                }else{
                    domain = url;
                }
            }
            return domain;
        } catch (Exception e) {
            String[] parts = url.split(":");
            if (parts.length > 0) {
                domain = parts[0];
            }else{
                domain = url;
            }
            return domain;
        }
    }

}