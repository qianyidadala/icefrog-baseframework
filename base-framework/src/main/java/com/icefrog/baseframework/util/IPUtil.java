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

import javax.servlet.http.HttpServletRequest;

/***
 * IpUtils
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public class IPUtil {
  
	 public static String getIpAddr(HttpServletRequest request) {
	     
	      String ip =applyIp(request.getHeader("x-forwarded-for"));
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = applyIp(request.getHeader("Proxy-Client-IP"));
	     }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = applyIp(request.getHeader("WL-Proxy-Client-IP"));
	      }     
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = applyIp(request.getRemoteAddr());
	     }    
	     return ip;     
	}
	
	private static String applyIp(String ip){
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			 return ip;
		 }
		return ip.indexOf(",")==-1 ? ip:ip.split(",")[0]; 
	}
}