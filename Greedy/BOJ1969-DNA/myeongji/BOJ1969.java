package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1969 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		String[] str = new String[N];
		char[] c; // A, C, G, T
		char[] alphabet = {'A', 'C', 'G', 'T'};

		for(int i=0; i<N; i++) {
			str[i] = br.readLine();
		}
		
		int hamming_distance = 0;
		String result = "";
		for(int i=0; i<M; i++) {
			c = new char[4];
			for(int j=0; j<N; j++) {
				if(str[j].charAt(i) == 'A') c[0]++;
				if(str[j].charAt(i) == 'C') c[1]++;
				if(str[j].charAt(i) == 'G') c[2]++;
				if(str[j].charAt(i) == 'T') c[3]++;
			}
			int max = Integer.MIN_VALUE;
			char max_c = ' ';
			for(int j=0; j<4; j++) {
				if(max < c[j]) {
					max = c[j];
					max_c = alphabet[j];
				}
			}
			result += max_c;
			hamming_distance += (N-max);
		}
		System.out.println(result);
		System.out.println(hamming_distance);
		br.close();

	}

}
