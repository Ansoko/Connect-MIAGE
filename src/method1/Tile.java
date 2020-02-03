package method1;

public class Tile {

	public enum mark{
		DeadEnd, 
		Start, 
		Connect, 
		Cross;
	}

	private mark state;

	private int num; //numéro de la tuile correspond à son type
	/* 0 : vide
	 * 1 : ns   2 : we 
	 * 3 : ne   4 : se   5 : sw  6 : nw 
	 * 7 : croix
	 */

	public Tile(int num) {
		this.num = num;
	}

	public Tile() {
		num = 0+(int)(Math.random() * (7 - 0 + 1));
	}

	//factory
	public static Tile createTile() {
		return new Tile();
	}	


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public mark getState() {
		return state;
	}

	public void setState(mark state) {
		this.state = state;
	}

	public String toString() {
		return num+"";
	}

	/**
	 * isConnected retourne vrai si les deux tuilles peuvent être l'une à coté de l'autre.
	 * 
	 * @param t
	 * (dans le cas où la tuille est au bord, on considérera le bord comme une tuille t==null)
	 * @param pos
	 * : 1=nord, 2=est, 3=sud, 4=ouest
	 * @return 
	 * <ul>
	 * <li>Retourne VRAI si les tuilles sont connectées,</li>
	 * <li>Retourne FAUX si la tuille est connectée à la tuille t alors qu'elle ne devrait pas,</li>
	 * <li>Retourne VRAI si les tuilles ne sont pas connectées.</li>
	 * </ul>
	 */
	public boolean isConnected(Tile t, int pos) {	
		//si case vide
		if(num==0)
			return false;

		switch(pos) {
		case 1:
			if(t==null && (num==2 || num==4 || num==5))
				return true; //pas connecté au bord, cad que la tuille peut être positionnée ici
			if(t==null)
				return false;

			if((num==1 || num==3 || num==6 || num==7) && (t.num==1 || t.num==4 || t.num==5 || t.num==7))
				return true;
			if((num==1 || num==3 || num==6 || num==7) && (t.num==2 || t.num==3 || t.num==6 || t.num==0))
				return false;
			break;
		case 2:
			if(t==null && (num==1 || num==5 || num==6))
				return true;
			else if(t==null)
				return false;

			if((num==2 || num==3 || num==4 || num==7) && (t.num==2 || t.num==5 || t.num==6 || t.num==7))
				return true;
			if((num==2 || num==3 || num==4 || num==7) && (t.num==1 || t.num==3 || t.num==4 || t.num==0))
				return false;
			break;
		case 3:
			if(t==null && (num==2 || num==3 || num==6))
				return true;
			else if(t==null)
				return false;

			if((num==1 || num==4 || num==5 || num==7) && (t.num==1 || t.num==3 || t.num==6 || t.num==7))
				return true;
			if((num==1 || num==4 || num==5 || num==7) && (t.num==2 || t.num==4 || t.num==5 || t.num==0))
				return false;
			break;
		case 4:
			if(t==null && (num==1 || num==3 || num==4))
				return true;
			else if(t==null)
				return false;

			if((num==2 || num==5 || num==6 || num==7) && (t.num==2 || t.num==3 || t.num==4 || t.num==7))
				return true;
			if((num==2 || num==5 || num==6 || num==7) && (t.num==1 || t.num==5 || t.num==6 || t.num==0))
				return false;
			break;
		default:
			return false;
		}

		return true;
	}
}
