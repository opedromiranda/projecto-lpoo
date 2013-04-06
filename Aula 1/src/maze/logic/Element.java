package maze.logic;

public class Element {
	char drawing;
	int x,y;
	Element(char c) {
		drawing = c;
	}

	public char getDrawing() {
		return drawing;
	}

	public void setDrawing(char c) {
		drawing = c;
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
