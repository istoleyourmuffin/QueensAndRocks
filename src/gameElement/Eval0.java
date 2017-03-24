package gameElement;

public class Eval0 implements Eval{

	public float getEval(Player player, Board b) {
		return b.getScore(player) - b.getScore(b.getGame().otherPlayer(player));
	}

}
