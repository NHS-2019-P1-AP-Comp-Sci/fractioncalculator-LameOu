/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner(System.in);
    	int num = 1;
    	while (num > 0) {
    		String input = userInput.nextLine();
    		if (input.equals("quit")) {
    			num = 0;
    			break;
    		}
    		String answer = produceAnswer(input);
    		System.out.println(answer);
    		System.out.println();
    	}
    	userInput.close();
    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input) {
    	// TODO: Implement this function to produce the solution to the input
    	String firstFrac = "";
    	int firstEnd = 0;
    	String secondFrac = "";
    	int secondEnd = 0;
    	String operator = "";
    	int counter = 1;
    	for (int i = 0; i < input.length(); i++) {
    		if (input.charAt(i) == ' ' && counter == 1) {
    			// This finds the first fraction by looking for the first space
    			// Then it takes everything before the first space and makes it into a fraction
    			firstFrac = input.substring(0, i);
    			firstEnd = i + 1;
    			counter += 1;
    		} else if (input.charAt(i) == ' ' && counter == 2) {
    			// This finds the operator by looking for the first and second space
    			// Then it takes everything between the spaces and determines the operation
    			operator = input.substring(firstEnd, i);
    			secondEnd = i + 1;
    			counter += 1;
    		} else if (counter == 3) {
    			// This finds the second fraction by looking for the second space
    			// Then it takes everything after the second space and makes it into a fraction
    			secondFrac = input.substring(secondEnd, input.length());
    		}
    	}
    	String firstFracParse = parse(firstFrac);
    	String secondFracParse = parse(secondFrac);
    	return secondFracParse;
    }
    	
    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static String parse(String fraction) {
    	String whole = "";
    	int firstEnd = 0;
    	String numerator = "";
    	int secondEnd = 0;
    	String denominator = "";
    	for (int i = 0; i < fraction.length(); i++) {
    		if (fraction.charAt(i) == '_') {
    			whole = fraction.substring(0, i);
    			firstEnd = i + 1;
    		} else if (fraction.charAt(i) == '/') {
    			numerator = fraction.substring(firstEnd, i);
    			secondEnd = i + 1;
    			denominator = fraction.substring(secondEnd, fraction.length());
    		}
    	}
    	String values = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
    	return values;
    }
	
}
