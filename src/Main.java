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
		String s2 = b.toString();
		System.out.println(s2);
		
		GameUI g = new GameUI(b,2);
		g.launch();
	}
	
}
