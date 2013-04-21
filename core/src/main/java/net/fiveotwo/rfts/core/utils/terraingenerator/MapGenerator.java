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
	
	public net.fiveotwo.rfts.core.utils.TileMap GenerateMap(int x, int y, int base, float complexity, String mapa){//the delegator
		TileMap tl=new RealGenerator().GenerateMapBit(x,y,base, complexity, mapa);	
		return tl;
	}
	
}

class RealGenerator{//the delegate
	String map;
	public static net.fiveotwo.rfts.core.utils.Tile[][] tilemap;
	public static List<Ant> hormigas;
	TileMap GenerateMapBit(int x, int y,int base, float complexity, String map){// size and type of terrain
		this.map=map;
		tilemap=new Tile[x][y];
		for(int sx=0;sx<x;sx++){
			for(int sy=0;sy<y;sy++){
						setTile(sx,sy,new StaticTile("",base,String.valueOf(base),0));		
			}			
		}
		FillBottom();
		hormigas=new ArrayList<Ant>();
		//fill with ants
		int total= (int)((x*2)/((x+(y/6)))*complexity);	
		for(int xv=0;xv<total;xv++){
			int lifespawn=(int) (Math.random()*complexity);
			int size=(int) (Math.random()*Width());
			int height=(int) Height()-50;
			double value=Math.random()*4;
			int val=(int)value;
			Ant ant=new Ant(tilemap, val, lifespawn,size,height,map);
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
		
		for(int sx=0;sx<x;sx++){
			for(int sy=1;sy<y-1;sy++){
						if(tilemap[sx][sy].Collision==1&&tilemap[sx][sy-1].Collision==0){
							setTile(sx,sy,new StaticTile(map+"5",2,String.valueOf(2),1));	
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
				double value=Math.random()*4;
				int val=(int)value;
				
				//		setTile(sx,sy,new StaticTile(map+val,1,"",1));
			}			
		}
	}
}