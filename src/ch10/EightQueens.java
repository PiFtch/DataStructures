package ch10;
import ch4.SeqStack;
import java.util.*;

public class EightQueens {
	int[][] chessBoard;
	int n;
	long count = 0;
	
	public EightQueens(int n) {
		this.chessBoard = new int[n][n];
		this.n = n;
	}
	// **************************************************************************
	public void recursive() {
		placeQueen(0);
		System.out.println(n + " Queens: Number of solutions: " + count);
	}
	private void placeQueen(int row) {
		if (row >= this.n) {
			this.count++;
//			showChessBoard();
		}
		else {
			for (int col = 0; col < this.n; col++) {
				this.chessBoard[row][col] = 1;
				if (!this.conflict(row, col))
					placeQueen(row + 1);
				this.chessBoard[row][col] = 0;
			}
		}
	}
	// ************************************************************************
	public void iterative() {
		iterativeSolution();
		System.out.println(this.n + " Queens: Number of solutions: " + this.count);
	}
	private void iterativeSolution() {
		SeqStack<Position> stack = new SeqStack<Position>(n);
		// ������ջ�м���Ԫ�أ���һ��DFS·��
		for (int row = 0; row < n; row++) {	
			boolean colFound = false;
			for (int col = 0; col < n; col++) {
				if (!conflict(row, col)) {
					stack.push(new Position(row, col));
					chessBoard[row][col] = 1;
					colFound = true;
					break;
				}
			}
			if (!colFound)
				break;
		}
		
		while (!stack.isEmpty()) {
			Position pos = stack.pop();
			if (pos.row == n - 1)
				count++;
			// �ָ���posλ�÷��ûʺ�֮ǰ���ֳ�
			chessBoard[pos.row][pos.col] = 0;
			// �ڸ��ֳ�posλ��֮����Ѱ�Һ��ʵ�λ��
			boolean found = false;
			for (int c = pos.col + 1; c < n; c++) {
				if (!conflict(pos.row, c)) {
					chessBoard[pos.row][c] = 1;
					stack.push(new Position(pos.row, c));
					found = true;
					break;
				}
			}
			// ���ֳ�row�ҵ�����һ�����ʵ�λ��
			if (found) {
				for (int row = pos.row + 1; row < n; row++) {
					boolean colFound = false;
					for (int col = 0; col < n; col++) {
						if (!conflict(row, col)) {
							stack.push(new Position(row, col));
							chessBoard[row][col] = 1;
							colFound = true;
							break;
						}
					}
					if (!colFound)
						break;
				}
			} 
		} 
	}
	// ************************************************************************
	public void probability() {
		while (!LV()) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					chessBoard[i][j] = 0;
		}
		this.showChessBoard();
	}

	private boolean LV() {
		Random r = new Random();
//		int nextQueen = 0;	// �ȴ����õ���һ���ʺ�
		int[] availableColumns = new int[this.n];	// һ���п��Է��ûʺ����
		int rowAvailableQueenCount = 1;		// һ���п��Է��õĻʺ���
		for (int row = 0; row < this.n && rowAvailableQueenCount > 0; row++) {
			rowAvailableQueenCount = 0;
			for (int col = 0; col < this.n; col++) {	// �ҵ�һ�������п��Է��ûʺ����
				if (!conflict(row, col)) {
					availableColumns[rowAvailableQueenCount++] = col;
				}
			}
			if (rowAvailableQueenCount > 0) {	// ������λ�÷��ûʺ�
				this.chessBoard[row][availableColumns[r.nextInt(rowAvailableQueenCount)]] = 1;		// �ڴ��з��ûʺ�
			}
		}
		
		return rowAvailableQueenCount > 0;
	}
	// ************************************************************************
	private boolean conflict(int row, int col) {
		// (row,col) is the place to be tested whether it is in conflict with Queens placed before
		for (int i = 0; i < row; i++) {
			if (this.chessBoard[i][col] == 1)
				return true;
			int d = row - i;
			if (col - d >= 0 && chessBoard[i][col-d] == 1)
				return true;
			if (col + d < this.n && chessBoard[i][col+d] == 1)
				return true;
		}
		return false;
	}
	public void showChessBoard() {
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++)
				System.out.print(chessBoard[row][col] + "  ");
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for (int i = 4; i < 18; i++) {
//			EightQueens ex = new EightQueens(i);
//			long t = System.currentTimeMillis();
//			ex.recursive();
//			System.out.println(i + " recursive: " + (System.currentTimeMillis() - t));
//		}
//		
//		for (int i = 4; i < 18; i++) {
//			EightQueens ex = new EightQueens(i);
//			long t = System.currentTimeMillis();
//			ex.iterative();
//			System.out.println(i + " iterative: " + (System.currentTimeMillis() - t));
//		}

		EightQueens example3 = new EightQueens(8);
		example3.probability();
	}
}
