package method2;
import java.util.Arrays;
import javax.swing.JFrame;

public class Board {
	public final int LINE = 3;
	public final int ROW = 5 ;
	private Circuit longest;
	private LiteTile board[][];

	public Board() { // automatiques avec les constantes
		board = new LiteTile[LINE][ROW];
		longest = new Circuit();
	}

	public void cornerResearch() { // recherche des coins se (numéro 4)
		for(int i = 0; i < LINE; i++ ) {
			for (int j = 0; j < ROW; j++) {
				if(this.board[i][j] != null && this.board[i][j].getNum() == 4) { //on trouve un coin se
					followCircuit(i,j); // on parcourt le circuit
				}
			}
		}
	}
	
	private void followCircuit(int posI, int posJ) { //carrément pas fini
		int length = 1;
		int startI = posI, startJ = posJ;
		boolean flag = true;
		if(startJ+1 == LINE) { //si la case à droite n'est pas un mur
			flag = false;
		}
		posJ +=1;
		
		while(true && posI != startI && posJ !=startJ) {
			//comparer avec la case suivante
			
			// passer posI et posJ à la case suivante
		}
				
		if(true && posI != startI && posJ !=startJ )
			//je sais pas
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
}
