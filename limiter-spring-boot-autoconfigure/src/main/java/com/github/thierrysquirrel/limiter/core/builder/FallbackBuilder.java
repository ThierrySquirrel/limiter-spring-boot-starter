/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.limiter.core.builder;

import java.lang.reflect.Method;

/**
 * ClassName: FallbackBuilder 
 * Description: 
 * date: 2020/6/18 15:01
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class FallbackBuilder {
    private FallbackBuilder() {
    }
    public static Method builderFallbackMethod(Object bean,String fallbackMethod,Class<?>[] parameterTypes) throws NoSuchMethodException {
        return bean.getClass ().getMethod (fallbackMethod, parameterTypes);
    }
}
