package GZIP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Gui_ErrorMessage {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_ErrorMessage window = new Gui_ErrorMessage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_ErrorMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 557, 294);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wrong choise !!");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(145, 11, 238, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPleaseEnterFiles = new JLabel("Please enter files with the same format type!!!");
		lblPleaseEnterFiles.setForeground(Color.RED);
		lblPleaseEnterFiles.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPleaseEnterFiles.setBounds(81, 63, 460, 32);
		frame.getContentPane().add(lblPleaseEnterFiles);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Gui_ErrorMessage.class.getResource("/GZIP/err.jpg")));
		label.setBounds(159, 108, 238, 136);
		frame.getContentPane().add(label);
	}

	
}
