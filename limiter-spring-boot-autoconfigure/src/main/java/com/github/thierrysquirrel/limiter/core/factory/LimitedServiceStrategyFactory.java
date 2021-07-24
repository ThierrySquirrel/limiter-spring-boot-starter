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

import com.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import com.github.thierrysquirrel.limiter.strategy.LimitedServiceStrategy;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ClassName: LimitedServiceStrategyFactory
 * Description:
 * date: 2020/6/18 17:22
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class LimitedServiceStrategyFactory {
    private static final Map<ServiceStatusConstant, LimitedServiceStrategy> LIMITED_SERVICE_STRATEGY_MAP = Maps.newConcurrentMap ();

    private LimitedServiceStrategyFactory() {
    }

    public static void putLimitedServiceStrategy(ServiceStatusConstant serviceStatusConstant, LimitedServiceStrategy limitedServiceStrategy) {
        LIMITED_SERVICE_STRATEGY_MAP.put (serviceStatusConstant, limitedServiceStrategy);
    }

    public static LimitedServiceStrategy getLimitedServiceStrategy(ServiceStatusConstant serviceStatusConstant) {
        return LIMITED_SERVICE_STRATEGY_MAP.get (serviceStatusConstant);
    }
}
