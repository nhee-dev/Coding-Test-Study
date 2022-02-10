// boj 1339 단어 수학 (20:07 ~ 21:00, 
package com.algo.week01;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1339 {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[] alphabet = new int[26];
		for (int i = 0; i < N; i++) 
		{
			String str = sc.nextLine();
			for (int j = str.length() - 1; j >= 0; j--) 
			{
				alphabet[str.charAt(j) - 'A'] 
						+= (int) Math.pow(10, str.length() - j - 1);
			}
		}
		
		Arrays.sort(alphabet);
		int sum = 0;
		int num = 9;
		for (int i = 25; i > 25 - 10; i--) {
			if(alphabet[i] == 0) break;
			
			sum += num * alphabet[i];
			num--;
		}
		
		System.out.println(sum);
	}
}