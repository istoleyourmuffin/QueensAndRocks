import gameElement.Board;
import gameElement.Eval;
import gameElement.Eval0;
import gameElement.Eval1;
import gameElement.Player;

public class EvalLambda implements Eval {

	float lambda;
	
	public EvalLambda(){
		lambda = (float)0.5;
	}
	
	public float getLambda(){
		return lambda;
	}
	
	public void setLambda(float l){
		lambda = l;
	}
	
	public float getEval(Player player, Board b) {
		Eval0 e0 = new Eval0();
		Eval1 e1 = new Eval1();
		return (lambda*e0.getEval(player, b))+((1-lambda)*e1.getEval(player, b));
	}

}
