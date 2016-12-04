# GameEngine
## Initialisation
Create a LibGDX project (via the setup tool), and then just add the engine jar.

**Make sure that you have the universal tween engine and the FreeType extension added to your project**
## Game class
The structure of an empty game class is the following :
```java
package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Game;

public class GameClass extends Game {

	public void create () {
		init();
	}

	
	public void dispose () {
		stop();
	}
}


```
## Screen
The structure of an empty game screen is the following :
```java
package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Screen;

public class ScreenTest extends Screen {

    public void show() {
        //Do initialisation stuff
    }

}
```
And you have to set the game class's screen to ScreenTest :
```java
	public void create () {
		//Very important
        init();
        setScreen(new ScreenTest());
	}
```
##Game objects
Screen contains game objects, wich contains components (ex: sprite renderer, collider, transform, animation...).
To add a game object to a screen :
```java
public class ScreenTest extends Screen {

    GameObject go;

    public void show() {
        //This game object is added in the screen
        add(new Player(new Transform(new Vector2(300,300))));
        //This one is added in the HUD, it will not follow the game camera
        addInHUD(new LifeCounter(new Transform(new Vector2(10, 10))));
    }

}
```
The player class :
```java
package fr.tommarx.gameenginetest;

public class Player extends GameObject {

    BoxBody body;

    public Player(Transform transform) {
        super(transform);
        addComponent(new SpriteRenderer(this, new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")))));
        body = new BoxBody(this, BodyDef.BodyType.DynamicBody);
        addComponent(body);
    }

    protected void update(float delta) {

        Game.debug(3, "Velx : " + body.getBody().getLinearVelocity().x);
        Game.debug(4, "Vely : " + body.getBody().getLinearVelocity().y);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.getBody().setLinearVelocity(new Vector2(200, body.getBody().getLinearVelocity().y));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.getBody().setLinearVelocity(new Vector2(-200, body.getBody().getLinearVelocity().y));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            body.getBody().setLinearVelocity(new Vector2(body.getBody().getLinearVelocity().x, 200));
        }

    }
}

```
##Debug mode
A debug mode is available. To enable it, set Game.debugging to true. You can log informations in the left-upper corner with the Game.debug(int line, String message) method.
Debug mode will show the Box2D bodies (usefull to check the colliders).
Exemple (in a screen) :
```java
        public void update() {
            //Log DEBUG MODE on first line and FPS on the second
            Game.debug(1, "DEBUG MODE");
            Game.debug(2, "FPS : " + Gdx.graphics.getFramesPerSecond());
            //Enable/disable debug mode when pressing D
            if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                Game.debugging = !Game.debugging;
            }
        }
```
##Layouts
Layouts can be used to render game objects by priority. Exemple :
```java
    EmptyGameObject background = new EmptyGameObject(new Transform(new Vector2(100, 100)));
    background.addComponent(new SpriteRenderer(background, new TextureRegion(new Texture(Gdx.files.internal("background.jpg")))));
    background.setLayout(0);
    //We can also set the scrolling speed to create a parallax effect
    background.setScrollingSpeed(0.2);
    add(background);
    EmptyGameObject player = ...;
    player.setLayer(1);
    //Player will be rendered after the background so background will be behind.
```
##Tweening (aka interpolation or easing)
The game engine has an integrate tweening system. The Game class has a TweenManager, wich can be call to launch tweens :
```java
    EmptyGameObject text;
    public void show() {
        //Creating a text game object
        text = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        text.addComponent(new Text(text, "Tweening demo !", Color.WHITE));
        add(text);
        //Set the alpha to 0
        ((Text) text.getComponentByClass("Text")).getColor().a = 0f;
        //Create a tween named "Alpha", with start value of 0, wich change to 1, in 3 seconds, no delay and doesn't repeat
        Game.tweenManager.goTween(new Tween("Alpha", Tween.LINEAR_EASE_NONE, 0f, 1f, 3f, 0f, false));
        //Create a second tween named "Alpha" also, wich starts from 1 and change in direction of -1, in 3 seconds and with 3 seconds of delay
        Game.tweenManager.goTween(new Tween("Alpha", Tween.LINEAR_EASE_NONE, 1f, -1f, 3f, 3f; false));
        Game.tweenManager.goTween(new Tween("Y", Tween.CUBE_EASE_INOUT, 0, 1f, 2f, 0f, false));
    }

    public void update() {
        //Set the alpha of the text by the "Alpha" tween value
        ((Text) text.getComponentByClass("Text")).getColor().a = Game.tweenManager.getValue("Alpha");
        //Set the y of the text by the "Y" tween
        text.getTransform().getPosition().y = Game.tweenManager.getValue("Y") * Gdx.graphics.getHeight() / 2;
    }
```
##WaitAndDo
You can use the Game.waitAndDo(float waitFor, Callable action) function to wait an amount of time before doing an action
##Lights
Screen have an rayHandler object, wich can be activate with the method isLightsEnabled(boolean b). Next you can use the Box2DLights stuffs.
To change the ambiance light :
```java
    isLightsEnabled(true);
    rayHandler.setAmbientLight(1f, 1f, 1f, .03f);
```
##Components
Components are small pieces of code that will animate game objects.
Game objects have just one component by default : the transform. Transform contains location, scale and rotation of the game obect.
To add a component to a game object :
```java
        //Creating a game object to location 300,300 with a scale of 0.5 and a rotation of 35
        EmptyGameObject go = new EmptyGameObject(new Transform(new Vector2(300, 300), new Vector2(0.5f,0.5f), 35));
        //Add a sprite renderer wich allow to draw images
        go.addComponent(new SpriteRenderer(go, new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")))));
        //Add a box body, wich listen collisions
        go.addComponent(new BoxBody(go, BodyDef.BodyType.DynamicBody));
        //Add the game object to the screen
        add(go);
```
You can also create your own component. You just have to create a class that extends Component and include all component's methods.
###All components
####Transform
The default component. Do nothing but contains location, scale and rotation. If you want to move, rotate or scale your gameobject, you need call Transform functions.
```java
public Transform (Vector2 position, Vector2 scale, float rotation) {}
public Transform (Vector2 position) {}
public Transform () {}
```
####Sprite renderer
Sprite renderer is the component that display image.
```java
public SpriteRenderer (GameObject go, TextureRegion texture) {}
```
####Bodies
Bodies listen for collisions and add the game object to the physics engine (Box2D).
2 types of bodies : BoxBody and CircleBody
```java
public BoxBody(GameObject go, float width, float height, BodyDef.BodyType bodyType) {}
public CircleBody(GameObject go, float radius, BodyDef.BodyType bodyType) {}
//Those constructors works only if the gameobject has a sprite renderer
public BoxBody(GameObject go, BodyDef.BodyType bodyType) {}
public CircleBody(GameObject go, BodyDef.BodyType bodyType) {}
```
####Animation manager
Animation manager will contain all animations that the game object require. The game object must have a sprite renderer to use the animation manager. Exemple :
```java
        //In a game object extending class
        //Adding a sprite renderer (required for use the animation manager)
        addComponent(new SpriteRenderer(this, new TextureRegion(new Texture(Gdx.files.internal("player_default.jpg")))));
        //Adding the component to the game object
        animManager = new AnimationManager(this);
        addComponent(animManager);
        //Adding the 'walk_left' animation to the animation manager, with the id 0
        //3rd and 4th params are the cols and the rows of the spritesheet, the 5th is the speed and the last one is the looping option
        animManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("walk_left.png")), 6, 5, 0.025f, true), 0);
        //Adding the 'walk_right' animation to the animation manager, with the id 1
        animManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("walk_right.png")), 6, 5, 0.025f, true), 1);
        //Setting the current animation to the walk_left animation
        animManager.setCurrentAnimation(0);
```
####Text
Text displays text with BitmapFont.
```java
public Text(GameObject go, String text, Color color) {}
public Text(GameObject go, FileHandle fontFile, String text, Color color) {}
```
####Box renderer
A box renderer simply draw a rectangle of a given color :
```java
public BoxRenderer(GameObject go, float width, float height, Color color) {}
```
####Point light
A light that difuses all around it.
```java
public PointLight(GameObject go, int power, int length, Color color, RayHandler rayHandler) {}
```
####Cone light
A light that diffuse in a cone shape (usefull for lamp) :
```java
public ConeLight(GameObject go, int power, int length, Color color, RayHandler rayHandler, float angle) {}
```