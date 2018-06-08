package com.walmart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class NodeConnection {
	private int start;
	private int end;
	private int people;

	public NodeConnection(int start, int end, int people) {
		super();
		this.start = start;
		this.end = end;
		this.people = people;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}
}

public class MinimalPathClient {

	public static int getRootElements(Integer[] a1, Integer[] b1) {
		int unique = 0;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < a1.length; j++) {
				if (b1[i] != a1[j]) {
					unique = b1[i];
				}
			}
		}
		return unique;
	}

	public static List<Integer> getLeafElements(Integer[] a1, Integer[] b1) {
		List<Integer> substracts = new LinkedList<Integer>();
		List<Integer> b = Arrays.asList(b1);
		for (int i = 0; i < a1.length; i++) {
			if (!b.contains(a1[i])) {
				substracts.add(a1[i]);
			}
		}
		return substracts;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of junction");
		int numberOfJunctions = scanner.nextInt();

		System.out.println("Enter number of Queues");
		int numberOfQueues = scanner.nextInt();
		Integer[] startPoints = new Integer[numberOfQueues];
		Integer[] endPoints = new Integer[numberOfQueues];
		Integer[] people = new Integer[numberOfQueues];

		System.out.println("Enter start, end and people");
		Scanner scanner1 = new Scanner(System.in);
		List<NodeConnection> nodes = new ArrayList<NodeConnection>();

		for (int i = 0; i < numberOfQueues; i++) {
			String inputArray = scanner1.nextLine();
			String[] arrVal = inputArray.split(" ");
			NodeConnection node = new NodeConnection(Integer.parseInt(arrVal[0]), Integer.parseInt(arrVal[1]),
					Integer.parseInt(arrVal[2]));
			startPoints[i] = Integer.parseInt(arrVal[0]);
			endPoints[i] = Integer.parseInt(arrVal[1]);
			people[i] = Integer.parseInt(arrVal[2]);
			nodes.add(node);
		}

		int root = MinimalPathClient.getRootElements(startPoints, endPoints);
		// System.out.println(root);

		List<Integer> leafElements = MinimalPathClient.getLeafElements(startPoints, endPoints);

		// System.out.println(leafElements.toString());

		for (Integer node : leafElements) {
			List<NodeConnection> nodeObj = nodes.stream().filter(p -> p.getStart() == node)
					.collect(Collectors.toList());
			System.out.println(nodeObj.get(0).getEnd());
		}

	}
}
