package adt.bst;

import adt.bt.BTNode;

/**
 *
 * Performs consistency validations within a BST instance
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {

	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}

	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {

		if (this.getBSt() != null) {
			if(this.getBSt().isEmpty()) return true;
			return isBST(this.getBSt().getRoot());
		}
		return false;
	}

	private boolean isBST(BTNode<T> node) {
		if (!node.isEmpty()) {
			T nodeValue = node.getData();

			if (node.getLeft().getData() != null && node.getLeft().getData().compareTo(nodeValue) > 0) {
				return  false;
			}
			if (node.getRight().getData() != null && node.getRight().getData().compareTo(nodeValue) < 0) {
				return  false;
			}

			return isBST(node.getLeft()) && isBST(node.getRight());
		}

		return true;
	}

}