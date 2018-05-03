package ch2;
//	单链表结点，包含数据域data和指针域next，指向下一个结点
public class SinglyNode<T> {
		public T data;
		public SinglyNode<T> next;
		
		// 构造空结点
		public SinglyNode() {
			this.data = null;
			this.next = null;
		}
		
		// 构造数据指定结点
		public SinglyNode(T data, SinglyNode<T> next) {
			this.data = data;
			this.next = next;
		}
		
		// 描述字符串
		public String toString() {
			return this.data.toString();
		}
}


