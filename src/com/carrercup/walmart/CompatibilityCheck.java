package com.carrercup.walmart;

import java.util.LinkedList;

/*
 https://www.careercup.com/question?id=5706718039244800
<p>
Two friends Kohli and Dhoni want to test their friendship to check how compatible they are. Given a list of n movies numbered 1,2,3....n and asked both of them to rank the movies.
<br>
Design an algorithm to find compatibility difference between them.
<br>
<br>
Compatibility difference is the number of mis-matches in the relative rankings of the same movie given by them i.e. if Kohli ranks Movie 3 before Movie 2 and Dhoni ranks Movie 2 before Movie 3 then its a relative ranking mis-match Compatibility difference is the maximum number of mis-matches
<br>
<br>
Sample Input
<br>
<br>
5
<br>
31245
<br>
32415
<br>
Sample Output
<br>
<br>
2
<br>
Explanation
<br>
<br>
Movies are 1,2,3,4,5. Kohli ranks them 3,1,2,4,5, Dhoni ranks them 3,2,4,1,5. Compatibility difference is 2 because Kohli ranks movie 1 before 2,4 but Dhoni ranks it after.
</p>

 */
public class CompatibilityCheck {
	
	public static double computeCompatibility(LinkedList<Integer> user1Rates, LinkedList<Integer> user2Rates)
	{
		int difference = 0;
		for(int i=0; i<user1Rates.size();i++)
		{
			Integer rate = user1Rates.get(i);
			for(int j=i+1; j<user1Rates.size();j++)
			{
				Integer rateToTest = user1Rates.get(j);
				//rate>rateTotest
				if(!isSameOrder(user2Rates, rate, rateToTest))
				{
					difference++;
				}
			}
		}
		return difference;
	}
	
	private static boolean isSameOrder(LinkedList<Integer> user2Rates, Integer rate, Integer rateToTest)
	{
		return user2Rates.indexOf(rate)<user2Rates.indexOf(rateToTest);
	}
	
	public static void main(String args[]){
		LinkedList<Integer> user1Rates = new LinkedList<Integer>();
		user1Rates.add(4);
		user1Rates.add(3);
		user1Rates.add(1);
		user1Rates.add(5);
		user1Rates.add(2);

		LinkedList<Integer> user2Rates = new LinkedList<Integer>();
		user2Rates.add(2);
		user2Rates.add(5);
		user2Rates.add(3);
		user2Rates.add(4);
		user2Rates.add(1);
		
		System.out.println(CompatibilityCheck.computeCompatibility(user1Rates, user2Rates));
	}

}
