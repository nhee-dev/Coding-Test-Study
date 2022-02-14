import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static int[] made;
    static HashSet<Integer> hashSet = new HashSet<>();

    public static void recur(int now, int end, int cnt) {
        if (cnt == end) { // 종료 조건
            int sum = 0;
            for (int i = 0; i < end; i++) {
                sum += made[i];
            }

            hashSet.add(sum); // 중복을 제거하며 결과를 보관
            return;
        }

        for (int i = now + 1; i < N; i++) {
            made[cnt] = arr[i];
            recur(i, end, cnt + 1); // 재귀 실행
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        made = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) { // 부분수열의 길이
            for (int j = 0; j < N; j++) { // 첫번째 요소
                made[0] = arr[j];
                recur(j, i, 1); // 재귀 실행
            }
        }

        int num = 1;
        while (true) {
            if (!hashSet.contains(num)) { // 도출한 결과중에 해당 값이 있는지 체크
                break;
            }
            num++;
        }

        bw.write(String.valueOf(num));
        bw.flush();
        bw.close();
        br.close();
    }
}

