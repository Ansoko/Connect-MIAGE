package algo.opti;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import connect.*;

public class Path {
	private boolean cycle;
	private List<Tile> tiles;
	
	public Path(Tile[][] board, Tile starter) {
		this.cycle = true;
		this.tiles = new ArrayList<>();
		this.tiles.add(starter);
		this.fillPath(board, starter.getX(), starter.getY());
	}
	
	public Path() {
		this.cycle = true;
		this.tiles = new ArrayList<>();
	}
	
	public List<Tile> getPath(){
		return this.tiles;
	}
	
	public void setCycle(boolean cycle) {
		this.cycle = cycle;
	}
	
	public boolean getCycle() {
		return cycle;
	}
	
	//Retourne vrai si la case n'est pas en dehors du board
	private boolean checkIfTileExists(Tile[][] board, int x, int y) { 
		boolean exist = true;
		try {
			Tile t = board[x][y];
		}catch (Exception e){
			exist = false;
		}
		return exist;
	}
	
	//Retourne vrai si la direction est dans le tableau
		private boolean checkIfDirectionExists(Direction[] t, Direction dir) { 
			for(Direction d : t) {
				if(dir == d) {
					return true;
				}
			}
			return false;
		}
	
	//Retourne vrai si le trajet possède une tuile donnée
	public boolean ownsTile(Tile t) {
		return tiles.contains(t);
	}
	
	//remplit le trajet même s'il est pas fermé
	private void fillPath(Tile[][] board, int i, int j) {
		for(Direction dir : Main.ConnectionsMap.get(board[i][j].getType())) {
			switch(dir) {
				case Top:
					if (this.checkIfTileExists(board,i,j-1) && checkIfDirectionExists(Main.ConnectionsMap.get(board[i+1][j].getType()),Direction.Left)) {
						if (!this.ownsTile(board[i][j-1])) {
							this.getPath().add(board[i][j-1]);
							this.fillPath(board, i, j-1);
						}
					} else {
						this.setCycle(false);
					}
					break;
				
				case Down:
					if (this.checkIfTileExists(board,i,j+1) && checkIfDirectionExists(Main.ConnectionsMap.get(board[i][j+1].getType()),Direction.Top)) {
						if (!this.ownsTile(board[i][j+1])) {
								this.getPath().add(board[i][j+1]);
								this.fillPath(board, i, j+1);
							}
						} else {
							this.setCycle(false);
						}
					break;
				case Left:
					if (this.checkIfTileExists(board,i-1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(board[i-1][j].getType()),Direction.Right)) {
						if (!this.ownsTile(board[i-1][j])) {
							this.getPath().add(board[i-1][j]);
							this.fillPath(board, i-1, j);
						}
					} else {
						this.setCycle(false);
					}
					break;
				case Right:
					if (this.checkIfTileExists(board,i+1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(board[i+1][j].getType()),Direction.Left)) {
						if (!this.ownsTile(board[i+1][j])) {
							this.getPath().add(board[i+1][j]);
							this.fillPath(board, i+1, j);
						}
					} else {
						this.setCycle(false);
					}
					break;
			}
		}
	}

}
