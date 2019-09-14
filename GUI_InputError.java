package GZIP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_InputError {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void mainInput() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_InputError window = new GUI_InputError();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GUI_InputError() {
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
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(145, 11, 238, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPleaseEnterFiles = new JLabel("The back /forward window is empty/too long.");
		lblPleaseEnterFiles.setForeground(Color.RED);
		lblPleaseEnterFiles.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPleaseEnterFiles.setBounds(100, 47, 431, 32);
		frame.getContentPane().add(lblPleaseEnterFiles);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Gui_ErrorMessage.class.getResource("/GZIP/err.jpg")));
		label.setBounds(139, 97, 244, 125);
		frame.getContentPane().add(label);
		
		JLabel lblTryAgainNext = new JLabel("Try again next time!!");
		lblTryAgainNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTryAgainNext.setForeground(Color.RED);
		lblTryAgainNext.setBounds(166, 71, 184, 28);
		frame.getContentPane().add(lblTryAgainNext);
		
		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(204, 220, 89, 24);
		frame.getContentPane().add(btnExit);
	}
}