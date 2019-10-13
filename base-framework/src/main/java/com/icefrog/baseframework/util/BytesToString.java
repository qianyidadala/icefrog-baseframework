/***
 * Copyright 2019 Icefrog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icefrog.baseframework.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 二进制数组转换成进制字符串工具类
 */
public class BytesToString {

    /**
     * hexDigits
     */
    private final static char[] HEXDIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
        'b', 'c', 'd', 'e', 'f'};
    /**
     * Pattern
     */
    private final static Pattern P = Pattern.compile("^[0-9abcdef]{2,4}$");

    /**
     * 转换字节数组为10进制字串
     *
     * @param b 字节数组
     * @return 10进制字串
     */
    public static String byteArrayToNumString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            //使用本函数则返回加密结果的10进制数字字串，即全数字形式
            resultSb.append(byteToNumString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节数组为10进制字串
     *
     * @param b 字节数组
     * @param pos 开始位置
     * @param length 长度
     * @return 10进制字串
     */
    public static String byteArrayToNumString(byte[] b, int pos, int length) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = pos; i < pos + length; i++) {
            //使用本函数则返回加密结果的10进制数字字串，即全数字形式
            resultSb.append(byteToNumString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            //若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @param pos 开始位置
     * @param length 长度
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b, int pos, int length) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = pos; i < length + pos; i++) {
            //若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节为16进制字符串
     *
     * @param b 字节
     * @return 16进制字符串
     */
    private static String byteToNumString(byte b) {

        int be = b;
        if (be < 0) {
            be = 256 + be;
        }
        return String.valueOf(be);
    }

    /**
     * 转换字节为10进制字符串
     *
     * @param b 字节
     * @return 10进制字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return "" + HEXDIGITS[d1] + HEXDIGITS[d2];
    }

    /**
     * 16进字符串转换成字节数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toLowerCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (Arrays.binarySearch(HEXDIGITS, hexChars[pos]) << 4
                | Arrays.binarySearch(HEXDIGITS, hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 转换字符数组内非ASCII编码为%nn的结构字符串
     *
     * @return 非ASCII编码为%nn的结构字符串
     */
    public static String byteArrayToURLString(byte[] b) {
        byte ch = 0x00;
        int i = 0;
        if (b == null || b.length <= 0) {
            return null;
        }

        String[] pseudo = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F"};
        StringBuilder out = new StringBuilder(b.length * 2);

        while (i < b.length) {
            // First check to see if we need ASCII or HEX
            boolean flag = (b[i] >= '0' && b[i] <= '9') || (b[i] >= 'a' && b[i] <= 'z')
                || (b[i] >= 'A' && b[i] <= 'Z') || b[i] == '$' || b[i] == '-' || b[i] == '_'
                || b[i] == '.' || b[i] == '!';
            if (flag) {
                out.append((char) b[i]);
                i++;
            } else {
                out.append('%');
                // Strip off high nibble
                ch = (byte) (b[i] & 0xF0);
                // shift the bits down
                ch = (byte) (ch >>> 4);
                // must do this is high order bit is
                ch = (byte) (ch & 0x0F);
                // on!
                // convert the nibble to a
                out.append(pseudo[(int) ch]);
                // String Character
                // Strip off low nibble
                ch = (byte) (b[i] & 0x0F);
                // convert the nibble to a
                out.append(pseudo[(int) ch]);
                // String Character
                i++;
            }
        }

        String rslt = new String(out);

        return rslt;
    }

    /**
     * 将hex数据转换成input流
     */
    public static InputStream hexStringToInputStream(String hexString) {
        return new BufferedInputStream(new ByteArrayInputStream(hexStringToBytes(hexString)));
    }

    /**
     * 读取文件内容
     *
     * @return 读取文件内容的16进制字符串
     */
    public static String fileArrayToHexString(File file) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buf = new byte[(int) file.length()];
            int off = 0;
            while (off < buf.length) {
                int i = input.read(buf, off, buf.length - off);
                if (i < 0) {
                    break;
                }
                off += i;
            }
            return BytesToString.byteArrayToHexString(buf);
        } catch (Exception e) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /**
     * 字符串转unicode字符串
     *
     * @return unicode字符串
     */
    public static String stringToUnicode(String string) {

        StringBuilder unicode = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    public static String unicode2String(String unicode) {

        StringBuilder string = new StringBuilder();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            String s = null;
            // 转换出每一个代码点
            if (hex[i].length() < 4) {
                s = hex[i];
            } else {
                s = hex[i].substring(0, 4).toLowerCase();
            }
            Matcher m = P.matcher(s);
            if (!m.matches()) {
                continue;
            }
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
}
