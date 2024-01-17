package com.sec421.controller.informationCollection;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.alibaba.fastjson.JSONObject;
import com.sec421.tools.HttpTool;
import com.sec421.tools.Tools;
import javafx.scene.control.Alert;

import java.util.Base64;
import java.util.List;

import static com.sec421.tools.HttpTool.generateURL;

public class FofaController {
    public static String fofaSearch(String searchText) throws Exception {
        String result = "";
        String values = Tools.getProperty("fofa");
        String[] EmaliKey = values.split(":");
        if(EmaliKey.length == 2) {
            String email = EmaliKey[0];
            String key = EmaliKey[1];

            String fResult = fofaHTTP(email, key, searchText, 10000);
            if (fResult.startsWith("请检查")){
                return fResult;
            }

            JSONObject object = (JSONObject) JSONObject.parse(fResult);
            List<String> listStr = object.parseArray(object.getJSONArray("results").toJSONString(), String.class);

            for (String s:listStr) {
                s = s.replace("\"","").replace("\\r\\n","").replace("\\t","");
                String host = s.split(",", 3)[0].replace("[","");
                String port = s.split(",", 3)[1];
                String protocol = s.split(",", 3)[2].replace("]","");
                String httpurl = generateURL(host,port,protocol);
                result += httpurl +"\r\n";
            }
            return result;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("fofa 配置错误\n");
            alert.showAndWait();
            return "fofa 配置错误";
        }

    }


    public static String fofaHTTP(String emali, String key, String value, int size) {

        String qbase64 = Base64.getEncoder().encodeToString(value.getBytes());

        String url = "https://fofa.info/api/v1/search/all?email=" + emali + "&key=" + key + "&qbase64=" + qbase64 + "&full=true&fields=host,port,protocol&size=" + size;
        String result = "";
        result = HttpTool.getHttpReuest(url);
        return result;
    }

}
