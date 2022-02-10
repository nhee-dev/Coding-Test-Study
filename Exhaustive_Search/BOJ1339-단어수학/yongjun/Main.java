import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static int visited[] = new int[26];
	public static ArrayList<Character> al = new ArrayList<>();
	public static int alSize = 0;
	public static String[] str;
	public static int n = 0;
	public static int sum = 0;

	public static void func(int cnt, int num, int idx) {
		if (cnt == 0) {
			int temp = 0;
			for (int i = 0; i < n; i++) {
				int size = str[i].length();
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < size; j++) {
					char c = str[i].charAt(j);
					sb.append(Integer.toString(visited[c - 'A']).charAt(0));
				}
				temp += Integer.parseInt(sb.toString()); // 문자열을 숫자로 바꾸고 더함
			}
			sum = Math.max(temp, sum);
			return;
		}
		for (int i = 0; i < alSize; i++) {
			char c = al.get(i);
			if (visited[c - 'A'] == -1) { // 방문한적 없다면
				visited[c - 'A'] = num; // 숫자를 추가(방문함)
				func(cnt - 1, num - 1, i);
				visited[c - 'A'] = -1; // 방문하지 않은것으로 바꿈
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		n = Integer.parseInt(br.readLine());

		str = new String[n];

		for (int i = 0; i < 26; i++) {
			visited[i] = -1;
		}

		for (int i = 0; i < n; i++) {
			str[i] = br.readLine(); // 문장을 읽음
			int size = str[i].length();
			for (int j = 0; j < size; j++) {
				char c = str[i].charAt(j);
				if (!al.contains(c)) {
					al.add(c); // ArrayList에 중복되지 않게 글자 추가
				}
			}
		}

		alSize = al.size();
		func(alSize, 9, 0);

		System.out.println(sum);
		br.close();
	}

}
