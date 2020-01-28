package connect;
import javax.swing.JFrame;

public class Plateau {
	
	private Jeton plateau[][];

	public Plateau(int n, int m) { //n = lignes, m=colonnes
		plateau = new Jeton[n][m];
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
	
	
	public void methode1() {
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau[i].length; j++) {
				System.out.print(i+"/"+j+" ");
			}
			System.out.println();
		}
	}

}
