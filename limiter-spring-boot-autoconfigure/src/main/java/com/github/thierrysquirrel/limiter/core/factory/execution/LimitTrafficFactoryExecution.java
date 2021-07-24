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

import com.github.thierrysquirrel.limiter.annotation.LimitTraffic;
import com.github.thierrysquirrel.limiter.core.error.LimitException;
import com.github.thierrysquirrel.limiter.core.factory.LimitTrafficFactory;
import com.github.thierrysquirrel.limiter.core.factory.RateLimiterFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: LimitTrafficFactoryExecution
 * Description:
 * date: 2020/6/18 15:29
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class LimitTrafficFactoryExecution {
    private LimitTrafficFactoryExecution() {
    }

    public static Object limitTraffic(ProceedingJoinPoint point, ApplicationContext applicationContext,LimitTraffic limitTraffic, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        boolean release = RateLimiterFactory.isRelease (limitTraffic.limitName (), limitTraffic.permitsPerSecond ());
        if (release) {
            return LimitTrafficFactory.release (point);
        }
        return LimitTrafficFactory.fallback (applicationContext, limitTraffic, parameterTypes, parameter);
    }
}
