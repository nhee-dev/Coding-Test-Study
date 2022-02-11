package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1062 {
    static String[] sArr;
    static boolean[] used;
    static int max;

    public static void com(int K, int start) {
        if (K == 0) {
            int count = 0;
            for (String s : sArr) {
                if (s == "") {
                    count++;
                } else {
                    boolean flag = true;
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (c != 'a' && c != 'n' && c != 't' && c != 'i' && c != 'c') {
                            if (!used[c - 'a']) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        count++;
                    }
                }
            }
            max = Math.max(count, max);
            return;
        }

        // 중복 제거
        for (int i = start + 1; i < 26; i++) {
            if (!used[i]) {
                used[i] = true;
                com(K - 1, i);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/1062.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()) - 5;
        if (K < 0) {
            System.out.println(0);
            return;
        }
        sArr = new String[N];
        used = new boolean[26];
        max = 0;

        int count = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            s = s.substring(4, s.length() - 4);
            sArr[i] = s;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c != 'a' && c != 'n' && c != 't' && c != 'i' && c != 'c') {
                    count++;
                }
            }
        }
        if (K > count) K = count;
        com(K, 0);
        System.out.println(max);
    }
}
