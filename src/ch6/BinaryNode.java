package ch6;

public class BinaryNode<T extends Comparable<? super T>> {
	public T data;
	public BinaryNode<T> left, right;
	
	public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public BinaryNode(T data) {
		this.data = data;
		this.left = this.right = null;
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
