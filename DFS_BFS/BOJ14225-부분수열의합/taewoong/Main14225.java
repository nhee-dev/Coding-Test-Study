package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14225 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14225.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] count = new int[2000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < (1 << N); i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            count[sum]++;
        }

        int flag = 1;
        for (int i = 1; i < 2000001; i++) {
            if (count[i] == 0) {
                flag = i;
                break;
            }
        }
        System.out.println(flag);
    }
}
