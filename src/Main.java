import java.util.Scanner;

import gameElement.*;
import graphics.GameUI;


public class Main {
	
	public void testFonctions(){
		Board b = new Board();
		String s = b.toString();
		System.out.println(s);
		System.out.println("------------------------------------");
		Game game = b.getGame();
		b.setPiece(5, 2, game.getQueen0());
		b.setPiece(1, 5, game.getRock1());
		b.setPiece(2, 3, game.getQueen1());
		String s2 = b.toStringAccess();
		System.out.println(s2);
		int i1 = b.numberOfAccessible();
		System.out.println("Nombre de cases accessibles : " + i1);
		int i2 = b.numberOfQueens();
		System.out.println("Nombre de reines : " + i2);
		
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
			System.out.println("Où voulez-vous placer votre reine ?\nEn hauteur:");
			i = reader.nextInt();
			System.out.println("En largeur");
			j = reader.nextInt();
			if(!b.placeQueen(i, j))
				System.out.println("La reine ne peut pas être placée ici");
		}
		stringAccess = b.toStringAccess();
		System.out.println("Tableau actuel");
		System.out.println(stringAccess);
		if(b.numberOfQueens() == 8)
			System.out.println("Gagné !");
		else 
			System.out.println("Perdu !");
		reader.close();
	}
	
	public static void main(String[] args){
		Main m = new Main();
		m.testSolo();
	}
	
}
