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

package com.icefrog.baseframework.enums;

import java.io.Serializable;

/***
 * 枚举类型接口。 理论所有自定义枚举都应当实现该接口已获得统一接口
 *
 * @author icefrog.su@qq.com
 */
public interface IEnum {

    /***
     * 获得枚举值
     * @return 字符串类型的枚举值结果
     */
    String getValue();

    /***
     * 获得枚举值
     * @return Serializable类型的枚举值结果
     */
    Serializable getValueDefined();

    /***
     * 获得枚举描述内容
     * @return 字符串形式枚举描述内容
     */
    String getDescription();

}
