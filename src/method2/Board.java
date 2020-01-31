package method2;
import java.util.Arrays;

import javax.swing.JFrame;

import method1.Tile;

public class Board {
	public final int LINE = 5;
	public final int ROW = 5 ;
	private Circuit longest;
	private Tile board[][];

	public Board() { // automatiques avec les constantes
		board = new Tile[LINE][ROW];
	}

	public String toString() {
		String tab="";
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				tab = tab + i +"/"+j+" ";
			}
			tab = tab + "\n";
		}
		return tab;
	}

	private void createWindow() {
		JFrame window = new JFrame();

		window.setTitle("Jeu Connect");
		//Définit sa taille : 400 pixels de large et 100 pixels de haut
		window.setSize(400, 100);
		//Nous demandons maintenant à notre objet de se positionner au centre
		window.setLocationRelativeTo(null);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		window.setVisible(true);
	}
	
	public void cornerResearch() { // recherche des coins se (numéro 4)
		for(int i = 0; i < LINE; i++ ) {
			for (int j = 0; j < ROW; j++) {
				
			}
		}
	}
}
