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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1的算法
 */
public class Sha1 {

    /**
     * 将字节数组算出16进制SHA串
     *
     * @param origin 字节数组
     * @return 16进制SHA字符串
     */
    public static String encode16(byte[] origin) throws NoSuchAlgorithmException {
        byte[] b = encode(origin);
        if (b == null) {
            return null;
        }
        return BytesToString.byteArrayToHexString(b);
    }

    /**
     * 返回字节数组算出二进制SHA串
     *
     * @param origin 字节数组
     * @return 二进制SHA串
     */
    public static byte[] encode(byte[] origin) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(origin);
    }

    /**
     * 将字符串算出16进制SHA-1串，字符串按系统编码进行编码
     *
     * @param origin 字符串
     * @return 16进制SHA-1字符串
     */
    public static String encode16(String origin) throws NoSuchAlgorithmException {
        return encode16(origin.getBytes());
    }

    /**
     * 将文件内容算出16进制SHA-1串
     *
     * @param fileName 文件完整路径
     * @return 16进制SHA-1字符串
     */
    public static String encodeFile16(String fileName)
        throws IOException, NoSuchAlgorithmException {
        File files = new File(fileName);
        return encode16(files);
    }

    /**
     * 将文件内容算出16进制SHA-1串
     *
     * @param files 文件对象
     * @return 16进制SHA-1字符串
     */
    public static String encode16(File files) throws IOException, NoSuchAlgorithmException {
        String resultString = null;
        FileInputStream fr = null;
        DigestInputStream mdfile = null;
        try {
            fr = new FileInputStream(files);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            mdfile = new DigestInputStream(fr, md);
            int len = 64 * 1024;
            byte[] buf = new byte[len];
            while (mdfile.read(buf, 0, len) != -1) {
            }
            resultString = BytesToString.byteArrayToHexString(md.digest());
        } finally {
            if (mdfile != null) {
                try {
                    mdfile.close();
                } catch (IOException e) {
                }
                mdfile = null;
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                }
                fr = null;
            }
        }
        return resultString;
    }
}
