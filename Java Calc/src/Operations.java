/*Author: Ioannis Gylaris
 *Date: 11/02/2018	
 *Purpose: Choose the operation and perform the calculation		 
 */

public class Operations {
	
	public int findOper(int sum,int dedct,int div,int mult,int pow,int perc,int sqR,int log2,int log10,int ln) { //determines which operation will be selected
		
		if (sum!=-1 || dedct!=-1 || div!=-1 || mult!=-1 || pow!=-1 || perc!=-1 || sqR!=-1 || log2!=-1 || log10!=-1 || ln!=-1) { //if at least one operation was detected
			
			int[] tmp = { sum, dedct, div, mult,pow,perc,sqR,log2,log10,ln }; //store all indexes of where each operation was found in the input
			int min=0;
			int j=0;
			
			for(int i=j;j<tmp.length;j++) {
				if(tmp[i]!=-1) { //does the specific operation exist in the user input?
					min = tmp[i]; //if yes, set its index in that input as the minimum 
					j=i;         //and save its place in the "tmp" array
					break;
				}
			}
			for (int i=j+1; i < tmp.length; i++) { //start looking at the other operations which were not checked in the previous loop
				if (tmp[i] < min && tmp[i]!=-1) { //does the operation exist in the user input and does it have a smaller index in it than the current minimum?
					min = tmp[i];
				}
			}
			return min; //return the index of the first valid operation that the user typed in as input
			
		}else {
			return -1;
		}
	}
	
	public double findRes(double[]a,int oper) { //Calculates the result
		double result;
		switch(oper) {
		    case 0: result=a[0]+a[1]; //'+'
		            break;
		    case 1: result=a[0]-a[1]; //'-'
		            break;
		    case 2: result=a[0]/a[1]; //'/'
		            break;
		    case 3: result=a[0]*a[1]; //'*'
		            break;
		    case 4: result=Math.pow(a[0],a[1]); //'^'
		            break;
		    case 5: result=(a[0]*100)/a[1]; //'%'
		            break;
		    case 6: result=Math.sqrt(a[0]); //'S'
		            break;
		    case 7: result=Math.log(a[0])/Math.log(2); //'L'
		            break;
		    case 8: result=Math.log10(a[0]); //'l'
		            break;
		    case 9: result=Math.log(a[0]); //'e'
		            break;
		    default: System.out.println("Something went wrong");
		             return -1;
		}
		return result;
	}
}
