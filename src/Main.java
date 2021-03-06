import java.util.ArrayList;
import java.util.Scanner;

import gameElement.*;
import graphics.GameUI;


public class Main {

	public float optimisation(){
		EvalLambda el = new EvalLambda();
		float lambda = el.getLambda();
		float lambdaPrime = el.getLambda()+(float)0.01;
		float t = 0, t2 = 0;
		while(lambdaPrime-lambda <= 0.1){
			//t = computerAgainstHimself(el);
			el.setLambda(lambdaPrime);
			//t2 = computerAgainstHimself(el);
			if(t < t2){
				lambdaPrime += 0.01;
			}else{
				lambda = lambdaPrime;
				lambdaPrime = lambda + (float)0.01;
			}
			el.setLambda(lambda);
		}
		return lambda;
	}
	
	public void testFonctionsSolo(){
		Board b = new Board(8);
		String s = b.toString();
		Game game = b.getGame();
		int i1 = b.numberOfAccessible();
		System.out.println("Nombre de cases accessibles : " + i1);
		int i2 = b.numberOfQueens();
		System.out.println("Nombre de reines : " + i2);
		long t = System.currentTimeMillis();
		System.out.println(b.solutionSteps(b));
		long t2 = System.currentTimeMillis();
		System.out.println("Temps d'ex�cution : "+(t2 - t));
		int[] array = b.boardToArray();
		System.out.println("\n--------------------Test depthFirstSearchArray--------------------\n");
		long t3 = System.currentTimeMillis();
		System.out.println(b.solutionSteps2(b));
		//System.out.println(Board.solutionSteps(array));
		long t4 = System.currentTimeMillis();
		System.out.println("Temps d'ex�cution : "+(t4 - t3));
	}

	public void testFonctionsDuo() {
		Board b = new Board();
		b.setPiece(3, 4, b.getGame().getQueen0());
		b.setPiece(2, 3, b.getGame().getRock1());
		b.setPiece(4, 5, b.getGame().getRock0());
		b.setPiece(4, 3, b.getGame().getRock1());
		b.setPiece(2, 5, b.getGame().getRock0());
		b.setPiece(3, 2, b.getGame().getRock0());
		b.setPiece(2, 4, b.getGame().getRock1());
		b.setPiece(4, 4, b.getGame().getRock1());
		b.setPiece(3, 5, b.getGame().getRock0());

		System.out.println(b.toStringAccess2(b.getGame().getPlayer1()));
		System.out.println("Nombre de reines du joueur 0: "+b.numberOfQueens2(b.getGame().getPlayer0()));
		System.out.println("Nombre de rochers du joueur 1: "+b.numberOfRocks2(b.getGame().getPlayer1()));
		System.out.println("Score du joueur 0: "+b.getScore(b.getGame().getPlayer0()));

	}


	public void testUI(){
		Board b = new Board(8);
		GameUI g = new GameUI(b,2);
		g.setEvaluation(new Eval1());
		g.launch();
	}


	public void testSolo() {
		Board b = new Board();
		String stringAccess;
		Scanner reader = new Scanner(System.in);
		int i, j;
		while(b.numberOfQueens() < 8 && b.numberOfAccessible() > 0){
			stringAccess = b.toStringAccess();
			System.out.println("Tableau actuel");
			System.out.println(stringAccess);
			System.out.println("Voulez-vous placer votre reine ?\nEn hauteur:");
			i = reader.nextInt();
			System.out.println("En largeur");
			j = reader.nextInt();
			if(!b.placeQueen(i, j))
				System.out.println("La reine ne peut pas �tre plac�e ici");
		}
		stringAccess = b.toStringAccess();
		System.out.println("Tableau actuel");
		System.out.println(stringAccess);
		if(b.numberOfQueens() == 8)
			System.out.println("Gagn� !");
		else 
			System.out.println("Perdu !");
		reader.close();
	}

	public void testDuo() {
		Board b = new Board(4);
		String stringAccess;
		Scanner reader = new Scanner(System.in);
		int i, j, k;
		int tour = 0;
		Player player0 = b.getGame().getPlayer0();
		Player player1 = b.getGame().getPlayer1();
		boolean place0 = false;
		boolean place1 = false;
		
		while((b.getNumberOfRocksLeft(player0) > 0 || b.numberOfAccessible2(player0) > 0)
				&& (b.getNumberOfRocksLeft(player1) > 0 || b.numberOfAccessible2(player1) > 0)){
			System.out.println("Score : Joueur0 - "+b.getScore(player0)+" | Joueur1 - "+b.getScore(player1));
			System.out.println("----------------------------------------");
			System.out.println("Nombre de rochers : Joueur0 - "+b.getNumberOfRocksLeft(player0)+" | Joueur1 - "+b.getNumberOfRocksLeft(player1));
			System.out.println("----------------------------------------");
			if(tour%2 == 0){
				place0 = false;
				while(!place0){
					System.out.println("Tour du Joueur0");
					System.out.println(b.toStringAccess2(player0));
					System.out.println("Reine(0) ou Rocher(1) ?");
					k = reader.nextInt();
					System.out.println("En hauteur:");
					i = reader.nextInt();
					System.out.println("En largeur");
					j = reader.nextInt();
					if(k == 0){
						place0 = b.placeQueen2(i, j, player0);
					}else{
						place0 = b.placeRock2(i, j, player0);
					}
					if(!place0)
						System.out.println("Placement impossible");
				}
			}else{
				place1 = false;
				while(!place1){
					System.out.println("Tour du Joueur1");
					System.out.println(b.toStringAccess2(player1));
					System.out.println("Reine(0) ou Rocher(1) ?");
					k = reader.nextInt();
					System.out.println("En hauteur:");
					i = reader.nextInt();
					System.out.println("En largeur");
					j = reader.nextInt();
					if(k == 0){
						place1 = b.placeQueen2(i, j, player1);
						
					}else{
						place1 = b.placeRock2(i, j, player1);
					}
					if(!place1)
						System.out.println("Placement impossible");
				}
			}
			tour++;
		}
		if(b.getScore(player0) > b.getScore(player1))
			System.out.println("Le joueur 0 gagne la partie");
		else
			System.out.println("Le joueur 1 gagne la partie");
	}
	
