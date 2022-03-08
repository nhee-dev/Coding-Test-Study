import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n+1];
						
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int g1 = Integer.parseInt(st.nextToken());
				int g2 = Integer.parseInt(st.nextToken());
				
				arr[g1] = g2; // 서류 성적 순 정렬
			}
			
			int top = arr[1]; // 서류 1등은 무조건 선발된다.
			int sum = 1;
			
			for (int i = 2; i < n+1; i++) {
				// 서류 성적 순으로 면접 성적을 확인
				// 선발된 사람의 면접 성적보다 높은 성적이 나오면 선발
				if(arr[i] < top) {
					sum++;
					top = arr[i];
				}
			}
			
			System.out.println(sum);
		}
		
		
		br.close();
	}
}
