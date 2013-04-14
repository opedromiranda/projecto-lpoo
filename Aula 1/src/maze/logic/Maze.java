package maze.logic;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class Maze {
	char[][] matrix;
	int lado;
	int x, y;
	Stack<Celula> path = new Stack<Celula>();
	Vector<Celula> caminhos = new Vector<Celula>(); // vector que guarda todos
													// os espacos brancos. Para
													// depois colocar dragao.
/**
 * 
 * 
 * @param l construtor que cria maze de lado l
 */
	public Maze(int l) {
		lado = l;
		matrix = new char[lado][lado];
		fillMatrix();
		createExit();
		generate();
	}
	/**
	 * 
	 * 
	 * @return celulas possiveis de colocar elementos
	 */
	public Vector<Celula> getCaminho(){
		return caminhos;
	}
	
	/**
	 * preenche matriz de X para depois construir caminhos
	 */
	void fillMatrix() {
		for (int c1 = 0; c1 < lado; c1++) {
			for (int c2 = 0; c2 < lado; c2++) {
				if (c1 == 0 || c2 == 0 || c1 == lado - 1 || c2 == lado - 1)
					matrix[c1][c2] = 'W';
				else
					matrix[c1][c2] = 'X';
			}
		}
	}

	/**
	 * 
	 * Cria saida num lado aleatorio
	 */
	void createExit() {
		Random r = new Random();
		int l = r.nextInt(4) + 1; // 0 a 3
		int pos = r.nextInt(lado - 2) + 1; // se lado for 5, d� um numero de 1 a
											// 3

		switch (l) {
		case 1: // saida em cima
			matrix[0][pos] = 'S';
			x = pos;
			y = 1;
			break;
		case 2: // saida em baixo
			matrix[lado - 1][pos] = 'S';
			x = pos;
			y = lado - 2;
			break;
		case 3: // saida � esquerda
			matrix[pos][0] = 'S';
			x = 1;
			y = pos;
			break;
		case 4: // saida � direita
			matrix[pos][lado - 1] = 'S';
			x = lado - 2;
			y = pos;
			break;
		default:
			break;
		}
	}

	/*
	 * 
	 * devolve o tamanho do lado da matriz
	 */
	public int getLado() {
		return lado;
	}

	/*
	 * 
	 * cria os caminhos e paredes da matriz
	 */
	public char[][] generate() {
		matrix[y][x] = ' ';
		Celula c1 = new Celula(x, y);
		Vector<Celula> celulasVizinhas = new Vector<Celula>();

		boolean podeAvancar = true;

		do {
			Celula cima = new Celula(c1.getX(), c1.getY() - 1);
			Celula baixo = new Celula(c1.getX(), c1.getY() + 1);
			Celula direita = new Celula(c1.getX() + 1, c1.getY());
			Celula esquerda = new Celula(c1.getX() - 1, c1.getY());
			
			if(!temCantosBrancos(cima, 0))
				celulasVizinhas.add(cima);
			
			if(!temCantosBrancos(baixo, 1))
				celulasVizinhas.add(baixo);
			
			if(!temCantosBrancos(direita, 2))
				celulasVizinhas.add(direita);
			
			if(!temCantosBrancos(esquerda, 3))
				celulasVizinhas.add(esquerda);

			// remover celulas ocupadas vizinhas
			for (int c = 0; c < celulasVizinhas.size(); c++) {
				char vizinho = matrix[celulasVizinhas.elementAt(c).getY()][celulasVizinhas
						.elementAt(c).getX()];

				int espacosBrancos = celulaIsolada(celulasVizinhas.elementAt(c));
				
				
				// remove invalidas (parede ou com espacos brancos adjacentes)
				
				
				if (vizinho != 'X' || espacosBrancos >1) {
						celulasVizinhas.remove(c);
						c--;
						
					}
				

			}

			if (celulasVizinhas.size() == 0) {
				if (path.size() == 0)
					podeAvancar = false;
				else {
					c1 = path.pop();

				}
			}

			else {
				Random r = new Random();
				int newPos = r.nextInt(celulasVizinhas.size());
				c1 = new Celula(celulasVizinhas.elementAt(newPos).getX(),
						celulasVizinhas.elementAt(newPos).getY());

				matrix[c1.getY()][c1.getX()] = ' ';
				path.add(c1);

				caminhos.add(c1);

				celulasVizinhas.clear();
			}
		} while (podeAvancar);
		return matrix;
	}

	/**
	 * 
	 * @param c1 celula a avaliar
	 * @param c, se 0: cima, 1: baixo, 2: direita, 3:esquerda.
	 * @return devolve um boleano se a celula para onde queremos ir ja tem um caminho adjacente
	 */
	boolean temCantosBrancos(Celula c1,int c){
		boolean result = false;
		
		
		if(c1.getX() > 0 && c1.getY() > 0 && c1.getX() < lado - 1 && c1.getY() < lado - 1){
			
			Celula cantoSupDir,cantoSupEsq,cantoInfEsq,cantoInfDir,cima,esquerda,direita,baixo;
			cantoSupDir = new Celula(c1.getX() + 1, c1.getY() - 1);
			cantoSupEsq = new Celula(c1.getX() - 1, c1.getY() - 1);
			cantoInfEsq = new Celula(c1.getX() - 1, c1.getY() + 1);
			cantoInfDir = new Celula(c1.getX() + 1, c1.getY() + 1);
			
			cima = new Celula(c1.getX() , c1.getY() - 1);
			baixo = new Celula(c1.getX() , c1.getY() + 1);
			esquerda = new Celula(c1.getX() -1 , c1.getY());
			direita = new Celula(c1.getX() + 1, c1.getY());
			

			switch(c){
			case 0: // cima
				if(matrix[cantoSupDir.getY()][cantoSupDir.getX()] == ' ' && matrix[cima.getY()][cima.getX()] == 'X' && matrix[direita.getY()][direita.getX()] == 'X'){
					return true;
				}
				else if(matrix[cantoSupEsq.getY()][cantoSupEsq.getX()] == ' ' && matrix[cima.getY()][cima.getX()] == 'X' && matrix[esquerda.getY()][esquerda.getX()] == 'X'){
					return true;
				}
				break;
			case 1: //baixo 
				if(matrix[cantoInfDir.getY()][cantoInfDir.getX()] == ' ' && matrix[baixo.getY()][baixo.getX()] == 'X' && matrix[direita.getY()][direita.getX()] == 'X'){
					return true;
				}
				else if(matrix[cantoInfEsq.getY()][cantoInfEsq.getX()] == ' ' && matrix[baixo.getY()][baixo.getX()] == 'X' && matrix[esquerda.getY()][esquerda.getX()] == 'X'){
					return true;
				}
				break;
			case 2: //direita
				if(matrix[cantoSupDir.getY()][cantoSupDir.getX()] == ' ' && matrix[cima.getY()][cima.getX()] == 'X' && matrix[direita.getY()][direita.getX()] == 'X'){
					return true;
				}
				else if(matrix[cantoInfDir.getY()][cantoInfDir.getX()] == ' ' && matrix[baixo.getY()][baixo.getX()] == 'X' && matrix[direita.getY()][direita.getX()] == 'X'){
					return true;
				}
				break;
			case 3: //esqerda
				if(matrix[cantoSupEsq.getY()][cantoSupEsq.getX()] == ' ' && matrix[cima.getY()][cima.getX()] == 'X' && matrix[esquerda.getY()][esquerda.getX()] == 'X'){
					return true;
				}
				else if(matrix[cantoInfEsq.getY()][cantoInfEsq.getX()] == ' ' && matrix[baixo.getY()][baixo.getX()] == 'X' && matrix[esquerda.getY()][esquerda.getX()] == 'X'){
					return true;
				}
				break;
				default: break;
			}
				
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param c1, se 0: cima, 1: baixo, 2: direita, 3:esquerda.
	 * @return Devolve se celula destino tem celulas adjacentes brancas
	 */
	int celulaIsolada(Celula c1) {
		Celula cima = new Celula(c1.getX(), c1.getY() - 1);
		Celula baixo = new Celula(c1.getX(), c1.getY() + 1);
		Celula direita = new Celula(c1.getX() + 1, c1.getY());
		Celula esquerda = new Celula(c1.getX() - 1, c1.getY());
		int espacosBrancos = 0;
		if (cima.getY() > 0)
			if (matrix[cima.getY()][cima.getX()] == ' ') {
				espacosBrancos++;
			}

		if (baixo.getY() < lado)
			if (matrix[baixo.getY()][baixo.getX()] == ' ') {
				espacosBrancos++;
			}

		if (esquerda.getX() > 0)
			if (matrix[esquerda.getY()][esquerda.getX()] == ' ') {
				espacosBrancos++;
			}
		if (direita.getX() < lado - 1)
			if (matrix[direita.getY()][direita.getX()] == ' ') {
				espacosBrancos++;
			}
			
		return espacosBrancos;
	}
}
