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
package com.github.thierrysquirrel.limiter.strategy;

import com.github.thierrysquirrel.limiter.annotation.LimitedService;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: LimitedServiceStrategy
 * Description:
 * date: 2020/6/18 16:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@FunctionalInterface
public interface LimitedServiceStrategy {
    /**
     * limitedService
     *
     * @param applicationContext applicationContext
     * @param point              point
     * @param limitedService     limitedService
     * @param methodString       methodString
     * @param parameterTypes     parameterTypes
     * @param parameter          parameter
     * @return Object
     * @throws LimitException limitException
     */
    Object limitedService(ApplicationContext applicationContext, ProceedingJoinPoint point, LimitedService limitedService, String methodString, Class<?>[] parameterTypes, Object[] parameter) throws LimitException;
}
