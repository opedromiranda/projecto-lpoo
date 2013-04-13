package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import maze.logic.Celula;
import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.Sword;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
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

	
	static Hero myHero = new Hero();
	static Sword mySword = new Sword();
	static Eagle myEagle = new Eagle();

	static Vector<JPanel> elementos = new Vector<JPanel>();

	static int opcao_escolhida = 0;
	
	static final int PAREDE = 0;
	static final int HEROI = 1;
	static final int DRAGAO = 2;
	static final int SAIDA = 3;
	static final int CAMINHO = 4;
	
	
	public labirintoManual(int tamanho, int dragoes) {
		setVisible(true);
		setBounds(100, 100, 436, 286);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		maze = new Maze(tamanho);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_1 = new JButton("Heroi");
		btnNewButton_1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = HEROI;
			}			
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Parede");
		btnNewButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = PAREDE;
			}			
		});
		panel.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Drag\u00E3o");
		btnNewButton_2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = DRAGAO;
			}			
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Caminho");
		btnNewButton_3.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = CAMINHO;
			}			
		});
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Saída");
		btnNewButton_4.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				opcao_escolhida = SAIDA;
			}			
		});
		panel.add(btnNewButton_4);
		
		panel_1 = new JPanel();

		contentPane.add(panel_1, BorderLayout.CENTER);

		panel_1.setLayout(new GridLayout(tamanho, tamanho)); // ALTERAR GRID
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
		
			System.out.println(": " + arg0.getSource().toString());

			if (arg0.getSource() instanceof Elemento) {

				Elemento parede = (Elemento) arg0.getSource();

				System.out.println("Path: " + parede.getPath());
				
				
				switch(opcao_escolhida){
				case HEROI:
					parede.setImage("heroi.jpg");
					break;
				case DRAGAO:
					parede.setImage("dragao.jpg");
					break;
				case SAIDA:
					parede.setImage("saida.jpg");
					break;
				case PAREDE:
					parede.setImage("parede.jpg");
					break;
				case CAMINHO:
					parede.setImage("caminho.jpg");
					break;
				default:
					break;
				}
				//parede.setImage("caminho.jpg");

				System.out.println("Num elementos: "
						+ panel_1.getComponentCount());

				if (parede.foiClicado() == false) {
					System.out.println("Nunca foi clicado");
					parede.setfoiClicado(true);

				} else {
					System.out.println("Já foi clicado");

				}

			}
			else
				System.out.println(arg0);

			panel_1.removeAll();

			System.out.println("NUMERO DE ELEMENTOS: " + elementos.size());
			for (int c = 0; c < elementos.size(); c++) {
				
				JPanel p = new Elemento("caminho.jpg");
				p.setLayout(new BorderLayout());
				
				Elemento elemActual = (Elemento) elementos.elementAt(c);
				if (elemActual.getPath() == "parede.jpg") {
					p = new Elemento("parede.jpg");
					elementos.setElementAt(p, c);
					
				}
				p.addMouseListener(mouseClicked);
				p.setFocusable(true);
				panel_1.add(p);
				
			}

			
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
}
