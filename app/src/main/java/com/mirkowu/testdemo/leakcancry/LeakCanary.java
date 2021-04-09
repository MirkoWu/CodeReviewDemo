package com.mirkowu.testdemo.leakcancry;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class LeakCanary {
    public static final String TAG = LeakCanary.class.getSimpleName();
    private static volatile LeakCanary INSTANCE;//volatile 保证可见性 和有序性（禁止指令重排序） 读写操作前插入内存屏障，不能把后面的指令重排到前面

    public static LeakCanary getInstance() {
        if (INSTANCE == null) {//提高效率
            synchronized (LeakCanary.class) {//保证原子性
                if (INSTANCE == null) {//二次检测 保证单例
                    INSTANCE = new LeakCanary();//非原子操作
                    //memory =allocate();//1. 分配内存空间
                    //2.初始化对象instance
                    //3.设置instance对象引用指向刚分配的内存空间

                    //3.设置instance对象指向刚分配的内存空间 ,此时对象还未初始化
                    //2.初始化对象instance
                }
            }
        }
        return INSTANCE;
    }

    //    private WeakReference<Activity> mReference;
    private HashMap<Long, WeakReference<Activity>> map = new HashMap<>();
    private ReferenceQueue<Activity> queue = new ReferenceQueue();

    private LeakCanary() {
    }

    public void install(Application application) {
        application.registerActivityLifecycleCallbacks(new LeakActivityLifecycleCallbacks() {
            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d(TAG, "onActivityDestroyed---" + activity);
                watch(activity);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        check();
                    }
                }).start();

            }
        });

    }

    public void watch(Activity activity) {
        WeakReference<Activity> mReference = new WeakReference<>(activity, queue);
        map.put(System.nanoTime(), mReference);
    }

    public boolean check() {
        Reference<? extends Activity> reference;
        if ((reference = queue.poll()) != null) {//说明已经回收
            Log.d(TAG, "第一次回收 成功---" + reference.get());
            return false;
        }
        Runtime.getRuntime().gc();//手动GC，不能保证100%触发

        //再查一次,还没有则算内存泄漏
        if ((reference = queue.poll()) != null) {
            Log.d(TAG, "第二次回收 成功---" + reference.get());
        }
        boolean isLeak = reference == null;

        if (isLeak) {
            Log.d(TAG, "第二次 泄漏---");
        }
        return isLeak;
    }
}
