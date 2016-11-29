package fr.tommarx.gameengine.Components;

import fr.tommarx.gameengine.Game.Drawable;
import fr.tommarx.gameengine.Game.GameObject;

public abstract class Component extends Drawable {

   private GameObject go;

   protected GameObject getGameObject(){
      return go;
   }

   public abstract void update();

}
