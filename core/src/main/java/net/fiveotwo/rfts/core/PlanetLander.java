package net.fiveotwo.rfts.core;

import static playn.core.PlayN.*;

import java.util.HashMap;

import playn.core.AssetWatcher;
import playn.core.Game;
import playn.core.Font;
import playn.core.Image;
import playn.core.Json;
import playn.core.Sound;
import playn.core.TextFormat;
import playn.core.util.Callback;

import playn.core.gl.*;

import net.fiveotwo.rfts.core.utils.AccelInterface;
import net.fiveotwo.rfts.core.utils.Animation;
import net.fiveotwo.rfts.core.utils.AnimationPlayer;
import net.fiveotwo.rfts.core.utils.LeapInterface;
import net.fiveotwo.rfts.core.utils.Rectangle;
import net.fiveotwo.rfts.core.utils.Screen;
import net.fiveotwo.rfts.core.utils.TexturePacker;
import net.fiveotwo.rfts.core.utils.Vector2;

public class PlanetLander implements Game {
	
	
	public static TextFormat format;
	public Animation anm;
	public static int updateRate;
	public SplashScreen splash;
	public static boolean godmode = true;
	public static boolean activesound = true;
	public static float rx,ry=1;
	public static Rectangle fingerBounds;
	public AnimationPlayer sprite = new AnimationPlayer();
	Vector2 loadsp = new Vector2(0, 0);
	public  Screen activeScreen;
	public Menu menu;
	static float WIDTH,HEIGHT;
	public MainMenu mainme;
	public Map map;
	LeapInterface leap;
	public static AccelInterface acelerometer;
	private static HashMap<String, Screen> screens = new HashMap<String, Screen>();
	public static HashMap<String, playn.core.Image> Imagenes = new HashMap<String, playn.core.Image>();
	public static HashMap<String, playn.core.Sound> Sonidos = new HashMap<String, playn.core.Sound>();
	public static AssetWatcher watcher = new AssetWatcher();
	public static Boolean isDone = false;
	
	
	
  @Override
  public void init() {
    // create and add background image layer
    Image bgImage = assets().getImage("images/bg.png");
   // leap.test();
	WIDTH = 800;
	HEIGHT = 480;
	if(platformType() == platformType().ANDROID){
		graphics().ctx().setTextureFilter(GLContext.Filter.LINEAR, GLContext.Filter.LINEAR);
		graphics().rootLayer().setScale(graphics().screenWidth()/WIDTH, graphics().screenHeight()/HEIGHT);	
	}
	
    format = new TextFormat().withFont(graphics().createFont("PressStart2P", 
    		Font.Style.PLAIN, 14));
	splash = new SplashScreen(this);
	map = new Map("Moon",1.6f,new Vector2(20,0),PlanetLander.acelerometer);
	menu = new Menu(this,"MainMenu.json");
	mainme = new MainMenu(this);
	int width = (10);
	int left = 0;
	int height = (10);
	int top = 0;
	fingerBounds = new Rectangle(left, top, width, height, 1);
	activeScreen = splash;
	isDone = true;
	activeScreen.Init();
    assets().getText("jsons/Sounds.json",
			new Callback<String>() {
				@Override
				public void onSuccess(String result) {
					loadLevels(result);
					assets().getText("jsons/SpaceApps.json",
							new Callback<String>() {
								@Override
								public void onSuccess(String result) {
									LoadEverything(result);
									isDone = true;
								}
								@Override
								public void onFailure(Throwable cause) {
									log().debug("Error en la carga de datos interior",cause);
									
								}
							});
				}

				@Override
				public void onFailure(Throwable cause) {
					// TODO Auto-generated method stub
					
				}
			});
    
  }
  
	public static Rectangle FingerRectangle(float x, float y) {
		int left = (int) (x - 10);
		int top = (int) (y - 10);
		return new Rectangle(left, top, fingerBounds.Bottom(),
				fingerBounds.Right(), 1);
	}
  
	public void loadLevels(String json) {
		Json.Object niveles = json().parse(json);
		Json.Array arraysonidos = niveles.getArray("Sounds");
		for (int i = 0; i < arraysonidos.length(); i++) {
			Sound s = assets().getSound(arraysonidos.getObject(i).getString("url"));
			watcher.add(s);
			Sonidos.put(arraysonidos.getObject(i).getString("name"), s);
		}
		Json.Array arraymusica = niveles.getArray("Music");
		for (int i = 0; i < arraymusica.length(); i++) {
			Sound s = assets().getMusic(arraymusica.getObject(i).getString("url"));
			watcher.add(s);
			Sonidos.put(arraymusica.getObject(i).getString("name"), s);
		}
	}
	
	
	public PlanetLander(){
		
	}
	
	public PlanetLander(AccelInterface accelint){
		acelerometer = accelint;
	}
	
	public PlanetLander(LeapInterface leapint){
		leap = leapint;
	}
	
	public void LoadEverything(String json) {
		TexturePacker tp = new TexturePacker(watcher,"images/Sprites/");
		Imagenes = tp.ParseData(json);
		watcher.start();
//		anm = new Animation(Imagenes.get("push_loading"), 6f, 80f, true);
//		sprite.PlayAnimation(anm);
//		//loadsp = new Vector2(800 - anm.FrameWidth(), 480 - anm.FrameHeight());
	}

  @Override
  public void paint(float alpha) {
    
	  
  }

  @Override
  public void update(float delta) {
		activeScreen.Update(delta);
		updateRate = updateRate();
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
