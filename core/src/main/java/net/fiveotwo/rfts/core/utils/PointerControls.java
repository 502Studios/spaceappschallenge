package net.fiveotwo.rfts.core.utils;

import static playn.core.PlayN.pointer;
import net.fiveotwo.rfts.core.Map;
import playn.core.Pointer;

public class PointerControls implements playn.core.Pointer.Listener{
	Map mapa;
		public PointerControls(Map mapa){
			this.mapa=mapa;
			pointer().setListener(this);

		}
        @Override
        public void onPointerEnd(Pointer.Event event) {
        	
        }
        @Override
        public void onPointerCancel(Pointer.Event event) {
        }
        @Override
        public void onPointerDrag(Pointer.Event event) {
        }
        @Override
        public void onPointerStart(Pointer.Event event) {
        	if(mapa.hasInstructions == true){
        		mapa.hasInstructions = false;
        	}
        	if(mapa.getLander().hasCrashed()){
        		mapa.shutdown();
        		mapa.Init();
        	}
        }
}
