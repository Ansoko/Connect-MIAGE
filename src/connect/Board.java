package connect;
import java.util.Arrays;

import javax.swing.JFrame;

import method1.Tile;

public class Board {
	
	private Tile board[][];

	public Board(int n, int m) { //n = lignes, m=colonnes
		board = new Tile[n][m];
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

		window.setTitle("Jeu du connect");
		//Définit sa taille : 400 pixels de large et 100 pixels de haut
		window.setSize(400, 100);
		//Nous demandons maintenant à notre objet de se positionner au centre
		window.setLocationRelativeTo(null);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		window.setVisible(true);
	}
	
	public void iterativResearch() {
		
	}
}
