import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static String[] str;
	public static int n = 0;
	public static boolean[] l = new boolean[26];
	public static int read = 0;

	public static void func(int k, int idx) {
		if (k == 0) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				int len = str[i].length();
				boolean flag = true;
				for (int j = 4; j < len - 4; j++) { // anta ** tica 형태이니 앞 4글자, 뒤 4글자는 제외
					char c = str[i].charAt(j);
					if (!l[c - 'a']) { // 읽을 수 없는 글자가 있다면
						flag = false;
						break;
					}
				}
				if (flag) {
					cnt++;
				}
			}
			read = Math.max(cnt, read);
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (!l[i]) { // 배우지 않은 글자이라면
				l[i] = true;
				func(k - 1, i);
				l[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 단어의 개수
		int k = Integer.parseInt(st.nextToken()); // 가르칠 수 있는 글자

		if (k < 5) { // 가르칠 수 있는 단어가 5개보다 작다면
			System.out.println(0); // 읽을 수 있는 단어는 없다.
			br.close();
			return;
		}
		if (k == 26) { // 모든 알파벳을 가르칠 수 있다면
			System.out.println(n); // 모든 문장을 읽을 수 있다.
			br.close();
			return;
		}

		str = new String[n];

		for (int i = 0; i < n; i++) { // 문자열을 읽어들인다.
			str[i] = br.readLine();
		}

		// 반드시 들어가는 글자 체크
		l['a' - 'a'] = true;
		l['n' - 'a'] = true;
		l['t' - 'a'] = true;
		l['i' - 'a'] = true;
		l['c' - 'a'] = true;

		func(k - 5, 0);

		System.out.print(read);

		br.close();
	}

}
