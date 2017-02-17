package gameElement;

public class Game {
	private Player player0;
	private Player player1;
	private Queen queen0;
	private Queen queen1;
	private Rock rock0;
	private Rock rock1;
	private Empty empty;	
	
	public Empty getEmpty() {
		return empty;
	}

	public Game(){
		this.player0 = new Player(0);
		this.player1 = new Player(1);
		this.queen0 = new Queen(this.player0);
		this.queen1 = new Queen(this.player1);
		this.rock0 = new Rock(this.player0);
		this.rock1 = new Rock(this.player1);
		this.empty = new Empty(this.player0);
	}
	
	public Player getPlayer0() {
		return this.player0;
	}

	public Player getPlayer1() {
		return this.player1;
	}

	public Queen getQueen0() {
		return this.queen0;
	}

	public Queen getQueen1() {
		return this.queen1;
	}

	public Rock getRock0() {
		return this.rock0;
	}
	
	public Rock getRock1() {
		return this.rock1;
	}
	
	public void setColorMode(){
		if(this.player0.getColorMode().equals("bw")){
			this.player0.setColorMode("og");
			this.player1.setColorMode("og");
		}else{
			this.player0.setColorMode("bw");
			this.player1.setColorMode("bw");
		}
	}
	
	public Player otherPlayer(Player p){
		Player play = null;
		if(p.getNumber() == this.player0.getNumber()){
			play = this.player1;
		}else{
			play = this.player0;
		}
		return play;
	}
	
}
