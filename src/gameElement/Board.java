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
		int lig = -1;
		for (int j = 0; j < this.getSize(); j++){
			for (int i = 0; i < this.getSize(); i++){
				if(this.getPiece(i, j) instanceof Queen){
					lig = i;
				}
			}
			array[j] = lig;
			lig = -1;
		}
		return array;
	}
	
	public static Board arrayToBoard(int[] array) {
		Board b = new Board(array.length);
		for(int j = 0 ; j < b.getSize() ; j++){
			for(int i = 0 ; i < b.getSize() ; i++){
				if(array[j] == i){
					b.setPiece(i, j, b.getGame().getQueen0());
					b.setNumberOfPieces(b.getNumberOfPieces()+1);
				}else
					b.setPiece(i, j, b.getGame().getEmpty());
			}
		}
		return b;
	}
	
	public static boolean isAccessibleArray(int i, int j, int[] array){
		if (array[j] >= 0 || (i >= array.length) || (j >= array.length)){
			return false;
		}
		boolean queenFound = false;
		int x;
		int y = 0;
		while(!queenFound && (y < array.length)){
			x = 0;
			while(!queenFound && (x < array.length)){
				if(!(x == i && y == j)) {
					if(array[y] >= 0 && ((array[y] == i) || (y == j) || (Math.abs(array[y] - i) == Math.abs(y - j)))) {
						queenFound = true;
					}
				}
				x++;
			}
			y++;
		}
		return !queenFound;
	}
	public static int numberOfQueensArray(int[] array) {
		int nb = 0;
		for(int i=0; i < array.length; i++)
			if(array[i]>=0)
				nb++;
		return nb;
	}
	
	public static ArrayList<int[]> getArraySuccessors(int[] array){
		ArrayList<int[]> succList = new ArrayList<int[]>();
		int[] succ;
		int j = Board.numberOfQueensArray(array);
			for(int i = 0; i < array.length; i++) {
				if(Board.isAccessibleArray(i,j,array)){
					succ = new int[array.length];
					for(int k = 0; k < array.length; k++){
						if (k == j)
							succ[j] = i;
						else
							succ[k] = array[k];
					}
					succList.add(succ);
				}
		}
		return succList;
	}
	
	public static boolean isSolutionArray(int[] array){
		boolean isSolution = true;
		int index = 0;
		while (isSolution && index < array.length){
			if(array[index] < 0)
				isSolution = false;
			index++;
		}
		return isSolution;
	}
	
	public static ArrayList<int[]> depthFirstSearchArray(){
		int[] array = new int[8];
		return Board.depthFirstSearchArray(array);
	}
	
	public static ArrayList<int[]> depthFirstSearchArray(int[] initialState) {
		ArrayList<int[]> solution = Board.depthFirstSearchArrayAlt(initialState);
		if(solution == null)
			throw new NoSuchElementException("Aucune solution trouvée");
		return solution;
	}
	
	public static ArrayList<int[]> depthFirstSearchArrayAlt(int[] currentState){
		ArrayList<int[]> solution = null;
		if(Board.isSolutionArray(currentState)){
			solution = new ArrayList<int[]>();
			solution.add(currentState);
			return solution;
		}
		ArrayList<int[]> successors = Board.getArraySuccessors(currentState);
		for(int[] succ: successors){
			solution = Board.depthFirstSearchArrayAlt(succ);
			if(solution != null){
				solution.add(currentState);
				return solution;
			}
		}
		return solution;
	}
	
	public static String solutionSteps(int[] array){
		String s = "";
		ArrayList<int[]> list = Board.depthFirstSearchArray(array);
		Collections.reverse(list);
		for(int[] succ : list){
			s+="[ ";
			for (int i = 0 ; i < succ.length ; i++)
				s+=succ[i]+" ";
			s+="]\n ----------------------- \n";
		}
		return s;
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
