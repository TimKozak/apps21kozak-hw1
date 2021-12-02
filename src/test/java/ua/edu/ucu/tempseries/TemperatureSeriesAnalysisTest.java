package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int expResult = 4;

        int actualResult = seriesAnalysis.addTemps(temperatureSeries);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummary() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(temperatureSeries);

        TempSummaryStatistics summary = seriesAnalysis.summaryStatistics();

        assertEquals(summary.getAvgTemp(), 1, 0.00001);
        assertEquals(summary.getDevTemp(), 3.7416573867739413, 0.00001);
        assertEquals(summary.getMinTemp(), -5.0, 0.00001);
        assertEquals(summary.getMaxTemp(), 5.0, 0.00001);
    }

    @Test
    public void testGreaterLessThan() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] greaterThanTwo = new double[]{3.0, 5.0};
        double[] lessThanTwo = new double[]{-5.0, 1.0};

        assertEquals(seriesAnalysis.findTempsGreaterThen(2.0)[0], greaterThanTwo[0], 0.00001);
        assertEquals(seriesAnalysis.findTempsLessThen(2.0)[0], lessThanTwo[0], 0.00001);
    }

    @Test
    public void testSearches() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(seriesAnalysis.findTempClosestToValue(6), 5.0, 0.00001);
        assertEquals(seriesAnalysis.findTempClosestToZero(), 1, 0.00001);
        assertEquals(seriesAnalysis.min(), -5.0, 0.00001);
        assertEquals(seriesAnalysis.max(), 5.0, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testInputMismatch() {
        double[] temperatureSeries = {3.0, -400.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(temperatureSeries);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentDeviation() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.deviation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentClosest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToValue(6);
    }

    @Test(expected = InputMismatchException.class)
    public void testIllegalArgumentConstructor() {
        double[] temperatureSeries = {3.0, -400.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }
}
