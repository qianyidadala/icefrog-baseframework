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

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * hmac算法
 */
public class Hmac {

    private final static String SHA1 = "HmacSHA1";

    private final static String[] HEXDIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToNumString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte itemByte : b) {
            //使用本函数则返回加密结果的10进制数字字串，即全数字形式
            resultSb.append(byteToNumString(itemByte));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节数组为10进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte itemByte : b) {
            //若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
            resultSb.append(byteToHexString(itemByte));
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
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }

    /**
     * 将字节数组算出16进制HMAC串
     *
     * @param origin 字节数组
     * @param key 密钥
     * @param algo 加密方式：HmacSHA1,
     * @return 16进制HMAC字符串
     */
    public static String encode16(byte[] origin, String key, String algo)
        throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algo);
        SecretKeySpec spec = new SecretKeySpec(key.getBytes(), algo);
        mac.init(spec);
        byteArrayToHexString(mac.doFinal(origin));
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return byteArrayToHexString(md.digest(origin));
    }

    /**
     * 将字符串算出16进制HMAC串，字符串按系统编码进行编码
     *
     * @param origin 字符串
     * @param key 密钥
     * @param algo 加密方式：HmacSHA1,
     * @return 16进制HMAC字符串
     */
    public static String encode16(String origin, String key, String algo)
        throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algo);
        SecretKeySpec spec = new SecretKeySpec(key.getBytes(), algo);
        mac.init(spec);
        return byteArrayToHexString(mac.doFinal(origin.getBytes()));
    }
}
