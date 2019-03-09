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

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.jlu.chengjie.zhihu.ZApplication;

@SuppressWarnings("all")
public class ToastHelper {

    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void toastSafe(int id) {
        toastSafe(id, true);
    }

    public static void toastSafe(int id, boolean longToast) {
        toastSafe(ZApplication.getContextHolder().getString(id), longToast);
    }

    public static void toastSafe(String msg) {
        toastSafe(msg, true);
    }

    public static void toastSafe(String msg, boolean longToast) {
        toast(msg, longToast);
    }

    private static void toast(String msg, boolean longToast) {
        handler.post(() -> Toast.makeText(ZApplication.getContextHolder(), msg,
                longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show());
    }
}
