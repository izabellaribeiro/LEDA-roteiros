package problems;


import util.Util;

public class FloorBinarySearchImpl<I extends Number> implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer floor = null;

		if (array != null && array.length > 0) {
			quickSort(array, 0, array.length - 1);
			floor = recursiveSearch(array, x, null, 0, array.length - 1);
		}

		return floor;
	}

	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex) {
			int pivotIndex = this.partition(array, leftIndex, rightIndex);
			this.quickSort(array, leftIndex, pivotIndex - 1);
			this.quickSort(array, pivotIndex + 1, rightIndex);
		}
	}

	private Integer recursiveSearch(Integer[] array, Integer x, Integer floor, int leftIndex, int rightIndex) {

		if (leftIndex >= 0 && rightIndex < array.length && leftIndex <= rightIndex) {
			int middleIndex = (leftIndex + rightIndex) / 2;

			if (array[middleIndex].compareTo(x) > 0) {
				return recursiveSearch(array, x, floor, leftIndex,middleIndex-1);
			} else if (array[middleIndex].compareTo(x) < 0) {
				return recursiveSearch(array, x,array[middleIndex], middleIndex +1, rightIndex);
			}
			return array[middleIndex];

		}
		return floor;
	}


	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		int middleIndex = (leftIndex + rightIndex) / 2;
		medianOfThree(array, leftIndex, middleIndex, rightIndex);

		Integer pivot = array[middleIndex];
		Util.swap(array, middleIndex, rightIndex - 1);
		int pivotIndex = rightIndex - 1;

		for (int i = pivotIndex - 1; i > leftIndex; i--) {
			if (pivot.compareTo(array[i]) < 0) {
				pivotIndex--;
				Util.swap(array, pivotIndex, i);
			}
		}
		Util.swap(array, rightIndex - 1, pivotIndex);
		return pivotIndex;

	}

	private void medianOfThree(Integer[] array, int leftIndex, int middleIndex, int rightIndex) {
		if (array[leftIndex].compareTo(array[middleIndex]) > 0) {
			Util.swap(array, leftIndex, middleIndex);
		}
		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);
		}
		if (array[middleIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, middleIndex, rightIndex);
		}
	}


}
