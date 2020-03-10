import java.lang.Math;
import java.util.Arrays;

public class Homework2 {
	public static void main(String[] args) {
		// System.out.println("Check reversed: " + checkReversed("dat A sTructU res", "ser uTCUrtsa   tad"));
		// System.out.println("Shortest strings: " + shortestStrings(new String[] {"I", "want", "to", "love", "hippopotamus", "and also", "data", "work", "yeah"}));
		// System.out.println("Binary max: " + binaryMax(new int[] {2,5,12,13,45,23,1,79,80}, 0, 8));
		// System.out.println("Least zeroes: " + numZeroes(0));
		// System.out.println("Is palindrome: " + isPalindrome("sTA...$#r!rats"));
		
		int[] toSort = new int[] {4, 77, 98, 30, 20, 50, 77, 22, 49, 2}; // Array from the homework assignment
		// bubbleSort(toSort);
		// bubbleSortRecursive(toSort, 0);
		// selectionSort(toSort);
		// insertionSort(toSort);
		// System.out.println(Arrays.toString(mergeSort(toSort, 0, 9)));
	}
	
	/**
	 * Checks if a string is a reversed copy of another string.
	 * 
	 * As the number of characters in a and b, n, approaches infinity, 
	 * the number of operations approaches n, aka the running time is O(n), best case k (if a and b are not of equal length).
	 * This is because, as the comments outline, the for loop is the only line that adds a variable amount of operations, proportional to n.
	 * @param a The first string.
	 * @param b The second string.
	 * @return True if the strings are reversed copies, false otherwise.
	 */
	public static int checkReversed(String a, String b) {
		// remove whitespace and uppercase (not counting these two towards algorithm operation count because they aren't necessary to check reversal)
		a = a.replace(" ", "").toLowerCase();
		b = b.replace(" ", "").toLowerCase();
		
		if (a.length() != b.length()) return 0; // +k operations
		
		for (int i = 0; i < a.length(); i++) { // +kn operations
			if (!a.substring(i, i+1).equals(b.substring(b.length() - (i+1), b.length() - i)))
				return 0;
		} return 1; // +1 operation
	}
	
	/**
	 * Given an array of strings, picks the shortest from each group of three and appends it to an array.
	 * 
	 * Summing the operations noted in the comments below, as the number of strings n approaches infinity,
	 * the number of operations approaches n, aka the running time is O(n), best case n. This is because, as the comments outline,
	 * There is only one loop, and it depends on the amount of elements n in the array
	 * @param strings An array of strings such that strings.length() % 3 == 0.
	 * @return The array of shortest strings.
	 */
	public static String shortestStrings(String[] strings) {
		String[] returnStrings = new String[strings.length / 3];  // +k operations
		
		for (int i = 0; i < strings.length; i += 3) { // +kn operations (none of the interior operations require loops).
			String a = strings[i];
			String b = strings[i + 1];
			String c = strings[i + 2];
			
			int l = Math.min(Math.min(a.length(), b.length()), c.length());
			
			if (l == a.length())
				returnStrings[i/3] = a;
			else if (l == b.length())
				returnStrings[i/3] = b;
			else if (l == c.length())
				returnStrings[i/3] = c;
		} return Arrays.toString(returnStrings); // + 1 operations
	}
	
	/**
	 * Finds the maximal integer in an array of integers using binary recursion.
	 * @param arr An array of integers.
	 * @param start The first index to start searching from.
	 * @param end The last index to stop searching at.
	 * @return The largest integer in the array.
	 */
	public static int binaryMax(int[] arr, int start, int end) {
		if (start == end) return arr[start];
		int mid = (start + end) / 2;
		return Math.max(binaryMax(arr, start, mid), binaryMax(arr, mid + 1, end));
	}
	
