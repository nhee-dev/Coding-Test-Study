import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Time implements Comparable<Time> {
		public int s;
		public int e;

		public Time(int s, int e) {
			this.s = s;
			this.e = e;
		}

		// 우선순위 큐에는 종료시간을 기준으로 오름차순 정렬된다.
		// 0 : 같다 | 음수 : 작다 | 양수 : 크다
		@Override
		public int compareTo(Time o) {
			return this.e - o.e;
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		int n = Integer.parseInt(br.readLine());

		Time[] time = new Time[n];

		PriorityQueue<Time> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			time[i] = new Time(s, e);
		}

		// 수업 시작시간 기준으로 정렬
		Arrays.sort(time, new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.s - o2.s;
			}
		});
		
		pq.offer(time[0]);

		for (int i = 1; i < n; i++) {
			// 현재 큐에 삽입된 강의의 종료시간보다, 큐에 삽입할 시작시간이 같거나 크다면
			// 한 강의실을 이어 쓸 수 있다.
			if (pq.peek().e <= time[i].s) {
				pq.poll();
			}
			pq.offer(time[i]);
		}

		System.out.print(pq.size());

		br.close();
	}

}
