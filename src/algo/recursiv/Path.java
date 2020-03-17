package algo.recursiv;
//import java.util.logging.Logger;
//import java.util.logging.Level;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import connect.*;

/**
 * <b>Path est la classe repr��sentant un circuit de tuile.</b>
 * <p>
 * Un circuit est caract��ris�� par les informations suivantes :
 * <ul>
 * <li>Une liste de tuiles qui le composent</li>
 * <li>Un bool��en d��terminant si le circuit est ferm�� ou ouvert</li>
 * </ul>
 * </p>
 * 
 * @see OptiTile
 * 
 * @author Valentine_Bouch��
 * @version 1.0
 */
public class Path {
	private boolean closed;
	private List<OptiTile> optiTiles;
	
    /**
     * Constructeur du circuit
     * 
     * @param tab
     *          Le tableau de tuiles.
     * @param starter
     *          La premi��re tuile de ce circuit.
     * 
     * @see OptiTile
     */
	public Path(OptiTile[][] tab, OptiTile starter) {
		this.closed = true;
		this.optiTiles = new ArrayList<>();
		this.optiTiles.add(starter);
		starter.setMark(true);
		this.fillPath(tab, starter.getX(), starter.getY());
		if(this.closed)
		    System.out.println("J'ai créé un trajet de valeur "+ this.closed);
	}
	
    /**
     * Constructeur vide de la grille de jeu
     * 
     */
	public Path() {
		this.closed = true;
		this.optiTiles = new ArrayList<>();
	}
	
	/**
     * Retourne le circuit 
     * 
     * @return optiTiles
     * 
     */
	public List<OptiTile> getPath(){
		return this.optiTiles;
	}
	
	/**
     * Change l'��tat du circuit
     * 
     * @param closed
     *          L'��tat du circuit
     * 
     */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	/**
     * Renvoie l'��tat du circuit
     * 
     * @return closed
     * 
     */
	public boolean getClosed() {
		return closed;
	}
	
	/**
     * Retourne le nombre de croix d'un circuit
     * 
     * @return nb le nombre de croix
     * 
     */
	public int nbCross() {
		int nb = 0;
		for (int i = 0; i < this.optiTiles.size(); i++) {
			if(this.optiTiles.get(i).getType() == Type.Cross) {
				nb++;
			}
		}		 
		return nb;
	}
	
	/**
     * Retourne vrai si la case est bien existante et dans les bornes du tableau, sinon faux
     * 
     * @return exists
     * 
     */
	private boolean checkIfTileExists(OptiTile[][] grid, int coordX, int coordY) { 
		boolean exist = true;
		try {
			OptiTile t = grid[coordX][coordY];
		}catch (Exception e){
			//System.out.println("erreur ? "+coordX +" "+coordY);
			exist = false;
		}
		return exist;
	}
	
	/**
     * Retourne vrai si la direction est dans le tableau
     * 
     * @param t
     *          Le tableau de directions possibles
     * @param dir
     *          Une direction
     * 
     */
	private boolean checkIfDirectionExists(Direction[] t, Direction dir) { 
		if(t != null) {
			for(Direction d : t) {
				if(dir == d)
					return true;
			}
		}
		return false;
	}
	
	//
	/**
     * Recherche toutes les tuiles composants un circuit, qu'il soit ferm�� ou ouvert
     * 
     */
	private void fillPath(OptiTile[][] grid, int i, int j) {
		for(Direction dir : Main.ConnectionsMap.get(grid[i][j].getType())) {
			switch(dir) {
				case Top:
					if (this.checkIfTileExists(grid,i-1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i-1][j].getType()),Direction.Down)) {
						if (!grid[i-1][j].getMark()) { //!this.ownsTile(grid[i-1][j])
						    grid[i-1][j].setMark(true);
							this.getPath().add(grid[i-1][j]);
							this.fillPath(grid, i-1, j);
						}
					} else {
						if(this.getClosed() != false)
							this.setClosed(false);
						//System.out.println("Circuit ouvert Top");
					}
					break;
				
				case Down:
					if (this.checkIfTileExists(grid,i+1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i+1][j].getType()),Direction.Top)) {
						if (!grid[i+1][j].getMark()) { //!this.ownsTile(grid[i+1][j])
	                        grid[i+1][j].setMark(true);
							this.getPath().add(grid[i+1][j]);
							this.fillPath(grid, i+1, j);
							}
					} else {
						if(this.getClosed() != false)
							this.setClosed(false);
						//System.out.println("Circuit ouvert Down");
					}
					break;
				case Left:
					if (this.checkIfTileExists(grid,i,j-1) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i][j-1].getType()),Direction.Right)) {
						if (!grid[i][j-1].getMark()) { //!this.ownsTile(grid[i][j-1])
	                        grid[i][j-1].setMark(true);
							this.getPath().add(grid[i][j-1]);
							this.fillPath(grid, i, j-1);
						}
					} else {
						if(this.getClosed() != false)
							this.setClosed(false);
						//System.out.println("Circuit ouvert Left");
					}
					break;
				case Right:
					if (this.checkIfTileExists(grid,i,j+1) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i][j+1].getType()),Direction.Left)) {
						if (!grid[i][j+1].getMark()) { //!this.ownsTile(grid[i][j+1])
                            grid[i][j+1].setMark(true);
							this.getPath().add(grid[i][j+1]);
							this.fillPath(grid, i, j+1);
						}
					} else {
						if(this.getClosed() != false)
							this.setClosed(false);
						//System.out.println("Circuit ouvert Right");
					}
					break;
			}
		}
	}

}
