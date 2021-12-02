package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
    }

//    Done
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature: temperatureSeries) {
            if (temperature < -273) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries;
    }

//    Done
    public double average() {
        if (temperatureSeries.length == 0) throw new IllegalArgumentException();

        double sum = 0;

        for (double temperature : temperatureSeries) {
            sum += temperature;
        }

        return sum/temperatureSeries.length;
    }

//    Done
    public double deviation() {
        if (temperatureSeries.length == 0) throw new IllegalArgumentException();

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
        return findTempClosestToValue(-274);
    }

//    Done
    public double max() {
        return findTempClosestToValue(999999999);
    }

//    Done
    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

//    Done
    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) throw new IllegalArgumentException();

        double currentClosestElem = temperatureSeries[0];
        double currentClosestDistance = Math.abs(temperatureSeries[0] - tempValue);

        for (double temperature: temperatureSeries) {
            if (Math.abs((temperature - tempValue)) < currentClosestDistance) {
                currentClosestElem = temperature;
                currentClosestDistance = Math.abs((temperature - tempValue));
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
            if (temp < -273) {
                throw new InputMismatchException();
            }
        }

        int amountOfNewTemps = temps.length;
        int previousLength = temperatureSeries.length;

        temperatureSeries = Arrays.copyOf(temperatureSeries, previousLength + amountOfNewTemps);
        int j = 0;
        for (double temp : temps) {
            temperatureSeries[previousLength + j] = temp;
            j++;
        }

        return previousLength + amountOfNewTemps;
    }
}
