package gameElement;

public class Rock implements Square {
	private Player player;

	public Rock(Player p){
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
			s = "R";
		}else{
			s = "r";
		}
		return s;
	}

}
