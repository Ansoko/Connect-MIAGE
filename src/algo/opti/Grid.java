package algo.opti;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
	private OptiTile [][] tab;
	private List <Path> paths;
	private int maxLength;
	private Path longest;
	
	public static final Map<Type, String> DrawMap ;
	static {
		DrawMap = new HashMap<>();
		DrawMap.put(Type.Empty, " ");
		DrawMap.put(Type.DownLeft, "╚");
		DrawMap.put(Type.DownRight, "╝");
		DrawMap.put(Type.TopLeft, "╔");
		DrawMap.put(Type.TopRight, "╗");
		DrawMap.put(Type.Vertical, "║");
		DrawMap.put(Type.Horizontal, "═");
		DrawMap.put(Type.Cross, "╬");
	}
	
	
	public Grid(int i, int j) {
		this.tab = new OptiTile[i][j];
		this.paths = new ArrayList<Path>();
		this.maxLength = 0;
		this.longest = new Path();
	}
	
	public OptiTile[][] getGrid(){
		return this.tab;
	}
	
	public void setGrid(int i, int j, OptiTile t){
		this.tab[i][j] = t;
	}
	
	public void showTab() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				System.out.print(Grid.DrawMap.get(this.tab[i][j].getType()));
			}
			System.out.println();
		}
	}
	
	//cherche si la tuile est déjà dans un trajet existant
	public boolean checkPaths(OptiTile t) {
		for(Path p : paths) {
			if(p.ownsTile(t))
				return true;
		}
		return false;
	}
	
	//crée des trajets
	public void launcher() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				if (this.tab[i][j].getType() != Type.Empty && !checkPaths(tab[i][j])) {
					System.out.println("Je suis passé par là"+i+" "+j);
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
			if(p.getCycle()) {
				int length = p.getPath().size()+p.nbCross();
				System.out.println(length);
				if(length >= max) {
					max = length;
					p1 = p;
				}
			}
		}
		this.maxLength = max;
		this.longest = p1;
	}
	
	public void getResult() {
		if(this.maxLength != 0)
			System.out.println("Le circuit fermé le plus long est de "+this.maxLength+" traits.");
		else
			System.out.println("Il n'y a aucun circuit fermé.");
	}

}
