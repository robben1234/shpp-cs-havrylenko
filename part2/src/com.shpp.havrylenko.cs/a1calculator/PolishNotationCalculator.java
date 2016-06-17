package com.shpp.havrylenko.cs.a1calculator;

 /*
 * PolishNotationCalculator   6/16/16, 22:23
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Stack;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class PolishNotationCalculator {

    public static Double calculate(String exp) {
        return calculate(exp.split("\\s"));
    }

    public static Double calculate(String[] expression) {

        Stack<String> stack = new Stack<>();

        for (String token : expression) {
            if (ShuntingYard.isNumber(token)) {
                stack.push(token);
            } else if (ShuntingYard.legalOps.contains(token)) {
                double first = 0.;
                double second = 0.;
                switch (token) {
                    case "+":
                        first = Double.parseDouble(stack.pop());
                        second = Double.parseDouble(stack.pop());
                        stack.push("" + (first + second));
                        break;
                    case "-":
                        first = Double.parseDouble(stack.pop());
                        second = Double.parseDouble(stack.pop());
                        stack.push("" + (second - first));
                        break;
                    case "*":
                        first = Double.parseDouble(stack.pop());
                        second = Double.parseDouble(stack.pop());
                        stack.push("" + (first * second));
                        break;
                    case "/":
                        first = Double.parseDouble(stack.pop());
                        if (Double.parseDouble(stack.peek()) != 0.) {
                            second = Double.parseDouble(stack.pop());
                        } else {
                            throw new ArithmeticException("/ by zero");
                        }
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
