
package ch4;
import ch2.SeqList;

public final class SeqStack<T> implements Stack<T> {
	private SeqList<T> list;
	// 构造方法
	public SeqStack(int length) {		// 构造容量length的空栈
		this.list = new SeqList<T>(length);
	}
	public SeqStack() {					// 构造默认容量的空栈
		this(64);
	}
	
	// 判断栈是否为空
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	// 入栈，尾插入
	public void push(T x) {
		this.list.insert(x);
	}
	
	// 返回栈顶元素
	public T peek() {
		return this.list.get(list.size() - 1);
	}
	
	// 出栈
	public T pop() {
		return list.remo
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
