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

/***
 * 基础Controller规约. 约束Controller基础操作限制
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public interface IController {
    
    /***
     * 引发ApiException
     * @param message exception message
     * @throws Exception Exception
     */
    void throwApiException(String message) throws Exception;
    
    /***
     * 引发ApiException
     * @param message exception message
     * @param t Throwable
     * @throws Exception Exception
     */
    void throwApiException(String message, Throwable t) throws Exception;
    
    /***
     * 引发WebException (extends to RuntimeException)
     */
    void throwWebException();
    
    /***
     * 引发WebException (extends to RuntimeException)
     * @param message exception message
     */
    void throwWebException(String message);
    
    /***
     * 引发WebException (extends to RuntimeException)
     * @param message exception message
     * @param t Throwable
     */
    void throwWebException(String message, Throwable t);
    
}
