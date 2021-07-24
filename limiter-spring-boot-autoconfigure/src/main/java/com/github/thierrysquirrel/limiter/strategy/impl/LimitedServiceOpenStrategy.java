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
package com.github.thierrysquirrel.limiter.strategy.impl;

import com.github.thierrysquirrel.limiter.annotation.LimitedService;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.LimitedServiceFactory;
import com.github.thierrysquirrel.limiter.core.factory.ServiceDomainFactory;
import com.github.thierrysquirrel.limiter.strategy.LimitedServiceStrategy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: LimitedServiceOpenStrategy
 * Description:
 * date: 2020/6/18 16:41
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class LimitedServiceOpenStrategy implements LimitedServiceStrategy {
    @Override
    public Object limitedService(ApplicationContext applicationContext, ProceedingJoinPoint point, LimitedService limitedService, String methodString, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        try {
            long beforeTime = System.currentTimeMillis ();
            Object proceed = point.proceed ();
            ServiceDomainFactory.successExecution (methodString, beforeTime);
            return proceed;
        } catch (Throwable throwable) {
            log.error ("LimitedServiceOpenStrategy Error",throwable);
            ServiceDomainFactory.fail (methodString);
            return LimitedServiceFactory.fallback (applicationContext, limitedService, parameterTypes, parameter);
        }
    }
}
