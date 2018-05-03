package ch5;

public class GenNode<T> {
	public T data;
	public GenList<T> child;
	public GenNode<T> next;
	public GenNode() {
		this.data = null;
		this.child = null;
		this.next = null;
	}
	
	public GenNode(T data) {
		this.data = data;
		this.child = null;
		this.next = null;
	}
	
	public GenNode(T data, GenList<T> child, GenNode<T> next) {
		this.data = data;
		this.child = child;
		this.next = next;
	}
	
	public String toString() {
//		return this.data.toString();
		String str;
		if (this.data != null)
			str = this.data.toString();
		else
			str = this.child.toString();
		return str;
	}
	
}
