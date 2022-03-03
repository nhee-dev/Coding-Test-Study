package A0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1969_DNA {
	
	static int N,M;
	static String[] DNA;
	static int answer;
	
	static void count() {
		for(int j=0;j<M;j++) {
			int a=0;
			int t=0;
			int g=0;
			int c=0;
			for(int i=0;i<N;i++) {
				switch(DNA[i].charAt(j)) {
					case 'A':
						a++;
						break;
					case 'T':
						t++;
						break;
					case 'G':
						g++;
						break;
					case 'C':
						c++;
						break;
					
				}
				
			}
			System.out.print(makeDNA(a, t, g, c));
		}
	}
	static char makeDNA(int a, int t, int g, int c) {
		int max = Math.max(a>t?a:t,  g>c?g:c);
		answer += N-max;
		if(max==a) return 'A';
		else if(max==c) return 'C';
		else if(max==g) return 'G';
		else return 'T';
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		DNA = new String[N];
		answer = 0;
		for(int i=0;i<N;i++) {
			DNA[i] = br.readLine();
		}
		count();
		System.out.println();
		System.out.println(answer);
		
	}

}
