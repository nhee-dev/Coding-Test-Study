package A0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339단어수학 {
	
	public static String[] arr; //입력 문자열 배열
	public static int[] alpha; //알파벳별 크기 저장?을 위한 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new String[N];
		alpha = new int[26]; //알파벳 대문자 26개
		for(int i=0;i<N;i++) {
			arr[i]=br.readLine();
		}
		for(int i=0;i<N;i++) {
			int x = (int)Math.pow(10, arr[i].length()-1);
			for(int j=0;j<arr[i].length();j++) {
				alpha[(int)arr[i].charAt(j)-65]+=x; //A면 0, B면 1 ... 에 x(10^자리수) 저장
				x=x/10;
			}
		}
		
		Arrays.sort(alpha); //정렬, 뒤에서부터 0전까지 9부터 차례로 할당 및 더하기
		int tmp=9;
		int answer=0;
		for(int i=alpha.length-1;i>0;i--) {
			if(alpha[i]==0) break; //뒤에서부터(큰 것부터) 9부터 차례로 주다가, 0을 만나면 멈춘다
			else {
				answer+=alpha[i]*tmp;
				tmp--;
			}
		}
		System.out.println(answer);
		
	}

}
