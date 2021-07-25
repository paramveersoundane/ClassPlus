import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

class Portal implements Comparator<Portal>{
    int universe;
    int time;
    Portal(){}
    Portal(int v,int w){
        this.universe=v;
        this.time=w;
    }
    @Override
    public int compare(Portal n1, Portal n2){
        return n1.time - n2. time;
    }
    public String toString(){
        return "Universe: "+this.universe+", Time: " + this.time;
    }
}

public class CoronaUniverse {
	
	public static void main(String[] args) {
		Scanner scan=new  Scanner(System.in);
		int n=scan.nextInt(); // number of universe
		int m=scan.nextInt(); // number of portals
		HashMap<Integer,ArrayList<Portal>> adjList=new HashMap<Integer, ArrayList<Portal>>();
		for(int i=1;i<=m;i++) {
			int fromUniverse=scan.nextInt();
			int toUniverse=scan.nextInt();
			int time=scan.nextInt();
			ArrayList<Portal> portals=adjList.getOrDefault(fromUniverse, new  ArrayList<Portal>());
			portals.add(new Portal(toUniverse, time));
			adjList.put(fromUniverse, portals);
		}
		int hm[] = new int[n+1];
		for(int i=1;i<=n;i++) {
        	hm[i]=-1;
        }
		int result=shortestPath(adjList, n, hm);
		scan.close();
		System.out.println(result);
	}
	// Djikstra Algorithm
	static int shortestPath(HashMap<Integer,ArrayList<Portal>> adjList, int n, int hm[]) {
		
		PriorityQueue<Portal> pq=new PriorityQueue<Portal>(new Portal());
		hm[1]=0;
		pq.add(new Portal(1,0));
		while(!pq.isEmpty()) {
			Portal p=pq.poll();
        	ArrayList<Portal> portals = adjList.get(p.universe);
        	if(portals==null) {
        		continue;
        	}
        	for(Portal u: portals) {
        		int w=p.time+u.time;
        		if(hm[u.universe]>w || hm[u.universe]==-1) {
        			pq.add(new Portal(u.universe,w));
        			hm[u.universe]=w;
        		}
        	}
		}
		return hm[n];
		
	}
}
