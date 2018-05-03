
package ch4;
import ch2.SeqList;

public final class SeqStack<T> implements Stack<T> {
	private SeqList<T> list;
	// ���췽��
	public SeqStack(int length) {		// ��������length�Ŀ�ջ
		this.list = new SeqList<T>(length);
	}
	public SeqStack() {					// ����Ĭ�������Ŀ�ջ
		this(64);
	}
	
	// �ж�ջ�Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	// ��ջ��β����
	public void push(T x) {
		this.list.insert(x);
	}
	
	// ����ջ��Ԫ��
	public T peek() {
		return this.list.get(list.size() - 1);
	}
	
	// ��ջ
	public T pop() {
		return this.list.remove(list.size() - 1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
