package connect;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import method1.*;
import method2.*;
import graphic.*;
import algo.opti.*;

public class Main {
    
    final static int TEST = 100;
	
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
	    launchTestsOnAlgoOpti();
		
		
		/*
		Board p1 = new Board();
		System.out.println(p1.toString());
		
		p1.iterativResearch();		
		Graphic window = new Graphic();
		*/
	}
	
	
	public static void launchTestsOnAlgoOpti() {
	    double avg =0;
	    Grid gridTest = new Grid(10,10);
	    gridTest.createAleaTab();
	    OptiTile[][] saved = gridTest.getTab();
        try {
            File file = new File("records_10-10.txt");
            FileWriter fileW = new FileWriter(file);
            
            for (int k = 0; k < TEST; k++) {
                gridTest.reset(saved);
                System.out.println(gridTest.getMaxLength());
    //            Grid gridTest = new Grid(4,5);
    //            gridTest.setGrid(0, 0, new OptiTile(0, 0, Type.Empty));
    //            gridTest.setGrid(0, 1, new OptiTile(0, 1, Type.TopLeft));
    //            gridTest.setGrid(0, 2, new OptiTile(0, 2, Type.Horizontal));
    //            gridTest.setGrid(0, 3, new OptiTile(0, 3, Type.TopRight));
    //            gridTest.setGrid(0, 4, new OptiTile(0, 4, Type.TopRight));
    //              
    //            gridTest.setGrid(1, 0, new OptiTile(1, 0, Type.TopLeft));
    //            gridTest.setGrid(1, 1, new OptiTile(1, 1, Type.Cross));
    //            gridTest.setGrid(1, 2, new OptiTile(1, 2, Type.TopRight));
    //            gridTest.setGrid(1, 3, new OptiTile(1, 3, Type.Vertical));
    //            gridTest.setGrid(1, 4, new OptiTile(1, 4, Type.TopRight));
    //              
    //            gridTest.setGrid(2, 0, new OptiTile(2, 0, Type.DownLeft));
    //            gridTest.setGrid(2, 1, new OptiTile(2, 1, Type.Cross));
    //            gridTest.setGrid(2, 2, new OptiTile(2, 2, Type.DownRight));
    //            gridTest.setGrid(2, 3, new OptiTile(2, 3, Type.Vertical));
    //            gridTest.setGrid(2, 4, new OptiTile(2, 4, Type.TopRight));
    //              
    //            gridTest.setGrid(3, 0, new OptiTile(3, 0, Type.Empty));
    //            gridTest.setGrid(3, 1, new OptiTile(3, 1, Type.DownLeft));
    //            gridTest.setGrid(3, 2, new OptiTile(3, 2, Type.Horizontal));
    //            gridTest.setGrid(3, 3, new OptiTile(3, 3, Type.DownRight));
    //            gridTest.setGrid(3, 4, new OptiTile(3, 4, Type.TopRight));
    //            gridTest.showTab();
                
         
                long start = System.nanoTime();
                callAlgoOpti(gridTest);
                double time = (System.nanoTime() - start);
                avg = avg+time;
                System.out.println("TEMPS D'EXÉCUTION : "+time + " ns");          
                fileW.write("\nTemps d'exécution : "+time+" ns.");
            }
            fileW.write("\n \n");
            for (int i = 0; i < gridTest.getTab().length; i++) {
                for (int j = 0; j < gridTest.getTab().length; j++) {
                    fileW.write(Grid.DrawMap.get(gridTest.getTab()[i][j].getType()));
                }
                fileW.write("\n");
            }
            if(gridTest.getMaxLength()!=0) {
                fileW.write("\nLongueur du plus long chemin : "+ gridTest.getMaxLength());
            }else{
                fileW.write("\nPas de circuit fermé.");
            }
            fileW.write("\n \n");
            System.out.println();
            avg=avg/TEST;
            fileW.write("La moyenne d'exécution des tests est de "+avg+" ns.");
            fileW.flush();
            fileW.close();
        }catch(Exception e) {
            System.err.println("Error: "+ e.getMessage());
        }
    }
	
	/**
     * Appelle l'algo de composition des circuits sur la grille de jeu et recherche le plus long fermé
     * 
     */
	public static void callAlgoOpti(Grid gridTest) {
		gridTest.launcher();
		gridTest.lookForLongest();
		gridTest.getResult();
	}

}
