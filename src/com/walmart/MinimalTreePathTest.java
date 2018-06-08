package com.walmart;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Tree
{
	int start;
	int end;
	int people;
	
	public Tree(int start, int end, int people) {
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

class TreeTravel
{
	int time;
	String path;
	
	public TreeTravel(int time, String path) {
		super();
		this.time = time;
		this.path = path;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}

class MinimalTreePathTest {
	

	private static Scanner scanner;
	private static Scanner scanner1;

	public static int getRootElements(List<Integer> a1, List<Integer> b1) {
		int unique = 0;
		for (int i = 0; i < b1.size(); i++) {
			for (int j = 0; j < a1.size(); j++) {
				if (b1.get(i) != a1.get(j)) {
					unique = b1.get(i);
				}
			}
		}
		return unique;
	}

	public static List<Integer> getLeafElements(List<Integer> a1, List<Integer> b1) {
		List<Integer> substracts = new LinkedList<Integer>();
		for (int i = 0; i < a1.size(); i++) {
			if (!b1.contains(a1.get(i))) {
				substracts.add(a1.get(i));
			}
		}
		return substracts;
	}
	
	 public static int other_Node_Weight_Calculation(List<Tree> itemsref, int v_start, int v_end)
     {
         int temp_queue_count=0;
         List<Tree> travelPath = itemsref.stream().filter(i -> i.end == v_end && i.start != v_start).collect(Collectors.toList());
         while (travelPath.size() > 0)
         {
             temp_queue_count = temp_queue_count + travelPath.stream().mapToInt(t->t.people).sum();
             List<Integer> nodes = travelPath.stream().map(t->t.start).collect(Collectors.toList());
             travelPath = itemsref.stream().filter(i-> nodes.contains(i.end)).collect(Collectors.toList());
         }
         return temp_queue_count;

     }    

	public static void main(String args[]) {
        List<TreeTravel> items_weight = new LinkedList<TreeTravel>();
        List<Tree> items = new LinkedList<Tree>();
        
        scanner = new Scanner(System.in);
		int numberOfJunctions = scanner.nextInt();

		int numberOfQueues = numberOfJunctions-1;
		numberOfQueues = scanner.nextInt();
		
		scanner1 = new Scanner(System.in);

		for (int i = 0; i < numberOfQueues; i++) {
			String inputArray = scanner1.nextLine();
			String[] arrVal = inputArray.split(" ");
			Tree node = new Tree(Integer.parseInt(arrVal[0]), Integer.parseInt(arrVal[1]),
					Integer.parseInt(arrVal[2]));
			items.add(node);
		}

        List<Integer> start =   items.stream().map(t->t.start).collect(Collectors.toList());
        List<Integer> end =   items.stream().map(t->t.end).collect(Collectors.toList());

        int root = MinimalTreePathTest.getRootElements(start, end);
        //System.out.println(root);
        
		List<Integer> leafElements = MinimalTreePathTest.getLeafElements(start,end);
        //System.out.println(leafElements);
        
        for (Integer entry : leafElements)
        { 
            int t_waiting = 1;                
            String s_travel_path = "";
            Tree travel_node = items.stream().filter(i-> i.start == entry).findFirst().get();
            s_travel_path = s_travel_path+travel_node.getStart();
            do
            {
                int v_other_node_weightage;
                t_waiting = t_waiting + travel_node.people;
                int v_start = travel_node.start;

                s_travel_path = s_travel_path + " -> " + travel_node.end;
                v_other_node_weightage = other_Node_Weight_Calculation(items, v_start, travel_node.end);
                if (v_other_node_weightage >= t_waiting)
                {
                    t_waiting = t_waiting * 2;
                }
                else
                {
                    t_waiting = t_waiting + v_other_node_weightage;
                }
                if (travel_node.end == root) {
                    break;
                }
                
                int tempEnd = travel_node.getEnd();
                travel_node = items.stream().filter(i -> i.start == tempEnd).findFirst().get();

            } while (true);
            
            items_weight.add(new TreeTravel(t_waiting, s_travel_path));
        }
        
        int opt_path = items_weight.stream().mapToInt(c->c.time).min().getAsInt();
        for(TreeTravel path:items_weight.stream().filter(f->f.time==opt_path).collect(Collectors.toList()))
        {
        	//System.out.println("The optical path is "+path.path+ " and time unit to reach counter "+(path.time-1)+"");
        	System.out.println(path.time-1);
        	System.out.println(path.path);
        }
	}

}
