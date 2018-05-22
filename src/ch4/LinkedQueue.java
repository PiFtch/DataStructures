package ch4;
import ch2.SinglyNode;
public class LinkedQueue<T> implements Queue<T> {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private SinglyNode<T> front, rear;
	public LinkedQueue() {
		this.front = this.rear = null;
	}
	
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.front == null && this.rear == null;
	}
	

	@Override
	public boolean add(T x) {
		// TODO Auto-generated method stub
		if (x == null)
			return false;
		SinglyNode<T> p = new SinglyNode<T>(x, null);
		if (this.front == null)
			this.front = p;
		else 
			this.rear.next = p;
		this.rear = p;
		return true;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return this.isEmpty() ? null : this.front.data;
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		T x = this.front.data;
		this.front = this.front.next;
		if (this.front == null)
			this.rear = null;
		return x;
	}

}
