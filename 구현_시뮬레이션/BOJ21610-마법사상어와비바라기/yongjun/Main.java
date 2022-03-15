import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] cy = { 0, -1, -1, 1, 1 }; // 대각선 방향 물
	public static int[] cx = { 0, -1, 1, 1, -1 };

	public static int N; // 격자 크기
	public static int M; // 구름 이동 횟수
	public static int[][] map; // 바구니 정보
	public static boolean[][] beforeCloud; // 이동 전 구름
	public static boolean[][] afterCloud; // 이동 후 구름

	public static void move(int d, int s) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (beforeCloud[i][j] == true) {
					int nY = setPos(i + dy[d] * s);
					int nX = setPos(j + dx[d] * s);
					afterCloud[nY][nX] = true;
				}
			}
		}
	}

	public static int setPos(int pos) {
		while (pos < 1 || pos > N) {
			if (pos < 1) {
				pos = pos + N;
			} else if (pos > N) {
				pos = pos - N;
			}
		}
		return pos;
	}

	public static void rain() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (afterCloud[i][j]) {
					map[i][j] += 1;
				}
			}
		}
	}

	public static void addWater() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 구름이 있던 위치일 때
				if (afterCloud[i][j]) {
					// 대각선 검사
					for (int k = 1; k <= 4; k++) {
						int nY = i + cy[k];
						int nX = j + cx[k];
						// 배열 범위를 벗어나면 실행 안함
						if (nY < 1 || nY > N || nX < 1 || nX > N) {
							continue;
						}
						// 대각선에 물이 있으면 증가
						if (map[nY][nX] > 0) {
							map[i][j] += 1;
						}
					}
				}
			}
		}
	}

	public static void createCloud() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 구름이 없었고, 물의 양이 2 이상이면 구름 생성
				if (!afterCloud[i][j] && map[i][j] >= 2) {
					map[i][j] -= 2;
					beforeCloud[i][j] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		beforeCloud = new boolean[N + 1][N + 1];
		afterCloud = new boolean[N + 1][N + 1];

		// 지도 초기화
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기 구름
		beforeCloud[N][1] = true;
		beforeCloud[N][2] = true;
		beforeCloud[N - 1][1] = true;
		beforeCloud[N - 1][2] = true;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리

			move(d, s); // 1. 구름 이동
			rain(); // 2. 구름이 있던칸에 비가 1씩 내림
			beforeCloud = new boolean[N + 1][N + 1]; // 3. 이동 전 구름 초기화
			addWater(); // 4. 비가 내린칸 대각선을 검사하여 물 증가
			createCloud(); // 5. 새로운 구름 생성
			afterCloud = new boolean[N + 1][N + 1]; // 6. 이동 후 구름 초기화
		}

		int sum = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum += map[i][j];
			}
		}

		System.out.print(sum);

		br.close();
	}

}
