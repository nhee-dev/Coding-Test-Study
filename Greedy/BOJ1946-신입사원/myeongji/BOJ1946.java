package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ1946 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Pair> list = new ArrayList<>();
			for(int i=0; i<N; i++){
				String[] s = br.readLine().split(" ");
				list.add(new Pair(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
			}
			
			Collections.sort(list, new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					if(o1.val1 < o2.val1) return -1;
					else if(o1.val1 > o2.val1) return 1;
					return 0;
				}
			});
			
			int result = 1; // 서류 1등
			int min = list.get(0).val2;
			for(int i=1; i<N; i++) {
				if(list.get(i).val2 < min ) {
					min = list.get(i).val2;
					result++;
				}
			}
			
			
			System.out.println(result);
		}
		br.close();
	}

}
class Pair{
	int val1; // 서류
	int val2; // 면접
	public Pair(int val1, int val2) {
		super();
		this.val1 = val1;
		this.val2 = val2;
	}
	
}