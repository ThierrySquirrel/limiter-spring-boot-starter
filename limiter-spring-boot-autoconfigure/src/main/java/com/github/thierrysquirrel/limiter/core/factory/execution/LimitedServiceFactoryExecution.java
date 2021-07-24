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

import com.github.thierrysquirrel.limiter.annotation.LimitedService;
import com.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.LimitedServiceStrategyFactory;
import com.github.thierrysquirrel.limiter.core.factory.ServiceDomainFactory;
import com.github.thierrysquirrel.limiter.strategy.LimitedServiceStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: LimitedServiceFactoryExecution
 * Description:
 * date: 2020/6/18 17:33
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class LimitedServiceFactoryExecution {
    private LimitedServiceFactoryExecution() {
    }

    public static Object execution(ApplicationContext applicationContext, ProceedingJoinPoint point, LimitedService limitedService, String methodString, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        ServiceStatusConstant serviceStatusConstant = ServiceDomainFactory.getServiceStatusConstant (methodString);
        LimitedServiceStrategy limitedServiceStrategy = LimitedServiceStrategyFactory.getLimitedServiceStrategy (serviceStatusConstant);
        return limitedServiceStrategy.limitedService (applicationContext, point, limitedService, methodString, parameterTypes, parameter);
    }
}
