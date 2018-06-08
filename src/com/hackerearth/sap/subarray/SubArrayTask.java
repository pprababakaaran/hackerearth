package com.hackerearth.sap.subarray;

import java.util.Scanner;

public class SubArrayTask {

	@SuppressWarnings("resource")
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		// System.out.println("Enter the number of test cases");

		int numberOfTest = scanner.nextInt();
		Scanner sc = new Scanner(System.in);

		for (int test = 0; test < numberOfTest; test++) {
			// System.out.println("Enter the number of elements in array");
			int numberOfElements = scanner.nextInt();
			// System.out.println("Enter the value for array " +
			// numberOfElements);
			String arrStr = sc.nextLine();
			String[] arrVal = arrStr.split(" ");
			Integer arr[] = new Integer[numberOfElements];

			for (int i = 0; i < arr.length; i++) {

				try {
					if (arrVal[i].isEmpty() || arrVal[i] == null) {
						arr[i] = null;
					} else {
						arr[i] = Integer.parseInt(arrVal[i]);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					arr[i] = null;
				}
				// System.out.println(arr[i]);
			}

			int cnt = 0;
			int conseq = 1;
			for (int i = 0; i < arr.length - 1; i++) {
				if (i == arr.length - 2) {
					if (arr[i] == arr[i + 1] && arr[i] == null) {
						break;
					}
					if (arr[i] == arr[i + 1]) {
						conseq++;
						cnt = cnt + (conseq * (conseq + 1) / 2);
					} else if (arr[i] != null && arr[i + 1] != null && (arr[i] != arr[i + 1])) {
						cnt++;
						cnt++;
					} else {
						cnt++;
					}
					break;

				} else if (arr[i] != null) {
					if (arr[i] == arr[i + 1]) {
						conseq++;
					} else {
						if (conseq == 1) {
							cnt++;
						} else {
							cnt = cnt + (conseq * (conseq + 1) / 2);
							conseq = 1;
						}
					}
				} else {
					continue;
				}
			}
			System.out.println(cnt);
		}

	}

}
