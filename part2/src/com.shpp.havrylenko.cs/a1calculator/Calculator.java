package com.shpp.havrylenko.cs.a1calculator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.shpp.havrylenko.cs.a1calculator.UtilCalc.*;

/**
 * Models work of calculator via RVP
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Calculator {

    /**
     * Injects input variables to the formula
     * @param formula String your formula to inject vars
     * @param vars Map<String, Double> with variables
     * @return
     */
    private static String injectVars(String formula, HashMap<String, Double> vars) {

        if (vars == null)
            return formula;

        String[] formulaArray = formula.split("\\s");
        String result = "";

        for (String aFormulaArray : formulaArray) {
            if (vars.keySet().contains(aFormulaArray)) {
                result += vars.get(aFormulaArray) + " ";
            } else {
                result += aFormulaArray + " ";
            }
        }
        return result;
    }

    /**
     * Polymorf version of {@code injectFunctions}
     * @param formula String to inject functions
     * @return String[] formula with injected function calls results
     */
    private static String[] injectFunctions(String formula) {
        return injectFunctions(formula.split("\\s"));
    }

    /**
     * Injects functions calls results into the formula
     * @param formula String to inject functions
     * @return String[] formula with injected function calls results
     */
    private static String[] injectFunctions(String[] formula) {

        List<String> formulaList = Arrays.asList(formula);
        int funcCallIdx = 0;
        for (int i = 0; i < formulaList.size(); i++) {
            if (legalFunctions.contains(formulaList.get(i))) {
                String function = formulaList.get(i);
                int openParIdx = funcCallIdx = i;
                String funcArgs = "";
                while (!formulaList.get(i).equals(")")) {
                    if (ShuntingYard.isNumber(formulaList.get(i)) || legalOps.contains(formulaList.get(i)))
                        funcArgs += formulaList.get(i) + " ";
                    i++;
                }
                int closeParIdx = i;
                double arg = PolishNotationCalculator.calculate(ShuntingYard.toPostfix(funcArgs.split("\\s"))
                                                                            .split("\\s"));
                arg = doMath(function, arg);
                formulaList.set(openParIdx++, "" + arg);
                formulaList = Stream
                        .concat(formulaList
                                        .subList(0, openParIdx)
                                        .stream(),
                                formulaList
                                        .subList(++closeParIdx, formulaList.size())
                                        .stream())
                        .collect(Collectors.toList());
                i = ++funcCallIdx;
            }
        }

        String result = "";
        for (String token : formulaList) {
            result += token + " ";
        }

        return result.trim().split("\\s");
    }

    /**
     * Calculates result of function call
     * @param function String what function to call
     * @param arg Argument to function call
     * @return Double result
     */
    private static Double doMath(String function, Double arg) {
        switch (function) {
            case IFunctions.SQRT:
                return Math.sqrt(arg);
            case IFunctions.SIN:
                return Math.sin(arg);
            case IFunctions.COS:
                return Math.cos(arg);
            default:
                return null;
        }
    }

    /**
     * Calculates result of your math formula; public API
     * @param formula String formula with mathematic expression
     * @param variables HashMap with variables used in formula
     * @return Double result of calculation
     */
    public static Double calculate(String formula, HashMap<String, Double> variables) {
        formula = injectVars(formula, variables);
        formula = validateFormula(formula);
        return PolishNotationCalculator.calculate(ShuntingYard.toPostfix(injectFunctions(formula)));
    }

    /**
     * Validates input
     * @param formula String user input
     * @return String validated formula
     */
    private static String validateFormula(String formula) throws IllegalArgumentException {

        StringBuilder validatedFormula = new StringBuilder();
        StringBuilder moreThanOneCharDigit = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {

            if (ShuntingYard.isNumber("" + formula.charAt(i))) {
                int digitIdx = i;
                boolean dotted = false;
                while (ShuntingYard.isNumber("" + formula.charAt(digitIdx)) || ".".equals("" + formula.charAt(digitIdx))) {
                    if(".".equals("" + formula.charAt(digitIdx))) {
                        if(!dotted)
                            dotted = true;
                        else
                            throw new IllegalArgumentException(inputErrorMessage);
                    }

                    moreThanOneCharDigit.append(formula.charAt(digitIdx++));
                    if (formula.length() <= digitIdx)
                        break;
                }
                validatedFormula.append(moreThanOneCharDigit).append(" ");
                i += moreThanOneCharDigit.length() - 1;
                moreThanOneCharDigit = new StringBuilder();
            } else if (legalOps.contains("" + formula.charAt(i))) {
                validatedFormula.append(formula.charAt(i)).append(" ");
            } else {
                for (String key : legalFunctions) {
                    if (key.startsWith("" + formula.charAt(i))) {
                        String maybe = "";
                        for (int j = 0; j <= 3; j++) {
                            if (formula.length() <= i + j)
                                throw new IllegalArgumentException(inputErrorMessage);
                            maybe = maybe + formula.charAt(i + j);
                        }
                        if (key.startsWith(maybe)) {
                            i = i + 4;
                            String spaces = "";
                            for (int j = 0; j < 6 - key.length(); j++) {
                                spaces += " ";
                            }
                            validatedFormula.append(key).append(spaces);
                        }
                    }
                }
            }
        }
        return validatedFormula.toString();
    }

    /**
     * CLI interface of program
     * @param args args
     */
    public static void main(String[] args) {

        // TODO: --h


        if(args.length > 0) {
            System.out.println("USAGE: java Calculator");
            System.out.println("SUPPORTED OPERATORS: + - * /");
            System.out.println("SUPPORTED FUNCTIONS: sqrt() sin() cos()");
            System.out.println("WE ALSO SUPPORT VARIABLES. THEY SHOULD BE SEPARATED WITH WHITESPACES FROM EVERYTHING");
            System.out.println("INPUT FORMAT:");
            System.out.println("\tVARIABLES:\t<name> <value>\n\t\t:next to enter next stage of input");
            System.out.println("\tFORMULA:  1 + 2 + 3 + ( 4 + 5 )");
            return;
        }

        Scanner in = new Scanner(System.in);
        HashMap<String, Double> vars = new HashMap<>();
        String formula = "";

        System.out.println("Enter variables. By format: <name> <value>\n:next to next stage of input:");
        while (in.hasNext()) {
            if (in.hasNextLine()) {
                String[] line = in.nextLine().split("\\s");
                String name = line[0];
                Double value;
                if (name.equals(":next"))
                    break;
                try {
                    value = Double.parseDouble(line[1]);
                } catch (NumberFormatException e) {
                    System.err.println(inputErrorMessage);
                    return;
                }
                vars.put(name, value);
            }
        }
        System.out.println("Enter your formula.\tDON'T FORGET VAR USAGE FORMAT: <WHITESPACE><var name><WHITESPACE>");
        if (in.hasNextLine())
            formula = in.nextLine();

        double res = calculate(formula, vars);
        System.out.println(res);
    }
}
