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
				if(this.board[i][j] != null && this.isCornerSE()) { //on trouve un coin se
					followCircuit(i,j); // on parcourt le circuit
				}
			}
		}
	}
	
	private void followCircuit(int posI, int posJ) { 
		int length = 1;
		int startI = posI, startJ = posJ;
		boolean flag = true; //flag devient faux si le circuit est ouvert
		boolean start = false; // start devient vrai si le circuit est arriv� au d�but
	
		while(flag && !start) {
			if(this.board[posI][posJ].getE() && startJ+1 < LINE) {//si la case � droite n'est pas un mur
				posJ +=1; //on va vers la droite
			}
			else if(this.board[posI][posJ].getN() && startI-1 >= 0) {//si la case au nord n'est pas un mur
				posI -=1; //on va vers le haut
			}
			else if(this.board[posI][posJ].getW() && startJ-1 >= 0) {//si la case � l'ouest n'est pas un mur
				posJ -=1; //on va vers le haut
			}
			else if(this.board[posI][posJ].getS() && startI+1 > ROW) {//si la case au sud n'est pas un mur
				posI +=1; //on va vers le bas
			}
			else {
				flag = false;
			}
			
			if(posI==startI && posJ == startJ)
				start = true;
			else {
				if(this.board[posI][posJ].getNum() == 0) {
					flag = false;
				}else {
					//si la case d'avant est marqu�e ou est le start alors on vient de l�
					//si on vient du nord alors la case doit avoir le nord true ETC
					//syst�me d'anne so pour les cases
					//Stocker la case pr�c�dentes pour check
					
					//si tout est ok
					length +=1; // on ajoute la case trouv�e et on la marque
					this.board[posI][posJ].setMark(true);
				}
			}
		}
		
		while(true && posI != startI && posJ !=startJ) {
			//comparer avec la case suivante
			
			// passer posI et posJ � la case suivante
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
