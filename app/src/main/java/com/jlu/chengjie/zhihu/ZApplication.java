package com.jlu.chengjie.zhihu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.lang.ref.WeakReference;

@SuppressWarnings("unused")
public class ZApplication extends Application {

    private static WeakReference<Context> contextHolder;

    private static WeakReference<Activity> currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        contextHolder = new WeakReference<>(getApplicationContext());
        registerActivityLifecycleCallbacks(callbacks);
    }

    private ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    };

    public static Activity getCurrentActivity() {
        return currentActivity.get();
    }

    public static Context getContextHolder() {
        return contextHolder.get();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