	public void testDuoRockFirst() {
		Board b = new Board(4);
		String stringAccess;
		Scanner reader = new Scanner(System.in);
		int i, j, k;
		int tour = 0;
		Player player0 = b.getGame().getPlayer0();
		Player player1 = b.getGame().getPlayer1();
		boolean place0 = false;
		boolean place1 = false;
		
		while((b.getNumberOfRocksLeft(player0) > 0 || b.numberOfAccessible2(player0) > 0)
				&& (b.getNumberOfRocksLeft(player1) > 0 || b.numberOfAccessible2(player1) > 0)){
			System.out.println("Score : Joueur0 - "+b.getScore(player0)+" | Joueur1 - "+b.getScore(player1));
			System.out.println("----------------------------------------");
			System.out.println("Nombre de rochers : Joueur0 - "+b.getNumberOfRocksLeft(player0)+" | Joueur1 - "+b.getNumberOfRocksLeft(player1));
			System.out.println("----------------------------------------");
			if(tour%2 == 0){
				place0 = false;
				while(!place0){
					System.out.println("Tour du Joueur0");
					System.out.println(b.toStringAccess2(player0));

					if(tour == 0){
						System.out.println("Premier tour: placement d'un rocher obligatoire");
						System.out.println("En hauteur:");
						i = reader.nextInt();
						System.out.println("En largeur");
						j = reader.nextInt();
						place0 = b.placeRock2(i, j, player0);
						if(!place0)
							System.out.println("Placement impossible");
					}else{
						System.out.println("Reine(0) ou Rocher(1) ?");
						k = reader.nextInt();
						System.out.println("En hauteur:");
						i = reader.nextInt();
						System.out.println("En largeur");
						j = reader.nextInt();
						if(k == 0){
							place0 = b.placeQueen2(i, j, player0);
						}else{
							place0 = b.placeRock2(i, j, player0);
						}
						if(!place0)
							System.out.println("Placement impossible");
					}
				}
			}else{
				place1 = false;
				while(!place1){
					System.out.println("Tour du Joueur1");
					System.out.println(b.toStringAccess2(player1));
					System.out.println("Reine(0) ou Rocher(1) ?");
					k = reader.nextInt();
					System.out.println("En hauteur:");
					i = reader.nextInt();
					System.out.println("En largeur");
					j = reader.nextInt();
					if(k == 0){
						place1 = b.placeQueen2(i, j, player1);
						
					}else{
						place1 = b.placeRock2(i, j, player1);
					}
					if(!place1)
						System.out.println("Placement impossible");
				}
			}
			tour++;
		}
		if(b.getScore(player0) > b.getScore(player1))
			System.out.println("Le joueur 0 gagne la partie");
		else
			System.out.println("Le joueur 1 gagne la partie");
	}
	
	public float computerAgainstHimself(Eval e, Eval e2){
		long t = System.currentTimeMillis();
		Board b = new Board(4);
		int tour = 0;
		Player player0 = b.getGame().getPlayer0();
		Player player1 = b.getGame().getPlayer1();
		while(!b.isFinal()){
			System.out.println("Score : Joueur0 - "+b.getScore(player0)+" | Joueur1 - "+b.getScore(player1));
			System.out.println("----------------------------------------");
			System.out.println("Nombre de rochers : Joueur0 - "+b.getNumberOfRocksLeft(player0)+" | Joueur1 - "+b.getNumberOfRocksLeft(player1));
			System.out.println("----------------------------------------");
			if(tour%2 == 0){
				System.out.println("Tour du Joueur0");
				System.out.println(b.toStringAccess2(player0));
				b = b.minimax(b, player0, 2, e);
			}else{
				System.out.println("Tour du Joueur1");
				System.out.println(b.toStringAccess2(player1));
				b = b.minimax(b, player1, 2, e2);
			}
			tour++;
			
		}
		if(b.getScore(player0) > b.getScore(player1))
			System.out.println("Le joueur 0 gagne la partie");
		else if(b.getScore(player1) > b.getScore(player0))
			System.out.println("Le joueur 1 gagne la partie");
		else
			System.out.println("Egalit� !");
		System.out.println("Score final : Joueur0 - "+b.getScore(player0)+" | Joueur1 - "+b.getScore(player1));
		long t2 = System.currentTimeMillis();
		System.out.println("Temps d'exécution : "+(t2-t));
		return (float)t2-t;
	}

	
	public void testOptimisation(){
		float lambdaFinal = optimisation();
		System.out.println("Lambda final : "+ lambdaFinal);
	}

	public static void main(String[] args){
		Main m = new Main();
		//m.testSolo();
		//m.testDuoRockFirst();
		//m.testFonctionsSolo();
		//m.testFonctionsDuo();
		//m.testUI();
		//m.testOptimisation();
		m.computerAgainstHimself(new Eval2(), new EvalLambda());
	}

}
