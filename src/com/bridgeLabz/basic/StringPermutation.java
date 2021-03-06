package com.bridgeLabz.basic;

import com.bridgeLabz.utility.Util;


public class StringPermutation {
	/**
	 * prints the all possible String permutations by shifting its characters.
	 * 
	 * @param charArray as the input parameter
	 * @param left      starting position of the character array.
	 * @param right     ending position of the character array.
	 */
	public void permutation(char[] charArray, int left, int right) {
		int start;
		/**
		 * when starting left position equalize with the ending right position it just
		 * print out the combinations of characters.
		 */
		if (left == right) {
			for (int i = 0; i < charArray.length; i++) {
				System.out.print(charArray[i]);
			}
			System.out.println();
		} else {
			/**
			 * this loop starts from starting characters and continues unless it reach
			 * ending character. inside the loop its recurssive when left equalize with
			 * right it prints the output.
			 */
			for (start = left; start < right; start++) {
				/**
				 * @code it swaps the character by calling swap function.
				 */
				swap(charArray, left, start);
				/**
				 * after printing all possible outcomes of charArray input again it calls the
				 * recurssive function itself by shifting its starting position to its next
				 * character. after finding all possible outcomes by fixing each characters.
				 */
				permutation(charArray, left + 1, right);
				/**
				 * This function is for back tracking to the initial string such that we can fix
				 * next characters and check for all possible outcomes.
				 */
				swap(charArray, left, start);
			}
		}
	}

	/**
	 * takes the input character array and returns after swapping.
	 * 
	 * @param arr as the parameter which is need to be swapped
	 * @param i   swapping position of the value
	 * @param j   swapping position of the value
	 */
	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * execution main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StringPermutation permutation = new StringPermutation();
		System.out.println("Enter the String to be permute : ");
		String s = Util.scanner.nextLine();
		Util.scanner.close();
		char[] ch = s.toCharArray();
		System.out.println("Permutations are :");
		permutation.permutation(ch, 0, ch.length);
	}
}