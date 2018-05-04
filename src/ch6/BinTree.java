package ch6;

public class BinTree<T> implements BinaryTree<T> {
	
	public BinaryNode<T> root;
	
	// Constructors *********************************************
	public BinTree() {
		this.root = null;
	}
	
	// End of Constructors **************************************
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.root == null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size(this.root);
	}
	
	private int size(BinaryNode<T> p) {
		if (p != null)
			return size(p.left) + size(p.right) + 1;
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return height(this.root);
	}
	
	private int height(BinaryNode<T> p) {
		if (p != null) {
			int leftHeight = height(p.left);
			int rightHeight = height(p.right);
			return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
		}
		return 0;
	}

	@Override
	public void preOrder() {
		// TODO Auto-generated method stub
		preOrder(this.root);
		System.out.println();
	}
	
	private void preOrder(BinaryNode<T> p) {
		if (p != null) {
			System.out.println(p.data.toString() + " ");
			preOrder(p.left);
			preOrder(p.right);
		}
	}

	public String toString() {
		return toString(this.root);
	}
	
	private String toString(BinaryNode<T> p) {
		if (p == null)
			return "^";
		return p.data.toString() + " " + toString(p.left) + toString(p.right);
	}
	@Override
	public void inOrder() {
		// TODO Auto-generated method stub
		inOrder(this.root);
	}
	
	private void inOrder(BinaryNode<T> p) {
		if (p != null) {
			inOrder(p.left);
			System.out.print(p.data.toString() + " ");
			inOrder(p.right);;
		}
	}

	@Override
	public void postOrder() {
		// TODO Auto-generated method stub
		postOrder(this.root);
		System.out.println();
	}
	
	private void postOrder(BinaryNode<T> p) {
		if (p != null) {
			postOrder(p.left);
			postOrder(p.right);
			System.out.print(p.data.toString() + " ");
		}
	}

	@Override
	public void levelOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BinaryNode<T> insert(T x) { // insert x as the root node, while original root as its left child
		// TODO Auto-generated method stub
		this.root = new BinaryNode<T>(x, this.root, null);
		return this.root;
	}

	@Override
	public BinaryNode<T> insert(BinaryNode<T> p, T x, boolean leftChild) {
		// TODO Auto-generated method stub
		if (x == null)
			return null;
		if (leftChild) {
			p.left = new BinaryNode<T>(x, p.left, null);
			return p.left;
		}
		return p.right = new BinaryNode<T>(x, null, p.right);
	}

	@Override
	public void remove(BinaryNode<T> parent, boolean leftChild) {
		// TODO Auto-generated method stub
		if (leftChild)
			parent.left = null;
		else
			parent.right = null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.root = null;
	}

	@Override
	// return the node lies in the lowest level
	public BinaryNode<T> search(T key) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	private BinaryNode<T> search(BinaryNode<T> p, T key) {
		return null;
	}

	@Override
	public boolean contains(T key) {
		// TODO Auto-generated method stub
		return contains(this.root, key);
	}
	
	private boolean contains(BinaryNode<T> p, T key) {
		if (p == null)
			return false;
		if (p.data == key)
			return true;
		return contains(p.left, key) || contains(p.right, key);
	}

	@Override
	// return the lowest level the key lies
	// if this tree does not contain key, return 0
	public int level(T key) {
		// TODO Auto-generated method stub
		return level(this.root, key);
	}
	
	private int level(BinaryNode<T> p, T key) {
		if (p == null)
			return 0;
		if (p.data == key)
			return 1;
		int leftLevel = level(p.left, key);
		int rightLevel = level(p.right, key);
		if (leftLevel == 0 && rightLevel == 0)
			return 0;
		else
			return 1 + (leftLevel < rightLevel ? leftLevel : rightLevel);
	}
	
}
