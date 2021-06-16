package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = insertElements(array);
		return floor(tree.getRoot(), numero);
	}

	private Integer floor(BSTNode<Integer> node, double number) {

		if (node.getData() == null) {
			return null;
		}
		if (number == node.getData()) {
			return node.getData();
		}
		if (number < node.getData()) {
			return floor((BSTNode<Integer>) node.getLeft(), number);
		}

		Integer value = floor((BSTNode<Integer>) node.getRight(), number);
		if (value != null) {
			return value;
		} else {
			return node.getData();
		}
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = insertElements(array);
		return ceil(tree.getRoot(), numero);

	}

	private Integer ceil(BSTNode<Integer> node, double number) {
		if (node.getData() == null) {
			return null;
		} else if (node.getData() == number) {
			return node.getData();
		} else if (number > node.getData()) {
			return ceil((BSTNode<Integer>) node.getRight(), number);
		}

		Integer value = ceil((BSTNode<Integer>) node.getLeft(), number);
		if (value != null) {
			return value;
		} else {
			return node.getData();
		}
	}

	private BSTImpl<Integer> insertElements(Integer[] array) {
		BSTImpl<Integer> bst = new BSTImpl<Integer>();
		for (int i : array) {
			bst.insert(i);
		}
		return bst;
	}

}
