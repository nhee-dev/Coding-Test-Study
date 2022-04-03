import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Edge>[] al;

	public static int V;
	public static int E;
	public static int K;

	public static int[] d;
	public static PriorityQueue<Edge> pq;

	static class Edge implements Comparable<Edge> {
		public int e;
		public int w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	public static void dijkstra() {
		d = new int[V + 1];
		pq = new PriorityQueue<>();

		Arrays.fill(d, Integer.MAX_VALUE);

		d[K] = 0;

		// K에서 갈 수 있는 정점 d에 추가
		for (int i = 0; i < al[K].size(); i++) {
			Edge edge = al[K].get(i);

			// K에서 e까지 여러 간선이 있을 수 있으니 최소값 배정
			d[edge.e] = Math.min(d[edge.e], edge.w);

			pq.add(new Edge(edge.e, edge.w));
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			// 더 큰 가중치로 도착하게 되었다면 continue
			if (d[edge.e] < edge.w) {
				continue;
			}

			for (int i = 0; i < al[edge.e].size(); i++) {
				Edge temp = al[edge.e].get(i);

				if (d[temp.e] > d[edge.e] + temp.w) {
					d[temp.e] = d[edge.e] + temp.w;
					pq.add(new Edge(temp.e, d[temp.e]));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		al = new ArrayList[V + 1];

		// 정점 1부터 V까지 리스트 생성
		for (int i = 1; i <= V; i++) {
			al[i] = new ArrayList<>();
		}

		// 간선들을 모두 저장
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			al[s].add(new Edge(e, w));
		}

		dijkstra();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (d[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(d[i]).append("\n");
			}
		}

		System.out.print(sb.toString());

		br.close();
	}

}