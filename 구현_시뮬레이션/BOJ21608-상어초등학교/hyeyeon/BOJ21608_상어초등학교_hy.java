package A0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21608_상어초등학교_hy {
	
	static int N, stdNum;
	static int[][] seat;
	static int[][] friend;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int[] score = {0, 1, 10, 100, 1000};

	static boolean isSeat(int x, int y) {
		if(x<=0||x>N||y<=0||y>N) return false;
		return true;
	}

	static boolean isFavorite(int x, int y, int num) {
		for(int std:friend[num]) {
			if(seat[x][y]==std) return true;
		}
		
		return false;
	}

	static void findSeat(int[][] arr) {
		int selectX=0;
		int selectY=0;
		int maxfavorite=Integer.MIN_VALUE;
		int maxempty = Integer.MIN_VALUE;

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(seat[i][j]==0) {
					int tmpfavorite = 0;
					int tmpempty = 0;
					for(int k=0;k<4;k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(isSeat(nx,ny)) {
							if(isFavorite(nx,ny,stdNum)) tmpfavorite++;
							if(seat[nx][ny]==0) tmpempty++;
						}
					}
					
					if(maxfavorite<tmpfavorite) {
						maxfavorite = tmpfavorite;
						maxempty = tmpempty;
						selectX = i;
						selectY = j;
						
					}
					else if(maxfavorite==tmpfavorite) {
						if(maxempty<tmpempty) {
							maxempty=tmpempty;
							selectX = i;
							selectY = j;
						}
						else if(maxempty==tmpempty) {
							if(selectX>i) {
								selectX=i;
								selectY=j;
							}
							else if(selectX==i) {
								if(selectY>j) {
									selectX=i;
									selectY=j;
								}
							}
						}
					}
			}
		}
	}
	seat[selectX][selectY]=stdNum;
}
	static int getScore(int x, int y, int num) {
		int cnt=0;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(isSeat(nx,ny)) {
				if(isFavorite(nx,ny,num)) cnt++;
			}
		}
		
		return score[cnt];
	}
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		seat = new int[N+1][N+1];
		friend = new int[N*N+1][4];
		
		for(int i=0;i<N*N;i++) {
			st = new StringTokenizer(br.readLine());
			stdNum = Integer.parseInt(st.nextToken());
			for(int j=0;j<4;j++) {
				friend[stdNum][j]=Integer.parseInt(st.nextToken());
			}
			findSeat(seat);
		}
		
		int ans=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				ans+=getScore(i,j,seat[i][j]);
			}
		}
		System.out.println(ans);
		
		
	}

}
