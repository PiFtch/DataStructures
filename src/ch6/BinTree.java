package ch6;
import ch4.SeqStack;
import ch4.Stack;
public class BinTree<T extends Comparable<? super T>> implements BinaryTree<T> {
	
	public BinaryNode<T> root;
	
	// Constructors *********************************************
	public BinTree() {
		this.root = null;
	}
	
	public BinTree(BinaryNode<T> root) {
		this.root = root;
	}
	
	// Construct BinaryTree using a preOrder sequence with empty flag '^'
	public BinTree(T[] preList) {
		this.root = create(preList);
	}
	private int i = 0;
	private BinaryNode<T> create(T[] preList) {
		BinaryNode<T> p = null;
		if (i < preList.length) {
			T element = preList[i];
			i++;
			if (element != null) {
				p = new BinaryNode<T>(element);
				p.left = create(preList);
				p.right = create(preList);
			}
		}
		return p;
	}
	
	public BinTree(BinTree<T> bintree) {
		this.root = copy(bintree.root);
	}
	
	public BinaryNode<T> copy(BinaryNode<T> p) {
		if (p == null)
			return null;
		BinaryNode<T> node = new BinaryNode<T>(p.data);
		node.left = copy(p.left);
		node.right = copy(p.right);
		return node;
	}

