package gameElement;

import java.util.Random;

public class Eval2 implements Eval {

	@Override
	public float getEval(Player player, Board b) {
		int n = 0, square, x , y;
		Random rand = new Random();
		Board currentBoard;
		Player currentPlayer;
		boolean place;
		for (int i = 0; i < 20; i++){
			currentBoard = new Board(b);
			currentPlayer = b.getGame().otherPlayer(player);
			while(!currentBoard.isFinal()){
				square = rand.nextInt();
				place = false;
				if((square%2 == 0 && currentBoard.numberOfAccessible2(currentPlayer) > 0) || currentBoard.getNumberOfRocksLeft(currentPlayer) == 0){ //queen
					while (!place){
						x = rand.nextInt(currentBoard.getSize());
						y = rand.nextInt(currentBoard.getSize());
						place = currentBoard.placeQueen2(x, y, currentPlayer);
					}
				}else{ //rock
					while (!place){
						x = rand.nextInt(currentBoard.getSize());
						y = rand.nextInt(currentBoard.getSize());
						place = currentBoard.placeRock2(x, y, currentPlayer);
					}
				}
				currentPlayer = currentBoard.getGame().otherPlayer(currentPlayer);
			}
			if (currentBoard.getScore(player) > currentBoard.getScore(currentBoard.getGame().otherPlayer(player)))
				n++;
		}
		return 2*n/10-1;
	}

}
