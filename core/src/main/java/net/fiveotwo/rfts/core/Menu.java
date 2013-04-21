package net.fiveotwo.rfts.core;

import net.fiveotwo.rfts.core.entities.Boton;
import net.fiveotwo.rfts.core.utils.Screen;
import net.fiveotwo.rfts.core.utils.TextBox;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.Vector2;

import java.util.ArrayList;
import java.util.List;
import playn.core.*;
import playn.core.Font.Style;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN.LifecycleListener;
import playn.core.util.Callback;
import static playn.core.PlayN.*;

public class Menu extends Screen {
	PlanetLander planetlander;
	List<Boton> buttons = new ArrayList<Boton>();
	List<MenuImage> imagenes = new ArrayList<MenuImage>();
	String menuname, name, upperlevel;
	private ImmediateLayer layer;
	MenuLoader ml;
	Image bg,b2;
	public TextBox txtBox;
	CanvasImage title;

	public TextFormat tformat = new TextFormat().withFont(graphics()
			.createFont("PressStart2P", Font.Style.PLAIN, 40));
	boolean DoneLoading = false;
	public final AssetWatcher watcher = new AssetWatcher();

	public Menu(PlanetLander planetlander, String name) {
		this.planetlander = planetlander;
		this.menuname = name;
	}

	@Override
	public String name() {
		return this.menuname;
	}

	public void LoadEverything(String data) {
		ml = new MenuLoader(this, data);
	}

	@Override
	public void Init() {
		bg = PlanetLander.Imagenes.get("Layer1_1");
		b2 = PlanetLander.Imagenes.get("Layer1_1");
		buttons.clear();
		title = graphics().createImage(
		("Choose a level".length()) * tformat.font.size() ,
		tformat.font.size() );
		title.canvas().setFillColor(0xffffffff);
		title.canvas().setStrokeWidth(10);
		title.canvas().setStrokeColor(0xffffffff);
		title.canvas().fillText(graphics().layoutText("Choose a level", tformat),
		0, 0);
		assets().getText("jsons/" + this.menuname + ".json",
				new Callback<String>() {
					@Override
					public void onSuccess(String resource) {
						LoadEverything(resource);
										DoneLoading = true;
									}

					@Override
					public void onFailure(Throwable err) {
					}
				});
		pointer().setListener(new Pointer.Listener() {
			@Override
			public void onPointerStart(Pointer.Event event) {
				// TODO Auto-generated method stub
				for (int i = 0; i < buttons.size(); i++) {
					buttons.get(i).Touched(
							PlanetLander.FingerRectangle(event.x()/PlanetLander.rx, event.y()/PlanetLander.ry));
				}
			}

			@Override
			public void onPointerEnd(Pointer.Event event) {
//				for (int i = 0; i < buttons.size(); i++) {
//					if (buttons.get(i).wasTouched(
//							PlanetLander.FingerRectangle(event.x()/PlanetLander.rx, event.y()/PlanetLander.ry))
//							&& buttons.get(i).isEnabled()) {
						planetlander.activeScreen = new Map("Luna",1.6f,new Vector2(0,0),PlanetLander.acelerometer);
						planetlander.activeScreen.Init();
//					}
//				}
			}

			@Override
			public void onPointerDrag(Pointer.Event event) {
			}

			@Override
			public void onPointerCancel(playn.core.Pointer.Event event) {
				// TODO Auto-generated method stub
				
			}
		});
		graphics().rootLayer().clear();
		
		layer = graphics().createImmediateLayer(800,480, new ImmediateLayer.Renderer() {
					public void render(Surface surf) {
						surf.clear();
						surf.drawImage(bg, 0, 0, 800,480);						
						for (int i = 0; i < buttons.size(); i++) {
							buttons.get(i).Draw(surf);
						}
						for (int i = 0; i < imagenes.size(); i++) {
							surf.drawImage(imagenes.get(i).imagen,
									imagenes.get(i).x, imagenes.get(i).y);
						}
//						surf.drawImage(b2, 0, 0, 800,480);
						surf.drawImage(title, 400-title.width()/2,30);
					}
				});
		keyboard().setListener(new Keyboard.Listener() {

			@Override
			public void onKeyDown(Event event) {
				// TODO Auto-generated method stub
				if (event.key() == Key.BACK||event.key()==Key.ESCAPE) {

				}
			}

			@Override
			public void onKeyTyped(TypedEvent event) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onKeyUp(Event event) {
				// TODO Auto-generated method stub
			}
			
		});
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

		
		graphics().rootLayer().add(layer);
	}

	@Override
	public void Update(float delta) {
	}

	@Override
	public void shutdown() {
		graphics().rootLayer().clear();
	}

	@Override
	public void Draw(float delta) {
	}
}
