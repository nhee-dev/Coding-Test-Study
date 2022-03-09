import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[301];
		int[] DP = new int[301];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		DP[1] = arr[1];
		DP[2] = arr[1] + arr[2];
		DP[3] = Math.max(arr[1], arr[2]) + arr[3];

		for (int i = 4; i <= N; i++) {
			DP[i] = Math.max(DP[i - 3] + arr[i - 1], DP[i - 2]) + arr[i];
		}

		System.out.println(DP[N]);

		br.close();
	}

}
