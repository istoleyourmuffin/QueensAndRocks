package gameElement;

public class Queen implements Square {
private Player player;
	
	public Queen(Player p){
		this.player = p;
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

	public void setPlayer(Player p) {
		// TODO Auto-generated method stub
		this.player = p;
	}
	
	public String toString(){
		String s = "";
		if(player.getNumber() == 0){
			s = "Q";
		}else{
			s = "q";
		}
		return s;
	}

}
