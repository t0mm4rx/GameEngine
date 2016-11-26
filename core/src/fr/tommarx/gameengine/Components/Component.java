package fr.tommarx.gameengine.Components;

import fr.tommarx.gameengine.Game.GameObject;

public abstract class Component {

   private GameObject go;

   protected GameObject getGameObject(){
      return go;
   }

   public abstract void render();
   public abstract void renderInHUD();
   public abstract void update();

}
