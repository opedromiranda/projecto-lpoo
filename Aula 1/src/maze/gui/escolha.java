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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class escolha extends JFrame {

	private JPanel contentPane;
	private JTextField txtSelecioneOTamanho;
	private JTextField txtQuantosDragesQuer;
	public static escolha frame = new escolha();
	public int dragons;
	public int tamanho;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public escolha() {
		
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
		txtSelecioneOTamanho.setBounds(10, 42, 247, 20);
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
		
		txtQuantosDragesQuer = new JTextField();
		txtQuantosDragesQuer.setEditable(false);
		txtQuantosDragesQuer.setText("Quantos drag\u00F5es quer (> 0) ?");
		txtQuantosDragesQuer.setColumns(10);
		txtQuantosDragesQuer.setBounds(10, 105, 247, 20);
		contentPane.add(txtQuantosDragesQuer);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Object valor2 = spinner_1.getValue();
				dragons = valor2.hashCode();
				
			}
		});
		spinner_1.setBounds(289, 105, 62, 20);
		contentPane.add(spinner_1);
		
		final JButton btnNewButton = new JButton("Jogar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dragons == 0 || tamanho < 5){
					JDialog nada = new nada();
				}
				else{
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
