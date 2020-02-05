package graphic;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class Graphic  {
	
	public Graphic() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame connect = new JFrame ("Jeu du Connect");
		JPanel panel = new JPanel ();
		GridLayout grid = new GridLayout(5,5,10,10);

		panel.setLayout(grid);
		connect.setContentPane(panel);		
		connect.setSize(1440, 900);
		connect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connect.setVisible(true);
	}	 
	//	║═╚╔╗╝╬

}
