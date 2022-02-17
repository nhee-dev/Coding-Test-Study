import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int T, N, M, K;
    static int[][]arr = new int[51][51];
    static boolean[][]visit = new boolean[51][51];
    static int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};

    public static void dfs(int x, int y){
        int nx, ny;

        for (int i = 0; i < 4; i++) {
            nx = x + d[i][0];
            ny = y + d[i][1];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] == true || arr[nx][ny] == 0)
                continue;

            visit[nx][ny] = true;
            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        
        T = Integer.parseInt(br.readLine());
        

        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int count = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                
                arr[y][x] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(visit[i][j] == false && arr[i][j] == 1){
                        visit[i][j] = true;
                        dfs(i,j);
                        count++;
                    }
                }
            }

            for (int i = 0; i < 50; i++) {
                Arrays.fill(arr[i], 0);
                Arrays.fill(visit[i], false);
            }
            bw.write(String.valueOf(count) + "\n");
        }


        bw.flush();
        bw.close();
        br.close();
    }
}