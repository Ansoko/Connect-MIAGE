package algo.recursive;
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
	
	/**
     * Constructeur de la grille vide de jeu
     * 
     * @param i
     *          La taille du tableau en x.
     * @param j
     *          La taille du tableau en y.
     * 
     */
	public Grid(int i, int j) {
		this.tab = new OptiTile[i][j];
		this.paths = new ArrayList<Path>();
		this.maxLength = 0;
		this.longest = new Path();
	}
	
	public void reset() {
        this.paths = new ArrayList<Path>();
        this.maxLength = 0;
        this.longest = new Path();
    }
	
	public OptiTile[][] forcedClone(OptiTile[][] t) {
	    OptiTile[][] tab = new OptiTile[t.length][t[0].length];
	    for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                tab[i][j] = new OptiTile(t[i][j].getX(), t[i][j].getY(), t[i][j].getType());
            }
	    }
	    return tab;
    }
	
	
	
	/**
     * Retourne le tableau de tuiles
     * 
     * @return tab le tableau de tuiles
     * 
     */
	public OptiTile[][] getTab(){
		return this.tab;
	}
	
	/**
     * Ajoute une tuile ������ une case de la grille
     * 
     * @param x
     *          La position en x.
     * @param y
     *          La position en y.
     * @param t
     *          La tuile ������ placer sur la case.
     * 
     */
	public void setTile(int x, int y, OptiTile t){
		this.tab[x][y] = t;
	}
	
	/**
     * Affiche la grille de tuiles
     * 
     */
	public void showTab() {
		for (int i = 0; i < this.tab.length; i++) {
			for (int j = 0; j < this.tab[i].length; j++) {
				System.out.print(Grid.DrawMap.get(this.tab[i][j].getType()));
			}
			System.out.println();
		}
	}
	
	/**
     * Lance la recherche des circuits en parcourant chaque case du tableau
     * 
     */
	public void launcher() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if (this.tab[i][j].getType() != Type.Empty && !tab[i][j].getMark()) {//!checkPaths(tab[i][j])
					paths.add(new Path(this.tab, this.tab[i][j]));
				}
			}
		}
	}
	
	/**
     * Recherche le circuit le plus long parmis les circuits enregistr������s
     * 
     */
	public void lookForLongest() { 
		Path p1 = new Path();
		int max = 0;
		for(Path p : this.paths ) {
			if(p.getClosed()) {
			    int length = p.getPath().size()+p.nbCross();
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
     * Affiche le résultat de la recherche du plus long circuit ferm������
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
