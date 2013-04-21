package net.fiveotwo.rfts.core.entities;

import playn.core.Color;
import playn.core.Surface;
import net.fiveotwo.rfts.core.PlanetLander;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.Vector2;

public class StaticTile extends Tile{
	
	public StaticTile(String texture, int collision, String data, int tp){
		super(texture,collision, data,tp);
	}

	@Override
	public void Draw(Surface surf, Vector2 position) {
		if(this.Texture!=null)
	//	surf.drawImage(PlanetLander.Imagenes.get(Texture), position.X, position.Y);
		DebugDraw(surf,position);
	}

	@Override
	public void Update(float f) {
		
	}
	
	public void DebugDraw(Surface surf, Vector2 position){
		surf.setFillColor(Color.rgb(255, 255, 255));
		if(this.Collision==1)
		surf.fillRect(position.X, position.Y, 10, 10);
		surf.setFillColor(Color.rgb(140, 100, 102));
		if(this.Collision==2)
			surf.fillRect(position.X, position.Y, 10, 10);
	}

}
