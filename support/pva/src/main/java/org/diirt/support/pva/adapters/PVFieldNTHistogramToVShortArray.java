/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.diirt.support.pva.adapters;


import java.util.ArrayList;
import java.util.List;

import org.epics.pvdata.pv.DoubleArrayData;
import org.epics.pvdata.pv.ShortArrayData;
import org.epics.pvdata.pv.PVDoubleArray;
import org.epics.pvdata.pv.PVShortArray;
import org.epics.pvdata.pv.PVStructure;
import org.epics.pvdata.pv.ScalarType;
import org.diirt.util.array.ArrayDouble;
import org.diirt.util.array.ArrayInt;
import org.diirt.util.array.ArrayShort;
import org.diirt.util.array.ListInt;
import org.diirt.util.array.ListShort;
import org.diirt.vtype.ArrayDimensionDisplay;
import org.diirt.vtype.VShortArray;
import org.diirt.vtype.VTypeToString;
import org.diirt.vtype.ValueFactory;

/**
 * @author msekoranja
 *
 */
public class PVFieldNTHistogramToVShortArray extends AlarmTimeDisplayExtractor implements VShortArray {

        private static final String RANGES_UNIT = "";

        private final ListInt size;
        private final ListShort list;

        private final List<ArrayDimensionDisplay> displays = new ArrayList<ArrayDimensionDisplay>(1);

        /**
         * @param pvField
         * @param disconnected
         */
        public PVFieldNTHistogramToVShortArray(PVStructure pvField, boolean disconnected) {
                super(pvField, disconnected);

                PVShortArray valueField =
                        (PVShortArray)pvField.getScalarArrayField("value", ScalarType.pvShort);
                if (valueField != null)
                {
                        ShortArrayData data = new ShortArrayData();
                        valueField.get(0, valueField.getLength(), data);

                        this.size = new ArrayInt(data.data.length);
                        this.list = new ArrayShort(data.data);
                }
                else
                {
                        size = null;
                        list = null;
                }

                PVDoubleArray rangesField =
                                (PVDoubleArray)pvField.getScalarArrayField("ranges", ScalarType.pvDouble);
                if (rangesField != null)
                {
                        DoubleArrayData data = new DoubleArrayData();
                        rangesField.get(0, rangesField.getLength(), data);

                        ArrayDimensionDisplay display = ValueFactory.newDisplay(new ArrayDouble(data.data), RANGES_UNIT);
                        displays.add(display);
                }
        }

        /* (non-Javadoc)
         * @see org.epics.pvmanager.data.Array#getSizes()
         */
        @Override
        public ListInt getSizes() {
                return size;
        }

        /* (non-Javadoc)
         * @see org.epics.pvmanager.data.VShortArray#getData()
         */
        @Override
        public ListShort getData() {
                return list;
        }

    @Override
    public String toString() {
        return VTypeToString.toString(this);
    }

    @Override
    public List<ArrayDimensionDisplay> getDimensionDisplay() {
        return displays;
    }

}
