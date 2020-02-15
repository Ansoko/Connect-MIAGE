package algo.opti;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import connect.*;

public class Path {
	private boolean cycle;
	private List<OptiTile> optiTiles;
	
	public Path(OptiTile[][] grid, OptiTile starter) {
		this.cycle = true;
		this.optiTiles = new ArrayList<>();
		this.optiTiles.add(starter);
		this.fillPath(grid, starter.getX(), starter.getY());
		System.out.println("J'ai créé un trajet");
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
	private boolean checkIfTileExists(OptiTile[][] grid, int x, int y) { 
		boolean exist = true;
		try {
			OptiTile t = grid[x][y];
		}catch (Exception e){
			System.out.println("erreur ?"+x +" "+y);
			exist = false;
		}
		return exist;
	}
	
	//Retourne vrai si la direction est dans le tableau
		private boolean checkIfDirectionExists(Direction[] t, Direction dir) { 
			int i = 0; //to erase
			if(t != null) {
				for(Direction d : t) {
					System.out.println("on compare " + dir + " avec la case "+ i + " " + d);
					if(dir == d) {
						System.out.println("check ok");
						return true;
					}
					i++;
				}
			}
			return false;
		}
	
	//Retourne vrai si le trajet possède une tuile donnée
	public boolean ownsTile(OptiTile t) {
		return optiTiles.contains(t);
	}
	
	//remplit le trajet même s'il est pas fermé
	private void fillPath(OptiTile[][] grid, int i, int j) {
		System.out.println(i+" / "+j);
		for(Direction dir : Main.ConnectionsMap.get(grid[i][j].getType())) {
			System.out.println(grid[i][j].getType());
			switch(dir) {
				case Top:
					System.out.println("Top"+ i +" " + j);
					if(this.checkIfTileExists(grid,i-1,j)) { //to erase
						System.out.println("Vérification : "+grid[i-1][j].getType());
					}
					if (this.checkIfTileExists(grid,i-1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i-1][j].getType()),Direction.Down)) {
						if (!this.ownsTile(grid[i-1][j])) {
							System.out.println("la case "+(i-1)+" "+j+ " n'est pas dans le trajet");
							this.getPath().add(grid[i-1][j]);
							System.out.println("Top est ajouté");
							this.fillPath(grid, i-1, j);
						}
					} else {
						if(this.getCycle() != false)
							this.setCycle(false);
						System.out.println("Circuit ouvert Top");
					}
					break;
				
				case Down:
					System.out.println("Down " + i +" " + j);
					if(this.checkIfTileExists(grid,i+1,j)) { //to erase
						System.out.println("Vérification : "+grid[i+1][j].getType());
					}
					if (this.checkIfTileExists(grid,i+1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i+1][j].getType()),Direction.Top)) {
						if (!this.ownsTile(grid[i+1][j])) {
							System.out.println("la case "+(i+1)+" "+j+ " n'est pas dans le trajet");
								this.getPath().add(grid[i+1][j]);
								System.out.println("Down est ajouté");
								this.fillPath(grid, i+1, j);
							}
						} else {
							if(this.getCycle() != false)
								this.setCycle(false);
							System.out.println("Circuit ouvert Down");
						}
					break;
				case Left:
					System.out.println("Left" + i +" " + j);
					if(this.checkIfTileExists(grid,i,j-1)) { //to erase
						System.out.println("Vérification : "+grid[i][j-1].getType());
					}
					if (this.checkIfTileExists(grid,i,j-1) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i][j-1].getType()),Direction.Right)) {
						if (!this.ownsTile(grid[i][j-1])) {
							System.out.println("la case "+i+" "+(j-1)+ " n'est pas dans le trajet");
							this.getPath().add(grid[i][j-1]);
							System.out.println("Left est ajouté");
							this.fillPath(grid, i, j-1);
						}
					} else {
						if(this.getCycle() != false)
							this.setCycle(false);
						System.out.println("Circuit ouvert Left");
					}
					break;
				case Right:
					System.out.println("Right" + i +" " + j);
					if(this.checkIfTileExists(grid,i,j+1)) { //to erase
						System.out.println("Vérification : "+grid[i][j+1].getType());
					}
					if (this.checkIfTileExists(grid,i,j+1) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i][j+1].getType()),Direction.Left)) {
						if (!this.ownsTile(grid[i][j+1])) {
							System.out.println("la case "+i+" "+(j+1)+ " n'est pas dans le trajet");
							this.getPath().add(grid[i][j+1]);
							System.out.println("Right est ajouté");
							this.fillPath(grid, i, j+1);
						}
					} else {
						if(this.getCycle() != false)
							this.setCycle(false);
						System.out.println("Circuit ouvert Right");
					}
					break;
			}
		}
	}

}
