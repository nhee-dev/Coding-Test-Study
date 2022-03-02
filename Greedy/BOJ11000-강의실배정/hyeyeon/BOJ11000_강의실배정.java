package A0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11000_강의실배정 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] Class = new int[N][2];
		boolean[] visited =new boolean[N];
		int cnt=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			Class[i][0]=Integer.parseInt(st.nextToken());
			Class[i][1]=Integer.parseInt(st.nextToken());
		}
		visited= new boolean[N];
		Arrays.sort(Class, (o1, o2) -> { 
			if(o1[0] == o2[0]){
				return Integer.compare(o1[1],o2[1]); }
			else{ return Integer.compare(o1[0],o2[0]); 
			} 
		});
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i==j) continue;
				if(visited[i]||visited[j]) 
					continue;
				if(Class[i][1]==Class[j][0]) {
					cnt++;
					visited[i]=true;
					visited[j]=true;
				}
				
			}
		}
		
		for(int i=0;i<N;i++) {
			if(!visited[i])
				cnt++;
		}
		
		System.out.println(cnt);
		
	}
}
