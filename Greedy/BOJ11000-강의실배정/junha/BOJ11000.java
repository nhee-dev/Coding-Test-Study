import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> q = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        int i;
        int[][] arr = new int[N][2];
        StringTokenizer st;
        for (i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });

        q.add(arr[0][1]);
        for (i = 1; i < N; i++) {
            if (arr[i][0] >= q.peek())
                q.poll();
            q.add(arr[i][1]);
        }

        bw.write(String.valueOf(q.size()) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}