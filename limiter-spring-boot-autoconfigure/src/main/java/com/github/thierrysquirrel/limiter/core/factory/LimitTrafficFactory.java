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

import com.github.thierrysquirrel.limiter.annotation.LimitTraffic;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.execution.FallbackFactoryExecution;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: LimitTrafficFactory
 * Description:
 * date: 2020/6/18 15:26
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class LimitTrafficFactory {
    private LimitTrafficFactory() {
    }

    public static Object release(ProceedingJoinPoint point) throws LimitException {

        try {
            return point.proceed ();
        } catch (Throwable throwable) {
            throw new LimitException (throwable);
        }
    }

    public static Object fallback(ApplicationContext applicationContext, LimitTraffic limitTraffic, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        return FallbackFactoryExecution.fallback (applicationContext, limitTraffic.fallbackClass (), limitTraffic.fallbackMethod (), parameterTypes, parameter);
    }
}
