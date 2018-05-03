package ch3;

public class MyString implements Comparable<MyString>, java.io.Serializable {
	private final char[] value;
	//private Object SinglyList;
	
	public MyString() {		//构造空串
		this.value = new char[0];
	}
	
	public MyString(java.lang.String str) {		// 由字符串常量构造串
		this.value = new char[str.length()];
		for (int i = 0; i < this.value.length; i++) {
			this.value[i] = str.charAt(i);
		}
	}
	
	//以value数组从i开始的n个字符构造串，i>=0,n>=0, i+n<=value.length
	//若i与n指定序号越界，抛出字符串序号越界异常
	public MyString(char[] value, int i, int n) {
		if (i >= 0 && n >= 0 && i+n <= value.length) {
			this.value = new char[n];
			for (int j = 0; j < n; j++)
				this.value[j] = value[i+j];
		} else
			throw new StringIndexOutOfBoundsException("i="+i+", n="+n+", i+n="+(i+n));
	}
	
	public MyString(char[] value) {			//以字符数组构造串
		this(value, 0, value.length);
	}
	
	public MyString(MyString str) {			//深拷贝
		this(str.value);
	}
	
	public int length() {					// 返回串长度
		return this.value.length;
	}
	
	public java.lang.String toString() {
		return new String(this.value);
	}
	
	public char charAt(int i) {				// 返回第i个字符
		if (i >= 0 && i < this.value.length)
			return this.value[i];
		throw new StringIndexOutOfBoundsException(i);
	}
	
	@Override
	public int compareTo(MyString str) {
		// TODO Auto-generated method stub
		int i, min;
		if (str.value.length <= this.value.length)
			min = str.value.length;
		else 
			min = this.value.length;
		
		for (i = 0; i < min; i++) {
			if (this.value[i] != str.value[i])
				return this.value[i] - str.value[i];
		}
		if (str.value.length == this.value.length)
			return 0;
		else
			return this.value.length - str.value.length;
	}

	// 返回序号从begin至end-1的子串，0<=begin<=end-1<length
	public MyString substring(int begin, int end) {
		return new MyString(this.value, begin, end-begin);
	}
	// 返回从begin至串尾的子串
	public MyString substring(int begin) {
		return substring(begin, this.value.length);
	}
	
	// 连接串
	// 返回this与str连接生成的串，若str == null，则插入"null"
	public MyString concat(MyString str) {
		if (str == null) {
			str = new MyString("null");
		}
		char[] buffer = new char[this.value.length + str.length()];
		int i;
		for (i = 0; i < this.value.length; i++)
			buffer[i] = this.value[i];
		for (int j = 0; j < str.value.length; j++)
			buffer[i + j] = str.value[j];
		return new MyString(buffer);			// 以字符数组构造串对象
	}
	
	// 返回当前串删除所有空格的字符串
	public MyString trim() {
		// buffer用来存储去掉空格的字符数组
		char[] buffer = new char[this.value.length];
		System.out.println("buffer size: " + buffer.length);
		int i, j;
		for (i = 0, j = 0; i < this.value.length; i++) {
			if (this.value[i] != ' ') {
				buffer[j] = this.value[i];
				j++;
			}
		}
		return new MyString(buffer, 0, j);
	}
	
	// 比较串相等

	// 插入串
	// 在i处插入str
	public MyString insert(int i, MyString str) {
		/*
		MyString temp = this.substring(0, i);
		temp.concat(str);
		temp.concat(this.substring(i));
		return temp;
		*/
		MyString s1 = this.substring(0, i);
		MyString s2 = this.substring(i);
		return s1.concat(str).concat(s2);
	}
	// 统计串中各字符出现次数
//	public void countChar() {
//		char[] ch = new char[this.value.length];
//		int[] num;
//		
//		if (this.value.length == 0)
//			return;
//		else
//			ch[0] = this.value[0];
//		
//		for (int i = 1; i < this.value.length; i++) {
//			
//		}
		
/*		if (this.value.length == 0)
			return;
		else
			SinglyList<char> ch = new SinglyNode(this.value[0], null);
		
	} */
	
	public int countChar(char ch) {
		int num = 0;
		for (int i = 0; i < this.value.length; i++) {
			if (this.value[i] == ch)
				num++;
		}
		return num;
	}
	
	public boolean equals(MyString str) {
		if (this.value.length != str.value.length)
			return false;
		int i = 0;
		while (i < this.value.length) {
			if (this.value[i] != str.value[i])
				return false;
			i++;
		}
		if (i == this.value.length)
			return true;
		return false;
	}
	
	public int contains(char ch) {	// 返回ch的位置，查找失败返回-1
		for (int i = 0; i < this.value.length; i++)
			if (this.value[i] == ch)
				return i;
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyString str1 = new MyString("abc");
		MyString str2 = new MyString("abc de  f g");
		MyString str3 = new MyString(str2.value, 3, 2);
		System.out.println(str1.compareTo(str1));
		System.out.println(str1.compareTo(str3));
		System.out.println(str2.trim());
		System.out.println(str2.trim().insert(1, str1));
	}


}
