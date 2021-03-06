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

import android.util.Log;

@SuppressWarnings("unused")
public class ZLog {

    private static final String PRE_FIX = "ZhiHu-";

    private static boolean debug = true;

    private static String addPreFix(String tag) {
        return PRE_FIX + tag;
    }

    public static void d(String tag, String msg) {
        if (debug) Log.d(addPreFix(tag), msg);
    }

    public static void w(String tag, String msg) {
        if (debug) Log.w(addPreFix(tag), msg);
    }

    public static void e(String tag, String msg) {
        if (debug) Log.e(addPreFix(tag), msg);
    }

    public static void e(String tag, String msg, Exception e) {
        if (debug) Log.e(addPreFix(tag), msg, e);
    }

    public static void setDebug(boolean on) {
        debug = on;
    }
}
