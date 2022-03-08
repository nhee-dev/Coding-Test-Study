package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ14891 {

	static int[][] gear = new int[4][8];
	public static void rotate(int n, int d) {
		int[] a = new int[8];
		
		if(d == 1) { // 시계
			for(int i=0; i<7; i++) {
				a[i+1] = gear[n][i];
			}
			a[0] = gear[n][7];
			
		}else {// 반시계
			for(int i=7; i>0; i--) {
				a[i-1] = gear[n][i];
			}
			a[7] = gear[n][0];
		}
		for(int i=0; i<8; i++) {
			gear[n][i] = a[i];
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<4; i++) {
			String s = br.readLine();
			for(int j=0; j<8; j++) {
				gear[i][j] = s.charAt(j)-'0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		int[] state = new int[4];

		for(int i=0; i<K; i++) {
			String[] s = br.readLine().split(" ");
			int n = Integer.parseInt(s[0])-1;
			int d = Integer.parseInt(s[1]);
			Arrays.fill(state, 0);
			state[n] = d;
		
			for(int j=n; j<3; j++) {
				if(gear[j][2] != gear[j+1][6]) state[j+1] = -state[j];
			}
			
			for(int j=n; j>0; j--) {
				if(gear[j][6] != gear[j-1][2]) state[j-1] = -state[j];
				
			}
			for(int j=0; j<4; j++) {
				if(state[j] != 0) rotate(j, state[j]);
			}
			
		}
		int score = 0;
		for(int i=0; i<4; i++) {
			if(gear[i][0]==1)
				score+=Math.pow(2, i);
		}
//		score += gear[0][0];
//		score += gear[1][0];
//		score += gear[2][0];
//		score += gear[3][0];
		System.out.println(score);
	}

}
