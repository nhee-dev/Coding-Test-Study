package A0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class info implements Comparable<info>{
	int node;
	int dist;
	
	info(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
	@Override
    public int compareTo(info node) { //우선순위: 비용이 적은 순
        return this.dist <= node.dist ? -1 : 1;
    }
}
public class BOJ18352_특정거리의도시찾기_queue {
	
	static int N, M, K, X;
	static ArrayList<ArrayList<Integer>> map;
	static int[] dist;
	static void solve(int n) {
		//PriorityQueue<info> q = new PriorityQueue<>();
		Queue<info> q = new LinkedList<>();
		dist[n]=0;
		for(int i=0;i<map.get(n).size();i++) {
			q.add(new info(map.get(n).get(i),dist[n]));
		}
		
		while(!q.isEmpty()) {
			info tmp = q.poll();
			int nxt = tmp.node;
			int tmpdist = tmp.dist;
			if(dist[nxt]>tmpdist+1) {
				dist[nxt] = tmpdist+1;
				for(int i=0;i<map.get(nxt).size();i++) {
					q.add(new info(map.get(nxt).get(i),dist[nxt]));
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new ArrayList<ArrayList<Integer>>();
		dist = new int[N+1];
		for(int i=0;i<=N;i++) {
			map.add(new ArrayList<>());
			dist[i]=Integer.MAX_VALUE;
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken()); 
			map.get(from).add(to);
		}
		solve(X);
		boolean flag = false;
		for(int i=1;i<dist.length;i++) {
			if(dist[i]==K) {
				System.out.println(i);
				flag=true;
			}
			
		}
		if(!flag) System.out.println(-1);
	}
	
}
