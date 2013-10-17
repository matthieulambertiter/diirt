/**
 * Copyright (C) 2012 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.graphene;

import org.epics.util.array.ArrayDouble;
import org.epics.util.array.ListNumber;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carcassi
 */
public class Cell2DDatasetsTest {

    @Test
    public void linearRange1() {
        Cell2DDataset dataset = Cell2DDatasets.linearRange(new Cell2DDatasets.Function2D() {

            @Override
            public double getValue(double x, double y) {
                return x + y;
            }
        }, RangeUtil.range(0, 1), 10, RangeUtil.range(0, 1), 20);
        
        System.out.println(dataset.getValue(0, 0));
        System.out.println(dataset.getValue(9, 19));
        
        assertThat(dataset.getXCount(), equalTo(10));
        assertThat(dataset.getXRange().getMinimum().doubleValue(), equalTo(0.0));
        assertThat(dataset.getXRange().getMaximum().doubleValue(), equalTo(1.0));
        assertThat(dataset.getYCount(), equalTo(20));
        assertThat(dataset.getYRange().getMinimum().doubleValue(), equalTo(0.0));
        assertThat(dataset.getYRange().getMaximum().doubleValue(), equalTo(1.0));
        assertThat(dataset.getStatistics().getAverage(), closeTo(1.0, 0.0001));
        assertThat(dataset.getStatistics().getStdDev(), closeTo(0.40697, 0.0001));
        assertThat(dataset.getStatistics().getCount(), equalTo(200));
        assertThat(dataset.getStatistics().getMinimum().doubleValue(), closeTo(0.075, 0.0001));
        assertThat(dataset.getStatistics().getMaximum().doubleValue(), closeTo(1.925, 0.0001));
        
        // Check values
        assertThat(dataset.getXBoundaries().getDouble(0), equalTo(0.0));
        assertThat(dataset.getYBoundaries().getDouble(0), equalTo(0.0));
        assertThat(dataset.getValue(0, 0), closeTo(0.075, 0.0001));
        assertThat(dataset.getValue(9, 19), closeTo(1.925, 0.0001));
        assertThat(dataset.getXBoundaries().getDouble(10), equalTo(1.0));
        assertThat(dataset.getYBoundaries().getDouble(20), equalTo(1.0));
    }
    
    @Test
    public void datasetFrom1() {
        ListNumber values = new ArrayDouble(5, 3, 7, -1, 2, 2);
        ListNumber xBoundaries = new ArrayDouble(-1, 0, 1);
        ListNumber yBoundaries = new ArrayDouble(0, 1, 2, 3);
        Cell2DDataset dataset = Cell2DDatasets.datasetFrom(values, xBoundaries, yBoundaries);
        
        assertThat(dataset.getXCount(), equalTo(2));
        assertThat(dataset.getXRange().getMinimum().doubleValue(), equalTo(-1.0));
        assertThat(dataset.getXRange().getMaximum().doubleValue(), equalTo(1.0));
        assertThat(dataset.getYCount(), equalTo(3));
        assertThat(dataset.getYRange().getMinimum().doubleValue(), equalTo(0.0));
        assertThat(dataset.getYRange().getMaximum().doubleValue(), equalTo(3.0));
        assertThat(dataset.getStatistics().getAverage(), equalTo(3.0));
        assertThat(dataset.getStatistics().getStdDev(), closeTo(2.51661, 0.0001));
        assertThat(dataset.getStatistics().getMinimum(), equalTo((Number) (-1.0)));
        assertThat(dataset.getStatistics().getMaximum(), equalTo((Number) 7.0));
        assertThat(dataset.getStatistics().getCount(), equalTo(6));
        
        // Check values
        assertThat(dataset.getXBoundaries().getDouble(0), equalTo(-1.0));
        assertThat(dataset.getXBoundaries().getDouble(1), equalTo(0.0));
        assertThat(dataset.getXBoundaries().getDouble(2), equalTo(1.0));
        assertThat(dataset.getYBoundaries().getDouble(0), equalTo(0.0));
        assertThat(dataset.getYBoundaries().getDouble(1), equalTo(1.0));
        assertThat(dataset.getYBoundaries().getDouble(2), equalTo(2.0));
        assertThat(dataset.getYBoundaries().getDouble(3), equalTo(3.0));
        assertThat(dataset.getValue(0, 0), equalTo(5.0));
        assertThat(dataset.getValue(1, 0), equalTo(3.0));
        assertThat(dataset.getValue(0, 1), equalTo(7.0));
        assertThat(dataset.getValue(1, 1), equalTo(-1.0));
        assertThat(dataset.getValue(0, 2), equalTo(2.0));
        assertThat(dataset.getValue(1, 2), equalTo(2.0));
    }
}
