package net.fiveotwo.rfts.core.entities;

import playn.core.Color;
import playn.core.Surface;
import net.fiveotwo.rfts.core.utils.Rectangle;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.Vector2;

public class Leg {
	Rectangle BBox;
	Vector2 Position;
	boolean broken;
	String name;
	Feet base;
	Lander mother;
	public Vector2 velocity=new Vector2(0,0);

	public Leg(Vector2 position, String img, Lander ld){
		//create boundaries;
		// add new Feet;
		setPosition(position);
		this.setBounds(new Rectangle(0,0,20,40,1));
		this.mother=ld;

		base=new Feet("",new Vector2(this.boundingBox().Width/2,this.boundingBox().Bottom()), this);
	}
	
	public void Update(float delta, Lander ld){
		setPosition(new Vector2(ld.boundingBox().Right(),ld.boundingBox().Height/2));
		doPhysics(delta/1000);
		doCollisions();
	}
	
	public void Draw(Surface surf, Vector2 offsets){
		base.draw(surf, offsets);
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
		surf.setFillColor(Color.rgb(15, 40, 89));
		surf.fillRect(getPosition().X+offsets.X, getPosition().Y+offsets.Y, 20,40);
	}
	
	void doPhysics(float delta){
		velocity.Y = velocity.Y +  mother.world.getGravity() * delta;
		setPosition(new Vector2(getPosition().X,getPosition().Y+velocity.Y));
		setPosition(getPosition().add(velocity));
		setPosition(new Vector2((float) Math.round(getPosition().X),
				(float) Math.round(getPosition().Y)));	
	}
	
	void doCollisions(){
		Rectangle bounds = this.boundingBox();
		int leftTile = (int) Math.floor((float) bounds.Left / Tile.Width);
		int rightTile = (int) Math.ceil(((float) bounds.Right() / Tile.Width)) - 1;
		int topTile = (int) Math.floor((float) bounds.Top / Tile.Height)-1;
		int bottomTile = (int) Math
				.ceil(((float) bounds.Bottom() / Tile.Height)) - 1;
		for (int x = leftTile; x <= rightTile; ++x) {
			for (int y = topTile; y <= bottomTile; ++y) {
				int collision = mother.world.getTileMap().GetCollision(x, y);
				Rectangle tileBounds = mother.world.getTileMap().GetBounds(x, y);
				Vector2 depth = Rectangle.GetIntersectionDepth(bounds,
						tileBounds);
				if (depth != new Vector2()) {
					float absDepthX = Math.abs(depth.X);
					float absDepthY = Math.abs(depth.Y);

					if (absDepthX > 0 || absDepthY > 0) {
						if(collision==0||collision==8){
								if (absDepthY < absDepthX) {
									if(depth.Y>0){}}
						}}
						//Is a impasable tile,dded by the user, timed, or breakeable.
						if (collision !=0) {
							if (absDepthY < absDepthX) {
								setPosition(new Vector2(getPosition().X,
										getPosition().Y + depth.Y));
								
								velocity.Y=0;
								bounds = boundingBox();
							} else {
								setPosition(new Vector2(getPosition().X+depth.X,
										getPosition().Y));
								
								bounds = boundingBox();
							}
						}
					}
			}
		}
	}

}
