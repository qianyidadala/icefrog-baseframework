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

package com.icefrog.baseframework.web;

import com.icefrog.baseframework.exception.ApiException;

/***
 * ApiBaseController, 为Rest服务提供基础能力. 区别于WebBaseController
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public abstract class ApiBaseController extends BaseController {
    
    /***
     * 引发ApiException
     * @param message exception message
     * @throws Exception Exception
     */
    @Override
    public void throwApiException(String message) throws Exception {
        throw new ApiException(message);
    }
    
    /***
     * 引发ApiException
     * @param message exception message
     * @param t Throwable
     * @throws Exception Exception
     */
    @Override
    public void throwApiException(String message, Throwable t) throws Exception {
        throw new ApiException(message, t);
    }
    
    /***
     * 引发WebException (extends to RuntimeException)
     * ApiBaseController 不支持此方法, 调用此方法将引发UnsupportedOperationException异常
     * 请参见WebBaseController
     */
    @Deprecated
    @Override
    public void throwWebException() {
        throw new UnsupportedOperationException("ApiBaseController Unsupported the WebException, please see the WebBaseController");
    }
    
    /***
     * 引发WebException (extends to RuntimeException)
     * ApiBaseController 不支持此方法, 调用此方法将引发UnsupportedOperationException异常
     * 请参见WebBaseController
     * @param message exception message
     */
    @Deprecated
    @Override
    public void throwWebException(String message) {
        throw new UnsupportedOperationException("ApiBaseController Unsupported the WebException, please see the WebBaseController");
    }
    
    /***
     * 引发WebException (extends to RuntimeException)
     * ApiBaseController 不支持此方法, 调用此方法将引发UnsupportedOperationException异常
     * 请参见WebBaseController
     * @param message exception message
     * @param t Throwable
     */
    @Deprecated
    @Override
    public void throwWebException(String message, Throwable t) {
        throw new UnsupportedOperationException("ApiBaseController Unsupported the WebException, please see the WebBaseController");
    }
    
}
