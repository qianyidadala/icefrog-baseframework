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

package com.icefrog.baseframework.cache;

import java.util.Set;

public interface Cache<K, V> {

    void put(K var1, V var2);

    Object get(K var1);

    Set<K> keySet();

    /**
     * 是否存在
     *
     * @param item 项
     * @return 是否存在
     */
    boolean containsKey(K item);

    /**
     * 删除item
     *
     * @param item 项
     */
    void remove(K item);
}
