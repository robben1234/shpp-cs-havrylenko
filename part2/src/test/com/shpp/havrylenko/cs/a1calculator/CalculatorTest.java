//package test.com.shpp.havrylenko.cs.a1calculator;
//
///*
// * CalculatorTest   6/17/16, 02:17
// *
// * By Kyrylo Havrylenko
// *
// */
//
//import com.shpp.havrylenko.cs.a1calculator.Calculator;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.HashMap;
//
///**
// * <what class do>
// *
// * @author Kyrylo Havrylenko
// * @see
// */
//public class CalculatorTest {
//
//    @Test
//    public void shouldReturn10() {
//        String formula = "5 + 5";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(10., result, 0.01);
//    }
//
//    @Test
//    public void shouldReturn5() {
//        String formula = "       1 + 2 + ( 1 +   1) + 1";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(6., result, 0.1);
//    }
//
//    @Test
//    public void testFunctions() {
//        String formula = "1 + sin( 2 ) + cos( 3 ) + sqrt( 50 ) + 2";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(1 + Math.sin(2) + Math.cos(3) + Math.sqrt(50) + 2, result, 0.1);
//    }
//
//    @Test
//    public void testSqrt() {
//        String formula = "1 + sqrt( 50 ) + 2";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(1 + Math.sin(2) + Math.cos(3) + Math.sqrt(50) + 2, result, 0.1);
//    }
//
//    @Test
//    public void spaceValidation() {
//        String formula = "(3+2)-           1 +          1       + 2         - (      3 + 2)";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals((3 + 2) - 1 + 1 + 2 - (3 + 2), result, 0.1);
//    }
//
//    @Test
//    public void spaceValidation2() {
//        String formula = "        1 +          2       + 2         - (      3 + 2 )";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(0, result, 0.1);
//    }
//
//    @Test
//    public void minusTest() {
//        String formula = "500 - 600";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(-100, result, 0.1);
//    }
//
//    @Test
//    public void minusTest2() {
//        String formula = "10 - 20";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(-10, result, 0.1);
//    }
//
//    @Test
//    public void paranthesTest() {
//        String formula = "500 - ( 600 - ( 300 + 20 - ( 2 * 3 ) ) )";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(500 - (600 - (300 + 20 - (2 * 3))), result, 0.1);
//    }
//
//    @Test
//    public void multiplicationTest() {
//        String formula = "2 * 2";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(4, result, 0.1);
//    }
//
//    @Test
//    public void multiplicationZero() {
//        String formula = "2 * 0";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(0, result, 0.1);
//    }
//
//    @Test
//    public void divisionTest() {
//        String formula = "4 / 2";
//        double result = Calculator.calculate(formula, null);
//        Assert.assertEquals(2, result, 0.1);
//    }
//
//    @Test
//    public void divisionByZero() {
//        String formula = "4 / 0";
//        double result = Calculator.calculate(formula, null);
//        System.out.println(result);
//    }
//
//    @Test
//    public void grandTest() {
//        String formula = "23 * a / 7 + ( 9 * 2)";
//        HashMap<String, Double> vars = new HashMap<>();
//        vars.put("a", 123.);
//        double result = Calculator.calculate(formula, vars);
//        Assert.assertEquals(23 * 123 / 7 + ( 9 * 2), result, 0.2);
//    }
//
//    @Test
//    public void varTest() {
//        String formula = "2 * a";
//        HashMap<String, Double> vars = new HashMap<>();
//        vars.put("a", 10.);
//        double result = Calculator.calculate(formula, vars);
//        Assert.assertEquals(2 * 10., result, 0.1);
//    }
//
//}