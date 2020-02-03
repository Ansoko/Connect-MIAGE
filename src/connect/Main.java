package connect;
import javax.swing.JFrame;

import method1.*;
import method1.Board;
import method2.*;
import graphic.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board p1 = new Board();
		System.out.println(p1.toString());
		
		p1.iterativResearch();		
		Graphic window = new Graphic();
		
	}

}
