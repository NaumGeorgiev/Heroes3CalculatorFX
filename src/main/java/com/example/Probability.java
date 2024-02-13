package com.example;

import java.util.Arrays;

public class Probability {
    public static double getChance(double minDamage, double maxDamage, int target, int creatureCount, double multiplier) {
        double[] damageValues = getDamageValues(minDamage, maxDamage);
        double mean = getMean(damageValues);
        double sigma = getSigma(mean, damageValues);
        mean = mean * creatureCount;
        sigma = sigma * Math.sqrt(creatureCount);
        double z = (target/multiplier-mean)/sigma;
        return standardNormalCDF(z);
    }

    private static double getSigma(double mean, double[] damageValues) {
        double sigma = 0;
        for (int i = 0; i < damageValues.length; i++) {
            sigma += Math.pow(damageValues[i] - mean, 2);
        }
        return sigma / damageValues.length;
    }

    private static double getMean(double[] damageValues) {
        return getElementsSum(damageValues) / damageValues.length;
    }

    private static double getElementsSum(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static double[] getDamageValues(double minDamage, double maxDamage) {
        double[] damageValues = new double[(int) (maxDamage - minDamage + 1)];
        for (int i = 0; i < damageValues.length; i++) {
            damageValues[i] = minDamage + i;
        }
        return damageValues;
    }

    private static double standardNormalCDF(double z) {
        return 0.5 * (1 + erf(z / Math.sqrt(2)));
    }

    private static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                        t * (0.37409196 +
                                t * (0.09678418 +
                                        t * (-0.18628806 +
                                                t * (0.27886807 +
                                                        t * (-1.13520398 +
                                                                t * (1.48851587 +
                                                                        t * (-0.82215223 +
                                                                                t * (0.17087277))))))))));

        if (z >= 0) {
            return ans;
        } else {
            return -ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(getChance(1, 3, 200, 100, 1.2));
        // Probability as=new Probability(0, 0, 0, 10, 20);
        // System.out.println(Arrays.toString(as.getDamageArray(10, 20)));
    }
}
