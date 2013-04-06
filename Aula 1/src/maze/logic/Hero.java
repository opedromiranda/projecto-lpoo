package maze.logic;

public class Hero extends Element {
	
	
	boolean hasSword = false;
	public Hero() {
		super('H');
	}
	
	public void setHasSword(boolean b){
		hasSword = b;
		if(hasSword){
			setDrawing('A');
		}
	}
	public boolean hasSword(){
		return hasSword;
	}
}
