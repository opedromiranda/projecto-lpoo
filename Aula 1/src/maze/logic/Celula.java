package maze.logic;

public class Celula {
	int x,y;
	public Celula(int x,int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * 
	 * @param n posicao x da celula
	 */
	public void setX(int n){
		x = n;
	}
	/**
	 * 
	 * 
	 * @param n posicao y da celula
	 */
	public void setY(int n){
		y = n;
	}
	
	/**
	 * 
	 * @return posicao x da celula
	 */
	public int getX(){
		return x;
	}
	/**
	 * 
	 * @return posicao y da celula
	 */
	public int getY(){
		return y;
	}
}
