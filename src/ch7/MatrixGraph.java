package ch7;

import ch4.LinkedQueue;
import ch5.Matrix;
import ch5.Triple;

public class MatrixGraph<T> extends AbstractGraph<T> {
	protected Matrix matrix;
	
	public MatrixGraph(int length) {
		super(length);
		this.matrix = new Matrix(length);
	}
	
	public MatrixGraph() {
		this(10);
	}
	
	public MatrixGraph(T[] vertices) {
		this(vertices.length);
		for (int i = 0; i < vertices.length; i++) {
			this.insertVertex(vertices[i]);
		}
	}
	
	public MatrixGraph(T[] vertices, Triple[] edges) {
		this(vertices);
		for (int j = 0; j < edges.length; j++)
			this.insertEdge(edges[j]);
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices.length; j++) {
				if (i != j && this.matrix.get(i, j) == 0)
					this.matrix.set(i, j, MAX_WEIGHT);
			}
		}
	}
	
	
	public String toString() {
		String str = super.toString() + "邻接矩阵: \n";
		int n = this.vertexCount();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (this.matrix.get(i, j) == MAX_WEIGHT)
					str += "     ∞";
				else
					str += String.format("%6d", this.matrix.get(i, j));
			str += "\n";
		}
		return str;
	}
	
	public void insertEdge(int i, int j, int weight) {
		if (i != j) {
			if (weight <= 0 || weight > MAX_WEIGHT)
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight);
		} else throw new IllegalArgumentException("不能插入自身环，i=" + i + "，j=" + j);
		
	}
	public void insertEdge(Triple edge) {
		this.insertEdge(edge.row, edge.column, edge.value);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] vertices = {"A", "B","C","D","E"};
		Triple[] edges = {new Triple(0, 1, 45), new Triple(0, 2, 28), new Triple(0, 3, 10),
						  new Triple(1, 0, 45), new Triple(1, 2, 12), new Triple(1, 4, 21),
						  new Triple(2, 0, 28), new Triple(2, 1, 12), new Triple(2, 3, 17), new Triple(2, 4, 26),
						  new Triple(3, 0, 10), new Triple(3, 2, 17), new Triple(3, 4, 15),
						  new Triple(4, 1, 21), new Triple(4, 2, 26), new Triple(4, 3, 15)};
		MatrixGraph<String> graph = new MatrixGraph<String>(vertices, edges);
		System.out.println(graph.toString());
		
		int i = graph.insertVertex(new String("F"));
		graph.insertEdge(3, i, 13);
		graph.insertEdge(new Triple(i, 3, 13));
		graph.insertEdge(4, i, 11);
		graph.insertEdge(new Triple(i, 4, 11));
		System.out.println(graph.toString());
		
		System.out.println("边<" + 3 + "," + 2 + ">的权为" + graph.weight(3, 2));
