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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractCacheFactory {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    public Cache getCache(String key) {
        Cache cache = caches.get(key);
        if (cache == null) {
            caches.put(key, createCache(key));
            cache = caches.get(key);
        }
        return cache;
    }

    protected abstract Cache createCache(String key);
}
