package baekjoon;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

public class Main1339 {
    static char[] arr;
    static int size = 0;

    public static void cal(int n) {
        for (int i = 0; i < size; i++) {

        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1339.txt"));

        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());
        String[] s = new String[N];
        HashSet<Character> set = new HashSet<>();
        arr = new char[10];

        for (int i = 0; i < N; i++) {
            s[i] = sc.nextLine();
        }

//        System.out.println('A' - 'A');
        for (String word : s) {
            for (int i = 0; i < s.length; i++) {
                set.add(word.charAt(i));
            }
        }

        size = 0;
        for (char c : set) {
            arr[size++] = c;
        }

        cal(9);
    }
}
