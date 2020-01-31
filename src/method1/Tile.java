package method1;

public class Tile {

	public enum marquage{
		DeadEnd, 
		Start, 
		Connect, 
		Cross;
	}

	private marquage state;

	private int num; //numéro de la tuile 
	/*
	 * 1 : ns,  2 : we
	 * 3 : ne   4 : se   5 : sw  6 : nw
	 * 7 : croix
	 */

	public Tile () {
		//appel de la factory ? y
	}

	public boolean isConnected(Tile t, int pos) { //pos : 1=nord, 2=est, 3=sud, 4=ouest
		switch(pos) {
		case 1:
			if((num==1 || num==3 || num==6 || num==7) && (t.num==1 || t.num==4 || t.num==5 || t.num==7))
				return true;
			break;
		case 2:
			if((num==2 || num==3 || num==4 || num==7) && (t.num==2 || t.num==5 || t.num==6 || t.num==7))
				return true;
			break;
		case 3:
			if((num==1 || num==4 || num==5 || num==7) && (t.num==1 || t.num==3 || t.num==6 || t.num==7))
				return true;
			break;
		case 4:
			if((num==2 || num==5 || num==6 || num==7) && (t.num==2 || t.num==3 || t.num==4 || t.num==7))
				return true;
			break;
		default:
			return false;
		}
		return false;
	}
}
