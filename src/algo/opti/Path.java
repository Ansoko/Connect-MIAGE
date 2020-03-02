package algo.opti;
//import java.util.logging.Logger;
//import java.util.logging.Level;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import connect.*;

/**
 * <b>Path est la classe représentant un circuit de tuile.</b>
 * <p>
 * Un circuit est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une liste de tuiles qui le composent</li>
 * <li>Un booléen déterminant si le circuit est fermé ou ouvert</li>
 * </ul>
 * </p>
 * 
 * @see OptiTile
 * 
 * @author Valentine_Bouché
 * @version 1.0
 */
public class Path {
	private boolean cycle;
	private List<OptiTile> optiTiles;
	
	
	public Path(OptiTile[][] grid, OptiTile starter) {
		this.cycle = true;
		this.optiTiles = new ArrayList<>();
		this.optiTiles.add(starter);
		starter.setMark(true);
		this.fillPath(grid, starter.getX(), starter.getY());
		if(this.cycle)
		    System.out.println("J'ai créé un trajet de valeur "+ this.cycle);
	}
	
	public Path() {
		this.cycle = true;
		this.optiTiles = new ArrayList<>();
	}
	
	public List<OptiTile> getPath(){
		return this.optiTiles;
	}
	
	public void setCycle(boolean cycle) {
		this.cycle = cycle;
	}
	
	public boolean getCycle() {
		return cycle;
	}
	
	//retourne le nombre de croix d'un trajet
	public int nbCross() {
		int nb = 0;
		for (int i = 0; i < this.optiTiles.size(); i++) {
			if(this.optiTiles.get(i).getType() == Type.Cross) {
				nb++;
			}
		}		 
		return nb;
	}
	
	//Retourne vrai si la case n'est pas en dehors du grid
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
	
	//Retourne vrai si la direction est dans le tableau
		private boolean checkIfDirectionExists(Direction[] t, Direction dir) { 
			int i = 0; //to erase
			if(t != null) {
				for(Direction d : t) {
					if(dir == d) {
						return true;
					}
					i++;
				}
			}
			return false;
		}
	
	//Retourne vrai si le trajet possède une tuile donnée // obsolète ?
	public boolean ownsTile(OptiTile t) {
		return optiTiles.contains(t); // complecité de la fonction contain ?
	}
	
	//remplit le trajet même s'il est pas fermé
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
						if(this.getCycle() != false)
							this.setCycle(false);
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
						if(this.getCycle() != false)
							this.setCycle(false);
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
						if(this.getCycle() != false)
							this.setCycle(false);
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
						if(this.getCycle() != false)
							this.setCycle(false);
						//System.out.println("Circuit ouvert Right");
					}
					break;
			}
		}
	}

}
