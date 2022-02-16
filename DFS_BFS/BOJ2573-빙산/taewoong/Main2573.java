package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2573 {
    static int[][] arr;
    static int N;
    static int M;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static boolean[][] visited;


    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < M) return true;
        return false;
    }

    public static void melt() {
        int[][] count = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        int newI = i + di[dir];
                        int newJ = j + dj[dir];
                        if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                            count[i][j]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = arr[i][j] -= count[i][j];
                arr[i][j] = n > 0 ? n : 0;
            }
        }
    }

    public static void dfs(int i, int j) {
        if (!visited[i][j]) {
            visited[i][j] = true;
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && arr[newI][newJ] != 0 && !visited[newI][newJ]) {
                    dfs(newI, newJ);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/2573.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        int count = 0;
        while (count < 2) {
            melt();
            year++;
            count = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
        }

        System.out.println(year);
    }
}
