package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2579 {
    static int[] arr;
    static int[] D;
    static int N;


    public static int dp(int n) {
        if (n < 0) return 0;
        if (n == 0) return D[0] = arr[0];
        if (n == 1) return D[1] = arr[0] + arr[1];
        if (D[n] != 0) return D[n];
        return D[n] = Math.max(dp(n - 2) + arr[n], dp(n - 3) + arr[n - 1] + arr[n]);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/2579.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        D = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(dp(N - 1));
    }
}
