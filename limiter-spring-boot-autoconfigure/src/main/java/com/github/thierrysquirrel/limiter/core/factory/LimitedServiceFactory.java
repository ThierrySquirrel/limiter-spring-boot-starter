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
package com.github.thierrysquirrel.limiter.core.factory;

import com.github.thierrysquirrel.limiter.annotation.LimitedService;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.execution.FallbackFactoryExecution;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: LimitedServiceFactory
 * Description:
 * date: 2020/6/18 16:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class LimitedServiceFactory {
    private LimitedServiceFactory() {
    }

    public static Object fallback(ApplicationContext applicationContext, LimitedService limitedService, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        return FallbackFactoryExecution.fallback (applicationContext, limitedService.fallbackClass (), limitedService.fallbackMethod (), parameterTypes, parameter);
    }
}
