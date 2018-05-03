package ch2;
//import javax.swing.plaf.synth.SynthStyle;

public class SinglyList<T> {
	public SinglyNode<T> head;
	//******************************************构造方法******************************
	// 构造空单链表
	public SinglyList() {
		this.head = new SinglyNode<T>();
	}
	
	// 由数组构造单链表
	public SinglyList(T[] values) {
		this();							// 先构造空单链表，有一个头结点
		SinglyNode<T> p = this.head;
		for (int i = 0; i < values.length; i++) {
			p.next = new SinglyNode<T>(values[i], null);
			p = p.next;
		}
	}
	
	// 深拷贝单链表
	public SinglyList(SinglyList<T> list) {
		this();
		SinglyNode<T> pthis = this.head;
		SinglyNode<T> plist = list.head.next;
		while (plist != null) {
			pthis.next = new SinglyNode<T>(plist.data, plist.next);
			plist = plist.next;
			pthis = pthis.next;
		}
	}
	//*******************************************************************************
	// 判断单链表是否为空
	public boolean isEmpty() {
		return this.head.next == null;
	}
	
	// **************************************** 存取 ******************************************
	// 返回第i个元素，若i越界，返回null
	public T get(int i) {
		SinglyNode<T> p = this.head.next;
		for (int j = 0; p != null && j < i; j++)
			p = p.next;
		return (i >= 0 && p != null) ? p.data : null;
	}
	
	// 插入x作为第i个结点，返回插入结点
	// i<0，插入在最前，i>n，插入在最后
	public SinglyNode<T> insert(int i, T x) {
		if (x == null)
			throw new NullPointerException("x == null");
		SinglyNode<T> front = this.head;
		for (int j = 0; front.next != null && j < i; j++)
			front = front.next;
		front.next = new SinglyNode<T>(x, front.next);
		return front.next;
	}
	//****************************************删除******************************************
	// 删除
	public T remove(int i) {
		SinglyNode<T> front = this.head;
		for (int j = 0; front.next != null && j < i; j++)
			front = front.next;
		if (i >= 0 && front.next!= null) {
			T old = front.next.data;
			front.next = front.next.next;
			return old;
		}
		return null;
	}
	
	// 清空
	public void clear() {
		this.head.next = null;
	}
		
	
	//****************************************查找*******************************************
	//顺序查找和基于查找算法的操作
	public SinglyNode<T> search(T key) {
		SinglyNode<T> p = this.head.next;
		while (p != null && p.data != key)
			p = p.next;
		if (p.data == key)
			return p;
		return null;
	}
	
	// 判断是否包含关键字为key的元素
	public boolean contain(T key) {
		SinglyNode<T> p = this.head.next;
		while (p != null) {
			if (p.data == key)
				return true;
			p = p.next;
		}
		return false;
	}
	
	//插入不重复元素，查找不成功，尾插入
	public SinglyNode<T> insertDiferent(T x) {
		SinglyNode<T> p = this.head;
		while (p.next != null) {
			if (p.next.data == x)
				return p.next;
			p = p.next;
		}
		p.next = new SinglyNode<T>(x, null);
		return p.next;
	}
	
	//删除首个与key相等元素，返回被删除元素，查找不成功返回null
	public SinglyNode<T> remove(T key) {
		SinglyNode<T> front = this.head;
		SinglyNode<T> p;
		while (front.next != null) {
			if (front.next.data == key) {
				p = front.next;
				front.next = p.next;
				return p;
			}
			front = front.next;
		}
		return null;
	}
	
	//比较相等   2-1
//	public boolean equals(SinglyList<T> list) {
//		SinglyNode<T> pthis = this.head;
//		SinglyNode<T> plist = list.head;
//		while (pthis.next != null && plist.next != null) {
//			if (pthis.next.data == plist.next.data) {
//				pthis = pthis.next;
//				plist = plist.next;
//			} else {
//				return false;
//			}
//		}
//		if (pthis.next == null && plist.next == null)
//			return true;
//		return false;
//	}
	
