package com.ipooleth.common.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @author CN087482
 */
public class StringUtil {
    public static final String SIGN_EMPTY = "";
    public static final String SIGN_EQUAL = "=";
    public static final String SIGN_SPACE = " ";
    public static final String SIGN_UNDERSCORE = "_";
    public static final String SIGN_HYPHEN = "-";
    public static final String SIGN_DOT = ".";
    public static final String SIGN_COMMA = ",";
    public static final String SIGN_COLON = ":";
    public static final String SIGN_SEMICOLON = ";";
    public static final String SIGN_QUESTION_MARK = "?";
    public static final String SIGN_OR = "|";
    public static final String SIGN_AND = "&";
    public static final String SIGN_STAR = "*";
    public static final String SIGN_SLASH = "/";
    public static final String SIGN_DOUBLE_SLASH = "//";
    public static final String SIGN_POUND = "#";
    public static final String SIGN_BACKSLASH = "\\";
    public static final String SIGN_TILDE = "~";
    /** "yyyy-MMM-dd HH:mm:ss" */
    public static final String DATE_TIME_PATTERN = "yyyy-MMM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final char CTRL_Z = 26;
    public static boolean isEmpty(String arg) {
        return arg == null || arg.trim().length() == 0;
    }

    /**
     * 判断字符串是否为null或空字符串
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * trim a string
     *
     * @param s
     * @return
     */
    public static String trim(String s) {
        return s == null ? "" : s.trim();
    }

    /**
     * 将YYYY-MM-DD HH:mm:ss 转换为YYYYMMDDhhmmss
     * @param dateTimeStr
     * @return String
     */
    public static String formatdateTimeString(String dateTimeStr){
        if(dateTimeStr == null || dateTimeStr.trim().length() == 0){
            return "";
        }
        return dateTimeStr.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
    }


    /**
     * 转换退款订单号为支付订单号
     * @param refOrderNumber
     * @return
     */
    public static String convertRefundNo(String refOrderNumber){
        if(!isBlank(refOrderNumber)){
            String refundPfx = refOrderNumber.substring(0, 8);
            if(isNumeric(refundPfx)){
                return refOrderNumber.substring(9,refOrderNumber.length());
            }
        }
        return refOrderNumber;
    }

    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 将字节流转换成字符串，charSet不能为空，必须指定
     * @param is
     * @param charSet 编码
     * */
    public static String stream2String(final InputStream is, String charSet) throws IOException {
        if (null == is || StringUtil.isBlank(charSet)) {
            return null;
        }
        BufferedReader reader = null;
        StringBuffer sb = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, charSet));
            sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (null != reader) {
                reader.close();
            }
        }
        if (null == sb) {
            return null;
        }
        return sb.toString();
    }
    public static boolean checkIllegalWords(String words, String illegal){
        Pattern p=Pattern.compile(illegal);
        Matcher m=p.matcher(words);
        return m.find();
    }

    public static String dateToString(Date dateTime, Locale locale) {
        if (dateTime == null) return SIGN_EMPTY;
        if (locale == null)
            locale = Locale.ENGLISH;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN, locale);
        return sdf.format(dateTime);
    }

    /**
     * 将double型数字转换成保留2位小数点字符串
     *
     * @param d
     * @return String
     */
    public static String doubleFormat(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }
    /**
     * 判断字符是否在指定的字符中
     * @param target
     * @param regex
     * @param splite
     * @return
     */
    public static boolean contain(String target, String regex, String splite){
        String[] regexs = regex.split(splite);
        if(regexs == null){
            return false;
        }
        for(String tmp: regexs){
            if(tmp.equals(target)){
                return true;
            }
        }
        return false;

    }

    public static byte[] readByteStream(BufferedInputStream in,String encoding) throws IOException {
        LinkedList<Mybuf> bufList = new LinkedList<Mybuf>();
        int size = 0;
        byte buf[];
        do {
            buf = new byte[128];
            int num = in.read(buf);
            if (num == -1) {
                break;
            }
            size += num;
            bufList.add(new Mybuf(buf, num));
        } while (true);
        buf = new byte[size];
        int pos = 0;
        for (ListIterator<Mybuf> p = bufList.listIterator(); p.hasNext();) {
            Mybuf b = (Mybuf) p.next();
            for (int i = 0; i < b.size;) {
                buf[pos] = b.buf[i];
                i++;
                pos++;
            }
        }
        String temp = new String(buf,encoding);
        temp = java.net.URLDecoder.decode(temp,encoding);
        buf = temp.getBytes(encoding);
        return buf;
    }

    public static String readByteString(BufferedInputStream in,String encoding) throws IOException {
        byte[] buf = readByteStream(in, encoding);
        return new String(buf,encoding);
    }

    /**
     * 有效的ada
     *
     * @param
     * @return
     */
    public static boolean isValidAda(Long ada) {
        return ada != null && ada.intValue() > 0;
    }

    /***
     * 判断字符串长度，其中一个中文算2位
     *
     * @param source
     * @return int
     * @throws UnsupportedEncodingException
     */
    public static int length(String source) throws UnsupportedEncodingException {
        if (source == null || source.length() == 0) {
            return 0;
        }
        source = new String(source.getBytes(), "ISO8859_1");
        return source.length();
    }
}


class Mybuf {

    public byte buf[];
    public int size;

    public Mybuf(byte b[], int s) {
        buf = b;
        size = s;
    }

}
