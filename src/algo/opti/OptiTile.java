package algo.opti;

public class OptiTile {
	private int x;
	private int y;
	private Type type;
	private boolean mark;
	
	public OptiTile(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.mark = false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public boolean getMark() {
        return this.mark;
    }
	
	public void setMark(boolean mark) {
        this.mark = mark;
    }
}
