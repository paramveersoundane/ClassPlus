import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class CoronaEpicenter {
	// insert Road into Adj List
	static void insertRoad(HashMap<Integer, ArrayList<Integer>> adjList, int cityA, int cityB) {
		//connect A->B
		ArrayList<Integer> roads=adjList.getOrDefault(cityA, new ArrayList<Integer>());
		roads.add(cityB);
		adjList.put(cityA,roads);
		// connect B->A
		roads=adjList.getOrDefault(cityB, new ArrayList<Integer>());
		roads.add(cityA);
		adjList.put(cityB,roads);
	}
	// Add x nearest node to set
	static Set<Integer> BFSNormal(HashMap<Integer, ArrayList<Integer>> adjList,int hotspot, int x){
		
		Set<Integer> set= new HashSet<Integer>();
		Queue<Integer> q=new LinkedList<Integer>();
		Queue<Integer> distance=new LinkedList<Integer>();
		
		q.add(hotspot);
		int y=0;
		distance.add(y);
		while(!q.isEmpty()) {
			int v=q.poll();
			int d=distance.poll();
			if(d==x) {
				continue;
			}
			d=d+1;
			ArrayList<Integer> temp = adjList.get(v);
			//System.out.println(temp);
			for(int i: temp) {
				if(set.contains(i)) {
					continue;
				}
			    set.add(i);
				q.add(i);
				distance.add(d);
			}
			
		}
		return set;
		
	}
	// Add x nearest node to set which also contains in tempset
	static Set<Integer> BFS(HashMap<Integer, ArrayList<Integer>> adjList,int hotspot, int x,Set<Integer> tempset){
		
		Set<Integer> set= new HashSet<Integer>();
		Queue<Integer> q=new LinkedList<Integer>();
		int y=0;
		Queue<Integer> distance=new LinkedList<Integer>();
		q.add(hotspot);
		distance.add(y);
		if(tempset.contains(hotspot)) {
			set.add(hotspot);
		}
		
		while(!q.isEmpty()) {
			int v=q.poll();
			int d=distance.poll();
			if(d==x) {
				continue;
			}
			d=d+1;
			ArrayList<Integer> temp = adjList.get(v);
			for(int i: temp) {
				if(set.contains(i)) {
					continue;
				}
				
				if(tempset.contains(i)) {
					set.add(i);
				}
				q.add(i);
				distance.add(d);
			}
			
		}
		
		return set;
		
	}
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		int h=scan.nextInt();
		int x=scan.nextInt();
		HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> hotspots= new ArrayList<Integer>();
		for(int i=0;i<h;i++) {
			int temp=scan.nextInt();
			hotspots.add(temp);
		}
		for(int i=1;i<=n-1;i++) {
			int cityA=scan.nextInt();
			int cityB=scan.nextInt();
			insertRoad(adjList, cityA, cityB);
		}
		Set<Integer> set= BFSNormal(adjList, hotspots.get(0), x);

		for(int i=1;i<hotspots.size();i++) {
			set=BFS(adjList, hotspots.get(i), x, set);
		}
		scan.close();
		System.out.println(set);
		System.out.println(set.size());
	}

}
