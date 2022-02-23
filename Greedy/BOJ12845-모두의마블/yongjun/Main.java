import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];

		int max = Integer.MIN_VALUE; // 레벨의 최대 값
		int maxIdx = -1; // 레벨이 최대인 곳의 인덱스

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			if (num > max) { // 최대값 찾기
				max = num;
				maxIdx = i;
			}
		}

		// 1 2 3 4 5 4 3 5 2 1
		int gold = 0;
		
		for (int i = maxIdx + 1; i < n; i++) {
			gold += arr[maxIdx] + arr[i];
		}
		for (int i = maxIdx - 1; i >= 0; i--) {
			gold += arr[maxIdx] + arr[i];
		}

		System.out.print(gold);

		br.close();
	}

}
