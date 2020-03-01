package connect;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
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
		Grid gridTest = new Grid(12,12);
		gridTest.createAleaGrid();
		
//        Grid gridTest = new Grid(4,4);
//        gridTest.setGrid(0, 0, new OptiTile(0, 0, Type.Empty));
//        gridTest.setGrid(0, 1, new OptiTile(0, 1, Type.TopLeft));
//        gridTest.setGrid(0, 2, new OptiTile(0, 2, Type.Horizontal));
//        gridTest.setGrid(0, 3, new OptiTile(0, 3, Type.TopRight));
//          
//        gridTest.setGrid(1, 0, new OptiTile(1, 0, Type.TopLeft));
//        gridTest.setGrid(1, 1, new OptiTile(1, 1, Type.Cross));
//        gridTest.setGrid(1, 2, new OptiTile(1, 2, Type.TopRight));
//        gridTest.setGrid(1, 3, new OptiTile(1, 3, Type.Vertical));
//          
//        gridTest.setGrid(2, 0, new OptiTile(2, 0, Type.DownLeft));
//        gridTest.setGrid(2, 1, new OptiTile(2, 1, Type.Cross));
//        gridTest.setGrid(2, 2, new OptiTile(2, 2, Type.DownRight));
//        gridTest.setGrid(2, 3, new OptiTile(2, 3, Type.Vertical));
//          
//        gridTest.setGrid(3, 0, new OptiTile(3, 0, Type.Empty));
//        gridTest.setGrid(3, 1, new OptiTile(3, 1, Type.DownLeft));
//        gridTest.setGrid(3, 2, new OptiTile(3, 2, Type.Horizontal));
//        gridTest.setGrid(3, 3, new OptiTile(3, 3, Type.DownRight));
//        gridTest.showTab();
        
		long start = System.currentTimeMillis();
		callTestAlgoOpti(gridTest);
		long time = System.currentTimeMillis() - start;
		System.out.println("TEMPS D'EXÉCUTION : "+time);
		
		
		try {
		    File file = new File("D:\\records.txt");
            FileWriter fileW = new FileWriter(file);
            for (int i = 0; i < gridTest.getGrid().length; i++) {
                for (int j = 0; j < gridTest.getGrid().length; j++) {
                    fileW.write(Grid.DrawMap.get(gridTest.getGrid()[i][j].getType()));
                }
                fileW.write("\n");
            }
            fileW.write("\nTemps d'exécution : "+time+" ms.");
            if(gridTest.getMaxLength()!=0) {
                fileW.write("\nLongueur du plus long chemin : "+ gridTest.getMaxLength());
            }else{
                fileW.write("\nPas de circuit fermé.");
            }
            fileW.flush();
            fileW.close();
        }catch(Exception e) {
            System.err.println("Error: "+ e.getMessage());
        }
		
		
		/*
		Board p1 = new Board();
		System.out.println(p1.toString());
		
		p1.iterativResearch();		
		Graphic window = new Graphic();
		*/
	}
	
	public static void callTestAlgoOpti(Grid gridTest) {
		gridTest.launcher();
		gridTest.lookForLongest();
		gridTest.getResult();
	}

}
