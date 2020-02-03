package graphic;
import javax.swing.JFrame;
import java.awt.Graphics;

public class Graphic extends JFrame {
	
	public Graphic() {
		this.setTitle("Jeu du Connect");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new Panneau());
		this.setVisible(true);
	}	 
	//	║═╚╔╗╝╬
	
}
