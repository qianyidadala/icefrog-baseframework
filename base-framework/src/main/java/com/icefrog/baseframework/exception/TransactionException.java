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
 * Transaction Exception. 用于各种事务场景回滚（Spring注解事务需要RuntimeException或派生类作为回滚依据）
 *
 * @author icefrog.su@qq.com
 */
public class TransactionException extends RuntimeException {
    
    private static final long serialVersionUID = 5912317429431109021L;
    
    public TransactionException(){
        super();
    }
    
    public TransactionException(String message){
        super(message);
    }
    
    public TransactionException(String message, Throwable t){
        super(message, t);
    }
}