	/**
	 * Calculates the least number of zeroes in the binary representation of a given number.
	 * While, if you call this, the function is technically not 100% recursive, that is only for the sake of abstraction and simplicity of use. 
	 * The user is not required to calculate the inputs for themself, although they may avail themself of numZeroes(int, int, int, int): int should they so choose,
	 * which is fully recursive.
	 * @param n A nonnegative integer.
	 * @return The calculated least number of zeroes.
	 */
	public static int numZeroes(int n) {
		// Had to make zero a special case, but every other nonnegative number that can fit inside int works
		if (n == 0) return 1;
		int p = (int) (Math.log(n)/Math.log(2)); // The exponent of the nearest power of 2 to n on the low end, aka floor(log2(n)).
		int t = p; // t will eventually represent the least number of zeroes in a binary representation of n. Starts at p and is decremented from there.
		
		return numZeroes(n, p, t, 0); // Plug the calculated inputs into the more complex function
	}
	
	/**
	 * Calculates the least number of zeroes in the binary representation of a given number.
	 * 
	 * This function requires several variables with complex relationships to be tracked between calls, and it is recommended to use the simpler numZeroes(int): int to call this function.
	 * It is highly recommended to view the document for an explanation of how these numbers are calculated.
	 * @param n A nonnegative integer.
	 * @param p The exponent of the nearest power of 2 to n on the low end, aka floor(log2(n)).
	 * @param t When count == ndim, represents the least number of zeroes in the binary representation of n. When calling this function, set t = p.
	 * @param count Starts at 0, indicates current recursion depth.
	 * @return The calculated least number of zeroes.
	 */
	public static int numZeroes(int n, int p, int t, int count) {
		int ndim = (int) (Math.ceil(p/2.)); // The number of times the program should run in order to get to the bottom of the tree.
		
		if (count >= ndim) // if we reached maximum recursion depth (>= because 2, for example, would yield the wrong answer otherwise).
			return t;
		
		// Had to make zero a special case, but every other nonnegative number that can fit inside int works
		if (n == 0 && count == 0) {
			return 1;
		}
		
		// See document for an in depth explanation of how these numbers are calculated.
		n = (int) (n % (Math.pow(2, p-2*count))); // calculating the input number's position (i.e. 74 will yield 74 % 64 = 10)
		double pos = n / Math.pow(2, p-2*count); // representing the position as a fraction of the total (i.e. 10/64 will yield 0.15625)
		
		// if the number's lower nearest power of 2 is odd and this is the first pass
		if (p % 2 == 1 && count == 0) {
			if (0 <= pos && pos < 0.5) { // if it's in the first half of the tree
				return numZeroes(n, p-1, t, ++count);}
			else if (0.5 <= pos && pos <= 1) // if it's in the second half of the tree
				return numZeroes(n, p-1, --t, ++count);
		}
		
		// Calls numZeroes with the appropriate inputs based on the value of pos, 0  <= pos <= 1. 
		if (0 <= pos && pos < 0.25)
			return numZeroes(n, p, t, ++count);
		else if (0.25 <= pos && pos < 0.75)
			return numZeroes(n, p, --t, ++count);
		else if (0.75 <= pos && pos <= 1)
			return numZeroes(n, p, t-2, ++count);
		// If for some reason pos is not between 0 and 1 (which is an error)
		else
			return -1;
	}
	
	/**
	 * Checks whether a string is a palindrome, ignoring punctuation and capitalization.
	 * @param string The string to be checked.
	 * @return True if the string is a palindrome, false otherwise.
	 */
	public static boolean isPalindrome(String string) {
		// remove punctuation and uppercase
		string = string.replaceAll("[^a-zA-Z ]", "").toLowerCase();
		// base case
		if (string.length() == 1)
			return true;
		// if the first and last character match
		else if (string.substring(0, 1).equals(string.substring(string.length()-1, string.length())))
			// if the length is 2 its a palindrome
			if (string.length() == 2)
				return true;
			// otherwise continue checking
			else
				return isPalindrome(string.substring(1, string.length()-1));
		// if it doesn't match then it isn't a palindrome
		return false;
	}
		
