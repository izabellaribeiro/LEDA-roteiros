package adt.heap;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import adt.heap.extended.FloorCeilHeapImpl;

public class FloorCeilHeapTest {

    Comparator<Integer> comparator = new ComparatorMaxHeap<>();
    FloorCeilHeapImpl fc = new FloorCeilHeapImpl(comparator);

    Integer[] array = { 3 };
    Integer[] array2 = { 3, 2, 6, 100, 8 };
    Integer[] array3 = {};
    Integer[] array4 = { -1, 2, 3, 50, 0 };
    Integer[] array5 = { -9 };
    Integer[] array6 = null;

    @Test
    public void testFloor() {
        assertEquals(fc.floor(array, 3), new Integer(3));
        assertEquals(fc.floor(array, 100), new Integer(3));
        assertEquals(fc.floor(array, 4), new Integer(3));
        assertNull(fc.floor(array, 2));
        assertNull(fc.floor(array, 0));
        assertNull(fc.floor(array, -10));

        assertNull(fc.floor(array3, 3));

        assertEquals(fc.floor(array2, 8), new Integer(8));

    }

    @Test
    public void testFloor2() {
        assertEquals(fc.floor(array5, 0), new Integer(-9));
        assertEquals(fc.floor(array4, 1000), new Integer(50));
        assertEquals(fc.ceil(array5, -100), new Integer(-9));
    }

    @Test
    public void testCeil() {
        assertEquals(fc.ceil(array, 3), new Integer(3));
        assertEquals(fc.ceil(array, 2), new Integer(3));
        assertNull(fc.ceil(array, 10));

        assertNull(fc.ceil(array5, 10));

    }

    @Test
    public void testNull() {
        assertNull(fc.floor(array6, 3));
        assertNull(fc.ceil(array6, 3));
    }
}