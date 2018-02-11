/*Author: Ioannis Gylaris
 *Date: 11/02/2018	
 *Purpose: main		 
 */

public class Exec {

	public static void main(String[] args) {
		MainCalc trial = new MainCalc();
		int operator = trial.chooseOperator();
        trial.chooseOperands(operator,false,0,0);
	}

}
