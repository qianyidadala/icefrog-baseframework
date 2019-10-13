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
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5算法
 */
public final class Md5 {

    /**
     * 将字节数组算出16进制MD5串
     *
     * @param origin 字节数组
     * @return 16进制MD5字符串
     */
    public static String encode16(byte[] origin) throws NoSuchAlgorithmException {
       MessageDigest md = MessageDigest.getInstance("Md5");
       return BytesToString.byteArrayToHexString(md.digest(origin));
    }

    /**
     * 将字符串算出16进制MD5串，字符串按系统编码进行编码
     *
     * @param origin 字符串
     * @return 16进制MD5字符串
     */
    public static String encode16(String origin) throws NoSuchAlgorithmException {
        return encode16(origin.getBytes());
    }

    /**
     * 将文件内容算出16进制MD5串
     *
     * @param fileName 文件完整路径
     * @return 16进制MD5字符串
     */
    public static String encodeFile16(String fileName) throws IOException, NoSuchAlgorithmException {
        File files = new File(fileName);
        return encode16(files);
    }

    /**
     * 将文件内容算出16进制MD5串
     *
     * @param files 文件对象
     * @return 16进制MD5字符串
     */
    public static String encode16(File files) throws IOException, NoSuchAlgorithmException {
        String resultString = null;
        FileInputStream fr = null;
        try {
            fr = new FileInputStream(files);
            resultString = encode16(fr);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
            }
            fr = null;
        }
        return resultString;
    }

    /**
     * 将流的内容算出16进制MD5串
     *
     * @param inputStream inputStream
     * @return	16进制MD5串
     */
    public static String encode16(InputStream inputStream)
        throws NoSuchAlgorithmException, IOException {
            MessageDigest md = MessageDigest.getInstance("Md5");
            DigestInputStream mdfile = new DigestInputStream(inputStream, md);
            int len = 64 * 1024;
            byte[] buf = new byte[len];
            while (mdfile.read(buf, 0, len) != -1) {
            }
        return  BytesToString.byteArrayToHexString(md.digest());
    }
}
