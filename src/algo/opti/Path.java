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
	
	//Retourne vrai si la case n'est pas en dehors du grid
	private boolean checkIfTileExists(OptiTile[][] grid, int x, int y) { 
		boolean exist = true;
		try {
			OptiTile t = grid[x][y];
		}catch (Exception e){
			exist = false;
		}
		return exist;
	}
	
	//Retourne vrai si la direction est dans le tableau
		private boolean checkIfDirectionExists(Direction[] t, Direction dir) { 
			for(Direction d : t) {
				System.out.println("check if dir exist :" + d);
			//for(int i = 0; i< t.length; i++) {
				if(dir == d) {
					return true;
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
			switch(dir) {
				case Top:
					System.out.println("Top");
					if (this.checkIfTileExists(grid,i,j-1) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i][j-1].getType()),Direction.Down)) {
						if (!this.ownsTile(grid[i][j-1])) {
							this.getPath().add(grid[i][j-1]);
							System.out.println("Top c'est bon");
							this.fillPath(grid, i, j-1);
						}
					} else {
						this.setCycle(false);
					}
					break;
				
				case Down:
					System.out.println("Down");
					if (this.checkIfTileExists(grid,i,j+1) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i][j+1].getType()),Direction.Top)) {
						if (!this.ownsTile(grid[i][j+1])) {
								this.getPath().add(grid[i][j+1]);
								this.fillPath(grid, i, j+1);
							}
						} else {
							this.setCycle(false);
						}
					break;
				case Left:
					System.out.println("Left");
					if (this.checkIfTileExists(grid,i-1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i-1][j].getType()),Direction.Right)) {
						if (!this.ownsTile(grid[i-1][j])) {
							this.getPath().add(grid[i-1][j]);
							this.fillPath(grid, i-1, j);
						}
					} else {
						this.setCycle(false);
					}
					break;
				case Right:
					System.out.println("Right");
					if (this.checkIfTileExists(grid,i+1,j) && checkIfDirectionExists(Main.ConnectionsMap.get(grid[i+1][j].getType()),Direction.Left)) {
						if (!this.ownsTile(grid[i+1][j])) {
							this.getPath().add(grid[i+1][j]);
							this.fillPath(grid, i+1, j);
						}
					} else {
						this.setCycle(false);
					}
					break;
			}
		}
	}

}
