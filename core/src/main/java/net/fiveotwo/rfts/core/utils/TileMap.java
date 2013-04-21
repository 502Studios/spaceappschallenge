package net.fiveotwo.rfts.core.utils;

/*
 * A Simple TileMap implementation
 * 2013 502Studios
 */

import playn.core.Surface;

public class TileMap {
	Tile tilemap[][];
	int mapWidth, mapHeight, offsetX, offsetY, firstTileX, lastTileX,
	firstTileY, lastTileY,firstOfX,lastOfX,firstOfY,lastOfY= 0;
	int ScreenWidth, ScreenHeight;
	
	public TileMap(int sw, int sh, Tile tl[][]){
		this.ScreenWidth=sw;
		this.ScreenHeight=sh;
		this.tilemap=tl;
	}
	
	public void UpdateOffsets(Camara cam){
		mapWidth = tilesToPixelsX(this.Width());
		mapHeight = tilesToPixelsY(this.Height());

		if(cam.Position().X>mapWidth-(ScreenWidth/2)){
			cam.setPosition(new Vector2(mapWidth-(ScreenWidth/2),cam.Position().Y));
		}
		if(cam.Position().Y>mapHeight-(ScreenHeight/2)){
			cam.setPosition(new Vector2(cam.Position().X,mapHeight-(ScreenHeight/2)));
		}
		offsetX = (ScreenWidth/4 - Math.round(cam.Position().X));
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, (ScreenWidth/4) - mapWidth);
		offsetY = ScreenHeight/4- Math.round(cam.Position().Y);
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max((ScreenHeight/4 - mapHeight),
				offsetY);
		// which tiles are visible_
		firstTileX = pixelsToTilesX(-offsetX);
		firstOfX=pixelsToTilesX(-offsetX*0.5f);
		if (firstTileX < 0) {
			firstTileX = 0;
		}
		lastTileX = firstTileX + pixelsToTilesX(ScreenWidth) + 1;
		lastOfX=firstOfX+pixelsToTilesX(ScreenWidth)+1;
		if (lastTileX > this.Width()) {
			lastTileX = this.Width();
		}
		firstTileY = pixelsToTilesY(-offsetY);
		if (firstTileY < 0) {
			firstTileY = 0;
		}
		lastTileY = firstTileY + pixelsToTilesY(ScreenHeight)
				+ 1;
		if (lastTileY > this.Height()) {
			lastTileY = this.Height();		

		}
	}
	
	public void updateTiles(float update) {
		if (tilemap != null) {
			for (int x = firstTileX; x < lastTileX; x++) {
				for (int y = firstTileY; y < lastTileY; y++) {
					tilemap[x][y].Update(update);
				}
			}
		}
	}

	public void DrawTiles(Surface surf) {
		if (tilemap != null) {
			for (int x = firstTileX; x < lastTileX; x++) {
				for (int y = firstTileY; y < lastTileY; y++) {
						Vector2 position = new Vector2(tilesToPixelsX(x)
								+ offsetX-1, tilesToPixelsY(y) + offsetY-1);
						if(tilemap[x][y].Texture!="")
						tilemap[x][y].Draw(surf, position);
				}
			}
		}
	}
	
	
	// pixels to tiles and viceversa
		public int pixelsToTilesX(int pixels) {
			return (int) Math.floor( pixels / Tile.Width);
		}

		public int tilesToPixelsX(int numTiles) {
			return numTiles * Tile.Width;
		}

		public int pixelsToTilesY(int pixels) {
			return (int) Math.floor( pixels / Tile.Height);
		}

		public int tilesToPixelsY(int numTiles) {
			return numTiles * Tile.Height;
		}

		public int pixelsToTilesX(float pixels) {
			return pixelsToTilesX((int)(pixels));
		}

		public int pixelsToTilesY(float pixels) {
			return pixelsToTilesY((int)(pixels));
		}
		
		public Rectangle GetBounds(int x, int y) {
			return new Rectangle(tilesToPixelsX(x), tilesToPixelsY(y), Tile.Width,
					Tile.Height, 1);
		}

		public int Width() {
			return tilemap.length;
		}

		public int Height() {
			return tilemap[0].length;
		}

		public int GetCollision(int x, int y) {
			if (x < 0 || x >= Width() || y < 0 || y >= Height()) {
				return -1;
			}
			return tilemap[x][y].Collision;
		}

		public void SetTile(Tile t, int x, int y) {
			tilemap[x][y] = t;
		}

		public Tile GetTile(int x, int y) {
			if (x < 0 || x >= Width() || y < 0 || y >= Height()) {
				return null;
			}
			return tilemap[x][y];
		}
		
		public Tile[][] getTilemap(){
			return this.tilemap;
		}
		
		public Vector2 getOffsets(){
			return new Vector2(offsetX,offsetY);
		}
}
