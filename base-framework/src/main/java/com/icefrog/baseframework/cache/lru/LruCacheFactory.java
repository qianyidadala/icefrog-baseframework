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

package com.icefrog.baseframework.cache.lru;

import com.icefrog.baseframework.cache.AbstractCacheFactory;
import com.icefrog.baseframework.cache.Cache;
import com.icefrog.baseframework.cache.CacheConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LruCacheFactory extends AbstractCacheFactory {

    private static LruCacheFactory instance = null;

    public static LruCacheFactory getInstance() {
        if (instance == null) {
            synchronized (LruCacheFactory.class) {
                if (instance == null) {
                    instance = new LruCacheFactory();
                }
            }
        }
        return instance;
    }

    @Override
    protected Cache createCache(String cacheKey) {
        int cacheLength = extractCacheLength(cacheKey);
        return new LruCache(cacheLength);
    }

    private int extractCacheLength(String cacheKey) {
        String[] keys = cacheKey.split(CacheConstant.LRU_KEY_SEPARATOR);
        int cacheLength;
        if (keys.length < CacheConstant.LRU_KEY_ARRAY_LENGTH) {
            return CacheConstant.LRU_DEFAULT_LENGTH;
        }
        try {
            cacheLength = Integer.valueOf(keys[1]);
        } catch (NumberFormatException e) {
            log.error( "redis create key format exception, key="+cacheKey+"", e);
            cacheLength = CacheConstant.LRU_DEFAULT_LENGTH;
        }
        return cacheLength;
    }
}
