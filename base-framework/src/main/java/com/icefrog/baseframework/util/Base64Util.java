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

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

    /**
     * Base64解码
     */
    public static byte[] decode(String str) {
        Base64 base64 = new Base64();
        return base64.decode(str.getBytes());
    }

    /**
     * Base64解码
     */
    public static String decodeString(String str) {
        Base64 base64 = new Base64();
        return new String(base64.decode(str.getBytes()));
    }

    /**
     * Base64编码
     */
    public static String encode(byte[] b) {
        Base64 base64 = new Base64();
        return new String(base64.encode(b));
    }

    /**
     * Base64编码
     */
    public static byte[] encodeByte(byte[] b) {
        Base64 base64 = new Base64();
        return base64.encode(b);
    }
}
