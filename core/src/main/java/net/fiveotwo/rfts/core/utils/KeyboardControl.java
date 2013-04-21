package net.fiveotwo.rfts.core.utils;

import static playn.core.PlayN.keyboard;
import net.fiveotwo.rfts.core.Map;
import playn.core.Key;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;

public class KeyboardControl implements playn.core.Keyboard.Listener{
	Map mapa;
	public KeyboardControl(Map mapa){
		setMap(mapa);
		keyboard().setListener(this);
	}

	@Override
	public void onKeyDown(Event event) {
		if(event.key() == Key.LEFT){
			mapa.getCam().setPosition(mapa.getCam().Position().add(new Vector2(-40,0)));
		}
		if(event.key() == Key.RIGHT){
			mapa.getCam().setPosition(mapa.getCam().Position().add(new Vector2(40,0)));			
		}
		if(event.key() == Key.UP){
			mapa.getCam().setPosition(mapa.getCam().Position().add(new Vector2(0,-40)));					
				}
		if(event.key() == Key.DOWN){
			mapa.getCam().setPosition(mapa.getCam().Position().add(new Vector2(0,40)));
		}
		
		if(event.key()==Key.W){
			mapa.getLander().bottomThruster();
		}
		if(event.key()==Key.A){
			mapa.getLander().sideThruster(-1);
		}
		if(event.key()==Key.D){
			mapa.getLander().sideThruster(1);
		}
	}				

	@Override
	public void onKeyTyped(TypedEvent event) {
		
	}
	@Override
	public void onKeyUp(Event event) {
	}
	
	void setMap(Map mp){
		this.mapa=mp;
	}
	
	Map getMap(){
		return this.mapa;
	}
	


}
