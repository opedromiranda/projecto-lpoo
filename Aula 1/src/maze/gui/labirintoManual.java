package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.json.JSONArray;
import org.json.JSONObject;

import maze.logic.Celula;
import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.Sword;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class labirintoManual extends JFrame {

	private JPanel contentPane;
	MyMouseListener mouseClicked;
	private JPanel panel;
	private JPanel panel_1;
	static char[][] matrix;
	static Vector<Celula> caminho = new Vector<Celula>();
	static Vector<Celula> dragoes = new Vector<Celula>();
	static Vector<Dragon> dragons = new Vector<Dragon>();
	static Maze maze;
	String elemento;

	static Hero myHero = null;
	static Sword mySword = new Sword();
	static Eagle myEagle = new Eagle();

	static Vector<JPanel> elementos = new Vector<JPanel>();

	static int opcao_escolhida = 0;

	static final int PAREDE = 0;
	static final int HEROI = 1;
	static final int DRAGAO = 2;
	static final int SAIDA = 3;
	static final int CAMINHO = 4;
	static final int ESPADA = 5;

	static int lado; 
	
	public labirintoManual(int tamanho, int dragoes) {
		setVisible(true);
		setBounds(100, 100, 436, 286);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lado = tamanho;
		
		maze = new Maze(tamanho);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnHeroi = new JButton("Heroi");
		btnHeroi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = HEROI;
			}
		});
		panel.add(btnHeroi);

		JButton btnParede = new JButton("Parede");
		btnParede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = PAREDE;
			}
		});
		panel.add(btnParede);

		JButton btnDragao = new JButton("Drag\u00E3o");
		btnDragao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = DRAGAO;
			}
		});
		panel.add(btnDragao);

		JButton btnCaminho = new JButton("Caminho");
		btnCaminho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = CAMINHO;
			}
		});
		
		JButton btnEspada = new JButton("Espada");
		btnEspada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				opcao_escolhida = ESPADA;
				System.out.println(opcao_escolhida + "");
			}
		});
		panel.add(btnEspada);
		panel.add(btnCaminho);

		JButton btnSaida = new JButton("Sa\u00EDda");
		btnSaida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = SAIDA;
			}
		});
		panel.add(btnSaida);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				labirinto lab;
				try {
					lab = new labirinto("save_manual.txt", "maze_manual.txt");
					setVisible(false);
					
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnJogar);

		panel_1 = new JPanel();

		contentPane.add(panel_1, BorderLayout.CENTER);

		panel_1.setLayout(new GridLayout(13, 13)); // ALTERAR GRID
																// PARA TAMHO
																// QUANDO TESTAR
																// EM JANELA

		printMaze();

		mouseClicked = new MyMouseListener();
		panel_1.addMouseListener(mouseClicked);
		panel_1.setFocusable(true);
		panel_1.requestFocusInWindow();

	}

	public void printMaze() {

		panel_1.removeAll();

		for (int c1 = 0; c1 < maze.getLado(); c1++) {
			for (int i = 0; i < maze.getLado(); i++) {
				Elemento panel1;
				MyMouseListener mouseListener = new MyMouseListener();
				if (c1 == 0 || i == 0 || c1 == maze.getLado() - 1
						|| i == maze.getLado() - 1) {
					panel1 = new Elemento("parede.jpg");
					// panel1 = new parede();
					panel1.setLayout(new BorderLayout());
					panel1.addMouseListener(mouseListener);
				} else {
					panel1 = new Elemento("caminho.jpg");
					panel1.setLayout(new BorderLayout());
					panel1.addMouseListener(mouseListener);
				}
				elementos.add(panel1);
				panel_1.add(panel1);
			}
		}

		panel_1.validate();

	}

	private class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			for (int c = 0; c < elementos.size(); c++) {
				Elemento actual = (Elemento) elementos.elementAt(c);
				if (actual.getPath() == "heroi.jpg" && opcao_escolhida == HEROI) {
					System.out.println("HEROI JA EXISTE");
					Elemento novo2 = new Elemento("caminho.jpg");
					elementos.setElementAt(novo2, c);
					panel_1.add(novo2, c);
					panel_1.remove(c + 1);
					
				}

				if (arg0.getSource() == elementos.elementAt(c)) {

					Elemento novo = new Elemento("caminho.jpg");

					switch (opcao_escolhida) {
					case HEROI:
						myHero = new Hero();
						novo.setImage("heroi.jpg");
						break;
					case DRAGAO:
						novo.setImage("dragao.jpg");
						break;
					case SAIDA:
						novo.setImage("saida.jpg");
						break;
					case PAREDE:
						novo.setImage("parede.jpg");
						break;
					case CAMINHO:
						novo.setImage("caminho.jpg");
						break;
					case ESPADA:
						novo.setImage("espada.jpg");
						break;
					default:
						break;
					}

					novo.setLayout(new BorderLayout());
					novo.addMouseListener(mouseClicked);
					novo.setFocusable(true);
					elementos.setElementAt(novo, c);
					panel_1.add(novo, c);
					panel_1.remove(c + 1);
				}
			}

			save();
			panel_1.validate();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	
	void save(){
		char[][] m = new char[lado][lado];
		int x = 0;
		int y = 0;
		char c = 'W';
		
		JSONObject main = new JSONObject();
		main.put("lado_labirinto",lado);
		
		JSONObject eagle_attributes = new JSONObject();
		eagle_attributes.put("has_sword", false);
		eagle_attributes.put("is_free", false);
		eagle_attributes.put("x", 0);
		eagle_attributes.put("y", 0);
		
		
		JSONArray json_dragons = new JSONArray();
		
		for(int i = 0; i < elementos.size(); i++){
			Elemento elem = (Elemento) elementos.elementAt(i);
			
			//if(elem.getPath() == "heroi.jpg")
				//c = 'H';
			if(elem.getPath() == "dragao.jpg"){
				json_dragons.put(new JSONObject()
									.put("x", y)
									.put("y", x));
			}
			if(elem.getPath() == "heroi.jpg"){
				main.put("jogador_x", y);
				main.put("jogador_y", x);
				main.put("has_sword", false);

			}
			
			if(elem.getPath() == "espada.jpg"){
				main.put("sword_x", y);
				main.put("sword_y", x);

			}
			if(elem.getPath() == "parede.jpg")
				c = 'X';
			else if(elem.getPath() == "canto.jpg")
				c = 'W';
			else if(elem.getPath() == "saida.jpg")
				c = 'S';
			//else if(elem.getPath() == "dragao.jpg")
				//c = 'D';
			//else if(elem.getPath() == "espada.jpg")
				//c = 'E';
			else
			//if(elem.getPath() == "caminho.jpg")
				c = ' ';
			m[y][x] = c;
			x++;
			if(x == lado){
				x=0;
				y++;
			}
			
		}
		
		main.put("eagle", eagle_attributes);
		main.put("dragons", json_dragons);

		
		String mazeTXT = "";

		for (int i = 0; i < lado; i++) {
			for (int v = 0; v < lado; v++) {
				mazeTXT += m[i][v];
			}
			mazeTXT += "\n";
		}
		try {
			FileWriter fstream = new FileWriter("save_manual.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(main.toString(5));
			out.close();
			fstream = new FileWriter("maze_manual.txt");
			out = new BufferedWriter(fstream);
			out.write(mazeTXT);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
}
