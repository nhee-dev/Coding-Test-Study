package com.algo.week04;

import java.util.Scanner;

public class BOJ21610 {

	static int N, M;
	static int[][] arr;
	static int[] d;
	static int[] s;	
	static int[][] cloud; // i, j정보

	static int[] dj = { 0, -1, -1, 0, 1, 1, 1, 0, -1 }; // 0 : dummy
	static int[] di = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		d = new int[M];
		s = new int[M];		
		for (int i = 0; i < M; i++) {
			d[i] = sc.nextInt();
			s[i] = sc.nextInt();
		}
		
		solve();
	}

	private static void solve() {
		for (int num = 0; num < M; num++) {
			cloud =  new int[][] { {N-1, 1}, {N-1, 2}, {N, 1}, {N, 2} };
			int direction = d[num];
			int space = s[num];
			
			for (int i = 0; i < 4; i++) {
				// 행
				cloud[i][0] = cloud[i][0] + di[direction] * space;
				if (cloud[i][0] > 0)
					cloud[i][0] %= N;
					
				
				// 열
			}
		}
	}
}

// 파이어볼, 토네이도, 파이어스톰, 물복사버그
// 새로 배운 마법 - 비바라기 -> 비구름 생성
// N x N 격자에서 비바라기를 연습 (1,1) ~ (N,N)
// 칸 하나에 저장할 수 있는 물의 양 제한 X, A[r][c] : 바구니에 저장된 물의 양
// "1번 행"과 "N번 행", "1번 열"과 "N번 열"이 연결되어 있다.

// 비바라기 시전 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름 생성 (구름은 칸 전체 차지)
// 구름에 이동을 "M번" 명령
// 이동 명령 i - d_i, s_i
// 방향 : 8개의 정수! 
// x, y - (-1, 0), (-1, -1), (0, -1), (1, -1), 
//		   (1, 0), (1, 1), (0, 1), (-1, 1)

// 이동 방법
// 1. 모든 구름이 d_i 방향으로 s_i 칸 이동
// 2. "구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가" -> 이동한 다음이야 아니면 이동하면서 모두 포함이야.
// 3. 구름이 모두 사라진다.
// 4. 위에서 물이 증가한 칸에서 물복사버그 마법 시전
//	 물복사버그 마법 : 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 본인 물의 양이 증가
//	 대각선 방향이 거리가 1인 칸이 아니다.
// 	 바구니에 저장된 물의 양이 2 이사인 모든 칸에 구름이 생기고 물의 양이 2 줄어든다.
// 5. 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다. created{][]
//	-> 물의 양이 2 이상인 모든 칸에 -2한다 (단, non-visited만)
//  -> 이 visited는 오로지 그 이동 한 턴에만 영향을 준다.

