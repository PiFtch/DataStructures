package ch4;

public interface Stack<T> {
	public abstract boolean isEmpty();		// �ж�ջ�Ƿ�Ϊ��
	public abstract void push(T x);			// ѹ��ջ
	public abstract T peek();				// ����ջ��Ԫ��
	public abstract T pop();				// ����ջ
}
