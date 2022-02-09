package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 단어수학 {


	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] word = new String[N];
		int[] alphabet = new int[26];//A~Z
	
	
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			int digit = (int) Math.pow(10, line.length()-1); //자리수
			for(int j=0; j<line.length(); j++) {
				int idx = line.charAt(j) - 'A';
				alphabet[idx] += digit;
				digit/=10;
			}
		}
		
		//정렬
		Arrays.sort(alphabet);
	
		
		int result = 0;
		for(int i=25; i>=17; i--) {
			result += alphabet[i]*(i-16);
		}
		System.out.println(result);
		br.close();
	}

}
