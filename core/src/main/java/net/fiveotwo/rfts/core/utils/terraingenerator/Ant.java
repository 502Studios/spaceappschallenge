package net.fiveotwo.rfts.core.utils.terraingenerator;

import net.fiveotwo.rfts.core.entities.StaticTile;
import net.fiveotwo.rfts.core.utils.Tile;


public class Ant {
//"ant" or entity that transverses the map placing random tiles, it can move in 8 directions and has a life spawn of how many steps can it move before dying.
	int PositionX,PositionY,Type,Life;
	Tile[][] Mapa;
	int Direction;
	public Ant(Tile[][] mp, int type, int life, int posx, int posy){
		this.Type=type;
		this.Life=life;
		this.PositionX=posx;
		this.PositionY=posy;
		this.Mapa=mp;
	}
	
	public void Update(float delta){
		subLife();
		Direction=(int) (Math.random()*7);
		switch(Direction){
		case 0://up
			if(getTile(PositionX,PositionY-1) != null){
				setTile(PositionX,PositionY-1,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionY=PositionY-1;
				CheckBottoms();
			}
			break;
		case 1://upl
			if(getTile(PositionX-1,PositionY-1) != null){
				setTile(PositionX-1,PositionY-1,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionX=PositionX-1;PositionY=PositionY-1;
				CheckBottoms();
			}
			break;
		case 2://left
			if(getTile(PositionX-1,PositionY) != null){
				setTile(PositionX-1,PositionY,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionX=PositionX-1;
				CheckBottoms();
			}
			break;
		case 3://downl
			if(getTile(PositionX-1,PositionY+1) != null){
				setTile(PositionX-1,PositionY+1,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionX=PositionX-1;PositionY=PositionY+1;
				CheckBottoms();
			}
			break;
		case 4://down
			if(getTile(PositionX,PositionY+1) != null){
				setTile(PositionX,PositionY+1,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionY=PositionY+1;
				CheckBottoms();
			}
			break;
		case 5://downr
			if(getTile(PositionX+1,PositionY+1) != null){
				setTile(PositionX+1,PositionY+1,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionX=PositionX+1;PositionY=PositionY+1;
			}
			break;
		case 6://right
			if(getTile(PositionX+1,PositionY) != null){
				setTile(PositionX+1,PositionY,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionX=PositionX+1;
				CheckBottoms();
			}
			break;
		case 7://upr
			if(getTile(PositionX+1,PositionY-1) != null){ 
				setTile(PositionX+1,PositionY-1,new StaticTile(String.valueOf(this.Type), this.Type, String.valueOf(this.Type), Direction));
				PositionX=PositionX+1;PositionY=PositionY-1;
				CheckBottoms();
			}
			break;										
		}
	}
	
	public boolean isAlive(){
		return getLife()>0 ? true:false;
	}

	public int getLife(){
		return this.Life;
	}
	
	public void setLife(int value){
		this.Life=value;
	}
	
	public void subLife(){
		setLife(getLife()-1);
	}

	
	public Tile getTile(int x, int y){
		if (x < 0 || x >= Width() || y < 0 || y >= Height()) {
			return null;
		}
		return RealGenerator.tilemap[x][y];	
	}
	public void setTile(int x, int y,Tile tl){
		RealGenerator.tilemap[x][y]=tl;
	}

	public int Width() {
		return RealGenerator.tilemap.length;
	}

	public int Height() {
		return RealGenerator.tilemap[0].length;
	}
	
	void CheckBottoms(){
		if(getTile(PositionX,PositionY+1) != null){
			for(int y=PositionY+1;y<Height();y++){
				if(getTile(PositionX,y).Collision==0){
				setTile(PositionX,y,new StaticTile(String.valueOf(this.Type), 2, String.valueOf(this.Type), 2));
				}else{
					return;
				}
			}
		}
	}
	
	
}
