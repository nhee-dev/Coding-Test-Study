import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // DNA 수
		int m = Integer.parseInt(st.nextToken()); // 문자열 길이

		// 자리별로 A C G T 출현 횟수를 저장할 배열
		// 코드 가독성을 위해 'A':65 ~ 'Z':90 보다 큰 100으로 설정
		int[][] nc = new int[100][m];

		String[] dna = new String[n];

		for (int i = 0; i < n; i++) {
			dna[i] = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = dna[i].charAt(j);
				nc[c][j]++;
			}
		}

		StringBuilder hd = new StringBuilder();

		int sum = 0;
		for (int i = 0; i < m; i++) {
			char c = 'A';
			int max = nc['A'][i];
			int cnt = nc['A'][i] + nc['C'][i] + nc['G'][i] + nc['T'][i];
			
			if (nc['C'][i] > max) {
				max = nc['C'][i];
				c = 'C';
			}
			if (nc['G'][i] > max) {
				max = nc['G'][i];
				c = 'G';
			}
			if (nc['T'][i] > max) {
				max = nc['T'][i];
				c = 'T';
			}
			hd.append(c);
			cnt -= nc[c][i];
			sum += cnt;
		}

		System.out.print(hd + "\n" + sum);

		br.close();
	}

}
