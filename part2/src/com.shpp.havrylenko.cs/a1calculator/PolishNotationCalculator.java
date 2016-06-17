package com.shpp.havrylenko.cs.a1calculator;

 /*
 * PolishNotationCalculator   6/16/16, 22:23
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Stack;

import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.legalOps;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class PolishNotationCalculator {

    static Double calculate(String exp) {
        return calculate(exp.split("\\s"));
    }

    static Double calculate(String[] expression) {

        Stack<String> stack = new Stack<>();

        for (String token : expression) {
            if (ShuntingYard.isNumber(token)) {
                stack.push(token);
            } else if (legalOps.contains(token)) {
                double first = Double.parseDouble(stack.pop());
                double second = Double.parseDouble(stack.pop());
                switch (token) {
                    case IOperators.PLUS:
                        stack.push("" + (first + second));
                        break;
                    case IOperators.MINUS:
                        stack.push("" + (second - first));
                        break;
                    case IOperators.MULT:
                        stack.push("" + (first * second));
                        break;
                    case IOperators.DIVIDE:
                        stack.push("" + (second / first));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator");
                }
            }
        }
        return Double.parseDouble(stack.pop());
    }

}
