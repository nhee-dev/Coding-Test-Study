package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12845 {
    static int N;
    static int[] arr;
    static int size;
    static int total;

    public static boolean isIn(int newI) {
        if (newI >= 0 && newI < size) return true;
        return false;
    }

    public static void solve(int idx) {
        while (size > 1) {
            int left = 0;
            int right = 0;

            if (isIn(idx - 1)) {
                left = arr[idx - 1];
            }
            if (isIn(idx + 1)) {
                right = arr[idx + 1];
            }

            // 오른쪽이 더 큰경우
            if (right > left) {
                total += arr[idx] + right;
                for (int i = idx + 2; i < size; i++) {
                    arr[i - 1] = arr[i];
                }
            } else {    // 왼쪽이 더 큰경우
                total += arr[idx] + left;
                for (int i = idx; i < size; i++) {
                    arr[i - 1] = arr[i];
                }
            }
            size--;
            idx = findMax();
        }
    }

    public static int findMax() {
        int max_idx = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i] > arr[max_idx]) {
                max_idx = i;
            }
        }
        return max_idx;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/12845.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        size = N;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(findMax());
        System.out.println(total);
    }
}
