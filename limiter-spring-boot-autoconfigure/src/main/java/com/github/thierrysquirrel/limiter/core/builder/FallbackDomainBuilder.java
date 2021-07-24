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

import com.github.thierrysquirrel.limiter.core.domain.FallbackDomain;

import java.lang.reflect.Method;

/**
 * ClassName: FallbackDomainBuilder
 * Description:
 * date: 2020/6/18 15:06
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class FallbackDomainBuilder {
    private FallbackDomainBuilder() {
    }

    private static FallbackDomain builderFallbackDomain(Object bean, Method method, Object[] parameter) {
        FallbackDomain fallbackDomain = new FallbackDomain ();
        fallbackDomain.setBean (bean);
        fallbackDomain.setMethod (method);
        fallbackDomain.setParameter (parameter);
        return fallbackDomain;
    }

    public static FallbackDomain builderFallbackDomain(Object bean, String fallbackMethod, Class<?>[] parameterTypes, Object[] parameter) throws NoSuchMethodException {
        Method method = FallbackBuilder.builderFallbackMethod (bean, fallbackMethod, parameterTypes);
        return builderFallbackDomain (bean, method, parameter);
    }
}
