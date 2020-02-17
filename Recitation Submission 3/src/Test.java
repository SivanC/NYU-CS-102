public class Test {
	public static void main(String[] args) {
		System.out.println(recursiveMaximum(new int[] {10, 6, 3, 8, 1, 1, 0}, 6, 0));
		System.out.println(sumEvenNegative(new int[] {-1, 2, 3, 5, 10}, 0, 0));
	}
	
	public static int recursiveMaximum(int[] arr, int index, int max) {
		if (index < 0) 
			return max;
		if (arr[index] > max)
			max = arr[index];
		return recursiveMaximum(arr, --index, max);
	}
	
	public static int sumEvenNegative(int[] arr, int index, int sum) {
		if (index > arr.length - 1)
			return sum;
		if (arr[index] % 2 == 0 || arr[index] < 0)
			sum += arr[index];
		return sumEvenNegative(arr, ++index, sum);
	}
}
