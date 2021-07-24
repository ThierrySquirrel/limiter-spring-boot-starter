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
package com.github.thierrysquirrel.limiter.core.constant;

/**
 * ClassName: ServiceDomainFactoryConstant
 * Description:
 * date: 2020/6/18 15:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ServiceDomainFactoryConstant {
    public static final int RESET_COUNT_TIME = 1000;
    public static final int PERCENTAGE_CONVERSION = 100;
    public static final int FAIL_RATIO = 32;
    public static final int MAX_TIMEOUT_COUNT = Runtime.getRuntime ().availableProcessors ();
    public static final int MAX_CLOSE_TIME = 4000;
    public static final int MAX_TRY_COUNT = Runtime.getRuntime ().availableProcessors ();
    public static final int TIMEOUT=1000;

    private ServiceDomainFactoryConstant() {
    }
}
