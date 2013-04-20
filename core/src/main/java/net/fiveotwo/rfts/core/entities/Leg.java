package net.fiveotwo.rfts.core.entities;

import playn.core.Surface;
import net.fiveotwo.rfts.core.utils.Rectangle;
import net.fiveotwo.rfts.core.utils.Vector2;

public class Leg {
	Rectangle BBox;
	Vector2 Position;
	boolean broken;
	String name;
	
	public Leg(Vector2 position, String img){
		
	}
	
	public void Update(float delta){
		
	}
	
	public void Draw(Surface surf, Vector2 offsets){
		DebugDraw(surf,offsets);
	}
	
	public void setBroken(){this.broken=true;}
	public boolean isBroken(){return this.broken;}
	
	public Rectangle boundingBox(){
		int left = (int) (Math.round(getPosition().X) + BBox.Left);
		int top = (int) (Math.round(getPosition().Y) + BBox.Top);
		return new Rectangle(left, top, BBox.Width, BBox.Height, 1);
	}
	
	public Vector2 getPosition(){
		return this.Position;
	}
	public void setBounds(Rectangle b){this.BBox=b;}
	public void setPosition(Vector2 pos){
		this.Position=pos;
	}
	void DebugDraw(Surface surf, Vector2 offsets){
		
	}
}
