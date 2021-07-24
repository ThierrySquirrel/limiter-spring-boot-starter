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

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;

/**
 * ClassName: RateLimiterFactory
 * Description:
 * date: 2020/6/18 14:59
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RateLimiterFactory {
    private static final Map<String, RateLimiter> RATE_LIMITER_MAP = Maps.newConcurrentMap ();

    private RateLimiterFactory() {
    }

    public static boolean isRelease(String limitName, double permitsPerSecond) {
        return RATE_LIMITER_MAP.computeIfAbsent (limitName, key -> RateLimiter.create (permitsPerSecond)).tryAcquire ();
    }
}
