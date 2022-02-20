import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int dy[] = { -1, 1, 0, 0 };
	public static int dx[] = { 0, 0, -1, 1 };

	public static char arr[][];
	public static boolean visited[][];
	public static int h;
	public static int w;

	public static class XY {
		int x;
		int y;
		int cnt;

		public XY(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static int bfs(int y, int x) {
		Queue<XY> queue = new LinkedList<>();
		int d = 0;
		visited[y][x] = true;

		queue.add(new XY(x, y, 0));

		while (!queue.isEmpty()) {
			XY cxy = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nY = cxy.y + dy[i];
				int nX = cxy.x + dx[i];
				if (0 <= nX && nX < w && 0 <= nY && nY < h && !visited[nY][nX] && arr[nY][nX] == 'L') {
					visited[nY][nX] = true;
					queue.add(new XY(nX, nY, cxy.cnt + 1));
					d = Math.max(d, cxy.cnt + 1);
				}
			}
		}
		return d;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		arr = new char[h][w];
		visited = new boolean[h][w];

		for (int i = 0; i < h; i++) {
			String str = br.readLine();
			for (int j = 0; j < w; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		int result = 0;

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == 'L') {
					visited = new boolean[h][w];
					int d = bfs(i, j);
					result = Math.max(result, d);

				}
			}
		}

		System.out.println(result);

	}
}