package ch7;
import ch2.SeqList;

public abstract class AbstractGraph<T> implements GraphInterface<T>{
	protected static final int MAX_WEIGHT = 0x0000ffff;
	protected SeqList<T> vertexList;
	
	public AbstractGraph (int length) {
		this.vertexList = new SeqList<T>(length);
	}
	
	public AbstractGraph() {
		this(10);
	}
	
	public int vertexCount() {
		return this.vertexList.size();
	}
	
	public String toString() {
		return "¶¥µã¼¯ºÏ: " + this.vertexList.toString() + "\n";
	}
	
	public T getVertex(int i) {
		// TODO Auto-generated method stub
		return this.vertexList.get(i);
	}
	
	public void setVerTex(int i, T x) {
		this.vertexList.set(i, x);
	}
	
}
