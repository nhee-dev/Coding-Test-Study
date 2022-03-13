import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		int N = Integer.parseInt(br.readLine());

		// 1 <= N <= 1,500,000
		int[] DP = new int[1500051];

		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());

			// 현재까지 얻을 수 있는 금액을 가져온다.
			DP[i] = Math.max(DP[i], DP[i - 1]);
			// T일 이후에 금액을 얻을 수 있으며,
			// (DP[i + T]의 이득)과, (지금까지 이득 + 오늘 상담해서 T일 후의 이득) 중 큰 값 선택
			DP[i + T] = Math.max(DP[i + T], DP[i] + P);
		}

		// 퇴사는 N + 1일에 이루어진다
		// N일에 1일짜리 일을 하면 DP[N+1]값이 생긴다.
		System.out.print(Math.max(DP[N], DP[N + 1]));

		br.close();
	}

}
