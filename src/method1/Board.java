package method1;

import java.util.List;

import method1.Tile.mark;

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
				board[i][j] = new Tile(); //tuile al���atoire
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

		//on ne s'occupe pas du mark des bords dans un premier temps
		for(int i=1; i<board.length-1; i++) {
			for(int j=1; j<board[i].length-1; j++) {
				if(isEmpty(i, j)) {
					board[i][j].setState(mark.DeadEnd);
				}else if(!board[i][j].isConnected(board[i-1][j], 1) || !board[i][j].isConnected(board[i][j+1], 2) || !board[i][j].isConnected(board[i+1][j], 3)  || !board[i][j].isConnected(board[i][j-1], 4)) { 
					//on regarde ��� chaque fois si elle est non connect���e ��� tous ses bords
					board[i][j].setState(mark.DeadEnd);
				}
			}

			//mark colonne coll���e ��� gauche
			if(!board[i][0].isConnected(board[i-1][0], 1) || !board[i][0].isConnected(board[i][1], 2) || !board[i][0].isConnected(board[i+1][0], 3) || !board[i][0].isConnected(null, 4)) { 
				board[i][0].setState(mark.DeadEnd);
			}

			//colonne coll���e ��� droite
			if(!board[i][board[i].length-1].isConnected(board[i-1][board[i].length-1], 1) || !board[i][board[i].length-1].isConnected(null, 2) || !board[i][board[i].length-1].isConnected(board[i+1][board[i].length-1], 3) || !board[i][board[i].length-1].isConnected(board[i][board[i].length-2], 4)) { 
				board[i][board[i].length-1].setState(mark.DeadEnd);
			}
		}

		//premi���re ligne
		for(int j=1; j<board[LINE-1].length-1; j++) {
			if(isEmpty(0, j)) {
				board[0][j].setState(mark.DeadEnd);
			}else if(!board[0][j].isConnected(null, 1) || !board[0][j].isConnected(board[0][j+1], 2) || !board[0][j].isConnected(board[1][j], 3) || !board[0][j].isConnected(board[0][j-1], 4)) { 
				board[0][j].setState(mark.DeadEnd);
			}
		}

		//dernière ligne
		for(int j=1; j<board[LINE-1].length-1; j++) {
			if(isEmpty(LINE-1, j)) {
				board[LINE-1][j].setState(mark.DeadEnd);
			}else if(!board[LINE-1][j].isConnected(board[LINE-2][j], 1) || !board[LINE-1][j].isConnected(board[LINE-1][j+1], 2) || !board[LINE-1][j].isConnected(null, 3) || !board[LINE-1][j].isConnected(board[LINE-1][j-1], 4)) { 
				board[LINE-1][j].setState(mark.DeadEnd);
			}
		}

		//coins
		if(board[0][0].getNum()!=4 || (!board[0][0].isConnected(board[0][1], 2) || !board[0][0].isConnected(board[1][0], 3))) { 
			board[0][0].setState(mark.DeadEnd);
		}
		if(board[0][ROW-1].getNum()!=5 || (!board[0][ROW-1].isConnected(board[0][ROW-2], 4) || !board[0][ROW-1].isConnected(board[1][ROW-1], 3))) { 
			board[0][ROW-1].setState(mark.DeadEnd);
		}
		if(board[LINE-1][0].getNum()!=3 || (!board[LINE-1][0].isConnected(board[LINE-2][0], 1) || !board[LINE-1][0].isConnected(board[LINE-1][1], 2))) { 
			board[LINE-1][0].setState(mark.DeadEnd);
		}
		if(board[LINE-1][ROW-1].getNum()!=6 || (!board[LINE-1][ROW-1].isConnected(board[LINE-2][ROW-1], 1) || !board[LINE-1][ROW-1].isConnected(board[LINE-1][ROW-2], 4))) { 
			board[LINE-1][ROW-1].setState(mark.DeadEnd);
		}


		//on reparcourt le tableau en s'arrêtant uniquement sur les tuilles sans mark
		boolean findMark = true;
		while(findMark) {
			findMark = false;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					if(board[i][j].getState()==null) {
						//on regarde ses voisins de branche, si ils sont marqu��� alors on marque aussi
						//en haut
						if(board[i][j].getNum()==1 || board[i][j].getNum()==3 || board[i][j].getNum()==6 || board[i][j].getNum()==7) {
							if(board[i-1][j].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
						//à droite
						if(board[i][j].getNum()==2 || board[i][j].getNum()==3 || board[i][j].getNum()==4 || board[i][j].getNum()==7) {
							if(board[i][j+1].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
						//en bas
						if(board[i][j].getNum()==1 || board[i][j].getNum()==4 || board[i][j].getNum()==5 || board[i][j].getNum()==7) {
							if(board[i+1][j].getState()==mark.DeadEnd) {
								board[i][j].setState(mark.DeadEnd);
								findMark = true;
							}
						}
						//à gauche
						if(board[i][j].getNum()==2 || board[i][j].getNum()==5 || board[i][j].getNum()==6 || board[i][j].getNum()==7) {
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
					board[i][j].setState(mark.Start);
					count = calculateCircuit(i,j);
					System.out.println("Circuit de longueur "+count+".");
					if(count > longest) {
						longest = count;
					}
				}
			}

		}
		System.out.println("Le plus long chemin est de "+longest);
	}

	private boolean isEmpty(int i, int j) {
		if (board[i][j]==null)
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

