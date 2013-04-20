package net.fiveotwo.rfts.core.entities;

import playn.core.Surface;
import net.fiveotwo.rfts.core.Map;
import net.fiveotwo.rfts.core.utils.Entity;
import net.fiveotwo.rfts.core.utils.Rectangle;
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
	
	public Lander(Map mapa,float mass, float fuel, Vector2 initialthrust, String name){
		this.fuel=fuel;		
		this.mass=mass;
		this.initialthrust=initialthrust;
		this.name=name;
		world=mapa;
		loadContent();
	}
	
	@Override
	public void loadContent() {
		
	}

	@Override
	public void update(float delta) {
		
		
	}

	@Override
	public void draw(Surface surf, Vector2 offsets) {
		DebugDraw(surf,offsets);
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
		
	}

}
