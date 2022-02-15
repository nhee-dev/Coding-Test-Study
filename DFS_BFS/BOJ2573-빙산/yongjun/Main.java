import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] dy = { -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[][] arr;
	public static int n;
	public static int m;

	public static boolean[][] visited;
	public static int[][] stack = new int[2][50000];
	public static int top = -1;
	public static int result = 0;
	public static int cnt = 0;
	public static boolean flag = false; // 빙산이 존재하는지 알려줌

	public static void decrease() {
		result++;

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				for (int k = 0; k < 4; k++) {
					// 현재 위치가 빙산이고 상하좌우 순서대로 바다가 있으며, 현재 연도에 녹은게 아니라면
					if (arr[i][j] > 0 && arr[i + dy[k]][j + dx[k]] <= 0 && !visited[i + dy[k]][j + dx[k]]) {
						arr[i][j]--; // 빙산 높이 감소
						visited[i][j] = true;
					}
				}
			}
		}
	}

	public static void bfs(int y, int x) {
		cnt++; // 탐색이 발생하면, 빙산의 개수 추가

		top = -1;

		top++;
		stack[0][top] = y;
		stack[1][top] = x;
		while (top != -1) {
			int cy = stack[0][top];
			int cx = stack[1][top];
			top--; // pop
			visited[cy][cx] = true;
			for (int i = 0; i < 4; i++) {
				// 방문하지 않은 연결된 빙산이 있으면,
				if (!visited[cy + dy[i]][cx + dx[i]] && arr[cy + dy[i]][cx + dx[i]] > 0) {
					top++;
					stack[0][top] = cy + dy[i];
					stack[1][top] = cx + dx[i];
					visited[cy + dy[i]][cx + dx[i]] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		do {
			flag = false;
			cnt = 0;
			visited = new boolean[n][m];
			decrease(); // 빙산 녹임
			visited = new boolean[n][m];
			for (int i = 1; i < n - 1; i++) {
				for (int j = 1; j < m - 1; j++) {
					if (!visited[i][j] && arr[i][j] > 0) { // 빙산을 발견하면
						flag = true; // 빙산이 존재한다고 알림
						bfs(i, j);
					}
				}
			}
			if (cnt > 1) { // 빙산이 2개 이상 존재하게 되면
				System.out.print(result);
				break;
			}
		} while (flag);
		// 조건문을 빠져나왔는데, 빙산이 하나도 없었다면,
		if (!flag) {
			System.out.print(0);
		}
		br.close();
	}

}