	/**
	 * Swaps two integers in an array using a two-variable swap.
	 * @param index1 The index of the first value.
	 * @param index2 The index of the second value.
	 * @param arr An integer array.
	 * @return The updated integer array.
	 */
	public static int[] swapVal(int index1, int index2, int[] arr) {
		int a = arr[index1];
		int b = arr[index2];
		a += b;
		b = a - b;
		a -= b;
		arr[index1] = a;
		arr[index2] = b;
		return arr;
	}
	
	/**
	 * Bubble sort implementation. Worst case O(n^2) operations as n -> infinity, best case n.
	 * @param arr An unsorted array.
	 * @return A sorted array.
	 */
	public static int[] bubbleSort(int[] arr) {
		System.out.println("Sorting array using non-recursive bubble sort...");
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j+1]) {
					arr = swapVal(j, j+1, arr);
					System.out.println(Arrays.toString(arr));
				}
			}
		} return arr;
	}
	
	/**
	 * Recursive bubble sort implementation. Worst case O(n^2) operations as n -> infinity, best case n.
	 * @param arr An unsorted array.
	 * @param index Indicates base case for recursion. Starts at 0.
	 * @return A sorted array.
	 */
	public static int[] bubbleSortRecursive(int[] arr, int index) {
		if (index == arr.length - 1)
			return arr;
		for (int i = 0; i < arr.length - i - 1; i++) {
			if (arr[i] > arr[i+1]) {
				arr = swapVal(i, i+1, arr);
				System.out.println(Arrays.toString(arr));
			}
		} return bubbleSortRecursive(arr, index + 1);
	}
	
	/**
	 * Implements selection sort. Worst case O(n^2) operations as n -> infinity, best case n^2.
	 * @param arr An unsorted array.
	 * @return A sorted array.
	 */
	public static int[] selectionSort(int[] arr) {
		System.out.println("Sorting array using selection sort...");
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				System.out.println(Arrays.toString(arr));
				if (arr[i] > arr[j]) {
					arr = swapVal(i, j, arr);
				}
			}
		} return arr;
	}
	
	/**
	 * Implements insertion sort. Worst case O(n^2) operations as n -> infinity, best case n.
	 * @param arr An unsorted array.
	 * @return A sorted array.
	 */
	public static int[] insertionSort(int[] arr) {
		System.out.println("Sorting array using insertion sort...");
		System.out.println(Arrays.toString(arr));
		int index = 2;
		for (int i = 0; i < arr.length-1; i++) {
			if (arr[i] > arr[i+1]) {
				arr = swapVal(i, i+1, arr);
				System.out.println(Arrays.toString(arr));
				for (int j = index; j > 0; j--) {
					if (arr[j] < arr[j-1]) {
						arr = swapVal(j, j-1, arr);
						System.out.println(Arrays.toString(arr));
					}
				} index++;
			}
		} return arr;
	}

	public static int[] merge(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;
		
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				c[k] = a[i];
				i++;
			} else {
				c[k] = b[j];
				j++;
			} k++;
		} while (i < a.length) {
			c[k] = a[i];
			i++;
			k++;
		} while (j < b.length) {
			c[k] = b[j];
			j++;
			k++;
		} return c;
	}
	
	public static int[] mergeSort(int[] arr, int start, int end) {
		if (start >= end) return new int[] {arr[start]};
		int mid = (start + end) / 2;
		return merge(mergeSort(arr, start, mid), mergeSort(arr, mid + 1, end));
	}
	
	public static int countOnes(int n) {
		if (n == 0) return 0;
		else if (n == 1) return 1;
		else if (n % 2 == 1) return 1 + countOnes(n/2);
		else return countOnes(n/2);
	}
	
	public static void partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		int i = start - 1;
		
		for (int j = 0; j < end-1; j++) {
			if (arr[j] < pivot) {
				i++;
				swapVal(i, j, arr);
			}
		} swapVal(i+1, end, arr);
	}
}
