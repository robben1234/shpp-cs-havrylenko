package com.shpp.havrylenko.cs.task6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        for(int i = 0; i < toneMatrix.length; i++) {
            if(toneMatrix[i][column]) {
                for(int j = 0; j < result.length; j++) {
                    result[j] += samples[i][j];
                }
            }
        }

        double maxValue = 0.;
        double minValue = 0.;
        
        for(double aResult : result) {
            if(aResult > 1. || aResult < -1.) {
                if(aResult > maxValue) maxValue = aResult;
                else if(aResult < minValue) minValue = aResult;
            }
        }

        double divider;
        double approxMinMax = maxValue + minValue;

        if(approxMinMax == 0) return result;
        else if(approxMinMax > 0) divider = maxValue;
        else divider = minValue;

        for(int i = 0; i < result.length; i++) {
            result[i] /= divider;
        }

        return result;
    }
}
