package gameElement;

public class Eval1 implements Eval {

	public float getEval(Player player, Board b) {
		int nbAccess = b.numberOfAccessible2(player)-b.numberOfAccessible2(b.getGame().otherPlayer(player));
		int nbImprenables = 0;
		Square currentPiece;
		for(int i = 0; i < b.getSize(); i++){
			for(int j = 0; j < b.getSize(); j++){
				currentPiece = b.getPiece(i, j);
				if(currentPiece instanceof Queen){
						if((i == 0 && j == 0) || (i == 0 && j == b.getSize()-1) || (i == b.getSize()-1 && j == 0) || (i == b.getSize()-1 && j == b.getSize()-1)){
							if(currentPiece.getPlayer().equals(player))
								nbImprenables += 3;
							else
								nbImprenables -= 3;
						}else if((i == 0) || (j == 0) || (i == b.getSize()-1) || (j == b.getSize()-1)){
							if(currentPiece.getPlayer().equals(player))
								nbImprenables += 5;
							else
								nbImprenables -= 5;
						}else{
							if(currentPiece.getPlayer().equals(player))
								nbImprenables += 8;
							else
								nbImprenables -= 8;
						}
				}
			}
		}
		return nbAccess+nbImprenables;
	}

}