	//**********************************************************************************************
	//**********************************************************************************************
	//lab
	//2.1 比较相等
	public boolean equals(Object obj) {
		if (!(obj instanceof SinglyList<?>))
			return false;
		SinglyList<T> list = (SinglyList<T>)obj;
		SinglyNode<T> pthis = this.head;
		SinglyNode<T> plist = list.head;
		while (pthis.next != null && plist.next != null) {
			if (pthis.next.data == plist.next.data) {
				pthis = pthis.next;
				plist = plist.next;
			} else {
				return false;
			}
		}
		if (pthis.next == null && plist.next == null)
			return true;
		return false;
	}

	//2.2返回子表SinglyList<T> subList(begin, end)
	// 若begin>end或begin<0,返回null;		若begin,end有出界，返回界内的一部分子表，若没有界内部分，返回null
	public SinglyList<T> subList(int begin, int end) {		// 返回由begin到end的子表
		if (begin > end || begin < 0)									
			return null;
		SinglyNode<T> p = this.head;
		for (int i = 0; p.next != null && i < begin; i++) {
			p = p.next;
		}	// exit loop when i == begin, p refers to pos i-1
		if (p.next == null) {		// exit loop when p.next == null, p doesn't have a successor
			return null;
		}
		// when pos begin is not empty
		SinglyList<T> list = new SinglyList<T>();		// list to return
		SinglyNode<T> plist = list.head;
		for (int j = begin; p.next != null && j < end; j++) {
			plist.next = new SinglyNode<T>(p.next.data, p.next.next);		//init: p.next refers to pos begin
			plist = plist.next;
			p = p.next;
		}	// exit loop when p refers to the last element (or j == end while p refers to pos end-1, plist refers to pos end-1)
		if (p.next != null) {
			plist.next = new SinglyNode<T>(p.next.data, null);
		}
		return list;
	}
	
	//2-3  删除子表SinglyList<T> remove(begin, end)
	// 0 <= begin <= end 	
	//if begin > end or begin < 0 then return null;
	//if begin and end exceeds position bounds then return the part in original list
	public SinglyList<T> remove(int begin, int end) {
		if (begin > end || begin < 0)
			return null;
		SinglyNode<T> p = this.head;
		// locate begin position
		for (int i = 0; p.next != null && i < begin; i++) {
			p = p.next;
		}
		if (p.next == null) // begin exceed bound
			return null;
		// p refers to pos begin-1
		SinglyNode<T> pend = p;
		for (int j = begin; pend.next != null && j <= end; j++) {	// init: pend.next refers to pos begin
			pend = pend.next;			
		}
		// pend refers to end or the last element
		p.next = pend.next;
		return this;
	}
	
	
	//***********************************************************************************************************
	//***********************************************************************************************************
	
	public String toString() {
		SinglyNode<T> p = this.head.next;
		String str = this.getClass().getName() + "(";
		while (p != null) {
			str += p.toString() + (p.next == null ? ")" : ",");
			p = p.next;
		}
		return str;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] values = {1,2,3,4,5};
		SinglyList<Integer> list1 = new SinglyList<>(values);
		System.out.println(list1.toString());
		SinglyList<Integer> list2 = new SinglyList<>(list1);
		System.out.println(list2.toString());
//		System.out.println(list1.toString());
//		System.out.println(list1.get(0).toString());
//		System.out.println(list1.remove(0).toString());
//		System.out.println(list1.toString());
//		System.out.println(list2.toString());
//		System.out.println(list1.search(2).toString());
//		System.out.println(list1.contain(1));
//		System.out.println(list1.insertDiferent(1));
//		System.out.println(list1.remove((Integer)5).toString());
//		System.out.println(list1.toString());
//		System.out.println(list2.toString());
//		list1 = new SinglyList<>(list2);
//		System.out.println(list1.toString());
//	
		System.out.println(list2.equals(list1));
		
//		list2 = list1.subList(1, 3);
		list2 = list1.remove(3, 5);
		System.out.println(list2);
		System.out.println(list1);
	}

}
