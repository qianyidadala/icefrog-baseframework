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

import com.icefrog.baseframework.api.ApiResult;
import com.icefrog.baseframework.exception.ApiException;
import com.icefrog.baseframework.exception.WebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/***
 * Global Exception Handler.  针对多种异常类型不同处理
 *
 * @author icefrog.su@qq.com
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ApiBaseController {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult apiGlobalExceptionHandler(Exception e){
        String errorMsg = String.format("系统异常:%s", e.getMessage());
        log.error(errorMsg, e);
        return error(errorMsg);
    }
    
    @ExceptionHandler(WebException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String webGlobalExceptionHandler(Exception e){
        String errorMsg = String.format("系统异常:%s", e.getMessage());
        log.error(errorMsg, e);
        return "/error/500.html";
    }

}
