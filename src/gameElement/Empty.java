package gameElement;

public class Empty implements Square{
	private Player player;
	
	public Empty(Player p){
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
		return "E";
	}

}
