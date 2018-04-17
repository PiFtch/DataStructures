package ch4;
import ch2.SinglyList;
public class LinkedStack<T> implements Stack<T> {
	
	private SinglyList<T> list;
	
	// ���췽��
	public LinkedStack() {
		this.list = new SinglyList<T>();
	}
	
	// �ж�ջ�Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	// ��ջ
	public void push(T x) {
		this.list.insert(0, x);			// ������ͷ����Ԫ��x
	}
	
	// ��ջ������ջ��Ԫ�أ���ջ�գ�����null
	public T pop() {
		return this.list.remove(0);
	}
	
	// ����ջ��Ԫ�أ� ��ջ�գ�����null
	public T peek() {
		return this.list.get(0);
	}
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	*/
}
