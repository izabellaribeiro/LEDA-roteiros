package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 *
 * Implementacao de uma arvore AVL
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;
		if (!node.isEmpty()) {
			balance = this.recursiveHeight(node.getLeft()) - recursiveHeight(node.getRight());
		}

		return balance;

	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		BSTNode<T> aux;
		if (balance > 1) {
			BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
			int newBalance = calculateBalance(leftNode);

			// LL
			if (newBalance > 0) {
				aux = Util.rightRotation(node);
			}
			// LR
			else {
				Util.leftRotation(leftNode);
				aux = Util.rightRotation(node);
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
			}
			// RL
			else {
				Util.rightRotation(rightNode);
				aux = Util.leftRotation(node);
			}

			if (aux.getParent() == null) {
				super.root = aux;
			}

		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {

		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
		while (parentNode != null) {
			rebalance(parentNode);
			parentNode = (BSTNode<T>) parentNode.getParent();
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			recursiveInsert(this.root, element);
		}
	}

	private void recursiveInsert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		} else {
			if (node.getData().compareTo(element) < 0) {
				recursiveInsert((BSTNode<T>) node.getRight(), element);
			} else if (node.getData().compareTo(element) > 0) {
				recursiveInsert((BSTNode<T>) node.getLeft(), element);
			}

			rebalance(node);
		}

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		super.remove(element);
		rebalanceUp(node);
	}
}