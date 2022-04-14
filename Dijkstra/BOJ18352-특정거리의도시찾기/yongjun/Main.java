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
	public static int K;
	public static int X;

	public static ArrayList<ArrayList<Integer>> al;
	public static PriorityQueue<Integer> pq;
	public static int[] d;

	public static void dijkstra() {
		pq = new PriorityQueue<>();

		d = new int[N + 1];
		Arrays.fill(d, Integer.MAX_VALUE);

		d[X] = 0;

		// X번 도시에서 갈 수 있는 도시 번호를 d와 pq에 추가
		for (int i = 0; i < al.get(X).size(); i++) {
			d[al.get(X).get(i)] = 1;
			pq.add(al.get(X).get(i));
		}

		while (!pq.isEmpty()) {
			int edge = pq.poll();

			for (int i = 0; i < al.get(edge).size(); i++) {
				int t = al.get(edge).get(i);

				if (d[t] > d[edge] + 1) {
					d[t] = d[edge] + 1;
					pq.add(t);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시의 정보

		al = new ArrayList<>();

		// 도시의 개수만큼 arrayList 추가
		for (int i = 0; i < N + 1; i++) {
			al.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			al.get(s).add(e);
		}

		dijkstra();

		StringBuilder sb = new StringBuilder();
		boolean flag = false;

		for (int i = 1; i <= N; i++) {
			if (d[i] == K) {
				sb.append(i).append("\n");
				flag = true;
			}
		}
		if (flag) {
			System.out.print(sb);
		} else {
			System.out.print("-1");
		}

		br.close();
	}

}
