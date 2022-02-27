package A0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_모두의마블 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int index=0;
		int max=0;
		
		for(int i=0;i<N;i++) {
			if(max<arr[i]) {
				max=arr[i];
				index=i;
			}
		}
		int sum=0;
		for(int i=0;i<N;i++) {
			if(i!=index) {
				sum+=arr[i];
			}
		}
		
		int answer = max*(N-1)+sum;
		System.out.println(answer);
		
	}
}
