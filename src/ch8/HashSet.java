package ch8;

import ch2.SinglyList;
import ch2.SinglyNode;

public class HashSet<T> {
	private SinglyList<T>[] table;		// ɢ�б�ͬ��ʵ�������������
	private	int count = 0;				// Ԫ�ظ���
	private static final float LOAD_FANCTOR = 0.75f;	// װ������
	public HashSet(int length) {		// ��������Ϊlength��ɢ�б�
		if (length < 10)
			length = 10;
		this.table = new SinglyList[length];
		for (int i = 0; i < length; i++)
			this.table[i] = new SinglyList<T>();		// ����յ�����
	}
	
	public HashSet() {
		this(16);
	}
	
	public HashSet(T[] values) {		// ��values�ṩԪ�أ�����ɢ�б�
		this((int)(values.length / LOAD_FANCTOR) + 1);
		this.addAll(values);
	}
	
	// ɢ�к���������ؼ���ΪxԪ�ص�ɢ�е�ַ,��x==null���׳��ն����쳣
	private int hash(T x) {
		int key = Math.abs(x.hashCode());
		return key % this.table.length;
	}
	
	public T search(T key) {			// ���ز��ҵ��Ĺؼ���Ϊkey��Ԫ�أ�����ʧ�ܷ���null
		SinglyNode<T> find = this.table[this.hash(key)].search(key);	// �ҵ���Ӧ�ĵ������ڱ��в���
		return find == null ? null : find.data;
	}
	
	public boolean add(T x) {			// ����xԪ��
		if (this.count >= this.table.length * LOAD_FANCTOR) {	// ����
			SinglyList<T>[] temp = this.table;
			this.table = new SinglyList[this.table.length * 2];
			for (int i = 0; i < this.table.length; i++)
				this.table[i] = new SinglyList<T>();
			this.count = 0;
			for (int i = 0; i < temp.length; i++)
				for (SinglyNode<T> p = temp[i].head.next; p != null; p = p.next)
					this.add(p.data);
		}
		boolean insert = this.table[this.hash(x)].insertDiferent(x) != null;
		if (insert)												// ���벻�ظ�Ԫ��
			this.count++;
		return insert;
	}
	
	public T remove(T key) {			// ɾ���ؼ���ΪkeyԪ�أ����ر�ɾ��Ԫ�أ�����ʧ�ܷ���null
		T x = (T) this.table[this.hash(key)].remove(key);
		if (x != null)
			this.count--;
		return x;
	}
	
	public boolean contains(T key) {
		return this.table[this.hash(key)].contain(key);
	}
	
	public int size() {			// Ԫ�ظ���
		return this.count;
	}
	
	public boolean isEmpty() {
		return this.count == 0;
	}
	
	public void addAll(T[] values) {
		for (int i = 0; i < values.length; i++) {
			this.add(values[i]);
//			count++;
		}
	}
	
	public void clear() {		// ɾ������Ԫ��
		for (int i = 0; i < this.table.length; i++)
			this.table[i].clear();
	}
	
	public String toString() {
		String str = "Hash Key: \n";
		for (int i = 0; i < this.table.length; i++) {
			str = str + " " + i + " : ";
			str += this.table[i].toString();
			str += "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] integers = {11, 25, 5, 122, 34, 15, 67, 24, 139, 1, 556};
        HashSet<Integer> hashSet = new HashSet<Integer>(integers);
        System.out.println(hashSet);
        hashSet.add(293);
        System.out.println("After hashSet.add(293) : ");
        System.out.println(hashSet);
        System.out.println("hashSet.isEmpty() = " + hashSet.isEmpty());
        System.out.println("hashSet.size() = " + hashSet.size());
        System.out.println("hashSet.contains(122) = " + hashSet.contains(122));
        System.out.println("hashSet.contains(902) = " + hashSet.contains(902));
        hashSet.remove(12);
        System.out.println("After hashSet.remove(12) : ");
        System.out.println(hashSet);
        System.out.println("hashSet.search(12) = " + hashSet.search(12));
        System.out.println("hashSet.search(67) = " + hashSet.search(67));
        hashSet.addAll(new Integer[] {7, 4, 9});
        System.out.println("After hashSet.addAll(new Integer[] {3, 4, 5}) : ");
        System.out.println(hashSet);
        hashSet.clear();
        System.out.println("After hashSet.clear()");
        System.out.println(hashSet);
	}

}
