package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main21608 {
    static int N;
    static int[] order;
    static int[][] arr;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static int count;
    static int[][] seat;
    static int happy;
    static int[] happy_score = {0, 1, 10, 100, 1000};

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }


    public static void solve(int k) {
        int[][] like = new int[N][N];
        int max_like = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seat[i][j] == 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        int newI = i + di[dir];
                        int newJ = j + dj[dir];
                        if (isIn(newI, newJ)) {
                            for (int x = 0; x < 4; x++) {
                                if (seat[newI][newJ] == arr[k][x]) {
                                    like[i][j]++;
                                }
                            }
                        }
                    }
                    max_like = Math.max(max_like, like[i][j]);
                }
            }
        }

        int max_empty = 0;
        int max_empty_i = 0;
        int max_empty_j = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (like[i][j] == max_like && seat[i][j] == 0) {
                    int empty = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int newI = i + di[dir];
                        int newJ = j + dj[dir];
                        if (isIn(newI, newJ) && seat[newI][newJ] == 0) {
                            empty++;
                        }
                    }
                    if (empty >= max_empty) {
                        max_empty = empty;
                        max_empty_i = i;
                        max_empty_j = j;
                    }
                }
            }
        }
        seat[max_empty_i][max_empty_j] = k;
    }

    public static void happyCal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = seat[i][j];
                int like = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int newI = i + di[dir];
                    int newJ = j + dj[dir];
                    if (isIn(newI, newJ)) {
                        for (int x = 0; x < 4; x++) {
                            if (seat[newI][newJ] == arr[k][x]) {
                                like++;
                            }
                        }
                    }
                }
                happy += happy_score[like];
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/21608.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N * N + 1][4];
        order = new int[N * N + 1];
        for (int i = 1; i <= N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            order[i] = k;
            for (int j = 0; j < 4; j++) {
                arr[k][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
        seat = new int[N][N];
        for (int i = 1; i <= N * N; i++) {
            int k = order[i];
            solve(k);
        }

        happy = 0;

        happyCal();


        System.out.println(happy);
    }
}
