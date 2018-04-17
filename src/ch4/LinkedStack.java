package ch4;
import ch2.SinglyList;
public class LinkedStack<T> implements Stack<T> {
	
	private SinglyList<T> list;
	
	// 构造方法
	public LinkedStack() {
		this.list = new SinglyList<T>();
	}
	
	// 判断栈是否为空
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	// 入栈
	public void push(T x) {
		this.list.insert(0, x);			// 单链表头插入元素x
	}
	
	// 出栈，返回栈顶元素，若栈空，返回null
	public T pop() {
		return this.list.remove(0);
	}
	
	// 返回栈顶元素， 若栈空，返回null
	public T peek() {
		return this.list.get(0);
	}
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	*/
}
