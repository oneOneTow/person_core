package com.ccbcfx.learn.util;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/3/1 17:16
 */
public class StringUtil {
    private static final char UNDERLINE = '_';

    public static String camelToUnderline(String string) {
        if (string == null || "".equals(string.trim())) {
            return "";
        }
        int len = string.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String string) {
        if (string == null || "".equals(string.trim())) {
            return "";
        }
        int len = string.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(string.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }



}
