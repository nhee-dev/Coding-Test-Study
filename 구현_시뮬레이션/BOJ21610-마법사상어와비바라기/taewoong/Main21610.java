package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main21610 {
    static int N;
    static int M;
    static int[][] arr;
    static int[][] cloud;
    static int[][] ds;
    static int[] di = {-2, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dj = {-2, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] di_water = {-1, -1, 1, 1};
    static int[] dj_water = {-1, 1, -1, 1};

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static void move(int m) {
        int d = ds[0][m];
        int s = ds[1][m];
        int[][] new_cloud = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j] == 1) {
                    int newI = i + di[d] * s;
                    int newJ = j + dj[d] * s;
                    while (newI < 0) {
                        newI += N;
                    }
                    while (newJ < 0) {
                        newJ += N;
                    }
                    newI %= N;
                    newJ %= N;
                    new_cloud[newI][newJ] = 1;
                }
            }
        }
        cloud = new_cloud;
    }

    public static void water() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j] == 1) {
                    arr[i][j] += 1;
                }
            }
        }

        int[][] water_sum = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j] == 1) {
                    int sum = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int newI = i + di_water[dir];
                        int newJ = j + dj_water[dir];
                        if (isIn(newI, newJ) && arr[newI][newJ] != 0) {
                            sum++;
                        }
                    }
                    water_sum[i][j] = sum;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] += water_sum[i][j];
            }
        }

    }

    public static void create() {
        int[][] new_cloud = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] >= 2 && cloud[i][j] == 0) {
                    new_cloud[i][j] = 1;
                    arr[i][j] -= 2;
                }
            }
        }
        cloud = new_cloud;
    }

    public static void magic() {
        cloud[N - 1][0] = 1;
        cloud[N - 1][1] = 1;
        cloud[N - 2][0] = 1;
        cloud[N - 2][1] = 1;
        for (int m = 0; m < M; m++) {
            move(m);
            water();
            create();
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/21610.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        cloud = new int[N][N];
        ds = new int[2][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            ds[0][i] = d;
            ds[1][i] = s;
        }
        magic();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += arr[i][j];
            }
        }
        System.out.println(count);
    }
}
