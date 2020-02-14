package algo.opti;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Board {
	
	private Tile [][] tab;
	private List <Path> paths;
	private int maxLength;
	private Path longest;
	
	
	public Board(int i, int j) {
		this.tab = new Tile[i][j];
		this.paths = new ArrayList<Path>();
		this.maxLength = 0;
		this.longest = new Path();
	}
	
	//cherche si la tuile est déjà dans un trajet existant
	public boolean checkPaths(Tile t) {
		for(Path p : paths) {
			if(p.ownsTile(t))
				return true;
		}
		return false;
	}
	
	//crée des trajets
	public void go() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				if (this.tab[i][j].getType() != Type.Empty && checkPaths(tab[i][j])) {
					paths.add(new Path(this.tab, this.tab[i][j]));
				}
			}
		}
	}
	
	//cherche le plus long trajet
	public void lookForLongest() { 
		Path p1 = new Path();
		int max = 0;
		for(Path p : this.paths ) {
			if(p.getCycle() && p.getPath().size() >= max) {
				max = p.getPath().size();
				p1 = p;
			}
		}
		this.maxLength = max;
		this.longest = p1;
	}

}
