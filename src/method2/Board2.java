package method2;
import java.util.Arrays;
import javax.swing.JFrame;

public class Board2 {
	public final int LINE = 3;
	public final int ROW = 5 ;
	private Circuit longest;
	private LiteTile board[][];

	public Board2() { // automatiques avec les constantes
		board = new LiteTile[LINE][ROW];
		longest = new Circuit();
	}

	public void cornerResearch() { // recherche des coins se 
		for(int i = 0; i < LINE; i++ ) {
			for (int j = 0; j < ROW; j++) {
				if(board[i][j] == null && board[i][j].isCornerSE() && !board[i][j].isMark()) //on trouve un coin se
					followCircuit(i,j); // on parcourt le circuit
			}
		}
	}
	
	private void followCircuit(int posI, int posJ) { 
		int length = 1;
		int startI = posI, startJ = posJ;
		boolean flag = true; //flag devient faux si le circuit est ouvert
		boolean start = false; // start devient vrai si le circuit est arrivï¿½ au dï¿½but
	
		while(flag && !start) {
<<<<<<< HEAD
			if(board[posI][posJ].isE() && startJ+1 < LINE) {//si la case à droite n'est pas un mur
=======
			if(this.board[posI][posJ].getE() && startJ+1 < LINE) {//si la case ï¿½ droite n'est pas un mur
>>>>>>> 675af3a5ff50cd7ae5f2119b443d14fb6610dfa9
				posJ +=1; //on va vers la droite
			}
			else if(this.board[posI][posJ].isN() && startI-1 >= 0) {//si la case au nord n'est pas un mur
				posI -=1; //on va vers le haut
			}
<<<<<<< HEAD
			else if(this.board[posI][posJ].isW() && startJ-1 >= 0) {//si la case à l'ouest n'est pas un mur
=======
			else if(this.board[posI][posJ].getW() && startJ-1 >= 0) {//si la case ï¿½ l'ouest n'est pas un mur
>>>>>>> 675af3a5ff50cd7ae5f2119b443d14fb6610dfa9
				posJ -=1; //on va vers le haut
			}
			else if(this.board[posI][posJ].isS() && startI+1 > ROW) {//si la case au sud n'est pas un mur
				posI +=1; //on va vers le bas
			}
			else {
				flag = false;
			}
			
			if(posI==startI && posJ == startJ)
				start = true;
			else {
				if(this.board[posI][posJ] == null) {
					flag = false;
				}else {
					//si la case d'avant est marquï¿½e ou est le start alors on vient de lï¿½
					//si on vient du nord alors la case doit avoir le nord true ETC
					//systï¿½me d'anne so pour les cases
					//Stocker la case prï¿½cï¿½dentes pour check
					
					//si tout est ok
					length +=1; // on ajoute la case trouvï¿½e et on la marque
					this.board[posI][posJ].setMark(true);
				}
			}
		}
		
		while(true && posI != startI && posJ !=startJ) {
			//comparer avec la case suivante
			
			// passer posI et posJ ï¿½ la case suivante
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
}
