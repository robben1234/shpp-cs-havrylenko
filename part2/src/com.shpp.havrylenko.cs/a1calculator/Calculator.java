package com.shpp.havrylenko.cs.a1calculator;

 /*
 * Calculator   6/17/16, 00:08
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Calculator {

    static List<String> legalFunctions = Arrays.asList("sqrt(", "sin(", "cos(");
    static String inputErrorMessage = "Illegal Input! Restart app and try harder next time!";

    //    public static double calculate(String formula, HashMap<String, Double> variables) {
    //        127+a+34+sin(a)+4545-(sqrt(a)*2)
    //    }
    //
    private static String injectVars(String formula, HashMap<String, Double> vars) {

        if(vars == null)
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

    private static String[] injectFunctions(String formula) {
        return injectFunctions(formula.split("\\s"));
    }

    private static String[] injectFunctions(String[] formula) {

        List<String> formulaList = Arrays.asList(formula);
        int funcCallIdx = 0;
        for (int i = 0; i < formulaList.size(); i++) {
            if(legalFunctions.contains(formulaList.get(i))) {
                String function = formulaList.get(i);
                int openParIdx = funcCallIdx = i;
                String funcArgs = "";
                while (!formulaList.get(i).equals(")")) {
                    if (ShuntingYard.isNumber(formulaList.get(i)) || ShuntingYard.legalOps.contains(formulaList.get(i)))
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

    private static Double doMath(String function, Double arg) {
        switch (function) {
            case "sqrt(":
                return Math.sqrt(arg);
            case "sin(":
                return Math.sin(arg);
            case "cos(":
                return Math.cos(arg);
            default:
                return null;
        }
    }

    public static Double calculate(String formula, HashMap<String, Double> variables) {
        formula = injectVars(formula, variables);
        formula = validateFormula(formula);
        System.out.println("Validated version: " + formula);
        return PolishNotationCalculator.calculate(ShuntingYard.toPostfix(injectFunctions(formula)));
    }

    private static String validateFormula(String formula) {

        StringBuilder validatedFormula = new StringBuilder();
        StringBuilder moreThanOneCharDigit = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {

            if(ShuntingYard.isNumber("" + formula.charAt(i))) {
                int digitIdx = i;
                while(ShuntingYard.isNumber("" + formula.charAt(digitIdx))) {
                    moreThanOneCharDigit.append(formula.charAt(digitIdx++));
                    if(formula.length() <= digitIdx)
                        break;
                }
                validatedFormula.append(moreThanOneCharDigit).append(" ");
                i += moreThanOneCharDigit.length() - 1;
                moreThanOneCharDigit = new StringBuilder();
            } else if(ShuntingYard.legalOps.contains("" + formula.charAt(i))) {
                validatedFormula.append(formula.charAt(i)).append(" ");

            } else {
                for(String key : legalFunctions) {
                    if(key.startsWith("" + formula.charAt(i))) {
                        String maybe = "";
                        for (int j = 0; j <= 3; j++) {
                            if(formula.length() <= i + j)
                                throw new IllegalArgumentException(inputErrorMessage);
                            maybe = maybe + formula.charAt(i + j);
                        }
                        if(key.startsWith(maybe)) {
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, Double> vars = new HashMap<>();
        String formula = " 123 + sqrt( a + 123 ) + a";;

        System.out.println("ENTER VARIABLES. By format: <name> <value>\n:next to next stage of input:");
        while(in.hasNext()) {
            if(in.hasNextLine()) {
                String[] line = in.nextLine().split("\\s");
                String name = line[0];
                Double value;
                if(name.equals(":next"))
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
        System.out.println("Enter your formula:");
        if(in.hasNextLine())
            formula = in.nextLine();

        //TODO: validate and prepare formula



//        HashMap<String, Double> map = new HashMap<>();
//        map.put("a", 999.);
        double res = calculate(formula, vars);
        System.out.println(res);
    }
}
