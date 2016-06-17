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


}
