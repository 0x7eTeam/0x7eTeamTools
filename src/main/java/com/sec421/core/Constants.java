package com.sec421.core;

/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */

public class Constants {

    public static String NAME = "0x7eSecurity";

    public static String VERSION = "v1.1 ";

    public static String AUTHOR = "luze";

    public static String SECURITYSTATEMENT = "----------------------------------------------------------------\r\n\t\t\t" +
            "本工具仅提供给安全测试人员进行安全自查使用\r\n\t\t\t" +
            "用户滥用造成的一切后果与作者无关\r\n\t\t\t" +
            "使用者请务必遵守当地法律\r\n\t\t\t" +
            "本程序不得用于商业用途，仅限学习交流\r\n" +
            "----------------------------------------------------------------\r\n" +
            "\t\t目前程序被没有任何漏洞利用代码，感谢各位师傅\r\n\t";

    public static String UPDATELOG = "----------------------------------------------------------------\r\n\t\t\t" +
            "更新日志记录\r\n\t\t\t" +
            "----------------------------------------------------------------\r\n" +
            "0x7eTeamTools V1.1   -----------2024.1.12\r\n\t\t\t" +
            "1、修复了反弹shell命令生成功能bug\r\n\t\t\t" +
            "2、修复了某些页面鼠标滚轮功能bug\r\n\t\t\t" +
            "3、新增了js接口的一些处理逻辑\r\n\t\t\t" +
            "\t3.1针对型如../xx.js、./xx.js、//xx.js等js爬取做了修复\r\n\t\t\t" +
            "\t3.2针对型如../func、./func、/func等接口处理做了修复\r\n\t\t\t" +
            "----------------------------------------------------------------\r\n" +
            "0x7eTeamTools V1.0   -----------2024.1.1\r\n\t\t\t" +
            "----------------------------------------------------------------\r\n";

    public static String CONFPATH = "conf/config.ini";

    public static String POCPATH = "conf/poc/";
    public static String  IMPACKET_PATH = "conf/impacket-0.11.0/examples/";

}
