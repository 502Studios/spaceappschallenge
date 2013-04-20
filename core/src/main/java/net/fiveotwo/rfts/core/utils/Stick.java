package net.fiveotwo.rfts.core.utils;

public class Stick {
	Vector2 Position,Direction,LastPosition;
	
	public Stick(Vector2 Pos){
		SetCenter(Pos);
		SetDirection(Pos);
		LastPosition=Pos;
	}
	
	public void SetCenter(Vector2 Pos){
		this.Position=Pos;
	}
	
	public Vector2 GetCenter(){
		return this.Position;
	}
	
	public void SetDirection(Vector2 Pos){
		this.Direction=Pos;
	}
	
	public Vector2 GetDirection(){
		return this.Direction;
	}
	
	public int CalculateMovement(Vector2 Position){
		LastPosition=Position;
		if(GetCenter()!=Position||LastPosition!=Position){
			float DeltaY=GetCenter().Y-Position.Y;
			float DeltaX=Position.X-GetCenter().X;
			float angle=(float) Math.atan2(DeltaY, DeltaX);	
			angle=(int) Math.toDegrees(angle);
		if(angle < 0){
	        angle= 360+angle;
	    }System.out.println(angle);
		if(angle>=0&&angle<=30){
			return 0; //right
		}else if(angle>30&&angle<=60){
			return 1; //rightup
		}else if(angle>60&&angle<=120){
			return 2; //up
		}else if(angle>120&&angle<=150){
			return 3; //leftup
		}else if(angle>150&&angle<=210){
			return 4; //left
		}else if(angle>210&&angle<=240){
			return 5; //leftdown
		}else if(angle>240&&angle<=300){
			return 6; //down
		}else if(angle>300&&angle<=330){
			return 7; //rightdown
		}else if(angle>330&&angle<=360){
			return 0; //right
		}
		return 9;
	}else{
		return 9;
	}
	}
}
