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

package com.icefrog.baseframework.feign;

import com.icefrog.baseframework.api.ApiResult;
import com.icefrog.baseframework.web.BaseServer;
import lombok.extern.slf4j.Slf4j;

/***
 * 基础Fallback
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
@Slf4j
public abstract class BaseFallback extends BaseServer {
    
    protected ApiResult errorHandler(String message , Throwable t){
        log.error(message, t.getMessage(), t);
        return error(message + t.getMessage());
    }

}