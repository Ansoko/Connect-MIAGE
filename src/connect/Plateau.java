package connect;
import javax.swing.JFrame;

public class Plateau {
	
	Jeton plateau[][];

	public Plateau(int n, int m) {
		plateau[][] = new Jeton[n][m];
	}

	private void createFenetre() {
		JFrame fenetre = new JFrame();

		fenetre.setTitle("Jeu du connect");
		//Définit sa taille : 400 pixels de large et 100 pixels de haut
		fenetre.setSize(400, 100);
		//Nous demandons maintenant à notre objet de se positionner au centre
		fenetre.setLocationRelativeTo(null);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		fenetre.setVisible(true);
	}

}
