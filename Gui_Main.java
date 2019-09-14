package GZIP;


import java.awt.EventQueue;
import java.awt.SystemColor;
//import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.filechooser.FileSystemView;

//import org.eclipse.wb.swing.FocusTraversalOnArray;

//import data_compress.integers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.IOException;

public class Gui_Main {

	private JTextField textField;
	private JTextField textField_1;
	public String input="";
	static String input1 = "";
	String str1,str2,str3 ="";
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_Main window = new Gui_Main();
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
	public Gui_Main() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(681, 384);
		frame.setLocationRelativeTo(null);
		frame.setBackground(new Color(10,10, 10));
		frame.getContentPane().setBackground(new Color(204, 153, 51));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Gui_Main.class.getResource("/GZIP/gzip1-1.jpg")));
		label.setBackground(new Color(204, 102, 0));
		label.setBounds(322, 63, 306, 249);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel("Lea Grboski && Meitar Lisha");
		lblNewLabel.setBackground(new Color(204, 102, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(340, 11, 288, 41);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblGzip = new JLabel("Gzip ");
		lblGzip.setFont(new Font("Arial Black", Font.ITALIC, 64));
		lblGzip.setBounds(10, 11, 185, 97);
		frame.getContentPane().add(lblGzip);

		JLabel lblToEncode = new JLabel("to encode:");
		lblToEncode.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblToEncode.setBounds(22, 150, 173, 32);
		frame.getContentPane().add(lblToEncode);

		JLabel lblToDecode = new JLabel("to decode:");
		lblToDecode.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblToDecode.setBounds(22, 235, 173, 32);
		frame.getContentPane().add(lblToDecode);

		JButton btnNewButton = new JButton("press ");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//compress button on press

				frame.setVisible(false);
				frame = new JFrame();
				frame.setVisible(true);
				frame.addWindowStateListener(new WindowStateListener() {
					public void windowStateChanged(WindowEvent arg0) {
						if(input!=""&&input1!=""){


						}
					}
				});
				frame.setSize(613, 384);
				frame.setLocationRelativeTo(null);
				frame.setBackground(new Color(10,10, 10));
				frame.getContentPane().setBackground(new Color(204, 153, 51));
				frame.getContentPane().setLayout(null);

				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(Gui_Main.class.getResource("/GZIP/gzip1-1.jpg")));
				label.setBackground(Color.WHITE);
				label.setForeground(Color.BLUE);
				label.setBounds(283, 73, 281, 253);
				frame.getContentPane().add(label);

				JLabel lblInsertA = new JLabel("insert a back window (s) :");
				lblInsertA.setHorizontalAlignment(SwingConstants.CENTER);
				lblInsertA.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblInsertA.setForeground(new Color(0, 0, 0));
				lblInsertA.setBackground(new Color(255, 255, 255));
				lblInsertA.setBounds(10, 108, 191, 40);
				frame.getContentPane().add(lblInsertA);

				JLabel lblInsertABack = new JLabel("insert a front window (t) :");
				lblInsertABack.setHorizontalAlignment(SwingConstants.CENTER);
				lblInsertABack.setBackground(new Color(255, 255, 255));
				lblInsertABack.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblInsertABack.setBounds(10, 208, 191, 33);
				frame.getContentPane().add(lblInsertABack);

				textField = new JTextField();
				textField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						input = textField.getText();   //receive input from text field
						System.out.println("the back window you enter : " + input);
					}
				});



				textField.addKeyListener(new KeyAdapter() {

					public void keyTyped(java.awt.event.KeyEvent e) {
						char c = e.getKeyChar();
						if(!(Character.isDigit(c)|| c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE )){
							e.consume(); //not be title
						}
					}
				});
				textField.setBounds(198, 114, 65, 33);
				frame.getContentPane().add(textField);
				textField.setColumns(10);

				textField_1 = new JTextField();
				textField_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						input1 = textField_1.getText();   //receive input from text field
						System.out.println("the front window you enter : " + input1);
					}
				});
				textField_1.addKeyListener(new KeyAdapter() {

					public void keyTyped(java.awt.event.KeyEvent e) {
						char c = e.getKeyChar();
						if(!(Character.isDigit(c)|| c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE )){
							e.consume(); //not be title
						}
					}
				});
				textField_1.setColumns(10);
				textField_1.setBounds(198, 210, 65, 33);
				frame.getContentPane().add(textField_1);

				JLabel lblGzipDataCompression = new JLabel("GZIP data compression");
				lblGzipDataCompression.setFont(new Font("Tahoma", Font.BOLD, 26));
				lblGzipDataCompression.setBounds(23, 11, 307, 62);
				frame.getContentPane().add(lblGzipDataCompression);

				JLabel lblLeaGraboski = new JLabel("lea graboski & meitar lisha");
				lblLeaGraboski.setHorizontalAlignment(SwingConstants.CENTER);
				lblLeaGraboski.setForeground(Color.BLACK);
				lblLeaGraboski.setBackground(new Color(255, 255, 255));
				lblLeaGraboski.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblLeaGraboski.setBounds(347, 11, 197, 51);
				frame.getContentPane().add(lblLeaGraboski);

				JButton btnClickToStart = new JButton("click to start");

				btnClickToStart.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						input=textField.getText();
						input1=textField_1.getText();
						if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || input.length()>10 || input1.length()>10)
							GUI_InputError.mainInput();
						else{
							boolean isLegal=false;
							Gui_ErrorMessage window = new Gui_ErrorMessage();
							String newStr="";
							String newStr1="";

							while(!isLegal){
								window.frame.setVisible(false);


								input=textField.getText();
								input1=textField_1.getText();

								if(( input!="" )&& (input1!="")){

									Gui_InputFile ina = new Gui_InputFile();

									frame.setVisible(false);

									ina.frame.setVisible(true);
									JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());


									int returnValue = jfc.showOpenDialog(null);


									if (returnValue == JFileChooser.APPROVE_OPTION) {
										File selectedFile = jfc.getSelectedFile();
										str1 = selectedFile.getAbsolutePath();
										for(int i=0; i<str1.length(); i++){
											if(str1.charAt(i)=='\\' )
												newStr+='\\';
											newStr+=str1.charAt(i);			
										}
									}
									ina.frame.setVisible(false);
									EventQueue.invokeLater(new Runnable() {
										public void run() {
											try {

												window.frame.setVisible(false);

											} catch (Exception e) {
												e.printStackTrace();

											}
										}
									});
								}
								Gui_OutputFile ina1 = new Gui_OutputFile();
								frame.setVisible(false);
								ina1.frame.setVisible(true);

								JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

								int returnValue = jfc.showOpenDialog(null);


								if (returnValue == JFileChooser.APPROVE_OPTION) {
									File selectedFile = jfc.getSelectedFile();
									str2 =selectedFile.getAbsolutePath();
									for(int i=0; i<str2.length(); i++){
										if(str2.charAt(i)=='\\' )
											newStr1+='\\';
										newStr1+=str2.charAt(i);			
									}
								}

								ina1.frame.setVisible(false);
								isLegal=sameType(str1,str2);
								if(!isLegal){
									EventQueue.invokeLater(new Runnable() {
										public void run() {
											try {

												window.frame.setVisible(true);

											} catch (Exception e) {
												e.printStackTrace();

											}
										}
									});

								}
							}
							try {

								GZIPCompress.main(newStr, newStr1, Integer.parseInt(input), Integer.parseInt(input1));			//calling gzip compress
								System.out.println("Done!");


							} catch (ClassNotFoundException | IOException e1) {
								System.out.println("Error! Please try again");
							}
							System.exit(0);

						}


					}
				});

				btnClickToStart.setBackground(SystemColor.control);
				btnClickToStart.setForeground(new Color(0, 0, 0));
				btnClickToStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnClickToStart.setBounds(94, 290, 121, 23);
				frame.getContentPane().add(btnClickToStart);
			}
		});
		btnNewButton.setBounds(188, 158, 79, 23);
		frame.getContentPane().add(btnNewButton);


		JButton btnPress = new JButton("press");
		btnPress.addActionListener(new ActionListener() {// decode button on press

			public void actionPerformed(ActionEvent arg0) {

				boolean isLegal=false;
				String newStr2="";
				String newStr1="";

				while(!isLegal){


					frame.setVisible(false);
					Gui_ErrorMessage window = new Gui_ErrorMessage();
					Gui_InputFile f=new Gui_InputFile();
					f.frame.setVisible(true);

					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						str2 =selectedFile.getAbsolutePath();
						for(int i=0; i<str2.length(); i++){
							if(str2.charAt(i)=='\\' )		//add to the path two backslash, so the func could read it as a path
								newStr1+='\\';
							newStr1+=str2.charAt(i);			
						}
						f.frame.setVisible(false);
					}
					Gui_OutputFile f2 = new Gui_OutputFile();
					f2.frame.setVisible(true);
					JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					f.frame.setVisible(false);
					int returnValue1 = jf.showOpenDialog(null);
					if (returnValue1 == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jf.getSelectedFile();
						str3 =selectedFile.getAbsolutePath();
						for(int i=0; i<str3.length(); i++){
							if(str3.charAt(i)=='\\' )
								newStr2+='\\';
							newStr2+=str3.charAt(i);			
						}
						f2.frame.setVisible(false);
					}isLegal=sameType(str2,str3);
					if(!isLegal){
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();

								}
							}
						});

					}
				}
				try {
					GZIPDecompress.main(newStr1, newStr2);			//call Gzip decompress
					System.out.println("Done!");
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("File error! Please make sure the file you entered in the encoded one and start again");
					//e.printStackTrace();
				}

				System.exit(0);
			}
		});
		btnPress.setForeground(Color.BLACK);
		btnPress.setBackground(Color.GRAY);
		btnPress.setBounds(188, 244, 79, 23);
		frame.getContentPane().add(btnPress);
	}
	
	
	public static boolean sameType(String str1 , String str2){			//check if the files format are the same

		int  str1Index= str1.length()-1;
		int  str2Index= str2.length()-1;

		boolean sameFormat=true;

		while(str1.charAt(str1Index)!='.' && str2.charAt(str2Index)!='.'){
			if(str1.charAt(str1Index)==str2.charAt(str2Index))
				sameFormat=sameFormat&&true;
			else
				sameFormat=sameFormat&&false;
			str1Index--;
			str2Index--;

		}

		if(str1.charAt(str1Index)=='.' && str2.charAt(str2Index)=='.')
			sameFormat=sameFormat&&true;
		else
			sameFormat=sameFormat&&false;


		return sameFormat;
	}


}
