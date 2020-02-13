package algo.opti;
import java.util.Map;
import java.util.HashMap;

public class Tile {
	
	public enum Type{
		Empty, 
		DownLeft, 
		DownRight,
		TopLeft,
		TopRight,
		Vertical,
		Horizontal,
		Cross
	}
	
	public enum Direction{
		Top, 
		Down, 
		Right,
		Left
	}
	
	public static final Map<Type, Direction[]> ConnectionsMap ;
		static {
			ConnectionsMap = new HashMap<>();
		    ConnectionsMap.put(Type.Empty, null);
		    ConnectionsMap.put(Type.DownLeft, new Direction[] {Direction.Top,Direction.Right});
		    ConnectionsMap.put(Type.DownRight, new Direction[] {Direction.Top,Direction.Left});
		    ConnectionsMap.put(Type.TopLeft, new Direction[] {Direction.Down,Direction.Right});
		    ConnectionsMap.put(Type.TopRight, new Direction[] {Direction.Down,Direction.Left});
		    ConnectionsMap.put(Type.Vertical, new Direction[] {Direction.Top,Direction.Down});
		    ConnectionsMap.put(Type.Horizontal, new Direction[] {Direction.Left,Direction.Right});
		    ConnectionsMap.put(Type.Cross, new Direction[] {Direction.Top,Direction.Right,Direction.Down,Direction.Left});
		}
	
	private int x;
	private int y;
	private Type type;
	
	public Tile(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
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

}
