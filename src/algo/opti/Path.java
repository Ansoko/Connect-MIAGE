package algo.opti;
import java.util.ArrayList;
import java.util.List;

public class Path {
	private boolean cycle;
	private List<Tile> Tiles;
	
	public Path(Tile[][] board, Tile starter, List<Tile>Tiles) {
		this.cycle = true;
		this.Tiles.add(starter);
		this.fillPath(board, starter.getX(), starter.getY());
	}
	
	private boolean checkIfExists() {
		//attention to do exception
		//return (tab[x][y] !== undefined);
		return true;
	}
	
	private boolean ownsTile(Tile t) {
		//tuileTrajet.find(t=> t.getX() == tuile.getX() && t.getY() == tuile.getY()) != null; 
		//Je sais plus ce que find renvoie s'il trouve pas, on va dire null.
		return true;
	}
	
	private void fillPath(Tile[][] board, int x, int y) {
		
	}

}
