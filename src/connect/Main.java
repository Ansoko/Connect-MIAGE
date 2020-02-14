package connect;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JFrame;
import method1.*;
import method2.*;
import graphic.*;
import algo.opti.*;

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
		
		//TESTS
		Grid gridTest = new Grid(3,3);
		gridTest.setGrid(0, 0, new OptiTile(0, 0, Type.TopLeft));
		gridTest.setGrid(0, 1, new OptiTile(0, 1, Type.Vertical));
		gridTest.setGrid(0, 2, new OptiTile(0, 2, Type.TopRight));
		
		gridTest.setGrid(1, 0, new OptiTile(1, 0, Type.Horizontal));
		gridTest.setGrid(1, 1, new OptiTile(1, 1, Type.Empty));
		gridTest.setGrid(1, 2, new OptiTile(1, 2, Type.Horizontal));
		
		gridTest.setGrid(2, 0, new OptiTile(2, 0, Type.DownRight));
		gridTest.setGrid(2, 1, new OptiTile(2, 1, Type.Vertical));
		gridTest.setGrid(2, 2, new OptiTile(2, 2, Type.DownLeft));
		
		gridTest.launcher();
		
		//FIN TESTS
		/*
		Board p1 = new Board();
		System.out.println(p1.toString());
		
		p1.iterativResearch();		
		Graphic window = new Graphic();
		*/
	}

}
