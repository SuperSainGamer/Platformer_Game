package mainGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class gameWindow {
		
	
	public static JLabel mouseXlbl;
	public static JLabel mouseYlbl;
	public static JLabel debugLbl;
		
	public gameWindow(int w, int h, String Title, mainGame mainGame) {

		mainGame.setPreferredSize(new Dimension(w, h));
		mainGame.setMaximumSize(new Dimension(w, h));
		mainGame.setMinimumSize(new Dimension(w, h));
		 
		mouseXlbl = new JLabel();
		mouseXlbl.setText("Direction ");
		mouseXlbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mouseXlbl.setBackground(Color.white);
		mouseXlbl.setOpaque(true);;
		mouseXlbl.setBounds(0, 0, 350, 32);
		mouseXlbl.setVisible(true);
		
		debugLbl = new JLabel();
		debugLbl.setText(" ");
		debugLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		debugLbl.setBackground(Color.white);
		debugLbl.setOpaque(true);;
		debugLbl.setBounds(360, 0, 350, 32);
		debugLbl.setVisible(true);
		
		JFrame frame = new JFrame(Title);
		//frame.setUndecorated(true);
		frame.add(debugLbl);
		frame.add(mouseXlbl);
		frame.add(mainGame);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		mainGame.start();

	}

}
