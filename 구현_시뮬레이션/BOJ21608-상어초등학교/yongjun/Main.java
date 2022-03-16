import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] dy = { -1, 0, 1, 0 }; // 상, 좌, 하, 우
	public static int[] dx = { 0, -1, 0, 1 }; // 상, 좌, 하, 우
	public static int[] sf = { 0, 1, 10, 100, 1000 }; // 만족도 점수

	public static int N;
	public static int[][] map; // 자리
	public static int[][] like; // 학생 정보
	public static int[][] sPos; // 학생 좌표

	public static void step1(int sNum) {
		// 자리에 좋아하는 학생이 있는지 검사하는 flag
		boolean flag = false;
		
		int[][] candidate = new int[N + 1][N + 1]; // 자리 배치 후보지

		for (int i = 0; i < 4; i++) {
			int lNum = like[sNum][i]; // 좋아하는 학생
			// 좋아하는 학생이 자리에 있다면
			if (sPos[lNum][0] > 0) {
				for (int j = 0; j < 4; j++) {
					int nY = sPos[lNum][0] + dy[j];
					int nX = sPos[lNum][1] + dx[j];
					// 좋아하는 학생 주변 자리가 비어있으면 후보에 추가
					if (checkIdx(nY, nX) && map[nY][nX] == 0) {
						flag = true;
						candidate[nY][nX]++;
					}
				}
			}
		}

		// 좋아하는 학생이 자리에 있고, 좋아하는 학생 주변에 빈자리가 있을 때
		if (flag) {
			int[][] cPos = new int[N * N + 1][2]; // 후보 좌표
			int cIdx = 0; // 같은 조건을 만족하는 자리 개수
			int max = 0; // 가장 많이 인접하는 자리의 학생 수
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (candidate[i][j] > max) {
						cIdx = 0;
						cPos[cIdx][0] = i;
						cPos[cIdx][1] = j;
						max = candidate[i][j];
						cIdx++;
					} else if (candidate[i][j] > 0 && candidate[i][j] == max) {
						cPos[cIdx][0] = i;
						cPos[cIdx][1] = j;
						cIdx++;
					}
				}
			}
			// 좋아하는 학생들이 가장 많이 있는 자리
			if (cIdx == 1) {
				map[cPos[0][0]][cPos[0][1]] = sNum;
				sPos[sNum][0] = cPos[0][0];
				sPos[sNum][1] = cPos[0][1];
			} else if (cIdx > 1) { // 좋아하는 학생이 가장 많이 있는 자리가 여러 개인 경우
				int[][] blank = new int[N + 1][N + 1];

				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						blank[i][j] = -1;
					}
				}

				for (int i = 0; i < cIdx; i++) {
					blank[cPos[i][0]][cPos[i][1]] = 0;
					
					for (int j = 0; j < 4; j++) {
						int nY = cPos[i][0] + dy[j];
						int nX = cPos[i][1] + dx[j];
						if (checkIdx(nY, nX) && map[nY][nX] == 0) {
							blank[cPos[i][0]][cPos[i][1]]++;
						}
					}
				}
				int cY = 0;
				int cX = 0;
				max = -1;
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (map[i][j] == 0 && blank[i][j] > max) {
							cY = i;
							cX = j;
							max = blank[i][j];
						}
					}
				}
				map[cY][cX] = sNum;
				sPos[sNum][0] = cY;
				sPos[sNum][1] = cX;
			}
		} else { // 좋아하는 학생이 자리에 없거나, 좋아하는 학생 주변에 자리가 없을때
			// 빈칸이 가장 많은 곳을 자리배정
			step2(sNum);
		}
	}

	public static void step2(int sNum) {

		int[][] blank = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				blank[i][j] = -1;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 해당 자리가 빈칸일때 주변 빈칸의 개수 검사
				if (map[i][j] == 0) {
					blank[i][j] = 0;
					for (int k = 0; k < 4; k++) {
						int nY = i + dy[k];
						int nX = j + dx[k];
						if (checkIdx(nY, nX) && map[nY][nX] == 0) {
							blank[i][j]++;
						}
					}
				}
			}
		}
		int cY = 0;
		int cX = 0;
		int max = -1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0 && blank[i][j] > max) {
					cY = i;
					cX = j;
					max = blank[i][j];
				}
			}
		}
		map[cY][cX] = sNum;
		sPos[sNum][0] = cY;
		sPos[sNum][1] = cX;
	}

	// 범위 체크
	public static boolean checkIdx(int y, int x) {
		boolean result = false;
		if (y > 0 && y <= N && x > 0 && x <= N) {
			result = true;
		}
		return result;
	}

	public static int getSF(int y, int x) {
		int sNum = map[y][x];
		int cnt = 0;
		// 해당학생 인접한 칸 탐색
		for (int i = 0; i < 4; i++) {
			int nY = y + dy[i];
			int nX = x + dx[i];
			if (checkIdx(nY, nX)) {
				int c = map[nY][nX];
				// 인접한 칸의 학생이 좋아하는 학생일 때
				for (int j = 0; j < 4; j++) {
					if (like[sNum][j] == c) {
						cnt++;
						break;
					}
				}
			}
		}
		return sf[cnt];
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1]; // 학생 자리
		like = new int[N * N + 1][4]; // 학생별로 좋아하는 학생 배열
		sPos = new int[N * N + 1][2]; // 학생별로 배치된 자리

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sNum = Integer.parseInt(st.nextToken());
			// 좋아하는 학생 정보 받기
			for (int j = 0; j < 4; j++) {
				like[sNum][j] = Integer.parseInt(st.nextToken());
			}
			step1(sNum);
		}

		int sum = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum += getSF(i, j);
			}
		}

		System.out.print(sum);

		br.close();
	}

}
