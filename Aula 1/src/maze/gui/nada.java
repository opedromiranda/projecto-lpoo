package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class nada extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtQuantidadesErradas;

	public nada() {
		final JPanel contentPanel = new JPanel();
		JTextField txtQuantidadesErradas;
		
		
		setType(Type.POPUP);
		setTitle("ERRO");
		setResizable(false);
		setBounds(100, 100, 374, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtQuantidadesErradas = new JTextField();
			txtQuantidadesErradas.setEditable(false);
			txtQuantidadesErradas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtQuantidadesErradas.setBounds(106, 26, 145, 56);
			txtQuantidadesErradas.setText("Quantidades erradas");
			contentPanel.add(txtQuantidadesErradas);
			txtQuantidadesErradas.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
