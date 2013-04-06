package maze.logic;

public class Eagle extends Element{
	boolean hasSword = false;
	boolean isFree = false;
	public Eagle(){
		super('A'); 
		// TODO Auto-generated constructor stub
	}
	
	public void setHasSword(boolean b){
		hasSword = b;
	}
	public boolean hasSword(){
		return hasSword;
	}
	public void setFree(boolean b){
		isFree = b;
	}
	public boolean isFree(){
		return isFree;
	}
}
