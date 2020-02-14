package connect;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JFrame;
import algo.opti.Direction;
import algo.opti.Type;
import method1.*;
import method2.*;
import graphic.*;

public class Main {
	
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
		
	public static void main(String[] args) {
		
		Board p1 = new Board();
		System.out.println(p1.toString());
		
		p1.iterativResearch();		
		Graphic window = new Graphic();
		
	}

}
