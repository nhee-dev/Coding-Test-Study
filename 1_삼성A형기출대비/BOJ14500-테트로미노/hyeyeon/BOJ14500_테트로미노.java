package 기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {
	
	static int map[][];
	static boolean visited[][];
	static int N, M;
	static int answer;
	static int max;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	static void dfs(int x, int y, int depth, int sum) {
		if(depth==4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isIn(nx,ny)) continue;
			if(visited[nx][ny]) continue;
			visited[nx][ny]=true;
			dfs(nx,ny,depth+1,sum+map[nx][ny]);
			visited[nx][ny]=false;
		}
	}
	
	static boolean isIn(int x, int y) {
		if(x<0||x>=N||y<0||y>=M) return false;
		return true;
	}
	
	static void unique(int x, int y) {
		int wing = 4; //상하좌우 날개
		int min = Integer.MAX_VALUE;
		int sum = map[x][y];
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			//날개가 맵 바깥에 있다면
			if(!isIn(nx,ny)) {
				wing--;
				continue;
			}
			if(wing<=2) return; //날개가 두개 이하면 ㅗ,ㅜ,ㅓ,ㅏ 모양이 안나옴
			min = Math.min(min, map[nx][ny]);
			sum+=map[nx][ny];
			
			//날개가 4개일 때는 가장 작은 부분을 제거해야함
			if(wing==4) {
				sum-=min;
			}
			max = Math.max(sum, max);
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		max=0;
		visited = new boolean[N][M];
	
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());	
			}
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dfs(i,j,0,map[i][j]);
				unique(i,j);
			}
		}
	
		System.out.println(max);
	
	}

}
