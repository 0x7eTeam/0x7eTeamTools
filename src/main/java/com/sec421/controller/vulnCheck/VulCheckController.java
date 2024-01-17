package com.sec421.controller.vulnCheck;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.sec421.controller.ui.MainController;
import com.sec421.core.Constants;
import com.sec421.tools.Tools;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class VulCheckController {
    public static Logger logger = Logger.getLogger(MainController.class);
    public static String vulCheck(String target, String pyScriptName) throws IOException, InterruptedException {

//        Proxy proxy = (Proxy) MainController.settingInfo.get("proxy");

        //python脚本改用绝对路径展示
//        String filename = Constants.POCPATH +  pyScriptName;
//        Path filePath = Paths.get(filename);
//        String pyScript =  filePath.toAbsolutePath().toString();
        String pyScript =  pyScriptName;
        String pyPath = Paths.get(Tools.getProperty("py_path")).toAbsolutePath().toString();

        ProcessBuilder pb = new ProcessBuilder(pyPath, pyScript,target );
        //py脚本的代理，请在py脚本里写好
//        if (proxy != null) {
//            Map<String, String> environment = pb.environment();
//            InetSocketAddress proxyAddress = (InetSocketAddress) proxy.address();
//            String proxyHost = proxyAddress.getHostString();
//            int proxyPort = proxyAddress.getPort();
//            if (proxy.type() == Proxy.Type.HTTP) {
//                environment.put("http.proxyHost", proxyHost);
//                environment.put("http.proxyPort", Integer.toString(proxyPort));
//            }else if (proxy.type() == Proxy.Type.SOCKS){
//                environment.put("socksProxyHost", proxyHost);
//                environment.put("socksProxyPort", Integer.toString(proxyPort));
//            }
//        }
        // 启动Python进程
        Process process = pb.start();

        // 获取标准输出
        InputStream stdout = process.getInputStream();
        BufferedReader outputReader = new BufferedReader(new InputStreamReader(stdout, "GBK"));

        // 获取标准错误输出
        InputStream stderr = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(stderr, "GBK"));

        // 读取标准输出内容
        String line;
        StringBuilder outputBuilder = new StringBuilder();
        while ((line = outputReader.readLine()) != null) {
            outputBuilder.append(line).append("\n");
        }

        // 读取标准错误输出内容
        StringBuilder errorBuilder = new StringBuilder();
        while ((line = errorReader.readLine()) != null) {
            errorBuilder.append(line).append("\n");
        }

        // 等待进程执行完毕，并获取返回值
        int exitCode = process.waitFor();
        // 打印执行结果
        logger.debug("["+target+"]  ----   "+pyScriptName);
        logger.debug("Exit Code: " + outputBuilder.toString());
        logger.debug("Output: " + errorBuilder.toString());
        logger.debug("Error: " + exitCode);
        logger.debug("---------------------------------------\r\n");

        return outputBuilder.toString().trim() + errorBuilder.toString().trim();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        vulCheck("aaa","1.py");
    }
}
