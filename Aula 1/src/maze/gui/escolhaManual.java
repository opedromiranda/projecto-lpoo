package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Window;
import maze.gui.nada;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class escolhaManual extends JFrame {

	private JPanel contentPane;
	private JTextField txtSelecioneOTamanho;
	public static escolha frame = new escolha();
	public int dragons;
	public int tamanho;


	public escolhaManual() {
		
		dragons = 0;
		tamanho = 0;
		
		setTitle("OP\u00C7\u00D5ES");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSelecioneOTamanho = new JTextField();
		txtSelecioneOTamanho.setEditable(false);
		txtSelecioneOTamanho.setText("Selecione o tamanho da labirinto (AxA) ( > 5) :");
		txtSelecioneOTamanho.setBounds(10, 42, 269, 20);
		contentPane.add(txtSelecioneOTamanho);
		txtSelecioneOTamanho.setColumns(10);
		
		final JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				 Object valor = spinner.getValue();
				 tamanho = valor.hashCode();
			}
		});
		spinner.setBounds(289, 42, 62, 20);
		contentPane.add(spinner);
		
		final JButton btnNewButton = new JButton("Criar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tamanho < 5){
					JDialog nada = new nada();
				}
				else{
					labirintoManual labirintoManual = new labirintoManual(tamanho);
					setVisible(false);
					
				}
				
					
			}
		});
		btnNewButton.setBounds(50, 153, 131, 57);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Voltar atr\u00E1s");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btnNewButton_1.setBounds(220, 153, 131, 57);
		contentPane.add(btnNewButton_1);
	}
	

}
