package connect;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algo.iterative.*;
import algo.recursive.*;

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

	public static void main(String[] args) throws IOException{

		List<String[]> tableTest = uploadTest();
		launchTestsOnAlgoRecursive(tableTest);
		launchTestsOnAlgoIterative(tableTest);
        
        
	}

	private static List<String[]> uploadTest() throws IOException {
		//circuit de test
		FileInputStream fstream = new FileInputStream("sujet_test4.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		String[] line;
		List<String[]> tableTest = new ArrayList<String[]>();

		while ((strLine = br.readLine()) != null)   {
			line = strLine.split("");
			tableTest.add(line);
		}
		fstream.close();

		return tableTest;	
	}

	public static void launchTestsOnAlgoIterative(List<String[]> tableTest) {
		Board p1 = new Board(tableTest);
		long start = System.nanoTime();
		p1.iterativResearch();
		double time = (System.nanoTime() - start);
		
		System.out.println("TEMPS D'EXÉCUTION : "+time + " ns");
	}

	public static void launchTestsOnAlgoRecursive(List<String[]> tableTest) {
		String times ="";
		Grid gridTest = new Grid(tableTest.size(),tableTest.get(0).length);
		
		gridTest.reset();
		for (int i=0; i<tableTest.size(); i++) {
			for (int j=0; j<tableTest.get(0).length; j++){
				gridTest.setTile(i, j, new OptiTile(i, j, tableTest.get(i)[j]));
			}
		}

		long start = System.nanoTime();
		callAlgoRecursive(gridTest);
		double time = (System.nanoTime() - start);
		
		System.out.println("TEMPS D'EXÉCUTION : "+time + " ns");         
		gridTest.showTab();
	}

	/**
	 * Appelle l'algo de composition des circuits sur la grille de jeu et recherche le plus long fermé
	 * 
	 */
	public static void callAlgoRecursive(Grid gridTest) {
		gridTest.launcher();
		gridTest.lookForLongest();
		gridTest.getResult();
	}
}
