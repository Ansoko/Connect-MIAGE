package connect;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algo.iterative.*;
import algo.recursive.*;
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

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int nb;
        System.out.println("Entrez un nombre de 1 à 13 pour accéder au test correspondant. "
                + "\nEntrez 0 pour avoir un affichage de tableau aléatoire.");
        nb = sc.nextInt();
        if(nb > 0 && nb < 14) {
            String filepath = "./Samples/sujet_test"+ nb + ".txt";
            List<String[]> tableTest = uploadTest(filepath);
            Graphic window = new Graphic(tableTest);
            launchTestsOnAlgoRecursive(tableTest);
            launchTestsOnAlgoIterative(tableTest);
        }
        else if(nb == 0) {
            int line = (int) Math.floor(Math.random() * 12+1);
            int row = (int) Math.floor(Math.random() * 30+1);
            System.out.println("Taille du tableau généré : "+line +"*"+ row);
            List<String[]> tableTest = createAleaTab(line,row);
            Graphic window = new Graphic(tableTest);
            launchTestsOnAlgoRecursive(tableTest);
            launchTestsOnAlgoIterative(tableTest);
        }
        else {
            System.out.println("Test inexistant.");
        }
        sc.close();
    }

    private static List<String[]> uploadTest(String filepath) throws IOException {
        //circuit de test
        FileInputStream fstream = new FileInputStream(filepath);
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
        System.out.println("\n\nRésultats de l'algo Itératif : \n");
        Board p1 = new Board(tableTest);
        long start = System.nanoTime();
        p1.iterativResearch();
        double time = (System.nanoTime() - start);
        System.out.println("TEMPS D'EXÉCUTION : "+time + " ns");
    }

    public static void launchTestsOnAlgoRecursive(List<String[]> tableTest) {
        System.out.println("\nRésultats de l'algo Récursif : \n");
        Grid gridTest = new Grid(tableTest.size(),tableTest.get(0).length);
        gridTest.reset();
        for (int i=0; i<tableTest.size(); i++) {
            for (int j=0; j<tableTest.get(i).length; j++){
                gridTest.setTile(i, j, new OptiTile(i, j, tableTest.get(i)[j]));
            }
        }
        long start = System.nanoTime();
        gridTest.launcher();
        gridTest.lookForLongest();
        gridTest.getResult();
        double time = (System.nanoTime() - start);
        System.out.println("TEMPS D'EXÉCUTION : "+time + " ns");         
    }
    
    public static List<String[]> createAleaTab(int line, int row) {
        List<String[]> tableTest = new ArrayList<String[]>();
        String chars = " ║═╚╔╗╝╬";
        String[] chain;
        for (int i = 0; i < line; i++) {
            chain = new String[row];
            for (int j = 0; j < row; j++) {
                int a = (int)Math.floor(Math.random() * 8); 
                chain[j] = ""+chars.charAt(a);
            }
            tableTest.add(chain);
        }
        return tableTest;
    }
}
