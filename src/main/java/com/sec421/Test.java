package com.sec421;


import cn.hutool.http.HttpRequest;
import com.sec421.controller.js.JsController;
import com.sec421.tools.Tools;

import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        String html = "        <div class=\"result-molecule  new-pmd\"\n" +
                "            tpl=\"app/rs\"\n" +
                "            m-name=\"molecules/app/rs/result_6b2d4b0\"\n" +
                "            m-path=\"../../pss.bdstatic.com/r/www/cache/static/molecules/app/rs/result_6b2d4b0\"\n" +
                "            m-path=\"/pss.bdstatic.com/r/www/cache/static/molecules/app/rs/result_6b2d4b0\"\n" +
                "            m-path=\"./pss.bdstatic.com/r/www/cache/static/molecules/app/rs/result_6b2d4b0\"\n" +
                "            data-cost={\"renderCost\":\"0.2\",\"dataCost\":0}\n" +
                "        >";
        System.out.println(html);
        Set<String> strings = Tools.extractJsInterface(html);
        System.out.println(strings);
    }
}