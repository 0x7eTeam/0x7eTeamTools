package com.sec421.tools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */

import com.sec421.controller.ui.MainController;
import com.sec421.core.Constants;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tools {
    private String section;
    private String key;
    private String value;
    public static String getProperty(String name) throws IOException {
        File file = new File(Constants.CONFPATH);
        if (file.exists()) {
            Ini ini = new Ini(file);
            System.out.println();
            String fofaEmail =ini.get("fofa", "email");
            String fofaApiKey =ini.get("fofa", "apikey");
            String hunterApiKey =ini.get("hunter", "apikey");
            String quakeApiKey =ini.get("quake", "apikey");
            String pythonPATH =ini.get("python", "path");

            if (name.equals("fofa")){
                return fofaEmail+":"+fofaApiKey;
            } else if (name.equals("hunter")) {
                return hunterApiKey;
            } else if (name.equals("quake")) {
                return quakeApiKey;
            }else if (name.equals("py_path")){
                return pythonPATH;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("config.ini文件没找到！！！！！\n");
            alert.showAndWait();
            return "config.ini文件没找到";
        }

        return null;
    }
    public static void setProxy() {
        Proxy proxy = (Proxy) MainController.settingInfo.get("proxy");
        if (proxy != null) {
            InetSocketAddress proxyAddress = (InetSocketAddress) proxy.address();
            String proxyHost = proxyAddress.getHostString();
            int proxyPort = proxyAddress.getPort();
            if (proxy.type() == Proxy.Type.HTTP) {
                System.setProperty("http.proxyHost", proxyHost);
                System.setProperty("http.proxyPort", Integer.toString(proxyPort));
            }else if (proxy.type() == Proxy.Type.SOCKS){
                System.setProperty("socksProxyHost", proxyHost);
                System.setProperty("socksProxyPort", String.valueOf(proxyPort));
            }
        }
    }
    public static String writeResult(String path, String content, boolean flag) {
        File f = new File(path);
        String results = null;
        try {
            if (!f.exists())
                f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, flag), "GBK"));
            writer.write(content + "\r\n");
            writer.flush();
            writer.close();
            results = "保存成功, \n" + path;
        } catch (IOException e) {
            results = "保存失败: " + e.getMessage();
        }
        return results;
    }
    public static String getFDate() {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mmss");
        result = sdf.format(new Date());
        return result;
    }
    public static void alert(String title, String info) {
        Alert alert = new Alert(Alert.AlertType.NONE, info, new ButtonType[] { new ButtonType("确定", ButtonBar.ButtonData.YES) });
                alert.setTitle(title);
        alert.showAndWait();
        alert.close();
  }
    public static void alertWARNING(String header_text, String content_text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        // 点 x 退出
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest((e) -> {
            window.hide();
        });

        alert.setHeaderText(header_text);
        alert.setContentText(content_text);
        alert.show();
    }
    public static void alertINFO( String content_text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText(content_text);
        alert.showAndWait();
    }

    // 因为使用echo 写 shell ，这里需要对 < > 转义
    public static String get_escape_shell(String str, String platform) {
        String key1 = "<";
        String key2 = ">";

        if (platform.equals("Linux")) {

            return "'" + str + "'";
        } else {
            return escape(key2, escape(key1, str, "^"), "^");
        }

    }

    public static List<String> findPyFiles(String directoryPath) {
        List<String> pyFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        // 检查目录是否存在
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 获取目录下的所有文件和文件夹
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归调用，搜索子目录
                    pyFiles.addAll(findPyFiles(file.getAbsolutePath()));
                } else {
                    // 判断文件名是否以.py结尾
                    if (file.getName().toLowerCase().endsWith(".py")) {
//                        pyFiles.add(file.getName());
                        pyFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }

        return pyFiles;
    }
    public static String escape(String key, String str, String escape_str) {
        StringBuffer stringBuilder1 = new StringBuffer(str);
        int a = str.indexOf(key);
        int i = 0;
        while (a != -1) {
            stringBuilder1.insert(a + i, escape_str);
            a = str.indexOf(key, a + 1);
            i++;
        }

        return stringBuilder1.toString();
    }

    public static String checkTheDomain(String weburl) {
        if ("".equals(weburl.trim())) {
            return "";
        } else {
            if (!weburl.startsWith("http")) {
                weburl = "http://" + weburl;
            }

            if (!weburl.endsWith("/")) {
                weburl = weburl + "/";
            }

            return weburl;
        }
    }

    public static String urlParse(String url) {
        if (!url.contains("http")) {
            url = "http://" + url;
        }

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }


    public static boolean checkTheURL(String weburl) {
        if ("".equals(weburl.trim())) {
            return false;
        } else {
            return weburl.startsWith("http");
        }
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String getDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    public static String reverse(String data) {
        StringBuilder sb = new StringBuilder(data);
        return sb.reverse().toString();
    }

    public static HashSet<String> read(String path, String encode, Boolean domain) {
        HashSet list = new HashSet();

        try {
            FileInputStream fs = new FileInputStream(new File(path));
            InputStreamReader isr = null;
            if (encode.equals("")) {
                isr = new InputStreamReader(fs);
            } else {
                isr = new InputStreamReader(fs, encode);
            }

            BufferedReader br = new BufferedReader(isr);
            String tem = null;

            while ((tem = br.readLine()) != null) {
                if (domain) {
                    tem = checkTheDomain(tem);
                }
                if (!list.contains(tem)) {
                    list.add(tem);
                }
            }

            br.close();
            isr.close();
        } catch (Exception var7) {
        }

        return list;
    }
    public static List<String> readFile(String path) {
        List<String> lt = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            try {
                Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bf = new BufferedReader(reader);
                while (true) {
                    String line = bf.readLine();
                    if (line == null) {
                        break;
                    } else if (line.trim().length() > 0) {
                        lt.add(line.trim());
                    }
                }
                bf.close();
                reader.close();
            } catch (IOException var6) {
                var6.printStackTrace();
            }
        }
        return lt;
    }

    public static boolean write(String path, String value) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            out.write(value);
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //创建ini文件
    public static boolean creatIniFile(String filePath,List<IniFileEntity> filecontent) throws IOException {
        File file = new File(filePath);
        if(file.exists()){
            return false;
        }
        file.createNewFile();
        Ini ini = new Ini();
        ini.load(file);

        //将文件内容保存到ini对象中
        filecontent.stream().forEach((entity)->{
            ini.add(entity.getSection(),entity.getKey(),entity.getValue()== null ? "": entity.getValue());
        });
        //将文件内容保存到文件中
        ini.store(file);
        return true;
    }

    //修改ini文件
    public static void updateIniFile(File iniFile,Map<String,Map<String,String>> updateData) throws IOException {
        Ini ini = new Ini();
        ini.load(iniFile);
        Profile.Section section = null;
        Map<String,String> dataMap = null;
        for (String sect : updateData.keySet()){
            section = ini.get(sect);
            dataMap = updateData.get(sect);
            for (String key : dataMap.keySet()){
                section.put(key,dataMap.get(key) == null ? "" :
                        dataMap.get(key));
            }
        }
        ini.store(iniFile);
    }

    public static String str2Hex(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; ++i) {
            int bit = (bs[i] & 240) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 15;
            sb.append(chars[bit]);
        }

        return sb.toString().trim();
    }

    public static String hex2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];

        for (int i = 0; i < bytes.length; ++i) {
            int n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 255);
        }

        return new String(bytes);
    }


    // 随机字符
    public static String getRandomString(int length) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }



    // base64编码
    public static String Base64Encode(String txt) {
        try {
            return Base64.getEncoder().encodeToString(txt.getBytes("UTF-8"));
        } catch (Exception var2) {
            return "";
        }
    }
    public static String getLineSeparator() {
        String os = System.getProperty("os.name").toLowerCase();

        String lineSeparator;
        if (os.contains("win")) {
            // Windows换行符
            lineSeparator = "\r\n";
        } else if (os.contains("mac")) {
            // Mac换行符
            lineSeparator = "\r";
        } else {
            // Linux换行符
            lineSeparator = "\n";
        }

        return lineSeparator;
    }
    public static List<String> convertToArrayList(String lines) {
        String os = System.getProperty("os.name").toLowerCase();

        String lineSeparator;
        if (os.contains("win")) {
            // Windows换行符
            lineSeparator = "\r\n";
        } else if (os.contains("mac")) {
            // Mac换行符
            lineSeparator = "\r";
        } else {
            // Linux换行符
            lineSeparator = "\n";
        }

        // 按照操作系统对应的换行符分割字符串，并转换为数组
        String[] lineArray = lines.split(lineSeparator);

        // 将数组转换为ArrayList
        List<String> stringList = new ArrayList<>(Arrays.asList(lineArray));

        return stringList;
    }
    public static List<String> convertToArrayListMotd(String lines) {
        String lineSeparator = "\n";
        // 按照操作系统对应的换行符分割字符串，并转换为数组
        String[] lineArray = lines.split(lineSeparator);
        // 将数组转换为ArrayList
        List<String> stringList = new ArrayList<>(Arrays.asList(lineArray));

        return stringList;
    }

    public static String normalizedURI(String url) throws URISyntaxException {
            URI uri = new URI(url);
            URI normalizedURI = uri.normalize();
            return normalizedURI.toString();
    }
    public static List<String> extractJsUrls(String html) {
        List<String> jsUrls = new ArrayList<>();
        String patternString  = "<script[^>]*src=(?:\\\"|')?([^\\\"'>]+)(?:\\\"|')?";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String jsUrl = matcher.group(1);
            jsUrls.add(jsUrl);
        }

        return jsUrls;
    }

    public static String urlPretreatment(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String path = uri.getPath().replace("//", "/");
        String newUrl = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), path, uri.getQuery(), uri.getFragment()).toString();
        return newUrl;
    }
    public static Set<String> extractJsInterface(String html) {
        Set<String> interfaces = new HashSet<>();
        // bug修复 /xxx  ./xxx  ../../../xxx
        String patternString  = "[\\\"']([..]*/[^\\\"'>]+)[\\\"']";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String interfaceUrl = matcher.group(1);
            interfaces.add(interfaceUrl);
        }

        return interfaces;
    }
    public static String extractDomain(String url) {
        String patternString = "(https?://)([^/#?]+).*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(url);

        if (matcher.matches()) {
            return matcher.group(1) + matcher.group(2) + "/";
        }
        return null;
    }
    public static String splitByHash(String url) {
        String[] parts = url.split("#", 2);
        return parts[0];
    }
}
