package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collections;

import fr.tommarx.gameengine.Components.Component;
import fr.tommarx.gameengine.Game.Drawable;

public class LayoutSorter {

    public static ArrayList<Drawable> sortByLayout(ArrayList<Drawable> target) {
        ArrayList<Integer> zindexes = new ArrayList<Integer>();
        ArrayList<Drawable> finalList = new ArrayList<Drawable>();

        for (Drawable d : target) {
            if (!zindexes.contains(d.getLayout())) {
                zindexes.add(d.getLayout());
            }
        }

        Collections.sort(zindexes);

        for (int z : zindexes) {
            for (Drawable d : target) {
                if (d.getLayout() == z) {
                    finalList.add(d);
                }
            }
        }

        zindexes.clear();
        return finalList;
    }

    public static ArrayList<Component> sortComponentsByLayout(ArrayList<Component> target) {
        ArrayList<Integer> zindexes = new ArrayList<Integer>();
        ArrayList<Component> finalList = new ArrayList<Component>();

        for (Component d : target) {
            if (!zindexes.contains(d.getLayout())) {
                zindexes.add(d.getLayout());
            }
        }

        Collections.sort(zindexes);

        for (int z : zindexes) {
            for (Component d : target) {
                if (d.getLayout() == z) {
                    finalList.add(d);
                }
            }
        }

        zindexes.clear();
        return finalList;
    }

}
