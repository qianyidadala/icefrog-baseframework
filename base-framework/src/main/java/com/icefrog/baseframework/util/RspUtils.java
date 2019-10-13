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

package com.icefrog.baseframework.util;

import com.icefrog.baseframework.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
public class RspUtils {

    public static void responseErrorApiResultJson(final String message, Map<String, Object> data, HttpServletResponse response){
        responseApiResultJson(ApiResult.CODE_FAILED, message, data, response);
    }
    
    public static void responseApiResultJson(final int code, final String message, final Map<String, Object> data,
                                      HttpServletResponse response){
        PrintWriter out = null;
        ApiResult apiResult = new ApiResult().setCode(code).setMessage(message).setData(data);
        String jsonApiResult = JSONObject.fromObject(apiResult).toString();
        try{
            response.setContentType("application/json;charset=UTF-8");
            out = response.getWriter();
            out.write(jsonApiResult);
        } catch (Exception e){
            log.error("response error (RspUtils#responseApiResultJson)", e);
        } finally {
            if(out != null){
                out.close();
            }
        }
    }

}