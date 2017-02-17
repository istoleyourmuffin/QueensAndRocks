package gameElement;

public class Player {
	private int number;
	private String colorMode;
	
	public Player(int n){
		this.number = n;
		this.colorMode = "bw";
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public String getColorMode(){
		return this.colorMode;
	}
	
	public void setNumber(int n){
		this.number = n;
	}
	
	public void setColorMode(String s){
		this.colorMode = s;
	}
	
	public String toString(){
		String s = "";
		if(this.number == 0){
			if(this.colorMode.equals("bw")){
				s = "white";
			}else{
				s = "green";
			}
		}else{
			if(this.colorMode.equals("bw")){
				s = "black";
			}else{
				s = "orange";
			}
		}
		return s;
	}
}
