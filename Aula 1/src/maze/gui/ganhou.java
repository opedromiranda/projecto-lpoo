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

public class ganhou extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public ganhou() {
		final JPanel contentPanel = new JPanel();
		JTextField GANHOU;
		
		
		setType(Type.POPUP);
		setTitle("WIN");
		setResizable(false);
		setBounds(100, 100, 374, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			GANHOU = new JTextField();
			GANHOU.setEditable(false);
			GANHOU.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GANHOU.setBounds(106, 26, 145, 56);
			GANHOU.setText("GANHOU");
			contentPanel.add(GANHOU);
			GANHOU.setColumns(10);
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
