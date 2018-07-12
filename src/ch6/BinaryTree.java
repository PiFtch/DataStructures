package ch6;

public interface BinaryTree<T extends Comparable<? super T>> {
	// return if this tree is empty
	boolean isEmpty();
	
	// return number of nodes
	int size();
	
	// return height of this tree
	int height();
	
	void preOrder();
	void inOrder();
	void postOrder();
	void levelOrder();
	
	// insert x as the root node and return it
	BinaryNode<T> insert(T x);
	// insert x as p's left/righr child
	BinaryNode<T> insert(BinaryNode<T> p, T x, boolean leftChild);
	// delete parent's left/right subtree
	void remove(BinaryNode<T> parent, boolean leftChild);
	// delete all nodes
	void clear();
	// search and return node with key
	BinaryNode<T> search(T key);
	// return if this tree contains a node with key
	boolean contains(T key);
	// return the level the node with key lies
	int level(T key);
}
