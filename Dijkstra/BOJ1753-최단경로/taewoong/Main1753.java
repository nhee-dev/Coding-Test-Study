package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1753 {
    static int V;
    static int E;
    static Node[] arr;
    static int[] d;
    static int INF = Integer.MAX_VALUE;
    static boolean[] visited;

    private static class Node {
        List<Cost> u;

        public Node() {
            this.u = new ArrayList<>();
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

    public static void solve(int start) {
        PriorityQueue<Cost> pq = new PriorityQueue<>();
        visited[start] = true;
        Node node = arr[start];
        for (int i = 0; i < node.u.size(); i++) {
            Cost costNode = node.u.get(i);
            if (costNode.cost < d[costNode.to]) {
                d[costNode.to] = costNode.cost;
            }
            pq.add(costNode);
        }
        d[start] = 0;
        while (!pq.isEmpty()) {
            Cost costNode = pq.poll();
            int v = costNode.to;
            int cost = costNode.cost;
            if (!visited[v]) {
                visited[v] = true;
                for (int i = 0; i < arr[v].u.size(); i++) {
                    Cost u_costNode = arr[v].u.get(i);
                    int u = u_costNode.to;
                    if (!visited[u]) {
                        int u_cost = u_costNode.cost;
                        if (d[v] + u_cost < d[u]) {
                            d[u] = d[v] + u_cost;
                        }
                        pq.add(new Cost(u, d[u]));
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/1753.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        if (V == 1) {
            System.out.println(0);
            return;
        }
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        arr = new Node[V + 1];
        for (int i = 1; i <= V; i++) {
            arr[i] = new Node();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[u].u.add(new Cost(v, w));
        }
        d = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            d[i] = INF;
        }
        visited = new boolean[V + 1];
        solve(start);
        for (int i = 1; i <= V; i++) {
            if (d[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(d[i]);
            }
        }
    }
}
