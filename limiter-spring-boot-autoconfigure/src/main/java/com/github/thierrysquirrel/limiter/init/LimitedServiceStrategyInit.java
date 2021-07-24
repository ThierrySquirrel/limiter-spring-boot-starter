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
package com.github.thierrysquirrel.limiter.init;

import com.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import com.github.thierrysquirrel.limiter.core.factory.LimitedServiceStrategyFactory;
import com.github.thierrysquirrel.limiter.strategy.impl.LimitedServiceCloseStrategy;
import com.github.thierrysquirrel.limiter.strategy.impl.LimitedServiceOpenStrategy;
import com.github.thierrysquirrel.limiter.strategy.impl.LimitedServiceTryStrategy;

import javax.annotation.PostConstruct;

/**
 * ClassName: LimitedServiceStrategyInit
 * Description:
 * date: 2020/6/18 17:28
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class LimitedServiceStrategyInit {
    @PostConstruct
    public void init() {
        LimitedServiceStrategyFactory.putLimitedServiceStrategy (ServiceStatusConstant.CLOSE, new LimitedServiceCloseStrategy ());
        LimitedServiceStrategyFactory.putLimitedServiceStrategy (ServiceStatusConstant.OPEN, new LimitedServiceOpenStrategy ());
        LimitedServiceStrategyFactory.putLimitedServiceStrategy (ServiceStatusConstant.TRY, new LimitedServiceTryStrategy ());
    }
}
