package ch7;

public interface GraphInterface<T> {
	/**
	 * return the number of vertex
	 */
	int vertexCount();
	
	/**
	 * return vertex Vi
	 */
	T getVertex(int i);
	
	/**
	 * set vertex Vi as x
	 */
	void setVertex(int i, T x);
	
	/**
	 * insert a vertex with value x, return index
	 */
	int insertVertex(T x);
	
	/**
	 * delete Vi and its edges
	 */
	void removeVertex(int i);
	
	
	int next(int i, int j);
	
	
	void insertEdge(int i, int j);
	
	
	void removeEdge(int i, int j);
	
	
	int weight(int i, int j);
	
	
	void DFSTraverse(int i);
	
	
	void BFSTraverse(int i);
	
	
	void minSpanTraverse();
	
	
	void shortestPath(int i);
	
	
	void shortestPath();
}
