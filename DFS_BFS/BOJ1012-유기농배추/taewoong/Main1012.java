package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1012 {
    static boolean[][] arr;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static int M;
    static int N;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < M && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static void dfs(int i, int j) {
        if (arr[i][j]) {
            arr[i][j] = false;
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newj = j + dj[dir];
                if (isIn(newI, newj) && arr[newI][newj]) {
                    dfs(newI, newj);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/1012.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());    //가로길이
            N = Integer.parseInt(st.nextToken());    //세로길이
            int K = Integer.parseInt(st.nextToken());

            arr = new boolean[M + 1][N + 1];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                arr[i][j] = true;
            }

            int count = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}