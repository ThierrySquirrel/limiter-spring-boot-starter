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

package com.github.thierrysquirrel.core.execution;


import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadPoolExecutorExecution
 * Description:
 * date: 2019/7/18 11:01
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolExecutorExecution {
	private ThreadPoolExecutorExecution() {
	}

	public static void statsThread(ThreadPoolExecutor threadPoolExecutor, Runnable runnable) {
		threadPoolExecutor.execute(runnable);
	}
}
