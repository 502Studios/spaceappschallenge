package net.fiveotwo.rfts.core;

import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.Font.Style;
import playn.core.ImmediateLayer;
import playn.core.Surface;
import net.fiveotwo.rfts.core.entities.Lander;
import net.fiveotwo.rfts.core.utils.AccelInterface;
import net.fiveotwo.rfts.core.utils.Camara;
import net.fiveotwo.rfts.core.utils.KeyboardControl;
import net.fiveotwo.rfts.core.utils.PointerControls;
import net.fiveotwo.rfts.core.utils.Screen;
import net.fiveotwo.rfts.core.utils.TextBox;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.TileMap;
import net.fiveotwo.rfts.core.utils.Vector2;
import net.fiveotwo.rfts.core.utils.terraingenerator.MapGenerator;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.platformType;
public class Map extends Screen{
	ImmediateLayer layer;
	String name;
	Lander landership;
	float gravity;
	Vector2 atmosphereDrag;
	Tile[][] tiles;
	TileMap tileMap;
	Camara cam;
	Lander lnd;
	public AccelInterface Acelerometer;
	public boolean hasInstructions;
	public TextBox txtBox;
	
	CanvasImage velocityY,velY,velocityX, VelX,fuel, gaso, thrusters, heat, gameover;
	float density=1.225f; //air density (but is constant for the moment XD)
	
