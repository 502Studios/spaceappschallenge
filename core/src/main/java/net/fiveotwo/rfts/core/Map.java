package net.fiveotwo.rfts.core;

import playn.core.ImmediateLayer;
import playn.core.Surface;
import net.fiveotwo.rfts.core.entities.Lander;
import net.fiveotwo.rfts.core.utils.Camara;
import net.fiveotwo.rfts.core.utils.Screen;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.TileMap;
import net.fiveotwo.rfts.core.utils.Vector2;
import net.fiveotwo.rfts.core.utils.terraingenerator.MapGenerator;
import static playn.core.PlayN.graphics;
public class Map extends Screen{
	ImmediateLayer layer;
	String name;
	Lander landership;
	float gravity;
	Vector2 atmosphereDrag;
	Tile[][] tiles;
	TileMap tileMap;
	Camara cam;
	public Map(String n, float grav,Vector2 atmdrag){
		this.name=n;
		this.gravity=grav;
		this.atmosphereDrag=atmdrag;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public void Init() {
		cam=new Camara(this, new Vector2(0,599));
		tiles=new Tile[1200][200];
		TileMap generated=new MapGenerator().GenerateMap(1200, 200, 0, 500);
		tiles=generated.getTilemap();
		tileMap=new TileMap(1024, 600, tiles);
		
		layer = graphics().createImmediateLayer(1024,600, new ImmediateLayer.Renderer() {
			@Override
			public void render(Surface surface) {
				if(PlanetLander.isDone){
					surface.clear();
					tileMap.DrawTiles(surface);
				}
			}

		});
		
		graphics().rootLayer().add(layer);
		
	}

	@Override
	public void Update(float delta) {
		cam.setPosition(new Vector2(cam.Position().X+10,cam.Position().Y+1));
		tileMap.UpdateOffsets(cam);
	}

	@Override
	public void shutdown() {
		
	}

	@Override
	public void Draw(float delta) {
		
	}

}
