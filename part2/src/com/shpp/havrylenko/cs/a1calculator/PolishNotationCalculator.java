package com.shpp.havrylenko.cs.a1calculator;

import com.shpp.havrylenko.cs.a5collections.KStack;

import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.isNumber;
import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.legalOps;

/**
 * Calculates RVP expression
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class PolishNotationCalculator {

    /**
     * Calculates RVP expression
     * @param exp String in postfix notation with math. formula
     * @return Double result
     */
    static Double calculate(String exp) {
        return calculate(exp.split("\\s"));
    }

    /**
     * Calculates RVP expression
     * @param expression String in postfix notation with math. formula
     * @return Double result
     */
    static Double calculate(String[] expression) {

        KStack<String> stack = new KStack<>();

        for (String token : expression) {
            if (isNumber(token)) {
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
