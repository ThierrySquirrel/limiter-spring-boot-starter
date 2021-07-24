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

import com.github.thierrysquirrel.limiter.core.builder.ServiceDomainBuilder;
import com.github.thierrysquirrel.limiter.core.constant.ServiceDomainFactoryConstant;
import com.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import com.github.thierrysquirrel.limiter.core.domain.ServiceDomain;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

/**
 * ClassName: ServiceDomainFactory
 * Description:
 * date: 2020/6/18 15:46
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ServiceDomainFactory {
    private static final Map<String, ServiceDomain> SERVICE_DOMAIN_MAP = Maps.newConcurrentMap ();

    private ServiceDomainFactory() {
    }

    private static ServiceDomain getServiceDomain(String methodString) {
        return SERVICE_DOMAIN_MAP.computeIfAbsent (methodString, key -> ServiceDomainBuilder.builderServiceDomain ());
    }

    public static ServiceStatusConstant getServiceStatusConstant(String methodString) {
        return getServiceDomain (methodString).getServiceStatusConstant ();
    }

    public static void successExecution(String methodString, long beforeTime) {
        long afterTime = System.currentTimeMillis ();
        if (afterTime - beforeTime < ServiceDomainFactoryConstant.TIMEOUT) {
            success (methodString);
        } else {
            timeout (methodString);
        }
        resetCount (methodString);
    }

    public static void fail(String methodString) {
        getServiceDomain (methodString).getFailCount ().increment ();
        resetCount (methodString);
    }

    public static boolean tryOpen(String methodString) {
        ServiceDomain serviceDomain = getServiceDomain (methodString);
        LongAdder tryCount = serviceDomain.getTryCount ();
        tryCount.increment ();
        long tryCountValue = tryCount.longValue ();
        resetCount (methodString);
        if (tryCountValue >= ServiceDomainFactoryConstant.MAX_TRY_COUNT) {
            serviceDomain.setServiceStatusConstant (ServiceStatusConstant.CLOSE);
            serviceDomain.setCloseTime (System.currentTimeMillis ());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static void open(String methodString) {
        getServiceDomain (methodString).setServiceStatusConstant (ServiceStatusConstant.OPEN);
        resetCount (methodString);
    }


    private static void success(String methodString) {
        getServiceDomain (methodString).getSuccessCount ().increment ();
        resetCount (methodString);
    }

    private static void timeout(String methodString) {
        getServiceDomain (methodString).getTimeoutCount ().increment ();
        resetCount (methodString);
    }

    public static void resetCount(String methodString) {
        ServiceDomain serviceDomain = getServiceDomain (methodString);
        Long resetCountTime = serviceDomain.getResetCountTime ();
        long thisTime = System.currentTimeMillis ();
        if (thisTime - resetCountTime < ServiceDomainFactoryConstant.RESET_COUNT_TIME) {
            return;
        }
        serviceDomain.setResetCountTime (thisTime);
        ServiceStatusConstant serviceStatusConstant = serviceDomain.getServiceStatusConstant ();
        if (serviceStatusConstant == ServiceStatusConstant.OPEN) {
            stateChange (serviceDomain, thisTime);
        }
        if (serviceStatusConstant == ServiceStatusConstant.CLOSE) {
            tryState (serviceDomain, thisTime);
        }

        serviceDomain.getSuccessCount ().reset ();
        serviceDomain.getFailCount ().reset ();
        serviceDomain.getTimeoutCount ().reset ();
        serviceDomain.getTryCount ().reset ();
    }

    private static void stateChange(ServiceDomain serviceDomain, long thisTime) {

        LongAdder successCount = serviceDomain.getSuccessCount ();
        successCount.increment ();
        long successCountValue = successCount.longValue ();
        long failCountValue = serviceDomain.getFailCount ().longValue ();
        long failRatio = (failCountValue * ServiceDomainFactoryConstant.PERCENTAGE_CONVERSION) / successCountValue;

        long timeoutCountValue = serviceDomain.getTimeoutCount ().longValue ();
        if (failRatio >= ServiceDomainFactoryConstant.FAIL_RATIO || timeoutCountValue >= ServiceDomainFactoryConstant.MAX_TIMEOUT_COUNT) {
            serviceDomain.setServiceStatusConstant (ServiceStatusConstant.CLOSE);
            serviceDomain.setCloseTime (thisTime);
        }


    }

    private static void tryState(ServiceDomain serviceDomain, long thisTime) {
        Long closeTime = serviceDomain.getCloseTime ();
        ServiceStatusConstant status = serviceDomain.getServiceStatusConstant ();
        if (status == ServiceStatusConstant.CLOSE && thisTime - closeTime >= ServiceDomainFactoryConstant.MAX_CLOSE_TIME) {
            serviceDomain.setServiceStatusConstant (ServiceStatusConstant.TRY);
        }
    }

}
