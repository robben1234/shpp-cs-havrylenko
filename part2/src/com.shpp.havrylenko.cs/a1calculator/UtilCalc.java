package com.shpp.havrylenko.cs.a1calculator;

import java.util.Arrays;
import java.util.List;

/**
 * Util class with constants
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class UtilCalc {
    static List<String> legalFunctions = Arrays.asList("sqrt(", "sin(", "cos(");
    static String inputErrorMessage = "Illegal Input! Restart app and try harder next time!";
    static List<String> legalOps = Arrays.asList(IOperators.PLUS,
                                                 IOperators.MINUS,
                                                 IOperators.MULT,
                                                 IOperators.DIVIDE,
                                                 IOperators.OPEN_P,
                                                 IOperators.CLOSE_P);


    /**
     * Gets precendence of operator
     * @param op String operator
     * @return int Precendence
     */
    static int getPrec(String op) {
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
}
