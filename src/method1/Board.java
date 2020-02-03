package method1;
import javax.swing.JFrame;
import method1.Tile;
import method1.Tile.marquage;

public class Board {
	public final int LINE = 5;
	public final int ROW = 5 ;
	private Circuit longest;
	private Tile board[][];

	public Board() { // automatiques avec les constantes
		board = new Tile[LINE][ROW];
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j] = new Tile(); //tuile al�atoire
			}
		}
	}

	public String toString() {
		String tab="";
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				tab = tab + board[i][j].toString() + " ";
			}
			tab = tab + "\n";
		}
		return tab;
	}



	public void iterativResearch() {

		//on ne s'occupe pas du marquage des bords dans un premier temps
		for(int i=1; i<board.length-1; i++) {
			for(int j=1; j<board[i].length-1; j++) {
				if(isEmpty(i, j)) {
					board[i][j].setState(marquage.DeadEnd);
				}else if(!board[i][j].isConnected(board[i-1][j], 1) || !board[i][j].isConnected(board[i][j+1], 2) || !board[i][j].isConnected(board[i+1][j], 3)  || !board[i][j].isConnected(board[i][j-1], 4)) { 
					//on regarde � chaque fois si elle est non connect�e � tous ses bords
					board[i][j].setState(marquage.DeadEnd);
				}
			}

			//marquage colonne coll�e � gauche
			if(!board[i][0].isConnected(board[i-1][0], 1) || !board[i][0].isConnected(board[i][1], 2) || !board[i][0].isConnected(board[i+1][0], 3) || !board[i][0].isConnected(null, 4)) { 
				board[i][0].setState(marquage.DeadEnd);
			}

			//colonne coll�e � droite
			if(!board[i][board[i].length-1].isConnected(board[i-1][board[i].length-1], 1) || !board[i][board[i].length-1].isConnected(null, 2) || !board[i][board[i].length-1].isConnected(board[i+1][board[i].length-1], 3) || !board[i][board[i].length-1].isConnected(board[i][board[i].length-2], 4)) { 
				board[i][board[i].length-1].setState(marquage.DeadEnd);
			}
		}

		//premi�re ligne
		for(int j=1; j<board[LINE-1].length-1; j++) {
			if(isEmpty(0, j)) {
				board[0][j].setState(marquage.DeadEnd);
			}else if(!board[0][j].isConnected(null, 1) || !board[0][j].isConnected(board[0][j+1], 2) || !board[0][j].isConnected(board[1][j], 3) || !board[0][j].isConnected(board[0][j-1], 4)) { 
				board[0][j].setState(marquage.DeadEnd);
			}
		}

		//derni�re ligne
		for(int j=1; j<board[LINE-1].length-1; j++) {
			if(isEmpty(LINE-1, j)) {
				board[LINE-1][j].setState(marquage.DeadEnd);
			}else if(!board[LINE-1][j].isConnected(board[LINE-2][j], 1) || !board[LINE-1][j].isConnected(board[LINE-1][j+1], 2) || !board[LINE-1][j].isConnected(null, 3) || !board[LINE-1][j].isConnected(board[LINE-1][j-1], 4)) { 
				board[LINE-1][j].setState(marquage.DeadEnd);
			}
		}

		//coins
		if(board[0][0].getNum()!=4 || (!board[0][0].isConnected(board[0][1], 2) || !board[0][0].isConnected(board[1][0], 3))) { 
			board[0][0].setState(marquage.DeadEnd);
		}
		if(board[0][ROW-1].getNum()!=5 || (!board[0][ROW-1].isConnected(board[0][ROW-2], 4) || !board[0][ROW-1].isConnected(board[1][ROW-1], 3))) { 
			board[0][ROW-1].setState(marquage.DeadEnd);
		}
		if(board[LINE-1][0].getNum()!=3 || (!board[LINE-1][0].isConnected(board[LINE-2][0], 1) || !board[LINE-1][0].isConnected(board[LINE-1][1], 2))) { 
			board[LINE-1][0].setState(marquage.DeadEnd);
		}
		if(board[LINE-1][ROW-1].getNum()!=6 || (!board[LINE-1][ROW-1].isConnected(board[LINE-2][ROW-1], 1) || !board[LINE-1][ROW-1].isConnected(board[LINE-1][ROW-2], 4))) { 
			board[LINE-1][ROW-1].setState(marquage.DeadEnd);
		}


		//on reparcourt le tableau en s'arr�tant que sur les tuilles sans marquage
		boolean mark = true;
		while(mark) {
			mark = false;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					if(board[i][j].getState()==null) {
						//on regarde ses voisins de branche, si ils sont marqu� alors on marque aussi
							//en haut
						if(board[i][j].getNum()==1 || board[i][j].getNum()==3 || board[i][j].getNum()==6 || board[i][j].getNum()==7) {
							if(board[i-1][j].getState()==marquage.DeadEnd) {
								board[i][j].setState(marquage.DeadEnd);
								mark = true;
							}
						}
							//� droite
						if(board[i][j].getNum()==2 || board[i][j].getNum()==3 || board[i][j].getNum()==4 || board[i][j].getNum()==7) {
							if(board[i][j+1].getState()==marquage.DeadEnd) {
								board[i][j].setState(marquage.DeadEnd);
								mark = true;
							}
						}
							//en bas
						if(board[i][j].getNum()==1 || board[i][j].getNum()==4 || board[i][j].getNum()==5 || board[i][j].getNum()==7) {
							if(board[i+1][j].getState()==marquage.DeadEnd) {
								board[i][j].setState(marquage.DeadEnd);
								mark = true;
							}
						}
							//� gauche
						if(board[i][j].getNum()==2 || board[i][j].getNum()==5 || board[i][j].getNum()==6 || board[i][j].getNum()==7) {
							if(board[i][j-1].getState()==marquage.DeadEnd) {
								board[i][j].setState(marquage.DeadEnd);
								mark = true;
							}
						}
					}
				}
			}
		}
	}

	private boolean isEmpty(int i, int j) {
		if (board[i][j]==null)
			return true;
		return false;
	}
}