	public Map(String n, float grav,Vector2 atmdrag, AccelInterface acc){
		this.name=n;
		this.gravity=grav;
		this.atmosphereDrag=atmdrag;
		this.Acelerometer=acc;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public void Init() {
		cam=new Camara(this, new Vector2(400,300));
		tiles=new Tile[400][200];
		TileMap generated=new MapGenerator().GenerateMap(400, 200, 0, 1000,name());
		tiles=generated.getTilemap();
		tileMap=new TileMap(1024, 600, tiles);
		hasInstructions=true;
		txtBox=new TextBox(800, 480, 1, PlanetLander.Imagenes.get("Layer1_1"));
		txtBox.setTextFormat("PressStart", Style.PLAIN, 16);
		txtBox.setMargins(75, 50);
		txtBox.setText("On July 20, 1969 the lunar module (LM) Eagle separated from the command module Columbia.\n Collins,  alone aboard Columbia, inspected Eagle as it pirouetted before him to ensure the craft was not damaged. \n As the descent began, Armstrong and Aldrin found that they were passing landmarks \n on the surface 4 seconds early and reported that they were long; \n they would land miles west of their target point.", Color.rgb(255, 255, 255), Color.rgb(255, 255, 255), 1);
		txtBox.setTextWrapping(800);
		lnd=new Lander(this, 100, 900, new Vector2(0,0),new Vector2(200,40), name);
		layer = graphics().createImmediateLayer(800,480, new ImmediateLayer.Renderer() {
			@Override
			public void render(Surface surface) {
				if(PlanetLander.isDone){
					surface.clear();
					if(hasInstructions){
						txtBox.drawTextBox(surface, new Vector2(0,0));
					}else{
					if(!lnd.hasCrashed()){
						surface.clear();
						tileMap.DrawTiles(surface);
						lnd.draw(surface, tileMap.getOffsets());
						DrawHUD(surface);
					}else{
						surface.clear();
						surface.drawImage(gameover, 800-gameover.width()-80, 240);
					}
				}
				}
			}
		});
		new PointerControls(this);
		if(platformType() == platformType().ANDROID){
			
		}
		new KeyboardControl(this);
		graphics().rootLayer().add(layer);
		
		velocityY = graphics().createImage(("Veloctiy Y:".length()) * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
		velocityY.canvas().setFillColor(0xffffffff);
		velocityY.canvas().setStrokeWidth(2);
		velocityY.canvas().setStrokeColor(0xff000000);
		velocityY.canvas().fillText(graphics().layoutText("Velocity Y:", PlanetLander.format), 0,0);
		
		velocityX = graphics().createImage(("Velocity X:".length()) * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
		velocityX.canvas().setFillColor(0xffffffff);
		velocityX.canvas().setStrokeWidth(2);
		velocityX.canvas().setStrokeColor(0xff000000);
		velocityX.canvas().fillText(graphics().layoutText("Velocity X:", PlanetLander.format), 0,0);
		
		fuel = graphics().createImage(("Fuel:".length()) * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
		fuel.canvas().setFillColor(0xffffffff);
		fuel.canvas().setStrokeWidth(2);
		fuel.canvas().setStrokeColor(0xff000000);
		fuel.canvas().fillText(graphics().layoutText("Fuel:", PlanetLander.format), 0,0);
		
		thrusters = graphics().createImage(("Altitude ".length()) * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
		thrusters.canvas().setFillColor(0xffffffff);
		thrusters.canvas().setStrokeWidth(2);
		thrusters.canvas().setStrokeColor(0xff000000);
		thrusters.canvas().fillText(graphics().layoutText("Altitude:", PlanetLander.format), 0,0);
		
		gameover = graphics().createImage(("You crashed the lander, touch to try again! ".length()) * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
		gameover.canvas().setFillColor(0xffffffff);
		gameover.canvas().setStrokeWidth(2);
		gameover.canvas().setStrokeColor(0xff000000);
		gameover.canvas().fillText(graphics().layoutText("You crashed the lander, touch to try again!", PlanetLander.format), 0,0);
		
	}
	
	void DrawHUD(Surface surf){
		surf.drawImage(velocityY, 10, 10);
			velY = graphics().createImage(String.valueOf(lnd.getVel().Y).length() * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
			velY.canvas().setFillColor(0xffffffff);
			velY.canvas().setStrokeWidth(2);
			velY.canvas().setStrokeColor(0xff000000);
			velY.canvas().fillText(graphics().layoutText(String.valueOf(lnd.getVel().Y), PlanetLander.format), 0,0);
		surf.drawImage(velY, 160, 10);

		surf.drawImage(velocityX, 10, 28);
			velY = graphics().createImage(String.valueOf(lnd.getVel().X).length() * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
			velY.canvas().setFillColor(0xffffffff);
			velY.canvas().setStrokeWidth(2);
			velY.canvas().setStrokeColor(0xff000000);
			velY.canvas().fillText(graphics().layoutText(String.valueOf(lnd.getVel().X), PlanetLander.format), 0,0);
			surf.drawImage(velY, 160, 28);
		surf.drawImage(fuel, 10, 46);
			gaso = graphics().createImage(String.valueOf(lnd.getFuel()).length() * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
			gaso.canvas().setFillColor(0xffffffff);
			gaso.canvas().setStrokeWidth(2);
			gaso.canvas().setStrokeColor(0xff000000);
			gaso.canvas().fillText(graphics().layoutText(String.valueOf(lnd.getFuel()), PlanetLander.format), 0,0);
			surf.drawImage(gaso, 160, 46);
		surf.drawImage(thrusters, 10, 64);
			heat = graphics().createImage(String.valueOf(lnd.getAltitude()).length() * PlanetLander.format.font.size(),PlanetLander.format.font.size()*1.5f);
			heat.canvas().setFillColor(0xffffffff);
			heat.canvas().setStrokeWidth(2);
			heat.canvas().setStrokeColor(0xff000000);
			heat.canvas().fillText(graphics().layoutText(String.valueOf(lnd.getAltitude()), PlanetLander.format), 0,0);
		surf.drawImage(heat, 150, 64);
	}
	
	void DrawMiniMap(Surface surf){
		
	}

	@Override
	public void Update(float delta) {
		if(!hasInstructions){
			if(!lnd.hasCrashed()){
				cam.setPosition(lnd.getPosition());
				tileMap.UpdateOffsets(cam);
				lnd.update(delta);
			}else{
				
			}
			}
	}

	@Override
	public void shutdown() {
		this.tileMap=null;
		this.tiles=null;
		this.layer.destroy();
		lnd=null;
	}

	@Override
	public void Draw(float delta) {
		
	}
	
	public float getGravity(){return this.gravity;}
	public Camara getCam(){return this.cam;}
	public TileMap getTileMap(){return this.tileMap;}
	public Lander getLander(){return this.lnd;}
	
	public float getDensity(){return this.density;}
	public void setDensity(float d){this.density=d;}

}
