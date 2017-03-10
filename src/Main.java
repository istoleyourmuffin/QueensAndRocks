import java.util.Scanner;

import gameElement.*;
import graphics.GameUI;


public class Main {
	
	public void testFonctions(){
		Board b = new Board(8);
		String s = b.toString();
		//System.out.println(s);
		//System.out.println("------------------------------------");
		Game game = b.getGame();
		//String s2 = b.toStringAccess();
		//System.out.println(s2);
		int i1 = b.numberOfAccessible();
		System.out.println("Nombre de cases accessibles : " + i1);
		int i2 = b.numberOfQueens();
		System.out.println("Nombre de reines : " + i2);
		long t = System.currentTimeMillis();
		System.out.println(b.solutionSteps2(b));
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t);
		/*b.setPiece(5, 2, game.getQueen0());
		b.setPiece(2, 3, game.getQueen0());
		int[] array = b.boardToArray();
		System.out.print("[ ");
		for (int i = 0 ; i < array.length ; i++)
			System.out.print(array[i]+" ");
		System.out.println("]");*/
		System.out.println("\n--------------------Test depthFirstSearchArray--------------------\n");
		int[] array = b.boardToArray();
		long t3 = System.currentTimeMillis();
		System.out.println(Board.solutionSteps(array));
		long t4 = System.currentTimeMillis();
		System.out.println(t2 - t);
	}
	
	public void testUI(){
		Board b = new Board();
		GameUI g = new GameUI(b,2);
		g.launch();
	}
	
	
	public void testSolo() {
		Board b = new Board();
		String stringAccess;
		Scanner reader = new Scanner(System.in);
		int i, j;
		while(b.numberOfQueens() < 8 && b.numberOfAccessible() > 0){
			stringAccess = b.toStringAccess();
			System.out.println("Tableau actuel");
			System.out.println(stringAccess);
			System.out.println("Voulez-vous placer votre reine ?\nEn hauteur:");
			i = reader.nextInt();
			System.out.println("En largeur");
			j = reader.nextInt();
			if(!b.placeQueen(i, j))
				System.out.println("La reine ne peut pas �tre plac�e ici");
		}
		stringAccess = b.toStringAccess();
		System.out.println("Tableau actuel");
		System.out.println(stringAccess);
		if(b.numberOfQueens() == 8)
			System.out.println("Gagn� !");
		else 
			System.out.println("Perdu !");
		reader.close();
	}
	
	public static void main(String[] args){
		Main m = new Main();
		//m.testSolo();
		m.testFonctions();
		//m.testUI();
	}
	
}
