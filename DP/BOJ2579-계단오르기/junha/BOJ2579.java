import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String args[]) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            bw.write(String.valueOf(arr[0]));

        } else if (N == 2) {
            bw.write(String.valueOf(arr[0] + arr[1]));

        } else if (N == 3) {
            bw.write(String.valueOf(Math.max(arr[1] + arr[2], arr[0] + arr[2])));

        } else {

            dp[0] = arr[0];
            dp[1] = arr[0] + arr[1];
            dp[2] = Math.max(arr[1] + arr[2], arr[0] + arr[2]);

            for (int i = 3; i < N; i++) {
                dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
            }

            bw.write(String.valueOf(dp[N-1]));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}