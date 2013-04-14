package maze.cli;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import maze.logic.Celula;
import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.Sword;

public class JogoLabirinto {

	static int NUM_ROWS = 10;
	static int NUM_COLUMNS = 10;
	static char[][] matrix;
	char[][] matrixDefault;
	static int[] heroPosition, dragonPosition;

	public static Hero myHero = new Hero();
	public static Sword mySword = new Sword();
	static Eagle myEagle = new Eagle();
	public static Dragon dragonTestes;


	static Vector<Celula> caminho = new Vector<Celula>();
	static Vector<Celula> dragoes = new Vector<Celula> ();
	public static Vector<Dragon> dragons = new Vector<Dragon>();
	static Maze maze;

	/**
	 * @param args
	 */

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		System.out.print("Qual a dimensão do labirinto (AxA)\n");
		Scanner in = new Scanner(System.in);
		int dimensao = in.nextInt();

		System.out.print("Quantos dragoes pretende ter?\n");
		Scanner in1 = new Scanner(System.in);
		int nDragoes = in1.nextInt();

		maze = new Maze(dimensao);
		matrix = maze.generate();
		caminho = maze.getCaminho();

		Random r = new Random();
		Celula posJogador, posDragao, posEspada;
		posJogador = caminho.elementAt(r.nextInt(caminho.size()));
		posEspada = caminho.elementAt(r.nextInt(caminho.size()));


		for(int i = 0; i < nDragoes ; i++){
			Dragon myDragon = new Dragon();
			posDragao = caminho.elementAt(r.nextInt(caminho.size()));

			myDragon.setX(posDragao.getX());
			myDragon.setY(posDragao.getY());

			dragons.add(myDragon);

		}
		myHero.setX(posJogador.getX());
		myHero.setY(posJogador.getY());

		mySword.setX(posEspada.getX());
		mySword.setY(posEspada.getY());

