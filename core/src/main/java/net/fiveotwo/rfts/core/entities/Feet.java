package net.fiveotwo.rfts.core.entities;

import playn.core.Color;
import playn.core.Surface;
import net.fiveotwo.rfts.core.utils.Entity;
import net.fiveotwo.rfts.core.utils.Rectangle;
import net.fiveotwo.rfts.core.utils.Vector2;

public class Feet extends Entity{
	Leg mother;
	public Feet(String name, Vector2 Position, Leg lg){
		setPosition(Position);
		this.setBounds(new Rectangle(0,0,10,10,1));
		this.mother=lg;

	}
	
	@Override
	public void loadContent() {
		this.setBounds(new Rectangle(0,0,10,10,1));
	}

	public void update(float delta, Vector2 pos) {
			mother.getPosition();
	}

	@Override
	public void draw(Surface surf, Vector2 offsets) {
		DebugDraw(surf,offsets);
	}

	@Override
	public void action() {
		
	}
	
	void DebugDraw(Surface surf, Vector2 offsets){
		surf.setFillColor(Color.rgb(15, 40, 89));
		surf.fillRect(getPosition().X+offsets.X, getPosition().Y+offsets.Y, 10,10);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
