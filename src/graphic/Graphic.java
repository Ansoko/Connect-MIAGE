package graphic;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.awt.GridLayout;

public class Graphic extends JFrame  {
	public Graphic(List<String[]> tableTest) {
		BufferedImage tabNb [] = new BufferedImage [8];
		JFrame frame = new JFrame("Test");
		JPanel panel = new JPanel(new GridLayout(tableTest.size(), tableTest.get(0).length));
		frame.setContentPane(panel);
		frame.setVisible(true);
		int ref;
		try {
		    tabNb[0] = ImageIO.read(new File("./Images/empty.png"));
		    tabNb[1] = ImageIO.read(new File("./Images/vertical.png"));
		    tabNb[2] = ImageIO.read(new File("./Images/horizontal.png"));
		    tabNb[3] = ImageIO.read(new File("./Images/downright.png"));
		    tabNb[4] = ImageIO.read(new File("./Images/downleft.png"));
		    tabNb[5] = ImageIO.read(new File("./Images/topright.png"));
		    tabNb[6] = ImageIO.read(new File("./Images/topleft.png"));
		    tabNb[7] = ImageIO.read(new File("./Images/cross.png"));
		    
		    for(String[] line : tableTest) {
		        for(String s : line) {
		            JLabel label = new JLabel();
	                panel.add(label);
	                switch(s) { // ║═╚╔╗╝╬
	                    case "║":
	                        ref = 1;
                            break;
	                    case "═":
	                        ref = 2;
                            break;
	                    case "╝":
	                        ref = 3;
                            break;
	                    case "╚":
	                        ref = 4;
                            break;
	                    case "╗":
	                        ref = 5;
                            break;
	                    case "╔":
	                        ref = 6;
                            break;
	                    case "╬":
	                        ref = 7;
                            break;
	                    default :
	                        ref = 0;
                            break;
	                }
	                label.setIcon(new ImageIcon(tabNb[ref]));
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
	}
}


