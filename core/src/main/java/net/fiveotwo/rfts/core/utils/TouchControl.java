package net.fiveotwo.rfts.core.utils;

import net.fiveotwo.rfts.core.Map;



public class TouchControl implements playn.core.Touch.Listener {
	boolean LeftStick,RightStick;
	Stick LStick,RStick;
	Map mapa;
	public TouchControl(Map mapa){

	}
	
	@Override
	public void onTouchStart(playn.core.Touch.Event[] touches) {
	
	}
	@Override
	public void onTouchMove(playn.core.Touch.Event[] touches) {
		
	}

	@Override
	public void onTouchEnd(playn.core.Touch.Event[] touches) {
		
	}

	@Override
	public void onTouchCancel(playn.core.Touch.Event[] touches){
		
	}
	void setMap(Map mp){this.mapa=mp;}
	Map getMap(){return this.mapa;}
	
	void RightStick(int value){
		
	}
	
	void LeftStick(int value){
	
	}
}
