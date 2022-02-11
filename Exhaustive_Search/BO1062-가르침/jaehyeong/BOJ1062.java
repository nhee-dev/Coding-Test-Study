
import java.util.Arrays;
import java.util.Scanner;


public class BOJ1062 {
	
	static int maxCnt = 0;
	static int wordNumsN;
	static int charNumsK;
	static String[] strs;

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		wordNumsN = sc.nextInt();
		charNumsK = sc.nextInt();
		sc.nextLine();
				
		strs = new String[wordNumsN];
		int[][] needChar = new int[wordNumsN][26];
		
		for(int i = 0; i < wordNumsN; i++) {
			strs[i] = sc.nextLine();
			for(int j = 0; j < strs[i].length(); j++) {
				needChar[i][strs[i].charAt(j)-97] = 1;
				
			}
			needChar[i][0] = 0;
			needChar[i][13] = 0;
			needChar[i][19] = 0;
			needChar[i][8] = 0;
			needChar[i][2] = 0;

		}
		
		int[] arr = new int [] {1, 3, 4, 5, 6 , 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25};
		boolean[] visited = new boolean[arr.length];
		
		int k = charNumsK-5;
		comb(arr, visited, 0, k, needChar);
		System.out.println(maxCnt);
		
	}
	
	static void comb(int[] arr, boolean[] visited, int start, int r, int[][] needChar) {
		if(r == 0) {
			checkMaxCnt(arr, visited, needChar);
			return;
		} else {;
			for(int i = start; i < arr.length; i++) {
				visited[i] = true;
				comb(arr, visited, i+1, r-1, needChar);
				visited[i] = false;
				
			}
			
		}
		
	}
	
	static void checkMaxCnt(int[] arr, boolean[] visited, int[][] needChar) {
		
		int[][] needChar2 = new int [needChar.length][needChar[0].length];
		for(int i = 0; i < needChar.length; i ++) {
			needChar2[i] = needChar[i].clone();
			
		}
		
		for(int i = 0; i < arr.length; i ++) {
			if(visited[i] == true) {
				for(int j = 0; j < needChar2.length; j++) {
					needChar2[j][arr[i]] = 0;
							
				}
				
			}
			
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(visited[i]==true) {
			}
		}
		
		int maxS = 0;
		for(int ver = 0; ver < needChar2.length; ver++) {
			for(int hor = 0; hor < needChar2[0].length; hor++) {

				if(needChar2[ver][hor] != 0) {
					break;
				} else if(hor == (needChar2[0].length-1)) {
					maxS += 1;
					
				}
				
			}
			
		}
		if(maxS >= maxCnt) {
			maxCnt = maxS;
		}
		
	}

}
