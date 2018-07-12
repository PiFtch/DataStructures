package ch9;

public class ShellSort {
	public static void sort(int[] A) {
		int d;
		for (d = A.length / 2; d >= 1; d = d / 2) {
			for (int i = d; i <A.length; i++) {
				int temp = A[i];
				int j = i - d;
				while (j >= 0 && A[j] > temp) {
					A[j+d] = A[j];
					j -= d;
				}
				A[j+d] = temp;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {88,96,123,58,64,1,23,54,42,25,69,789,265};
		ShellSort.sort(arr);
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}

}
