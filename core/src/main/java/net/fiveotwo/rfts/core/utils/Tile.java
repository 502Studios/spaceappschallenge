package net.fiveotwo.rfts.core.utils;

import playn.core.Surface;

	public abstract class Tile {
		public String Texture;
		public String name;
		public int Type;
		// collision
		public int Collision;
		public static int Width = 11;
		public static int Height = 11;

		public Tile() {
			
		}
		
		public static void setSize(int x, int y){
			Width=x;
			Height=y;
		}
		
		public Tile(String texture, int collision, String data, int tp) {
			if (texture != null) {
				Texture = texture;
				name = data;
				Collision = collision;
				Type=tp;
			}
		}
		
		public abstract void Draw(Surface surf, Vector2 position);

		public abstract void Update(float f);
}