	// End of Constructors **************************************
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] preList = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
		BinTree<String> bintree = new BinTree<>(preList);
		System.out.println("toString: " + bintree.toString());
		System.out.print("PreOrder: "); bintree.preOrder();
		System.out.print("InOrder: "); bintree.inOrder();
		System.out.print("PostOrder: "); bintree.postOrder();
		bintree.insert(bintree.root.left, "X", true);
		bintree.insert(bintree.root.right, "Y", false);
		bintree.insert("Z");
		System.out.println("toSrring: " + bintree.toString());
		System.out.print("PreOrder: "); bintree.preOrder();
		System.out.print("InOrder: "); bintree.inOrder();
		System.out.print("PostOrder: "); bintree.postOrder();
		
		// copy
		System.out.println("tree2: ");
		BinTree<String> tree2 = new BinTree<String>(bintree);
		System.out.println("toSrring: " + tree2.toString());
		System.out.print("PreOrder: "); tree2.preOrder();
		System.out.print("InOrder: "); tree2.inOrder();
		System.out.print("PostOrder: "); tree2.postOrder();
		System.out.println("\n");
		tree2.printGenList();
		System.out.println();
		tree2.insert("KK");
		System.out.print("InOrder: "); tree2.inOrder();

		/*
		// Test level()
		System.out.println("\n" + tree2.level("X"));
		// search
		System.out.println(tree2.search("k"));
		*/
		// preOrderIterative
		tree2.preOrderIterative();
		System.out.print("PostOrderIterative: "); bintree.postOrderIterative();
		/*
		// post
		tree2.postOrderIterative();
		*/
		System.out.println(bintree.isSorted(bintree));
		
		BinTree<Integer> tree = new BinTree<Integer>();
		tree.insert(5);
		tree.insert(10);
		System.out.println(tree.isSorted(tree));
		tree.insert(tree.root, 89, false);
		System.out.println(tree.isSorted(tree));
		tree.insert(tree.root.right, 20, false);
		System.out.println(tree.isSorted(tree));
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
			System.out.print(p.data.toString() + " ");
			preOrder(p.left);
			preOrder(p.right);
		}
	}
	
	// preOrder -- iterative method
	public void preOrderIterative() {
		Stack<BinaryNode<T>> stack = new SeqStack<BinaryNode<T>>();
		BinaryNode<T> p = this.root;
		while (true) {
			preOrderIterative(p, stack);
			if (stack.isEmpty())
				break;
			p = stack.pop();
		}
	}
	private void preOrderIterative(BinaryNode<T> root, Stack<BinaryNode<T>> stack) {
		BinaryNode<T> p = root;
		while (p != null) {
			System.out.print(p.data.toString()+ " ");
			if (p.right != null) {
				stack.push(p.right);
			}
			p = p.left;
		}
	}
	
	// postOrder -- iterative method
	/*
	public void postOrderIterative() {
		Stack<BinaryNode<T>> stack = new SeqStack<BinaryNode<T>>();
		BinaryNode<T> p = this.root;
		if (p != null)
			stack.push(p);
		while (!stack.isEmpty()) {
			if (stack.peek().left != p && stack.peek().right != p) {
				postOrderIterative(stack.peek(), stack);
			}
			p = stack.pop();
			System.out.print(p.data.toString() + " ");
		}
	}
	*/
	/*
	private void postOrderIterative(BinaryNode<T> root, Stack<BinaryNode<T>> stack) {
		BinaryNode<T> p = root;
		while (p != null) {
			if (p.left != null) {
				if (p.right != null)
					stack.push(p.right);
				stack.push(p.left);
			} else if (p.right != null)
				stack.push(p.right);
			p = stack.peek();
		}
	}
	*/
	
	public void postOrderIterative() {
		this.postOrderIterative(this.root);
	}
	private void postOrderIterative(BinaryNode<T> root) {
		if (root == null)
			return;
		Stack<BinaryNode<T>> stack = new SeqStack<BinaryNode<T>>();
		BinaryNode<T> current = null;
		BinaryNode<T> pre = null;
		stack.push(root);
		while (!stack.isEmpty()) {
			current = stack.peek();
			if ((current.left == null && current.right == null) || (pre != null && (pre == current.left || pre == current.right))) {
				System.out.print(current.data + " ");
				stack.pop();
				pre = current;
			} else {
				if (current.right != null) {
					stack.push(current.right);
				}
				if (current.left != null) {
					stack.push(current.left);
				}
			}
		}
	}

	// print genlist expression of binary tree
	public void printGenList() {
		printGenList(this.root);
	}
	private void printGenList(BinaryNode<T> p) {
		if (p == null) 
			System.out.print("^");
		else {
			System.out.print(p.data.toString());
			if (p.left != null || p.right != null) {
				System.out.print("(");
				printGenList(p.left);
				System.out.print(",");
				printGenList(p.right);
				System.out.print(")");
			}
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
		System.out.println();
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
	
	private void levelOrder(BinaryNode<T> left, BinaryNode<T> right) {
		
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
	// return the node with key, if this tree does not contain such a node, return null
	public BinaryNode<T> search(T key) {
		// TODO Auto-generated method stub
		return search(this.root, key);
	}
	
	private BinaryNode<T> search(BinaryNode<T> p, T key) {
		if (p == null)
			return null;
		if (p.data == key)
			return p;
		BinaryNode<T> left = search(p.left, key);
		if (left != null)
			return left;
		return search(p.right, key);
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
		if (p.data.equals(key))
			return 1;
		int leftLevel = level(p.left, key);
		int rightLevel = level(p.right, key);
		if (leftLevel == 0 && rightLevel == 0)
			return 0;
		else {
			return (leftLevel > 0 ? leftLevel : rightLevel) + 1;
		}
			//return 1 + (leftLevel < rightLevel ? leftLevel : rightLevel);
	}
	
	public <T extends Comparable<? super T>> boolean isSorted(BinaryTree<T> tree) {	// �ж��Ƿ�Ϊ����������
		return isSorted(this.root);
	}

	private <T extends Comparable<? super T>> boolean isSorted(BinaryNode<T> root) {
		if (root == null)
			return true;
		if ((root.left == null || root.left != null && root.left.data.compareTo(root.data) < 0)
			 && (root.right == null || root.right != null && root.right.data.compareTo(root.data) > 0)) {
			return isSorted(root.left) && isSorted(root.right);
		}
		return false;
	}
}
