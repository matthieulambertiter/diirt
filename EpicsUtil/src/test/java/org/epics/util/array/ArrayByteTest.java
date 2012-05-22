/*
 * Copyright 2011 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.util.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carcassi
 */
public class ArrayByteTest {
    
    public ArrayByteTest() {
    }

    @Test
    public void wrap1() {
        ArrayByte array = new ArrayByte(new byte[] {0, 1, 2, 3, 4, 5});
        assertThat(CollectionNumbers.toDoubleArray(array), equalTo(new double[] {0, 1, 2, 3, 4, 5}));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void wrap2() {
        ArrayByte array = new ArrayByte(new byte[] {0, 1, 2, 3, 4, 5});
        array.setDouble(0, 0);
    }

    @Test
    public void wrap3() {
        ArrayByte array = new ArrayByte(new byte[] {0, 1, 2, 3, 4, 5}, false);
        array.setDouble(0, 5);
        array.setDouble(5, 0);
        assertThat(CollectionNumbers.toDoubleArray(array), equalTo(new double[] {5, 1, 2, 3, 4, 0}));
    }
}
