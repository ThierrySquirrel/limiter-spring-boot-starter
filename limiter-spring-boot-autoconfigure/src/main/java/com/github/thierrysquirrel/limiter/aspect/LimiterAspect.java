/**
 * Copyright 2019 the original author or authors.
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

package com.github.thierrysquirrel.limiter.aspect;


import com.github.thierrysquirrel.limiter.annotation.LimitTraffic;
import com.github.thierrysquirrel.limiter.annotation.LimitedService;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.execution.LimitTrafficFactoryExecution;
import com.github.thierrysquirrel.limiter.core.factory.execution.LimitedServiceFactoryExecution;
import com.github.thierrysquirrel.limiter.core.utils.AspectUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

/**
 * ClassName: LimiterAspect
 * Description:
 * date: 2019/7/18 11:09
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Aspect
@Slf4j
@Data
public class LimiterAspect implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.github.thierrysquirrel.limiter.annotation.LimitTraffic)")
    public void limitTrafficPointcut() {
        log.debug ("Start LimitTraffic");
    }

    @Pointcut("@annotation(com.github.thierrysquirrel.limiter.annotation.LimitedService)")
    public void limitedServicePointcut() {
        log.debug ("Start LimitedService");
    }

    @Around("limitTrafficPointcut()")
    public Object limitTrafficAround(ProceedingJoinPoint point) throws LimitException {
        return LimitTrafficFactoryExecution.limitTraffic (point,
                applicationContext,
                AspectUtils.getAnnotation (point, LimitTraffic.class),
                AspectUtils.getParameterTypes (point),
                point.getArgs ());
    }

    @Around("limitedServicePointcut()")
    public Object limitedServiceAround(ProceedingJoinPoint point) throws LimitException {
        return LimitedServiceFactoryExecution.execution (applicationContext,
                point,
                AspectUtils.getAnnotation (point, LimitedService.class),
                AspectUtils.getMethodToString (point),
                AspectUtils.getParameterTypes (point),
                point.getArgs ());
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
