package ch4;

public interface Stack<T> {
	public abstract boolean isEmpty();		// ÅĞ¶ÏÕ»ÊÇ·ñÎª¿Õ
	public abstract void push(T x);			// Ñ¹ÈëÕ»
	public abstract T peek();				// ·µ»ØÕ»¶¥ÔªËØ
	public abstract T pop();				// µ¯³öÕ»
}
