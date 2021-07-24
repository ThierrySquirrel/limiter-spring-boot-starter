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
package com.github.thierrysquirrel.limiter.core.builder;

import com.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import com.github.thierrysquirrel.limiter.core.domain.ServiceDomain;

import java.util.concurrent.atomic.LongAdder;

/**
 * ClassName: ServiceDomainBuilder
 * Description:
 * date: 2020/6/18 15:43
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ServiceDomainBuilder {
    private ServiceDomainBuilder() {
    }

    public static ServiceDomain builderServiceDomain() {
        ServiceDomain serviceDomain = new ServiceDomain ();
        serviceDomain.setServiceStatusConstant (ServiceStatusConstant.OPEN);
        serviceDomain.setSuccessCount (new LongAdder ());
        serviceDomain.setFailCount (new LongAdder ());
        serviceDomain.setTimeoutCount (new LongAdder ());
        serviceDomain.setTryCount (new LongAdder ());
        long thisTime = System.currentTimeMillis ();
        serviceDomain.setResetCountTime (thisTime);
        serviceDomain.setCloseTime (thisTime);
        return serviceDomain;
    }
}
