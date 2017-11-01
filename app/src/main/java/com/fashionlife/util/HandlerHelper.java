package com.fashionlife.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * @author: lovexujh
 * @time: 2017/11/1
 * @descripition:
 */

public class HandlerHelper {

    private static final HandlerThread sHandlerThread = new HandlerThread("fashionlife-handler-thread");

    private static final Handler sHandler = new Handler(sHandlerThread.getLooper());

    public static void start() {
        sHandlerThread.start();
    }

    public static void post(Runnable runnable) {
        if (runnable != null) {
            sHandler.post(runnable);
        }
    }

    public class HandlerCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }


}
