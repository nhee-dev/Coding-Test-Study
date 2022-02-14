import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] arr;
    static int[][] visit;
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<position> q_ice = new LinkedList<>(); // 빙산의 위치
    static Queue<position> q_temp = new LinkedList<>(); // 없애줄 빙산 위치 저장
    static Queue<position> q_bfs = new LinkedList<>(); // bfs용
    static int remain = 0;
    static int year = 0;
    static int cnt_bfs = 0;

    static class position {
        int x;
        int y;

        position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void melt() { // 녹여주기

        int nx, ny;
        position now;

        int k = q_ice.size();
        for (int ice = 0; ice < k; ice++) {
            now = q_ice.poll();
            int num = 0;
            for (int i = 0; i < 4; i++) {
                nx = now.x + d[i][0];
                ny = now.y + d[i][1];

                if (arr[nx][ny] == 0) {
                    num++;
                }
            }

            if (arr[now.x][now.y] - num <= 0) {
                q_temp.add(new position(now.x, now.y)); // 바로 녹이지 않고 q_temp 사용
            } else {
                arr[now.x][now.y] -= num;
                q_ice.add(now);
            }
        }

        while (!q_temp.isEmpty()) { // 완전히 사라질 칸 처리
            now = q_temp.poll();

            arr[now.x][now.y] = 0;
            remain--;
        }
    }

    public static void bfs(position p) {  // 한덩어리인지 확인인
       int nx, ny;

        for (int i = 0; i < 4; i++) {
            nx = p.x + d[i][0];
            ny = p.y + d[i][1];

            if (visit[nx][ny] == year || arr[nx][ny] == 0) {
                continue;
            }

            visit[nx][ny] = year;
            q_bfs.add(new position(nx, ny));
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(visit[i], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] > 0) {
                    remain++;
                    q_ice.add(new position(i, j));
                }
            }
        }

        while (true) {
            melt();

            if (q_ice.isEmpty()) {
                year = 0;
                break;
            }

            q_bfs.add(q_ice.peek());
            cnt_bfs = 0;

            while (!q_bfs.isEmpty()) {
                position now = q_bfs.poll();

                visit[now.x][now.y] = year;
                cnt_bfs++;
                bfs(now);
            }

            year++;
            if (cnt_bfs != remain) {
                break;
            }
        }

        bw.write(String.valueOf(year));
        bw.flush();
        bw.close();
        br.close();
    }
}

