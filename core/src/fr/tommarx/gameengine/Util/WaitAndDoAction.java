package fr.tommarx.gameengine.Util;

import java.util.concurrent.Callable;

public class WaitAndDoAction {

    public Callable callable;
    public float time;
    public double timeB;

    public WaitAndDoAction (float time, Callable callable) {
        this.callable = callable;
        this.time = time;
        timeB = System.currentTimeMillis();
    }

}
