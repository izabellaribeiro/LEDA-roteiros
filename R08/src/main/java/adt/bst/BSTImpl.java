package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return recursiveHeight(this.root);
	}

	private int recursiveHeight(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;
		else
			return 1 + Math.max(recursiveHeight((BSTNode<T>) node.getLeft()),
					recursiveHeight((BSTNode<T>) node.getRight()));

	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> aux = new BSTNode<>();

		if (!this.isEmpty() && element != null)
			aux = recursiveSearch(element, this.root);

		return aux;

	}

	private BSTNode<T> recursiveSearch(T element, BSTNode<T> node) {
		BSTNode<T> auxNode = new BSTNode<>();

		if (!node.isEmpty()) {

			if (node.getData().equals(element))
				auxNode = node;
			else if (node.getData().compareTo(element) > 0)
				auxNode = recursiveSearch(element, (BSTNode<T>) node.getLeft());
			else
				auxNode = recursiveSearch(element, (BSTNode<T>) node.getRight());
		}
		return auxNode;
	}

	@Override
	public void insert(T element) {
		if (element != null)
			insert(element, this.root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		} else {
			if (node.getData().compareTo(element) > 0)
				insert(element, (BSTNode<T>) node.getLeft());
			else
				insert(element, (BSTNode<T>) node.getRight());
		}

	}

	@Override
	public BSTNode<T> maximum() {
		if (this.root.isEmpty())
			return null;
		else
			return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty())
			return node;
		else
			return maximum((BSTNode<T>) node.getRight());

	}

	@Override
	public BSTNode<T> minimum() {
		if (this.root.isEmpty())
			return null;
		else
			return minimum(this.root);

	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty())
			return node;
		else
			return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> sucessor = search(element);

		if (element != null && !this.root.isEmpty() && !sucessor.isEmpty()) {
			if (!sucessor.getRight().isEmpty()) {
				sucessor = minimum((BSTNode<T>) sucessor.getRight());
			} else {
				sucessor = sucessor(sucessor);
			}
		} else {
			sucessor = null;
		}

		return sucessor;

	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> sucessor = (BSTNode<T>) node.getParent();

		if (node.getParent() != null) {

			if (!sucessor.isEmpty() && sucessor.getRight().equals(node)) {
				sucessor = sucessor((BSTNode<T>) node.getParent());
			}
		}

		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = search(element);

		if (element != null && !this.root.isEmpty() && !result.isEmpty()) {
			if (!result.getLeft().isEmpty()) {
				result = maximum((BSTNode<T>) result.getLeft());
			} else {
				result = predecessor(result);
			}
		} else {
			result = null;
		}

		return result;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> predecessor = (BSTNode<T>) node.getParent();

		if (node.getParent() != null) {

			if (!predecessor.isEmpty() && predecessor.getLeft().equals(node)) {
				predecessor = predecessor((BSTNode<T>) node.getParent());
			}
		}

		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {


			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);



			} else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) {


				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				if (parent != null) {



					if (!parent.getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							parent.setRight(node.getLeft());
							node.getLeft().setParent(parent);


						} else {
							parent.setRight(node.getRight());
							node.getRight().setParent(parent);
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							parent.setLeft(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setLeft(node.getRight());
							node.getRight().setParent(parent);
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}

	}

	@Override
	public T[] preOrder() {

		T[] array = (T[]) new Comparable[this.size()];

		preOrder(this.root, 0, array);
		return array;

	}

	private void preOrder(BSTNode<T> node, int i, T[] array) {

		if (!node.isEmpty()) {
			array[i] = node.getData();
			preOrder((BSTNode<T>) node.getLeft(), i + 1, array);
			preOrder((BSTNode<T>) node.getRight(), i + 1 + size((BSTNode<T>) node.getLeft()), array);

		}

	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];

		order(this.root, 0, array);
		return array;
	}

	private int order(BSTNode<T> node, int i, T[] array) {
		if (!node.isEmpty()) {
			i = order((BSTNode<T>) node.getLeft(), i, array);
			array[i++] = node.getData();
			i = order((BSTNode<T>) node.getRight(), i, array);

		}
		return i;

	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];

		postOrder(this.root, 0, array);
		return array;
	}

	private int postOrder(BSTNode<T> node, int i, T[] array) {
		if (!node.isEmpty()) {
			i = postOrder((BSTNode<T>) node.getLeft(), i, array);
			i = postOrder((BSTNode<T>) node.getRight(), i, array);
			array[i++] = node.getData();

		}
		return i;

	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
