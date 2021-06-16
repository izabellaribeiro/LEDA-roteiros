package adt.bst;

import static org.junit.Assert.*;

import org.junit.Test;

public class Arvore {

    private SimpleBSTManipulationImpl<Integer> s = new SimpleBSTManipulationImpl<>();
    private BSTImpl<Integer> tree = new BSTImpl<>();
    private BSTImpl<Integer> tree2 = new BSTImpl<>();
    private BSTImpl<Integer> tree3 = new BSTImpl<>();
    private BSTImpl<Integer> tree4 = new BSTImpl<>();
    private BSTImpl<Integer> tree5 = new BSTImpl<>();
    private BSTImpl<Integer> tree6 = new BSTImpl<>();
    private BSTImpl<Integer> tree7 = new BSTImpl<>();
    private BSTImpl<Integer> tree8 = new BSTImpl<>();

    private void fillTree() {
        Integer[] array6 = { 1, 6, 8, 9, 10, 13, 35, 76, 89 };
        Integer[] array7 = {};

        for (int i : array6) {
            tree6.insert(i);
        }
        for (int i : array7) {
            tree7.insert(i);
        }

        Integer[] array = { 1, 6, 8, 9, 10, 13, 35, 76, 89 };
        Integer[] array2 = { 22 };
        for (int i : array) {
            tree.insert(i);
        }
        for (int i : array2) {
            tree2.insert(i);
        }

        Integer[] array3 = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        for (int i : array3) {
            tree3.insert(i);
        }

        Integer[] array4 = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        for (int i : array4) {
            tree4.insert(i);
        }
        Integer[] array5 = { 1 };
        for (int i : array5) {
            tree5.insert(i);
        }
        Integer[] array8 = {};

        for (int i : array8) {
            tree8.insert(i);
        }

    }

    @Test
    public void equals() {
        fillTree();
        assertTrue(s.equals(tree, tree6));
        assertTrue(s.equals(tree3, tree4));
        assertTrue(s.equals(tree7, tree8));

    }

    @Test
    public void isSimilar() {
        fillTree();
        assertTrue(s.isSimilar(tree3, tree4));
        tree4.remove(6);
        tree4.remove(12);
        assertFalse(s.isSimilar(tree3, tree4));

        assertFalse(s.isSimilar(tree, tree2));
        assertTrue(s.isSimilar(tree2, tree5));

        assertFalse(s.isSimilar(tree6, tree7));
    }

    @Test
    public void orderStatistics() {
        fillTree();

        // { 1, 6, 8, 9, 10, 13, 35, 76, 89 };
        assertEquals(s.orderStatistic(tree, 3), new Integer(8));
        assertEquals(s.orderStatistic(tree, 1), new Integer(1));
        assertEquals(s.orderStatistic(tree, 5), new Integer(10));
        assertEquals(s.orderStatistic(tree, 2), new Integer(6));

        assertEquals(s.orderStatistic(tree, 9), new Integer(89));
        assertEquals(s.orderStatistic(tree, 10), null);
        assertEquals(s.orderStatistic(tree, 100), null);
        assertEquals(s.orderStatistic(tree, -2), null);

        assertEquals(s.orderStatistic(tree3, 3), new Integer(0));
        assertNull(s.orderStatistic(tree7, 1));
    }
}