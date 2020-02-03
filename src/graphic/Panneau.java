package graphic;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {
  public void paintComponent(Graphics g){
    //x1, y1, width, height
	// création du tableau de case en fonction des lignes et des colonnes
    g.drawRect(15, 15, 500, 400);
    g.drawLine(100, 15,100,415);
  }               
}
