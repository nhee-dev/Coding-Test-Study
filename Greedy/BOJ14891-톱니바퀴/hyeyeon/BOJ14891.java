package A0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {
	
	static int[][] gear = new int[4][8];
	static void start(int n, int d, boolean[] v) {
		v[n]=true;
		
		int left = n-1;
		int right = n+1;
		if(left>=0 && (gear[left][2]!=gear[n][6]) && !v[left]) {
			start(left,d*(-1),v);
		}
		if(right<=3 && (gear[right][6]!=gear[n][2]) && !v[right]) {
			start(right,d*(-1),v);
		}
		
		move(n,d);
	}
	
	static void move(int n, int d) {
		int[] temp = new int[8];
		if(d ==1) {
			for(int i =0;i<=6;i++) {
				temp[i+1] =  gear[n][i];
			}
			temp[0]=gear[n][7];
		}
		else {
			for(int i=1;i<=7;i++) {
				temp[i-1] = gear[n][i];
			}
			temp[7] = gear[n][0];
		}
		
		for(int i =0; i<temp.length;i++) {
			gear[n][i] = temp[i];
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0;i<4;i++) {
			String[] line = br.readLine().split("");
			for(int j=0;j<8;j++) {
				gear[i][j]=Integer.parseInt(line[j]);
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		int gearNum;
		int direction;
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			gearNum = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[4];
			start(gearNum-1, direction, visited);
		}
		int total = 0;
		total += gear[0][0]*1;
		total += gear[1][0]*2;
		total += gear[2][0]*4;
		total += gear[3][0]*8;
		
		System.out.println(total);

		
	}
}
