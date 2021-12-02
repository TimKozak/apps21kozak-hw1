package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private final int minimumTemp = -273;
    private final int infinity = 999999999;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
    }

//    Done
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature: temperatureSeries) {
            if (temperature < minimumTemp) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries;
    }

//    Done
    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double sum = 0;

        for (double temperature : temperatureSeries) {
            sum += temperature;
        }

        return sum/temperatureSeries.length;
    }

//    Done
    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double[] squareDiffs = new double[temperatureSeries.length];

        double avg = average();

        double squareDiffSum = 0;

        for (int i = 0; i < temperatureSeries.length; i++) {
            double diff = temperatureSeries[i] - avg;
            double squareDiff = diff * diff;
            squareDiffs[i] = squareDiff;
        }

        for (double squareDiff : squareDiffs) {
            squareDiffSum += squareDiff;
        }

        double avgSquareDiff = squareDiffSum/squareDiffs.length;

        return Math.sqrt(avgSquareDiff);
    }

//    Done
    public double min() {
        return findTempClosestToValue(minimumTemp);
    }

//    Done
    public double max() {
        return findTempClosestToValue(infinity);
    }

//    Done
    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

//    Done
    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double currentClosestElem = temperatureSeries[0];
        double curClosestDist = Math.abs(temperatureSeries[0] - tempValue);

        for (double temperature: temperatureSeries) {
            if (Math.abs((temperature - tempValue)) < curClosestDist) {
                currentClosestElem = temperature;
                curClosestDist = Math.abs((temperature - tempValue));
            }
        }
        return currentClosestElem;
    }

//    Done
    public double[] findTempsLessThen(double tempValue) {
        int amountOfLessTemps = 0;

        for (double temperature : temperatureSeries) {
            if (temperature < tempValue) {
                amountOfLessTemps++;
            }
        }

        double[] lessTemps = new double[amountOfLessTemps];
        int j = 0;

        for (double temperature : temperatureSeries) {
            if (temperature < tempValue) {
                lessTemps[j] = temperature;
                j++;
            }
        }

        return lessTemps;
    }

//    Done
    public double[] findTempsGreaterThen(double tempValue) {
        int amountOfGreaterTemps = 0;

        for (double temperature : temperatureSeries) {
            if (temperature > tempValue) {
                amountOfGreaterTemps++;
            }
        }

        double[] greaterTemps = new double[amountOfGreaterTemps];
        int j = 0;

        for (double temperature : temperatureSeries) {
            if (temperature > tempValue) {
                greaterTemps[j] = temperature;
                j++;
            }
        }

        return greaterTemps;
    }

//    Done
    public TempSummaryStatistics summaryStatistics() {
        double avgTemp = average();
        double devTemp = deviation();
        double minTemp = min();
        double maxTemp = max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);
    }

//    Done
    public int addTemps(double... temps) {
        for (double temp: temps) {
            if (temp < minimumTemp) {
                throw new InputMismatchException();
            }
        }

        int amountOfNewTemps = temps.length;
        int previousLength = temperatureSeries.length;
        int newLength = previousLength + amountOfNewTemps;

        temperatureSeries = Arrays.copyOf(temperatureSeries, newLength);
        int j = 0;
        for (double temp : temps) {
            temperatureSeries[previousLength + j] = temp;
            j++;
        }

        return newLength;
    }
}
