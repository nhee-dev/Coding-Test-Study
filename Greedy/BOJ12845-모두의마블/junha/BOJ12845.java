import java.io.*;
import java.util.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int sum = 0;
        int now = arr[N - 1];

        for (int i = N - 1; i > 0; i--) {
            sum += now + arr[i - 1];
            now = Math.max(now, arr[i - 1]);
        }


        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}
