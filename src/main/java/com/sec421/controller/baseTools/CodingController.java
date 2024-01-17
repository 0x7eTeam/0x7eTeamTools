package com.sec421.controller.baseTools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import javafx.scene.control.TextArea;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class CodingController {


    private HashMap<Character, String> morseCodeMap = new HashMap<>();
    public void Base64Encode(TextArea textArea, String originalString) {
        try {
            String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes(StandardCharsets.UTF_8));
            textArea.setText(encodedString);
        } catch (Exception e) {
            textArea.setText("编码失败");
        }
    }
    public void Base64Decode(TextArea textArea, String originalString) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(originalString);
            String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
            textArea.setText(decodedString);
        } catch (Exception e) {
            textArea.setText("解码失败");
        }
    }


    public void UnicodeEncode(TextArea textArea,String text){
        // Unicode编码
        StringBuilder unicodeText = new StringBuilder();
        for (char c : text.toCharArray()) {
            unicodeText.append("\\u").append(String.format("%04x", (int) c));
        }
        textArea.setText(unicodeText.toString());
    }
    public void UnicodeDecode(TextArea textArea,String text){
        // Unicode解码
        try {
            StringBuilder decodedText = new StringBuilder();
            String[] unicodeChars = text.toString().split("\\\\u");
            for (int i = 1; i < unicodeChars.length; i++) {
                int codePoint = Integer.parseInt(unicodeChars[i], 16);
                decodedText.append((char) codePoint);
            }
            textArea.setText(decodedText.toString());
        } catch (Exception e){
            textArea.setText("解码失败");
        }
    }

    public void URLEncoder(TextArea textArea,String text){
        try {
            String encodedUrl = URLEncoder.encode(text, "UTF-8");
            textArea.setText(encodedUrl.toString());
        } catch (Exception e) {
            textArea.setText("编码失败");
        }
    }
    public void URLDecoder(TextArea textArea,String text){
        try {
            String decodedUrl = URLDecoder.decode(text, "UTF-8");
            textArea.setText(decodedUrl.toString());
        } catch (UnsupportedEncodingException e) {
            textArea.setText("解码失败");
        }
    }

    public void ReverseString(TextArea textArea,String text){
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = text.length() - 1; i >= 0; i--) {
                char c = text.charAt(i);
                if (Character.isSurrogate(c)) {
                    char reversedChar = Character.reverseBytes(c);
                    sb.append(reversedChar);
                } else {
                    sb.append(c);
                }
            }
            textArea.setText(sb.toString());
        } catch (Exception e){
            textArea.setText("反转失败了");
        }
    }
    private static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b).append(",");
        }
        return sb.toString();
    }
    public void ASCIIEncoding(TextArea textArea,String text){
        byte[] bytes = text.getBytes();
        textArea.setText(bytesToString(bytes));
    }
    public void ASCIIDecoding(TextArea textArea,String text){
        try {
            text.replace(" ","");
            // 尝试根据逗号分割字符串
            String[] asciiStrings = text.split(",");
            // 如果分割后数组长度为1，尝试根据空格分割字符串
            if (asciiStrings.length == 1) {
                asciiStrings = text.split(" ");
            }
            byte[] bytes = new byte[asciiStrings.length];
            for (int i = 0; i < asciiStrings.length; i++) {
                // 解析为ASCII码值
                int asciiCode = Integer.parseInt(asciiStrings[i].trim());
                // 转换为字节类型
                bytes[i] = (byte) asciiCode;
            }
            // ASCII 解码
            String decodedStr = new String(bytes);
            textArea.setText(decodedStr.toString());
        } catch (Exception e){
            textArea.setText("ASCII转码失败");
        }
    }    // ROT13编码
    public void  rot13Encode(TextArea textArea,String text) {
        try{
            StringBuilder result = new StringBuilder();
            for(int i=0; i<text.length(); i++) {
                char c = text.charAt(i);

                if(Character.isLetter(c)) {
                    char base = Character.isLowerCase(c) ? 'a' : 'A';
                    c = (char) (((c - base + 13) % 26) + base);
                }
                result.append(c);
            }
            textArea.setText(result.toString());
        } catch (Exception e) {
            textArea.setText("rot13转换失败");
        }

    }
    // 莫斯密码编码
    public void morseEncode(TextArea textArea,String originalString) {
        StringBuilder encodedBuilder = new StringBuilder();
        originalString = originalString.toUpperCase();

        for (char c : originalString.toCharArray()) {
            String code = this.getMorseCodeMap().get(c);
            if (code != null) {
                encodedBuilder.append(code).append(" ");
            }
        }
        textArea.setText(encodedBuilder.toString());
    }

    // 莫斯密码解码
    public  void morseDecode(TextArea textArea,String encodedString) {
        StringBuilder decodedBuilder = new StringBuilder();

        String[] codes = encodedString.split(" ");
        for (String code : codes) {
            for (char key : this.getMorseCodeMap().keySet()) {
                if (code.equals(this.getMorseCodeMap().get(key))) {
                    decodedBuilder.append(key);
                    break;
                }
            }
        }
        textArea.setText(decodedBuilder.toString());
    }

    public HashMap<Character, String> getMorseCodeMap() {
        this.morseCodeMap.put('A', ".-");
        this.morseCodeMap.put('B', "-...");
        this.morseCodeMap.put('C', "-.-.");
        this.morseCodeMap.put('D', "-..");
        this.morseCodeMap.put('E', ".");
        this.morseCodeMap.put('F', "..-.");
        this.morseCodeMap.put('G', "--.");
        this.morseCodeMap.put('H', "....");
        this.morseCodeMap.put('I', "..");
        this.morseCodeMap.put('J', ".---");
        this.morseCodeMap.put('K', "-.-");
        this.morseCodeMap.put('L', ".-..");
        this.morseCodeMap.put('M', "--");
        this.morseCodeMap.put('N', "-.");
        this.morseCodeMap.put('O', "---");
        this.morseCodeMap.put('P', ".--.");
        this.morseCodeMap.put('Q', "--.-");
        this.morseCodeMap.put('R', ".-.");
        this.morseCodeMap.put('S', "...");
        this.morseCodeMap.put('T', "-");
        this.morseCodeMap.put('U', "..-");
        this.morseCodeMap.put('V', "...-");
        this.morseCodeMap.put('W', ".--");
        this.morseCodeMap.put('X', "-..-");
        this.morseCodeMap.put('Y', "-.--");
        this.morseCodeMap.put('Z', "--..");
        this.morseCodeMap.put('0', "-----");
        this.morseCodeMap.put('1', ".----");
        this.morseCodeMap.put('2', "..---");
        this.morseCodeMap.put('3', "...--");
        this.morseCodeMap.put('4', "....-");
        this.morseCodeMap.put('5', ".....");
        this.morseCodeMap.put('6', "-....");
        this.morseCodeMap.put('7', "--...");
        this.morseCodeMap.put('8', "---..");
        this.morseCodeMap.put('9', "----.");
        this.morseCodeMap.put('.', ".-.-.-");
        this.morseCodeMap.put(',', "--..--");
        this.morseCodeMap.put('?', "..--..");
        this.morseCodeMap.put('!', "-.-.--");
        this.morseCodeMap.put(' ', "/");
        return morseCodeMap;
    }

    public void setMorseCodeMap(HashMap<Character, String> morseCodeMap) {
        this.morseCodeMap = morseCodeMap;
    }
}
