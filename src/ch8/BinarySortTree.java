package ch8;

public class BinarySortTree<T extends Comparable<? super T>> {
	public TriNode<T> root;
	public BinarySortTree() {
		this.root = null;
	}
	public BinarySortTree(T[] values) {
		this();
		for (int i = 0; i < values.length; i++)
			this.add(values[i]);
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public TriNode<T> searchNode(T key) {
		TriNode<T> p = this.root;
		while (p != null && key.compareTo(p.data) != 0)
			if (key.compareTo(p.data) < 0)
				p = p.left;
			else
				p = p.right;
		return p != null ? p : null;
	}
	
	public T search(T key) {
		TriNode<T> find = this.searchNode(key);
		return find != null ? find.data : null;
	}
	
	public boolean add(T x) {
		if (this.root == null)
			this.root = new TriNode(x);
		else {
			TriNode<T> p = this.root, parent = null;
			while ( p != null) {
				if (x.compareTo(p.data) == 0)
					return false;		// 重复元素，返回false
				parent = p;
				if (x.compareTo(p.data) < 0)
					p = p.left;
				else
					p = p.right;
			}
			if (x.compareTo(parent.data) < 0)
				parent.left = new TriNode(x,parent, null, null);
			else
				parent.right = new TriNode(x,parent, null, null);
		}
		return true;
	}
	public String toString() {		// inorder traverse
		String str = "[";
		TriNode<T> p = this.first(this.root);
		while (p != null) {
			str += p.data.toString() + " ";
			p = this.next(p);
		}
		return str + "]";
	}
	public TriNode<T> first(TriNode<T> root) {		// return the first successor of subtree in inorder traverse
		if (root != null)
			while(root.left != null)
				root = root.left;
		return root;
	}
	public TriNode<T> next(TriNode<T> p) {			// return successor of p in inorder traverse
		if (p != null) {
			if (p.right != null)
				return this.first(p.right);
			while (p.parent != null) {
				if (p.parent.left == p)
					return p.parent;
				p = p.parent;
			}
		}
		return null;
	}

	public void inorderPrevious() {		 // inorder traverse
		inorderPrevious(this.root);
	}
	private void inorderPrevious(TriNode<T> p) {
		if (p != null) {
			inorderPrevious(p.right);
			System.out.print(p.data.toString() + " ");
			inorderPrevious(p.left);
		} else
			return;
	}
	
	public TriNode<T> last(TriNode<T> p) {		// 返回以p为根的子树中根次序最后一个访问节点
		if (p != null)
			while (p.right != null)
				p = p.right;
		return p;
	}
	
	public TriNode<T> previous(TriNode<T> p) {		// 返回p在中根次序下的前驱
		if (p.left != null) {
			return this.last(p.left);
		}
		return null;
	}
	
	public void addAll(T[] values) {				// insert all elements in array values
		for (int i = 0; i < values.length; i++)
			this.add(values[i]);
	}
	
	public boolean contains(T key) {
		if (this.searchNode(key) == null)
			return false;
		return true;
	}
	
	public int size() {
		return size(this.root);
	}
	private int size(TriNode<T> p) {
		int count = 0;
		if (p != null) {
			count++;
			count += size(p.left) + size(p.right);
		}
		return count;
	}
	
	public void clear() {
		this.root = null;
	}
	
	public T remove(T key) {		// 删除关键字为key结点，返回被删除结点；没找到则返回null
		TriNode<T> p = this.searchNode(key);
		if (p != null && p.left != null && p.right != null) {		// case 1 : p is a 2-degree node, replace it with its successor
			TriNode<T> insucc = this.first(p.right);
			T temp = p.data;										// successor is a 1-degree or leaf node
			p.data = insucc.data;
			insucc.data = temp;
			p = insucc;
		}
		if (p != null && p == this.root) {							// case 2 : p is a 1-degree or leaf node						
			if (this.root.left != null)		
				this.root = p.left;
			else
				this.root = p.right;
			if (this.root != null)
				this.root.parent = null;							// delete new root's parent
			return p.data;
		}
		if (p != null && p == p.parent.left) {
			if (p.left != null) {
				p.parent.left = p.left;
				p.left.parent = p.parent;
			}
			else {
				p.parent.left = p.right;
				if (p.right != null)
					p.right.parent = p.parent;
			}
		}
		if (p != null && p == p.parent.right) {
			if (p.left != null) {
				p.parent.right = p.left;
				p.left.parent = p.parent;
			}
			else {
				p.parent.right = p.right;
				if (p.right != null)
					p.right.parent = p.parent;
			}
		}
		return p != null ? p.data : null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		Integer[] integers = {122, 908, 234, 8, 45, 13};
        BinarySortTree binarySortTree = new BinarySortTree(integers);
        System.out.println("binarySortTree = " + binarySortTree.toString());
        binarySortTree.inorderPrevious();
        System.out.println();
        System.out.println("binarySortTree.isEmpty() = " + binarySortTree.isEmpty());
        System.out.println("previous node of 122 in inorder traverse: " + binarySortTree.previous(binarySortTree.searchNode(122)));
        System.out.println("number of elements: " + binarySortTree.size());
        
        System.out.println("binarySortTree.searchNode(908) = " + binarySortTree.searchNode(908));
        System.out.println("binarySortTree.search(908) = " + binarySortTree.search(908));
        System.out.println("inorderPrevious : ");

        System.out.println("binarySortTree.last(binarySortTree.root) = " + binarySortTree.last(binarySortTree.root));
        System.out.println("binarySortTree.root = " + binarySortTree.root);

        binarySortTree.addAll(new Integer[] {59, 687, 26});
        System.out.println("After binarySortTree.addAll(new Integer[] {59, 687, 26}) : ");
        System.out.println("binarySortTree = " + binarySortTree);
        System.out.println("binarySortTree.contains(9) = " + binarySortTree.contains(9));
        System.out.println("binarySortTree.contains(687) = " + binarySortTree.contains(687));
        System.out.println("binarySortTree.size() = " + binarySortTree.size());
        binarySortTree.clear();
        System.out.println("After binarySortTree.clear() : ");
        System.out.println("binarySortTree = " + binarySortTree);
	}

}
