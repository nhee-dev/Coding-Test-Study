
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1339 {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		int strCnt = Integer.parseInt(sc.nextLine());
		int[] alpabRk = new int[26];
				
		for(int i = 0; i < strCnt; i++) {
			String s = sc.nextLine();
			char[] c = s.toCharArray();
			int powNum = -1;
			for(int j = c.length-1; j >= 0; j--) {
				int cRk = (int) (1 * Math.pow(10, ++powNum));
				alpabRk[c[j] - 65] += cRk;
				
			}
			
		}
		
		Arrays.sort(alpabRk);
		
		int sum = 0;
		int mltiNum = 9;
		for(int i = 25; i >= 0; i--) {
			if(alpabRk[i] == 0) {
				continue;
			}
			sum += alpabRk[i] * mltiNum--;
			
		}

		System.out.println(sum);

		
		
		
	}

}