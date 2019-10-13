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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加解密工具类
 */
public class Des {

    /**
     * 解密
     *
     * @return 解密串
     */
    public static String decode16(String string, String key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return BytesToString.byteArrayToHexString(decode(string.getBytes(), key.getBytes()));
    }

    /**
     * 解密
     *
     * @return 解密串
     */
    public static String decode(String string, String key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return new String(decode(string.getBytes(), key.getBytes()));
    }

    /**
     * 解密
     *
     * @return 解密串
     */
    public static String decode16(byte[] string, byte[] key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return BytesToString.byteArrayToHexString(decode(string, key));
    }

    /**
     * 解密
     *
     * @return 解密串
     */
    public static byte[] decode(byte[] string, byte[] key)
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey secretkey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretkey, random);
        // 真正开始解密操作
        return cipher.doFinal(string);
    }

    /**
     * 加密成16位串
     *
     * @return 解密串
     */
    public static String encode16(String string, String key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return BytesToString.byteArrayToHexString(encode(string.getBytes(), key.getBytes()));
    }

    /**
     * 加密
     *
     * @return 加密串
     */
    public static String encode(String string, String key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return new String(encode(string.getBytes(), key.getBytes()));
    }

    /**
     * 加密
     *
     * @return 加密串
     */
    public static String encodeBytes(byte[] string, byte[] key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return new String(encode(string, key));
    }

    /**
     * 加密16位串
     *
     * @return 加密串
     */
    public static String encode16Bytes(byte[] string, byte[] key)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return BytesToString.byteArrayToHexString(encode(string, key));
    }

    /**
     * 加密
     *
     * @return 加密串
     */
    public static byte[] encode(byte[] string, byte[] key)
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key);
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretkey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretkey, random);
        //现在，获取数据并加密
        //正式执行加密操作
        return cipher.doFinal(string);
    }
}
