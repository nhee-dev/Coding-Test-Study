package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503 {
    static int N;
    static int M;
    static int[][] arr;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int count;
    static int i;
    static int j;
    static int dir;

    public static void solve() {
        while (true){
            dir = (dir + 3) % 4;    // 왼쪽으로 방향 회전
            boolean flag = true;    // 4방향 탐색 후 청소할 곳이 없을 때 flag
            for (int n = 0; n < 4; n++, dir = (dir + 3) % 4) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (arr[newI][newJ] == 0) {
                    flag = false;
                    arr[newI][newJ] = 2;    // 청소했다고 표시
                    count++;
                    i = newI;
                    j = newJ;
                    break;
                }
            }
            if (flag){
                dir = (dir + 3) % 4;    // 후진할 방향
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (arr[newI][newJ] == 1) {
                    return;
                }
                // 원래 방향은 유지한채 후진
                dir = (dir + 2) % 4;
                i = newI;
                j = newJ;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/14503.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        i = Integer.parseInt(st.nextToken());
        j = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = 1;
        arr[i][j] = 2;
        solve();

        System.out.println(count);
    }
}
