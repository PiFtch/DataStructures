package ch2;

public class DoublyNode<T> {
	public T data;
	public DoublyNode<T> prev, next;
	
	public DoublyNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public DoublyNode(T data, DoublyNode<T> prev, DoublyNode<T> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	
	public DoublyNode(T data) {
		this.data = data;
		this.prev = this.next = null;
	}
	
//	public DoublyNode(SinglyNode<T> node) {
//		this.data = node.data;
//		this.prev = null;
//		this
//	}
	
	public String toString() {
		return this.data.toString();
	}
}
