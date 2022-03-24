import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int D;

	public static ArrayList<Shortcut> al;
	public static int[] d;

	public static int min; // 최솟값

	static class Shortcut {
		int s;
		int e;
		int len;

		public Shortcut(int s, int e, int len) {
			this.s = s;
			this.e = e;
			this.len = len;
		}
	}

	public static void dijkstra() {
		d = new int[D + 1];

		Arrays.fill(d, Integer.MAX_VALUE);

		d[0] = 0;

		// d 초기화
		// 시작 정점에서 갈 수 있는 정점 기록
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i).s == 0) {
				d[al.get(i).e] = al.get(i).len;
			}
		}

		for (int i = 1; i < D + 1; i++) {
			d[i] = Math.min(d[i], d[i - 1] + 1);

			for (int j = 0; j < al.size(); j++) {
				if (al.get(j).s == i) {
					d[al.get(j).e] = Math.min(al.get(j).len + d[i], d[al.get(j).e]);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		al = new ArrayList<>(); // 지름길 리스트

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());

			// 지름길의 길이가 그냥 가는 것 보다 길거나
			// 도착점인 D보다 크거나
			// 역주행인 경우 추가하지 않음
			if (e - s <= len || e > D || e <= s) {
				continue;
			}

			boolean flag = false;
			if (!al.isEmpty()) {
				// 기존 간선 검사
				for (int j = 0; j < al.size(); j++) {
					Shortcut temp = al.get(j);
					// 시작과 도착이 같은 간선을 찾으면 가중치가 작은 것으로 배정
					if (temp.s == s && temp.e == e) {
						temp.len = Math.min(temp.len, len);
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				al.add(new Shortcut(s, e, len));
			}
		}

		dijkstra();

		System.out.print(d[D]);

		br.close();
	}

}
