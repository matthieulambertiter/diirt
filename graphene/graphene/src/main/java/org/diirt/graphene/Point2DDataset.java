/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.diirt.graphene;

import org.diirt.util.stats.Statistics;
import org.diirt.util.array.ListNumber;
import org.diirt.util.stats.Range;

/**
 * A dataset consisting of a set of 2D points.
 * <p>
 * It represents a list of ordered tuples of two values, and their statistical information.
 * The order may not be meaningful, but can be used to identify the points.
 *
 * @author carcassi
 */
public interface Point2DDataset {

    /**
     * The x values of the point.
     * <p>
     * If the dataset is empty, it returns an empty list.
     *
     * @return the x values; never null
     */
    public ListNumber getXValues();

    /**
     * The y values of the point.
     * <p>
     * If the dataset is empty, it returns an empty list.
     *
     * @return the y values; never null
     */
    public ListNumber getYValues();

    /**
     * The statistical information of x values.
     * <p>
     * If the dataset is empty, or if it contains only NaN values, it returns null.
     *
     * @return x statistical information; null if no actual values in the dataset
     */
    public Statistics getXStatistics();

    /**
     * The statistical information of x values.
     * <p>
     * If the dataset is empty, or if it contains only NaN values, it returns null.
     *
     * @return x statistical information; null if no actual values in the dataset
     */
    public Statistics getYStatistics();

    /**
     * The suggested range to display the x values.
     *
     * @return the suggested x display range
     */
    public Range getXDisplayRange();

    /**
     * The suggested range to display the y values.
     *
     * @return the suggested y display range
     */
    public Range getYDisplayRange();

    /**
     * The number of points in the dataset.
     * <p>
     * This number matches the size of the list returned by {@link #getXValues() }.
     *
     * @return the number of values in this dataset
     */
    public int getCount();

}
