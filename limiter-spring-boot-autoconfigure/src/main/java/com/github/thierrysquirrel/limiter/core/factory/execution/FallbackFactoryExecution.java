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
package com.github.thierrysquirrel.limiter.core.factory.execution;

import com.github.thierrysquirrel.limiter.core.builder.FallbackDomainBuilder;
import com.github.thierrysquirrel.limiter.core.domain.FallbackDomain;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.FallbackFactory;
import com.github.thierrysquirrel.limiter.core.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: FallbackFactoryExecution
 * Description:
 * date: 2020/6/18 15:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class FallbackFactoryExecution {
    private FallbackFactoryExecution() {
    }

    public static Object fallback(ApplicationContext applicationContext,Class<?> fallbackClass,String fallbackMethod, Class<?>[] parameterTypes, Object[] parameter)throws LimitException {
        Object fallbackBean = ApplicationContextUtils.getFallbackBean (applicationContext, fallbackClass);
        try {
            FallbackDomain fallbackDomain = FallbackDomainBuilder.builderFallbackDomain (fallbackBean, fallbackMethod, parameterTypes, parameter);
            return FallbackFactory.fallback (fallbackDomain);
        }catch (Exception e){
            throw new LimitException (e);
        }
    }
}
