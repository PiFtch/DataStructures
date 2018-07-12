package ch9;

public class BinaryInsertionSort {
	
	public static void sort(int[] arr, int n) {
		int i, j, low, high, mid;
		int temp;
		for (i = 1; i < n; i++) {
			temp = arr[i];
			low = 0; high = i - 1;
			// 查找插入位置
			while (low <= high) {
				mid = (low + high) / 2;
				if (arr[mid] > temp)
					high = mid - 1;
				else
					low = mid + 1;
			}
			// 移动元素并插入
			for (j = i - 1; j >= low; j--)
				arr[j+1] = arr[j];
			arr[low] = temp;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {7,5,89,54,12,56,23,3};
		BinaryInsertionSort.sort(arr, arr.length);
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}

}
