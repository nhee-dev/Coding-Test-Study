// boj21608 상어 초등학교 (17:30 ~ )
package com.algo.week04;

import java.util.Scanner;

public class BOJ21608 {
	
	static int N;
	static int[][] studentInfo;
	static int[][] like;
	static int[][] seat;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int max, maxNum;
	static int maxX, maxY;
	static int Answer;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		studentInfo = new int[N * N][5];
		for (int i = 0; i < N * N; i++) {
			for (int j = 0; j < 5; j++) {
				studentInfo[i][j] = sc.nextInt();
			}
		}
		
		seat = new int[N][N];
		for (int i = 0; i < N * N; i++) {
			check1(i); // 좋아하는 학생이 상하좌우에 가장 많은 칸 체크
			
			if (maxNum > 1) {
				check2(i); // maxNum이 여러개면 favorite == max인 것 중 비어있는 칸이 많은 곳으로.		
			}
		}
		
		getSatisfaction();
		
		System.out.println(Answer);
		
		/*
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", seat[i][j]);
			}
			System.out.println();
		}
		*/
	}

	private static void getSatisfaction() {
		
		int studentNo = 0;
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				
				for (int i = 0; i < N * N; i++) {
					if (seat[y][x] == studentInfo[i][0]) {
						studentNo = i;
						break;
					}
				}
				
				int newX, newY;
				int satisfaction = 0;
				for (int dir = 0; dir < 4; dir++) {
					newX = x + dx[dir];
					newY = y + dy[dir];
					if (!isWall(newX, newY)) {
						for (int idx = 1; idx <= 4; idx++) {
							if (seat[newY][newX] == studentInfo[studentNo][idx]) {
								satisfaction++;
								break;
							}
						}
					}
				}
				
				Answer += (int) Math.pow(10, satisfaction - 1);
				
			}
		}
		
	}

	private static void check2(int student) {
		int maxEmpty = 0;
		int emptyX = 0, emptyY = 0;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (seat[y][x] == 0) {
					emptyX = x; emptyY = y;
					break;
				}
			}
		}

		
		int[][] empty = new int[N][N];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (max == like[y][x] && seat[y][x] == 0) {
					// 상하좌우에 비어있는 칸이 몇 개 있는가?
					int newX, newY;
					for (int dir = 0; dir < 4; dir++) {
						newX = x + dx[dir];
						newY = y + dy[dir];
						if (!isWall(newX, newY) && seat[newY][newX] == 0) {
							empty[y][x]++;
						}
					}
				}
				
				if (maxEmpty < empty[y][x]) {
					maxEmpty = empty[y][x];
					emptyX = x;
					emptyY = y;
				}
			}
		}
		
		seat[emptyY][emptyX] = studentInfo[student][0];
	}

	private static void check1(int student) {
		max = 0; maxNum = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (seat[y][x] == 0) {
					maxX = x; maxY = y;
					break;
				}
			}
		}
		
		like = new int[N][N];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (seat[y][x] == 0) {
					// 상하좌우에 좋아하는 학생 몇명 있는가?
					int newX, newY;
					for (int dir = 0; dir < 4; dir++) {
						newX = x + dx[dir];
						newY = y + dy[dir];
						if (!isWall(newX, newY)) {
							for (int idx = 1; idx <= 4; idx++) {
								if (seat[newY][newX] == studentInfo[student][idx]) {
									like[y][x]++;
									break;
								}
							}
						}
					}
					
					if (max == like[y][x]) {
						maxNum++;
					}
					else if (max < like[y][x]) {
						max = like[y][x];
						maxNum = 1;
						maxX = x; maxY = y;
					}					
				}
			}
		}
		
		if (maxNum == 1) {
			seat[maxY][maxX] = studentInfo[student][0];
		}
	}

	private static boolean isWall(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}

// 코드 짜고 나서 드는 생각 seat == 0인 걸로 한정해서 체크했어야 했나...? ㅜㅠㅠㅠ
// 시간 초과도 아니고 틀렸습니다...라....ㅠ...

// 우선순위 배치

// 