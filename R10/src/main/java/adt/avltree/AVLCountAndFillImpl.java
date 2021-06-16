package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		List<T[]> list = new ArrayList<T[]>();
		Arrays.sort(array);
		list.add(array);
		int i = 0;
		while (i < list.size()) {
			T[] arrayAux = list.get(i);
			int middle = arrayAux.length / 2;
			T[] arrayAux1 = Arrays.copyOfRange(arrayAux, 0, middle);
			T[] arrayAux2 = Arrays.copyOfRange(arrayAux, middle + 1, arrayAux.length);
			if (arrayAux.length > 1) {
				list.add(arrayAux1);
				list.add(arrayAux2);
			}
			insert(arrayAux[middle]);
			i++;
		}

	}



	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		BSTNode<T> aux;
		if (balance > 1) {
			BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
			int newBalance = calculateBalance(leftNode);

			// LL
			if (newBalance > 0) {
				aux = Util.rightRotation(node);
				LLcounter++;
			}
			// LR
			else {
				Util.leftRotation(leftNode);
				aux = Util.rightRotation(node);
				LRcounter++;
			}

			if (aux.getParent() == null) {
				super.root = aux;
			}

		} else if (balance < -1) {
			BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
			int newBalance = calculateBalance(rightNode);

			// RR
			if (newBalance < 0) {
				aux = Util.leftRotation(node);
				RRcounter++;
			}
			// RL
			else {
				Util.rightRotation(rightNode);
				aux = Util.leftRotation(node);
				RLcounter++;
			}

			if (aux.getParent() == null) {
				super.root = aux;
			}

		}
	}
}