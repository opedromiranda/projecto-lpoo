package maze.logic;

public class Hero extends Element {
	
	
	boolean hasSword = false;
	boolean estaVivo = true;
	public Hero() {
		super('H');
	}
	/**
	 * 
	 * Mete heroi armado
	 */
	public void setHasSword(boolean b){
		hasSword = b;
		if(hasSword){
			setDrawing('A');
		}
	}
	
	/**
	 * 
	 * Retorna se está armado
	 */
	public boolean hasSword(){
		return hasSword;
	}
	/**
	 * 
	 * Mata heroi, alterando variavel estaVivo
	 */
	public void matar(){
		estaVivo = false;
	}
	
	/**
	 * 
	 * Devolve se heroi está vivo ou morto
	 */
	public boolean getVida(){
		return estaVivo;
		
	}
}
