import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int S; // 시작
	public static int E; // 도착
	public static ArrayList<City>[] al;

	public static int[] d;
	public static boolean[] visited;
	public static PriorityQueue<City> pq;

	static class City implements Comparable<City> {
		int v;
		int w;

		public City(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(City o) {
			return this.w - o.w;
		}
	}

	public static void dijkstra() {
		d = new int[N + 1];
		visited = new boolean[N + 1];
		pq = new PriorityQueue<>();

		Arrays.fill(d, Integer.MAX_VALUE);

		d[S] = 0;
		pq.add(new City(S, 0));

		while (!pq.isEmpty()) {
			City start = pq.poll();

			if (visited[start.v]) {
				continue;
			}

			visited[start.v] = true;

			for (int i = 0; i < al[start.v].size(); i++) {
				City end = al[start.v].get(i);

				if (!visited[end.v] && d[end.v] > d[start.v] + end.w) {
					d[end.v] = d[start.v] + end.w;
					pq.add(new City(end.v, d[end.v]));
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		al = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			al[i] = new ArrayList<City>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			al[s].add(new City(e, w));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		dijkstra();

		System.out.print(d[E]);

		br.close();
	}

}
