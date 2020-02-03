package graphic;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {
  public void paintComponent(Graphics g){
    //x1, y1, width, height
    g.drawRect(50, 50, 500, 400);
  }               
}
