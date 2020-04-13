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
import java.awt.GridLayout;

public class Graphic extends JFrame  {
	
	public Graphic() {
		
		BufferedImage tabNb [] = new BufferedImage [8];
		
		
		JFrame frame = new JFrame("Test");
		JPanel panel = new JPanel(new GridLayout(10, 10));
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		try {
		    tabNb[0] = ImageIO.read(new File("empty.png"));
		    tabNb[1] = ImageIO.read(new File("vertical.png"));
		    tabNb[2] = ImageIO.read(new File("horizontal.png"));
		    tabNb[3] = ImageIO.read(new File("downright.png"));
		    tabNb[4] = ImageIO.read(new File("downleft.png"));
		    tabNb[5] = ImageIO.read(new File("topright.png"));
		    tabNb[6] = ImageIO.read(new File("topleft.png"));
		    tabNb[7] = ImageIO.read(new File("cross.png"));
		    
			for (int i = 0 ; i<100 ; i++) {
				JLabel label = new JLabel();
				panel.add(label);
				int random = (int) (Math.random()* 8);
				label.setIcon(new ImageIcon(tabNb[random]));
			}
		    

		} catch (Exception e) {
		    e.printStackTrace();
		}

		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
	}
    /* 
     * AFFICHAGE DES PLATEAUX TESTS   
     
	// INIT
        setTitle("Plateau de test");
        setSize(1400,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(getParent());
 
         
        // TEXTE
      JTextArea text = new JTextArea(readFile("sujet_test.txt"));
      JTextArea text1 = new JTextArea(readFile("sujet_test10.txt"));
         
        // AJOUT DANS LA FENETRE
        add(text,BorderLayout.CENTER);
        add(text1,BorderLayout.EAST);
         
        // AFFICHE
        setVisible(true);
        
	}
        public String readFile( String file )
        {
            // LIS LE FICHIER
            String lines = "";
            String line;
            try
            {
                // CREE LE FLUX
                BufferedReader reader = new BufferedReader( new FileReader(file) );
                 
                // LIS LIGNE A LIGNE
                while( (line = reader.readLine()) != null )
                    lines += line+"\n";
            }
            catch( Exception e )
            {
                lines = "Une erreur s'est produite durant la lecture du flux : "+e.getMessage();
            }  
             
            return lines;
        }
        */
	}	 
	//	║═╚╔╗╝╬