//		graph.removeVertex(5);
//		graph.removeEdge(3, 2);
//		System.out.println(graph.toString());
		graph.DFSTraverse(5);
		for (int i1 = 0; i1 < graph.vertexCount(); i1++)
			graph.BFSTraverse(i1);
		
		graph.minSpanTraverse();
		
		System.out.println("顶点3的出度为：" + graph.outdegree(3));
	}

	@Override
	public void setVertex(int i, T x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertVertex(T x) {
		// TODO Auto-generated method stub
		int i = this.vertexList.insert(x);
		if (i >= this.matrix.getRows())
			this.matrix.setRowsColumns(i+1, i+1);
		for (int j = 0; j < i; j++) {
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(i, j, MAX_WEIGHT);
		}
		
		return i;
	}

	@Override
	public void removeVertex(int i) {
		
		int n = this.vertexCount();
		if (i >= 0 && i < n) {
			this.vertexList.remove(i);			// remove vertex i from the vertex list
			for (int j = i + 1; j < n; j++) {		// move matrix lines below i up 1 line
				for (int k = 0; k < n; k++) {
					this.matrix.set(j - 1, k, this.matrix.get(j, k));
				}
			}
			for (int j = 0; j < n; j++)     	// move matrix colums after i 1 column forward
				for (int k = i + 1; k < n; k++)
					this.matrix.set(j, k - 1, this.matrix.get(j, k));
			this.matrix.setRowsColumns(n -1, n - 1);
		}
		else throw new IndexOutOfBoundsException("i = " + i);
	}

	@Override
	public int next(int i, int j) { 			// 返回vi在vj后的后继邻接结点序号；若j=-1，返回vi的第一个邻接结点序号
		// TODO Auto-generated method stub		// 若不存在后继邻接结点，返回-1
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && i != j)
			for (int k = j + 1; k < n; k++)
				if (this.matrix.get(i, k) > 0 && this.matrix.get(i, k) < MAX_WEIGHT)
					return k;
		return -1;
	}

	@Override
	public void insertEdge(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(int i, int j) {			// delete edge <vi, vj>
		// TODO Auto-generated method stub
		if (i != j)
			this.matrix.set(i, j, MAX_WEIGHT);
	}
	
	public void removeEdge(Triple edge) {
		this.removeEdge(edge.row, edge.column);
	}

	@Override
	public int weight(int i, int j) {
		// TODO Auto-generated method stub
		return this.matrix.get(i, j);
	}

	@Override
	public void DFSTraverse(int i) {		// 深度优先搜索遍历，从顶点vi出发
		// TODO Auto-generated method stub
		boolean[] visited = new boolean[this.vertexCount()];	// initialize with false
		int j = i;
		do {
			if (!visited[j]) {
				System.out.print("{");
				this.depthfs(j, visited);
				System.out.print("}");
			}
			j = (j + 1) % this.vertexCount();
		} while (j != i);
		System.out.println();
	}
	private void depthfs(int i, boolean[] visited) {		// 从i出发的一次深度优先搜索
		System.out.print(this.getVertex(i) + " ");
		visited[i] = true;
		int j = this.next(i, -1);		// i的第一个邻接结点
		while (j != -1) {					// /若i存在邻接结点
			if (!visited[j])
				depthfs(j, visited);
			j = this.next(i, j);
		}
	}

	@Override
	public void BFSTraverse(int i) {
		// TODO Auto-generated method stub
		boolean[] visited = new boolean[this.vertexCount()];
		int j = i;
		do {
			if (!visited[j]) {
				System.out.print("{");
				breadthfs(j, visited);
				System.out.print("}");
			}
			j = (j + 1) % this.vertexCount();
		} while (j != i);
		System.out.println();
	}
	private void breadthfs(int i, boolean[] visited) {
		System.out.print(this.getVertex(i) + " ");
		visited[i] = true;
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
		queue.add(i);
		while (!queue.isEmpty()) {
			i = queue.poll();
			for (int j = this.next(i, -1); j != -1; j = this.next(i, j)) {
				if (!visited[j]) {
					System.out.print(this.getVertex(j) + " ");
					visited[j] = true;
					queue.add(j);
				}
			}
		}
	}

	@Override
	public void minSpanTraverse() {
		// TODO Auto-generated method stub
		Triple[] mst = new Triple[this.vertexCount() - 1];		// 最小生成树的边集合，边数为顶点数-1
		for (int i = 0; i < mst.length; i++)
			mst[i] = new Triple(0, i + 1, this.weight(0, i + 1));	// 从结点V0到其他结点的边
		for (int i = 0; i < mst.length; i++) {						// 每轮循环确定一条权值最小的边
			int minWeight = mst[i].value, min = i;
			for (int j = i + 1; j <mst.length; j++)
				if (mst[j].value < minWeight) {
					minWeight = mst[i].value;
					min = j;
				}
			// 将权值最小的边交换到第i个元素，标识该边加入TE集合
			Triple edge = mst[min];
			mst[min] = mst[i];
			mst[i] = edge;
			// 将i+1~n-1的其他边用权值更小的边替换
			int tv = edge.column;
			for (int j = i+1; j < mst.length; j++) {
				int v = mst[j].column;
				int weight = this.weight(tv, v);
				if (weight < mst[j].value)
					mst[j] = new Triple(tv, v, weight);
			}
		}
		System.out.print("\n最小生成树的边集合: ");
		int mincost = 0;
		for (int i = 0; i < mst.length; i++) {
			System.out.print(mst[i] + " ");
			mincost += mst[i].value;
		}
		System.out.println(", 最小代价为" + mincost);
	}

	@Override
	public void shortestPath(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shortestPath() {
		// TODO Auto-generated method stub
		
	}
	
	public int outdegree(int i) {		// 返回顶点Vi的出度
		if ( i < 0 || i >= this.vertexCount())
			throw new IllegalArgumentException("Vertex index cannot go out of range [0, " + this.vertexCount() + ")");
		int count = 0;
		for (int j = 0; j < this.vertexCount(); j++)
			if (this.weight(i, j) < MAX_WEIGHT && this.weight(i, j) != 0)
				count++;
		
		return count;
	}
}


