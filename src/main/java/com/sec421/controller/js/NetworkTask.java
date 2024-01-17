package com.sec421.controller.js;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import cn.hutool.http.HttpResponse;
import com.sec421.tools.HttpTool;
import javafx.concurrent.Task;

public class NetworkTask extends Task<String> {
    private String url;
    private String reqMethod;

    public NetworkTask(String url, String reqMethod) {
        this.url = url;
        this.reqMethod = reqMethod;
    }

    @Override
    protected String call() {
        HttpResponse httpResponse = HttpTool.sendHttpReuest(url, reqMethod);
        int status = httpResponse.getStatus();
        String content_length = httpResponse.contentLength() == -1 ? "无效" : String.valueOf(httpResponse.contentLength());
        String body = httpResponse.body();
        if (body.length() > 500) {
            body = "长度过长，请自行处理";
        }
        if (status!=404) {
            String result = "------------------------------------\n" +
                    "[出货]   ---   " + url + "\n" +
                    "状态码: " + String.valueOf(status) + "\n" +
                    "Content-Length: " + content_length + "\n" +
                    "响应内容: " + body + "\n" +
                    "------------------------------------\n";

            return result;
        }else {
            return "404    ---" + url + "\n";
        }
    }
}