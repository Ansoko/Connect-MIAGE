package method1;

public class Tile {

	public enum marquage{
		DeadEnd, 
		Start, 
		Connect, 
		Cross;
		}
	
	private marquage state;
	
	private boolean n; //4 points cardinaux � retravailler
	private boolean s;
	private boolean e;
	private boolean w;
	
	private int num; //num�ro de la tuile 
	/*
	 * 1 : ns,  2 : we
	 * 3 : ne   4 : se   5 : sw  6 : nw
	 * 7 : croix
	 */
	
	public Tile () {
		//appel de la factory ?
	}
	
	public int getValue() { // renvoie la valeur du trait
		if(this.num == 7) { // si c'est une croix (num�ro 7)
			return 2;
		}
		return 1; // c'est une ligne ou un coin
	}
	
	
}
