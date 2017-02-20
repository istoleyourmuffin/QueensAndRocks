import gameElement.*;
import graphics.GameUI;


public class Main {
	public static void main(String[] args){
		Board b = new Board();
		String s = b.toString();
		System.out.println(s);
		System.out.println("--------------------------------------------------------");
		Game game = b.getGame();
		b.setPiece(5, 2, game.getQueen0());
		b.setPiece(1, 5 , game.getRock1());
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
	
}
