package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1969 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/1969.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        String DNA = "";
        int total = 0;
        for (int k = 0; k < M; k++) {
            int[] count = new int[4]; //ACGT
            for (int i = 0; i < N; i++) {
                char c = arr[i].charAt(k);
                if (c == 'A') count[0]++;
                else if (c == 'C') count[1]++;
                else if (c == 'G') count[2]++;
                else count[3]++;
            }
            int max_idx = 0;
            for (int j = 1; j < 4; j++) {
                if (count[j] > count[max_idx]) max_idx = j;
            }

            char max_c;
            if (max_idx == 0) max_c = 'A';
            else if (max_idx == 1) max_c = 'C';
            else if (max_idx == 2) max_c = 'G';
            else max_c = 'T';

            DNA += max_c;
            for (int i = 0; i < N; i++) {
                if (arr[i].charAt(k) != DNA.charAt(k)) total++;
            }
        }
        System.out.println(DNA);
        System.out.println(total);
    }
}
