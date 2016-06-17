package com.shpp.havrylenko.cs.a1calculator;

import java.util.Stack;

import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.inputErrorMessage;
import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.legalOps;

/**
 * Transforms infix notation expression into postfix notation
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class ShuntingYard {

    /**
     * Gets precendence of operator
     * @param op String operator
     * @return int Precendence
     */
    private static int getPrec(String op) {
        switch (op) {
            case IOperators.PLUS:
            case IOperators.MINUS:
                return 1;
            case IOperators.MULT:
            case IOperators.DIVIDE:
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Checks whether String is number
     * @param token String to check
     * @return boolean result
     */
    static boolean isNumber(String token) {
        try {
            Double d = Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Transforms infix notation expression into postfix notation
     * @param input String[] formula infix
     * @return String result postfix
     */
    static String toPostfix(String[] input) {

        String output = "";
        Stack<String> operators = new Stack<>();


        for (String token : input) {

            if (isNumber(token)) {
                output = output.concat(token) + " ";
            }

            if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output = output.concat(operators.pop() + " ");
                }
                if (operators.isEmpty())
                    return inputErrorMessage;
                if (operators.peek().equals("("))
                    operators.pop();
            } else if (legalOps.contains(token)) {
                while (!operators.isEmpty() && getPrec(token) <= getPrec(operators.peek())) {
                    output = output.concat(operators.pop() + " ");
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            String token = operators.peek();
            if (token.equals("(") || token.equals(")"))
                return inputErrorMessage;
            output = output.concat(operators.pop());
        }

        return output;
    }

}
