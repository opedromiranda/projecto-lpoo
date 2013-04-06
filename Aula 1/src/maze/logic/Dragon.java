package maze.logic;

import java.util.Random;

public class Dragon extends Element {
	
	public boolean dragonIsInHell = false;
	
	
	
	public Dragon() {
		super('D');
		
	}
	
	public void moveDragon( Sword espada, char[][] matrix){
		
		Random m = new Random();
		int mover = m.nextInt(10);
		
		if (mover > 3){
		Random r = new Random();
		int[] newDragonPosition = new int[2];
		boolean movimentoValido = false;
		
	
		while (!movimentoValido){		
			int movement = r.nextInt(4) + 1;
			switch (movement) {
			case 1: // goes left
				newDragonPosition[0] = getY() - 1;
				newDragonPosition[1] = getX();
				break;
			case 2: // goes down
				newDragonPosition[0] = getY();
				newDragonPosition[1] = getX() + 1;
				break;
			case 3: // goes right
				newDragonPosition[0] = getY() + 1;
				newDragonPosition[1] = getX();
				break;
			case 4: // goes up
				newDragonPosition[0] = getY();
				newDragonPosition[1] = getX() - 1;
				break;
			default:
				break;
			}
			
			
			if (newDragonPosition[0] == espada.getY()
					&& newDragonPosition[1] == espada.getX()) {
				setDrawing('F');
			}
			else if(getDrawing() == 'F')
				setDrawing('D');
			switch (matrix[newDragonPosition[0]][newDragonPosition[1]]) {
			case ' ':
				setX(newDragonPosition[1]);
				setY(newDragonPosition[0]);
				movimentoValido = true;
				break;
			
		
			default:
				break;
			}
		}
		
	}
		
	}


}
