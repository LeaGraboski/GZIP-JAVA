package GZIP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Gui_OutputFile {

public JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					
				
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
	public Gui_OutputFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
	    frame.setSize(585, 115);
	    frame.setLocationRelativeTo(null);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(153, 102, 0));
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 424, 115);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPleaseChooseFile = new JLabel("Please choose the output file");
		lblPleaseChooseFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseChooseFile.setToolTipText("");
		lblPleaseChooseFile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		lblPleaseChooseFile.setBackground(new Color(153, 102, 0));
		lblPleaseChooseFile.setForeground(new Color(255, 255, 255));
		lblPleaseChooseFile.setBounds(0, 0, 408, 76);
		frame.getContentPane().add(lblPleaseChooseFile);
	}

}

