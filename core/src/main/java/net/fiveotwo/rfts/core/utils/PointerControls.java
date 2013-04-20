package net.fiveotwo.rfts.core.utils;

import net.fiveotwo.rfts.core.Map;
import playn.core.Pointer;

public class PointerControls implements playn.core.Pointer.Listener{
	Map mapa;
		public PointerControls(Map mapa){
			this.mapa=mapa;
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
        }
}
