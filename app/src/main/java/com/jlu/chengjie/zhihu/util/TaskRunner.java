/*
 *    Copyright [2019] [chengjie.jlu@qq.com]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jlu.chengjie.zhihu.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskRunner {

    private static final String TAG = "TaskRunner";

    private static final int CORE_COUNT = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolExecutor executor;

    static {
        /*
         * Create a thread pool. Core thread count is set equal to maximum
         * thread count, and allowCoreThreadTimeOut is set to true, which
         * ensures that we can have all THREAD_POOL_COUNT threads created to
         * execute our jobs instead of queuing jobs before all of the threads
         * are created and no idle threads will live forever.
         */
        executor = new ThreadPoolExecutor(
                CORE_COUNT,                     // core pool threads count
                CORE_COUNT,                     // maximum pool threads count
                1,                              // idle thread can live at most 1 second if not reused
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        executor.allowCoreThreadTimeOut(true);  // allow core threads to be recycled if they're idle
    }

    private TaskRunner() {
        throw new AssertionError("No com.jlu.chengjie.zhihu.util.TaskRunner instances for you!");
    }

    public static void execute(Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            ZLog.e(TAG, "execute runnable exception: ", e);
        }
    }

}
