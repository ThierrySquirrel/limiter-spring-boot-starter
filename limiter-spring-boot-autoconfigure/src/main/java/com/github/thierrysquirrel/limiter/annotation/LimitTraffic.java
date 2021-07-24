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

package com.github.thierrysquirrel.limiter.annotation;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * ClassName: LimitTraffic
 * Description:
 * date: 2019/7/17 16:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface LimitTraffic {

    /**
     * Limit Name,If The Names Are The Same, Use The Same Token Bucket
     *
     * 限流名称,如果名称一样,使用同一个令牌桶
     *
     * @return String
     */
    String limitName();

    /**
     * Permits Per Second
     *
     * 每秒许可数
     *
     * @return double
     */

    double permitsPerSecond();

    /**
     * Fallback Class
     *
     * 回退Class
     *
     * @return Class
     */
    Class<?> fallbackClass();

    /**
     * Fallback Method
     *
     * 回退方法
     *
     * @return String
     */

    String fallbackMethod();
}
