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

package com.icefrog.baseframework.exception;

/***
 * Api Exception.  用于在任何形式的rest调用中。 不提供无参构造器， 因为所有ApiException都应当具备一个可直接提示用户的异常信息
 *
 * 继承Exception, 用于警示web层尽量手动处理异常兜底, 而非全局异常处理
 *
 * @author icefrog.su@qq.com
 */
public class ApiException extends Exception {
    
    private static final long serialVersionUID = 1218182282183079791L;
    
    public ApiException(String message){
        super(message);
    }
    
    public ApiException(String message, Throwable t){
        super(message, t);
    }
    
    
}
