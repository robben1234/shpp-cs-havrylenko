package com.shpp.havrylenko.cs.a4fractals;

/**
 * Class to represent Complex numbers
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class Complex {
    double real;
    double imag;

    /**
     * Constructor by default
     */
    Complex() {
        real = 0.;
        imag = 0.;
    }

    /**
     * Constructor
     * @param real real part
     * @param imag imaginary part
     */
    Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * Calculates absolute value of complex
     * @return double abs value
     */
    double abs() {
        return Math.sqrt(real * real + imag * imag);
    }

    /**
     * Generates double value of complex
     * @return double
     */
    double toDouble() {
        return real + imag;
    }

    /**
     * Multiplicates this on b
     * @param b Complex
     * @return Complex resulting this*b
     */
    Complex mult(Complex b) {
        double real = this.real * b.real - this.imag * b.imag;
        double imag = this.real * b.imag + this.imag * b.real;
        return new Complex(real, imag);
    }

    /**
     * Sums this and b
     * @param b Complex
     * @return Complex = this+b
     */
    Complex plus(Complex b) {
        double real = this.real + b.real;
        double imag = this.imag + b.imag;
        return new Complex(real, imag);
    }
}
