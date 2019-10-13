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

import com.icefrog.baseframework.exception.WebException;

/***
 * WebBaseController, 为基础web提供服务. 区别于ApiBaseController
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public abstract class WebBaseController extends BaseController {
    /***
     * 引发ApiException
     * WebBaseController 不支持此方法, 调用此方法将引发UnsupportedOperationException异常
     * 请参见ApiBaseController
     * @param message exception message
     * @throws Exception Exception
     */
    @Deprecated
    @Override
    public void throwApiException(String message) throws Exception {
        throw new UnsupportedOperationException("WebBaseController Unsupported the ApiException, please see the ApiBaseController");
    }
    
    /***
     * 引发ApiException
     * WebBaseController 不支持此方法, 调用此方法将引发UnsupportedOperationException异常
     * 请参见ApiBaseController
     * @param message exception message
     * @param t Throwable
     * @throws Exception Exception
     */
    @Deprecated
    @Override
    public void throwApiException(String message, Throwable t) throws Exception {
        throw new UnsupportedOperationException("WebBaseController Unsupported the ApiException, please see the ApiBaseController");
    }
    
    /***
     * 引发WebException (extends to RuntimeException)
     */
    @Override
    public void throwWebException() {
        throw new WebException();
    }
    
    /***
     * 引发WebException (extends to RuntimeException)
     * @param message exception message
     */
    @Override
    public void throwWebException(String message) {
        throw new WebException(message);
    }
    
    /***
     * 引发WebException (extends to RuntimeException)
     * @param message exception message
     * @param t Throwable
     */
    @Override
    public void throwWebException(String message, Throwable t) {
        throw new WebException(message, t);
    }
}
