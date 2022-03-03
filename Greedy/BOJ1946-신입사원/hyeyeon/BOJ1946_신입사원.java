package A0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1946_신입사원 {
	static int N;
	static int[][] grade; 
	static int cnt;
	
	static void check() {
		cnt=1;
		int min = grade[0][1];
		for(int i=1;i<N;i++) {
			if(grade[i][1]<min) {
				cnt++;
				min=grade[i][1];
			}
		}
	}
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			grade = new int[N][2];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				grade[i][0]=Integer.parseInt(st.nextToken());
				grade[i][1]=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(grade, new Comparator<int[]>() { 
				@Override 
				public int compare(int[] o1, int[] o2) {
					if(o1[0] == o2[0]) {
						return o1[1] - o2[1]; 
					}
					else { 
						return o1[0] - o2[0]; 
					} 
				} 
			});


			check();
			System.out.println(cnt);
		}
	}
}
