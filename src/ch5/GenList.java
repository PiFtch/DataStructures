package ch5;

public class GenList<T> {
	public GenNode<T> head;
	// Constructor
	public GenList() {
		this.head = new GenNode<T>();
	}
	
	public GenList(T[] atoms) {
		this.head = new GenNode<T>();
		GenNode<T> p = this.head;
		for (int i = 0; i < atoms.length; i++) {
			p.next = new GenNode<T>(atoms[i]);
			p = p.next;
		}
	}
	
	public boolean isEmpty() {
		return this.head.next == null ? true : false;
	}
	public GenNode<T> insert(int i, T x) {
		GenNode<T> p = this.head;
		int length = this.size();
		if (i < 0)
			i = 0;
		else if (i >= length) {
			i = length;
		}
			
		for (int j = 0; j < i; j++) {
			p = p.next;
		}
		p.next = new GenNode<T>(x, null, p.next);
		return p.next;
	}
	
	public void insert(int i, GenList<T> list) {
		GenNode<T> p = this.head;
		int length = this.size();
		if (i < 0)
			i = 0;
		else if (i >= length)
			i = length;
		
		for (int j = 0; j < i; j++)
			p = p.next;
//		p.next.child = list;
		p.next = new GenNode<T>(null, list, p.next);
	}
	public int size() {
		GenNode<T> p = this.head;
		int count = 0;
		while (p.next != null) {
			count++;
			p = p.next;
		}
		return count;
	}
	public String toString() {
		return this.toString("");
	}
	
	public String toString(String str) {
		str += "(";
		for (GenNode<T> p = this.head.next; p != null; p = p.next) {
			if (p.child == null)
				str += p.data.toString();
			else
				str += p.child.toString();
			if (p.next != null)
				str += ",";
		}
		return str + ")";
	}
	
	public int depth() {
		// 空表情况
		if (this.head.next == null)
			return 1;
		// 表非空
		GenNode<T> p = this.head.next;
		int max = 0;
		while (p != null) {
			int temp = 0;
			if (p.child != null)
				temp = p.child.depth();
			if (temp > max)
				max = temp;
			p = p.next;
		}
		return max + 1;
	}
	
	public void delete(int i) {
		if (i < 0 || i >= this.size())
			throw new IndexOutOfBoundsException();
		GenNode<T> p = this.head;
		for (int j = 0; j < i; j++) {
			p = p.next;
		}
		p.next = p.next.next;
	}
	
	public GenNode<T> search(T key) {
		GenNode<T> p = this.head;
		while (p.next != null) {
			if (p.next.data == key)
				return p.next;
			else
				p = p.next;
		}
		return null;
	}
	
	public boolean equals(GenList<T> list) {
		GenNode<T> pthis = this.head;
		GenNode<T> plist = list.head;
		if (this.size() != list.size())
			return false;
		if (this.depth() != list.depth())
			return false;
		if (this.isEmpty() && list.isEmpty())
			return true;
		
		
		while (pthis.next != null) {
			if (plist.next == null)
				return false;
			if (pthis.next.data == null && plist.next.data == null) {
				if (pthis.next.child != null && plist.next.child != null) {
					System.out.println(pthis.next.child);
					if (pthis.next.child.equals(plist.next.child)) {
						pthis = pthis.next;
						plist = plist.next;
					} else {
						return false;
					}
				} else if (pthis.next.child == null && plist.next.child == null) {
					pthis = pthis.next;
					plist = plist.next;
				} else {
					return false;
				}
			} else if (pthis.next.data.equals(plist.next.data)) {
				pthis = pthis.next;
				plist = plist.next;
			} else {
				System.out.println(pthis.next.data + " " + plist.next.data);
				return false;
			}
		}
		if (pthis.next == null && plist.next == null)
			return true;
		else
			return false;
	}
	
	public GenList<T> copy() {
		GenNode<T> pthis = this.head;
		GenList<T> des = new GenList<T>();
		GenNode<T> pdes = des.head;
		
		while (pthis.next != null) {
			pdes.next = new GenNode<T>(pthis.next.data, pthis.next.child, pthis.next.next);
			pthis = pthis.next;
			pdes = pdes.next;
		}
		
		return des;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] values = {1,2,3,4,5,6};
		GenList<Integer> list = new GenList<Integer>(values);
		GenList<Integer> list1 = new GenList<Integer>(values);
//		list.insert(3, 7);
//		System.out.println(list.toString());
//		list.delete(3);
//		System.out.println(list.toString());
		list.insert(2, list1);
		System.out.println(list.toString());
		System.out.println(list.depth());
		System.out.println(list.search(5));
		GenList<Integer> list2 = new GenList<Integer>(values);
		GenList<Integer> list3 = new GenList<Integer>(values);
		list2.insert(2, list3);
		System.out.println(list2);
		System.out.println(list.equals(list2));
		Integer x = new Integer(10);
		list.insert(2, x);
		GenList<Integer> des = list.copy();
		System.out.println(des);
		x++;
		System.out.println(list);
		System.out.println(des);

	}

}
