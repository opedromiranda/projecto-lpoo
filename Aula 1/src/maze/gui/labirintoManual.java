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
	mouseClicked mouseClicked;
	private JPanel panel;
	private JPanel panel_1;
	static char[][] matrix;
	static Vector<Celula> caminho = new Vector<Celula>();
	static Vector<Celula> dragoes = new Vector<Celula> ();
	static Vector<Dragon> dragons = new Vector<Dragon>();
	static Maze maze;
	String elemento;
	
	static Hero myHero = new Hero();
	static Sword mySword = new Sword();
	static Eagle myEagle = new Eagle();

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
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Parede");
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Drag\u00E3o");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Caminho");
		panel.add(btnNewButton_3);
		
		panel_1 = new JPanel();
		
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(13, 13));                //ALTERAR GRID PARA TAMHO QUANDO TESTAR EM JANELA
		
		printMaze();
		

		mouseClicked = new mouseClicked();
		panel_1.addMouseListener(mouseClicked);
		panel_1.setFocusable(true);
		panel_1.requestFocusInWindow();
		
		
		
	}
	
	public void printMaze() {

		
		panel_1.removeAll();
		
		
		for (int c1 = 0; c1 < maze.getLado(); c1++) {
			for( int i = 0; i < maze.getLado(); i++){
				JPanel panel1;
				if(c1 == 0 || i == 0 || c1 == maze.getLado() - 1 || i == maze.getLado() - 1){
					panel1 = new parede();
					panel1.setLayout(new BorderLayout());
				}
				else
				{
				panel1 = new caminho();
				panel1.setLayout(new BorderLayout());
				}
				
				panel_1.add(panel1);
			}
		}

		panel_1.validate();

		
	}
	
public void reprintMaze(JPanel alterar, String tipo ) {

		
		panel_1.removeAll();
		
		
		for (int c1 = 0; c1 < maze.getLado(); c1++) {
			for( int i = 0; i < maze.getLado(); i++){
				JPanel panel1;
				if(c1 == 0 || i == 0 || c1 == maze.getLado() - 1 || i == maze.getLado() - 1){
					panel1 = new parede();
					panel1.setLayout(new BorderLayout());
				}
				else
				{
				panel1 = new caminho();
				panel1.setLayout(new BorderLayout());
				}
				
				panel_1.add(panel1);
			}
		}

		panel_1.validate();

		
	}
	
	
	private class mouseClicked implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			int x = e.getX();
			int y = e.getY();
			
			
			
			panel2 = new parede();
			panel2.setLayout(new BorderLayout());
			
			
			repaintMaze(panel2, "parede");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

		

}
