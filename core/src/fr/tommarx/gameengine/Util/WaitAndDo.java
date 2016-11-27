package fr.tommarx.gameengine.Util;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class WaitAndDo {

    private ArrayList<WaitAndDoAction> actions;

    public WaitAndDo () {
        actions = new ArrayList<WaitAndDoAction>();
    }

    public void WaitAndDo(float time, Callable callable) {
        actions.add(new WaitAndDoAction(time, callable));
    }

    public void update() {
        ArrayList<WaitAndDoAction> actionsToDelete = new ArrayList<WaitAndDoAction>();
        for (WaitAndDoAction action : actions) {
            if (System.currentTimeMillis() - action.timeB >= action.time * 1000) {
                try {
                    action.callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                actionsToDelete.add(action);
            }
        }
        actions.removeAll(actionsToDelete);
    }

}
