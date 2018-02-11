/*Author: Ioannis Gylaris
 *Date: 11/02/2018	
 *Purpose: A simple calculator		 
 */


import java.util.Scanner;

public class MainCalc {
	
	private final char[] OPS = {'+','-','/','*','^','%','S','L','l','e'}; // contains all permitted operations
	private Operations op = new Operations();
	private double result=0.0;

	public int chooseOperator() {
		
		do {
			System.out.print("Enter a valid operation character from the selection \n('^'=power |'L'=log2 |'l'=log10 |'S'=square root |'e'=ln |'%' = what percentage of 'b' is 'a' in \"a%b\"): ");
			
			for(int i=0;i<OPS.length;i++) {//prints out all the available operations for the user to choose
				System.out.print(OPS[i]+" ");
			}
			System.out.println("\nOnly the first operator will be considered");
			Scanner inputs = new Scanner(System.in);
			String in = inputs.nextLine();
			
			int sum = in.indexOf(OPS[0]); //keeps track of the indexes for each operation character that the user entered
			int dedct = in.indexOf(OPS[1]);//if a specific operation has not been entered -1 is returned
			int div = in.indexOf(OPS[2]);
			int mult = in.indexOf(OPS[3]);
			int pow = in.indexOf(OPS[4]);
			int perc = in.indexOf(OPS[5]);
			int sqR = in.indexOf(OPS[6]);
			int log2 = in.indexOf(OPS[7]);
			int log10 = in.indexOf(OPS[8]);
			int ln = in.indexOf(OPS[9]);
			int action = op.findOper(sum,dedct,div,mult,pow,perc,sqR,log2,log10,ln); //returns the smallest index to determine which was typed first
        
			if(action!=-1) { //if at least one operation character was entered determine which one and return its index in the OPS array
				if(action == sum) {
					return 0;
				}else if(action == dedct) {
					return 1;
				}else if(action == div) {
					return 2;
				}else if(action == mult){
					return 3;
				}else if(action == pow) {
					return 4;
				}else if(action == perc) {
					return 5;
				}else if(action == sqR) {
					return 6;
				}else if(action == log2) {
					return 7;
				}else if(action == log10){
					return 8;
				}else{
					return 9;
				}
			}else {
				System.out.println("No valid operator found please try again");
				continue;
			}
			
		} while(true); //repeat until an operation has been chosen
		
	}
    
	public double chooseOperands(int oper,boolean test,double testOp1,double testOp2) { //the "test" parameter is only true when a JUnit test is run
		
		boolean flag=false; //is set to true when the user wants to perform an additional calculation
		int operator = oper; //the index of the operation character selected in the OPS array
		
		do {
			
			int oprnds=0; //number of current operands selected
			double[] data= {0,0}; //array containing those specific operands  
			if(flag) {
				operator = chooseOperator(); //if the user wants to perform another calculation choose a new operation before choosing new operands
				if(keepRes()) { //does the user want to use the previous result as a new operand?
					data[0]=result;
					oprnds++;
				}
			}
			
			if (!test) { //if this is a test skip the user inputs and use the given operator and operands
				System.out.println(
						"Enter one or two numbers on which you want to perform an operation (maximum 2 operands per calculation are allowed): ");
				System.out.println(
						"Only the first/first two operands will be considered and they will be used in the order given (based on the operator you selected)");
				while (true) {
					Scanner inputs = new Scanner(System.in);
					if (oprnds == 2 && operator <= 5) { //if an operator which requires 2 operands has been selected stop at 2 operands
						break;
					} else if (oprnds == 1 && operator > 5) { //if an operator which requires 1 operand has been selected stop at 1 operand
						break;
					}
					if (inputs.hasNextInt()) {
						data[oprnds] = inputs.nextInt(); //store operands in the array
						oprnds++;
					}
				} 
			}else {
				data[0] = testOp1;
				data[1] = testOp2;
			}
			// These if statements ensure that all operation specific constraints are considered
			if(oprnds==0 && !test) { //if this is a test run the variable "oprnds" will be 0 by default
				System.out.println("No numbers were found, please try again");
				continue;
			}else if((operator==2 || operator==5) && data[1]==0) {
				System.out.println("Cannot use zero for this calculation, please try to input a different number");
				if (!test) { //if this is a test return 0, this value is used instead of -1 as the program exiting in this case is considered the correct action
					continue;
				}else {
					return 0;
				}
			}else if(operator==6 && data[0]<0) {
				System.out.println("Cannot find the square root of a negative number, please try again");
				if (!test) {
					continue;
				}else {
					return 0;
				}
			}else if(operator>6 && data[0]<=0) {
				System.out.println("A positive number is needed to complete the calculation, please try again");
				if (!test) {
					continue;
				}else {
					return 0;
				}
			}
			
			result = op.findRes(data,operator);//performs the calculation and returns the result
			if (!test) {//only execute if this is not a test
				if (oprnds == 2) { //if 2 operands were used print out the appropriately formatted answer
					System.out.println(data[0] + " " + OPS[operator] + " " + data[1] + " = " + result);
				} else {
					System.out.println(OPS[operator] + " " + data[0] + " = " + String.format("%.3f", result));
				} 
				flag = calcAgain(); //another calculation?
			}
			if(!flag) { // if not end the program
			   return result;
		    }
			
	    } while(true); //repeat until the user chooses to stop the calculations or correct operands have been entered
		
	}
	
	private boolean calcAgain() {
		System.out.println("Would you like to perform another calculation? Yes/No (must be written in the exact same way otherwise \"No\" will be assumed)");
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			if(in.next().contains("Yes")) { //does the first token contain the answer "Yes"?
				return true;
			}else{
				break;
			}
		}
		return false;
	}
	
	private boolean keepRes() {
		System.out.println("Would you also like to use the previous result into the new calculation?"
				+ "Yes/No (must be written in the exact same way otherwise \"No\" will be assumed)");
		Scanner keepOld = new Scanner(System.in);
		while(keepOld.hasNext()) {
			if(keepOld.next().contains("Yes")) {//does the first token contain the answer "Yes"?
			   return true;
			}else {
				break;
			}
		}
		return false;
	}
	
}
