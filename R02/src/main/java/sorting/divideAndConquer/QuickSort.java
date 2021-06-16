package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */

public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex < rightIndex) {
			int indexPivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, indexPivot - 1);
			sort(array, indexPivot + 1, rightIndex);
		}
	}

	public int partition(T[] values, int left, int right){

		T pivot = values[left];
		int i = left;

		for (int j = left + 1; j <= right ; j++) {
			if (values[j].compareTo(pivot) < 0 || values[j].compareTo(pivot) == 0) {
				i++;
				Util.swap(values, i, j);
			}
		}

		Util.swap(values, left, i);

		return i;
	}

}
