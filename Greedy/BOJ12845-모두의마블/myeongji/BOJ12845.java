package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12845 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] card = new int[n];
		String[] s = br.readLine().split(" ");
	
		for(int i=0; i<n; i++) {
			card[i] = Integer.parseInt(s[i]);
		}
		
		Arrays.sort(card);	
		int result = 0;
		for(int i=0; i<n-1; i++) {
			result+=(card[i]+card[n-1]);
		}
		System.out.println(result);
	}

}
