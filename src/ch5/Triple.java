package ch5;

public class Triple implements Comparable<Triple>{
	public int row, column, value;
	
	public Triple(int row, int column, int value) {
		if (row >= 0 && column >= 0) {
			this.row = row;
			this.column = column;
			this.value = value;
		} else throw new IllegalArgumentException();
	}
	
	public Triple(Triple tri) {
		this(tri.row, tri.column, tri.value);
	}
	
	public String toString() {
		return "(" + row +"," + column + "," + value + ")";
	}
	
	public int compareTo(Triple tri) {
		if (this.row == tri.row && this.column == tri.column)
			return 0;
		return (this.row < tri.row | this.row == tri.row && this.column < tri.column)? -1 : 1;
	}
	
	public boolean equals(Triple tri) {
		if (this.compareTo(tri) == 0 && this.value == tri.value)
			return true;
		return false;
	}
	
	public void add(Triple item) {
		if (this.compareTo(item) == 0)
			this.value += item.value;
		else throw new IllegalArgumentException();
	}
	
	public boolean removable() {
		return this.value == 0;
	}
	
	public Triple toSymmetry() {
		return new Triple(this.column, this.row, value);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
