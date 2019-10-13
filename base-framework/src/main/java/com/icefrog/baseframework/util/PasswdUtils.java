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

import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/***
 * 密码构建工具
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public class PasswdUtils {
    
    /***
     * 构建一个密码, 使用MD5+salt加密, 需自行保存salt值，否则无法利用验证方法进行校验
     * @param passwd 明文密码
     * @param salt salt val
     * @return encode password with md5 remix salt
     */
    public static final String buildPasswd(String passwd, String salt){
    
        try {
            return MD5.encode16(MD5.encode16(passwd) + salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /***
     * 校验密码正确性
     * @param passwd 待校验的明文密码
     * @param encodePasswd 加密过的真实密码
     * @param salt salt val
     * @return 校验通过则返回true, 否则返回false
     */
    public static final boolean verification(String passwd, String encodePasswd, String salt){
        Assert.notNull(passwd, "待验证的明文密码不允许为空!");
        Assert.notNull(encodePasswd, "待验证的加密密码不允许为空!");
        return encodePasswd.equals(buildPasswd(passwd, salt));
    }
    
    /***
     * 内部MD5实现, 便于此工具类迁移
     */
    static class MD5 {
        
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
    }
    
}


