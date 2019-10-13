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

import java.util.HashMap;
import java.util.Map;

/***
 * 基础service, 包含针对ApiResult的快捷操作
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public class BaseServer {
    
    /***
     * 构建一个默认消息的成功状态返回对象
     * @return ApiResult
     */
    protected ApiResult success(){
        return this.buildApiResult(ApiResult.CODE_SUCCESS, ApiResult.SUCCESS_MESSAGE, null, null);
    }
    
    /***
     * 构建一个返回指定消息的成功状态返回对象
     * @param message 成功消息
     * @return ApiResult
     */
    protected ApiResult success(String message){
        return this.buildApiResult(ApiResult.CODE_SUCCESS, message, null, null);
    }
    
    /***
     * 构建一个返回指定消息和附加数据的成功状态返回对象
     * @param message 成功消息
     * @param data 附加数据
     * @return ApiResult
     */
    protected ApiResult success(String message, Map<String, Object> data){
        return this.buildApiResult(ApiResult.CODE_SUCCESS, message, data, null);
    }
    
    protected ApiResult success(Map<String, Object> data){
        return this.success(ApiResult.SUCCESS_MESSAGE, data);
    }
    
    protected ApiResult success(String key, Object value){
        Map<String, Object> data = new HashMap<>(1);
        data.put(key, value);
        return this.success(data);
    }
    
    /***
     * 构建一个默认消息的失败状态返回对象
     * @return ApiResult
     */
    protected ApiResult error(){
        return this.buildApiResult(ApiResult.CODE_FAILED, ApiResult.FAILED_MESSAGE, null, null);
    }
    
    /***
     * 构建一个返回指定消息的失败状态返回对象
     * @param message 失败消息
     * @return ApiResult
     */
    protected ApiResult error(String message){
        return this.buildApiResult(ApiResult.CODE_FAILED, message, null, null);
    }
    
    /***
     * 构建一个返回指定消息的失败状态返回对象
     * @param message 失败消息
     * @param t Throwable object
     * @return ApiResult
     */
    protected ApiResult error(String message, Throwable t){
        return this.buildApiResult(ApiResult.CODE_FAILED, message, null, t);
    }
    
    /***
     * 构建一个返回指定消息和附加数据的失败状态返回对象
     * @param message 失败消息
     * @param data 附加数据
     * @return ApiResult
     */
    protected ApiResult error(String message, Map<String, Object> data){
        return this.buildApiResult(ApiResult.CODE_FAILED, message, data, null);
    }
    
    /***
     * 构建一个返回指定消息和附加数据的失败状态返回对象
     * @param message 失败消息
     * @param data 附加数据
     * @param t Throwable object
     * @return ApiResult
     */
    protected ApiResult error(String message, Map<String, Object> data, Throwable t){
        return this.buildApiResult(ApiResult.CODE_FAILED, message, data, t);
    }
    
    private ApiResult buildApiResult(final int code, final String message, Map data, Throwable t){
        if(t != null){
            t.printStackTrace();
        }
        return new ApiResult().setCode(code).setMessage(message).setData(data);
    }
    
}