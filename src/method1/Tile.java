package method1;

public class Tile {

	public enum marquage{
		DeadEnd, 
		Start, 
		Connect, 
		Cross;
		}
	
	private marquage state;
	
	private boolean n; //4 points cardinaux à retravailler
	private boolean s;
	private boolean e;
	private boolean w;
	
	private int value;
	
	public Tile () {
		
	}
	

}
