package connect;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import method1.*;
import algo.opti.*;

public class Main {

    final static int TEST = 10;

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
        launchTestsOnAlgoOpti();
        //launchTestsOnAlgoNoOpti(tableTest);

        /*  
        Graphic window = new Graphic();
         */
    }

    private static List<String[]> uploadTest() throws IOException {
        //circuit de test
        FileInputStream fstream = new FileInputStream("sujet_test.txt");
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

    public static void launchTestsOnAlgoNoOpti(List<String[]> tableTest) {
        double avg =0;
        for (int i = 0; i<TEST; i++) {
            Board p1 = new Board(tableTest);
            //System.out.println(p1.toString());
            long start = System.nanoTime();
            p1.iterativResearch();
            double time = (System.nanoTime() - start);
            avg = avg+time;
            System.out.println("TEMPS D'EXÉCUTION "+ i +" : "+time + " ns");
        }
        avg=avg/TEST;
        System.out.println("La moyenne d'exécution des tests est de "+avg+" ns.");
    }

    public static void launchTestsOnAlgoOpti() {
        double avg =0;
        Grid gridTest = new Grid(10,10);
        String times ="";
        //Aléatoire
        gridTest.createAleaTab();
        gridTest.showTab();
        OptiTile[][] stock = gridTest.getTab();
        //Fin aléatoire

        for (int k = 0; k < TEST; k++) {
            //Aléatoire
            OptiTile[][] saved = gridTest.forcedClone(stock);
            //Fin Aléatoire

            //Défini
            //            OptiTile[][] saved = {{new OptiTile(0, 0, Type.Empty),new OptiTile(0, 1, Type.TopLeft),new OptiTile(0, 2, Type.Horizontal),new OptiTile(0, 3, Type.TopRight),new OptiTile(0, 4, Type.TopRight),new OptiTile(0, 5, Type.TopLeft),new OptiTile(0, 6, Type.Horizontal),new OptiTile(0, 7, Type.Horizontal),new OptiTile(0, 8, Type.Horizontal),new OptiTile(0, 9, Type.TopRight)},
            //                    {new OptiTile(1, 0, Type.TopLeft),new OptiTile(1, 1, Type.Cross),new OptiTile(1, 2, Type.TopRight),new OptiTile(1, 3, Type.Vertical),new OptiTile(1, 4, Type.TopRight),new OptiTile(1, 5, Type.Vertical),new OptiTile(1, 6, Type.TopRight),new OptiTile(1, 7, Type.TopRight),new OptiTile(1, 8, Type.Empty),new OptiTile(1, 9, Type.Vertical)},
            //                    {new OptiTile(2, 0, Type.DownLeft),new OptiTile(2, 1, Type.Cross),new OptiTile(2, 2, Type.DownRight),new OptiTile(2, 3, Type.Vertical),new OptiTile(2, 4, Type.TopRight),new OptiTile(2, 5, Type.Vertical),new OptiTile(2, 6, Type.Horizontal),new OptiTile(2, 7, Type.Cross),new OptiTile(2, 8, Type.TopRight),new OptiTile(2, 9, Type.Vertical)},
            //                    {new OptiTile(3, 0, Type.Empty),new OptiTile(3, 1, Type.DownLeft),new OptiTile(3, 2, Type.Horizontal),new OptiTile(3, 3, Type.DownRight),new OptiTile(3, 4, Type.TopRight),new OptiTile(3, 5, Type.Vertical),new OptiTile(3, 6, Type.Vertical),new OptiTile(3, 7, Type.Cross),new OptiTile(3, 8, Type.TopLeft),new OptiTile(3, 9, Type.DownRight)},
            //                    {new OptiTile(4, 0, Type.DownLeft),new OptiTile(4, 1, Type.TopLeft),new OptiTile(4, 2, Type.Horizontal),new OptiTile(4, 3, Type.TopLeft),new OptiTile(4, 4, Type.TopRight),new OptiTile(4, 5, Type.Vertical),new OptiTile(4, 6, Type.Horizontal),new OptiTile(4, 7, Type.Vertical),new OptiTile(4, 8, Type.Vertical),new OptiTile(4, 9, Type.TopRight)},
            //                    {new OptiTile(5, 0, Type.Horizontal),new OptiTile(5, 1, Type.TopLeft),new OptiTile(5, 2, Type.Horizontal),new OptiTile(5, 3, Type.DownLeft),new OptiTile(5, 4, Type.DownRight),new OptiTile(5, 5, Type.DownLeft),new OptiTile(5, 6, Type.Horizontal),new OptiTile(5, 7, Type.Horizontal),new OptiTile(5, 8, Type.DownRight),new OptiTile(5, 9, Type.TopRight)},
            //                    {new OptiTile(6, 0, Type.Horizontal),new OptiTile(6, 1, Type.Cross),new OptiTile(6, 2, Type.Horizontal),new OptiTile(6, 3, Type.Vertical),new OptiTile(6, 4, Type.Cross),new OptiTile(6, 5, Type.DownLeft),new OptiTile(6, 6, Type.Horizontal),new OptiTile(6, 7, Type.Horizontal),new OptiTile(6, 8, Type.TopLeft),new OptiTile(6, 9, Type.TopRight)},
            //                    {new OptiTile(7, 0, Type.TopLeft),new OptiTile(7, 1, Type.TopLeft),new OptiTile(7, 2, Type.DownLeft),new OptiTile(7, 3, Type.TopRight),new OptiTile(7, 4, Type.DownLeft),new OptiTile(7, 5, Type.Cross),new OptiTile(7, 6, Type.TopRight),new OptiTile(7, 7, Type.Vertical),new OptiTile(7, 8, Type.Vertical),new OptiTile(7, 9, Type.Vertical)},
            //                    {new OptiTile(8, 0, Type.Empty),new OptiTile(8, 1, Type.TopLeft),new OptiTile(8, 2, Type.Horizontal),new OptiTile(8, 3, Type.Cross),new OptiTile(8, 4, Type.Vertical),new OptiTile(8, 5, Type.DownLeft),new OptiTile(8, 6, Type.TopRight),new OptiTile(8, 7, Type.DownLeft),new OptiTile(8, 8, Type.Vertical),new OptiTile(8, 9, Type.Vertical)},
            //                    {new OptiTile(9, 0, Type.TopLeft),new OptiTile(9, 1, Type.TopLeft),new OptiTile(9, 2, Type.Horizontal),new OptiTile(9, 3, Type.Vertical),new OptiTile(9, 4, Type.Cross),new OptiTile(9, 5, Type.TopRight),new OptiTile(9, 6, Type.Horizontal),new OptiTile(9, 7, Type.TopRight),new OptiTile(9, 8, Type.Horizontal),new OptiTile(9, 9, Type.DownRight)}};
            //Fin défini

            gridTest.reset(saved);
            gridTest.showTab();

            long start = System.nanoTime();
            callAlgoOpti(gridTest);
            double time = (System.nanoTime() - start);
            times = times + k+": "+time + " ns \n";
            avg = avg+time;
            System.out.println("TEMPS D'EXÉCUTION : "+time + " ns");          
        }

        File file = new File("records2.txt");
        try {
            FileWriter fileW = new FileWriter(file);
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
            fileW.write(times);
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
