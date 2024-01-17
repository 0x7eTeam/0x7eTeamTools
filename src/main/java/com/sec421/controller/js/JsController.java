package com.sec421.controller.js;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import cn.hutool.http.HttpResponse;
import com.sec421.tools.HttpTool;
import com.sec421.tools.Tools;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class JsController {

    private TextArea textArea;
    public String getJSURLResult(String url) throws Exception {
        String result = "当前页js链接如下:\r\n\n";
        String domain="";
        String js_url = "";
        String jsfileReq = HttpTool.getHttpReuest(url);
        List<String> jsUrls = Tools.extractJsUrls(jsfileReq);
        for (String jsUrl : jsUrls) {
            if (jsUrl.startsWith("/")){
                domain = Tools.extractDomain(url);
            }else{
                domain = Tools.splitByHash(url);
            }
            if (jsUrl.startsWith("http") || js_url.startsWith("//")){
                js_url = jsUrl;
            }else {
                if (!domain.endsWith("/")){
                    URL myurl = new URL(domain);
                    String mypath = myurl.getPath();
                    String fileName = mypath.substring(mypath.lastIndexOf('/') + 1);
                    domain = domain.replace(fileName,"");
                }
                js_url = (domain+jsUrl).replace("/./","/");
            }
            js_url = Tools.normalizedURI(js_url);
            result+=js_url+Tools.getLineSeparator();
        }

        return result;
    }
    public String getJSInterFaceResult(String url) {
        String result ="";
        String jsInterFaceReq = HttpTool.getHttpReuest(url);
        Set<String> jsInterFaces = Tools.extractJsInterface(jsInterFaceReq);
        for (String jsInterFace : jsInterFaces) {
            result+=jsInterFace+Tools.getLineSeparator();
        }
        return result;
    }

    public String getCTXResult(String interfaceStr, String ctx) {
        String result = "";
        List<String> list = Arrays.asList(interfaceStr.split("\n"));
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(ctx).append(s).append(Tools.getLineSeparator());
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

}
