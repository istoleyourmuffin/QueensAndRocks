package gameElement;

import java.util.ArrayList;


public class Board {
	
	//ATTENTION
	//Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
	//Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
	
	
	
	//---------------TP1------------------------
	public Square getPiece(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccessible(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	public int numberOfAccessible() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numberOfQueens() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean placeQueen(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//----------TP2-----------------------
	public ArrayList<Board> depthFirstSearch(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//------------TP3----------------------
	public boolean isAccessible2(int i, int j, Player currentPlayer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public boolean placeQueen2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean placeRock2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getNumberOfRocksLeft(Player player){
		// TODO Auto-generated method stub
		return 0;  
	}
	
	public int getScore(Player player){
		// TODO Auto-generated method stub
		return 0;
	}



	//----------------------TP4&5--------------------------
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	





	
	

}
