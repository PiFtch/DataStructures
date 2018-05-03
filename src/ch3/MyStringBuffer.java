package ch3;

public final class MyStringBuffer {
	private char[] value;
	private int n;					// Length of string
	
	// Constructor
	public MyStringBuffer(int capacity) {
		this.value = new char[capacity];
		this.n= 0;
	}
	
	public MyStringBuffer() {
		this.value = new char[16];
		this.n = 0;
	}
	
	public MyStringBuffer(String str) {
		this.value = new char[str.length() + 16];
		this.n= str.length();
		for (int i = 0; i < this.n; i++) {
			this.value[i] = str.charAt(i);
		}
	}
	
	// Return length of string
	public int length() {
		return this.n;
	}
	
	// Return capacity of char array value
	public int capacity() {
		return this.value.length;
	}
	
	// ��value����0��n����String��
	public String toString() {
		return new String(this.value, 0, this.n);
	}
	
	// ��i������str��, 0 <= i < length(). iԽ�磬�׳��쳣
	public MyStringBuffer insert(int i, String str) {
		if (this.n == 0 && i == 0 || this.n > 0 && i >= 0 && i <= this.n) {
			if (str == null)
				str = "null";
			char[] temp = this.value;
			if (this.value.length < this.n + str.length()) {
				this.value = new char[(this.value.length + str.length())*2];
				for (int j = 0; j < i; j++)
					this.value[j] = temp[j];
			}
			for (int j = this.n - 1; j >= i; j--) {
				this.value[j + str.length()] = temp[j];
			}
			for (int j = 0; j < str.length(); j++) {
				this.value[i + j] = str.charAt(j);
			}
			this.n += str.length();
			return this;
		} else {
			throw new StringIndexOutOfBoundsException("i="+i);
		}
	}
	
	public MyStringBuffer insert(int i, MyStringBuffer sbuf) {
		if (this.n == 0 && i == 0 || this.n > 0 && i >= 0 && i <= this.n) {
			if (sbuf == null)
				sbuf = new MyStringBuffer("null");
			char[] temp = this.value;
			if (this.value.length < this.n + sbuf.n) {
				this.value = new char[(this.value.length + sbuf.n)*2];
				for (int j = 0; j < i; j++) {
					this.value[j] = temp[j];
				}
			}
			for (int j = this.n - 1; j >= i; j--) {
				this.value[j + sbuf.n] = temp[j];
			}
			for (int j = 0; j < sbuf.n; j++) {
				this.value[j + i] = sbuf.value[j];
			}
			this.n += sbuf.n;
			return this;
		} else {
			throw new IndexOutOfBoundsException("i="+i);
		}
	}
	
	public MyStringBuffer insert(int i, boolean b) {
		return this.insert(i, b ? "true" : "false");
	}
	
	public MyStringBuffer append(String str) {
		return this.insert(this.n, str);
	}
	
	// ɾ��begin��end-1���Ӵ�
	public MyStringBuffer delete(int begin, int end) {
		if (begin >= 0 && begin < this.n && end >= 0 && begin <= end) {
			if (end > this.n)
				end = this.n;
			for (int i = begin; i < end; i++) {
				this.value[i] = this.value[i + (end - begin)];
			}
			this.n -= (end - begin);
			return this;
		} else
			throw new StringIndexOutOfBoundsException("begin="+begin+", end=" + end + "end-begin=" + (end-begin));
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyStringBuffer sbuf1 = new MyStringBuffer("hello");
		MyStringBuffer sbuf2 = new MyStringBuffer("world");
		System.out.println(sbuf1.append(" ").append("world").insert(2, sbuf2));
		System.out.println(sbuf1);
	}

}
