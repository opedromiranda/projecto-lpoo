package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class perdeu extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public perdeu() {
		final JPanel contentPanel = new JPanel();
		JTextField Gameover;
		
		
		setType(Type.POPUP);
		setTitle("ERRO");
		setResizable(false);
		setBounds(100, 100, 374, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			Gameover = new JTextField();
			Gameover.setEditable(false);
			Gameover.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Gameover.setBounds(106, 26, 145, 56);
			Gameover.setText("Game Over");
			contentPanel.add(Gameover);
			Gameover.setColumns(10);
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
