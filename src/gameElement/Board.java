package gameElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


public class Board {

	private Game game;
	private int size;
	private int numberOfPieces;
	private Square[][] board;

	public Board(){
		this.game = new Game();
		this.size = 8;
		this.numberOfPieces = 0;
		this.board = new Square[this.size][this.size];
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				board[i][j] = game.getEmpty();
			}
		}
	}
	
	public Board(int s){
		this.game = new Game();
		this.size = s;
		this.numberOfPieces = 0;
		this.board = new Square[this.size][this.size];
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				board[i][j] = game.getEmpty();
			}
		}
	}

	public Board(Board b){
		this.game = b.getGame();
		this.size = b.getSize();
		this.numberOfPieces = b.getNumberOfPieces();
		this.board = new Square[this.size][this.size];
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				board[i][j] = b.getPiece(i,j);
			}
		}
	}

	//---------------TP1------------------------
	public Square getPiece(int i, int j) {
		// TODO Auto-generated method stub
		return board[i][j];
	}

	public void setPiece(int i, int j, Square piece){
		if(this.getPiece(i,j) instanceof Empty){
			if(!(piece instanceof Empty))
				this.numberOfPieces++;
		}else{
			if(piece instanceof Empty){
				this.numberOfPieces--;
			}
		}
		board[i][j] = piece;
	}

	public void removePiece(int i, int j){
		if(!(this.isEmpty(i,j))){
			board[i][j] = game.getEmpty();
			this.numberOfPieces--;
		}
	}

	public boolean isEmpty(int i, int j){
		return (this.getPiece(i,j) instanceof Empty);
	}

	public String toString(){
		String retour = "";
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				retour += this.getPiece(i,j).toString()+"    ";
			}
			retour += " \n";
		}
		return retour;
	}

	public boolean isAccessible(int i, int j) {
		if(!(this.isEmpty(i,j)) || (i >= size) || (j >= size))
			return false;
		int x = 0;
		int y = 0;
		boolean queenFound = false;
		while(!queenFound && (y < this.size)){
			x = 0;
			while(!queenFound && (x < this.size)){
				if(!(x == i && y == j)) {
					if(this.getPiece(x,y) instanceof Queen && ((x == i) || (y == j) || (Math.abs(x - i) == Math.abs(y - j))))
						queenFound = true;
				}
				x++;
			}
			y++;
		}
		return !queenFound;
	}

	public String toStringAccess() {
		String retour = this.toString();
		retour += "\n---------Cases inacessibles---------\n";
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				if(!this.isAccessible(i,j))
					retour += "X";
				else
					retour += "-";
				retour += "    ";
			}
			retour += " \n";
		}
		return retour;
	}

	public int numberOfAccessible() {
		int nbOfAccessible = 0;
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				if(this.isAccessible(i,j))
					nbOfAccessible++;
			}
		}
		return nbOfAccessible;
	}

	public int numberOfQueens() {
		int nbOfQueens = 0;
		for(int i = 0 ; i < this.size ; i++){
			for(int j = 0 ; j < this.size ; j++){
				if(this.getPiece(i, j) instanceof Queen)
					nbOfQueens++;
			}
		}
		return nbOfQueens;
	}

	public boolean placeQueen(int i, int j) {
		if(this.isAccessible(i, j)){
			this.setPiece(i, j, game.getQueen0());
			return true;
		}
		return false;
	}

	//----------TP2-----------------------
	public static ArrayList<Board> depthFirstSearch(){
		Board b = new Board();
		return b.depthFirstSearch(b);
	}
	
	public ArrayList<Board> depthFirstSearch(Board initialState) {
		ArrayList<Board> solution = null;
		if(this.isSolution()){
			solution = new ArrayList<Board>();
			solution.add(this);
			return solution;
		}
		for(Board succ:this.getSuccessors()){
			solution = succ.depthFirstSearch(initialState);
			if(solution != null){
				solution.add(this);
				return solution;
			}
		}
		if(solution == null && this.equals(initialState))
			throw new NoSuchElementException("Aucune solution trouvée");
		return solution;
	}
	
	public String solutionSteps(Board b){
		String s = "";
		ArrayList<Board> list = b.depthFirstSearch(b);
		Collections.reverse(list);
		for(Board succ : list){
			s+=succ.toString()+"\n ----------------------- \n";
		}
		return s;
	}

	public boolean isSolution() {
		return this.numberOfQueens() == this.getSize();
	}

	public ArrayList<Board> getSuccessors() {
		ArrayList<Board> succList = new ArrayList<Board>();
		Board succ;
		for (int i = 0; i < this.getSize(); i++){
			for (int j = 0; j < this.getSize(); j++){
				if(isAccessible(i,j)){
					succ = new Board(this);
					succ.setPiece(i,j,game.getQueen0());
					succList.add(succ);
				}
			}
		}
		return succList;
	}	
	
	public ArrayList<Board> getNewSuccessors() {
		ArrayList<Board> succList = new ArrayList<Board>();
		Board succ;
		int j = this.numberOfQueens();
		for (int i = 0; i < this.getSize(); i++){
			if(isAccessible(i,j)){
				succ = new Board(this);
				succ.setPiece(i,j,game.getQueen0());
				succList.add(succ);
			}
		}
		return succList;
	}	

	public static ArrayList<Board> depthFirstSearch2(){
		Board b = new Board();
		return b.depthFirstSearch2(b);
	}
	
	public ArrayList<Board> depthFirstSearch2(Board initialState) {
		ArrayList<Board> solution = null;
		if(this.isSolution()){
			solution = new ArrayList<Board>();
			solution.add(this);
			return solution;
		}
		for(Board succ:this.getNewSuccessors()){
			solution = succ.depthFirstSearch2(initialState);
			if(solution != null){
				solution.add(this);
				return solution;
			}
		}
		if(solution == null && this.equals(initialState))
			throw new NoSuchElementException("Aucune solution trouvée");
		return solution;
	}
	
	public String solutionSteps2(Board b){
		String s = "";
		ArrayList<Board> list = b.depthFirstSearch2(b);
		Collections.reverse(list);
		for(Board succ : list){
			s+=succ.toString()+"\n ----------------------- \n";
		}
		return s;
	}
	
	public int[] boardToArray() {
		int[] array = new int[this.getSize()];
		int col = -1;
		for (int j = 0; j < this.getSize(); j++){
			for (int i = 0; i < this.getSize(); i++){
				if(this.getPiece(i, j) instanceof Queen){
					col = i;
				}
			}
			array[j] = col;
			col = -1;
		}
		return array;
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


	/** -------------------------------------------GETTERS ET SETTERS------------------------------------------   */

	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getNumberOfPieces() {
		return numberOfPieces;
	}


	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}






}
