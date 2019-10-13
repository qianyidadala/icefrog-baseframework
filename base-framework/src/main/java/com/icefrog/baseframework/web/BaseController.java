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

import com.icefrog.baseframework.constance.BaseConstance;
import com.icefrog.baseframework.util.HttpClientUtil;
import com.icefrog.baseframework.util.HttpMethod;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/***
 * BaseController
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public abstract class BaseController extends BaseServer implements IController {
    
    /***
     * 获取当前登录用户id, 可能null
     * @return user id
     */
    public String getUserId(){
        JSONObject userInfo = this._getUserInfo();
        return userInfo.getString("id");
    }
    
    /***
     * 获取当前登录用户JsonObject， 可能null
     * @return JSONObject
     */
    public JSONObject getUserInfo(){
        return this._getUserInfo();
    }
    
    private JSONObject _getUserInfo(){
        return JSONObject.fromObject(this.getRequest().getAttribute(BaseConstance.UserInfoHeader));
    }

    protected HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
    
    protected HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }
    
    /**
     * 发送一个http请求 默认使用GET请求方式
     * @return
     */
    protected String callApi(String url){
        return HttpClientUtil.doGet(url, null);
    }
    
    /**
     * 发送一个http请求 默认使用GET请求方式 并指定请求参数
     * @return
     */
    protected String callApi(String url,final Map<String, String> params){
        return HttpClientUtil.doGet(url, params);
    }
    
    /**
     * 发送一个http请求 并指定请求方式
     * @return
     */
    protected String callApi(String url,final HttpMethod requestMethod){
        if(requestMethod == HttpMethod.GET){
            return HttpClientUtil.doGet(url, null);
        }else if(requestMethod == HttpMethod.POST){
            return HttpClientUtil.doPost(url, null);
        }else{
            throw new RuntimeException("Unsupported request method");
        }
    }
    
    /**
     * 发送一个http请求 指定请求方式与参数信息
     * @return
     */
    protected String callApi(String url,final HttpMethod requestMethod, final Map<String, String> params) {
        if(params == null){
            throw new RuntimeException("参数map为null");
        }
        if(requestMethod == HttpMethod.GET){
            return HttpClientUtil.doGet(url, params);
        }else if(requestMethod == HttpMethod.POST){
            return HttpClientUtil.doPost(url, params);
        }else{
            throw new RuntimeException("Unsupported request method");
        }
    }
}
