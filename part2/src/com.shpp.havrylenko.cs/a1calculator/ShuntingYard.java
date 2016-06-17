package com.shpp.havrylenko.cs.a1calculator;

 /*
 * ShuntingYard   6/16/16, 20:36
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Stack;

import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.inputErrorMessage;
import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.legalOps;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class ShuntingYard {

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

    static boolean isNumber(String token) {
        try {
            Double d = Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

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
