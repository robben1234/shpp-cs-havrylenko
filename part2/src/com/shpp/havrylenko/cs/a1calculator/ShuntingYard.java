package com.shpp.havrylenko.cs.a1calculator;

import com.shpp.havrylenko.cs.a5collections.KStack;

import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.*;

/**
 * Transforms infix notation expression into postfix notation
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class ShuntingYard {



    /**
     * Transforms infix notation expression into postfix notation
     * @param input String[] formula infix
     * @return String result postfix
     */
    static String toPostfix(String[] input) {

        String output = "";
        KStack<String> operators = new KStack<>();


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
