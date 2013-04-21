package net.fiveotwo.rfts.core;

import net.fiveotwo.rfts.core.entities.Boton;
import playn.core.AssetWatcher;
import playn.core.Font;
import playn.core.Image;
import playn.core.ImmediateLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.PlayN;
import playn.core.TextFormat;
import playn.core.Keyboard.TypedEvent;
import playn.core.Platform.Type;
import playn.core.PlayN.LifecycleListener;
import playn.core.Pointer;
import playn.core.Surface;
import playn.core.Pointer.Event;

import net.fiveotwo.rfts.core.utils.Screen;
import net.fiveotwo.rfts.core.utils.Vector2;

import static playn.core.PlayN.*;

public class MainMenu extends Screen {

	private ImmediateLayer layer;
	Boton continuar, options, exit, editor,heyzap;
	boolean doneLoading = false;
	PlanetLander planet;
	Image titulo, fondo,copy;
	public TextFormat tformat;
	public final AssetWatcher watcher = new AssetWatcher();

	public MainMenu(PlanetLander planet) {
		this.planet = planet;
	}

	@Override
	public String name() {
		return "Menu";
	}
	
	@Override
	public void Init() {
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
	

		
		layer = graphics().createImmediateLayer(800,480,new ImmediateLayer.Renderer() {

					public void render(Surface surf) {
						if (PlanetLander.watcher.isDone() && PlanetLander.isDone) {
							surf.clear();
							surf.drawImage(PlanetLander.Imagenes.get("Layer1_1"), 0, 0);
							surf.drawImage(PlanetLander.Imagenes.get("title"), 160 ,40, 480, 220);
						//	surf.drawImage(copy, 0, 440);
							continuar.Draw(surf);
						}
					}
				});
		graphics().rootLayer().clear();
		graphics().rootLayer().add(layer);
		watcher.start();
										tformat = new TextFormat().withFont(graphics()
												.createFont("PressStart", Font.Style.PLAIN, 40));
										// TODO Auto-generated method stub
										continuar = new Boton(
												PlanetLander.Imagenes.get("bnorm"),
												PlanetLander.Imagenes
														.get("bnormpick"),
												"Press to Continue",
												new Vector2(160, 40),
												new Vector2(330,
														320));

										
										doneLoading = true;
									
										keyboard().setListener(new Keyboard.Listener() {
										@Override
										public void onKeyUp(playn.core.Keyboard.Event event) {
											
										}
										
										@Override
										public void onKeyTyped(TypedEvent event) {
											
										}
										
										@Override
										public void onKeyDown(playn.core.Keyboard.Event event) {
											
										}
									});


		pointer().setListener(new Pointer.Listener() {

			@Override
			public void onPointerStart(Event event) {

				continuar.Touched(PlanetLander.FingerRectangle(event.x()/PlanetLander.rx, event.y()/PlanetLander.ry));
			
			}

			@Override
			public void onPointerEnd(Event event) {
					planet.activeScreen = new Menu(planet,"MainMenu");
					planet.activeScreen.Init();

			}

			@Override
			public void onPointerDrag(Event event) {
			}

			@Override
			public void onPointerCancel(Event event) {
				
			}
		});
	}

	@Override
	public void shutdown() {
		graphics().rootLayer().clear();
		layer.destroy();
		layer = null;
	}

	@Override
	public void Update(float delta) {
		
	}

	@Override
	public void Draw(float alpha) {
		
	}


	
}
