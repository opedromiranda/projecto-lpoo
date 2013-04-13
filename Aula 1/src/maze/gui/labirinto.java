package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;

import org.json.JSONArray;
import org.json.JSONObject;

import maze.logic.Celula;
import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.Sword;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class labirinto extends JFrame {

	private JPanel contentPane;
	private int numDragons;
	private int tamanho;
	static char[][] matrix;
	static int[] heroPosition, dragonPosition;
	static int[] newHeroPosition = new int[2];
	private KeyListener keyMove;

	static Hero myHero = new Hero();
	static Sword mySword = new Sword();
	static Eagle myEagle = new Eagle();

	static Vector<Celula> caminho = new Vector<Celula>();
	static Vector<Celula> dragoes = new Vector<Celula>();
	static Vector<Dragon> dragons = new Vector<Dragon>();
	static Maze maze;

	/**
	 * Launch the application.
	 */

	public void printMaze() {

		contentPane.removeAll();

		for (int i = 0; i < dragons.size(); i++) {
			matrix[dragons.elementAt(i).getY()][dragons.elementAt(i).getX()] = dragons
					.elementAt(i).getDrawing();
		}

		for (int c1 = 0; c1 < maze.getLado(); c1++) {
			for (int c2 = 0; c2 < maze.getLado(); c2++) {
				JPanel panel;
				if (c1 == myHero.getY() && c2 == myHero.getX()
						&& myHero.getDrawing() == 'A') {
					panel = new ash();
					panel.setLayout(new BorderLayout());
				} else if (c1 == myHero.getY() && c2 == myHero.getX()
						&& myHero.getDrawing() == 'H') {
					panel = new heroi();
					panel.setLayout(new BorderLayout());
				} else if (c1 == mySword.getY() && c2 == mySword.getX()
						&& mySword.getDrawing() == 'E') {
					panel = new espada();
					panel.setLayout(new BorderLayout());
				} else if (c1 == mySword.getY() && c2 == mySword.getX()
						&& mySword.getY() == 0 && mySword.getX() == 0) {
					panel = new parede();
					panel.setLayout(new BorderLayout());
				} else if (c1 == myEagle.getY() && c2 == myEagle.getX()
						&& myEagle.isFree()) {
					panel = new aguia();
					panel.setLayout(new BorderLayout());
				} else if (matrix[c1][c2] == 'X') {
					panel = new parede();
					panel.setLayout(new BorderLayout());
				} else if (matrix[c1][c2] == ' ') {
					panel = new caminho();
					panel.setLayout(new BorderLayout());
				} else if (matrix[c1][c2] == 'A') {
					panel = new ash();
					panel.setLayout(new BorderLayout());
				} else if (matrix[c1][c2] == 'W') {
					panel = new parede();
					panel.setLayout(new BorderLayout());
				} else if (matrix[c1][c2] == 'S') {
					panel = new caminho();
					panel.setLayout(new BorderLayout());
				} else {
					panel = new dragao();
					panel.setLayout(new BorderLayout());
				}

				contentPane.add(panel);
			}
		}

		contentPane.validate();

		for (int i = 0; i < dragons.size(); i++) {
			matrix[dragons.elementAt(i).getY()][dragons.elementAt(i).getX()] = ' ';
		}
	}

	public labirinto(int tamanho, int numDragons) {
		this.tamanho = tamanho;
		this.numDragons = numDragons;

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(tamanho, tamanho));

		keyMove = new KeyMoveListener();
		contentPane.addKeyListener(keyMove);
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();

		maze = new Maze(tamanho);
		matrix = maze.generate();

		caminho = maze.getCaminho();

		Random r = new Random();
		Celula posJogador, posDragao, posEspada;
		posJogador = caminho.elementAt(r.nextInt(caminho.size()));
		posEspada = caminho.elementAt(r.nextInt(caminho.size()));

		for (int i = 0; i < numDragons; i++) {
			Dragon myDragon = new Dragon();
			posDragao = caminho.elementAt(r.nextInt(caminho.size()));
			myDragon.setX(posDragao.getX());
			myDragon.setY(posDragao.getY());

			dragons.add(myDragon);

		}
		myHero.setX(posJogador.getX());
		myHero.setY(posJogador.getY());

		System.out.print(posJogador.getX() + " pos Y " + posJogador.getY());

		mySword.setX(posEspada.getX());
		mySword.setY(posEspada.getY());

		printMaze();

	}

	/**
	 * This constructor assumes there are save files.
	 * 
	 * @throws IOException
	 */
	public labirinto() throws IOException {
		// TODO Auto-generated constructor stub
		String gameData = readFile("save.txt");

		
		JSONObject json = new JSONObject(gameData);

		JSONObject json_eagle = json.getJSONObject("eagle");
		
		myEagle.setX(json_eagle.getInt("x"));
		myEagle.setY(json_eagle.getInt("y"));
		myEagle.setHasSword(json_eagle.getBoolean("has_sword"));
		myEagle.setFree(json_eagle.getBoolean("is_free"));

		myHero.setX(json.getInt("jogador_x"));
		myHero.setY(json.getInt("jogador_y"));
		myHero.setHasSword(json.getBoolean("has_sword"));
		
		mySword.setX(json.getInt("sword_x"));
		mySword.setY(json.getInt("sword_y"));

		
		
		
		JSONArray json_dragons = json.getJSONArray("dragons");
		for (int c = 0; c < json_dragons.length(); c++) {
			JSONObject json_dragon = json_dragons.getJSONObject(c);
			Dragon d = new Dragon();
			d.setX(json_dragon.getInt("x"));
			d.setY(json_dragon.getInt("y"));
			dragons.add(d);
		}

		int lado = json.getInt("lado_labirinto");
		
		maze = new Maze(lado);
		
		matrix = new char[lado][lado];
		loadMazeFromFile();
		
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(lado, lado));

		keyMove = new KeyMoveListener();
		contentPane.addKeyListener(keyMove);
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		
		
		printMaze();
	}

	/**
	 * 
	 * @return 0 if load is successful, 1 if not.
	 * @throws IOException
	 */
	public static int loadMazeFromFile() throws IOException {

		FileInputStream fstream = new FileInputStream("maze.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		int y = 0;
		
		while((strLine = br.readLine()) != null) {
			System.out.print(strLine + "\n");
			for (int c = 0; c < strLine.length(); c++) {
				
				matrix[y][c] = strLine.charAt(c);
			}
			y++;
		}
		in.close();

		System.out.print("FILE\n");
		return 0;
	}

	/**
	 * 
	 * @param name
	 *            of file
	 * @return all data inside file as a String
	 * @throws IOException
	 */
	private static String readFile(String name) throws IOException {
		FileInputStream stream = new FileInputStream(name);
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	private boolean processOp() {

		boolean exit = false;

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
				JDialog ganhou = new ganhou();
				setVisible(false);
			}
			break;

		default:
			break;
		}

		if (myEagle.isFree())
			moveEagle();

		for (int i = 0; i < dragons.size(); i++)
			if (!dragons.elementAt(i).dragonIsInHell)
				dragons.elementAt(i).moveDragon(mySword, matrix);

		if (exit == false) {

			for (int i = 0; i < dragons.size(); i++) {

				if ((Math.abs(dragons.elementAt(i).getY() - myHero.getY()) == 0 && Math
						.abs(dragons.elementAt(i).getX() - myHero.getX()) == 1)
						|| (Math.abs(dragons.elementAt(i).getY()
								- myHero.getY()) == 1 && Math.abs(dragons
								.elementAt(i).getX() - myHero.getX()) == 0)
						|| dragons.elementAt(i).getY() - myHero.getY() == 0
						&& dragons.elementAt(i).getX() - myHero.getX() == 0) {

					if (myHero.hasSword() == false) {
						System.out.println("Morre heroi");
						JDialog perdeu = new perdeu();
						setVisible(false);
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

	static void moveEagle() {
		int dx, dy;
		if (myEagle.hasSword()) { // tem espada, volta para o heroi
			dx = myEagle.getX() - myHero.getX();
			dy = myEagle.getY() - myHero.getY();
			if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1)
					|| (dx == -1 && dy == 0) || (dx == 0 && dy == -1)) {
				myEagle.setFree(false);
				myHero.setHasSword(true);
			}

		} else { // não tem espada
			dx = myEagle.getX() - mySword.getX();
			dy = myEagle.getY() - mySword.getY();
			if (dx == 0 && dy == 0) {
				myEagle.setHasSword(true);
				mySword.setX(0);
				mySword.setY(0);
				mySword.setDrawing('W');
				return;
			}

		}

		if (dx > 0)
			myEagle.setX(myEagle.getX() - 1);
		else if (dx < 0)
			myEagle.setX(myEagle.getX() + 1);

		if (dy > 0)
			myEagle.setY(myEagle.getY() - 1);
		else if (dy < 0)
			myEagle.setY(myEagle.getY() + 1);
	}

	private class KeyMoveListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				newHeroPosition[0] = myHero.getY();
				newHeroPosition[1] = myHero.getX() + 1;
				break;
			case KeyEvent.VK_DOWN:
				newHeroPosition[0] = myHero.getY() + 1;
				newHeroPosition[1] = myHero.getX();
				break;
			case KeyEvent.VK_LEFT:
				newHeroPosition[0] = myHero.getY();
				newHeroPosition[1] = myHero.getX() - 1;
				break;
			case KeyEvent.VK_UP:
				newHeroPosition[0] = myHero.getY() - 1;
				newHeroPosition[1] = myHero.getX();
				break;
			case KeyEvent.VK_F:
				if (myHero.hasSword() == false) {
					myEagle.setX(myHero.getX());
					myEagle.setY(myHero.getY());
					myEagle.setFree(true);
				}
				break;
			case KeyEvent.VK_S:
				saveGame();

				break;

			default:
			}
			processOp();
			printMaze();

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * 
	 * @return 0 if game was saved, 1 if encountered error.
	 */
	public int saveGame() {
		JSONObject main = new JSONObject();
		main.put("lado_labirinto", maze.getLado() + "");
		main.put("jogador_x", myHero.getX());
		main.put("jogador_y", myHero.getY());
		main.put("has_sword", myHero.hasSword());

		main.put("sword_x", mySword.getX());
		main.put("sword_y", mySword.getY());

		JSONArray json_dragons = new JSONArray();

		for (int c = 0; c < dragons.size(); c++) {
			json_dragons.put(new JSONObject().put("x",
					dragons.elementAt(c).getX()).put("y",
					dragons.elementAt(c).getY()));
		}

		JSONObject eagle_attributes = new JSONObject();
		eagle_attributes.put("has_sword", myEagle.hasSword());
		eagle_attributes.put("is_free", myEagle.isFree());
		eagle_attributes.put("x", myEagle.getX());
		eagle_attributes.put("y", myEagle.getY());

		main.put("eagle", eagle_attributes);
		main.put("dragons", json_dragons);

		// String mazeTXT = maze.getLado() + "\n";
		String mazeTXT = "";

		for (int c = 0; c < maze.getLado(); c++) {
			for (int v = 0; v < maze.getLado(); v++) {
				mazeTXT += matrix[c][v];
			}
			mazeTXT += "\n";
		}
		try {
			FileWriter fstream = new FileWriter("save.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(main.toString(5));
			out.close();

			fstream = new FileWriter("maze.txt");
			out = new BufferedWriter(fstream);
			out.write(mazeTXT);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}

		return 0;
	}
}
