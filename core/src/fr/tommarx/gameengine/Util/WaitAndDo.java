package fr.tommarx.gameengine.Util;

import java.util.concurrent.Callable;


public class WaitAndDo {

    public static void WaitAndDo(final float time, final Callable callable) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                double timeB = System.currentTimeMillis();
                while (true) {
                    if (System.currentTimeMillis() - timeB >= time * 1000) {
                        try {
                            callable.call();
                            Thread.currentThread().interrupt();
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t.start();
    }

}
