package ch2;

public class CirDoublyList<T> {
	public DoublyNode<T> head;
	
	// 构造方法
	public CirDoublyList() {
		this.head = new DoublyNode<T>();
		this.head.prev = this.head;
		this.head.next = this.head;
	}
	
//	public CirDoublyList(SinglyList<T> list) {
//		this.head = new DoublyNode(null, null, list.head.next);
//	}
	
	public CirDoublyList(T[] values) {
		this.head = new DoublyNode<T>();
		DoublyNode<T> p = this.head;
		for (int i = 0; i < values.length; i++) {
			p.next = new DoublyNode<T>(values[i]);
			p.next.prev = p;
			p = p.next;
		}
		p.next = this.head;
	}
	
	public String toString() {
		DoublyNode<T> p = this.head.next;
		String str = this.getClass().getName() + "(";
		while (p != this.head) {
			str += p.toString() + (p.next == this.head ? ")" : ",");
			p = p.next;
		}
		return str;
	}
	//***********************************************************************************************
	//子集contains(CirDoublyList<T> list)
	public boolean contains(CirDoublyList<T> list) {
		if (list.head.next == list.head)
			return false;
		DoublyNode<T> pthis = this.head.next,pthisOut = this.head.next;
		DoublyNode<T> plist = list.head.next;
		while (pthisOut != this.head) {
			pthis = pthisOut;
			if (pthis.data == list.head.next.data) {
				while (plist.next != list.head) {
					if (plist.next.data == pthis.next.data) {
						plist = plist.next;
						pthis = pthis.next;
					} else break;
				}
				if (plist.next == list.head)
					return true;
			}
			
			
			pthisOut = pthisOut.next;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] values = {1,2,3,4,5,6};
		CirDoublyList<Integer> list1 = new CirDoublyList<Integer>(values);
		System.out.println(list1.toString());
		Integer[] att = {1,2,3,4,5,6,7};
		CirDoublyList<Integer> list2 = new CirDoublyList<Integer>(att);
		System.out.println(list1.contains(list2));
	}

}
