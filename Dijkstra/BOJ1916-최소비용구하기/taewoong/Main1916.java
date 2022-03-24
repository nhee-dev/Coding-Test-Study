package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1916 {
    static int N;
    static int M;
    static Node[] arr;
    static int[] d;
    static int INF = Integer.MAX_VALUE;
    static boolean[] visited;

    private static class Node {
        List<Cost> w;

        public Node() {
            w = new ArrayList<>();
        }

    }

    private static class Cost implements Comparable<Cost> {
        int to;
        int cost;

        public Cost(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cost o) {
            if (this.cost < o.cost) return -1;
            else if (this.cost > o.cost) return 1;
            else return 0;
        }
    }

    public static void solve(int start, int end) {
        PriorityQueue<Cost> pq = new PriorityQueue<>();
        for (int i = 0; i < arr[start].w.size(); i++) {
            Cost w = arr[start].w.get(i);
            pq.add(w);
            if (w.cost < d[w.to]) d[w.to] = w.cost;
        }
        visited[start] = true;
        while (!pq.isEmpty()) {
            if (visited[end]) break;
            Cost v = pq.poll();
            if (!visited[v.to]) {
                for (int i = 0; i < arr[v.to].w.size(); i++) {
                    Cost w = arr[v.to].w.get(i);
                    if (!visited[w.to]) {
                        if (d[v.to] + w.cost < d[w.to]) {
                            d[w.to] = d[v.to] + w.cost;
                        }
                        pq.add(new Cost(w.to, d[w.to]));
                    }
                }
                visited[v.to] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/1916.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new Node[N + 1];
        d = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new Node();
        }
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[from].w.add(new Cost(to, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            d[i] = INF;
        }
        solve(start, end);
        System.out.println(d[end]);
    }
}
