import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int[] dy = { -1, 0, 1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };

	public static int N;
	public static int[][] map;
	public static int[][] d;
	public static boolean[][] visited;
	public static PriorityQueue<Edge> pq;

	static class Edge implements Comparable<Edge> {
		public int y;
		public int x;
		public int w;

		public Edge(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	public static void dijkstra(int tc) {
		visited = new boolean[N][N];
		d = new int[N][N];
		pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			Arrays.fill(d[i], Integer.MAX_VALUE);
		}

		// 시작점으로 초기화
		d[0][0] = map[0][0];
		visited[0][0] = true;

		// 시작점에서 갈 수 있는 정점
		d[0][1] = map[0][1] + d[0][0];
		d[1][0] = map[1][0] + d[0][0];
		pq.add(new Edge(0, 1, d[0][1]));
		pq.add(new Edge(1, 0, d[1][0]));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			// 방문 처리
			visited[edge.y][edge.x] = true;

			// 현재 정점에서 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int nY = edge.y + dy[i];
				int nX = edge.x + dx[i];

				if (checkIdx(nY, nX) && !visited[nY][nX]) {
					d[nY][nX] = Math.min(d[nY][nX], edge.w + map[nY][nX]);

					pq.add(new Edge(nY, nX, d[nY][nX]));
				}
			}
		}

		System.out.println("Problem " + tc + ": " + d[N - 1][N - 1]);
	}

	public static boolean checkIdx(int y, int x) {
		if (y >= 0 && y < N && x >= 0 && x < N) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		String t = "0";
		int tc = 1;

		while (!(t = br.readLine()).equals("0")) {
			N = Integer.parseInt(t);
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dijkstra(tc);
			tc++;
		}

		br.close();
	}

}
