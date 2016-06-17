package com.shpp.havrylenko.cs.a1calculator;

 /*
 * ShuntingYard   6/16/16, 20:36
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class ShuntingYard {

    public static List<String> legalOps = Arrays.asList("+", "-", "*", "/", "(", ")");

    private static int getPrec(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public static boolean isNumber(String token) {
        try {
            Double d = Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String toPostfix(String[] input) {

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
                    return "INVALID INPUT";

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
                return "INVALID INPUT";
            output = output.concat(operators.pop());
        }


        return output;
    }

}
