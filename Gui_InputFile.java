package GZIP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class Gui_InputFile {

	public JFrame frame;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {	
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});}

	/**
	 * @wbp.parser.entryPoint
	 */
	public Gui_InputFile() {
		initialize();}
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame();
		int height = screenSize.height;
	    int width = screenSize.width;
	    frame.setSize(width/2, height/2);
	    frame.setLocationRelativeTo(null);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(153, 102, 0));
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 424, 115);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPleaseChooseFile = new JLabel("please choose the input file");
		lblPleaseChooseFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseChooseFile.setToolTipText("");
		lblPleaseChooseFile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblPleaseChooseFile.setBackground(new Color(153, 102, 0));
		lblPleaseChooseFile.setForeground(new Color(255, 255, 255));
		lblPleaseChooseFile.setBounds(0, 0, 398, 76);
		frame.getContentPane().add(lblPleaseChooseFile);
	}}

