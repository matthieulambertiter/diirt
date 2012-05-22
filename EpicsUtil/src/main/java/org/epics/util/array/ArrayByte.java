/*
 * Copyright 2011 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.util.array;

/**
 * Wraps a {@code byte[]} into a {@link ListByte}.
 *
 * @author Gabriele Carcassi
 */
public final class ArrayByte extends ListByte {
    
    private final byte[] array;
    private final boolean readOnly;

    /**
     * A new read-only {@code ArrayByte} that wraps around the given array.
     * 
     * @param array an array
     */
    public ArrayByte(byte[] array) {
        this(array, true);
    }
    
    /**
     * A new {@code ArrayByte} that wraps around the given array.
     * 
     * @param array an array
     * @param readOnly if false the wrapper allows writes to the array
     */
    public ArrayByte(byte[] array, boolean readOnly) {
        this.array = array;
        this.readOnly = readOnly;
    }

    @Override
    public final IteratorByte iterator() {
        return new IteratorByte() {
            
            private int index;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public byte nextByte() {
                return array[index++];
            }
        };
    }

    @Override
    public final int size() {
        return array.length;
    }
    
    @Override
    public final byte getByte(int index) {
        return array[index];
    }

    @Override
    public void setByte(int index, byte value) {
        if (!readOnly) {
            array[index] = value;
        } else {
            throw new UnsupportedOperationException("Read only list.");
        }
    }
    
}
