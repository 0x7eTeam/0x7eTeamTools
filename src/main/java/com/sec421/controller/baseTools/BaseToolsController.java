package com.sec421.controller.baseTools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import java.util.HashMap;

public class BaseToolsController {

    public String UploadFileTools(HashMap hashMap,String str){
        str = str.replace("127.0.0.1",hashMap.get("ip").toString());
        str = str.replace("8000",hashMap.get("port").toString());
        str = str.replace("exp.exe",hashMap.get("srcfile").toString());
        str = str.replace("windows.exe",hashMap.get("dstfile").toString());
        return str;
    }
    public String BruteforceTools(String str){
        String textModule = "\n" +
                "端口扫描:\n" +
                "sudo nmap -sT --min-rate 10000 -p- targetIP\n" +
                "sudo nmap -p- -Pn --min-rate 10000 targetIP\n" +
                "sudo nmap -sT -sV -sC -O -p80,139 targetIP\n" +
                "TARGET=targetIP && nmap -p$(nmap -p- --min-rate=1000 -T4 $TARGET -Pn | grep ^[0-9] | cut -d '/' -f 1 | tr '\\n' ',' | sed s/,$//) -sC -sV -Pn -vvv $TARGET -oN nmap_tcp_all.nmap\n" +
                "\n"+
                "目录爆破:\n" +
                "dirsearch -u http://targetIP -f -e zip,jar,tar.gz --wordlists=/usr/share/dirb/wordlists/common.txt\n" +
                "dirsearch -u http://targetIP -x 403\n" +
                "dirsearch -u http://targetIP -x 403,401 -w /usr/share/wordlists/dirb/big.txt\n" +
                "\n"+
                "gobuster dir -u http://targetIP -w /usr/share/wordlists/dirbuster/directory-list-2.3-medium.txt -t 50\n" +
                "gobuster dir -u http://targetIP --wordlist=/usr/share/wordlists/dirbuster/directory-list-2.3-medium.txt -t 50\n" +
                "gobuster dir -k -u https://targetIP --wordlist=/usr/share/wordlists/dirbuster/directory-list-2.3-medium.txt -t 50 \n" +
                "\n"+
                "feroxbuster -u http://targetIP -w /usr/share/seclists/Discovery/Web-Content/directory-list-2.3-big.txt\n" +
                "feroxbuster -u http://targetIP -x php -w /usr/share/seclists/Discovery/Web-Content/raft-medium-directories-lowercase.txt -k\n" +
                "\n"+
                "wfuzz -u http://targetIP/?FUZZ.php -w /usr/share/seclists/Discovery/Web-Content/burp-paraameter-names.txt -H \"cookie: PHPSESSID=asdh231asdad\" --hh 1678\n" +
                "wfuzz -u http://targetIP -w /usr/share/wordlists/rockyou.txt -d \"key=FUZZ\" --hw 25\n" +
                "\n"+
                "ffuf -w /usr/share/seclists/Discovery/Web-Content/raft-small-words-lowercase.txt -u http://targetIP/FUZZ -t 50 -mc 200\n" +
                "\n"+
                "domain brute:\n"+
                "wfuzz -u http://targetIP -H \"HOST: FUZZ.targetIP\" -w /usr/share/seclists/Discovery/DNS/subdomains-top1million-5000.txt\n" +
                "wfuzz -u http://targetIP -H \"HOST: FUZZ.targetIP\" -w /usr/share/seclists/Discovery/DNS/subdomains-top1million-5000.txt --hh 315\n" +
                "wfuzz -H \"Host: FUZZ.targetIP\" --hc 302,400 -t 50 -H \"User-Agent: luzesec\" -c -z file,\"/usr/share/seclists/Discovery/Web-Content/raft-small-words-lowercase.txt\" http://targetIP\n" +
                "wfuzz -c -w /usr/share/seclists/Discovery/DNS/subdomains-top1million-5000.txt -u http://targetIP -H \"Host: FUZZ.targetIP\" --hl 7\n" +
                "gobuster vhost -u http://targetIP -w /usr/share/seclists/Discovery/DNS/bitquark-subdomains-top100000.txt -t 20 -o gobuster_vhost.txt\n" +
                "\n"+
                "ssrf探测:\n"+
                "ffuf -w /opt/SecLists/Fuzzing/4-digits-0000-9999.txt -u 'http://targetIP/?url=\"http://127.0.0.1:FUZZ\"' -fs 0\n"+
                "";
        return textModule.replace("targetIP",str);

    }
}
