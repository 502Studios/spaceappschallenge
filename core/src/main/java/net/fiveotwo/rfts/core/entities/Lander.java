package net.fiveotwo.rfts.core.entities;


import static playn.core.PlayN.*;

import playn.core.Color;
import playn.core.Surface;
import net.fiveotwo.rfts.core.Map;
import net.fiveotwo.rfts.core.PlanetLander;
import net.fiveotwo.rfts.core.utils.Entity;
import net.fiveotwo.rfts.core.utils.Rectangle;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.Vector2;
/*
 * The Lander has 2 "legs" atached to it, these "legs" check for boundings these must be aligned to make a sucessfull landing, 
 * Thruster has certain mass as property it interacts with the planets gravity, also the Lander thrusters are linked to its fuel container
 * so be wise to use them
 */
public class Lander extends Entity{	
	float fuel;
	float mass;
	Leg rightLeg;
	Leg leftLeg;
	Vector2 impulse;
	Vector2 initialthrust;
	String name;
	Map world;
	float tick=0;
	Vector2 A;
	public Vector2 velocity=new Vector2(0,0);
	public Vector2 thrust=new Vector2(0,0);
	boolean verThrust;
	boolean horThrustPositive;
	boolean horThrustNegative;
    int AccelerationPerSec = 20;
    int FuelUsedPerSec=100;
	int altitude;
	
	boolean crashed=false;
	
	float t=0;
	
	float dragCoefficient=0.47f;   // is like a sphere xD
	
	boolean flagHThrustPositive=false;
	boolean flagHThrustNegative=false;
 	
	public Lander(Map mapa,float mass, float fuel, Vector2 initialthrust, Vector2 pos, String name){
		this.fuel=fuel;		
		this.mass=mass;
		this.initialthrust=initialthrust;
		this.name=name;
		world=mapa;
		A=new Vector2(0,world.getGravity());
		this.velocity=initialthrust;
		setPosition(pos);
		loadContent();
		
	}
	
	public void PositionAndVelocity(){
	
	}
	
	@Override
	public void loadContent() {
		thrust=new Vector2(0,0);
		this.setBounds(new Rectangle(0,0,40,40,1));
		leftLeg=new Leg(new Vector2(this.boundingBox().Left-10,this.boundingBox().Height/2), "", this);
		rightLeg=new Leg(new Vector2(this.boundingBox().Right()-10,this.boundingBox().Height/2), "",this);
	}

	@Override
	public void update(float delta) {
		t+=delta/1000;
		doPhysics(delta/1000);
		doCollisions();
		rightLeg.Update(delta/1000,this);
		leftLeg.Update(delta/1000,this);
		verThrust=false;
		this.horThrustNegative=false;
		this.horThrustPositive=false;
	}
	
	void doPhysics(float delta){
		float accel=0;
		float xaccel=0;
		float xacceleration=0;
		if(verThrust){
		double elapsedFiring = delta;
        double fuelUsed = elapsedFiring * FuelUsedPerSec;
        this.fuel-=fuelUsed;
        	accel = AccelerationPerSec * delta;
		}
		
			
		if(this.horThrustPositive){
			xaccel=AccelerationPerSec * delta;

			double elapsedFiring = delta;
			double fuelUsed = elapsedFiring * FuelUsedPerSec;
			this.fuel-=fuelUsed;	        
	        
		}
		
		if(this.horThrustNegative){
			xaccel=-AccelerationPerSec * delta;
			
			double elapsedFiring = delta;
			double fuelUsed = elapsedFiring * FuelUsedPerSec;
			this.fuel-=fuelUsed;
		}
		
		
		if(velocity.X!=0){
		   
		  xaccel=xaccel-Math.abs(velocity.X)/(velocity.X)*1/2*this.world.getDensity()*this.dragCoefficient*this.velocity.X*this.velocity.X;
		 
		}
		
		 velocity.X=velocity.X+xaccel;
		 
//		if(this.horThrustPositive){
//			xaccel=AccelerationPerSec * delta-1/2*this.world.getDensity()*this.dragCoefficient*this.velocity.X*this.velocity.X;
//			
//			double elapsedFiring = delta;
//			double fuelUsed = elapsedFiring * FuelUsedPerSec;
//	        this.fuel-=fuelUsed;	        
//	        this.flagHThrustPositive=true;
//	        
//	        velocity.X= velocity.X+ xaccel;
//		}
//		
//		if(this.horThrustNegative){
//			xaccel=-AccelerationPerSec * delta*(0.5f)+1/2*this.world.getDensity()*this.dragCoefficient*this.velocity.X*this.velocity.X;
//			
//			double elapsedFiring = delta;
//			double fuelUsed = elapsedFiring * FuelUsedPerSec;
//	        this.fuel-=fuelUsed;
//	        this.flagHThrustNegative=true;
//	        
//	        velocity.X= velocity.X+ xaccel;	        
//		}
//		
//		if(this.flagHThrustPositive&&!this.horThrustNegative){
//			xaccel=-(0.1f)*delta*1/2*this.world.getDensity()*(0.5f)*this.dragCoefficient*this.velocity.X*this.velocity.X;
//			
//			velocity.X= velocity.X+ xaccel;
//			if(velocity.X==0){this.flagHThrustPositive=false;}
//		}
//		
//		if(this.flagHThrustNegative&&!this.horThrustPositive){
//			xaccel=(0.1f)*delta*1/2*this.world.getDensity()*this.dragCoefficient*this.velocity.X*this.velocity.X;
//			
//			
//			velocity.X= velocity.X+ xaccel;
//			if(velocity.X==0){this.flagHThrustNegative=false;}
//		}		
		
		velocity.Y = velocity.Y +  (world.getGravity() * delta)-accel;
				
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
				int collision = world.getTileMap().GetCollision(x, y);
				Rectangle tileBounds = world.getTileMap().GetBounds(x, y);
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
								
								
								    //aceleration (0-velocity)/t (with t equal a 1seg have to resist 3G )
								if(velocity.Y*10>9.8f){
									//crash 
									crashed=true;
									System.out.print("sorry try again");
									
								}
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

	@Override
	public void draw(Surface surf, Vector2 offsets) {
		surf.drawImage(PlanetLander.Imagenes.get("rocketship"),getPosition().X+offsets.X, getPosition().Y+offsets.Y);
	//	DebugDraw(surf,offsets);
	}

	@Override
	public void action() {
		
	}
	
	public Rectangle leftLegBB(){
		return null;
	}
	
	public Rectangle rightLegBB(){
		return null;
	}
	
	void DebugDraw(Surface surf, Vector2 offsets){
		surf.setFillColor(Color.rgb(15, 100, 102));
			surf.fillRect(getPosition().X+offsets.X, getPosition().Y+offsets.Y, 40,40);
			
		leftLeg.Draw(surf, offsets);
		rightLeg.Draw(surf, offsets);
	}
	
	public Vector2 getVel(){return this.velocity;}
	public float getFuel(){return this.fuel;}
	
	public void bottomThruster(){
		verThrust=true;
	}
	
	public void sideThruster(int dir){
		if(dir>=0){
			this.horThrustPositive=true;
		}
		if(dir<0){
			this.horThrustNegative=true;
		}
		
	}
	
	public Vector2 getThrust(){return this.thrust;}
	public void setThrust(Vector2 tr){this.thrust=tr;}
	public int getAltitude(){
		altitude=this.world.getTileMap().Height()-this.world.getTileMap().pixelsToTilesX(this.boundingBox().Bottom());
		return this.altitude;}
	public boolean hasCrashed(){return this.crashed;}

}