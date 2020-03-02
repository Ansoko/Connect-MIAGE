package algo.opti;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Grid est la classe représentant une grille de jeu.</b>
 * <p>
 * Une grille est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un tableau de tuiles à 2 dimensions</li>
 * <li>Une liste de circuits qui la composent</li>
 * <li>La taille de son plus grand circuit</li>
 * <li>Son circuit fermé le plus long</li>
 * </ul>
 * </p>
 * 
 * @see Path, OptiTile
 * 
 * @author Valentine_Bouché
 * @version 1.0
 */
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
	
	public void createAleaGrid() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if(Math.random()*100 <= 75) {//test si on pose une tuile ou non à 75%
					int alea = 1 + (int)(Math.random() * 7);
					switch (alea){
					case(1):
						setGrid(i, j, new OptiTile(i, j, Type.DownLeft));
						break;
					case(2):
						setGrid(i, j, new OptiTile(i, j, Type.DownRight));
						break;
					case(3):
						setGrid(i, j, new OptiTile(i, j, Type.TopLeft));
						break;
					case(4):
						setGrid(i, j, new OptiTile(i, j, Type.TopRight));
						break;
					case(5):
						setGrid(i, j, new OptiTile(i, j, Type.Vertical));
						break;
					case(6):
						setGrid(i, j, new OptiTile(i, j, Type.Horizontal));
						break;
					case(7):
						setGrid(i, j, new OptiTile(i, j, Type.Cross));
						break;
					default : 
						System.out.println("Erreur remplissage grille");
						break;
					}
				}else {
					setGrid(i, j, new OptiTile(i, j, Type.Empty));
				}
			}
		}
		showTab();
	}
	
	public OptiTile[][] getGrid(){
		return this.tab;
	}
	
	public void setGrid(int i, int j, OptiTile t){
		this.tab[i][j] = t;
	}
	
	public void showTab() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				System.out.print(Grid.DrawMap.get(this.tab[i][j].getType()));
			}
			System.out.println();
		}
	}
	
	//cherche si la tuile est déjà dans un trajet existant obsolète ? ??
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
			for (int j = 0; j < tab[i].length; j++) {
				if (this.tab[i][j].getType() != Type.Empty && !tab[i][j].getMark()) {//!checkPaths(tab[i][j])
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
	
	/**
     * Renvoie le résultat de la rechere du plus long algo
     * 
     */
	public void getResult() {
		if(this.maxLength != 0)
			System.out.println("Le circuit fermé le plus long est de "+this.maxLength+" traits.");
		else
			System.out.println("Il n'y a aucun circuit fermé.");
	}
	
	/**
     * Renvoie la taille la plus grande enregistrée.
     * 
     * @return maxLength
     *              La taille la plus grande.
     * 
     */
	public int getMaxLength() {
	    return maxLength;
	}

}
