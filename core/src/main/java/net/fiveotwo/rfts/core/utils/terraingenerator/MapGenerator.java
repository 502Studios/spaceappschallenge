package net.fiveotwo.rfts.core.utils.terraingenerator;


import java.util.ArrayList;
import java.util.List;

import net.fiveotwo.rfts.core.entities.StaticTile;
import net.fiveotwo.rfts.core.utils.Tile;
import net.fiveotwo.rfts.core.utils.TileMap;


public class MapGenerator {
	net.fiveotwo.rfts.core.utils.Tile[][] TileMap;
	public MapGenerator(){
		
	}
	
	public net.fiveotwo.rfts.core.utils.TileMap GenerateMap(int x, int y, int base, float complexity){//the delegator
		TileMap tl=new RealGenerator().GenerateMapBit(x,y,base, complexity);	
		return tl;		
	}
	
}

class RealGenerator{//the delegate
	public static net.fiveotwo.rfts.core.utils.Tile[][] tilemap;
	public static List<Ant> hormigas;
	TileMap GenerateMapBit(int x, int y,int base, float complexity){// size and type of terrain
		tilemap=new Tile[x][y];
		for(int sx=0;sx<x;sx++){
			for(int sy=0;sy<y;sy++){
						setTile(sx,sy,new StaticTile(String.valueOf(base),base,String.valueOf(base),0));		
			}			
		}
		FillBottom();
		hormigas=new ArrayList<Ant>();
		//fill with ants
		int total= (int)((x*y)/((x+y))*complexity);		
		for(int xv=0;xv<total;xv++){
			int lifespawn=(int) (Math.random()*1000);
			int size=(int) (Math.random()*Width());
			int height=(int) Height()-50;
			int val=(int) 1;
			Ant ant=new Ant(tilemap, val, lifespawn,size,height);
			hormigas.add(ant);
		}		
		
		while(hormigas.size()>0){
			for(Ant hor: hormigas){
				if(hor.isAlive()){
					hor.Update(1);
				}else{
					hormigas.remove(hor);					
					break;
				}
			}
		}
		
		TileMap tlmp=new TileMap(base, base, tilemap);	
		System.out.println("Map generated");
		return tlmp;
	}
	
	public Tile getTile(int x, int y){
			if (x < 0 || x >= Width() || y < 0 || y >= Height()) {
				return null;
			}
			return tilemap[x][y];	
	}
	public void setTile(int x, int y,Tile tl){
		tilemap[x][y]=tl;
	}
	
	public int Width() {
		return tilemap.length;
	}

	public int Height() {
		return tilemap[0].length;
	}
	
	void FillBottom(){
		for(int sx=0;sx<Width();sx++){
			for(int sy=Height()-50;sy<Height();sy++){
						setTile(sx,sy,new StaticTile("",1,"",1));
			}			
		}
	}
}