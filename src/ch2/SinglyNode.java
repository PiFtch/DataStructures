package ch2;
//	�������㣬����������data��ָ����next��ָ����һ�����
public class SinglyNode<T> {
		public T data;
		public SinglyNode<T> next;
		
		// ����ս��
		public SinglyNode() {
			this.data = null;
			this.next = null;
		}
		
		// ��������ָ�����
		public SinglyNode(T data, SinglyNode<T> next) {
			this.data = data;
			this.next = next;
		}
		
		// �����ַ���
		public String toString() {
			return this.data.toString();
		}
}
