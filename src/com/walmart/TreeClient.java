package com.walmart;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class TreeClient {

	public static int weight = 0;
	private static Scanner scanner;
	private static Scanner scanner1;

	static class NodeInput {
		int start;
		int end;
		int people;

		public NodeInput(int start, int end, int people) {
			super();
			this.start = start;
			this.end = end;
			this.people = people;
		}
	}

	public static int getRootElement(List<Integer> starts, List<Integer> ends) {
		int unique = 0;
		for (Integer endNode : ends) {
			if (!starts.contains(endNode)) {
				unique = endNode;
			}
		}
		return unique;
	}

	public static List<Integer> getLeafElements(List<Integer> starts, List<Integer> ends) {
		List<Integer> substracts = new LinkedList<Integer>();
		for (Integer startNode : starts) {
			if (!ends.contains(startNode)) {
				substracts.add(startNode);
			}
		}
		return substracts;
	}

	public static void getPathRecursive(List<Integer> starts, List<Integer> ends, int nodeVal, int root,
			List<Integer> path) {
		if (starts.contains(nodeVal)) {
			path.add(nodeVal);
			int startindex = starts.indexOf(nodeVal);
			int endVal = ends.get(startindex);
			if (starts.contains(endVal) && endVal != root) {
				getPathRecursive(starts, ends, endVal, root, path);
			} else {
				path.add(root);
			}
		}
	}

	public static List<List<Integer>> getPossiblePath(List<Integer> starts, List<Integer> ends, List<Integer> leafs,
			int root) {
		List<List<Integer>> paths = new LinkedList<List<Integer>>();
		for (Integer leaf : leafs) {
			List<Integer> path = new LinkedList<Integer>();
			getPathRecursive(starts, ends, leaf, root, path);
			paths.add(path);
		}
		return paths;
	}

	public static void calWeight(List<NodeInput> nodes, List<List<Integer>> paths, List<Integer> path,
			List<List<Integer>> iterPath) {
		List<NodeInput> filteredNode = nodes.stream().filter(x -> x.start == path.get(0) && x.end == path.get(1))
				.collect(Collectors.toList());
		if (filteredNode.size() > 0) {
			NodeInput curPath = filteredNode.get(0);
			iterPath.add(path);
			weight = weight + curPath.people;

			for (List<Integer> tempPath : paths) {
				if (path != tempPath && tempPath.get(1) == curPath.end && !iterPath.contains(tempPath)) {
					iterPath.add(tempPath);
					calWeight(nodes, paths, tempPath, iterPath);
				}
			}
		}
	}

	public static Map<List<Integer>, Integer> getWeights(List<NodeInput> nodes, List<List<Integer>> paths) {
		Map<List<Integer>, Integer> weights = new HashMap<List<Integer>, Integer>();
		for (List<Integer> path : paths) {
			weight = 1;
			List<List<Integer>> iterPath = new LinkedList<List<Integer>>();
			calWeight(nodes, paths, path, iterPath);
			weights.put(path, weight);
			weight = 0;

		}
		return weights;
	}

	public static void main(String args[]) {
		scanner = new Scanner(System.in);
		scanner.nextInt();
		int numberOfQueues = scanner.nextInt();
		List<Integer> startPoints = new LinkedList<Integer>();
		List<Integer> endPoints = new LinkedList<Integer>();
		List<Integer> people = new LinkedList<Integer>();

		scanner1 = new Scanner(System.in);
		List<NodeInput> nodeInputs = new LinkedList<NodeInput>();

		for (int i = 0; i < numberOfQueues; i++) {

			String inputArray = scanner1.nextLine();
			String[] arrVal = inputArray.split(" ");
			startPoints.add(Integer.parseInt(arrVal[0]));
			endPoints.add(Integer.parseInt(arrVal[1]));
			people.add(Integer.parseInt(arrVal[2]));
			NodeInput input = new NodeInput(Integer.parseInt(arrVal[0]), Integer.parseInt(arrVal[1]),
					Integer.parseInt(arrVal[2]));
			nodeInputs.add(input);
		}

		int root = TreeClient.getRootElement(startPoints, endPoints);
		List<Integer> leafs = TreeClient.getLeafElements(startPoints, endPoints);
		List<List<Integer>> paths = getPossiblePath(startPoints, endPoints, leafs, root);
		Map<List<Integer>, Integer> weightMap = getWeights(nodeInputs, paths);
		List<Integer> key = new LinkedList<Integer>();
		int val = 0;
		for (Map.Entry<List<Integer>, Integer> entry : weightMap.entrySet()) {
			List<Integer> tempKey = entry.getKey();
			Integer tempVal = entry.getValue();
			int weight = tempVal*key.size();
			if(val ==0 || weight < val){
				val = weight;
				key = tempKey;
			} 
		}
		
		System.out.println(val);
		StringBuffer minimalPath = new StringBuffer();
		for(int i =0;i<key.size();i++){
			minimalPath.append(key.get(i));
			if(i!=key.size()-1){
				minimalPath.append(" -> ");
			}
		}
		System.out.println(minimalPath);
	}
}
