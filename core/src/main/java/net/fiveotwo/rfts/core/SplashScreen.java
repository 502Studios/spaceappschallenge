package net.fiveotwo.rfts.core;

import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.PlayN.LifecycleListener;
import playn.core.util.Callback;
import playn.core.Image;
import static playn.core.PlayN.*;

import net.fiveotwo.rfts.core.utils.Screen;

public class SplashScreen extends Screen{

	private ImageLayer logo,playn, cargando;
	CanvasImage nowloading;
	private boolean done;
	private int counter = 0;
	int ldcounter=0;
	private PlanetLander dig;
	int finalpos=150;
	public SplashScreen(PlanetLander dig){
		this.dig = dig;
	}
	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Init() {
		CanvasImage ci=PlayN.graphics().createImage(800, 480);
		ci.canvas().setFillColor(Color.rgb(46, 52, 54));
		ci.canvas().fillRect(0, 0, 800, 480);
		nowloading=graphics().createImage(300, 20);
		nowloading.canvas().setFillColor(0xffffffff);
		nowloading.canvas().fillText(graphics().layoutText("Now Loading", PlanetLander.format), 0, 0);
		cargando=PlayN.graphics().createImageLayer(nowloading);
		PlayN.setLifecycleListener(new LifecycleListener(){
			@Override
			public void onPause() {
				// TODO Auto-generated method stub
				if (graphics().ctx().quadShader(null) != null) {
					graphics().ctx().quadShader(null).clearProgram();
					}
					if (graphics().ctx().trisShader(null) != null) {
					graphics().ctx().trisShader(null).clearProgram();
					}
			}

			@Override
			public void onResume() {
				
			}

			@Override
			public void onExit() {
				// TODO Auto-generated method stub
				if (graphics().ctx().quadShader(null) != null) {
					graphics().ctx().quadShader(null).clearProgram();
					}
					if (graphics().ctx().trisShader(null) != null) {
					graphics().ctx().trisShader(null).clearProgram();
					}
			}
		});
		PlayN.graphics().rootLayer().add(PlayN.graphics().createImageLayer(ci));
		
		PlayN.graphics().rootLayer().addAt(cargando, 20, 440);	
		
		PlayN.assets().getImage("images/Sprites/gbstart.png").addCallback(new Callback<Image>(){

			@Override
			public void onSuccess(Image result) {
				PlayN.assets().getImage("images/Sprites/logo.png").addCallback(new Callback<Image>(){

					@Override
					public void onSuccess(Image result) {

						// TODO Auto-generated method stub
						playn=PlayN.graphics().createImageLayer(result);	
						playn.setSize(60, 64);
						playn.setTranslation(736, 412);
						playn.setVisible(true);
						PlayN.graphics().rootLayer().add(playn);
						logo.setSize(400, 200);
						logo.setTranslation(200, 150);
						logo.setVisible(true);
						PlayN.graphics().rootLayer().add(logo);
						done = true;
					}

					@Override
					public void onFailure(Throwable cause) {
						// TODO Auto-generated method stub
					}
				});
				logo = PlayN.graphics().createImageLayer(result);	
				
			}

			@Override
			public void onFailure(Throwable cause) {
				// TODO Auto-generated method stub
				
			}
			
		});

		
	}

	@Override
	public void Update(float delta) {
			
		if(ldcounter>20&&ldcounter<40){
			nowloading.canvas().clear();
			nowloading.canvas().fillText(graphics().layoutText("Now Loading.  ", PlanetLander.format), 0, 0);
		}
		if(ldcounter>40&&ldcounter<60){
			nowloading.canvas().clear();
			nowloading.canvas().fillText(graphics().layoutText("Now Loading.. ", PlanetLander.format), 0, 0);
		}
		if(ldcounter>60&&ldcounter<80){
			nowloading.canvas().clear();
			nowloading.canvas().fillText(graphics().layoutText("Now Loading... ", PlanetLander.format), 0, 0);
		}
		if(ldcounter>=80){
			nowloading.canvas().clear();
				nowloading.canvas().fillText(graphics().layoutText("Now Loading ", PlanetLander.format), 0, 0);
				ldcounter=0;
		}
		
		ldcounter++;
		
		if(done){
		counter++;
		if(PlanetLander.watcher.isDone() && PlanetLander.isDone && counter>=50){
			if(logo.alpha()>0){
			logo.setAlpha(logo.alpha()-0.01f);	
			playn.setAlpha(playn.alpha()-0.01f);
			cargando.setAlpha(cargando.alpha()-0.01f);
			}else{
			PlayN.graphics().rootLayer().remove(logo);
		//	dig.activeScreen = dig.mainme;
		//	dig.activeScreen.Init();
			}
		}
		}
	}

	@Override
	public void shutdown() {
		PlayN.graphics().rootLayer().clear();
	}

	@Override
	public void Draw(float delta) {
		
		
	}

}
