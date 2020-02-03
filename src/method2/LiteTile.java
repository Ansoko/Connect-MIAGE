package method2;

public class LiteTile {

	private boolean n; //4 points cardinaux � retravailler
	private boolean s;
	private boolean e;
	private boolean w;
	
	private boolean mark;
	private int num; //num�ro de la tuile 
	/*
	 * 1 : ns,  2 : we
	 * 3 : ne   4 : se   5 : sw  6 : nw
	 * 7 : croix
	 */
	
	public boolean isCornerSE() {
		if(this.s && this.e && !this.w && !this.n) 
			return true;
		return false;
	}
	
	public LiteTile() {
		this.mark = false;
	}
	
	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public boolean isN() {
		return n;
	}

	public void setN(boolean n) {
		this.n = n;
	}

	public boolean isS() {
		return s;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	public boolean isE() {
		return e;
	}

	public void setE(boolean e) {
		this.e = e;
	}

	public boolean isW() {
		return w;
	}

	public void setW(boolean w) {
		this.w = w;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
