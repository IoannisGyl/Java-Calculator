/*Author: Ioannis Gylaris
 *Date: 11/02/2018	
 *Purpose: Calculator testing		 
 */

import static org.junit.Assert.*;
import org.junit.Test;



public class ResultTesting {
	
	private MainCalc calcUnderTest = new MainCalc();
	private double[] testOperands = {-1500,-234,-23,-14,-7,-1.5,-1,-0.5,0,0.5,1,7,14,23,234,1500};
	
	
	@Test
	public void canItSum() {

		for(int i=0;i<testOperands.length-1;i++) {
			double expected = testOperands[i]+testOperands[i+1];
			assertEquals(expected,calcUnderTest.chooseOperands(0,true,testOperands[i],testOperands[i+1]),0.0);
		}
		
	}
	
	@Test
	public void canItDedct() {
		
		for(int i=0;i<testOperands.length-1;i++) {
			double expected = testOperands[i]-testOperands[i+1];
			assertEquals(expected,calcUnderTest.chooseOperands(1,true,testOperands[i],testOperands[i+1]),0.0);
		}
	}
	
	@Test
	public void canItDiv() {
		
		for(int i=0;i<testOperands.length-1;i++) {
			
			if(testOperands[i+1]!=0) {
				double expected = testOperands[i]/testOperands[i+1];
				assertEquals(expected,calcUnderTest.chooseOperands(2, true, testOperands[i], testOperands[i+1]),0.0);
			}else { //cannot divide with 0 so make sure the expected error value is returned
				double expected = 0;
				assertEquals(expected,calcUnderTest.chooseOperands(2, true, testOperands[i], testOperands[i+1]),0.0);
			}
		}
	}
	
	@Test
	public void canItMult() {
		
		for(int i=0;i<testOperands.length-1;i++) {
			double expected = testOperands[i]*testOperands[i+1];
			assertEquals(expected,calcUnderTest.chooseOperands(3, true, testOperands[i], testOperands[i+1]),0.0);
		}
	}
	
	@Test
	public void canItCalcThePowerOf() {
		
		for(int i=0;i<testOperands.length-1;i++) {
			double expected = Math.pow(testOperands[i],testOperands[i+1]);
			assertEquals(expected,calcUnderTest.chooseOperands(4, true, testOperands[i], testOperands[i+1]),0.0);
		}
	}
	
	@Test
	public void canItCalcThePercentageOf() {
		
		for(int i=0;i<testOperands.length-1;i++) {
			if (testOperands[i+1]!=0) {
				double expected = (testOperands[i] * 100) / testOperands[i + 1];
				assertEquals(expected, calcUnderTest.chooseOperands(5, true, testOperands[i], testOperands[i + 1]),
						0.0);
			}else { //cannot divide with 0 so make sure the expected error value is returned
				double expected = 0;
				assertEquals(expected, calcUnderTest.chooseOperands(5, true, testOperands[i], testOperands[i + 1]),
						0.0);
			}
		}
	}
	
	@Test
	public void canItCalcTheSQRoot() {
		
		for(int i=0;i<testOperands.length;i++) {
			if (testOperands[i]>=0) {
				double expected = Math.sqrt(testOperands[i]);
				assertEquals(expected, calcUnderTest.chooseOperands(6, true, testOperands[i],0),0.0);
			}else { //cannot compute the square root of a negative number so make sure the expected error value is returned
				double expected = 0;
				assertEquals(expected, calcUnderTest.chooseOperands(6, true, testOperands[i],0),0.0);
			}
		}
	}
	
	@Test
	public void canItCalcLog2() {
		
		for(int i=0;i<testOperands.length;i++) {
			if (testOperands[i]>0) {
				double expected = Math.log(testOperands[i])/Math.log(2);
				assertEquals(expected, calcUnderTest.chooseOperands(7, true, testOperands[i],0),0.0);
			}else { //cannot compute the logarithm of 0 or a negative number in any base
				double expected = 0;
				assertEquals(expected, calcUnderTest.chooseOperands(7, true, testOperands[i],0),0.0);
			}
		}
	}
	
	@Test
	public void canItCalcLog10() {
		
		for(int i=0;i<testOperands.length;i++) {
			if (testOperands[i]>0) {
				double expected = Math.log10(testOperands[i]);
				assertEquals(expected, calcUnderTest.chooseOperands(8, true, testOperands[i],0),0.0);
			}else {
				double expected = 0;
				assertEquals(expected, calcUnderTest.chooseOperands(8, true, testOperands[i],0),0.0);
			}
		}
	}
	
	@Test
	public void canItCalcLn() {
		
		for(int i=0;i<testOperands.length;i++) {
			if (testOperands[i]>0) {
				double expected = Math.log(testOperands[i]);
				assertEquals(expected, calcUnderTest.chooseOperands(9, true, testOperands[i],0),0.0);
			}else {
				double expected = 0;
				assertEquals(expected, calcUnderTest.chooseOperands(9, true, testOperands[i],0),0.0);
			}
		}
	}

}
