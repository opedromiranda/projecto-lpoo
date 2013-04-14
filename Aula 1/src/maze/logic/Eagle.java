package maze.logic;

public class Eagle extends Element{
	boolean hasSword = false;
	boolean isFree = false;
	public Eagle(){
		super('A'); 
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param b Diz se aguia ja tem a espada
	 */
	public void setHasSword(boolean b){
		hasSword = b;
	}
	/**
	 * 
	 * @return se aguia ja tem espada
	 */
	public boolean hasSword(){
		return hasSword;
	}
	/**
	 * 
	 * @param b, liberta aguia ou esconde depois de devolver espada ao heroi
	 */
	public void setFree(boolean b){
		isFree = b;
	}
	/**
	 * 
	 * @return, liberta aguia para apanhar espada
	 */
	public boolean isFree(){
		return isFree;
	}
}
