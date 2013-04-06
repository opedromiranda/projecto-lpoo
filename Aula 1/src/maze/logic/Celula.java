package maze.logic;

public class Celula {
	int x,y;
	public Celula(int x,int y){
		this.x = x;
		this.y = y;
	}
	public void setX(int n){
		x = n;
	}
	public void setY(int n){
		y = n;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
