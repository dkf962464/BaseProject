package com.rocky.googleadview.applicationview;

import com.rocky.googleadview.callinterface.FixedThreadInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThread {
    private static ExecutorService mFixedThreadPool;
    public static void getThread(final FixedThreadInterface threadInterface) {
        if (null == mFixedThreadPool) {
            mFixedThreadPool = Executors.newFixedThreadPool(3);
        }
        mFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
               threadInterface.run();
            }
        });
    }
}
