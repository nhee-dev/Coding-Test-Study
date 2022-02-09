import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static int n;
	public static String[] v;
	public static ArrayList<Character> al = new ArrayList<>();
	public static boolean[] visited = new boolean[10];
	public static int max = 0;

	public static void func(int d) {
		if (d == al.size()) {
			return;
		}
		for (int i = 9; i > -1; i--) {
			visited[i] = false;
			func(d + 1);

		}

	}

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

		n = Integer.parseInt(br.readLine());
		v = new String[n];

		for (int i = 0; i < n; i++) {
			v[i] = br.readLine(); // 입력
			int size = v[i].length();
			for (int j = 0; j < size; j++) {
				char c = v[i].charAt(j);
				if (!al.contains(c)) {
					al.add(c);
				}
			}
		}

		Collections.sort(al);

		/*
		 * for (int i = 0; i < al.size(); i++) { System.out.print(al.get(i) + " "); }
		 */

		int[] arr = new int[al.size()];

		func(al.size());

		br.close();
	}

}
