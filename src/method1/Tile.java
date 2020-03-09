package method1;

import java.util.HashMap;
import java.util.Map;

import algo.opti.Grid;

public class Tile {

	public enum mark{
		DeadEnd, 
		Start, 
		Connect, 
		Cross;
	}

	private mark state;

	private int num; //numéro de la tuile correspond � son type
	/* 0 : vide
	 * 1 : ns   2 : we 
	 * 3 : ne   4 : se   5 : sw  6 : nw 
	 * 7 : croix
	 */
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	

	public static final Map<Integer, String> DrawMap2 ;
	static {
		DrawMap2 = new HashMap<>();
		DrawMap2.put(0, " ");
		DrawMap2.put(3, "╚");
		DrawMap2.put(6, "╝");
		DrawMap2.put(4, "╔");
		DrawMap2.put(5, "╗");
		DrawMap2.put(1, "║");
		DrawMap2.put(2, "═");
		DrawMap2.put(7, "╬");
	}
	
	public Tile(String shape) {
		up = false;
		down = false;
		left = false;
		right = false;
		
		if(shape.contains("║")) {
			up = true;
			down = true;
			num = 1;
		}else if(shape.contains("═")) {
			left = true;
			right = true;
			num = 2;
		}else if(shape.contains("╚")) {
			up = true;
			right = true;
			num = 3;
		}else if(shape.contains("╔")) {
			down = true;
			right = true;
			num = 4;
		}else if(shape.contains("╗")) {
			down = true;
			left = true;
			num = 5;
		}else if(shape.contains("╝")) {
			up = true;
			left = true;
			num = 6;
		}else if(shape.contains("╬")) {
			up = true;
			down = true;
			right = true;
			left = true;
			num = 7;
		}else {
			num=0;
		}
	}

	public Tile() { //random
		up = false;
		down = false;
		left = false;
		right = false;
		num = 0+(int)(Math.random() * (7 - 0 + 1));
		if(num == 1) {
			up = true;
			down = true;
		}else if(num == 2) {
			left = true;
			right = true;
		}else if(num == 3) {
			up = true;
			right = true;
		}else if(num == 4) {
			down = true;
			right = true;
		}else if(num == 5) {
			down = true;
			left = true;
		}else if(num == 6) {
			up = true;
			left = true;
		}else if(num == 7) {
			up = true;
			down = true;
			right = true;
			left = true;
		}
			
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

	public boolean getExit(String exit) {
		if(exit == "down") {
			return down;
		}
		if(exit == "up") {
			return up;
		}
		if(exit == "left") {
			return left;
		}
		if(exit == "right") {
			return right;
		}
		return false;
	}
	
	public String toString() {
		return DrawMap2.get((Object)num);
	}

	/**
	 * isConnected retourne vrai si les deux tuilles peuvent �tre l'une � cot� de l'autre.
	 * 
	 * @param t
	 * (dans le cas où la tuille est au bord, on considérera le bord comme une tuille t==null)
	 * @param pos
	 * : 1=nord, 2=est, 3=sud, 4=ouest
	 * @return 
	 * <ul>
	 * <li>Retourne VRAI si les tuilles sont connect�es,</li>
	 * <li>Retourne FAUX si la tuille est connect�e � la tuille t alors qu'elle ne devrait pas,</li>
	 * <li>Retourne VRAI si les tuilles ne sont pas connect�es.</li>
	 * </ul>
	 */
	public boolean isConnected(Tile t, int pos) {	
		//si case vide
		if(num==0)
			return false;

		switch(pos) {
		case 1:
			if(t==null && !up)
				return true; //pas connect� au bord, cad que la tuille peut �tre positionn�e ici
			if(t==null)
				return false;

			if(up && t.down)
				return true;
			if(up && !t.down)
				return false;
			break;
		case 2:
			if(t==null && !right)
				return true;
			else if(t==null)
				return false;

			if(right && t.left)
				return true;
			if(right && !t.left)
				return false;
			break;
		case 3:
			if(t==null && !down)
				return true;
			else if(t==null)
				return false;

			if(down && t.up)
				return true;
			if(down && !t.up)
				return false;
			break;
		case 4:
			if(t==null && !left)
				return true;
			else if(t==null)
				return false;

			if(left && t.right)
				return true;
			if(left && !t.right)
				return false;
			break;
		default:
			return false;
		}

		return true;
	}
}