		String op;
		Scanner s;
		boolean exit;
		do {
			printMaze();
			s = new Scanner(System.in);
			op = s.nextLine();
			exit = processOp(op);
		} while (op.equals("sair") == false && exit == false);

	}


	public JogoLabirinto(){

		createDefaultMatrix();
		matrix = matrixDefault;

		myHero.setX(1);
		myHero.setY(1);

		mySword.setX(1);
		mySword.setY(8);



	};

	private static void printMaze() {

		for (int i = 0; i < dragons.size(); i++){
			matrix[dragons.elementAt(i).getY()][dragons.elementAt(i).getX()] = dragons.elementAt(i).getDrawing();
		}

		for (int c1 = 0; c1 < maze.getLado(); c1++) {
			for (int c2 = 0; c2 < maze.getLado(); c2++) {
				if (c1 == myHero.getY() && c2 == myHero.getX())
					System.out.print("" + myHero.getDrawing() + " ");
				else if (c1 == mySword.getY() && c2 == mySword.getX())
					System.out.print("" + mySword.getDrawing() + " ");
				else if (c1 == myEagle.getY() && c2 == myEagle.getX() && myEagle.isFree())
					System.out.print("" + myEagle.getDrawing() + " ");

				else 
					System.out.print(matrix[c1][c2] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < dragons.size(); i++){
			matrix[dragons.elementAt(i).getY()][dragons.elementAt(i).getX()] = ' ';
		}


	}




	public static boolean processOp(String op) {
		boolean exit = false;

		if (op.length() == 0)
			return exit;

		char movement = op.charAt(0);
		int[] newHeroPosition = new int[2];
		switch (movement) {
		case 'w':
			newHeroPosition[0] = myHero.getY() - 1;
			newHeroPosition[1] = myHero.getX();
			break;
		case 'a':
			newHeroPosition[0] = myHero.getY();
			newHeroPosition[1] = myHero.getX() - 1;
			break;
		case 's':
			newHeroPosition[0] = myHero.getY() + 1;
			newHeroPosition[1] = myHero.getX();
			break;
		case 'd':
			newHeroPosition[0] = myHero.getY();
			newHeroPosition[1] = myHero.getX() + 1;
			break;
		case 'f':
			if(myHero.hasSword() == false){
				myEagle.setX(myHero.getX());
				myEagle.setY(myHero.getY());
				myEagle.setFree(true);
			}
			break;

		default:
		}

		if (newHeroPosition[0] == mySword.getY()
				&& newHeroPosition[1] == mySword.getX()) {
			myHero.setHasSword(true);
			myHero.setX(newHeroPosition[1]);
			myHero.setY(newHeroPosition[0]);
			mySword.setX(0);
			mySword.setY(0);
			mySword.setDrawing('W');
		}
		switch (matrix[newHeroPosition[0]][newHeroPosition[1]]) {
		case ' ':

			myHero.setX(newHeroPosition[1]);
			myHero.setY(newHeroPosition[0]);
			break;

		case 'S':
			int verification = 0;
			for (int i = 0; i < dragons.size(); i++)
				if (dragons.elementAt(i).dragonIsInHell)
					verification++;
			if (verification == dragons.size()) {
				exit = true;
				matrix[newHeroPosition[0]][newHeroPosition[1]] = myHero
						.getDrawing();

				myHero.setX(newHeroPosition[1]);
				myHero.setY(newHeroPosition[0]);
				System.out.println("WIN!");
			}
			break;

		default:
			break;
		}


		if(myEagle.isFree())
			moveEagle();

		for (int i = 0; i < dragons.size(); i++)
			if (!dragons.elementAt(i).dragonIsInHell)
				dragons.elementAt(i).moveDragon(mySword, matrix);


		if (exit == false) {

			for (int i = 0; i < dragons.size(); i++){

				if ((Math.abs(dragons.elementAt(i).getY() - myHero.getY()) == 0 && Math
						.abs(dragons.elementAt(i).getX() - myHero.getX()) == 1)
						|| (Math.abs(dragons.elementAt(i).getY() - myHero.getY()) == 1 && Math
						.abs(dragons.elementAt(i).getX() - myHero.getX()) == 0)
						||dragons.elementAt(i).getY() - myHero.getY() == 0 && dragons.elementAt(i).getX() - myHero.getX() == 0) {

					if (myHero.hasSword() == false) {
						myHero.matar();
						System.out.println("Morre heroi");
						exit = true;
					} else if (myHero.getDrawing() == 'A') {
						System.out.println("Morre diabo!");

						dragons.elementAt(i).dragonIsInHell = true;
						dragons.elementAt(i).setDrawing('W');
						dragons.elementAt(i).setX(0);
						dragons.elementAt(i).setY(0);
					}
				}



			}
		}

		return exit;
	}

	public static void processOpSemMoveDragon(String op) {


		char movement = op.charAt(0);
		int[] newHeroPosition = new int[2];
		switch (movement) {
		case 'w':
			newHeroPosition[0] = myHero.getY() - 1;
			newHeroPosition[1] = myHero.getX();
			break;
		case 'a':
			newHeroPosition[0] = myHero.getY();
			newHeroPosition[1] = myHero.getX() - 1;
			break;
		case 's':
			newHeroPosition[0] = myHero.getY() + 1;
			newHeroPosition[1] = myHero.getX();
			break;
		case 'd':
			newHeroPosition[0] = myHero.getY();
			newHeroPosition[1] = myHero.getX() + 1;
			break;
		case 'f':
			if(myHero.hasSword() == false){
				myEagle.setX(myHero.getX());
				myEagle.setY(myHero.getY());
				myEagle.setFree(true);
			}
			break;

		default:
		}

		if (newHeroPosition[0] == mySword.getY()
				&& newHeroPosition[1] == mySword.getX()) {
			myHero.setHasSword(true);
			myHero.setX(newHeroPosition[1]);
			myHero.setY(newHeroPosition[0]);
			mySword.setX(0);
			mySword.setY(0);
			mySword.setDrawing('W');
		}
		switch (matrix[newHeroPosition[0]][newHeroPosition[1]]) {
		case ' ':

			myHero.setX(newHeroPosition[1]);
			myHero.setY(newHeroPosition[0]);
			break;

		case 'S':
			int verification = 0;
			if (dragonTestes.dragonIsInHell)
				verification++;
			if (verification == 1) {
				matrix[newHeroPosition[0]][newHeroPosition[1]] = myHero
						.getDrawing();

				myHero.setX(newHeroPosition[1]);
				myHero.setY(newHeroPosition[0]);
				System.out.println("WIN!");
			}
			break;

		default:
			break;
		}


		if(myEagle.isFree())
			moveEagle();

		if ((Math.abs(dragonTestes.getY() - myHero.getY()) == 0 && Math
				.abs(dragonTestes.getX() - myHero.getX()) == 1)
				|| (Math.abs(dragonTestes.getY() - myHero.getY()) == 1 && Math
				.abs(dragonTestes.getX() - myHero.getX()) == 0)
				||dragonTestes.getY() - myHero.getY() == 0 && dragonTestes.getX() - myHero.getX() == 0) {

			if (myHero.hasSword() == false) {
				myHero.matar();
				System.out.println("Morre heroi");
			} else if (myHero.getDrawing() == 'A') {
				System.out.println("Morre diabo!");

				dragonTestes.dragonIsInHell = true;
				dragonTestes.setDrawing('W');
				dragonTestes.setX(0);
				dragonTestes.setY(0);
			}



		}
	}


	static void moveEagle(){
		int dx,dy;
		if(myEagle.hasSword()){ // tem espada, volta para o heroi
			dx = myEagle.getX() - myHero.getX();
			dy = myEagle.getY() - myHero.getY();
			if(dx == 0 && dy == 0){
				myEagle.setFree(false);
				myHero.setHasSword(true);
			}

		}else{ // nÃ£o tem espada
			dx = myEagle.getX() - mySword.getX();
			dy = myEagle.getY() - mySword.getY();
			if(dx == 0 && dy == 0){
				myEagle.setHasSword(true);
				mySword.setX(0);
				mySword.setY(0);
				mySword.setDrawing('W');
				return;
			}

		}

		if(dx > 0)
			myEagle.setX(myEagle.getX() - 1);
		else
			if(dx < 0)
				myEagle.setX(myEagle.getX() + 1);

		if(dy > 0)
			myEagle.setY(myEagle.getY() - 1);
		else
			if(dy < 0)
				myEagle.setY(myEagle.getY() + 1);
	}

	public static void addDragon(Dragon myDragon){
		dragonTestes = myDragon;
	}

	static void printMatrix() {

		for (int row = 0; row < NUM_ROWS; row++) {
			for (int column = 0; column < NUM_COLUMNS; column++) {
				System.out.print(matrix[row][column] + " ");
			}
			System.out.println();
		}
	}

	public void createDefaultMatrix() {

		char[][] matrixDefault = new char[10][10];

		matrixDefault[0][0] = 'X';
		matrixDefault[0][1] = 'X';
		matrixDefault[0][2] = 'X';
		matrixDefault[0][3] = 'X';
		matrixDefault[0][4] = 'X';
		matrixDefault[0][5] = 'X';
		matrixDefault[0][6] = 'X';
		matrixDefault[0][7] = 'X';
		matrixDefault[0][8] = 'X';
		matrixDefault[0][9] = 'X';

		matrixDefault[1][0] = 'X';
		matrixDefault[1][1] = 'H';
		matrixDefault[1][2] = ' ';
		matrixDefault[1][3] = ' ';
		matrixDefault[1][4] = ' ';
		matrixDefault[1][5] = ' ';
		matrixDefault[1][6] = ' ';
		matrixDefault[1][7] = ' ';
		matrixDefault[1][8] = ' ';
		matrixDefault[1][9] = 'X';

		matrixDefault[2][0] = 'X';
		matrixDefault[2][1] = ' ';
		matrixDefault[2][2] = 'X';
		matrixDefault[2][3] = 'X';
		matrixDefault[2][4] = ' ';
		matrixDefault[2][5] = 'X';
		matrixDefault[2][6] = ' ';
		matrixDefault[2][7] = 'X';
		matrixDefault[2][8] = ' ';
		matrixDefault[2][9] = 'X';

		matrixDefault[3][0] = 'X';
		matrixDefault[3][1] = ' ';
		matrixDefault[3][2] = 'X';
		matrixDefault[3][3] = 'X';
		matrixDefault[3][4] = ' ';
		matrixDefault[3][5] = 'X';
		matrixDefault[3][6] = ' ';
		matrixDefault[3][7] = 'X';
		matrixDefault[3][8] = ' ';
		matrixDefault[3][9] = 'X';

		matrixDefault[4][0] = 'X';
		matrixDefault[4][1] = ' ';
		matrixDefault[4][2] = 'X';
		matrixDefault[4][3] = 'X';
		matrixDefault[4][4] = ' ';
		matrixDefault[4][5] = 'X';
		matrixDefault[4][6] = ' ';
		matrixDefault[4][7] = 'X';
		matrixDefault[4][8] = ' ';
		matrixDefault[4][9] = 'X';

		matrixDefault[5][0] = 'X';
		matrixDefault[5][1] = ' ';
		matrixDefault[5][2] = ' ';
		matrixDefault[5][3] = ' ';
		matrixDefault[5][4] = ' ';
		matrixDefault[5][5] = ' ';
		matrixDefault[5][6] = ' ';
		matrixDefault[5][7] = 'X';
		matrixDefault[5][8] = ' ';
		matrixDefault[5][9] = 'S';

		matrixDefault[6][0] = 'X';
		matrixDefault[6][1] = ' ';
		matrixDefault[6][2] = 'X';
		matrixDefault[6][3] = 'X';
		matrixDefault[6][4] = ' ';
		matrixDefault[6][5] = 'X';
		matrixDefault[6][6] = ' ';
		matrixDefault[6][7] = 'X';
		matrixDefault[6][8] = ' ';
		matrixDefault[6][9] = 'X';

		matrixDefault[7][0] = 'X';
		matrixDefault[7][1] = ' ';
		matrixDefault[7][2] = 'X';
		matrixDefault[7][3] = 'X';
		matrixDefault[7][4] = ' ';
		matrixDefault[7][5] = 'X';
		matrixDefault[7][6] = ' ';
		matrixDefault[7][7] = 'X';
		matrixDefault[7][8] = ' ';
		matrixDefault[7][9] = 'X';

		matrixDefault[8][0] = 'X';
		matrixDefault[8][1] = 'E';
		matrixDefault[8][2] = 'X';
		matrixDefault[8][3] = 'X';
		matrixDefault[8][4] = ' ';
		matrixDefault[8][5] = ' ';
		matrixDefault[8][6] = ' ';
		matrixDefault[8][7] = ' ';
		matrixDefault[8][8] = ' ';
		matrixDefault[8][9] = 'X';

		matrixDefault[9][0] = 'X';
		matrixDefault[9][1] = 'X';
		matrixDefault[9][2] = 'X';
		matrixDefault[9][3] = 'X';
		matrixDefault[9][4] = 'X';
		matrixDefault[9][5] = 'X';
		matrixDefault[9][6] = 'X';
		matrixDefault[9][7] = 'X';
		matrixDefault[9][8] = 'X';
		matrixDefault[9][9] = 'X';

		this.matrixDefault = matrixDefault;
	}

}
