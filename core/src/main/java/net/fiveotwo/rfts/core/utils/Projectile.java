package net.fiveotwo.rfts.core.utils;

import playn.core.Surface;

public abstract class Projectile {
	public Animation up,down,left,right,kill;
	public AnimationPlayer amp = new AnimationPlayer();
	boolean removeme;
	int strenght;
	int direction;
	Rectangle Bounds;
	Vector2 Position;
	public abstract void loadContent();
	public abstract void update(float delta);
	public abstract void draw(Surface surf, Vector2 offsets);
	public abstract void action();
	public Vector2 getPosition(){
		return this.Position;
	}
	public void setPosition(Vector2 pos){
		this.Position=pos;
	}
	public Rectangle boundingBox(){
		int left = (int) (Math.round(getPosition().X) + Bounds.Left);
		int top = (int) (Math.round(getPosition().Y) + Bounds.Top);
		return new Rectangle(left, top, Bounds.Width, Bounds.Height, 1);
	}
	public void setRemove(){this.removeme=true;}
	public boolean removeMe(){return this.removeme;}

}
