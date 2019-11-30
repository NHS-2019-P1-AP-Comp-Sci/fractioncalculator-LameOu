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
    	String firstAnswer = parse(firstFrac);
    	String secondAnswer = parse(secondFrac);
    	String answer = solve(firstAnswer, secondAnswer, operator);
    	return answer;
    }
    	
    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static String parse(String fraction) {
    	boolean wholeBool = false;
    	boolean fracBool = false;
    	boolean negative = false;
    	// This boolean determines if the whole is negative and whether
    	// to distribute that negative into the numerator
    	String whole = "0";
    	int firstEnd = 0;
    	String numerator = "0";
    	int secondEnd = 0;
    	String denominator = "1";
    	for (int i = 0; i < fraction.length(); i++) {
    		if (fraction.charAt(i) == '_') {
    			// Looks for underscore and determines whole number
    			whole = fraction.substring(0, i);
    			firstEnd = i + 1;
    			wholeBool = true;
    			if (whole.charAt(0) == '-' ) {
    				negative = true;
    			}
    		} else if (fraction.charAt(i) == '/') {
    			// Looks for forward slash and determines numerator and denominator
    			numerator = fraction.substring(firstEnd, i);
    			if (negative == true) {
    				numerator = "-" + numerator;
    			}
    			secondEnd = i + 1;
    			denominator = fraction.substring(secondEnd, fraction.length());
    			fracBool = true;
    		}
    	}
    	// Determines if the input is just a whole number or a fraction
    	if (wholeBool == false && fracBool == false) {
    		whole = fraction;
    	}
    	String newFrac = "";
    	int newNumerator = Integer.parseInt(whole) * Integer.parseInt(denominator) + 
    					   Integer.parseInt(numerator);
    	int newDenominator = Integer.parseInt(denominator);
    	newFrac = newNumerator + "/" + newDenominator;
    	return newFrac;
    }
    
    public static String solve(String a, String b, String operator) {
    	String newFrac = "";
    	int firstNum = 0;
    	int firstDen = 0;
    	int secondNum = 0;
    	int secondDen = 0;
    	for (int i = 0; i < a.length(); i++) {
    		if (a.charAt(i) == '/') {
    			firstNum = Integer.parseInt(a.substring(0, i));
    			firstDen = Integer.parseInt(a.substring(i + 1, a.length()));
    		}
    	}
    	for (int i = 0; i < b.length(); i++) {
    		if (b.charAt(i) == '/') {
    			secondNum = Integer.parseInt(b.substring(0, i));
    			secondDen = Integer.parseInt(b.substring(i + 1, b.length()));
    		}
    	}
    	int numerator = 0;
    	int denominator = firstDen * secondDen;
    	if (operator.equals("+")) {
    		numerator = firstNum * secondDen + secondNum * firstDen;
    	} else if (operator.equals("-")) {
    		numerator = firstNum * secondDen - secondNum * firstDen;
    	} else if (operator.equals("*")) {
    		numerator = firstNum * secondNum;
    	} else if (operator.equals("/")) {
    		denominator = firstDen * secondNum;
    		numerator = firstNum * secondDen;
    	}
    	newFrac = numerator + "/" + denominator;
    	return newFrac;
    }
}
