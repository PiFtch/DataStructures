package ch8;

public class TriNode<T> {
	public T data;
	public TriNode<T> parent, left, right;
	
	public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public TriNode(T data) {
		this(data, null, null, null);
	}
	
	public TriNode() {
		this(null, null, null, null);
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
