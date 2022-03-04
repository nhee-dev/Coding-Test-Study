import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int[][] gear;

	public static void rotate(int n, int d) {
		if (d == 1) { // 시계방향 회전
			int tmp = gear[n][7];
			for (int i = 6; i >= 0; i--) {
				gear[n][i + 1] = gear[n][i];
			}
			gear[n][0] = tmp;
		} else { // 반시계방향 회전
			int tmp = gear[n][0];
			for (int i = 0; i < 7; i++) {
				gear[n][i] = gear[n][i + 1];
			}
			gear[n][7] = tmp;
		}
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);

		gear = new int[4][8];

		for (int i = 0; i < 4; i++) {
			String s = sc.next();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}

		int k = sc.nextInt();

		for (int i = 0; i < k; i++) {
			int n = sc.nextInt() - 1; // 번호
			int d = sc.nextInt(); // 방향

			int[] flag;
			switch (n) {
			case 0:
				flag = new int[4];
				flag[0] = d; // -1 : 반시계 회전 | 0 : 그대로 | 1 : 시계 회전
				// 앞에서 회전이 있었고, 마주한 칸이 서로 다르면
				if (flag[0] != 0 && gear[0][2] != gear[1][6]) {
					flag[1] = -d;
				}
				if (flag[1] != 0 && gear[1][2] != gear[2][6]) {
					flag[2] = d;
				}
				if (flag[2] != 0 && gear[2][2] != gear[3][6]) {
					flag[3] = -d;
				}
				for (int j = 0; j < 4; j++) {
					if (flag[j] != 0) {
						rotate(j, flag[j]);
					}
				}
				break;
			case 1:
				flag = new int[4];
				flag[1] = d; // -1 : 반시계 회전 | 0 : 그대로 | 1 : 시계 회전
				// 앞에서 회전이 있었고, 마주한 칸이 서로 다르면
				if (flag[1] != 0 && gear[0][2] != gear[1][6]) {
					flag[0] = -d;
				}
				if (flag[1] != 0 && gear[1][2] != gear[2][6]) {
					flag[2] = -d;
				}
				if (flag[2] != 0 && gear[2][2] != gear[3][6]) {
					flag[3] = d;
				}
				for (int j = 0; j < 4; j++) {
					if (flag[j] != 0) {
						rotate(j, flag[j]);
					}
				}
				break;
			case 2:
				flag = new int[4];
				flag[2] = d; // -1 : 반시계 회전 | 0 : 그대로 | 1 : 시계 회전
				// 앞에서 회전이 있었고, 마주한 칸이 서로 다르면
				if (flag[2] != 0 && gear[1][2] != gear[2][6]) {
					flag[1] = -d;
				}
				if (flag[2] != 0 && gear[2][2] != gear[3][6]) {
					flag[3] = -d;
				}
				if (flag[1] != 0 && gear[0][2] != gear[1][6]) {
					flag[0] = d;
				}
				for (int j = 0; j < 4; j++) {
					if (flag[j] != 0) {
						rotate(j, flag[j]);
					}
				}
				break;
			case 3:
				flag = new int[4];
				flag[3] = d; // -1 : 반시계 회전 | 0 : 그대로 | 1 : 시계 회전
				// 앞에서 회전이 있었고, 마주한 칸이 서로 다르면
				if (flag[3] != 0 && gear[2][2] != gear[3][6]) {
					flag[2] = -d;
				}
				if (flag[2] != 0 && gear[1][2] != gear[2][6]) {
					flag[1] = d;
				}
				if (flag[1] != 0 && gear[0][2] != gear[1][6]) {
					flag[0] = -d;
				}
				for (int j = 0; j < 4; j++) {
					if (flag[j] != 0) {
						rotate(j, flag[j]);
					}
				}
				break;
			}
		}

		System.out.print(gear[0][0] * 1 + gear[1][0] * 2 + gear[2][0] * 4 + gear[3][0] * 8);
	}

}
