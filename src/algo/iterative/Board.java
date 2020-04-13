package algo.iterative;

import java.util.List;
import algo.iterative.Tile.mark;
/**
 * <b>Board est la classe représentant un plateau de jeu.</b>
 * <p>
 * Un plateau est caractérisée par les informations suivantes :
 * <ul>
 * <li>Les dimensions du plateau</li>
 * <li>Un tableau de tuiles à 2 dimensions</li>
 * <li>La taille de son plus grand circuit</li>
 * </ul>
 * </p>
 * 
 * @see Tile
 * 
 * @author Anne-Sophie_Koch
 */
public class Board {
	
	public int LINE;
	public int ROW;
	private Tile board[][];
	private int longest;
	
	public Board() { // tableau aléatoire
		LINE = 10;
		ROW = 30;
		board = new Tile[LINE][ROW];
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j] = new Tile(); //tuile aléatoire
			}
		}
		longest = 0;
	}

	public Board(List<String[]> tableTest) {
		LINE = tableTest.size();
		ROW = tableTest.get(0).length;
		board = new Tile[LINE][ROW];
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j]=new Tile(tableTest.get(i)[j]);
			}
		}
	}

	public String toString() {
		String tab="";
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				tab = tab + board[i][j].toString();
			}
			tab = tab + "\n";
		}
		return tab;
	}

	public void iterativResearch() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(isEmpty(i, j)) {
					board[i][j].setState(mark.DeadEnd);
				}else{  //on regarde à chaque fois si elle est non connectée à tous ses bords	
					//en haut
					if(board[i][j].getExit("up") && (i==0 || !board[i-1][j].getExit("down")) ){
						board[i][j].setState(mark.DeadEnd);
					}
					//à droite
					else if(board[i][j].getExit("right") && (j==board[i].length-1 || !board[i][j+1].getExit("left")) ) {
						board[i][j].setState(mark.DeadEnd);
					}
					//en bas
					else if(board[i][j].getExit("down") && (i==board.length-1 || !board[i+1][j].getExit("up")) ) {
						board[i][j].setState(mark.DeadEnd);
					}
					//à gauche
					else if(board[i][j].getExit("left") && (j==0 || !board[i][j-1].getExit("right")) ) {
						board[i][j].setState(mark.DeadEnd);
					}
				}
			}
		}

		//on reparcourt le tableau en s'arrêtant uniquement sur les tuilles sans "mark"
		boolean findMark = true;
		while(findMark) {
			findMark = false;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					if(board[i][j].getState()==null) {
						//on regarde ses voisins de branche, si ils sont marqués alors on marque aussi
						//en haut
						if(board[i][j].getExit("up")) {
							if(board[i-1][j].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
						//à droite
						if(board[i][j].getExit("right")) {
							if(board[i][j+1].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
						//en bas
						if(board[i][j].getExit("down")) {
							if(board[i+1][j].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
						//à gauche
						if(board[i][j].getExit("left")) {
							if(board[i][j-1].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
					}
				}
			}
		}

		
		//il ne reste plus que les circuits, qui ne sont pas marqués
		int count = 0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(board[i][j].getState()==null) {
					board[i][j].setState(mark.Connect);
					count = calculateCircuit(i,j);
					//System.out.println("Circuit de longueur "+count+".");
					if(count > longest) {
						longest = count;
					}
				}
			}
		}
		if(longest == 0) {
		    System.out.println("Il n'y a aucun circuit fermé.");
		}
		else{
		    System.out.println("Le plus long chemin est de "+longest);
		}

	}

	private boolean isEmpty(int i, int j) {
		if (board[i][j]==null || board[i][j].getNum()==0)
			return true;
		return false;
	}

	private int calculateCircuit(int i, int j){
		//up
		if(board[i][j].getExit("up") && board[i][j].isConnected(board[i-1][j], 1) 
				&& (board[i-1][j].getState()==null||board[i-1][j].getState()==mark.Cross)) {
			
			if(board[i-1][j].getNum()==7 && board[i-1][j].getState()!=mark.Cross) { //une case "croix" peut être traversée 2 fois
				board[i-1][j].setState(mark.Cross);
			}else {
				board[i-1][j].setState(mark.Connect);
			}
			
			return 1 + calculateCircuit(i-1, j);	
		}
		//right
		if(board[i][j].getExit("right") && board[i][j].isConnected(board[i][j+1], 2) 
				&& (board[i][j+1].getState()==null||board[i][j+1].getState()==mark.Cross)) {
			
			if(board[i][j+1].getNum()==7 && board[i][j+1].getState()!=mark.Cross) { //une case "croix" peut être traversée 2 fois
				board[i][j+1].setState(mark.Cross);
			}else {
				board[i][j+1].setState(mark.Connect);
			}
			
			return 1 + calculateCircuit(i, j+1);	
		}
		//down
		if(board[i][j].getExit("down") && board[i][j].isConnected(board[i+1][j], 3) 
				&& (board[i+1][j].getState()==null||board[i+1][j].getState()==mark.Cross)) {
			
			if(board[i+1][j].getNum()==7 && board[i-1][j].getState()!=mark.Cross) { //une case "croix" peut être traversée 2 fois
				board[i+1][j].setState(mark.Cross);
			}else {
				board[i+1][j].setState(mark.Connect);
			}
			
			return 1 + calculateCircuit(i+1, j);
		}
		//left
		if(board[i][j].getExit("left") && board[i][j].isConnected(board[i][j-1], 4) 
				&& (board[i][j-1].getState()==null||board[i][j-1].getState()==mark.Cross)) {
			
			if(board[i][j-1].getNum()==7 && board[i][j-1].getState()!=mark.Cross) { //une case "croix" peut être traversée 2 fois
				board[i][j-1].setState(mark.Cross);
			}else {
				board[i][j-1].setState(mark.Connect);
			}
			
			return 1 + calculateCircuit(i, j-1);
		}
		
		//default
		return 1;
	}
}

