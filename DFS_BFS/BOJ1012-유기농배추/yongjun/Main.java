import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static int m;
	public static int n;
	public static int c;

	public static int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
	public static int[] dx = { 0, 0, -1, 1 };

	public static int[][] q;
	public static int front;
	public static int rear;
	public static int cnt;

	// 연결된 모든 배추를 찾는다.
	public static void bfs(int y, int x) {
		q = new int[2][2500];
		rear = 0;
		front = 0;

		q[0][rear % 2500] = y;
		q[1][rear % 2500] = x;
		rear++; // 시작 지점 큐에 추가

		while (front != rear) {
			int cY = q[0][front % 2500]; // poll
			int cX = q[1][front % 2500];
			front++;
			arr[cY][cX] = 0; // 현재위치 방문 표시
			// 상 하 좌 우 탐색
			for (int i = 0; i < 4; i++) {
				int tY = cY + dy[i];
				int tX = cX + dx[i];
				// 배열의 범위를 벗어나면 넘어감
				if (tY < 0 || tY > n - 1 || tX < 0 || tX > m - 1) {
					continue;
				}
				// 탐색하지 않은 배추가 있으면 큐에 추가
				if (arr[tY][tX] == 1) {
					q[0][rear % 2500] = tY;
					q[1][rear % 2500] = tX;
					rear++;
					arr[tY][tX] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			m = Integer.parseInt(st.nextToken()); // 가로 길이
			n = Integer.parseInt(st.nextToken()); // 세로 길이
			c = Integer.parseInt(st.nextToken()); // 배추 수

			arr = new int[n][m];

			cnt = 0;

			for (int i = 0; i < c; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[y][x] = 1;
			}

			// 모든 지점에 대해 순회하지 않은 땅이 있으면 bfs 실행
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] == 1) {
						bfs(i, j);
						cnt++; // bfs가 실행될 때 마다 +1
					}
				}
			}

			sb.append(cnt).append("\n");
		}
		bw.write(sb.toString());

		bw.close();
		br.close();
	}

}
